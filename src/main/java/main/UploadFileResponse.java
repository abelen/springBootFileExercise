package main;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * The canonical upload file response to be returned out to client.
 */
@Getter
@Builder
@AllArgsConstructor
public class UploadFileResponse {

    /**
     * The filename.
     */
    @NonNull
    private String fileName;

    /**
     * The file type.
     */
    @NonNull
    private String fileType;

    /**
     * The size of the file.
     */
    @NonNull
    private Long size;
}
