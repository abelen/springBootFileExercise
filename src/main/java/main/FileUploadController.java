package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/files")
    public UploadFileResponse uploadFile(@RequestParam("file") final MultipartFile file) {
        final String fileName = fileStorageService.uploadFile(file);

        return new UploadFileResponse(fileName,
                file.getContentType(),
                file.getSize());
    }

    /**
     * Gets the file metadata.
     *
     * @param fileName the file name
     * @return the file metadata
     */
    @GetMapping("/files/{fileName}")
    public UploadFileResponse getFileMetadata(@PathVariable(name = "fileName") final String fileName) {
        final FileEntity fileEntity = fileStorageService.getFileMetadata(fileName);
        return UploadFileResponse.builder()
                .fileName(fileEntity.getFileName())
                .fileType(fileEntity.getFileType())
                .size(fileEntity.getSize())
                .build();
    }
}
