package jobportal.demo.DTO;

import lombok.Data;

@Data
public class VerifyOtpRequest {


    private String email;
    private String otp;

}
