package fragments;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.time.*;

import static com.codeborne.selenide.Selenide.$;

public class AbstractFragment {

    private By rootElement;

    protected void setRootElement(By locator) {
        this.rootElement = locator;
    }

    protected By getRootElement() {
        return rootElement;
    }

    protected SelenideElement getInteractableElement(By locator) {
        return $(getRootElement()).$(locator).shouldBe(Condition.interactable, Duration.ofSeconds(10));
    }

    protected void clickButton(By locator) {
        getInteractableElement(locator).click();
    }
}
