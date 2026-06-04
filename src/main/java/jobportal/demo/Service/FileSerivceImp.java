package jobportal.demo.Service;


import jobportal.demo.Entity.Photos;
import jobportal.demo.Entity.Resume;
import jobportal.demo.Repository.FileRepo;
import jobportal.demo.Repository.PhotoRepo;
import jobportal.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class FileSerivceImp implements  FileService{



    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PhotoRepo photoRepo;
    @Autowired
    private FileRepo fileRepo;

    @Override
    public ResponseEntity<Resource> downloadPhoto(Long id) throws Exception {

        Photos photo = photoRepo.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("User Id not Found"));

        // ✔ rebuild full path correctly
        String basePath = System.getProperty("user.dir") + "/";

        File file = new File(basePath + photo.getPhotoPath());

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }

        InputStreamResource resource =
                new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + photo.getPhotoName())
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> downloadResume(Long id) throws Exception {

        Resume resume = fileRepo.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("User Id not Found"));

        // ✔ rebuild correct path
        String basePath = System.getProperty("user.dir") + "/";

        File file = new File(basePath + resume.getResumePath());

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }

        InputStreamResource resource =
                new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + resume.getResumeName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
