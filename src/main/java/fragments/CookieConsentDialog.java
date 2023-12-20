package fragments;

import org.openqa.selenium.*;

public class CookieConsentDialog extends AbstractFragment {

    private static final By ROOT = By.className("ot-sdk-container");

    private static final By ACCEPT_NECESSARY_BUTTON = By.id("onetrust-reject-all-handler");

    public CookieConsentDialog() {
        setRootElement(ROOT);
    }

    public void clickAcceptNecessaryButton() {
        clickButton(ACCEPT_NECESSARY_BUTTON);
    }
}
