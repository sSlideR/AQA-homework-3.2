package LoginProccess.DBElements;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String login;
    private String password;
    private String status;
}
