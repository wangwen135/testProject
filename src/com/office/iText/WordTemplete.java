package com.office.iText;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * 根据itext提供的java类库，构建word模板，并添加相应的内容，从而导出word报告；平台不相关
 * 需要引入iText-2.1.7.jar;iTextAsian.jar;iText-rtf-2.1.7.jar
 * 
 * @author ryan
 */
public class WordTemplete {
	private Document document;
	private BaseFont bfChinese;

	public BaseFont getBfChinese() {
		return bfChinese;
	}

	public void setBfChinese(BaseFont bfChinese) {
		this.bfChinese = bfChinese;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public WordTemplete() {
		this.document = new Document(PageSize.A4);

	}

	/**
	 * @param filePath
	 *            建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void openDocument(String filePath) throws DocumentException,
			IOException {
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		RtfWriter2.getInstance(this.document, new FileOutputStream(filePath));
		this.document.open();
		// 设置中文字体
		this.bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
				BaseFont.NOT_EMBEDDED);
	}

	/**
	 * @param titleStr
	 *            标题
	 * @param fontsize
	 *            字体大小
	 * @param fontStyle
	 *            字体样式
	 * @param elementAlign
	 *            对齐方式
	 * @throws DocumentException
	 */
	public void insertTitle(String titleStr, int fontsize, int fontStyle,
			int elementAlign) throws DocumentException {
		Font titleFont = new Font(this.bfChinese, fontsize, fontStyle);
		Paragraph title = new Paragraph(titleStr);
		// 设置标题格式对齐方式
		title.setAlignment(elementAlign);
		title.setFont(titleFont);

		this.document.add(title);
	}

	/**
	 * @param contextStr
	 *            内容
	 * @param fontsize
	 *            字体大小
	 * @param fontStyle
	 *            字体样式
	 * @param elementAlign
	 *            对齐方式
	 * @throws DocumentException
	 */
	public void insertContext(String contextStr, int fontsize, int fontStyle,
			int elementAlign) throws DocumentException {
		// 正文字体风格
		Font contextFont = new Font(bfChinese, fontsize, fontStyle);
		Paragraph context = new Paragraph(contextStr);
		// 设置行距
		context.setLeading(30f);
		// 正文格式左对齐
		context.setAlignment(elementAlign);
		context.setFont(contextFont);
		// 离上一段落（标题）空的行数
		context.setSpacingBefore(5);
		// 设置第一行空的列数
		context.setFirstLineIndent(20);
		document.add(context);
	}

	/*
	 * 测试清单
	 */
	public void insertRiskTable() throws DocumentException {
		Table aTable = new Table(6, 3);
		int width[] = { 10, 40, 17, 13, 10, 10 };
		aTable.setWidths(width);// 设置每列所占比例
		aTable.setWidth(100); // 占页面宽度 90%
		aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable.setAutoFillEmptyCells(true); // 自动填满
		aTable.setBorderWidth(0); // 边框宽度
		aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色
		aTable.setPadding(2);// 衬距，看效果就知道什么意思了
		aTable.setSpacing(3);// 即单元格之间的间距
		aTable.setBorder(2);// 边框

		Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
		Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderColor(new Color(0, 0, 0));
		cell.setBackgroundColor(new Color(153, 204, 255));
		aTable.addCell(cell);

		Cell cell1 = new Cell(new Phrase("测试名称", fontChinese));
		cell1.setVerticalAlignment(Element.ALIGN_CENTER);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorderColor(new Color(0, 0, 0));
		cell1.setBackgroundColor(new Color(153, 204, 255));
		aTable.addCell(cell1);

		Cell cell2 = new Cell(new Phrase("测试发生可能性", fontChinese));
		cell2.setVerticalAlignment(Element.ALIGN_CENTER);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorderColor(new Color(0, 0, 0));
		cell2.setBackgroundColor(new Color(255, 255, 0));
		aTable.addCell(cell2);

		Cell cell3 = new Cell(new Phrase("测试损失度", fontChinese));
		cell3.setVerticalAlignment(Element.ALIGN_CENTER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setBorderColor(new Color(0, 0, 0));
		cell3.setBackgroundColor(new Color(255, 255, 0));
		aTable.addCell(cell3);

		Cell cell4 = new Cell(new Phrase("测试水平", fontChinese));
		cell4.setVerticalAlignment(Element.ALIGN_CENTER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBorderColor(new Color(0, 0, 0));
		cell4.setBackgroundColor(new Color(255, 255, 0));
		aTable.addCell(cell4);

		Cell cell5 = new Cell(new Phrase("测试等级", fontChinese));
		cell5.setVerticalAlignment(Element.ALIGN_CENTER);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell5.setBorderColor(new Color(0, 0, 0));
		cell5.setBackgroundColor(new Color(255, 255, 0));
		aTable.addCell(cell5);

		for (int i = 0; i < 12; i++) {
			aTable.addCell(new Cell(i + ""));
		}
		document.add(aTable);
		document.add(new Paragraph("\n"));
	}

	/*
	 * 现状评估
	 */
	public void insertRiskEvaluationTable() throws DocumentException {
		Table aTable = new Table(12, 4);
		int width1[] = { 5, 20, 5, 20, 5, 5, 5, 5, 5, 5, 5, 5 };
		aTable.setWidths(width1);// 设置每列所占比例
		aTable.setWidth(100); // 占页面宽度 90%
		aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable.setAutoFillEmptyCells(true); // 自动填满
		aTable.setBorderWidth(0); // 边框宽度
		aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色

		Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
		Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setRowspan(2);
		cell.setBorderColor(new Color(0, 0, 0));
		cell.setBackgroundColor(new Color(153, 204, 255));
		aTable.addCell(cell);

		Cell cell2 = new Cell(new Phrase("测试名称", fontChinese));
		cell2.setVerticalAlignment(Element.ALIGN_CENTER);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setRowspan(2);
		cell2.setBorderColor(new Color(0, 0, 0));
		cell2.setBackgroundColor(new Color(153, 204, 255));
		aTable.addCell(cell2);

		Cell cell3 = new Cell(new Phrase("行为代码", fontChinese));
		cell3.setVerticalAlignment(Element.ALIGN_CENTER);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setRowspan(2);
		cell3.setBorderColor(new Color(0, 0, 0));
		cell3.setBackgroundColor(new Color(153, 204, 255));
		aTable.addCell(cell3);

		Cell cell4 = new Cell(new Phrase("引发测试的行为", fontChinese));
		cell4.setVerticalAlignment(Element.ALIGN_CENTER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setRowspan(2);
		cell4.setBorderColor(new Color(0, 0, 0));
		cell4.setBackgroundColor(new Color(153, 204, 255));
		aTable.addCell(cell4);

		Cell cell5 = new Cell(new Phrase("控制现状", fontChinese));
		cell5.setVerticalAlignment(Element.ALIGN_CENTER);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell5.setColspan(8);
		cell5.setBorderColor(new Color(0, 0, 0));
		cell5.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell5);

		Cell cell6 = new Cell(new Phrase("部门内审查", fontChinese));
		cell6.setVerticalAlignment(Element.ALIGN_CENTER);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setBorderColor(new Color(0, 0, 0));
		cell6.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell6);

		Cell cell7 = new Cell(new Phrase("测试意识", fontChinese));
		cell7.setVerticalAlignment(Element.ALIGN_CENTER);
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBorderColor(new Color(0, 0, 0));
		cell7.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell7);

		Cell cell8 = new Cell(new Phrase("过程监控", fontChinese));
		cell8.setVerticalAlignment(Element.ALIGN_CENTER);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell8.setBorderColor(new Color(0, 0, 0));
		cell8.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell8);

		Cell cell9 = new Cell(new Phrase("奖惩机制", fontChinese));
		cell9.setVerticalAlignment(Element.ALIGN_CENTER);
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBorderColor(new Color(0, 0, 0));
		cell9.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell9);

		Cell cell10 = new Cell(new Phrase("明确责权", fontChinese));
		cell10.setVerticalAlignment(Element.ALIGN_CENTER);
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBorderColor(new Color(0, 0, 0));
		cell10.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell10);

		Cell cell11 = new Cell(new Phrase("执行者能力要求", fontChinese));
		cell11.setVerticalAlignment(Element.ALIGN_CENTER);
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBorderColor(new Color(0, 0, 0));
		cell11.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell11);

		Cell cell12 = new Cell(new Phrase("专业审查", fontChinese));
		cell12.setVerticalAlignment(Element.ALIGN_CENTER);
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell12.setBorderColor(new Color(0, 0, 0));
		cell12.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell12);

		Cell cell13 = new Cell(new Phrase("资源配置", fontChinese));
		cell13.setVerticalAlignment(Element.ALIGN_CENTER);
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setBorderColor(new Color(0, 0, 0));
		cell13.setBackgroundColor(new Color(204, 255, 255));
		aTable.addCell(cell13);

		for (int i = 0; i < 24; i++) {
			aTable.addCell(new Cell(i + ""));
		}

		document.add(aTable);
		document.add(new Paragraph("\n"));
	}

	/*
	 * 测试控制清单
	 */
	public void insertRiskControlTable() throws DocumentException {
		Table aTable = new Table(11, 3);
		int width[] = { 5, 13, 5, 9, 9, 13, 9, 9, 9, 9, 9 };
		aTable.setWidths(width);// 设置每列所占比例
		aTable.setWidth(100); // 占页面宽度 90%
		aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
		aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
		aTable.setAutoFillEmptyCells(true); // 自动填满
		aTable.setBorderWidth(0); // 边框宽度
		aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色

		Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
		Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderColor(new Color(0, 0, 0));
		cell.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell);

		Cell cell1 = new Cell(new Phrase("测试名称", fontChinese));
		cell1.setVerticalAlignment(Element.ALIGN_CENTER);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorderColor(new Color(0, 0, 0));
		cell1.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell1);

		Cell cell2 = new Cell(new Phrase("行为代码", fontChinese));
		cell2.setVerticalAlignment(Element.ALIGN_CENTER);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorderColor(new Color(0, 0, 0));
		cell2.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell2);

		Cell cell3 = new Cell(new Phrase("引发测试的行为", fontChinese));
		cell3.setVerticalAlignment(Element.ALIGN_CENTER);
		cell3.setBorderColor(new Color(0, 0, 0));
		cell3.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell3);

		Cell cell4 = new Cell(new Phrase("测试控制态度", fontChinese));
		cell4.setVerticalAlignment(Element.ALIGN_CENTER);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBorderColor(new Color(0, 0, 0));
		cell4.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell4);

		Cell cell5 = new Cell(new Phrase("控制措施", fontChinese));
		cell5.setVerticalAlignment(Element.ALIGN_CENTER);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell5.setBorderColor(new Color(0, 0, 0));
		cell5.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell5);

		Cell cell6 = new Cell(new Phrase("措施类型", fontChinese));
		cell6.setVerticalAlignment(Element.ALIGN_CENTER);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setBorderColor(new Color(0, 0, 0));
		cell6.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell6);

		Cell cell7 = new Cell(new Phrase("完成标志", fontChinese));
		cell7.setVerticalAlignment(Element.ALIGN_CENTER);
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBorderColor(new Color(0, 0, 0));
		cell7.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell7);

		Cell cell8 = new Cell(new Phrase("控制措施完成时间", fontChinese));
		cell8.setVerticalAlignment(Element.ALIGN_CENTER);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell8.setBorderColor(new Color(0, 0, 0));
		cell8.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell8);

		Cell cell9 = new Cell(new Phrase("控制措施牵头部门", fontChinese));
		cell9.setVerticalAlignment(Element.ALIGN_CENTER);
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBorderColor(new Color(0, 0, 0));
		cell9.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell9);

		Cell cell10 = new Cell(new Phrase("控制措施配合部门", fontChinese));
		cell10.setVerticalAlignment(Element.ALIGN_CENTER);
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBorderColor(new Color(0, 0, 0));
		cell10.setBackgroundColor(new Color(204, 153, 255));
		aTable.addCell(cell10);

		for (int i = 0; i < 22; i++) {
			aTable.addCell(new Cell(i + ""));
		}

		document.add(aTable);
		document.add(new Paragraph("\n"));

	}

	/**
	 * @param imgUrl
	 *            图片路径
	 * @param imageAlign
	 *            显示位置
	 * @param height
	 *            显示高度
	 * @param weight
	 *            显示宽度
	 * @param percent
	 *            显示比例
	 * @param heightPercent
	 *            显示高度比例
	 * @param weightPercent
	 *            显示宽度比例
	 * @param rotation
	 *            显示图片旋转角度
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void insertImg(String imgUrl, int imageAlign, int height,
			int weight, int percent, int heightPercent, int weightPercent,
			int rotation) throws MalformedURLException, IOException,
			DocumentException {
		// 添加图片
		Image img = Image.getInstance(imgUrl);
		if (img == null)
			return;
		img.setAbsolutePosition(0, 0);
		img.setAlignment(imageAlign);
		img.scaleAbsolute(height, weight);
		img.scalePercent(percent);
		img.scalePercent(heightPercent, weightPercent);
		img.setRotation(rotation);

		document.add(img);
	}

	public void closeDocument() throws DocumentException {
		this.document.close();
	}

	public static void main(String[] args) throws DocumentException,
			IOException {
		WordTemplete wt = new WordTemplete();
		wt.openDocument("d:\\dome1.doc");
		wt.insertTitle("一、测试基本情况", 12, Font.BOLD, Element.ALIGN_CENTER);

		wt.insertContext(
				"共识别出XXX个测试，XXX项测试行为，其中，违规类测试XX项，占测试总量的XX%，违约类测试XX项，占测试总量的XX%，侵权类测试XX项，占测试总量的XX%，怠于类测试XX项，占测试总量的XX%，不当类测试XX项，占测试总量的XX%。",
				12, Font.NORMAL, Element.ALIGN_LEFT);
		wt.insertContext(
				"根据测试测评结果，各等级测试数量及所占百分比分别为：一级测试共XX项，占测试总量的XX%；二级测试共XX项，占测试总量的XX%；三级测试共XX项，占测试总量的XX%；四级测试共XX项，占测试总量的XX%；五级测试共XX项，占测试总量的XX%。\n\n",
				12, Font.NORMAL, Element.ALIGN_LEFT);

		wt.insertContext("测试定向分析结果如下：", 12, Font.NORMAL, Element.ALIGN_LEFT);

		wt.insertContext("① 部门角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
		wt.insertImg("test.JPG", Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);
		wt.insertContext("② 主体角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
		wt.insertImg("test.JPG", Image.ALIGN_CENTER, 12, 35, 50, 60, 60, 30);
		wt.insertContext("③ 部门主体交叉角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
		wt.insertImg("test.JPG", Image.ALIGN_CENTER, 50, 75, 100, 100, 100, 30);
		wt.insertContext("④ 业务活动角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
		wt.insertImg("test.JPG", Image.ALIGN_CENTER, 12, 35, 50, 80, 80, 30);

		wt.insertTitle("二、重大测试清单", 12, Font.BOLD, Element.ALIGN_CENTER);
		wt.insertRiskTable();
		wt.insertTitle("三、测试控制现状评估结果", 12, Font.BOLD, Element.ALIGN_CENTER);
		wt.insertRiskEvaluationTable();
		wt.insertTitle("四、测试控制计划", 12, Font.BOLD, Element.ALIGN_CENTER);
		wt.insertRiskControlTable();
		wt.closeDocument();
	}
}
