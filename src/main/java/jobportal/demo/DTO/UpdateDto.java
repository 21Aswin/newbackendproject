package jobportal.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDto {
    private String name;

    private Integer age;

    private String email;

    private String department;

    private Integer experience;

    private Double income;

    private Double savings;

    private String phone;

    private String address;
}
