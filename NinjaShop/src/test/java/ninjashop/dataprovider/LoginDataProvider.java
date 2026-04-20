package ninjashop.dataprovider;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][] {
            {"invaliduser@gmail.com", "demo123", "Warning: No match for E-Mail Address and/or Password."},
            {"", "", "Warning: No match for E-Mail Address and/or Password."}
        };
    }
}