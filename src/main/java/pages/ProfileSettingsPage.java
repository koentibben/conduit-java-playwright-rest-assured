package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProfileSettingsPage extends BasePage {
    Page page;
    Locator logOutButton;

    public ProfileSettingsPage(Page page) {
        this.page = page;
        this.logOutButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Or click here to logout."));
    }

    public Locator getLogOutButton() {
        return logOutButton;
    }
}
