package main;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Unit tests for {@link FileController}.
 */
public class FileControllerTest {

    /**
     * Tests the {@link FileController#uploadFile(MultipartFile)}
     */
    @Test
    public void testUploadFile() throws IOException {
        final TestContext testContext = new TestContext();
        final UploadFileResponse uploadFileResponse = testContext.fileController.uploadFile(testContext.multipartFile);

        Assert.assertEquals(uploadFileResponse.getFileName(), "test.doc");
        Assert.assertEquals(uploadFileResponse.getFileType(), "application/msword");
        Assert.assertEquals(uploadFileResponse.getSize(), Long.valueOf(12345));
    }

    @Test
    public void testGetFileMetadata() throws IOException {
        final TestContext testContext = new TestContext();
//        final UploadFileResponse uploadFileResponse = testContext.fileController.getFileMetadataForFile("test.doc");
//
//        Assert.assertEquals(uploadFileResponse.getFileName(), "test.doc");
//        Assert.assertEquals(uploadFileResponse.getFileType(), "application/msword");
//        Assert.assertEquals(uploadFileResponse.getSize(), Long.valueOf(100));
    }

    /**
     * Keeps common context.
     */
    private static class TestContext {

        /**
         * The {@link FileController} under test.
         */
        @InjectMocks
        private FileController fileController;

        /**
         * Mocked {@link FileStorageService}
         */
        @Mock
        private FileStorageService fileStorageService;

        /**
         * Mocked {@link MultipartFile}.
         */
        @Mock
        private MultipartFile multipartFile;

        /**
         * Sets up the test context.
         */
        private TestContext() throws IOException {
            MockitoAnnotations.initMocks(this);
            Mockito.when(fileStorageService.uploadFile(Mockito.any(MultipartFile.class))).thenReturn(buildFileEntity());
            Mockito.when(multipartFile.getContentType()).thenReturn("application/msword");
            Mockito.when(multipartFile.getOriginalFilename()).thenReturn("test.doc");
            Mockito.when(multipartFile.getSize()).thenReturn(12345L);
            Mockito.when(multipartFile.getBytes()).thenReturn(new byte[10]);
//            Mockito.when(fileStorageService.getFile(Mockito.eq("test.doc"))).thenReturn(buildFileEntity());
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
}
