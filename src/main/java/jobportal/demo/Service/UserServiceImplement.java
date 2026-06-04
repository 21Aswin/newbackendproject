package jobportal.demo.Service;

import jobportal.demo.DTO.RegisterDto;
import jobportal.demo.DTO.ResponceDto;
import jobportal.demo.DTO.UpdateDto;
import jobportal.demo.Entity.Photos;
import jobportal.demo.Entity.Resume;
import jobportal.demo.Entity.Users;
import jobportal.demo.Repository.FileRepo;
import jobportal.demo.Repository.PhotoRepo;
import jobportal.demo.Repository.UserRepo;
import jobportal.demo.Specfication.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UserServiceImplement implements UserService{
    @Override
    public String updateUser(Long id, UpdateDto dto) {
        Users user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Id Not Found"));
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setDepartment(
                dto.getDepartment());

        user.setExperience(
                dto.getExperience());

        user.setIncome(
                dto.getIncome());

        user.setSavings(
                dto.getSavings());

        user.setPhone(
                dto.getPhone());

        user.setAddress(
                dto.getAddress());

        userRepo.save(user);
        return "Update User ";

    }

    @Override
    public String deleteUser(Long id) {
        Users user = userRepo.findById(id).orElseThrow(()->
                new RuntimeException("User ID not Found"));
        userRepo.delete(user);
        return "Delete user id"+id;
    }

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PhotoRepo photoRepo;
    @Autowired
    private FileRepo fileRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<ResponceDto> getAllUsers() {
        List<Users> user = userRepo.findAll();
        return
                user.stream().map(this::ConverDto).toList();
    }

    @Override
    public ResponceDto getUserById(Long id) {
        Users user = userRepo.findById(id).orElseThrow(() -> new
                RuntimeException("User Id not found"));
        return ConverDto(user);
    }

    @Override
    public List<ResponceDto> searchByUsers(Integer experience, Double income, String department){
        Specification<Users> spec = Specification.
                where(UserSpecification.hasExperience(experience))
                .and(UserSpecification.hasDepartment(department))
                .and(UserSpecification.hasIncome(income));
        return  userRepo.findAll()
                .stream().map(this::ConverDto).toList();
    }

    public ResponceDto ConverDto(Users user){
        ResponceDto dto = new ResponceDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setDepartment(
                user.getDepartment());

        dto.setExperience(
                user.getExperience());

        dto.setIncome(
                user.getIncome());

        dto.setSavings(
                user.getSavings());

        dto.setPhone(
                user.getPhone());

        dto.setAddress(
                user.getAddress());

        dto.setUsername(
                user.getUsername());

        if (user.getPhoto() != null) {
            dto.setPhotoPath(user.getPhoto().getPhotoPath());
        }

        if (user.getResume() != null) {
            dto.setResumePath(user.getResume().getResumePath());
        }
      return dto;

    }



    @Override
    public String registerUser(RegisterDto dto) throws Exception {
        Users user = new Users();

        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setDepartment(dto.getDepartment());
        user.setExperience(dto.getExperience());
        user.setIncome(dto.getIncome());
        user.setSavings(dto.getSavings());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setUsername(dto.getUsername());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

 Users savedUser = userRepo.save(user);

        savePhots(savedUser, dto.getPhoto());

        saveResume(savedUser, dto.getResume());

        return "User Registered Successfully";

    }
//photo upload method
public void savePhots(Users user, MultipartFile photo) throws Exception {

    String folder = System.getProperty("user.dir") + "/upload/photos/";

    File dir = new File(folder);
    if (!dir.exists()) {
        dir.mkdirs();
    }

    String filename =
            System.currentTimeMillis() + "_" + photo.getOriginalFilename();

    Path path = Paths.get(folder, filename);

    Files.copy(
            photo.getInputStream(),
            path,
            StandardCopyOption.REPLACE_EXISTING
    );

    Photos photoEntity = new Photos();
    photoEntity.setPhotoName(filename);

    // ✔ FIXED (store relative path only)
    photoEntity.setPhotoPath("upload/photos/" + filename);

    photoEntity.setUser(user);

    photoRepo.save(photoEntity);
}

    //file upload
    public void saveResume(Users user, MultipartFile resume) throws Exception {

        String folder = System.getProperty("user.dir") + "/upload/resume/";

        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filename =
                System.currentTimeMillis() + "_" + resume.getOriginalFilename();

        Path path = Paths.get(folder, filename);

        Files.copy(
                resume.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        Resume resumeEntity = new Resume();

        resumeEntity.setResumeName(filename);

        // ✔ FIXED (store relative path only)
        resumeEntity.setResumePath("upload/resume/" + filename);

        resumeEntity.setUser(user);

        fileRepo.save(resumeEntity);
    }
}
