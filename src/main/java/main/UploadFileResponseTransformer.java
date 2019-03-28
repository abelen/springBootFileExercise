package main;

import org.springframework.stereotype.Component;

/**
 * Transforms the {@link FileEntity} to its canonical {@link UploadFileResponse}.
 */
@Component
public class UploadFileResponseTransformer {

    /**
     * Converts the UploadFileResponse.
     *
     * @param fileEntity the {@link FileEntity} returned
     */
    public UploadFileResponse toUploadFileResponse(final FileEntity fileEntity) {
        return UploadFileResponse.builder()
                .fileId(fileEntity.getFileId())
                .fileName(fileEntity.getFileName())
                .fileType(fileEntity.getFileType())
                .size(fileEntity.getSize())
                .build();
    }
}
