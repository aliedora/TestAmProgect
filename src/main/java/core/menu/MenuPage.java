package core.menu;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MenuPage extends BasePage {

    private final By menuContainer = By.xpath(".//*[contains(@class,'menu-container')]");
    private final By menuButton = By.xpath(".//*[@id='submenu']");

    public MenuPage() {
        super();
        $(menuContainer).should(be(visible).because("Not loaded main menu component"));
    }


    @Step("Get all menu buttons")
    public ElementsCollection getMenuButtons() {
        return $(menuContainer).$$(menuButton);
    }

    @Step("Get text of all menu buttons")
    public String[] getMenuButtonTexts() {
        return getMenuButtons()
                .shouldBe(sizeGreaterThan(0).because("No menu buttons found"))
                .stream()
                .map(SelenideElement::getText)
                .toArray(String[]::new);
    }
}
