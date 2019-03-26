package main;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The File Storage Properties.
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    /**
     * The upload directory.
     */
    @Getter
    @Setter
    private String uploadDir;
}
