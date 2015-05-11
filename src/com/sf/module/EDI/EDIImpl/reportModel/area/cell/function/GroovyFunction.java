package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

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
import com.sf.module.EDI.EDIImpl.ui.function.GroovyFunctionEditor;
import com.sf.module.EDI.util.CodeUtils;

/**
 * 描述：Groovy脚本函数
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-8-15      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class GroovyFunction extends AbstractFunction {

	private static final long serialVersionUID = -6439292530141578772L;

	private static Logger logger = LoggerFactory.getLogger(GroovyFunction.class);

	public static final String TYPE = "groovy";

	/**
	 * 脚本
	 */
	private String script = "return value;";

	/**
	 * 脚本引擎
	 */
	// 不序列化
	private transient ScriptEngine engine;

	/**
	 * 临时值
	 */
	private Object tmpValue;

	/**
	 * 行数据
	 */
	private Map<String, Object> rowMap;

	private transient boolean showCreateMsg = false;

	private transient boolean showRunMsg = false;

	/**
	 * <pre>
	 * 设置行数据
	 * </pre>
	 * 
	 * @param rowMap
	 */
	public void setRowMap(Map<String, Object> rowMap) {
		this.rowMap = rowMap;
	}

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" \"");
		if (script.length() > 10) {// 只显示10个字符
			strbuf.append(script.subSequence(0, 10));
			strbuf.append("...");
		} else {
			strbuf.append(script);
		}
		strbuf.append("\" ");
		return strbuf.toString();
	}

	@Override
	public Object getValue() {

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
				return null;
			}
		}

		try {
			SimpleBindings sbind = new SimpleBindings();
			sbind.put("value", tmpValue);
			// 设置全局变量
			sbind.put(IReportModel.GLOBAL_CONTEXT, getGlobalContext());
			sbind.putAll(rowMap);
			return engine.eval(script, sbind);
		} catch (ScriptException e) {
			// 提示一次
			if (!showRunMsg) {
				showRunMsg = true;
				logger.error("运行脚本异常", e);
				JOptionPane.showMessageDialog(null,
						"运行脚本异常!\r\n" + e.getMessage());
			}

			return null;
		}
	}

	@Override
	public void setValue(Object value) {
		this.tmpValue = value;

	}

	@Override
	public String getType() {
		return GroovyFunction.TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<function type=\"");
		sb.append(GroovyFunction.TYPE);
		sb.append("\">");
		// 需要特殊处理，因为有特殊字符，包括回车，换行，括号等
		sb.append(CodeUtils.xmlEncode(this.script));
		sb.append("</function>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "脚本";
	}

	@Override
	public void fromXml(Element xml) {
		String script = xml.getText();
		if (script != null && !"".equals(script)) {
			setScript(script);
		}
	}

	@Override
	public boolean canEdit() {
		return true;
	}

	@Override
	public boolean canDelete() {
		return true;
	}

	@Override
	public void showEditor(Window windows) {
		GroovyFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public boolean containEntity() {
		return true;
	}

	@Override
	public void reset() {
		this.script = "return value;";
		this.tmpValue = null;
		this.rowMap = null;
		this.showCreateMsg = false;
		this.showRunMsg = false;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
