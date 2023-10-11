package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignUpPage extends BasePage {
    Page page;
    Locator userNameInput;
    Locator emailInput;
    Locator passwordInput;
    Locator signUpButton;

    public SignUpPage(Page page) {
        this.page = page;
        this.userNameInput = page.getByPlaceholder("Username");
        this.emailInput = page.getByPlaceholder("Email");
        this.passwordInput = page.getByPlaceholder("Password");
        this.signUpButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Sign up"));
    }

    public void navigate() {
        page.navigate("https://angular.realworld.io/");
        page.getByText("Sign up").click();
    }

    public void fillInUsername(String userName) {
        userNameInput.fill(userName);
    }

    public void fillInEmail(String email) {
        emailInput.fill(email);
    }

    public void fillInPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickSignUp() {
        signUpButton.click();
    }

    public Locator getUserNameInput() {
        return userNameInput;
    }

    public Locator getEmailInput() {
        return emailInput;
    }

    public Locator getPasswordInput() {
        return passwordInput;
    }

    public Locator getSignUpButton() {
        return signUpButton;
    }
}