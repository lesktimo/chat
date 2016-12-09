package ui;

import org.fluentlenium.adapter.FluentTest;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        baseUrl = "http://localhost:8080/" + port;
    }
//
//    @After
//    public void tearDown() {
//    }

    @Test
    public void canAddGroup() {
        goTo(baseUrl);
        $("a", withText("Groups")).click();
        assertFalse(pageSource().contains("Ryhmä1"));

        fill(find("#group")).with("Ryhmä1");
        submit(find("form").first());

        assertTrue(pageSource().contains("Ryhmä1"));
    }

    @Test
    public void canAddMessageToGroup() {
        
        String message  = "Hello World!";
        
        goTo(baseUrl);
        $("a", withText("Groups")).click();
        fill(find("#group")).with("Ryhmä1");
        submit(find("form").first());
        
        $("a", withText("Ryhmä1")).click();
        assertFalse(pageSource().contains(message));
        
        fill(find("#message")).with(message);
        submit(find("form").first());
        
        assertTrue(pageSource().contains(message));
    }
}
