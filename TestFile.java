@Test
public void homePage_proxy() throws Exception {
    try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "myproxyserver", myProxyPort)) {

        //set proxy username and password 
        final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
        credentialsProvider.addCredentials("username", "password");

        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
    }
}