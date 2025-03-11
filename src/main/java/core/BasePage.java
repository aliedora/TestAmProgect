package core;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;

public abstract class BasePage {

    public BasePage() {
    }

    public void waitForPageLoad(String uniqueElementSelector) {
        Selenide.$(uniqueElementSelector).shouldBe(visible);
    }
}