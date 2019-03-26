package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
        String fileName = StringUtils.cleanPath(file.getName());

        try {

            // create the file.
            final UploadFileEntity uploadFileEntity = UploadFileEntity.builder()
                    .data(file.getBytes())
                    .fileName(fileName)
                    .size(file.getSize())
                    .fileType(file.getContentType())
                    .build();

            fileRepository.save(uploadFileEntity);
        } catch (Exception ex) {

        }
        return fileName;
    }


}
