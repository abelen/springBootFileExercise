package main;

import org.springframework.data.repository.CrudRepository;

/**
 * The File Repository.
 */
public interface FileRepository extends CrudRepository<FileEntity, Integer> {

    FileEntity findByFileName(final String fileName);
}
