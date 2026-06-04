package jobportal.demo.Repository;

import jobportal.demo.Entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepo extends JpaRepository<Photos,Long> {


    Optional<Photos> findByUserId(Long id);
}
