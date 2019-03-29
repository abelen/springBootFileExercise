package main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public FileEntity uploadFile(final MultipartFile file) throws IOException {
        FileEntity fileEntity;
        try {

            fileEntity = getFile(file.getOriginalFilename());

            // check for idempotency
            if (fileEntity != null) {
                return fileEntity;
            }

            // create the file.
            fileEntity = FileEntity.builder()
                    .data(file.getBytes())
                    .fileName(file.getOriginalFilename())
                    .size(file.getSize())
                    .fileType(file.getContentType())
                    .build();

            fileRepository.save(fileEntity);
            log.info("File {} persisted.", file.getOriginalFilename());

            return fileEntity;
        } catch (IOException ex) {
            log.error("Exception occurred when uploading file: {}", ex.getStackTrace());
            throw ex;
        }
    }

    /**
     * Gets the file if it exists.
     *
     * @param fileId the filename.
     * @return
     */
    public FileEntity getFile(final Long fileId) throws FileNotFoundException {
        return fileRepository.findById(fileId).orElseThrow(
                () -> new FileNotFoundException()
        );
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

    /**
     * Returns all the files.
     *
     * @return all of the files
     */
    public List<FileEntity> getAllFiles() {
        return StreamSupport.stream(fileRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Returns files by a given file type.
     *
     * @return list of {@link FileEntity}
     */
    public List<FileEntity> getFileByFileType(final String fileType) {
        return StreamSupport.stream(fileRepository.findByFileType(fileType).spliterator(), false).collect(Collectors.toList());
    }
}
