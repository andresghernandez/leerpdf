package com.readpdf.userinterfaces;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.targets.Target;

public class DownloadPdfUserInterface {

	public static final Target BUTTON_DOWNLOAD_PDF = Target.the("button download pdf").
			located(By.xpath("//div[@id='home']//a[@class='downloadPDFUrl']"));
	
}
