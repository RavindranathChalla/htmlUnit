package com.github.emalock3.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setRedirectEnabled(false);
        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setPopupBlockerEnabled(true);
        webClient.getOptions().setTimeout(10000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage("http://www.google.com");
        //System.out.println(page.asXml());
        System.out.println("Title-->"+page.getTitleText());
        webClient.closeAllWindows();
    }
}
