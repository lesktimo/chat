package wepa.app.ui;

import org.fluentlenium.adapter.FluentTest;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ChatTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();
    private String baseUrl;

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @LocalServerPort
    private Integer port;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void testLogin() {
        goTo(baseUrl + "/login");
        fill(find("#username")).with("testi");
        fill(find("#password")).with("testi");
        $("button", withText("Kirjaudu sisään")).click();

        // uudelleenohjauksen tarkistus
        //assertTrue(pageSource().contains(""));
    }

    @Test
    public void cantLoginWithMissingInformation() {
        goTo(baseUrl + "/login");
        fill(find("#username")).with("testi");
        $("button", withText("Kirjaudu sisään")).click();

        // virheviestin tarkistus
        //assertTrue(pageSource().contains(""));
        fill(find("#password")).with("testi");
        $("button", withText("Kirjaudu sisään")).click();

        // virheviestin tarkistus
        //assertTrue(pageSource().contains(""));
    }

    @Test
    public void testRegistration() {
        goTo(baseUrl + "/reg");
        fill(find("#username")).with("user1");
        fill(find("#password")).with("pass");
        submit(find("form").first());

        assertTrue(pageSource().contains("Kirjaudu sisään"));
    }

    @Test
    public void cantRegisterWithMissingInformation() {
        goTo(baseUrl + "/login");
        fill(find("#username")).with("user1");
        submit(find("form").first());

        // virheviestin tarkistus
        //assertTrue(pageSource().contains(""));
        fill(find("#password")).with("pass");
        submit(find("form").first());

        // virheviestin tarkistus
        //assertTrue(pageSource().contains(""));
    }

    @Test
    public void canAddGroup() {
        login();

        goTo(baseUrl + "/groups");
        assertTrue(pageSource().contains("Kaikki chatit"));
        assertFalse(pageSource().contains("Ryhmä1"));

        fill(find("#topic")).with("Ryhmä1");
        submit(find("form").first());

        assertTrue(pageSource().contains("Ryhmä1"));
    }

//    @Test
//    public void canAddMessageToGroup() {
//        login();
//        addGroup();
//        $("a", withText("Ryhmä1")).click();
//        
//        String message = "Hello World!";
//
//        assertFalse(pageSource().contains(message));
//
//        fill(find("#message")).with(message);
//        $("button", withText("Send!")).click();
//
//        assertTrue(pageSource().contains(message));
//    }

    private void login() {
        goTo(baseUrl + "/login");
        fill(find("#username")).with("testi");
        fill(find("#password")).with("testi");
        $("button", withText("Kirjaudu sisään")).click();
    }

    private void addGroup() {
        goTo(baseUrl + "/groups");
        fill(find("#topic")).with("Ryhmä1");
        submit(find("form").first());
    }
}
