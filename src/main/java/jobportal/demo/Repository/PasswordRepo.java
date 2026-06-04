package jobportal.demo.Repository;


import jobportal.demo.Entity.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PasswordRepo  extends JpaRepository<PasswordResetOtp, Long> {

    Optional<PasswordResetOtp> findByEmail(String email);

     @Transactional
    void deleteByEmail(String email);
}
