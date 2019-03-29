package main;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The File Repository.
 */
public interface FileRepository extends CrudRepository<FileEntity, Long> {

    /**
     * Returns the file by its file name.
     *
     * @param fileName the file name
     * @return the {@link FileEntity}
     */
    FileEntity findByFileName(final String fileName);

    /**
     * Returns files by its file type.
     *
     * @param fileType the file type
     * @return the {@link FileEntity}
     */
    List<FileEntity> findByFileType(final String fileType);
}
