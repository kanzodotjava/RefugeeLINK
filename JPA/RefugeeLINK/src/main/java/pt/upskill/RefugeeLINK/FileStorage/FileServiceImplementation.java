package pt.upskill.RefugeeLINK.FileStorage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImplementation implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file, String uniqueIdentifier) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // Check if the file is a .jpg file
        if (!fileExtension.equalsIgnoreCase(".jpg")) {
            throw new IOException("Only .jpg files are allowed.");
        }

        // Creating a unique filename using the uniqueIdentifier
        String uniqueFileName = uniqueIdentifier + fileExtension;

        String filepath = path + File.separator + uniqueFileName;

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Overwrite the existing file, if it exists
        Files.copy(file.getInputStream(), Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }
}
