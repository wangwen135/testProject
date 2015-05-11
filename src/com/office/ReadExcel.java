package com.office;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcel extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadExcel frame = new ReadExcel();
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
	public ReadExcel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("选择运单号列表文件");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<String> bnoList = readBnoList();
				if (bnoList != null) {
					bnoList = handBnoList(bnoList);
				} else {
					return;
				}

				if (bnoList != null && !bnoList.isEmpty()) {
					System.out.println("xxxxx" + bnoList);
					StringBuffer strbuf = new StringBuffer();
					for (String string : bnoList) {
						strbuf.append(string);
						strbuf.append("\n");
					}
					textArea.setText(strbuf.toString());
				}
			}
		});
		btnNewButton.setBounds(188, 381, 205, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 536, 346);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	public List<String> handBnoList(List<String> readList) {
		if (readList == null || readList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "读取Excel文件内容为空", "警告",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		List<String> errorBno = new ArrayList<String>();

		Pattern p = Pattern.compile("\\d{12}");

		Set<String> bnoSet = new HashSet<String>();

		for (String bno : readList) {
			// 校验不规则运单，并予以提示
			if (p.matcher(bno).matches()) {
				bnoSet.add(bno);
			} else {
				// 过滤重复运单
				errorBno.add(bno);
			}
		}
		if (!errorBno.isEmpty()) {
			// 错误运单
			if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(this,
					"以下运单错误，是否继续？\n" + errorBno, "是否继续",
					JOptionPane.YES_NO_OPTION)) {
				return null;
			}
		}
		if (bnoSet.isEmpty()) {
			JOptionPane.showMessageDialog(this, "没有正确的运单号", "错误",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return new ArrayList<String>(bnoSet);
	}

	public List<String> readBnoList() {

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel",
				"xls");// Excel文件
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = chooser.showOpenDialog(this);

		File f = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
		} else {
			return null;
		}

		FileInputStream is = null;

		try {
			is = new FileInputStream(f);

			List<String> list = new ArrayList<String>();

			HSSFWorkbook wb = new HSSFWorkbook(is);

			HSSFSheet childSheet = wb.getSheetAt(0);// 读取第一个工作表
			int rowNum = childSheet.getLastRowNum();// 读取总行数
			for (int i = 1; i <= rowNum; i++)// 从第一行开始读取
			{
				HSSFRow row = childSheet.getRow(i);
				HSSFCell hssfCell = row.getCell((short) 0);
				if (hssfCell != null) {
					list.add(hssfCell.toString());
				}
			}

			return list;
		} catch (FileNotFoundException e) {
			// logger
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Excel文件没有找到\n" + e.getMessage(), "错误",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// logger
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"读取Excel文件异常\n" + e.getMessage(), "错误",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			// logger
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"读取Excel文件异常\n" + e.getMessage(), "错误",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
