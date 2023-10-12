package tests.data;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "logInCredentialsDataProvider")
    public static Object[][] logInCredentialsDataProvider() {
        return new Object[][]{
                {
                        "koentest@test.com",
                        "Test12345!",
                        true
                },
                {
                        "blaat@1337.com",
                        "TESTESTESTESTESTESTESTESTESTESTESTEST",
                        false
                }
        };
    }
}