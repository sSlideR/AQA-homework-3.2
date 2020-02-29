package LoginProccess.DBElements;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodes {
    private String id;
    private String user_id;
    private String code;
    private String created;
}
