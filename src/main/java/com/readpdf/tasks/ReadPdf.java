package com.readpdf.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import java.net.URL;
import static com.readpdf.userinterfaces.DownloadPdfUserInterface.*;
import java.io.InputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.BufferedInputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadPdf implements Task {

	@Override
	public <T extends Actor> void performAs(T actor) {
		BrowseTheWeb.as(actor).getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		String urlPdf = BUTTON_DOWNLOAD_PDF.resolveFor(actor).getAttribute("href");

		String pdfContent = "";
		try {
			pdfContent = readPdfContent(urlPdf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean containsText = pdfContent.contains("19320968");
		System.out.println("Contiene 19320968 : "+containsText);
		System.out.println(pdfContent);
		
		actor.attemptsTo(Click.on(BUTTON_DOWNLOAD_PDF));
		BrowseTheWeb.as(actor).getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		waitSelenium(10);
		//BrowseTheWeb.as(actor).getDriver().get(urlPdf);
		
		//BrowseTheWeb.as(actor).getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		//waitSelenium(10);
		
	}

	public static  String readPdfContent(String url) throws IOException {
		
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		int numberOfPages = getPageCount(doc);
		System.out.println("The total number of pages "+numberOfPages);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
		return content;
	}
	
	public static int getPageCount(PDDocument doc) {
		int pageCount = doc.getNumberOfPages();
		return pageCount;
		
	}

	public void waitSelenium(int seconds) {
		try {
			Thread.sleep(seconds * (long) 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static ReadPdf download() {
		return Tasks.instrumented(ReadPdf.class);
	}
}
