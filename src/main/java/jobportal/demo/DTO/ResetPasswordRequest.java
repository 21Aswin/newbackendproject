package jobportal.demo.DTO;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String email;
    private String newPassword;

}
