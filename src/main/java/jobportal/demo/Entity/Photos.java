package jobportal.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "photos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photos{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoName;

    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
