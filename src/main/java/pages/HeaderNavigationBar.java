package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.ArrayList;
import java.util.List;

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

    public void verifyLocatorsThatShouldAndShouldNotBeVisibleAfterSuccessfulSignUpOrSignIn(HeaderNavigationBar page) throws Exception {
        List<Locator> locatorsThatShouldNotBeVisibleAfterSuccessfulSignUp = new ArrayList<>();
        locatorsThatShouldNotBeVisibleAfterSuccessfulSignUp.add(page.getSignInButtonLocator());
        locatorsThatShouldNotBeVisibleAfterSuccessfulSignUp.add(page.getSignUpButtonLocator());

        List<Locator> locatorsThatShouldBeVisibleAfterSuccessfulSignUp = new ArrayList<>();
        locatorsThatShouldBeVisibleAfterSuccessfulSignUp.add(page.getHomeButtonLocator());
        locatorsThatShouldBeVisibleAfterSuccessfulSignUp.add(page.getSignedInNewArticleButtonLocator());
        locatorsThatShouldBeVisibleAfterSuccessfulSignUp.add(page.getSignedInSettingsButtonLocator());
        locatorsThatShouldBeVisibleAfterSuccessfulSignUp.add(page.getSignedInUserButtonLocator());

        for (Locator locatorThatShouldNotBeVisibleAfterSuccessfulSignUp : locatorsThatShouldNotBeVisibleAfterSuccessfulSignUp) {
            page.locatorShouldNotBeVisible(locatorThatShouldNotBeVisibleAfterSuccessfulSignUp);
        }

        for (Locator locatorThatShouldBeVisibleAfterSuccessfulSignUp : locatorsThatShouldBeVisibleAfterSuccessfulSignUp) {
            page.locatorShouldBeVisible(locatorThatShouldBeVisibleAfterSuccessfulSignUp);
        }
    }

}
