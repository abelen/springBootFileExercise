package main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The File Entity that stored in the database.
 */
@Getter
@Entity
@Table(name = "file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

    /**
     * The file id.
     */
    @Id
    @GeneratedValue
    private Long fileId;

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

    /**
     * The file data.
     */
    @Lob
    private byte[] data;
}
