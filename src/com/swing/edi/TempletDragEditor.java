package com.swing.edi;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.module.EDI.DIYAssociate.domain.DIYAssociate;
import com.sf.module.EDI.DIYTemplet.domain.DIYTemplet;
import com.sf.module.EDI.EDIImpl.dragDrop.EdiTableTransferHandler;
import com.sf.module.EDI.EDIImpl.dragDrop.EdiTreeTransferHandler;
import com.sf.module.EDI.EDIImpl.report.utils.ReportUtils;
import com.sf.module.EDI.EDIImpl.reportModel.ReportModel;
import com.sf.module.EDI.EDIImpl.reportModel.area.BodyArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.HeadArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TailArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.BaseEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.ConstantEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.ExchangeCNY2USDEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.GoodNoUnitEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.GoodNoUnitNameEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.GroovyEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.QuantityEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.QuantityOneByOneEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.TabEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.UnitEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.UnitEntity1;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.ValueOneByOneEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.CountFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.DateFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.FillFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.GroovyFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.JFConvertFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.ReplaceFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.SequenceFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.SubstringFunction;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.SumFunction;
import com.sf.module.EDI.EDIImpl.ui.orderConditionComboBox.OrderComboBox;
import com.sf.module.EDI.EDIImpl.ui.table.CtrlScrollMouseListener;
import com.sf.module.EDI.EDIImpl.ui.table.DragTableColumnModelListener;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTable;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableCellEditor;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableCellRenderer;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableColumnModel;
import com.sf.module.EDI.EDIImpl.ui.table.EdiTableModel;
import com.sf.module.EDI.EDIImpl.ui.table.ScrollAdjustmentListener;
import com.sf.module.EDI.EDIImpl.ui.table.TableHeadCellRenderer;
import com.sf.module.EDI.EDIImpl.ui.table.TableHeadMouseListener;
import com.sf.module.EDI.EDIImpl.ui.table.TableMouseListener;
import com.sf.module.EDI.EDIImpl.ui.table.TableUtils;
import com.sf.module.EDI.EDIImpl.ui.tree.EdiMutableTreeNode;
import com.sf.module.EDI.EDIImpl.ui.tree.EdiTreeCellRenderer;

/**
 * 描述：模板拖拽编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TempletDragEditor extends JDialog {

	private static final long serialVersionUID = -1291571444495787289L;

	/**
	 * 文件类型====文本文件
	 */
	public static final String FILE_TYPE_TXT = "01";

	/**
	 * 文件类型====Excel文件
	 */
	public static final String FILE_TYPE_EXCEL = "02";

	/**
	 * 文件类型====Word文件
	 */
	public static final String FILE_TYPE_WORD = "03";

	private static Logger logger = LoggerFactory
			.getLogger(TempletDragEditor.class);

	// private JPanel contentPane;
	private JScrollPane scrollPane_head;
	private EdiTable headTable;
	private JScrollPane scrollPane_body;
	private EdiTable bodyTable;
	private JScrollPane scrollPane_tail;
	private EdiTable tailTable;
	private JTree tree;
	/**
	 * 搜索key
	 */
	private JTextField textField_searchKey;
	/**
	 * 面板1 模板文件名
	 */
	private JTextField textField_fileName;

	/**
	 * 面板1 模板备注信息
	 */
	private JTextField textField_note;

	private JPanel panel_right;// 右边面板，有两个card页
	private CardLayout cardLayout;

	/**
	 * 文件名编辑表格
	 */
	private EdiTable table_fileName;
	/**
	 * 模板名称
	 */
	private JTextField txt_templetname;

	/**
	 * 生成文件行数限制
	 */
	private JFormattedTextField fTextField_rowLimit;
	/**
	 * 口岸
	 */
	private JTextField cusdeptcodePane;
	/**
	 * 进出口标识
	 */
	private JComboBox comboBox_ieflag;
	/**
	 * 按钮名称
	 */
	private JComboBox comboBox_btnName;

	/**
	 * 报关类型
	 */
	private JTextField gateTypePane;

	/**
	 * 导出文件类型
	 */
	private JComboBox comboBox_fileType;

	/**
	 * Excel工作表命名
	 */
	private JLabel label_sheetName;
	private JTextField textfield_sheetName;

	/**
	 * 文本文件列分隔符
	 */
	private JTextArea textArea_colSplit;
	private JLabel label_colSplit;
	private JScrollPane scrollPane_split;

	/**
	 * 文本文件换行符号
	 */
	private JLabel label_crlf;
	private JComboBox comboBox_crlf;

	/**
	 * 是否拆分多品名--标准格式(普通格式)
	 */
	private JCheckBox checkbox_splitmultgoods;

	/**
	 * 是否拆分多品名--台湾格式
	 */
	private JCheckBox checkbox_splitmultgoods_TPE;
	/**
	 * 备注
	 */
	private JTextArea textArea_note;
	/**
	 * 修改时间
	 */
	private JTextField txt_modifytime;
	/**
	 * 修改人
	 */
	private JTextField text_modifyby;

	// 数据排序
	private JComboBox comboBox_condition1;
	private JComboBox comboBox_condition2;
	private JComboBox comboBox_condition3;
	private JRadioButton radioButton_ascend1;
	private JRadioButton radioButton_ascend2;
	private JRadioButton radioButton_ascend3;
	private JRadioButton radioButton_descend1;
	private JRadioButton radioButton_descend2;
	private JRadioButton radioButton_descend3;

	/**
	 * 文件后缀名预览
	 */
	private JLabel lbl_filePostfix;
	/**
	 * 文件名称预览
	 */
	private JLabel lbl_previewFileName;
	/**
	 * 文件后缀名
	 */
	private JComboBox comboBox_filePostfix;

	/**
	 * 模板信息
	 */
	private DIYTemplet templetInfo;

	/**
	 * 模板是否被修改--无法判断修改与否，点击确认即认为修改
	 */
	private boolean templetModfiy = false;

	/**
	 * 关联信息
	 */
	private DIYAssociate associateInfo;

	/**
	 * 关联信息是否被修改--无法判断修改与否，点击确认即认为修改
	 */
	private boolean associateModify = false;

	/**
	 * 上次查找用的key
	 */
	private String lastSearchKey = null;
	/**
	 * 上次查找的结果
	 */
	private List<TreePath> lastSearchResult = null;
	/**
	 * 上次查找的位置
	 */
	private int lastResultIndex = 0;

	/**
	 * 是RTF文件
	 */
	private boolean isRTF = false;

	/**
	 * 单例
	 */
	private static TempletDragEditor tEditor;

	/**
	 * 确定按钮1
	 */
	private JButton btnConfirm1;

	/**
	 * 复制按钮1
	 */
	private JButton btnCopy1;

	/**
	 * 确定按钮2
	 */
	private JButton btnConfirm2;

	/**
	 * <pre>
	 * 获取实例
	 * </pre>
	 * 
	 * @param win
	 * @return
	 */
	public static synchronized TempletDragEditor getInstance(Window win) {
		if (tEditor == null) {
			tEditor = new TempletDragEditor(win);
		}
		return tEditor;
	}

	/**
	 * <pre>
	 * 获取模板信息
	 * </pre>
	 * 
	 * @return
	 */
	public DIYTemplet getTempletInfo() {
		// 从控件中取值
		this.templetInfo.setTname(textField_fileName.getText());// 模板名称
		this.templetInfo.setNote(textField_note.getText());// 备注
		// -----保存模板-----
		// 同步
		TableUtils.synchModelAndView(headTable);
		TableUtils.synchModelAndView(bodyTable);
		TableUtils.synchModelAndView(tailTable);

		// 保存时判断name不能为空---name保存在数据库的一个字段中
		TitleArea title = new TitleArea(textField_fileName.getText());
		title.setFileRowLimit((Integer) fTextField_rowLimit.getValue());
		// 设置列宽
		title.setWidthList(getColumnWidth());

		HeadArea head = new HeadArea((EdiTableModel) headTable.getModel());

		BodyArea body = new BodyArea((EdiTableModel) bodyTable.getModel());

		TailArea tail = new TailArea((EdiTableModel) tailTable.getModel());

		ReportModel model = new ReportModel(title, head, body, tail);

		this.templetInfo.setTemplet(model.toXml());

		return templetInfo;
	}

	/**
	 * <pre>
	 * 取列宽
	 * </pre>
	 * 
	 * @return
	 */
	public List<Integer> getColumnWidth() {
		List<Integer> widthList = new ArrayList<Integer>();
		TableColumnModel tcmodel = headTable.getColumnModel();
		for (int i = 0; i < tcmodel.getColumnCount(); i++) {
			TableColumn tc = tcmodel.getColumn(i);
			widthList.add(tc.getWidth());
		}
		tcmodel = bodyTable.getColumnModel();
		for (int i = widthList.size(); i < tcmodel.getColumnCount(); i++) {
			TableColumn tc = tcmodel.getColumn(i);
			widthList.add(tc.getWidth());
		}
		tcmodel = tailTable.getColumnModel();
		for (int i = widthList.size(); i < tcmodel.getColumnCount(); i++) {
			TableColumn tc = tcmodel.getColumn(i);
			widthList.add(tc.getWidth());
		}
		return widthList;
	}

	private int getColumnWidth(List<Integer> width, int index) {
		if (width.size() <= index) {
			return TitleArea.DEFAULT_WIDTH;
		} else {
			return width.get(index);
		}
	}

	/**
	 * <pre>
	 * 设置head、body、tail列宽
	 * </pre>
	 * 
	 * @param width
	 */
	public void setColumnWidth(List<Integer> width) {

		TableColumnModel tcmodel = headTable.getColumnModel();
		TableColumn tc;
		for (int i = 0; i < tcmodel.getColumnCount(); i++) {
			tc = tcmodel.getColumn(i);
			tc.setWidth(getColumnWidth(width, i));
			tc.setPreferredWidth(getColumnWidth(width, i));
		}
		tcmodel = bodyTable.getColumnModel();
		for (int i = 0; i < tcmodel.getColumnCount(); i++) {
			tc = tcmodel.getColumn(i);
			tc.setWidth(getColumnWidth(width, i));
			tc.setPreferredWidth(getColumnWidth(width, i));
		}
		tcmodel = tailTable.getColumnModel();
		for (int i = 0; i < tcmodel.getColumnCount(); i++) {
			tc = tcmodel.getColumn(i);
			tc.setWidth(getColumnWidth(width, i));
			tc.setPreferredWidth(getColumnWidth(width, i));
		}
	}

	/**
	 * <pre>
	 * 模板是否被修改
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isTempletModfiy() {
		return templetModfiy;
	}

	/**
	 * <pre>
	 * 新增或者编辑模板
	 * </pre>
	 * 
	 * @param templet
	 *            模板
	 * @param title
	 *            标题
	 * @param okBtn
	 *            确定按钮是否可见
	 * @param copyBtn
	 *            复制按钮是否可见
	 */
	public void editTemplet(DIYTemplet templet, String title, boolean okBtn,
			boolean copyBtn) {
		this.templetInfo = templet;
		this.templetModfiy = false;
		setTitle(title);
		// 切换到第一页
		cardLayout.first(panel_right);
		// -------设置值到控件--------

		// 三个表格
		if (!templetModelToView(templetInfo)) {
			return;
		}

		// 设置按钮是否可见
		btnConfirm1.setVisible(okBtn);
		btnCopy1.setVisible(copyBtn);

		textField_fileName.requestFocus();

		// 显示窗口
		setVisible(true);

	}

	/**
	 * <pre>
	 * 模板——模型到视图
	 * </pre>
	 * 
	 * @param templet
	 */
	private boolean templetModelToView(DIYTemplet templet) {
		// 模板名称
		textField_fileName.setText(templet.getTname());
		// 模板备注
		textField_note.setText(templet.getNote());

		String XML = templet.getTemplet();
		// 初始化模板表格
		if (XML == null || "".equals(XML.trim())) {
			fTextField_rowLimit.setValue(-1);

			Vector<Vector<ICell>> data = new Vector<Vector<ICell>>();// NOPMD
			Vector<ICell> row = new Vector<ICell>();// NOPMD
			row.setSize(6);
			data.add(row);

			setModelDataAndInitTable(headTable, null, data, 6);

			data = new Vector<Vector<ICell>>();// NOPMD
			row = new Vector<ICell>();// NOPMD
			row.setSize(6);
			data.add(row);

			setModelDataAndInitTable(bodyTable, null, data, 6);

			data = new Vector<Vector<ICell>>();// NOPMD
			row = new Vector<ICell>();// NOPMD
			row.setSize(6);
			data.add(row);

			setModelDataAndInitTable(tailTable, null, data, 6);

		} else {
			ReportModel rModel = new ReportModel();
			try {
				rModel.fromXml(XML);

				TitleArea title = rModel.getTitle();
				fTextField_rowLimit.setValue(title.getFileRowLimit());

				HeadArea head = rModel.getHead();
				Vector<Vector<ICell>> data = head.getTableModel()
						.getDataVector();
				setModelDataAndInitTable(headTable, title.getWidthList(), data,
						head.getColumnCount());

				BodyArea body = rModel.getBody();
				data = body.getTableModel().getDataVector();
				setModelDataAndInitTable(bodyTable, title.getWidthList(), data,
						body.getColumnCount());

				TailArea tail = rModel.getTail();
				data = tail.getTableModel().getDataVector();
				setModelDataAndInitTable(tailTable, title.getWidthList(), data,
						tail.getColumnCount());

			} catch (Exception e1) {
				logger.error("解析模板XML失败", e1);
				JOptionPane.showMessageDialog(this, "解析模板XML失败！", "错误",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * 设置模型内容并初始化表格
	 * </pre>
	 * 
	 * @param table
	 * @param widthList
	 * @param data
	 * @param columnSize
	 */
	public void setModelDataAndInitTable(EdiTable table,
			List<Integer> widthList, Vector<Vector<ICell>> data, int columnSize) {
		// 清除表格选择
		table.clearSelection();

		EdiTableModel model = (EdiTableModel) table.getModel();

		model.setDataVector(data);

		initTableColumn(table, widthList, columnSize);

	}

	/**
	 * <pre>
	 * 初始化表格列
	 * </pre>
	 * 
	 * @param widthList
	 * @param table
	 * @param needColumn
	 */
	public void initTableColumn(EdiTable table, List<Integer> widthList,
			int needColumn) {
		if (widthList == null)
			widthList = new ArrayList<Integer>();

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table
				.getColumnModel();

		// 如果原来的列数小于needColumn，那么要添加新的列
		while (columnModel.getColumnCount() < needColumn) {
			columnModel.addColumn(new TableColumn());
		}
		// 如果原来的列数大于needColumn，那么需要删除老的列
		while (columnModel.getColumnCount() > needColumn) {
			columnModel.removeColumn(columnModel.getColumn(0));
		}
		// 更新列的模型索引和宽度
		for (int i = 0; i < columnModel.getColumnCount(); i++) {
			TableColumn tc = columnModel.getColumn(i);
			tc.setModelIndex(i);
			if (i >= widthList.size()) {
				tc.setWidth(TitleArea.DEFAULT_WIDTH);
				tc.setPreferredWidth(TitleArea.DEFAULT_WIDTH);
			} else {
				tc.setWidth(widthList.get(i));
				tc.setPreferredWidth(widthList.get(i));
			}
		}
	}

	/**
	 * <pre>
	 * 编辑或者新增关联
	 * </pre>
	 * 
	 * @param associate
	 *            对象
	 * @param title
	 *            标题
	 * @param tname
	 *            模板名称
	 * @param isRTF
	 *            是否是RTF
	 * @param okBtn
	 *            确定按钮是否可见
	 */
	public void editAssociate(DIYAssociate associate, String title,
			String tname, boolean isRTF, boolean okBtn) {
		this.associateInfo = associate;
		this.associateModify = false;
		setTitle(title);
		txt_templetname.setText(tname);
		// 切换到最后页
		cardLayout.last(panel_right);

		// 设置值到控件
		if (!associateModelToView(associate, isRTF)) {
			return;
		}
		// 设置按钮是否可见
		btnConfirm2.setVisible(okBtn);

		cusdeptcodePane.requestFocus();
		// 显示窗口
		setVisible(true);
	}

	/**
	 * <pre>
	 * 关联-- - 模型到视图
	 * </pre>
	 * 
	 * @param associate
	 * @param isRTF
	 */
	private boolean associateModelToView(DIYAssociate associate, boolean isRTF) {
		this.isRTF = isRTF;
		// 口岸
		// setText不会触发valueChanged事件
		String oldCusdept = cusdeptcodePane.getText();
		cusdeptcodePane.setText(associate.getCusdeptcode());

		// 进出口标识
		String oldIeflag = (String) comboBox_ieflag.getSelectedItem();
		oldIeflag = oldIeflag == null ? "" : oldIeflag;
		String ieflag = associate.getIeflag();
		if (ieflag != null && !"".equals(ieflag)) {
			comboBox_ieflag.setSelectedItem(ieflag);
		} else {
			comboBox_ieflag.setSelectedIndex(0);
		}

		if (oldIeflag.equals(ieflag)
				&& !oldCusdept.equals(associate.getCusdeptcode())) {
			initBtnNameCombox();// 手动触发
		}

		// 按钮名称
		comboBox_btnName.setSelectedItem(associate.getBtnname());
		// 报关类型
		gateTypePane.setText(associate.getGatetype());

		// 导出文件类型
		if (isRTF) {
			comboBox_fileType.setModel(new DefaultComboBoxModel(
					new String[] { "03" }));
			comboBox_fileType.setSelectedItem("03");
			comboBox_fileType.setEnabled(false);
		} else {
			comboBox_fileType.setEnabled(true);
			comboBox_fileType.setModel(new DefaultComboBoxModel(new String[] {
					"01", "02" }));
			String fileType = associate.getFiletype();
			if (fileType != null && !"".equals(fileType)) {
				comboBox_fileType.setSelectedItem(fileType);
			} else {
				comboBox_fileType.setSelectedIndex(0);
			}
		}

		// 文本文件分隔符号
		textArea_colSplit.setText(associate.getColsplit());
		// excel工作表名
		textfield_sheetName.setText(associate.getSheetname() == null ? "Sheet1"
				: associate.getSheetname());

		// 回车换行符号
		comboBox_crlf.setSelectedItem(associate.getCrlf());

		if (isRTF) {
			// 设置其不可见
			setAboutTxtVisible(false);
			setAboutExcelVisible(false);
		} else {
			if (comboBox_fileType.getSelectedIndex() == 0) {// 现在只能出两种
				setAboutTxtVisible(true);
				setAboutExcelVisible(false);
			} else {
				setAboutTxtVisible(false);
				setAboutExcelVisible(true);
			}
		}

		// 是否拆分多品名

		// 拆分多品名的方式 0=不拆分,1=普通拆分，2=台湾拆分
		// 还需要兼容上一个版本设置的
		if ("Y".equals(associate.getSplitmultgoods())) {
			checkbox_splitmultgoods.setSelected(true);
		} else {
			checkbox_splitmultgoods.setSelected("1".equals(associate
					.getSplitmultgoods()));
		}
		checkbox_splitmultgoods_TPE.setSelected("2".equals(associate
				.getSplitmultgoods()));

		// 模板备注
		textArea_note.setText(associate.getNote());
		// 修改时间
		txt_modifytime.setText(associate.getModifytime().toLocaleString());
		// 修改人
		text_modifyby.setText(associate.getModifyby());

		// 排序字段
		setOrderByToView(associate.getOrderby());

		// 文件后缀名
		String postFix = associate.getFilepostfix();
		if (isRTF) {
			if (postFix == null) {
				postFix = ".doc";
			}
		} else {
			if (postFix == null) {
				postFix = ".txt";
			}
		}
		comboBox_filePostfix.setSelectedItem(postFix);

		// 文件名称
		String xml = associate.getFilename();
		if (xml == null || "".equals(xml.trim())) {
			table_fileName.setModel(new EdiTableModel(1, 6));
			TableUtils.setTableDefaultWidth(table_fileName);
		} else {
			try {
				BodyArea ba = new BodyArea();
				Document doc = DocumentHelper.parseText(xml);
				ba.fromXml(doc.getRootElement());
				table_fileName.setModel(ba.getTableModel());
				TableUtils.setTableDefaultWidth(table_fileName);
			} catch (DocumentException e) {
				logger.error("解析文件名称XML失败", e);
				JOptionPane.showMessageDialog(this, "解析文件名称XML失败！", "错误",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * 设置排序字段到面板
	 * </pre>
	 * 
	 * @param order
	 */
	private void setOrderByToView(String order) {
		// 先设置成默认值
		comboBox_condition1.setSelectedIndex(0);
		comboBox_condition2.setSelectedIndex(0);
		comboBox_condition3.setSelectedIndex(0);
		radioButton_ascend1.setSelected(true);
		radioButton_ascend2.setSelected(true);
		radioButton_ascend3.setSelected(true);

		if (order == null || "".equals(order)) {
			return;
		}
		String[] array = order.split(";");
		try {// 如果异常就不做了
			if (array.length > 0) {
				String[] str = array[0].split(",");
				comboBox_condition1.setSelectedItem(str[0]);
				radioButton_ascend1.setSelected("true".equals(str[1]));
				radioButton_descend1.setSelected("false".equals(str[1]));
			}
			if (array.length > 1) {
				String[] str = array[1].split(",");
				comboBox_condition2.setSelectedItem(str[0]);
				radioButton_ascend2.setSelected("true".equals(str[1]));
				radioButton_descend2.setSelected("false".equals(str[1]));
			}
			if (array.length > 2) {
				String[] str = array[2].split(",");
				comboBox_condition3.setSelectedItem(str[0]);
				radioButton_ascend3.setSelected("true".equals(str[1]));
				radioButton_descend3.setSelected("false".equals(str[1]));
			}

		} catch (Exception e) {
			logger.warn("警告：设置排序条件失败！", e);
		}

	}

	/**
	 * <pre>
	 * 从面板中获取排序串
	 * </pre>
	 * 
	 * @return 字段名1,true;字段名2,false
	 */
	private String getOrderByFromView() {
		// 字段名1,true;字段名2,false
		StringBuffer orderStr = new StringBuffer();

		String order1 = (String) comboBox_condition1.getSelectedItem();
		if (order1 != null && !"".equals(order1)) {
			orderStr.append(order1);
			orderStr.append(",");
			orderStr.append(radioButton_ascend1.isSelected());
		}
		String order2 = (String) comboBox_condition2.getSelectedItem();
		if (order2 != null && !"".equals(order2)) {
			if (orderStr.length() != 0)
				orderStr.append(";");
			orderStr.append(order2);
			orderStr.append(",");
			orderStr.append(radioButton_ascend2.isSelected());
		}

		String order3 = (String) comboBox_condition3.getSelectedItem();
		if (order3 != null && !"".equals(order3)) {
			if (orderStr.length() != 0)
				orderStr.append(";");
			orderStr.append(order3);
			orderStr.append(",");
			orderStr.append(radioButton_ascend3.isSelected());
		}

		return orderStr.toString();
	}

	private void setAboutExcelVisible(boolean visible) {
		label_sheetName.setVisible(visible);
		textfield_sheetName.setVisible(visible);
	}

	private void setAboutTxtVisible(boolean visible) {
		label_colSplit.setVisible(visible);
		textArea_colSplit.setVisible(visible);
		scrollPane_split.setVisible(visible);
		label_crlf.setVisible(visible);
		comboBox_crlf.setVisible(visible);
	}

	/**
	 * <pre>
	 * 获取关联信息
	 * </pre>
	 * 
	 * @return
	 */
	public DIYAssociate getAssociateInfo() {
		// 口岸
		this.associateInfo.setCusdeptcode(cusdeptcodePane.getText());
		// 进出口标识
		this.associateInfo
				.setIeflag((String) comboBox_ieflag.getSelectedItem());
		// 按钮名称
		this.associateInfo.setBtnname((String) comboBox_btnName
				.getSelectedItem());
		// 报关类型
		String gatetype = gateTypePane.getText();
		this.associateInfo.setGatetype("".equals(gatetype) ? null : gatetype);
		// 导出文件类型
		this.associateInfo.setFiletype(comboBox_fileType.getSelectedItem()
				.toString());
		// excel工作表名
		this.associateInfo.setSheetname(textfield_sheetName.getText());
		// 列分隔符号
		this.associateInfo.setColsplit(textArea_colSplit.getText());
		// 回车换行符号
		this.associateInfo.setCrlf((String) comboBox_crlf.getSelectedItem());

		// 拆分多品名 0=不拆分,1=普通拆分，2=台湾拆分
		String splitMultGoods = "0";
		if (checkbox_splitmultgoods.isSelected()) {
			splitMultGoods = "1";
		}
		if (checkbox_splitmultgoods_TPE.isSelected()) {
			splitMultGoods = "2";
		}
		this.associateInfo.setSplitmultgoods(splitMultGoods);

		// 备注
		this.associateInfo.setNote(textArea_note.getText());

		// 排序字段
		this.associateInfo.setOrderby(getOrderByFromView());

		// 文件后缀名
		this.associateInfo.setFilepostfix((String) comboBox_filePostfix
				.getSelectedItem());

		// 文件名
		// 同步
		TableUtils.synchModelAndView(table_fileName);
		BodyArea fileNameArea = new BodyArea(
				(EdiTableModel) table_fileName.getModel());
		this.associateInfo.setFilename(fileNameArea.toXml());

		return associateInfo;
	}

	/**
	 * <pre>
	 * 关联信息是否被修改
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isAssociateModify() {
		return associateModify;
	}

	/**
	 * <pre>
	 * 循环查找
	 * 如果搜索的key没改变则从上次搜索的结果中查找
	 * </pre>
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public TreePath circularSearch(DefaultMutableTreeNode node, String key) {
		if ("".equals(key)) {
			return null;
		}
		if (key.equals(lastSearchKey)) {// 从上次搜索的结果中取
			if (lastSearchResult == null || lastSearchResult.isEmpty()) {
				return null;
			}
			if (lastResultIndex < lastSearchResult.size() - 1) {
				lastResultIndex++;
			} else {
				lastResultIndex = 0;
			}
			return lastSearchResult.get(lastResultIndex);
		} else {
			// 重新查
			lastSearchResult = searchPathList(node, key);
			lastSearchKey = key;
			lastResultIndex = 0;
			if (lastSearchResult == null || lastSearchResult.isEmpty()) {
				return null;
			}
			return lastSearchResult.get(0);
		}
	}

	/**
	 * <pre>
	 * 递归查找所有匹配的节点
	 * </pre>
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public List<TreePath> searchPathList(DefaultMutableTreeNode node, String key) {
		List<TreePath> list = new ArrayList<TreePath>();
		int childCount = node.getChildCount();

		for (int i = 0; i < childCount; i++) {
			DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) node
					.getChildAt(i);
			String content = (String) node2.getUserObject();
			if (content.contains(key)) {
				list.add(new TreePath(node2.getPath()));
			}
			// 尝试递归
			if (node2.getChildCount() > 0) {
				List<TreePath> listPath = searchPathList(node2, key);
				if (listPath != null && !listPath.isEmpty()) {
					list.addAll(listPath);
				}
			}
		}
		return list;
	}

	/**
	 * Create the frame.
	 */
	private TempletDragEditor(Window win) {
		super(win);
		// 设置为模式窗口
		setModal(true);
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						TempletDragEditor.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		setTitle("模板编辑器");
		setSize(new Dimension(1020, 734));
		setPreferredSize(new Dimension(1024, 768));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		// setBounds(100, 100, 584, 547);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_west = new JPanel();
		panel_west.setPreferredSize(new Dimension(0, 0));
		contentPane.add(panel_west, BorderLayout.WEST);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panel_left = new JPanel();
		panel_left.setMinimumSize(new Dimension(0, 0));
		panel_left.setPreferredSize(new Dimension(190, 10));
		splitPane.setLeftComponent(panel_left);
		panel_left.setLayout(new BorderLayout(0, 0));

		JPanel panel_tree_tools = new JPanel();
		panel_left.add(panel_tree_tools, BorderLayout.NORTH);
		panel_tree_tools.setLayout(new BoxLayout(panel_tree_tools,
				BoxLayout.Y_AXIS));

		JPanel panel_exp_col = new JPanel();
		panel_exp_col.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		panel_tree_tools.add(panel_exp_col);
		FlowLayout fl_panel_exp_col = (FlowLayout) panel_exp_col.getLayout();
		fl_panel_exp_col.setVgap(1);
		fl_panel_exp_col.setAlignment(FlowLayout.LEFT);

		JButton btnExpand = new JButton("+");
		btnExpand.setToolTipText("全部展开");
		btnExpand.setPreferredSize(new Dimension(38, 19));
		btnExpand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.collapseRow(0);
				tree.collapseRow(1);
				tree.collapseRow(2);

				tree.expandRow(2);
				tree.expandRow(1);
				tree.expandRow(0);
			}
		});
		panel_exp_col.add(btnExpand);

		JButton btnCollapse = new JButton("-");
		btnCollapse.setToolTipText("全部收缩");
		btnCollapse.setPreferredSize(new Dimension(38, 19));
		btnCollapse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.collapseRow(0);
				tree.collapseRow(1);
				tree.collapseRow(2);
			}
		});
		panel_exp_col.add(btnCollapse);

		JPanel panel_search = new JPanel();
		panel_tree_tools.add(panel_search);
		panel_search.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		textField_searchKey = new JTextField();
		textField_searchKey.setPreferredSize(new Dimension(100, 20));
		panel_search.add(textField_searchKey);

		JButton btnSearch = new JButton("查找(F)");
		btnSearch.setMnemonic('f');
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 连续查找
				// 先清空选择，回到最前面
				tree.clearSelection();
				tree.scrollRowToVisible(0);

				TreeModel treemodel = tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) treemodel
						.getRoot();
				String key = textField_searchKey.getText();
				TreePath path = circularSearch(root, key);
				if (path != null) {
					tree.expandPath(path);
					tree.setSelectionPath(path);
					tree.scrollPathToVisible(path);
				}
			}
		});
		panel_search.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		panel_left.add(scrollPane, BorderLayout.CENTER);

		tree = new JTree();
		scrollPane.setViewportView(tree);

		panel_right = new JPanel();
		splitPane.setRightComponent(panel_right);
		cardLayout = new CardLayout(0, 0);
		panel_right.setLayout(cardLayout);

		// ButtonGroup bgFilePostfix = new ButtonGroup();

		JPanel dragEditorPanel = new JPanel();
		dragEditorPanel.setLayout(new BorderLayout(0, 0));
		panel_right.add(dragEditorPanel, "dragEditorPanel");

		JPanel fileCtrlPanel = new JPanel();
		dragEditorPanel.add(fileCtrlPanel, BorderLayout.NORTH);
		fileCtrlPanel.setLayout(new BorderLayout(0, 0));
		// ButtonGroup bgFileType = new ButtonGroup();

		JPanel panel_null = new JPanel();
		fileCtrlPanel.add(panel_null, BorderLayout.NORTH);

		JLabel label_1 = new JLabel(
				"（从左侧的树中拖拽变量、常量、函数到右侧的表格中，表格内单元格可以随意拖动，更多操作请使用表格右键菜单）");
		panel_null.add(label_1);

		JPanel reportFileNamePanel = new JPanel();
		FlowLayout fl_reportFileNamePanel = (FlowLayout) reportFileNamePanel
				.getLayout();
		fl_reportFileNamePanel.setAlignment(FlowLayout.LEFT);
		fl_reportFileNamePanel.setVgap(0);
		fileCtrlPanel.add(reportFileNamePanel, BorderLayout.WEST);

		JLabel lbl_name = new JLabel(" 模板文件名称：");
		reportFileNamePanel.add(lbl_name);

		textField_fileName = new JTextField();
		reportFileNamePanel.add(textField_fileName);
		textField_fileName.setColumns(25);

		JPanel panel_note = new JPanel();
		fileCtrlPanel.add(panel_note, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_note = new GridBagLayout();
		gbl_panel_note.columnWidths = new int[] { 0, 10, 0 };
		gbl_panel_note.rowHeights = new int[] { 35, 0 };
		gbl_panel_note.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_note.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_note.setLayout(gbl_panel_note);

		JLabel lbl_note = new JLabel(" 模板备注信息：");
		GridBagConstraints gbc_lbl_note = new GridBagConstraints();
		gbc_lbl_note.anchor = GridBagConstraints.WEST;
		gbc_lbl_note.insets = new Insets(0, 5, 0, 5);
		gbc_lbl_note.gridx = 0;
		gbc_lbl_note.gridy = 0;
		panel_note.add(lbl_note, gbc_lbl_note);

		textField_note = new JTextField();
		GridBagConstraints gbc_textField_note = new GridBagConstraints();
		gbc_textField_note.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_note.insets = new Insets(0, 0, 0, 5);
		gbc_textField_note.gridx = 1;
		gbc_textField_note.gridy = 0;
		panel_note.add(textField_note, gbc_textField_note);
		textField_note.setColumns(10);

		JPanel allTablePanel = new JPanel();
		// allTablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		allTablePanel.setLayout(new BoxLayout(allTablePanel, BoxLayout.Y_AXIS));
		dragEditorPanel.add(allTablePanel, BorderLayout.CENTER);

		JPanel panel_headAttribute = new JPanel();
		allTablePanel.add(panel_headAttribute);
		panel_headAttribute.setLayout(new BoxLayout(panel_headAttribute,
				BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_headAttribute.add(panel);

		JLabel lblNewLabel_1 = new JLabel("表头：");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.BLUE);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		flowLayout_1.setVgap(2);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_headAttribute.add(panel_4);

		JButton btn_headAddRow = new JButton("增加行");
		btn_headAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableUtils.addRow(headTable);
			}
		});
		panel_4.add(btn_headAddRow);

		JButton btn_headAddColumn = new JButton("增加列");
		btn_headAddColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TableUtils.addColumn(TempletDragEditor.this, headTable);
			}
		});
		panel_4.add(btn_headAddColumn);

		JPanel headPanel = new JPanel();
		allTablePanel.add(headPanel);
		headPanel.setLayout(new BorderLayout(0, 0));
		scrollPane_head = new JScrollPane();
		headPanel.add(scrollPane_head, BorderLayout.CENTER);

		headTable = new EdiTable();
		scrollPane_head.setViewportView(headTable);

		JPanel panel_bodyAttribute = new JPanel();
		allTablePanel.add(panel_bodyAttribute);
		panel_bodyAttribute.setLayout(new BoxLayout(panel_bodyAttribute,
				BoxLayout.X_AXIS));

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_bodyAttribute.add(panel_7);

		JLabel label = new JLabel("表体：");
		panel_7.add(label);
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setForeground(Color.BLUE);

		JLabel label_15 = new JLabel("行数限制：");
		panel_7.add(label_15);

		fTextField_rowLimit = new JFormattedTextField();
		fTextField_rowLimit.setToolTipText("超过指定行数将拆分成多个文档，小于等于0表示不限制");
		fTextField_rowLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		fTextField_rowLimit.setValue(Integer.valueOf(0));
		fTextField_rowLimit.setText("-1");
		fTextField_rowLimit.setColumns(10);
		panel_7.add(fTextField_rowLimit);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
		flowLayout_5.setVgap(2);
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_bodyAttribute.add(panel_8);

		JButton btn_bodyAddColumn = new JButton("增加列");
		btn_bodyAddColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TableUtils.addColumn(TempletDragEditor.this, bodyTable);
			}
		});
		panel_8.add(btn_bodyAddColumn);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(10, 240));
		allTablePanel.add(bodyPanel);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		scrollPane_body = new JScrollPane();
		bodyPanel.add(scrollPane_body, BorderLayout.CENTER);

		bodyTable = new EdiTable();
		scrollPane_body.setViewportView(bodyTable);

		JPanel panel_tailAttribute = new JPanel();
		allTablePanel.add(panel_tailAttribute);
		panel_tailAttribute.setLayout(new BoxLayout(panel_tailAttribute,
				BoxLayout.X_AXIS));

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_tailAttribute.add(panel_5);

		JLabel lblNewLabel_2 = new JLabel("表尾：");
		panel_5.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setVgap(2);
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_tailAttribute.add(panel_6);

		JButton btn_tailAddRow = new JButton("增加行");
		btn_tailAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableUtils.addRow(tailTable);
			}
		});
		panel_6.add(btn_tailAddRow);

		JButton btn_tailAddColumn = new JButton("增加列");
		btn_tailAddColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TableUtils.addColumn(TempletDragEditor.this, tailTable);
			}
		});
		panel_6.add(btn_tailAddColumn);

		JPanel tailPanel = new JPanel();
		allTablePanel.add(tailPanel);
		tailPanel.setLayout(new BorderLayout(0, 0));
		scrollPane_tail = new JScrollPane();
		tailPanel.add(scrollPane_tail, BorderLayout.CENTER);

		tailTable = new EdiTable();
		scrollPane_tail.setViewportView(tailTable);

		JPanel panel_south = new JPanel();
		allTablePanel.add(panel_south);
		FlowLayout fl_panel_south = (FlowLayout) panel_south.getLayout();
		fl_panel_south.setHgap(100);

		btnConfirm1 = new JButton("确定 (O)");
		btnConfirm1.setMnemonic('o');
		btnConfirm1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 模板名称不能为空
				String tname = textField_fileName.getText();
				if (tname == null || "".equals(tname)) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"模板名称不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
					textField_fileName.requestFocus();
					return;
				}

				try {
					DIYTemplet t = getTempletInfo();
					List<DIYTemplet> salist = new ArrayList<DIYTemplet>();
					salist.add(t);
					// ServerProxy.getProxy().getProxy(IDIYTempletAction.class)
					// .saveOrUpdateBatchList(salist);
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"保存成功！");

					templetModfiy = true;
					setVisible(false);

				} catch (Exception e1) {
					logger.error("保存模板信息失败", e1);
					String msg = e1.getMessage();
					if (msg != null && msg.contains("ConstraintViolation")) {// 违反约束
						JOptionPane.showMessageDialog(TempletDragEditor.this,
								"保存失败！请确认模板名称是唯一的！", "错误",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(TempletDragEditor.this,
								"保存失败！\n\r错误信息：" + e1.getMessage(), "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel_south.add(btnConfirm1);

		btnCopy1 = new JButton("复制(Y)");
		btnCopy1.setMnemonic('y');
		btnCopy1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 复制
				if (JOptionPane.showConfirmDialog(TempletDragEditor.this,
						"确认复制当前模板？", "提示：", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String str = JOptionPane.showInputDialog(
							TempletDragEditor.this, "请输入新模板的名称：", "");
					if (str != null) {
						try {
							// 重新构建一个对象
							DIYTemplet newT = new DIYTemplet();
							// 修改模板名称
							newT.setTname(str);
							// id
							// newT.setId(UUID.randomUUID().toString());
							String userName = "admin";
							// 创建人
							newT.setCreateby(userName);
							DIYTemplet t = getTempletInfo();
							// 复制备注
							newT.setNote(t.getNote());
							// 复制模板
							newT.setTemplet(t.getTemplet());

							List<DIYTemplet> salist = new ArrayList<DIYTemplet>();
							salist.add(newT);
							// ServerProxy.getProxy()
							// .getProxy(IDIYTempletAction.class)
							// .saveOrUpdateBatchList(salist);
							JOptionPane.showMessageDialog(
									TempletDragEditor.this, "复制模板成功！");

							templetModfiy = true;

						} catch (Exception e1) {
							logger.error("复制模板失败", e1);
							String msg = e1.getMessage();
							if (msg != null
									&& msg.contains("ConstraintViolation")) {// 违反约束
								JOptionPane.showMessageDialog(
										TempletDragEditor.this,
										"复制模板失败！请确认模板名称是唯一的！", "错误",
										JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(
										TempletDragEditor.this,
										"复制模板失败！\n\r错误信息：" + e1.getMessage(),
										"错误", JOptionPane.ERROR_MESSAGE);
							}
						}

					} else {
						return;
					}
				}
			}
		});
		panel_south.add(btnCopy1);

		JButton btnCancel1 = new JButton("取消 (C)");
		btnCancel1.setMnemonic('c');
		btnCancel1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置为未修改
				// templetModfiy = false;
				setVisible(false);
			}
		});
		panel_south.add(btnCancel1);

		JPanel associatePanel = new JPanel();
		panel_right.add(associatePanel, "name_291439879955238");
		associatePanel.setLayout(new BorderLayout(0, 0));

		JPanel panel2_north = new JPanel();
		associatePanel.add(panel2_north, BorderLayout.NORTH);

		JPanel panel2_center = new JPanel();
		associatePanel.add(panel2_center, BorderLayout.CENTER);
		panel2_center.setLayout(new BorderLayout(0, 0));

		JPanel panel_editAssociate = new JPanel();
		panel2_center.add(panel_editAssociate, BorderLayout.NORTH);
		panel_editAssociate.setPreferredSize(new Dimension(10, 310));
		panel_editAssociate.setLayout(null);

		JLabel label_5 = new JLabel("模板名称：");
		label_5.setBounds(21, 11, 69, 15);
		panel_editAssociate.add(label_5);

		txt_templetname = new JTextField();
		txt_templetname.setEditable(false);
		txt_templetname.setBounds(110, 8, 429, 21);
		panel_editAssociate.add(txt_templetname);
		txt_templetname.setColumns(10);

		cusdeptcodePane = new JTextField();
		cusdeptcodePane.setBounds(110, 37, 127, 21);
		panel_editAssociate.add(cusdeptcodePane);

		JLabel label_6 = new JLabel("口    岸：");
		label_6.setBounds(21, 43, 69, 15);
		panel_editAssociate.add(label_6);

		JLabel label_7 = new JLabel("  进出口标识：");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(311, 44, 93, 15);
		panel_editAssociate.add(label_7);

		comboBox_ieflag = new JComboBox();
		comboBox_ieflag.setModel(new DefaultComboBoxModel(new String[] { "I",
				"E", "IE" }));
		comboBox_ieflag.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					initBtnNameCombox();
				}
			}
		});
		comboBox_ieflag.setBounds(414, 41, 125, 21);

		comboBox_ieflag.setRenderer(new DefaultListCellRenderer() {

			private static final long serialVersionUID = -2295487486766243540L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				if ("".equals(value)) {
					setText("请选择...");
				} else if ("I".equals(value)) {
					setText("I - 进口");
				} else if ("E".equals(value)) {
					setText("E - 出口");
				} else if ("IE".equals(value)) {
					setText("IE - 进出口");
				}
				return c;
			}
		});

		panel_editAssociate.add(comboBox_ieflag);

		JLabel label_8 = new JLabel("按钮名称：");
		label_8.setBounds(21, 69, 69, 15);
		panel_editAssociate.add(label_8);

		JLabel label_9 = new JLabel("导出文件类型：");
		label_9.setBounds(21, 98, 88, 15);
		panel_editAssociate.add(label_9);

		comboBox_fileType = new JComboBox();
		comboBox_fileType.setModel(new DefaultComboBoxModel(new String[] {
				"01", "02" }));
		comboBox_fileType.setRenderer(new DefaultListCellRenderer() {

			private static final long serialVersionUID = 1030228483986356163L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				if ("".equals(value)) {
					setText("请选择...");
				} else if ("01".equals(value)) {
					setText("txt");
				} else if ("02".equals(value)) {
					setText("Excel");
				} else if ("03".equals(value)) {
					setText("Word");
				} else if ("04".equals(value)) {
					setText("XML");
				}
				return c;
			}
		});
		comboBox_fileType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// 自动选择对应的类型
				comboBox_filePostfix.setSelectedIndex(comboBox_fileType
						.getSelectedIndex());

				if (TempletDragEditor.FILE_TYPE_TXT.equals(e.getItem()
						.toString())) {
					setAboutTxtVisible(true);
					setAboutExcelVisible(false);
				} else {
					setAboutTxtVisible(false);
					setAboutExcelVisible(true);
				}
			}
		});
		comboBox_fileType.setBounds(110, 95, 127, 21);
		panel_editAssociate.add(comboBox_fileType);

		JLabel label_10 = new JLabel("备    注：");
		label_10.setBounds(21, 158, 69, 15);
		panel_editAssociate.add(label_10);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(110, 153, 429, 50);
		panel_editAssociate.add(scrollPane_2);

		textArea_note = new JTextArea();

		scrollPane_2.setViewportView(textArea_note);

		JLabel label_11 = new JLabel("修改时间：");
		label_11.setBounds(21, 214, 69, 15);
		panel_editAssociate.add(label_11);

		txt_modifytime = new JTextField();
		txt_modifytime.setEditable(false);
		txt_modifytime.setColumns(10);
		txt_modifytime.setBounds(110, 211, 127, 21);
		panel_editAssociate.add(txt_modifytime);

		JLabel label_12 = new JLabel("修  改  人：");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setBounds(311, 213, 93, 15);
		panel_editAssociate.add(label_12);

		text_modifyby = new JTextField();
		text_modifyby.setEditable(false);
		text_modifyby.setColumns(10);
		text_modifyby.setBounds(414, 210, 125, 21);
		panel_editAssociate.add(text_modifyby);

		gateTypePane = new JTextField();
		// gateTypePane.setAllowNull(false);
		gateTypePane.setBounds(414, 72, 125, 21);
		panel_editAssociate.add(gateTypePane);

		JLabel label_13 = new JLabel("报关类型：");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(311, 75, 93, 15);
		panel_editAssociate.add(label_13);

		// Excel 工作表命名
		label_sheetName = new JLabel("工作表命名：");
		label_sheetName.setBounds(21, 127, 88, 15);
		panel_editAssociate.add(label_sheetName);

		textfield_sheetName = new JTextField();
		textfield_sheetName.setBounds(110, 124, 127, 21);
		panel_editAssociate.add(textfield_sheetName);

		// Txt 文档列分隔符号
		label_colSplit = new JLabel("列分隔符号：");
		label_colSplit.setBounds(21, 127, 88, 15);
		panel_editAssociate.add(label_colSplit);

		scrollPane_split = new JScrollPane();
		scrollPane_split
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_split
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_split.setBounds(110, 124, 177, 21);
		panel_editAssociate.add(scrollPane_split);

		textArea_colSplit = new JTextArea();
		Set<AWTKeyStroke> keyset = new HashSet<AWTKeyStroke>();
		keyset.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));// 回车变tab
		textArea_colSplit.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keyset);
		textArea_colSplit.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane_split.setViewportView(textArea_colSplit);

		label_crlf = new JLabel("换行符：");// CR //LF
		label_crlf.setHorizontalAlignment(SwingConstants.RIGHT);
		label_crlf.setBounds(311, 127, 93, 15);
		panel_editAssociate.add(label_crlf);

		comboBox_crlf = new JComboBox();
		comboBox_crlf.setEditable(true);
		// \r 回车 ; \n 换行 ; \r\n 回车换行; tab \t

		comboBox_crlf.setModel(new DefaultComboBoxModel(new String[] { "回车换行",
				"回车", "换行", "TAB" }));

		comboBox_crlf.setBounds(414, 124, 125, 21);
		panel_editAssociate.add(comboBox_crlf);

		JLabel label_14 = new JLabel("拆分多品名：");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setBounds(311, 101, 93, 15);
		panel_editAssociate.add(label_14);

		checkbox_splitmultgoods = new JCheckBox("普通");
		checkbox_splitmultgoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkbox_splitmultgoods.isSelected()) {
					checkbox_splitmultgoods_TPE.setSelected(false);
				}
			}
		});
		checkbox_splitmultgoods
				.setToolTipText("<html><p>拆分标准的多品名       </p><p>品名按照<b><font color='red'>,</font></b>分隔 &nbsp;&nbsp; 数量单位按照<b><font color='red'>;</font></b>分隔</p>&nbsp;</html>");
		checkbox_splitmultgoods.setBounds(414, 97, 52, 23);
		panel_editAssociate.add(checkbox_splitmultgoods);

		checkbox_splitmultgoods_TPE = new JCheckBox("台湾");
		checkbox_splitmultgoods_TPE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkbox_splitmultgoods_TPE.isSelected()) {
					checkbox_splitmultgoods.setSelected(false);
				}
			}
		});
		checkbox_splitmultgoods_TPE
				.setToolTipText("<html><p>拆分台湾格式的多品名</p><p>品名按照<b><font color='red'>,</font></b>分隔 &nbsp;&nbsp; 数量单位按照<b><font color='red'>,</font></b>分隔</p>&nbsp;</html>");
		checkbox_splitmultgoods_TPE.setBounds(476, 97, 52, 23);
		panel_editAssociate.add(checkbox_splitmultgoods_TPE);

		comboBox_btnName = new JComboBox();
		comboBox_btnName.setEditable(true);
		comboBox_btnName.setBounds(110, 68, 127, 21);
		panel_editAssociate.add(comboBox_btnName);

		JPanel panel_condition = new JPanel();
		FlowLayout fl_panel_condition = (FlowLayout) panel_condition
				.getLayout();
		fl_panel_condition.setHgap(2);
		fl_panel_condition.setAlignment(FlowLayout.LEFT);
		panel_condition.setBorder(new TitledBorder(null, "数据排序",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_condition.setBounds(10, 239, 793, 62);
		panel_editAssociate.add(panel_condition);

		JLabel lblNewLabel_3 = new JLabel("条件1：");
		panel_condition.add(lblNewLabel_3);

		comboBox_condition1 = new OrderComboBox();
		panel_condition.add(comboBox_condition1);

		radioButton_ascend1 = new JRadioButton("升");
		radioButton_ascend1.setSelected(true);
		panel_condition.add(radioButton_ascend1);

		radioButton_descend1 = new JRadioButton("降");
		panel_condition.add(radioButton_descend1);

		ButtonGroup btngrp1 = new ButtonGroup();
		btngrp1.add(radioButton_ascend1);
		btngrp1.add(radioButton_descend1);

		JLabel label_16 = new JLabel("        条件2：");
		panel_condition.add(label_16);

		comboBox_condition2 = new OrderComboBox();
		panel_condition.add(comboBox_condition2);

		radioButton_ascend2 = new JRadioButton("升");
		radioButton_ascend2.setSelected(true);
		panel_condition.add(radioButton_ascend2);

		radioButton_descend2 = new JRadioButton("降");
		panel_condition.add(radioButton_descend2);
		ButtonGroup btngrp2 = new ButtonGroup();
		btngrp2.add(radioButton_ascend2);
		btngrp2.add(radioButton_descend2);

		JLabel label_17 = new JLabel("        条件3：");
		panel_condition.add(label_17);

		comboBox_condition3 = new OrderComboBox();
		panel_condition.add(comboBox_condition3);

		radioButton_ascend3 = new JRadioButton("升");
		radioButton_ascend3.setSelected(true);
		panel_condition.add(radioButton_ascend3);

		radioButton_descend3 = new JRadioButton("降");
		panel_condition.add(radioButton_descend3);
		ButtonGroup btngrp3 = new ButtonGroup();
		btngrp3.add(radioButton_ascend3);
		btngrp3.add(radioButton_descend3);

		JPanel panel_editFileName = new JPanel();
		panel_editFileName.setBorder(new TitledBorder(null, "自定义导出文件文件名",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel2_center.add(panel_editFileName, BorderLayout.CENTER);
		panel_editFileName.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_3.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_editFileName.add(panel_3, BorderLayout.NORTH);

		JLabel label_2 = new JLabel(
				"说明：从左侧拖拽元素到表格中，使用预览按钮可查看文件名。注意不要在文件名中出现下列特殊字符之一( \\ / : * ? \" < > | )  ");
		panel_3.add(label_2);

		JButton btn_fileNameAddCol = new JButton("增加列");
		btn_fileNameAddCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 增加列
//				TableUtils.addColumn(TempletDragEditor.this, table_fileName);
			}
		});
		panel_3.add(btn_fileNameAddCol);

		JPanel panel_9 = new JPanel();
		panel_editFileName.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_filename = new JPanel();
		panel_filename.setPreferredSize(new Dimension(100, 80));
		panel_9.add(panel_filename);
		panel_filename.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_filename = new JScrollPane();
		panel_filename.add(scrollPane_filename, BorderLayout.CENTER);

		table_fileName = new EdiTable();

		scrollPane_filename.setViewportView(table_fileName);

		JPanel panel_filePostfix = new JPanel();
		FlowLayout fl_panel_filePostfix = (FlowLayout) panel_filePostfix
				.getLayout();
		fl_panel_filePostfix.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_filePostfix);

		JLabel label_3 = new JLabel("文件后缀名：");
		panel_filePostfix.add(label_3);

		comboBox_filePostfix = new JComboBox();
		comboBox_filePostfix.setEditable(true);
		comboBox_filePostfix.setPreferredSize(new Dimension(80, 21));
		panel_filePostfix.add(comboBox_filePostfix);
		comboBox_filePostfix.setModel(new DefaultComboBoxModel(new String[] {
				".txt", ".xls" }));

		JPanel panel_previewFileName = new JPanel();
		FlowLayout fl_panel_previewFileName = (FlowLayout) panel_previewFileName
				.getLayout();
		fl_panel_previewFileName.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_previewFileName);

		JButton btn_previewFileName = new JButton("预览文件名称");
		panel_previewFileName.add(btn_previewFileName);
		btn_previewFileName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 预览文件名
				// 先同步
				TableUtils.synchModelAndView(table_fileName);

				BodyArea fileNameArea = new BodyArea(
						(EdiTableModel) table_fileName.getModel());

				List<Map<String, Object>> list = ReportUtils.getAnalogData(1);

				String fileName = null;
				try {
					fileName = ReportUtils.getFileName(fileNameArea, list);
				} catch (Exception e1) {
					logger.error("计算文件名出错", e1);
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"计算文件名出错");
					return;
				}

				lbl_previewFileName.setText(fileName);
				// 判断文件名中特殊字符
				String specialChar = ReportUtils.checkFileName(fileName);
				if (!"".equals(specialChar)) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"文件名中不能包含特殊字符，请处理！\n" + specialChar, "警告",
							JOptionPane.WARNING_MESSAGE);
				}
				// 后缀
				String postfix = (String) comboBox_filePostfix
						.getSelectedItem();

				if (postfix != null) {
					if (postfix.length() > 10) {
						JOptionPane.showMessageDialog(TempletDragEditor.this,
								"文件后缀名长度不能超过10个字符！");
						comboBox_filePostfix.requestFocus();
						return;
					}
					lbl_filePostfix.setText(postfix);
				}
			}
		});

		JLabel label_4 = new JLabel("  （预览文件名时使用固定值替换变量）");
		panel_previewFileName.add(label_4);

		JPanel panel_FileName = new JPanel();
		panel_9.add(panel_FileName);
		FlowLayout fl_panel_FileName = (FlowLayout) panel_FileName.getLayout();
		fl_panel_FileName.setAlignment(FlowLayout.LEFT);

		JLabel lblNewLabel = new JLabel("  文件名称约为：");
		panel_FileName.add(lblNewLabel);

		lbl_previewFileName = new JLabel("SF-测试文件名");
		lbl_previewFileName.setForeground(Color.BLUE);
		lbl_previewFileName.setFont(new Font("宋体", Font.BOLD, 12));
		panel_FileName.add(lbl_previewFileName);

		lbl_filePostfix = new JLabel(".txt");
		lbl_filePostfix.setFont(new Font("宋体", Font.BOLD, 12));
		lbl_filePostfix.setForeground(Color.BLUE);
		panel_FileName.add(lbl_filePostfix);

		JPanel panel_extend = new JPanel();
		panel_extend.setPreferredSize(new Dimension(10, 80));
		panel2_center.add(panel_extend, BorderLayout.SOUTH);

		JPanel panel2_south = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel2_south.getLayout();
		flowLayout_6.setHgap(120);
		associatePanel.add(panel2_south, BorderLayout.SOUTH);

		btnConfirm2 = new JButton("确定 (O)");
		btnConfirm2.setMnemonic('O');
		btnConfirm2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 口岸不能为空
				String cusdept = cusdeptcodePane.getText();
				if (cusdept == null || "".equals(cusdept)) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"口岸不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
					cusdeptcodePane.requestFocus();
					return;
				}

				// 按钮名称不能为空
				String btnName = (String) comboBox_btnName.getSelectedItem();
				if (btnName == null || "".equals(btnName)) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"按钮名称不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
					comboBox_btnName.requestFocus();
					return;
				}
				// 文本文件列分隔符
				String colSplit = textArea_colSplit.getText();
				if (colSplit.length() > 50) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"文本文件列分隔符长度不能超过50个字符！", "警告",
							JOptionPane.WARNING_MESSAGE);
					textArea_colSplit.requestFocus();
					return;
				}

				// 文件后缀名不能为空
				String filePostfix = (String) comboBox_filePostfix
						.getSelectedItem();
				if (filePostfix == null || "".equals(filePostfix)) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"文件后缀名不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
					comboBox_filePostfix.requestFocus();
					return;
				} else if (filePostfix.length() > 10) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"文件后缀名长度不能超过10个字符！", "警告",
							JOptionPane.WARNING_MESSAGE);
					comboBox_filePostfix.requestFocus();
					return;
				}

				// start 表格不能为空
				boolean tableIsNull = true;
				TableModel tm = table_fileName.getModel();
				for (int i = 0; i < tm.getRowCount(); i++) {
					if (!tableIsNull)
						break;
					for (int j = 0; j < tm.getColumnCount(); j++) {
						ICell cell = (ICell) tm.getValueAt(i, j);
						if (cell != null) {
							if (cell.getFunctionSize() > 0) {
								tableIsNull = false;// 表格不为空
								break;
							}
							if (cell.getEntitySize() > 0) {
								tableIsNull = false;// 表格不为空
								break;
							}
						}
					}
				}
				if (tableIsNull) {
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"文件名(表格)不能为空！请重新编辑！", "警告",
							JOptionPane.WARNING_MESSAGE);
					table_fileName.requestFocus();
					return;
				}

				// end 表格不能为空

				try {
					DIYAssociate a = getAssociateInfo();
					List<DIYAssociate> salist = new ArrayList<DIYAssociate>();
					salist.add(a);
					// ServerProxy.getProxy().getProxy(IDIYAssociateAction.class)
					// .saveOrUpdateBatchList(salist);
					JOptionPane.showMessageDialog(TempletDragEditor.this,
							"保存成功！");

					associateModify = true;
					setVisible(false);

				} catch (Exception e1) {
					logger.error("保存模板关联信息失败", e1);
					String msg = e1.getMessage();
					if (msg != null && msg.contains("ConstraintViolation")) {// 违反约束
						JOptionPane.showMessageDialog(TempletDragEditor.this,
								"保存失败！同一个模板对应的（口岸、进出口标识，按钮名称，报关类型）必须是唯一的！",
								"错误", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(TempletDragEditor.this,
								"保存失败！\n\r错误信息：" + e1.getMessage(), "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel2_south.add(btnConfirm2);

		JButton btn2cancel = new JButton("取消 (C)");
		btn2cancel.setMnemonic('C');
		btn2cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置为未修改
				associateModify = false;
				setVisible(false);
			}
		});
		panel2_south.add(btn2cancel);

		initTree();
		initTable();
	}

	private void initTree() {
		// tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		tree.setToolTipText("选中并使用鼠标拖拽到表格中");
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("选择字段") {
			private static final long serialVersionUID = 1L;
			{// NOPMD
				DefaultMutableTreeNode node_limb;
				node_limb = new DefaultMutableTreeNode("函数");
				node_limb.add(new EdiMutableTreeNode(new SumFunction()));
				node_limb.add(new EdiMutableTreeNode(new SequenceFunction()));
				node_limb.add(new EdiMutableTreeNode(new DateFunction()));
				node_limb.add(new EdiMutableTreeNode(new CountFunction()));
				node_limb.add(new EdiMutableTreeNode(new FillFunction()));
				node_limb.add(new EdiMutableTreeNode(new SubstringFunction()));
				node_limb.add(new EdiMutableTreeNode(new ReplaceFunction()));
				node_limb.add(new EdiMutableTreeNode(new JFConvertFunction()));
				node_limb.add(new EdiMutableTreeNode(new GroovyFunction()));

				// 还需要添加更多
				add(node_limb);
				node_limb = new DefaultMutableTreeNode("常量");
				node_limb.add(new EdiMutableTreeNode(
						new ConstantEntity("自定义常量")));
				node_limb.add(new EdiMutableTreeNode(new TabEntity()));
				add(node_limb);

				node_limb = new DefaultMutableTreeNode("变量");

				// ############## 特殊常量 start ##############
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("count")));// 总数
				// ############## 特殊常量 start ##############

				// node_limb.add(new EdiMutableTreeNode(new BaseEntity("id",
				// null,
				// "编号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bno")));// 运单编号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"sub_waybill_no")));// 子运单号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("gdesc")));// 托寄物内容
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("aweight")));// 实际重量
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("weight")));// 计费重量
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"weight_unit")));// 重量单位

				node_limb.add(new EdiMutableTreeNode(new QuantityEntity()));// 数量
				node_limb.add(new EdiMutableTreeNode(
						new QuantityOneByOneEntity()));// 数量 （多品名时：数量1,数量2,数量3）

				node_limb.add(new EdiMutableTreeNode(new UnitEntity1()));// 单位1(如：1件1米---只取件)
				node_limb.add(new EdiMutableTreeNode(new UnitEntity()));// 单位(如：1件1米---取件1米)
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("quantity")));// 数量/单位
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("pcs")));// 件数
				node_limb
						.add(new EdiMutableTreeNode(new ValueOneByOneEntity())); // 价值
																					// （多品名时：价值1,价值2,价值3）
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("cusvalue")));// 申报价值
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"cus_currency_symbol")));// 申报币别
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("price")));// 单价
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"invoice_value")));// 发票申报价值
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("hscode")));// 商品编码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("gmodel")));// 规格型号
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("srccode")));// 原寄地代码
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("srcname")));// 原寄地名称
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("descode")));// 目的地代码
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("desname")));// 目的地名称
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("gate_type")));// 报关类型
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"customs_date")));// 报关日期
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"exp_customs_date")));// 预报关日期
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("custype")));// 类别
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("cusbatch")));// 报关批次
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"org_cusbatch")));// 原始报关批次
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("velist")));// 车次
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"srccompany")));// 寄件公司
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("srcaddr")));// 寄件地址
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("srcpname")));// 寄件联络人
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srctel")));// 寄件电话
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("srcmobile")));// 寄件手机
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_union_no")));// 寄件公司统编号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_business_unit_code")));// 寄件公司经营单位编码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_postal_code")));// 寄件地址邮政编码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_city_code")));// 寄件城市三字代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_city_name")));// 寄件城市名称
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("srcstate")));// 寄件州、省名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"srccountry")));// 寄件国别地区代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_country_code")));// 寄件国别数字代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_country_name")));// 寄件国别地区名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"descompany")));// 收件公司
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("desaddr")));// 收件地址
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("despname")));// 收件联络人
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("destel")));// 收件电话
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("desmobile")));// 收件手机
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_union_no")));// 收件公司统编号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_business_unit_code")));// 收件公司经营单位编码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_postal_code")));// 收件地址邮政编码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_city_code")));// 收件城市三字代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_city_name")));// 收件城市名称
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("desstate")));// 收件州、省名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"descountry")));// 收件国别地区代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_country_code")));// 收件国别数字代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_country_name")));// 收件国别地区名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("tariff")));// 关税税率
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tariff_value")));// 关税
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("vat")));// 增值税税率
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("vat_value")));// 增值税
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"consumption_tax_rate")));// 消费税税率
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"consumption_tax")));// 消费税
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"payment_type")));// 付款方式
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("feeamt")));// 运费
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("insurance")));// 保险费
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"service_charge")));// 服务费
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"miscellaneous")));// 杂费
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("send_date")));// 寄件日期
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("send_time")));// 寄件时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"send_gl_time")));// 寄件格林日期时间
				// 补充变量
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"flight_start_date")));// 航班出发日期
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"flight_start_time")));// 航班出发时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"flight_arrival_datea")));// 航班到达日期
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"flight_arrival_time")));// 航班到达时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"company_name")));// 公司名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"filiale_name")));// 分公司名称
				// end
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("packageno")));// 袋(包)号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"packagenum")));// 袋(包)数
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"express_type")));// 快件类型
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("fdacode")));// FDA指标
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("packing")));// 包装方式
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("storages")));// 货物存处
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("mawb")));// 主提单号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("kjno")));// KJ单号
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("flightno")));// 车牌/航班号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transport_company_name")));// 运输公司名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"start_city_code")));// 始发城市代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"start_city_name")));// 始发城市名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"end_city_code")));// 目的城市代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"end_city_name")));// 目的城市名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"exemption_code")));// 征免性质代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"exemption_name")));// 征免性质名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"clearance_code")));// 验放代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"containerno")));// 集装箱号
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"trade_type")));// 贸易方式
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transaction_method_code")));// 成交方式代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transaction_method_name")));// 成交方式名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transport_mode")));// 运输方式
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("source")));// 数据来源
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("opdt")));// 操作日期
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("oparea")));// 操作地区
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"op_gl_time")));// 操作格林时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("note")));// 备注
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_time")));// 修改时间
				node_limb.add(new EdiMutableTreeNode(
						new BaseEntity("modify_by")));// 修改人
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_area")));// 修改地区
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_gl_time")));// 修改格林时间
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("tr_gdesc")));// 托寄物内容翻译
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_srccompany")));// 寄件公司翻译
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_srcaddr")));// 寄件地址翻译
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_srcname")));// 寄件联络人翻译
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_descompany")));// 收件公司翻译
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_desaddr")));// 收件地址翻译
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_desname")));// 收件联络人翻译
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("tr_flag")));// 翻译标示
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("sadinfo")));// 参考资料审核途径
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("selected")));// 是否选择
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("upsign")));// 修改标志
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("adsign")));// 审核标志
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("cfsign")));// 审核确认标志
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("passign")));// 清关标志
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_gl_time")));// 审核确认格林时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_area")));// 审核确认地区
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_time")));// 审核确认时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_by")));// 审核确认人
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"audit_area")));// 审核地区
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"audit_gl_time")));// 审核格林时间
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("audit_by")));// 审核人
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"audit_time")));// 审核时间
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("psrcnm")));// 列印原寄地代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("pdesnm")));// 列印目的地代码
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak1")));// 备用字段1
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak2")));// 备用字段2
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak3")));// 备用字段3
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak4")));// 备用字段4
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"produce_src")));// 原产地
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"fee_currency")));// 运费币别
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("tranflag")));// 品名精确翻译匹配标志
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"hscodeflag")));// 商品编码精确匹配标志
				node_limb
						.add(new EdiMutableTreeNode(new BaseEntity("senssign")));// 敏感品名标志
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"sensvalueflag")));// 敏感价值标记
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"maxpcsflag")));// 最大件数标记
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"maxaweightflag")));// 最大重量标记
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_business_unit_name")));// 寄件经营单位公司名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_business_unit_name")));// 收件经营单位公司名称
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"declaration_type")));// 申报类型
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"shipside_code")));// 码头/货场代码

				// 特殊常量
				node_limb.add(new EdiMutableTreeNode(
						new ExchangeCNY2USDEntity()));// 汇率 人民币对 美元

				node_limb.add(new EdiMutableTreeNode(new GoodNoUnitEntity()));// 第一计量单位代码
				node_limb
						.add(new EdiMutableTreeNode(new GoodNoUnitNameEntity()));// 第一计量单位名称

				node_limb.add(new EdiMutableTreeNode(new GroovyEntity()));// 脚本变量

				add(node_limb);

			}
		}));

		tree.setCellRenderer(new EdiTreeCellRenderer());
		// 树拖拽支持0
		tree.setDragEnabled(true);
		// tree.setDropMode(DropMode.)
		tree.setTransferHandler(new EdiTreeTransferHandler(tree)); // 树默认展开
		tree.expandRow(2);
		tree.expandRow(1);
		tree.expandRow(0);
	}

	private void initTable() {
		initTable(headTable, true);
		initTable(bodyTable, false);
		initTable(tailTable, true);
		// headTable.setColumnModel(columnModel)
		// 设置表格拖拽同步
		headTable.getColumnModel().addColumnModelListener(
				new DragTableColumnModelListener(headTable, bodyTable,
						tailTable));
		bodyTable.getColumnModel().addColumnModelListener(
				new DragTableColumnModelListener(bodyTable, headTable,
						tailTable));
		tailTable.getColumnModel().addColumnModelListener(
				new DragTableColumnModelListener(tailTable, bodyTable,
						headTable));
		// ###### 表头鼠标点击释放
//		headTable.getTableHeader().addMouseListener(
//				new CtrlScrollMouseListener(this, headTable, bodyTable,
//						tailTable));
//		bodyTable.getTableHeader().addMouseListener(
//				new CtrlScrollMouseListener(this, bodyTable, headTable,
//						tailTable));
//		tailTable.getTableHeader().addMouseListener(
//				new CtrlScrollMouseListener(this, tailTable, headTable,
//						bodyTable));
		// 设置滚动同步
		scrollPane_head.getHorizontalScrollBar().addAdjustmentListener(
				new ScrollAdjustmentListener(scrollPane_body, scrollPane_tail));

		scrollPane_body.getHorizontalScrollBar().addAdjustmentListener(
				new ScrollAdjustmentListener(scrollPane_head, scrollPane_tail));

		scrollPane_tail.getHorizontalScrollBar().addAdjustmentListener(
				new ScrollAdjustmentListener(scrollPane_head, scrollPane_body));
		// 设置文件名表格
		initTable(table_fileName, false);
	}

	private void initTable(EdiTable table, boolean addRow) {
		table.setRowHeight(70);// 50 就可以了
		table.setSelectionBackground(new Color(184, 207, 229));// 淡蓝色

		EdiTableColumnModel edicm = new EdiTableColumnModel();
		table.setColumnModel(edicm);

		EdiTableModel edim = new EdiTableModel(1, 6);
		table.setModel(edim);

		table.setSurrendersFocusOnKeystroke(true);
		// 设置表格选择方式
		// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		// table.setColumnSelectionAllowed(true);

		// table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// ###################################################
		// 应该重新写一个表格渲染器，表头和表体已经表尾 应该有标识
		table.setCellEditor(new EdiTableCellEditor(this));

		// 编辑器 JFrame
		table.setDefaultEditor(ICell.class, new EdiTableCellEditor(this));

		table.setDefaultRenderer(ICell.class, new EdiTableCellRenderer());

		// 表格右键
//		table.addMouseListener(new TableMouseListener(this, this, table, addRow));
		// 表头渲染器
		table.getTableHeader().setDefaultRenderer(new TableHeadCellRenderer());
		// 表头右键
//		table.getTableHeader().addMouseListener(
//				new TableHeadMouseListener(this, table));
		// 表格拖拽支持
		table.setDragEnabled(true);
		table.setDropMode(DropMode.USE_SELECTION);
		table.setTransferHandler(new EdiTableTransferHandler(table));
		// 添加表格监听器
	}

	/**
	 * <pre>
	 * 初始化按钮名称下拉框
	 * </pre>
	 */
	private void initBtnNameCombox() {

		// 获取到焦点时
		String cusdept = cusdeptcodePane.getText();
		String ieFlag = (String) comboBox_ieflag.getSelectedItem();
		if (null != cusdept && !"".equals(cusdept) && null != ieFlag
				&& !"".equals(ieFlag)) {
			// 设置Model之前保存上一次的选择
			Object item = comboBox_btnName.getSelectedItem();

		}
		comboBox_btnName.setModel(new DefaultComboBoxModel(new String[] {}));

	}

	public static void main(String[] args) {
		new TempletDragEditor(null).setVisible(true);
		
		//getInstance(null).setVisible(true);
		
	}
}
