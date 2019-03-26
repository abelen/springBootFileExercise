package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * The File Storage Service.
 */
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
    public String uploadFile(final MultipartFile file) {
        try {

            // create the file.
            final FileEntity fileEntity = FileEntity.builder()
                    .data(file.getBytes())
                    .fileName(file.getOriginalFilename())
                    .size(file.getSize())
                    .fileType(file.getContentType())
                    .build();

            fileRepository.save(fileEntity);
        } catch (Exception ex) {

        }
        return file.getOriginalFilename();
    }

    /**
     * Gets the file if it exists.
     *
     * @param fileName the filename.
     * @return
     */
    public FileEntity getFileMetadata(final String fileName) {
        return fileRepository.findByFileName(fileName);
    }

}
