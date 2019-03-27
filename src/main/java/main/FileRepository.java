package main;

import org.springframework.data.repository.CrudRepository;

/**
 * The File Repository.
 */
public interface FileRepository extends CrudRepository<FileEntity, Integer> {

    /**
     * Returns the file by its file name.
     *
     * @param fileName the file name
     * @return the {@link FileEntity}
     */
    FileEntity findByFileName(final String fileName);
}
