package com.image.barCode;

/*
 * Copyright 2004 Jeremias Maerki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * This example demonstrates creating a bitmap barcode using the bean API.
 * 
 * @author Jeremias Maerki
 * @version $Id: SampleBitmapBarcodeWithBean.java,v 1.2 2006/11/07 16:45:28
 *          jmaerki Exp $
 */
public class SampleBitmapBarcodeWithBean {

	public static void main(String[] args) {
		try {
			// Create the barcode bean
			// Code39Bean bean = new Code39Bean();
			Code128Bean bean = new Code128Bean();
			// Interleaved2Of5Bean bean = new Interleaved2Of5Bean();

			final int dpi = 98;

			// Configure the barcode generator
			bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); // makes the narrow
																// bar width
																// exactly one
																// pixel
			System.out.println(bean.getBarHeight());
			System.out.println(bean.getBarWidth(1));
			System.out.println(bean.getHeight());
			System.out.println(bean.getHumanReadableHeight());
			
			bean.setFontSize(0);
			
			bean.setBarHeight(7.09);
			

			// bean.setWideFactor(3);
			bean.doQuietZone(false);

			// Open output file
			File outputFile = new File("out.jpg");
			OutputStream out = new FileOutputStream(outputFile);
			try {
				// Set up the canvas provider for monochrome JPEG output
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,
						"image/jpeg", 290, BufferedImage.TYPE_BYTE_BINARY,
						false, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, "200727521396");

				// Signal end of generation
				canvas.finish();
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
