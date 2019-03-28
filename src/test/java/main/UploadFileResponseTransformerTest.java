package main;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link UploadFileResponseTransformer}
 */
public class UploadFileResponseTransformerTest {

    /**
     * Tests the {@link UploadFileResponseTransformer#toUploadFileResponse(FileEntity)}
     */
    @Test
    public void testToUploadFileResponse() {
        final UploadFileResponseTransformer uploadFileResponseTransformer = new UploadFileResponseTransformer();
        final UploadFileResponse uploadFileResponse = uploadFileResponseTransformer.toUploadFileResponse(buildFileEntity());

        Assert.assertEquals(uploadFileResponse.getFileId(), Long.valueOf(1));
        Assert.assertEquals(uploadFileResponse.getFileName(), "test.doc");
        Assert.assertEquals(uploadFileResponse.getFileType(), "application/msword");
        Assert.assertEquals(uploadFileResponse.getSize(), Long.valueOf(100));
    }

    /**
     * Builds the {@link FileEntity} for unit testing.
     *
     * @return the {@link FileEntity}
     */
    private FileEntity buildFileEntity() {
        return FileEntity.builder()
                .data(new byte[10])
                .fileName("test.doc")
                .fileType("application/msword")
                .size(100L)
                .fileId(1L)
                .build();
    }
}
