package com.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * 描述：BarCode
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-26      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class BarCodeImage {

	/**
	 * <pre>
	 * 根据msg生成 128 BarCode
	 * </pre>
	 * 
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage create(String msg) throws IOException {
		Code128Bean bean = new Code128Bean();

		final int dpi = 98;

		bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

		bean.setFontSize(0);

		bean.setBarHeight(7.09);

		bean.doQuietZone(false);

		BitmapCanvasProvider canvas = new BitmapCanvasProvider(290,
				BufferedImage.TYPE_BYTE_BINARY, false, 0);

		bean.generateBarcode(canvas, msg);

		canvas.finish();

		return canvas.getBufferedImage();

	}
}
