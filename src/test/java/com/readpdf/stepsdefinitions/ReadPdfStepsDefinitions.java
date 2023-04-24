package com.readpdf.stepsdefinitions;

import org.openqa.selenium.WebDriver;
import com.readpdf.drivers.Driver;
import com.readpdf.tasks.OpenPage;
import com.readpdf.tasks.ReadPdf;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;

public class ReadPdfStepsDefinitions {

	private Actor actor = Actor.named("the");
	
	@Managed
	private WebDriver browser;
	
	@Before
	public void setTheStage(){
	    OnStage.setTheStage(new OnlineCast());
	}
	
	@Given("^Park ingresa a la pagina del pdf$")
	public void parkIngresaALaPaginaDelPdf() {
		Driver.web();
		actor.can(BrowseTheWeb.with(browser));
		actor.wasAbleTo(OpenPage.web("", "https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=c520ca29414e579692cf1e9d828523ccc18dd93ec2db6e4428edec47f9cb9ee30df4403241a86e6fc23789ea6d01b3a1"));
	}

	@When("^el descarga el pdf$")
	public void elDescargaElPdf() {
		actor.wasAbleTo(ReadPdf.download());
	}

	@Then("^el leer la informacion del pdf$")
	public void elLeerLaInformacionDelPdf() {

	}
	
	
}
