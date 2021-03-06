package com.github.emalock3.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Main {
    public static void main(String... args) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
       // JavaScriptEngine engine = webClient.getJavaScriptEngine();
        //engine.holdPosponedActions();
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
        certificate();
        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        Iterable<DomElement> childEle;
        List<DomElement> divs = page.getElementsByTagName("div");
         for(DomElement element : divs){
             if(element.getAttribute("class").equals("well sidebar-nav")){
             childEle=element.getChildElements();
             for(DomElement ce: childEle){
                System.out.println(ce);
                  Iterable<DomElement> de=ce.getChildElements();
                  for(DomElement d:de){
                             if(d.getByXPath("//a").contains("Project Page")){
                  System.out.println(d.toString());
                  System.out.println( d.getByXPath("//a"));
                             }
                      }
             }
             }
         }

        //HtmlPage page = webClient.getPage("https://wuapp-prodrs.westernunion.net/RMP/jsp/update1.jsp");
        HtmlPage page = webClient.getPage("https://www.google.com/");
        //System.out.println(page.asXml());
       // final HtmlForm form = page.getFormByName("frm");
        final HtmlAnchor button = (HtmlAnchor) page.getElementById("gb_70");
        //final HtmlSubmitInput button = form.getInputByName("Commit");
        //final HtmlTextInput textField = form.getInputByName("sqlstring");

        System.out.println("Title-->"+page.getTitleText());
        
		System.out.println("button--"+button.asText());
		
        // Change the value of the text field
        //textField.type("root");
        
       // System.out.println("textField--"+textField);

        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
        final HtmlTextInput textField = page2.getElementByName("identifier");
        textField.type("ravinathchalla@gmail.com");
        System.out.println("textField--"+textField.getValueAttribute());
       
        webClient.closeAllWindows();
    }
    
    
    private void certificate() {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }

          public void checkClientTrusted(X509Certificate[] certs, String authType) {}

          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }};
        try {


          SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
          sslContext.init(null, trustAllCerts, new SecureRandom());
          HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

          HostnameVerifier allHostsValid = new HostnameVerifier() {

            public boolean verify(String hostname, SSLSession session) {
              return true;
            }

          };
          HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (

        Exception e) {
          // logger.error("Exception in openshift certificate :"+e.getMessage());

        }
      }
}
