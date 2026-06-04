package jobportal.demo.Repository;

import jobportal.demo.Entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<Resume,Long>{


    Optional<Resume> findByUserId(Long aLong);
}
