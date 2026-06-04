package jobportal.demo.Service;


import jobportal.demo.Entity.PasswordResetOtp;
import jobportal.demo.Entity.Users;
import jobportal.demo.Repository.PasswordRepo;
import jobportal.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ForgetPasswordSerivce {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordRepo passwordRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;


    public void sentOtp(String email){
        Users user =userRepo.findByEmail(email).orElseThrow(()
                -> new RuntimeException("User Email Not Found"));
        String otp = String.valueOf((int)(Math.random()*900000)+100000);
        passwordRepo.deleteByEmail(email);
        PasswordResetOtp passwordResetOtp = new PasswordResetOtp();
        passwordResetOtp.setEmail(email);
        passwordResetOtp.setOtp(otp);
        passwordResetOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        passwordRepo.save(passwordResetOtp);
        emailService.sendOtp(email,otp);
    }

    public boolean verifyOtp(String email, String otp){
        PasswordResetOtp passwordResetOtp = passwordRepo.findByEmail(email).orElseThrow(()
                -> new RuntimeException("User Email Not Found"));

        if(passwordResetOtp.getExpiryTime().isBefore(LocalDateTime.now())){
            return false;
        }
        return passwordResetOtp.getOtp().equals(otp);
    }
    @Transactional
    public void resetPassword(String email, String password){

        if(password == null || password.isEmpty()){
            throw new RuntimeException("Password cannot be empty");
        }

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Email Not Found"));

        user.setPassword(passwordEncoder.encode(password));

        userRepo.saveAndFlush(user);

        passwordRepo.deleteByEmail(email);
    }

}
