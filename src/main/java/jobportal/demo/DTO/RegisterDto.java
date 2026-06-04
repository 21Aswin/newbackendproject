package jobportal.demo.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterDto {

    private String name;
    private Integer age;
    private String email;
    private String department;
    private Integer experience;
    private Double income;
    private Double savings;
    private String phone;
    private String address;
    private String username;
    private String password;

    private MultipartFile photo;
    private MultipartFile Resume;
}
