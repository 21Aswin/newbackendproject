package jobportal.demo.Service;

import jobportal.demo.Entity.Users;
import jobportal.demo.Repository.UserRepo;
import jobportal.demo.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Not Found"));

        return new CustomUserDetails(user);
    }
}
