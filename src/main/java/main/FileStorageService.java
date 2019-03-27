package main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The File Storage Service.
 */
@Slf4j
@Service
public class FileStorageService {

    /**
     * The {@link FileRepository}.
     */
    @Autowired
    private FileRepository fileRepository;

    /**
     * Uploads the given file to the database.
     *
     * @param file the file to be uploaded
     * @return the filename
     */
    public String uploadFile(final MultipartFile file) throws IOException {
        try {

            // create the file.
            final FileEntity fileEntity = FileEntity.builder()
                    .data(file.getBytes())
                    .fileName(file.getOriginalFilename())
                    .size(file.getSize())
                    .fileType(file.getContentType())
                    .build();

            fileRepository.save(fileEntity);
        } catch (IOException ex) {
            log.error("Exception occurred when uploading file: {}", ex.getStackTrace());
            throw ex;
        }
        return file.getOriginalFilename();
    }

    /**
     * Gets the file if it exists.
     *
     * @param fileName the filename.
     * @return
     */
    public FileEntity getFile(final String fileName) {
        return fileRepository.findByFileName(fileName);
    }
}
