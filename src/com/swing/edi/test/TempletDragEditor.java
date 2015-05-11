package com.swing.edi.test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.swing.edi.dragDrop.EdiTableTransferHandler;
import com.swing.edi.dragDrop.EdiTreeTransferHandler;
import com.swing.edi.report.utils.ReportUtils;
import com.swing.edi.reportModel.ReportModel;
import com.swing.edi.reportModel.area.BodyArea;
import com.swing.edi.reportModel.area.HeadArea;
import com.swing.edi.reportModel.area.TailArea;
import com.swing.edi.reportModel.area.TitleArea;
import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.entity.BaseEntity;
import com.swing.edi.reportModel.area.cell.function.DateFunction;
import com.swing.edi.reportModel.area.cell.function.SequenceFunction;
import com.swing.edi.reportModel.area.cell.function.SumFunction;
import com.swing.edi.ui.table.EdiTable;
import com.swing.edi.ui.table.EdiTableCellEditor;
import com.swing.edi.ui.table.EdiTableCellRenderer;
import com.swing.edi.ui.table.EdiTableModel;
import com.swing.edi.ui.table.TableHeadCellRenderer;
import com.swing.edi.ui.table.TableHeadMouseListener;
import com.swing.edi.ui.table.TableMouseListener;
import com.swing.edi.ui.table.TableUtils;
import com.swing.edi.ui.tree.EdiMutableTreeNode;

import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
public class TempletDragEditor extends JFrame {

	private static final long serialVersionUID = -1291571444495787289L;
	private JPanel contentPane;
	private JTable headTable;
	private JTable bodyTable;
	private JTable tailTable;
	private JTree tree;
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
	private JTable table_fileName;
	/**
	 * 模板名称
	 */
	private JTextField txt_templetname;
	/**
	 * 口岸
	 */
	private JComboBox comboBox_cusdeptcode;
	/**
	 * 进出口标识
	 */
	private JComboBox comboBox_ieflag;
	/**
	 * 按钮名称
	 */
	private JTextField txt_btnName;

	/**
	 * 导出文件类型
	 */
	private JComboBox comboBox_fileType;

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//
//			// 当前系统风格
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (InstantiationException e1) {
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			e1.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TempletDragEditor frame = new TempletDragEditor();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TempletDragEditor() {
		setTitle("模板编辑器");
		setSize(new Dimension(1000, 734));
		setPreferredSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 584, 547);
		contentPane = new JPanel();
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
		FlowLayout fl_panel_tree_tools = (FlowLayout) panel_tree_tools
				.getLayout();
		fl_panel_tree_tools.setVgap(1);
		fl_panel_tree_tools.setAlignment(FlowLayout.LEFT);
		panel_left.add(panel_tree_tools, BorderLayout.NORTH);

		JButton btnExpand = new JButton("+");
		btnExpand.setToolTipText("全部展开");
		btnExpand.setPreferredSize(new Dimension(38, 20));
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
		panel_tree_tools.add(btnExpand);

		JButton btnCollapse = new JButton("-");
		btnCollapse.setToolTipText("全部收缩");
		btnCollapse.setPreferredSize(new Dimension(38, 20));
		btnCollapse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.collapseRow(0);
				tree.collapseRow(1);
				tree.collapseRow(2);
			}
		});
		panel_tree_tools.add(btnCollapse);

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
				TableUtils.addColumn(headTable);
			}
		});
		panel_4.add(btn_headAddColumn);

		JPanel headPanel = new JPanel();
		allTablePanel.add(headPanel);
		headPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_head = new JScrollPane();
		scrollPane_head.setPreferredSize(new Dimension(10, 160));
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

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
		flowLayout_5.setVgap(2);
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_bodyAttribute.add(panel_8);

		JButton btn_bodyAddColumn = new JButton("增加列");
		btn_bodyAddColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableUtils.addColumn(bodyTable);
			}
		});
		panel_8.add(btn_bodyAddColumn);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setPreferredSize(new Dimension(10, 75));
		allTablePanel.add(bodyPanel);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_body = new JScrollPane();
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
				TableUtils.addColumn(tailTable);
			}
		});
		panel_6.add(btn_tailAddColumn);

		JPanel tailPanel = new JPanel();
		allTablePanel.add(tailPanel);
		tailPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_tail = new JScrollPane();
		scrollPane_tail.setPreferredSize(new Dimension(10, 160));
		tailPanel.add(scrollPane_tail, BorderLayout.CENTER);

		tailTable = new EdiTable();
		scrollPane_tail.setViewportView(tailTable);

		JPanel panel_south = new JPanel();
		allTablePanel.add(panel_south);
		FlowLayout fl_panel_south = (FlowLayout) panel_south.getLayout();
		fl_panel_south.setHgap(80);

		JButton btnOpen = new JButton("打开 (O)");
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("打开xml文件");

				StringBuilder sb = new StringBuilder();

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML", "xml");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("选择的文件是:"
							+ chooser.getSelectedFile().getName());
					File f = chooser.getSelectedFile();
					try {
						BufferedReader br = new BufferedReader(
								new FileReader(f));
						String line;
						while ((line = br.readLine()) != null) {
							sb.append(line);
						}
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				ReportModel rModel = new ReportModel();
				try {
					rModel.fromXml(sb.toString());
					TitleArea title = rModel.getTitle();
					textField_fileName.setText(title.getReportName());

					HeadArea head = rModel.getHead();
					headTable.setModel(head.getTableModel());
					// checkBox_headShowAll.setSelected(head.isShowInAnyFiles());

					BodyArea body = rModel.getBody();
					bodyTable.setModel(body.getTableModel());
					// textField_limit.setText(body.getRowLimit() + "");

					TailArea tail = rModel.getTail();
					tailTable.setModel(tail.getTableModel());
					// checkBox_tailShowAll.setSelected(tail.isShowInAnyFiles());

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "读取xml文件出错！");
					e1.printStackTrace();
				}

			}
		});
		panel_south.add(btnOpen);

		JButton btnSave = new JButton("保存 (S)");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("保存!");
				// 同步
				TableUtils.synchModelAndView(headTable);
				TableUtils.synchModelAndView(bodyTable);
				TableUtils.synchModelAndView(tailTable);

				// 保存时判断name不能为空---name应该保存在数据库的一个字段中
				TitleArea title = new TitleArea(textField_fileName.getText());

				HeadArea head = new HeadArea((EdiTableModel) headTable
						.getModel());

				BodyArea body = new BodyArea((EdiTableModel) bodyTable
						.getModel());

				TailArea tail = new TailArea((EdiTableModel) tailTable
						.getModel());

				// String xml = ReportModelUtils.toXml(title, head, body, tail);

				ReportModel model = new ReportModel(title, head, body, tail);
				String xml = model.toXml();

				System.out.println(xml);

				// 写入到文件中
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML", "xml");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ chooser.getSelectedFile().getName());
					File f = chooser.getSelectedFile();
					try {
						f.createNewFile();
						FileOutputStream os = new FileOutputStream(f);
						os.write(xml.getBytes());
						os.flush();
						os.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel_south.add(btnSave);

		JButton btnSaveAs = new JButton("另存为");
		btnSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 先做后一页
				cardLayout.last(panel_right);
			}
		});
		panel_south.add(btnSaveAs);

		JButton btnExit = new JButton("退出 (X)");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_south.add(btnExit);

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
		panel_editAssociate.setPreferredSize(new Dimension(10, 220));
		panel_editAssociate.setLayout(null);

		JLabel label_5 = new JLabel("模板名称：");
		label_5.setBounds(21, 13, 69, 15);
		panel_editAssociate.add(label_5);

		txt_templetname = new JTextField();
		txt_templetname.setEditable(false);
		txt_templetname.setBounds(100, 10, 375, 21);
		panel_editAssociate.add(txt_templetname);
		txt_templetname.setColumns(10);

		comboBox_cusdeptcode = new JComboBox();
		comboBox_cusdeptcode.setBounds(100, 41, 113, 21);
		panel_editAssociate.add(comboBox_cusdeptcode);

		JLabel label_6 = new JLabel("口    岸：");
		label_6.setBounds(21, 44, 69, 15);
		panel_editAssociate.add(label_6);

		JLabel label_7 = new JLabel("  进出口标识：");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(267, 44, 93, 15);
		panel_editAssociate.add(label_7);

		comboBox_ieflag = new JComboBox();
		comboBox_ieflag.setModel(new DefaultComboBoxModel(new String[] {
				"I  进口", "E  出口", "IE 进出口" }));
		comboBox_ieflag.setBounds(370, 41, 105, 21);
		panel_editAssociate.add(comboBox_ieflag);

		JLabel label_8 = new JLabel("按钮名称：");
		label_8.setBounds(21, 75, 69, 15);
		panel_editAssociate.add(label_8);

		txt_btnName = new JTextField();
		txt_btnName.setBounds(100, 72, 113, 21);
		panel_editAssociate.add(txt_btnName);
		txt_btnName.setColumns(10);

		JLabel label_9 = new JLabel("导出文件类型：");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(267, 75, 93, 15);
		panel_editAssociate.add(label_9);

		comboBox_fileType = new JComboBox();
		comboBox_fileType.setModel(new DefaultComboBoxModel(new String[] {
				"txt", "excel" }));
		comboBox_fileType.setBounds(370, 72, 105, 21);
		panel_editAssociate.add(comboBox_fileType);

		JLabel label_10 = new JLabel("备    注：");
		label_10.setBounds(21, 108, 69, 15);
		panel_editAssociate.add(label_10);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 103, 375, 50);
		panel_editAssociate.add(scrollPane_2);

		textArea_note = new JTextArea();
		scrollPane_2.setViewportView(textArea_note);

		JLabel label_11 = new JLabel("修改时间：");
		label_11.setBounds(21, 166, 69, 15);
		panel_editAssociate.add(label_11);

		txt_modifytime = new JTextField();
		txt_modifytime.setEditable(false);
		txt_modifytime.setColumns(10);
		txt_modifytime.setBounds(100, 163, 143, 21);
		panel_editAssociate.add(txt_modifytime);

		JLabel label_12 = new JLabel("修  改  人：");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setBounds(267, 166, 93, 15);
		panel_editAssociate.add(label_12);

		text_modifyby = new JTextField();
		text_modifyby.setEditable(false);
		text_modifyby.setColumns(10);
		text_modifyby.setBounds(370, 163, 105, 21);
		panel_editAssociate.add(text_modifyby);

		JPanel panel_editFileName = new JPanel();
		panel_editFileName.setBorder(new TitledBorder(null,
				"\u81EA\u5B9A\u4E49\u5BFC\u51FA\u6587\u4EF6\u6587\u4EF6\u540D",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel2_center.add(panel_editFileName, BorderLayout.CENTER);
		panel_editFileName.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_3.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_editFileName.add(panel_3, BorderLayout.NORTH);

		JLabel label_2 = new JLabel(
				"说明：从左侧拖拽元素到表格中，使用预览按钮可查看文件名。注意不要在文件名中出现特殊字符。  ");
		panel_3.add(label_2);

		JButton btn_fileNameAddCol = new JButton("增加列");
		btn_fileNameAddCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 增加列
				TableUtils.addColumn(table_fileName);
			}
		});
		panel_3.add(btn_fileNameAddCol);

		JPanel panel_9 = new JPanel();
		panel_editFileName.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_filename = new JPanel();
		panel_filename.setPreferredSize(new Dimension(100, 100));
		panel_9.add(panel_filename);
		panel_filename.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_filename = new JScrollPane();
		panel_filename.add(scrollPane_filename, BorderLayout.CENTER);

		table_fileName = new JTable();

		scrollPane_filename.setViewportView(table_fileName);

		JPanel panel_filePostfix = new JPanel();
		FlowLayout fl_panel_filePostfix = (FlowLayout) panel_filePostfix
				.getLayout();
		fl_panel_filePostfix.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_filePostfix);

		JLabel label_3 = new JLabel("文件后缀名：");
		panel_filePostfix.add(label_3);

		comboBox_filePostfix = new JComboBox();
		comboBox_filePostfix.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 设置文件后缀名
				lbl_filePostfix.setText(comboBox_filePostfix.getSelectedItem()
						.toString());
			}
		});
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

				List<Map<String, String>> list = ReportUtils.getAnalogData(1);

				fileNameArea.setValue(list.get(0));
				List<List<Object>> contentList = fileNameArea.getValue();
				List<Object> nameList = contentList.get(0);
				StringBuilder sb_fileName = new StringBuilder();
				for (Object object : nameList) {
					if (object != null)
						sb_fileName.append(object);
				}
				lbl_previewFileName.setText(sb_fileName.toString());
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
		panel_extend.setPreferredSize(new Dimension(10, 100));
		panel2_center.add(panel_extend, BorderLayout.SOUTH);

		JPanel panel2_south = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel2_south.getLayout();
		flowLayout_6.setHgap(80);
		associatePanel.add(panel2_south, BorderLayout.SOUTH);

		JButton btn2ok = new JButton("确定 (O)");
		btn2ok.setMnemonic('O');
		panel2_south.add(btn2ok);

		JButton btn2cancel = new JButton("取消 (C)");
		btn2cancel.setMnemonic('C');
		panel2_south.add(btn2cancel);

		JButton button = new JButton("回到前一页");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 回前一页
				cardLayout.first(panel_right);
			}
		});
		panel2_south.add(button);

		initTree();
		initTable();

	}

	private void initTree() {
		// tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		tree.setToolTipText("选中并使用鼠标拖拽到表格中");
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("选择字段") {
			private static final long serialVersionUID = 1L;
			{
				DefaultMutableTreeNode node_limb;
				node_limb = new DefaultMutableTreeNode("函数");
				node_limb.add(new EdiMutableTreeNode(new SumFunction()));
				node_limb.add(new EdiMutableTreeNode(new SequenceFunction()));
				node_limb.add(new EdiMutableTreeNode(new DateFunction()));
				// 还需要添加更多
				add(node_limb);
				node_limb = new DefaultMutableTreeNode("常量");
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"EDI_CONSTANT", "自定义常量", "自定义常量")));
				add(node_limb);

				node_limb = new DefaultMutableTreeNode("变量");
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("id", null,
						"编号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bno",
						null, "运单编号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("gdesc",
						null, "托寄物内容")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("aweight",
						null, "实际重量")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("weight",
						null, "计费重量")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"weight_unit", null, "重量单位")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("quantity",
						null, "数量/单位")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("pcs",
						null, "件数")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("cusvalue",
						null, "申报价值")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"cus_currency_symbol", null, "申报币别")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("price",
						null, "单价")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"invoice_value", null, "发票申报价值")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("hscode",
						null, "商品编码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("gmodel",
						null, "规格型号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srccode",
						null, "原寄地代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srcname",
						null, "原寄地名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("descode",
						null, "目的地代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("desname",
						null, "目的地名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"gate_type", null, "报关类型")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"customs_date", null, "报关日期")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"exp_customs_date", null, "预报关日期")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("custype",
						null, "类别")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("cusbatch",
						null, "报关批次")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("velist",
						null, "车次")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"srccompany", null, "寄件公司")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srcaddr",
						null, "寄件地址")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srcpname",
						null, "寄件联络人")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srctel",
						null, "寄件电话")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"srcmobile", null, "寄件手机")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_union_no", null, "寄件公司统编号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_business_unit_code", null, "寄件公司经营单位编码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_postal_code", null, "寄件地址邮政编码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_city_code", null, "寄件城市三字代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_city_name", null, "寄件城市名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("srcstate",
						null, "寄件州、省名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"srccountry", null, "寄件国别地区代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_country_code", null, "寄件国别数字代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"src_country_name", null, "寄件国别地区名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"descompany", null, "收件公司")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("desaddr",
						null, "收件地址")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("despname",
						null, "收件联络人")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("destel",
						null, "收件电话")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"desmobile", null, "收件手机")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_union_no", null, "收件公司统编号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_business_unit_code", null, "收件公司经营单位编码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_postal_code", null, "收件地址邮政编码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_city_code", null, "收件城市三字代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_city_name", null, "收件城市名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("desstate",
						null, "收件州、省名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"descountry", null, "收件国别地区代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_country_code", null, "收件国别数字代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"des_country_name", null, "收件国别地区名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("tariff",
						null, "关税税率")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tariff_value", null, "关税")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("vat",
						null, "增值税税率")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"vat_value", null, "增值税")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"payment_type", null, "付款方式")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("feeamt",
						null, "运费")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"insurance", null, "保险费")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"service_charge", null, "服务费")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"miscellaneous", null, "杂费")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"send_date", null, "寄件日期")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"send_time", null, "寄件时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"send_gl_time", null, "寄件格林日期时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"packageno", null, "袋(包)号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"packagenum", null, "袋(包)数")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"express_type", null, "快件类型")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("fdacode",
						null, "FDA指标")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("packing",
						null, "包装方式")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("storages",
						null, "货物存处")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("mawb",
						null, "主提单号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("kjno",
						null, "KJ单号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("flightno",
						null, "车牌/航班号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transport_company_name", null, "运输公司名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"start_city_code", null, "始发城市代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"start_city_name", null, "始发城市名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"end_city_code", null, "目的城市代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"end_city_name", null, "目的城市名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"exemption_code", null, "征免性质代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"exemption_name", null, "征免性质名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"clearance_code", null, "验放代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"containerno", null, "集装箱号")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"trade_type", null, "贸易方式")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transaction_method_code", null, "成交方式代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transaction_method_name", null, "成交方式名称")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"transport_mode", null, "运输方式")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("source",
						null, "数据来源")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("opdt",
						null, "操作日期")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("oparea",
						null, "操作地区")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"op_gl_time", null, "操作格林时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("note",
						null, "备注")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_time", null, "修改时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_by", null, "修改人")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_area", null, "修改地区")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"modify_gl_time", null, "修改格林时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("tr_gdesc",
						null, "托寄物内容翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_srccompany", null, "寄件公司翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_srcaddr", null, "寄件地址翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_srcname", null, "寄件联络人翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_descompany", null, "收件公司翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_desaddr", null, "收件地址翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"tr_desname", null, "收件联络人翻译")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("tr_flag",
						null, "翻译标示")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("sadinfo",
						null, "参考资料审核途径")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("selected",
						null, "是否选择")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("upsign",
						null, "修改标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("adsign",
						null, "审核标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("cfsign",
						null, "审核确认标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("passign",
						null, "清关标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_gl_time", null, "审核确认格林时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_area", null, "审核确认地区")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_time", null, "审核确认时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"confirm_by", null, "审核确认人")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"audit_area", null, "审核地区")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"audit_gl_time", null, "审核格林时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("audit_by",
						null, "审核人")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"audit_time", null, "审核时间")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("psrcnm",
						null, "列印原寄地代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("pdesnm",
						null, "列印目的地代码")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak1",
						null, "备用字段1")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak2",
						null, "备用字段2")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak3",
						null, "备用字段3")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("bak4",
						null, "备用字段4")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"produce_src", null, "原产地")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"fee_currency", null, "运费币别")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("tranflag",
						null, "品名精确翻译匹配标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"hscodeflag", null, "商品编码精确匹配标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity("senssign",
						null, "敏感品名标志")));
				node_limb.add(new EdiMutableTreeNode(new BaseEntity(
						"org_cusbatch", null, "原始报关批次")));
				add(node_limb);

			}
		}));
		// 树拖拽支持
		tree.setDragEnabled(true);
		// tree.setDropMode(DropMode.)
		tree.setTransferHandler(new EdiTreeTransferHandler(tree));
		// 树默认展开
		tree.expandRow(2);
		tree.expandRow(1);
		tree.expandRow(0);
	}

	private void initTable() {
		initTable(headTable, true);
		initTable(bodyTable, false);
		initTable(tailTable, true);
		initTable(table_fileName, false);
	}

	private void initTable(JTable table, boolean addRow) {
		table.setRowHeight(75);

		EdiTableModel edim = new EdiTableModel(1, 6);
		table.setModel(edim);

		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		// table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// ###################################################
		// 应该重新写一个表格渲染器，表头和表体已经表尾 应该有标识
		table.setCellEditor(new EdiTableCellEditor(this));

		// 编辑器 JFrame
		table.setDefaultEditor(ICell.class, new EdiTableCellEditor(this));

		table.setDefaultRenderer(ICell.class, new EdiTableCellRenderer());

		// 表格右键
		table.addMouseListener(new TableMouseListener(this, table, addRow));
		// 表头渲染器
		table.getTableHeader().setDefaultRenderer(new TableHeadCellRenderer());
		// 表头右键
		table.getTableHeader().addMouseListener(
				new TableHeadMouseListener(table));
		// 表格拖拽支持
		table.setDragEnabled(true);
		table.setDropMode(DropMode.USE_SELECTION);
		table.setTransferHandler(new EdiTableTransferHandler(table));
		// 添加表格监听器
	}
}
