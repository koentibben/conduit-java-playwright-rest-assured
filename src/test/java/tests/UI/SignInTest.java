package tests.UI;

import com.microsoft.playwright.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderNavigationBar;
import pages.ProfilePage;
import pages.ProfileSettingsPage;
import pages.SignInPage;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignInTest {
    SignInPage signInPage;
    HeaderNavigationBar headerNavigationBar;
    ProfilePage profilePage;
    ProfileSettingsPage profileSettingsPage;
    Playwright playwright = Playwright.create();
    BrowserType chromium = playwright.chromium();
    Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));
    BrowserContext context = browser.newContext();
    Page page = context.newPage();

    @BeforeTest
    public void setUp() {
        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        page.navigate("https://angular.realworld.io/");
        signInPage = new SignInPage(page);
        headerNavigationBar = new HeaderNavigationBar(page);
        profilePage = new ProfilePage(page);
        profileSettingsPage = new ProfileSettingsPage(page);
        signInPage.navigate();
    }

    @AfterTest
    public void tearDown() {
        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        browser.close();
        page.close();
        playwright.close();
    }

    @DataProvider(name = "logInCredentialsCsv")
    public static Object[][] readCsv() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/src/main/resources/logInCredentials.csv"));
        List<String[]> csvData = csvReader.readAll();
        // remove column header names from .csv values
        csvData.remove(0);
        Object[][] csvDataObject = new Object[csvData.size()][];
        for (int i = 0; i < csvData.size(); i++) {
            csvDataObject[i] = csvData.get(i);
        }
        return csvDataObject;
    }

    @Test(dataProvider = "logInCredentialsCsv")
    public void signInHappyFlowCsv(String email, String password, String workingCredentials) throws Exception {
        signInPage.fillInEmail(email);
        signInPage.fillInPassword(password);
        signInPage.clickSignIn();
        TimeUnit.SECONDS.sleep(1);
        if (Objects.equals(workingCredentials, "true")) {
            headerNavigationBar.verifyLocatorsThatShouldAndShouldNotBeVisibleAfterSuccessfulSignUpOrSignIn(headerNavigationBar);
        }
        headerNavigationBar.signOutUserIfPossible(headerNavigationBar, profilePage, profileSettingsPage);
        signInPage.navigate();
    }

    @DataProvider(name = "logInCredentialsJson")
    String[] readJson() throws IOException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/logInCredentials.json");
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get("userLogins");

        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String username = (String) users.get("username");
            String password = (String) users.get("password");
            String workingCredentials = (String) users.get("workingCredentials");
            arr[i] = username + "," + password + "," + workingCredentials;
        }
        return arr;
    }

    @Test(dataProvider = "logInCredentialsJson")
    public void signInHappyFlowJson(String data) throws Exception {
        String[] user = data.split(",");
        signInPage.fillInEmail(user[0]);
        signInPage.fillInPassword(user[1]);
        signInPage.clickSignIn();
        TimeUnit.SECONDS.sleep(1);
        if (Objects.equals(user[2], "true")) {
            headerNavigationBar.verifyLocatorsThatShouldAndShouldNotBeVisibleAfterSuccessfulSignUpOrSignIn(headerNavigationBar);
        }
        headerNavigationBar.signOutUserIfPossible(headerNavigationBar, profilePage, profileSettingsPage);
        signInPage.navigate();
    }
}
