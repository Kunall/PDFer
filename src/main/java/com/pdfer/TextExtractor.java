package com.pdfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/*
 * @author Kunall
 *
 * Example to extract text from PDF file using Apache PDFBox.
 *
 */
public class TextExtractor {

	final static Logger logger = Logger.getLogger(TextExtractor.class);

	/*
	 * This method accepts the path of the PDF from which text needs to be
	 * extracted and returns the text.
	 * 
	 * @param pdfPath Path of the PDF file.
	 * 
	 * @return the content inside the PDF.
	 */
	public static String getTextFromPDF(final String pdfPath)
			throws FileNotFoundException, IOException {
		final PDFParser pdfParser = new PDFParser(new FileInputStream(new File(
				pdfPath)));
		String text = "";
		try {
			pdfParser.parse();
			final PDFTextStripper pdfStripper = new PDFTextStripper("utf-8");
			final PDDocument doc = pdfParser.getPDDocument();
			text = pdfStripper.getText(doc);
			TextExtractor.logger.info(text);
		} finally {
			pdfParser.clearResources();
		}
		return text;
	}

	/*
	 * Pass the path of the PDF file in the argument.
	 */
	public static void main(final String[] args) throws FileNotFoundException,
			IOException {
		System.out.println(TextExtractor.getTextFromPDF(args[0]));
	}

}
