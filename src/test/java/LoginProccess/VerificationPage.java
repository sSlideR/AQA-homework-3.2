package LoginProccess;

import LoginProccess.DBElements.DataBase;
import com.codeborne.selenide.SelenideElement;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement verificationCodeField = $("input[name='code'");
    private SelenideElement moveOnButton = $("button[data-test-id='action-verify']");

    public UserPage validVerificationCode(DataHelper.AuthInfo info) throws SQLException {
        verificationCodeField.setValue(DataBase.getVerificationCodeFromDB(info));
        moveOnButton.click();
        return new UserPage();
    }

}
