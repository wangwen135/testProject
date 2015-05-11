package com.sf.module.EDI.EDIImpl.ui.format;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.poi.hssf.util.HSSFColor;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.Format;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.IFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.align.AlignFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.border.BorderFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.color.ColorFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.font.FontFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.DateValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.NumberValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.StringValueFormat;

/**
 * 描述：格式编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FormatEditor extends JDialog {

	private static final long serialVersionUID = -7371815304555491728L;

	private final JPanel contentPanel = new JPanel();

	private static FormatEditor editor;

	/**
	 * 确定完成编辑
	 */
	private boolean confirm = false;

	public static synchronized FormatEditor getInstance(Window windows) {
		if (editor == null) {
			editor = new FormatEditor(windows);
		}
		return editor;
	}

	/**
	 * 颜色列表
	 */
	public static Hashtable<Integer, Color> colorsTable = new Hashtable<Integer, Color>();
	static {
		@SuppressWarnings("unchecked")
		Hashtable<Integer, HSSFColor> colorTable = HSSFColor.getIndexHash();
		for (int i = 0; i < 100; i++) {// 按照顺序来
			if (colorTable.containsKey(i)) {
				HSSFColor hssfc = colorTable.get(i);
				short[] s = hssfc.getTriplet();
				colorsTable.put(i, new Color(s[0], s[1], s[2]));
			}
		}
	}

	@SuppressWarnings("unused")
	private ICell cell;
	private IFormat cellFormat;

	//
	private JTabbedPane tabbedPane;
	private JTabbedPane tabbedPane_value;

	// 格式
	private StringValuePanel valuePanel_string;
	private NumberValuePanel valuePanel_number;
	private DateValuePanel valuePanel_date;

	// 对齐
	private JComboBox comboBox_AlignHorizontal;
	private JComboBox comboBox_AlignVertical;
	private JCheckBox chckbx_Wrap;

	// 字体
	private JTextField textField_fontFamily;
	private JTextField textField_fontStyle;
	private JTextField textField_fontSize;
	private JList list_fontFamily;
	private JList list_fontStyle;
	private JList list_fontSize;
	private JLabel label_fontPreview;

	// 边框
	private JCheckBox checkBox_borderTop;
	private JCheckBox checkBox_borderLeft;
	private JCheckBox checkBox_borderBottom;
	private JCheckBox checkBox_borderRight;
	private JLabel label_borderDemo;

	// 颜色
	private JCheckBox checkBox_fillColor;
	private JCheckBox checkBox_fontColor;
	// 示例：
	private JPanel panel_backgroundDemo;
	private JLabel label_foregroundDemo;
	private JPanel panel_background;
	private JPanel panel_foreground;
	// 前景色
	private Integer foregroundColor;
	// 背景颜色
	private Integer backgroundColor;

	public void showEdit(ICell cell) {
		confirm = false;
		this.cell = cell;
		cellFormat = cell.getFormat();
		if (cellFormat == null) {
			cellFormat = new Format();
			cell.setFormat(cellFormat);
		}

		modelToView();
		setVisible(true);
	}

	/**
	 * <pre>
	 * 模型到视图
	 * </pre>
	 */
	private void modelToView() {
		tabbedPane.setSelectedIndex(0);
		// 数值格式
		IValueFormat valueFormat = cellFormat.getFormat();
		if (valueFormat != null) {
			if (StringValueFormat.TYPE.equals(valueFormat.getType())) {
				tabbedPane_value.setSelectedComponent(valuePanel_string);
				valuePanel_string.setValueFormat(valueFormat);
				valuePanel_number.reset();
				valuePanel_date.reset();
			} else if (NumberValueFormat.TYPE.equals(valueFormat.getType())) {
				tabbedPane_value.setSelectedComponent(valuePanel_number);
				valuePanel_number.setValueFormat(valueFormat);
				valuePanel_string.reset();
				valuePanel_date.reset();
			} else if (DateValueFormat.TYPE.equals(valueFormat.getType())) {
				tabbedPane_value.setSelectedComponent(valuePanel_date);
				valuePanel_date.setValueFormat(valueFormat);
				valuePanel_number.reset();
				valuePanel_string.reset();
			}
		} else {
			tabbedPane_value.setSelectedIndex(0);
			valuePanel_number.reset();
			valuePanel_string.reset();
			valuePanel_date.reset();
		}

		// 对齐
		AlignFormat align = cellFormat.getAlign();
		if (align != null) {
			// 设置对齐
			setAlignFormat(align);
		} else {
			// 重置对齐
			resetAlignFormat();
		}

		// 字体
		FontFormat font = cellFormat.getFont();
		if (font != null) {
			// 设置字体
			setFontFormat(font);
		} else {
			// 重置字体
			resetFontFormat();
		}

		// 边框
		BorderFormat border = cellFormat.getBorder();
		if (border != null) {
			// 设置边框
			setBorderFormat(border);
		} else {
			// 重置边框
			resetBorderFormat();
		}

		// 颜色
		ColorFormat color = cellFormat.getColor();
		if (color != null) {
			// 设置颜色
			setColorFormat(color);
		} else {
			// 重置颜色
			resetColorFormat();
		}
	}

	private void setAlignFormat(AlignFormat align) {
		comboBox_AlignHorizontal.setSelectedIndex(align.getAlignment());
		comboBox_AlignVertical.setSelectedIndex(align.getVerticalAlignment());
		chckbx_Wrap.setSelected(align.isWrap());
	}

	private AlignFormat getAlignFormat() {
		// 判断是否修改
		if (comboBox_AlignHorizontal.getSelectedIndex() == AlignFormat.ALIGN_GENERAL
				&& comboBox_AlignVertical.getSelectedIndex() == AlignFormat.VERTICAL_BOTTOM
				&& !chckbx_Wrap.isSelected()) {
			return null;
		}
		AlignFormat align = new AlignFormat();
		align.setAlignment(comboBox_AlignHorizontal.getSelectedIndex());
		align.setVerticalAlignment(comboBox_AlignVertical.getSelectedIndex());
		align.setWrap(chckbx_Wrap.isSelected());
		return align;
	}

	private void resetAlignFormat() {
		comboBox_AlignHorizontal.setSelectedIndex(AlignFormat.ALIGN_GENERAL);
		comboBox_AlignVertical.setSelectedIndex(AlignFormat.VERTICAL_BOTTOM);
		chckbx_Wrap.setSelected(false);
	}

	private void setFontFormat(FontFormat font) {
		list_fontFamily.setSelectedValue(font.getFamily(), true);
		list_fontStyle.setSelectedIndex(font.getStyle());
		list_fontSize.setSelectedValue(font.getSize(), true);

	}

	private FontFormat getFontFormat() {
		if ("宋体".equals(list_fontFamily.getSelectedValue())
				&& 0 == list_fontStyle.getSelectedIndex()
				&& 10 == (Integer) list_fontSize.getSelectedValue()) {
			return null;
		}
		FontFormat font = new FontFormat();
		font.setFamily((String) list_fontFamily.getSelectedValue());
		font.setStyle(list_fontStyle.getSelectedIndex());
		font.setSize((Integer) list_fontSize.getSelectedValue());
		return font;
	}

	private void resetFontFormat() {
		list_fontFamily.setSelectedValue("宋体", true);
		list_fontStyle.setSelectedIndex(0);
		list_fontSize.setSelectedValue(10, true);
	}

	private void setBorderFormat(BorderFormat border) {
		checkBox_borderTop.setSelected(border.isTop());
		checkBox_borderLeft.setSelected(border.isLeft());
		checkBox_borderBottom.setSelected(border.isBottom());
		checkBox_borderRight.setSelected(border.isRight());
	}

	private BorderFormat getBorderFormat() {
		if (!checkBox_borderTop.isSelected()
				&& !checkBox_borderLeft.isSelected()
				&& !checkBox_borderBottom.isSelected()
				&& !checkBox_borderRight.isSelected()) {
			return null;
		}
		BorderFormat border = new BorderFormat();
		border.setTop(checkBox_borderTop.isSelected());
		border.setLeft(checkBox_borderLeft.isSelected());
		border.setBottom(checkBox_borderBottom.isSelected());
		border.setRight(checkBox_borderRight.isSelected());

		return border;
	}

	private void resetBorderFormat() {
		checkBox_borderTop.setSelected(false);
		checkBox_borderLeft.setSelected(false);
		checkBox_borderBottom.setSelected(false);
		checkBox_borderRight.setSelected(false);
	}

	/**
	 * <pre>
	 * 设置视图前景颜色
	 * </pre>
	 * 
	 * @param colorID
	 */
	private void setViewForeground(Integer colorID) {
		// 如果为0
		Color c = null;
		if (colorID == 0) {
			c = Color.BLACK;
		} else {
			c = colorsTable.get(colorID);
		}
		if (c == null) {
			return;
		}

		label_foregroundDemo.setForeground(c);
		panel_foreground.setBackground(c);
		// 前景色
		this.foregroundColor = colorID;

	}

	/**
	 * <pre>
	 * 设置视图背景颜色
	 * </pre>
	 * 
	 * @param colorID
	 */
	private void setViewBackground(Integer colorID) {
		Color c = null;
		if (colorID == 0) {
			c = Color.WHITE;
		} else {
			c = colorsTable.get(colorID);
		}
		if (c == null) {
			return;
		}

		panel_backgroundDemo.setBackground(c);
		panel_background.setBackground(c);

		// 背景颜色
		this.backgroundColor = colorID;
	}

	private void setColorFormat(ColorFormat color) {
		setViewForeground(color.getForeground());
		setViewBackground(color.getBackground());
	}

	private ColorFormat getColorFormat() {
		if (this.foregroundColor == 0 && this.backgroundColor == 0)
			return null;
		ColorFormat colorFormat = new ColorFormat();
		colorFormat.setForeground(this.foregroundColor);
		colorFormat.setBackground(this.backgroundColor);
		return colorFormat;
	}

	private void resetColorFormat() {
		setViewForeground(0);
		setViewBackground(0);
		checkBox_fillColor.setSelected(true);
		checkBox_fontColor.setSelected(false);
	}

	/**
	 * <pre>
	 * 视图到模型
	 * </pre>
	 */
	private void viewToModel() {
		cellFormat.setAlign(getAlignFormat());
		cellFormat.setFont(getFontFormat());
		cellFormat.setBorder(getBorderFormat());
		cellFormat.setColor(getColorFormat());
		if (tabbedPane_value.getSelectedIndex() == 0) {
			cellFormat.setFormat(null);
		} else {
			IValuePanel panel = (IValuePanel) tabbedPane_value
					.getSelectedComponent();
			cellFormat.setFormat(panel.getValueFormat());
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormatEditor dialog = new FormatEditor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private FormatEditor(Window windows) {
		super(windows);
		setModal(true);
		setResizable(false);
		setTitle("单元格格式");
		setBounds(100, 100, 425, 360);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			{
				JPanel panel_value = new JPanel();
				tabbedPane.addTab("  数 值  ", null, panel_value, null);
				panel_value.setLayout(new BorderLayout(0, 0));

				JPanel panel_4 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				panel_value.add(panel_4, BorderLayout.NORTH);

				JLabel label = new JLabel("分类：");
				panel_4.add(label);

				tabbedPane_value = new JTabbedPane(JTabbedPane.LEFT);
				panel_value.add(tabbedPane_value, BorderLayout.CENTER);

				JPanel panel_general = new JPanel();
				tabbedPane_value.addTab("  常 规  ", null, panel_general, null);
				panel_general.setLayout(null);

				JLabel lblNewLabel_1 = new JLabel("单元格不包涵任何特定的格式");
				lblNewLabel_1.setBounds(20, 10, 298, 24);
				panel_general.add(lblNewLabel_1);

				valuePanel_number = new NumberValuePanel();
				tabbedPane_value.addTab("  数 字  ", null, valuePanel_number,
						null);
				valuePanel_string = new StringValuePanel();
				tabbedPane_value.addTab("  文 本  ", null, valuePanel_string,
						null);

				valuePanel_date = new DateValuePanel();
				tabbedPane_value.addTab("  日 期  ", null, valuePanel_date, null);

			}
			{
				JPanel panel_align = new JPanel();
				tabbedPane.addTab("  对 齐  ", null, panel_align, null);
				panel_align.setLayout(null);

				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null,
						"\u6587\u672C\u5BF9\u9F50\u65B9\u5F0F",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 10, 246, 98);
				panel_align.add(panel);
				panel.setLayout(null);

				comboBox_AlignHorizontal = new JComboBox();
				comboBox_AlignHorizontal.setModel(new DefaultComboBoxModel(
						new String[] { "常规", "靠左", "居中", "靠右", "填充", "两端对齐",
								"跨列居中" }));
				comboBox_AlignHorizontal.setBounds(88, 25, 148, 21);
				panel.add(comboBox_AlignHorizontal);

				comboBox_AlignVertical = new JComboBox();
				comboBox_AlignVertical.setModel(new DefaultComboBoxModel(
						new String[] { "靠上", "居中", "靠下", "两端对齐" }));
				comboBox_AlignVertical.setBounds(88, 56, 148, 21);
				panel.add(comboBox_AlignVertical);

				JLabel label = new JLabel("水平对齐：");
				label.setBounds(10, 28, 77, 15);
				panel.add(label);

				JLabel label_1 = new JLabel("垂直对齐：");
				label_1.setBounds(10, 59, 77, 15);
				panel.add(label_1);

				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null,
						"\u6587\u672C\u63A7\u5236", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 128, 246, 75);
				panel_align.add(panel_1);
				panel_1.setLayout(null);

				chckbx_Wrap = new JCheckBox("自动换行");
				chckbx_Wrap.setBounds(10, 25, 103, 23);
				panel_1.add(chckbx_Wrap);
			}
			{
				JPanel panel_font = new JPanel();
				tabbedPane.addTab("  字 体  ", null, panel_font, null);
				panel_font.setLayout(null);

				JLabel label = new JLabel("字体：");
				label.setBounds(10, 5, 54, 15);
				panel_font.add(label);

				textField_fontFamily = new JTextField();
				textField_fontFamily.setEditable(false);
				textField_fontFamily.setBounds(10, 25, 205, 21);
				panel_font.add(textField_fontFamily);
				textField_fontFamily.setColumns(10);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 50, 205, 120);
				panel_font.add(scrollPane);

				list_fontFamily = new JList();
				list_fontFamily
						.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								textField_fontFamily.setText(list_fontFamily
										.getSelectedValue().toString());
								setFontPreView();
							}
						});
				list_fontFamily.setModel(new AbstractListModel() {
					private static final long serialVersionUID = -4064423874273249237L;
					GraphicsEnvironment ge = GraphicsEnvironment
							.getLocalGraphicsEnvironment();
					String[] values = ge.getAvailableFontFamilyNames();

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});
				scrollPane.setViewportView(list_fontFamily);

				JLabel lblNewLabel = new JLabel("字形：");
				lblNewLabel.setBounds(225, 5, 54, 15);
				panel_font.add(lblNewLabel);

				textField_fontStyle = new JTextField();
				textField_fontStyle.setEditable(false);
				textField_fontStyle.setColumns(10);
				textField_fontStyle.setBounds(225, 25, 90, 21);
				panel_font.add(textField_fontStyle);

				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(225, 50, 90, 120);
				panel_font.add(scrollPane_1);

				list_fontStyle = new JList();
				list_fontStyle
						.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								textField_fontStyle.setText(list_fontStyle
										.getSelectedValue().toString());
								setFontPreView();
							}
						});

				list_fontStyle.setModel(new AbstractListModel() {
					private static final long serialVersionUID = 1863221353376190580L;
					String[] values = new String[] { "常规", "加粗", "倾斜", "加粗 倾斜" };

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});
				scrollPane_1.setViewportView(list_fontStyle);

				textField_fontSize = new JTextField();
				textField_fontSize.setEditable(false);
				textField_fontSize.setColumns(10);
				textField_fontSize.setBounds(325, 25, 70, 21);
				panel_font.add(textField_fontSize);

				JScrollPane scrollPane_2 = new JScrollPane();
				scrollPane_2.setBounds(325, 50, 70, 120);
				panel_font.add(scrollPane_2);

				list_fontSize = new JList();
				list_fontSize
						.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								textField_fontSize.setText(list_fontSize
										.getSelectedValue().toString());
								setFontPreView();
							}
						});
				list_fontSize.setModel(new AbstractListModel() {

					private static final long serialVersionUID = 7698920287619577311L;

					public int getSize() {
						return 100;
					}

					public Object getElementAt(int index) {
						return index + 1;
					}
				});
				scrollPane_2.setViewportView(list_fontSize);

				JLabel label_1 = new JLabel("字号：");
				label_1.setBounds(325, 5, 54, 15);
				panel_font.add(label_1);

				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "\u9884\u89C8",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 175, 385, 75);
				panel_font.add(panel);
				panel.setLayout(new BorderLayout(0, 0));

				label_fontPreview = new JLabel("字体  字形  字号");
				label_fontPreview.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label_fontPreview, BorderLayout.CENTER);
			}
			{
				JPanel panel_border = new JPanel();
				tabbedPane.addTab("  边 框  ", null, panel_border, null);
				panel_border.setLayout(null);

				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "\u8FB9\u6846",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 75, 232, 151);
				panel_border.add(panel);
				panel.setLayout(null);

				checkBox_borderTop = new JCheckBox("上边框");
				checkBox_borderRight = new JCheckBox("右边框");
				checkBox_borderBottom = new JCheckBox("下边框");
				checkBox_borderLeft = new JCheckBox("左边框");
				label_borderDemo = new JLabel("文本");
				checkBox_borderTop.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						drawBroder();
					}
				});
				checkBox_borderTop.setBounds(80, 20, 68, 38);
				panel.add(checkBox_borderTop);
				checkBox_borderTop.setVerticalTextPosition(SwingConstants.TOP);
				checkBox_borderTop
						.setHorizontalTextPosition(SwingConstants.CENTER);
				checkBox_borderTop
						.setHorizontalAlignment(SwingConstants.CENTER);
				checkBox_borderLeft.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						drawBroder();
					}
				});
				checkBox_borderLeft.setBounds(6, 62, 72, 36);
				panel.add(checkBox_borderLeft);
				checkBox_borderLeft
						.setHorizontalAlignment(SwingConstants.RIGHT);
				checkBox_borderLeft
						.setHorizontalTextPosition(SwingConstants.LEFT);
				checkBox_borderLeft.setAutoscrolls(true);

				label_borderDemo.setBounds(80, 60, 68, 38);
				panel.add(label_borderDemo);
				label_borderDemo.setHorizontalAlignment(SwingConstants.CENTER);

				checkBox_borderRight.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						drawBroder();
					}
				});
				checkBox_borderRight.setBounds(150, 61, 75, 36);
				panel.add(checkBox_borderRight);

				checkBox_borderBottom.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						drawBroder();
					}
				});
				checkBox_borderBottom.setBounds(80, 100, 68, 38);
				panel.add(checkBox_borderBottom);
				checkBox_borderBottom
						.setVerticalTextPosition(SwingConstants.BOTTOM);
				checkBox_borderBottom
						.setHorizontalTextPosition(SwingConstants.CENTER);
				checkBox_borderBottom
						.setHorizontalAlignment(SwingConstants.CENTER);

				JPanel panel_1 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
				flowLayout.setHgap(25);
				panel_1.setBorder(new TitledBorder(null, "\u9884\u7F6E",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 10, 232, 65);
				panel_border.add(panel_1);

				JButton button_NoBorder = new JButton("无边框");
				button_NoBorder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkBox_borderTop.setSelected(false);
						checkBox_borderRight.setSelected(false);
						checkBox_borderBottom.setSelected(false);
						checkBox_borderLeft.setSelected(false);
					}
				});
				panel_1.add(button_NoBorder);

				JButton button_WrapBorder = new JButton("外边框");
				button_WrapBorder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkBox_borderTop.setSelected(true);
						checkBox_borderRight.setSelected(true);
						checkBox_borderBottom.setSelected(true);
						checkBox_borderLeft.setSelected(true);
					}
				});
				panel_1.add(button_WrapBorder);
			}

			{// 颜色设置区域
				JPanel panel_color = new JPanel();
				tabbedPane.addTab("  颜 色  ", null, panel_color, null);
				panel_color.setLayout(null);

				// ----颜色选择框----
				JPanel panel = new JPanel();
				panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
						null, null));
				panel.setBounds(10, 106, 384, 140);
				panel_color.add(panel);
				panel.setLayout(new GridLayout(0, 10, 0, 0));

				for (Entry<Integer, Color> element : colorsTable.entrySet()) {
					JButton btn = new JButton() {
						private static final long serialVersionUID = -5570545546841490532L;

						@Override
						protected void paintComponent(Graphics g) {
							super.paintComponent(g);
							Color c = getForeground();
							g.setColor(c);
							g.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
						}
					};

					Color c = element.getValue();
					final Integer key = element.getKey();
					// btn.setBackground(c);
					btn.setForeground(c);
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (checkBox_fontColor.isSelected()) {
								setViewForeground(key);
							} else {
								setViewBackground(key);
							}
						}
					});
					panel.add(btn);
				}

				JButton btn_unfillColor = new JButton("无填充颜色");
				btn_unfillColor.setBounds(10, 80, 100, 23);
				btn_unfillColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setViewBackground(0);
					}
				});
				panel_color.add(btn_unfillColor);

				JButton btn_autoColor = new JButton("自动(黑色)");
				btn_autoColor.setBounds(294, 80, 100, 23);
				btn_autoColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setViewForeground(0);
					}
				});
				panel_color.add(btn_autoColor);

				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new TitledBorder(null, "示例",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_2.setBounds(120, 23, 164, 68);
				panel_color.add(panel_2);
				panel_2.setLayout(new BorderLayout(0, 0));

				panel_backgroundDemo = new JPanel();
				panel_backgroundDemo.setBackground(Color.WHITE);
				panel_2.add(panel_backgroundDemo, BorderLayout.CENTER);
				panel_backgroundDemo.setLayout(new BorderLayout(0, 0));

				label_foregroundDemo = new JLabel("颜色示例");
				label_foregroundDemo.setFont(new Font("宋体", Font.BOLD, 14));
				label_foregroundDemo
						.setHorizontalAlignment(SwingConstants.CENTER);
				panel_backgroundDemo.add(label_foregroundDemo,
						BorderLayout.CENTER);

				checkBox_fillColor = new JCheckBox("填充颜色");
				checkBox_fillColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkBox_fontColor.setSelected(!checkBox_fontColor
								.isSelected());
					}
				});
				checkBox_fillColor.setHorizontalAlignment(SwingConstants.RIGHT);
				checkBox_fillColor
						.setHorizontalTextPosition(SwingConstants.LEFT);
				checkBox_fillColor.setSelected(true);
				checkBox_fillColor.setBounds(10, 6, 100, 23);
				panel_color.add(checkBox_fillColor);

				checkBox_fontColor = new JCheckBox("字体颜色");
				checkBox_fontColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkBox_fillColor.setSelected(!checkBox_fillColor
								.isSelected());
					}
				});
				checkBox_fontColor.setBounds(294, 6, 100, 23);
				panel_color.add(checkBox_fontColor);

				// 两个显示颜色的面板
				panel_background = new JPanel();
				panel_background.setBackground(Color.WHITE);
				panel_background.setBounds(10, 34, 100, 38);
				panel_color.add(panel_background);

				panel_foreground = new JPanel();
				panel_foreground.setBackground(Color.BLACK);
				panel_foreground.setBounds(294, 35, 100, 37);
				panel_color.add(panel_foreground);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定(O)");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewToModel();
						confirm = true;
						setVisible(false);
					}
				});
				okButton.setMnemonic('o');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消(C)");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setMnemonic('c');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	/**
	 * <pre>
	 * 设置字体预览
	 * </pre>
	 */
	public void setFontPreView() {
		StringBuffer strbuf = new StringBuffer();
		Object family = list_fontFamily.getSelectedValue();
		if (family == null) {
			family = "字体";
		}
		strbuf.append(family);

		strbuf.append(" ");
		strbuf.append(list_fontStyle.getSelectedValue());
		strbuf.append(" ");

		int style = list_fontStyle.getSelectedIndex();

		int size = list_fontSize.getSelectedIndex() + 1;
		strbuf.append(size);

		label_fontPreview.setText(strbuf.toString());
		label_fontPreview.setFont(new Font(family.toString(), style, size + 2));
	}

	/**
	 * <pre>
	 * 绘制边框
	 * </pre>
	 */
	private void drawBroder() {
		int topb = 0;
		int leftb = 0;
		int rightb = 0;
		int bottomb = 0;

		if (checkBox_borderTop.isSelected()) {
			topb = 1;
		}
		if (checkBox_borderLeft.isSelected()) {
			leftb = 1;
		}
		if (checkBox_borderRight.isSelected()) {
			rightb = 1;
		}
		if (checkBox_borderBottom.isSelected()) {
			bottomb = 1;
		}

		label_borderDemo.setBorder(new MatteBorder(topb, leftb, bottomb,
				rightb, Color.BLACK));

	}

	/**
	 * <pre>
	 * 是否是确定
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}
