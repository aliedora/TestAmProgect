package core.menu;

import core.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CookiesDialog extends BasePage {
    private final By container = By.xpath(".//*[@id = 'onetrust-group-container']");
    private final By btnAccept = By.xpath(".//*[@id = 'onetrust-accept-btn-handler']");

    public CookiesDialog() {
        super();
    }

    public void closeDialog(){
        if ($(container).is(visible)){
            $(btnAccept).should(be(visible)).click();
        }
        $(container).shouldNot(be(visible).because("Cookies dialog still exists"));
    }
}
