package jobportal.demo.DTO;

import lombok.Data;

@Data
public class ResponceDto{
    private Long id;
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

    private String photoPath;
    private String resumePath;
}
