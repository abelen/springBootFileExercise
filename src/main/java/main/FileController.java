package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The File endpoints.
 */
@RestController
public class FileController {

    /**
     * The {@link FileStorageService}
     */
    @Autowired
    private FileStorageService fileStorageService;

    /**
     * The {@link UploadFileResponseTransformer}.
     */
    @Autowired
    private UploadFileResponseTransformer uploadFileResponseTransformer;

    /**
     * Uploads the given file.
     *
     * @param file the file.
     * @return the {@link UploadFileResponse}
     */
    @PostMapping("/files")
    public UploadFileResponse uploadFile(@RequestParam("file") final MultipartFile file) throws IOException {
        return uploadFileResponseTransformer.toUploadFileResponse(fileStorageService.uploadFile(file));
    }

    /**
     * Gets the file metadata.
     *
     * @param fileName the file name
     * @return the file metadata
     */
    @RequestMapping(value = "/fileMetadata/{fileName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UploadFileResponse getFileMetadata(@PathVariable(name = "fileName") final String fileName) {
        return uploadFileResponseTransformer.toUploadFileResponse(fileStorageService.getFile(fileName));
    }

    /**
     * Gets the file metadata for files.
     *
     * @return the file metadata
     */
    @RequestMapping(value = "/fileMetadata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UploadFileResponse> getFileMetadataForAll() {
        final List<UploadFileResponse> uploadFileResponses = new ArrayList<>();
        final List<FileEntity> fileEntities = fileStorageService.getAllFiles();
        for (final FileEntity fileEntity : fileEntities) {
            uploadFileResponses.add(uploadFileResponseTransformer.toUploadFileResponse(fileEntity));
        }
        return uploadFileResponses;
    }

    /**
     * Downloads the file.
     *
     * @param fileId the file id
     * @param request the request
     * @return the file
     */
    @GetMapping("/files/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name="fileId")final Long fileId,
                                                 final HttpServletRequest request) {
        final FileEntity fileEntity = fileStorageService.getFile(fileId);

        final String contentType = Optional.ofNullable(fileEntity.getFileType()).orElse("application/octet-stream");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header("Content-Disposition", "attachment; fileName=\"" + fileEntity.getFileName() + "\"")
                .body(new ByteArrayResource(fileEntity.getData()));
    }
}
