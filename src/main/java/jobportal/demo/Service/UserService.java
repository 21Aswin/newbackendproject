package jobportal.demo.Service;

import jobportal.demo.DTO.RegisterDto;
import jobportal.demo.DTO.ResponceDto;
import jobportal.demo.DTO.UpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    String registerUser(RegisterDto dto)throws Exception;

    List<ResponceDto> getAllUsers();

    ResponceDto getUserById(Long id);

     List<ResponceDto> searchByUsers(Integer experience,Double income,String department);


    String updateUser(Long id, UpdateDto dto);

    String deleteUser(Long id);
}
