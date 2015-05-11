package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.swing.JOptionPane;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.EDIImpl.reportModel.IReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.entity.GroovyEntityEditor;
import com.sf.module.EDI.util.CodeUtils;

/**
 * 描述：Groovy脚本Entity
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-09-22      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class GroovyEntity extends AbstractEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3984169365140845115L;

	private static Logger logger = LoggerFactory.getLogger(GroovyEntity.class);

	public static final String TYPE = "GroovyEntity";

	/**
	 * 取数据库中所有的字段
	 */
	private String key = KEY_ALL_FIELDS;

	/**
	 * 脚本
	 */
	private String script = "";

	/**
	 * 脚本引擎
	 */
	// 不序列化
	private transient ScriptEngine engine;

	/**
	 * 临时值
	 */
	private Map<String, Object> rowMap;

	private transient boolean showCreateMsg = false;

	private transient boolean showRunMsg = false;

	public GroovyEntity() {

	}

	public GroovyEntity(String script) {
		this.script = script;
	}

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<entity type=\"");
		sbxml.append(TYPE);
		sbxml.append("\">");
		// 需要特殊处理，因为有特殊字符，包括回车，换行，括号等
		sbxml.append(CodeUtils.xmlEncode(this.script));
		sbxml.append("</entity>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {
		String script = xml.getText();
		if (script != null && !"".equals(script)) {
			setScript(script);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object obj) {
		if (obj == null || !(obj instanceof Map)) {
			rowMap = null;
		} else {
			rowMap = (Map<String, Object>) obj;
		}
	}

	@Override
	public String getViewStr() {
		return "Groovy";
	}

	@Override
	public Object getValue() {
		if (script == null || "".equals(script)) {
			return ICell.NULL_VALUE;
		}
		if (rowMap == null) {
			return ICell.NULL_VALUE;
		}
		// 运行脚本
		// 只需要提示一次就行了
		// 如果异常就返回null
		if (engine == null) {
			if (!showCreateMsg) {// 还没有创建过
				showCreateMsg = true;
				try {
					ScriptEngineManager manager = new ScriptEngineManager();
					engine = manager.getEngineByName("Groovy");
				} catch (Exception e) {
					logger.error("创建脚本引擎异常", e);
					JOptionPane.showMessageDialog(null, "创建脚本引擎异常，无法运行脚本！");
				}
			} else {
				// 创建失败了
				return ICell.NULL_VALUE;
			}
		}

		try {
			SimpleBindings sbind = new SimpleBindings();
			sbind.putAll(rowMap);
			// 设置全局变量
			sbind.put(IReportModel.GLOBAL_CONTEXT, getGlobalContext());
			return engine.eval(script, sbind);
		} catch (ScriptException e) {
			// 提示一次
			if (!showRunMsg) {
				showRunMsg = true;
				logger.error("运行脚本异常", e);
				JOptionPane.showMessageDialog(null,
						"运行脚本异常!\r\n" + e.getMessage());
			}

			return ICell.NULL_VALUE;
		}
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean canEdit() {
		return true;
	}

	@Override
	public IEntity edit(Window window) {
		// 调用弹出式编辑器编辑该Entity
		GroovyEntity tmp = new GroovyEntity(this.getScript());
		GroovyEntityEditor.getInstance(window).showEdit(tmp);
		return tmp;
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
