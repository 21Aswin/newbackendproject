package jobportal.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        @Column(unique = true)
        private String username;

        private String password;

    @OneToOne(mappedBy = "user")
    private Photos photo;

    @OneToOne(mappedBy = "user")
    private Resume resume;

    }
