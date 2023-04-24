package com.readpdf.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import com.readpdf.drivers.Driver;

public class OpenPage implements Task {

	private String environment;
	private String url;
	
	public OpenPage(String environment, String url) {
		this.environment = environment;
		this.url = url;
	}
	
	@Override
	public <T extends Actor> void performAs(T actor) {
		String urlEnvironment = url;
		actor.whoCan(BrowseTheWeb.with(Driver.inThePage(urlEnvironment)));
}

	public static OpenPage web(String environment, String url) {
		return Tasks.instrumented(OpenPage.class, environment, url);
	}
	
}