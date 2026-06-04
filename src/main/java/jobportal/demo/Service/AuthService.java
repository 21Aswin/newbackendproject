package jobportal.demo.Service;

import jobportal.demo.DTO.LoginDto;
import jobportal.demo.Entity.Users;
import jobportal.demo.Repository.UserRepo;
import jobportal.demo.Security.JwtUtil;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil = new JwtUtil();

    public String login(LoginDto dto) {
        Users user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        boolean match = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!match) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
