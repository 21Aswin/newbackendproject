package jobportal.demo.Controller;

import jobportal.demo.DTO.ForgotPasswordRequest;
import jobportal.demo.DTO.ResetPasswordRequest;
import jobportal.demo.DTO.VerifyOtpRequest;
import jobportal.demo.Service.EmailService;
import jobportal.demo.Service.ForgetPasswordSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
@CrossOrigin("*")
public class ForgetPasswordController{

    @Autowired
   private ForgetPasswordSerivce passwordSerivce;

    @Autowired
    private EmailService emailService;


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequest req){

        passwordSerivce.sentOtp(req.getEmail());

        return ResponseEntity.ok(
                "OTP Sent Successfully");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(
            @RequestBody VerifyOtpRequest req){

        boolean verified =
                passwordSerivce.verifyOtp(
                        req.getEmail(),
                        req.getOtp());

        if(verified){
            return ResponseEntity.ok("OTP Verified");
        }

        return ResponseEntity.badRequest()
                .body("Invalid OTP");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest req){

        passwordSerivce.resetPassword(
                req.getEmail(),
                req.getNewPassword());

        return ResponseEntity.ok(
                "Password Reset Successful");
    }


}
