package jobportal.demo.Controller;
import jobportal.demo.DTO.RegisterDto;
import jobportal.demo.DTO.ResponceDto;
import jobportal.demo.DTO.UpdateDto;

import jobportal.demo.Repository.FileRepo;
import jobportal.demo.Repository.PhotoRepo;
import jobportal.demo.Repository.UserRepo;

import jobportal.demo.Service.FileService;
import jobportal.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PhotoRepo photoRepo;

    @Autowired
    FileRepo fileRepo;

  @Autowired
    FileService fileService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute RegisterDto dto)
            throws Exception {
        return ResponseEntity.ok(
                userService.registerUser(dto));
    }

    @GetMapping()
    public  ResponseEntity<List<ResponceDto>>  getalluser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponceDto>
    getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponceDto>> searchByUsers(
            @RequestParam  Integer experience ,
            @RequestParam Double income,
            @RequestParam String department){
        return ResponseEntity.ok(userService.searchByUsers(experience,income,department));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateuser(@PathVariable Long id, UpdateDto dto){
        return ResponseEntity.ok(userService.updateUser(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.deleteUser(id));
    }
      @GetMapping("/photo/{id}")
    public  ResponseEntity<Resource> downloadPhotos(@PathVariable Long id)throws  Exception{
        return fileService.downloadPhoto(id);
    }
      @GetMapping("resume/{id}")
    public ResponseEntity<Resource> downloadResumes(@PathVariable Long id)throws  Exception{
      return fileService.downloadResume(id);
    }

}
