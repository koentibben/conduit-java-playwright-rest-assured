package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignInPage extends BasePage {
    Page page;
    Locator emailInput;
    Locator passwordInput;
    Locator signInButton;

    public SignInPage(Page page) {
        this.page = page;
        this.emailInput = page.getByPlaceholder("Email");
        this.passwordInput = page.getByPlaceholder("Password");
        this.signInButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Sign in"));
    }

    public void navigate() {
        page.navigate("https://angular.realworld.io/");
        page.getByText("Sign in").click();
    }

    public void fillInEmail(String email) {
        emailInput.fill(email);
    }

    public void fillInPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public Locator getEmailInput() {
        return emailInput;
    }

    public Locator getPasswordInput() {
        return passwordInput;
    }

    public Locator getSignInButton() {
        return signInButton;
    }
}
