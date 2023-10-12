package tests.UI;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HeaderNavigationBar;
import pages.SignUpPage;

import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignUpUITest {
    SignUpPage signUpPage;
    HeaderNavigationBar headerNavigationBar;
    Playwright playwright = Playwright.create();
    BrowserType chromium = playwright.chromium();
    Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));
    BrowserContext context = browser.newContext();
    Page page = context.newPage();
    Faker faker = new Faker();
    Random ran = new Random();

    @BeforeTest
    public void setUp() {
        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        page.navigate("https://angular.realworld.io/");
        signUpPage = new SignUpPage(page);
        headerNavigationBar = new HeaderNavigationBar(page);
        signUpPage.navigate();
    }

    @AfterTest
    public void tearDown() {
        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

    @Test
    public void signUpHappyFlow() throws Exception {
        signUpPage.fillInUsername(faker.name().firstName().toLowerCase() + ran.nextInt(6) + 5);
        signUpPage.fillInEmail(faker.name().firstName().toLowerCase() + faker.name().lastName().toLowerCase() + ran.nextInt(10) + 3);
        signUpPage.fillInPassword(faker.internet().password());
        signUpPage.clickSignUp();
        TimeUnit.SECONDS.sleep(1);
        headerNavigationBar.verifyLocatorsThatShouldAndShouldNotBeVisibleAfterSuccessfulSignUpOrSignIn(headerNavigationBar);
    }

    @Test
    public void checkSignUpRequiredFieldUsername() {
        signUpPage.fillInEmail(faker.name().firstName().toLowerCase() + faker.name().lastName().toLowerCase() + ran.nextInt(10) + 3);
        signUpPage.fillInPassword(faker.internet().password());
        assertThat(signUpPage.getSignUpButton()).isDisabled();
    }

    @Test
    public void checkSignUpRequiredFieldEmail() {
        signUpPage.fillInUsername(faker.name().firstName().toLowerCase() + ran.nextInt(6) + 5);
        signUpPage.fillInPassword(faker.internet().password());
        assertThat(signUpPage.getSignUpButton()).isDisabled();
    }

    @Test
    public void checkSignUpRequiredFieldPassword() {
        signUpPage.fillInUsername(faker.name().firstName().toLowerCase() + ran.nextInt(6) + 5);
        signUpPage.fillInEmail(faker.name().firstName().toLowerCase() + faker.name().lastName().toLowerCase() + ran.nextInt(10) + 3);
        assertThat(signUpPage.getSignUpButton()).isDisabled();
    }
}