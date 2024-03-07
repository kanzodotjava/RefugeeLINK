package pt.upskill.RefugeeLINK.FileStorage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service

public interface FileService {

    String uploadImage (String path, MultipartFile file) throws IOException;
}
