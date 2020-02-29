package LoginProccess;

import LoginProccess.DBElements.AuthCodes;
import LoginProccess.DBElements.User;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

        public static AuthInfo getAuthInfo() {
            return new AuthInfo("vasya", "qwerty123");
        }
    }

    @Value
    public static class VerificationCode {
        private String code;

        public static VerificationCode getVerificationCodeFore(AuthInfo authInfo) {

            return new VerificationCode("12345");
        }

        public static String getVerificationCodeFromDB(AuthInfo authInfo) throws SQLException {
            val runner = new QueryRunner();
            try (
                    val conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/app", "superUser", "ff243fdsfdsf"
                    );
            ) {

                val userIdSQL = String.format("SELECT * FROM users WHERE login = \"%s\"", authInfo.getLogin());
                val userId = runner.query(conn, userIdSQL, new BeanHandler<>(User.class)).getId();

                val verificationCode = String.format("SELECT * FROM auth_codes WHERE user_id = \"%s\" ORDER BY created DESC", userId);
                val userVerificationCode = runner.query(conn, verificationCode, new BeanHandler<>(AuthCodes.class)).getCode();

                return String.valueOf(userVerificationCode);
            }
        }

    }
}