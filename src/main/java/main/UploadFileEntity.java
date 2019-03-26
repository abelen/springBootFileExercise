package main;

import lombok.Builder;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "file")
@Builder
public class UploadFileEntity {

    /**
     * The file id.
     */
    @Id
    @GeneratedValue
    private Long fileId;

    /**
     * The filename.
     */
    private String fileName;


    /**
     * The file type.
     */
    private String fileType;

    /**
     * The size of the file.
     */
    @NonNull
    private Long size;

    /**
     * The file data.
     */
    @Lob
    private byte[] data;


}
