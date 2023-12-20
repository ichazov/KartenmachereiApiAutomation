package fragments;

import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent extends AbstractFragment {

    private static final By ROOT = By.id("header");

    private static final By ACCOUNT_ICON = By.cssSelector(".only-desktop-header-view.account");

    public HeaderComponent() {
        setRootElement(ROOT);
    }

    public void clickAccountIcon() {
        clickButton(ACCOUNT_ICON);
        getInteractableElement(By.id("login-modal"));
    }

    public boolean isUserLoggedIn() {
        return !$(By.cssSelector(".only-desktop-header-view.is-not-logged-in")).isDisplayed();
    }
}
