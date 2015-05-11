package com.print;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

public class PrintServiceTest {
	public static void main(String[] args) {
		DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(MediaSizeName.ISO_A4);
		PrintService[] pservices = PrintServiceLookup.lookupPrintServices(
				flavor, aset);
		if (pservices.length > 0) {
			DocPrintJob pj = pservices[0].createPrintJob();
			try {
				FileInputStream fis = new FileInputStream("test.ps");
				Doc doc = new SimpleDoc(fis, flavor, null);
				pj.print(doc, aset);
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
			} catch (PrintException e) {
				e.printStackTrace();
			}
		}

	}
}
