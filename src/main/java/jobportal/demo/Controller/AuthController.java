package jobportal.demo.Controller;

import jobportal.demo.DTO.LoginDto;
import jobportal.demo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginDto dto){
        String token = authService.login(dto);

        return ResponseEntity.ok(token);
    }
}
