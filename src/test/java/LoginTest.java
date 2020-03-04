import LoginProccess.DataHelper;
import LoginProccess.LoginPage;
import lombok.val;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static LoginProccess.DBElements.DataBase.*;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    void openBeforeTest() {
        open("http://localhost:9999/");
    }

    @Test
    void successfulLogin() throws SQLException {
        val authInfo = DataHelper.AuthInfo.getAuthInfo();
        val verificationPage = new LoginPage().validLogin(authInfo);
        val userPage = verificationPage.validVerificationCode(authInfo);
        userPage.verifySuccessfulLogin();
    }

    @AfterAll
    static void clearDbTables() throws SQLException{
        clearDbData("auth_codes,card_transactions,cards,users");
    }
}
