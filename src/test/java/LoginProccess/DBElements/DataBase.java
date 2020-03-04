package LoginProccess.DBElements;

import LoginProccess.DataHelper;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

@Value
public class DataBase {
    private String dBUrl;
    private String dBUser;
    private String dBUserPass;

    public static DataBase getDbAuthInfo() {
        return new DataBase("jdbc:mysql://localhost:3306/app", "superUser", "ff243fdsfdsf");
    }

    public static String getVerificationCodeFromDB(DataHelper.AuthInfo authInfo) throws SQLException {
        val dataBase = getDbAuthInfo();
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(dataBase.getDBUrl(), dataBase.getDBUser(), dataBase.getDBUserPass());
        ) {

            val userIdSQL = String.format("SELECT * FROM users WHERE login = \"%s\"", authInfo.getLogin());
            val userId = runner.query(conn, userIdSQL, new BeanHandler<>(User.class)).getId();

            val verificationCode = String.format("SELECT * FROM auth_codes WHERE user_id = \"%s\" ORDER BY created DESC", userId);
            val userVerificationCode = runner.query(conn, verificationCode, new BeanHandler<>(AuthCodes.class)).getCode();

            return String.valueOf(userVerificationCode);
        }
    }

    public static void clearDbData(String dBToClear) throws SQLException {
        val dataBase = getDbAuthInfo();

        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(dataBase.getDBUrl(), dataBase.getDBUser(), dataBase.getDBUserPass());
        ) {
            for (String dBName : dBToClear.split(",")) {
                val clearDataScript = String.format("DELETE FROM %s;", dBName);
                runner.update(conn, clearDataScript);
            }
        }
    }
}
