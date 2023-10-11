package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HeaderNavigationBar extends BasePage {
    Page page;
    Locator goToHomeButtonLocator;
    Locator signInButtonLocator;
    Locator signUpButtonLocator;
    Locator signedInNewArticleButtonLocator;
    Locator signedInSettingsButtonLocator;
    Locator signedInUserButtonLocator;

    public HeaderNavigationBar(Page page) {
        this.page = page;
        this.goToHomeButtonLocator = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Home"));
        this.signInButtonLocator = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Sign in"));
        this.signUpButtonLocator = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Sign up"));
        this.signedInNewArticleButtonLocator = page.getByRole(
                AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions().setHasText("New Article"));
        this.signedInSettingsButtonLocator = page.getByRole(
                AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions().setHasText("Settings"));
        this.signedInUserButtonLocator = page.locator("//a[contains(@href, '/profile/')]");
    }

    public void goToHome() {
        goToHomeButtonLocator.click();
    }

    public void goToSignIn() {
        signInButtonLocator.click();
    }

    public void goToSignUp() {
        signUpButtonLocator.click();
    }

    public void goToNewArtictle() {
        signedInNewArticleButtonLocator.click();
    }

    public void goToSignedInUser() {
        signedInUserButtonLocator.click();
    }

    public Locator getHomeButtonLocator() {
        return goToHomeButtonLocator;
    }

    public Locator getSignInButtonLocator() {
        return signInButtonLocator;
    }

    public Locator getSignUpButtonLocator() {
        return signUpButtonLocator;
    }

    public Locator getSignedInNewArticleButtonLocator() {
        return signedInNewArticleButtonLocator;
    }

    public Locator getSignedInSettingsButtonLocator() {
        return signedInSettingsButtonLocator;
    }

    public Locator getSignedInUserButtonLocator() {
        return signedInUserButtonLocator;
    }

}
