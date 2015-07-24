package com.pdfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdfparser.PDFParser;
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
	 * Pass the path of the PDF file in the argument.
	 */
	public static void main(final String[] args) throws FileNotFoundException,
			IOException {
		final PDFParser pdfParser = new PDFParser(new FileInputStream(new File(
				args[0])));
		try {
			pdfParser.parse();
			final PDFTextStripper pdfStripper = new PDFTextStripper("utf-8");
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(1);
			final String text = pdfStripper.getText(pdfParser.getPDDocument());
			TextExtractor.logger.info(text);
		} finally {
			pdfParser.clearResources();
		}
	}

}
