package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * The File upload endpoints.
 */
@RestController
public class FileUploadController {

    /**
     * The {@link FileStorageService}
     */
    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Uploads the given file.
     *
     * @param file the file.
     * @return the {@link UploadFileResponse}
     */
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") final MultipartFile file) {

        final String fileName = fileStorageService.uploadFile(file);

        return new UploadFileResponse(fileName,
                file.getContentType(),
                file.getSize());
    }
}
