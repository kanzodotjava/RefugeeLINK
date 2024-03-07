package pt.upskill.RefugeeLINK.FileStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload/{uniqueIdentifier}")
    public ResponseEntity<FileResponse> fileUpload(
            @PathVariable String uniqueIdentifier,
            @RequestParam("image") MultipartFile image
    ) {
        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, image, uniqueIdentifier);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "Failed to upload image!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new FileResponse(fileName, "File uploaded successfully!"), HttpStatus.OK);
    }
}
