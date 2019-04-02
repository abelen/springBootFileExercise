package main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The email POJO.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {

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
    private Long size;

    @Override
    public String toString() {
        return "fileName: " + fileName + " fileType: " + fileType + " size: " + size;
    }
}
