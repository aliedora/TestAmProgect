package uitests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import core.menu.CookiesDialog;
import core.menu.MenuPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Testing")
@Feature("Menu Button Verification")
public class MenuTest {
    private MenuPage menuPage;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
    }

    @BeforeEach
    void openMenuPage() {
        open("https://www.amdocs.com/");
        new CookiesDialog().closeDialog();
        menuPage = new MenuPage();
    }

    @Test
    @Story("Verify menu button texts")
    @Severity(SeverityLevel.NORMAL)
    @Description("Check that menu buttons have correct text")
    void testMenuButtonTexts() {
        String[] expectedTexts = {"About", "Topics", "Products & Services", "Insights"};

        for (int i = 0; i < expectedTexts.length; i++) {
            assertEquals(expectedTexts[i], menuPage.getMenuButtons().get(i).getText(),
                    "Menu button text does not match for index " + i);

        }
        Allure.addAttachment("Screenshot", "image/png",
                new ByteArrayInputStream(Objects.requireNonNull(Selenide.screenshot(OutputType.BYTES))), "png");    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
