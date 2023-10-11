package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    Page page;

    public BasePage() {
    }

    public void locatorShouldBeVisible(Locator locator) throws Exception {
        if (!locator.isVisible()) {
            throw new Exception(
                    locator + " should BE visible, but IS NOT!"
            );
        }
    }

    public void locatorShouldNotBeVisible(Locator locator) throws Exception {
        if (locator.isVisible()) {
            throw new Exception(
                    locator + " should NOT BE visible, but IS!"
            );
        }
    }

    public void clearInputField(Locator locator) {
        locator.fill("", new Locator.FillOptions()
                .setForce(true));
    }
}
