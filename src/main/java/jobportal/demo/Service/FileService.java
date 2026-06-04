package jobportal.demo.Service;


import org.jspecify.annotations.Nullable;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    public ResponseEntity<Resource> downloadPhoto(Long id) throws Exception;

   public ResponseEntity<Resource> downloadResume(Long id) throws  Exception;

}
