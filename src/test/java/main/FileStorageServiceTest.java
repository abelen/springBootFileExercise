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
 * Unit tests for {@link FileStorageService}.
 */
public class FileStorageServiceTest {

    /**
     * Tests the {@code FileStorageService#uploadFile(MultipartFile)}
     *
     * @throws IOException when getting bytes for file fails
     */
    @Test
    public void testUploadFile() throws IOException {

        final TestContext testContext = new TestContext();

        testContext.fileStorageService.uploadFile(testContext.multipartFile);

        Mockito.verify(testContext.fileRepository).save(Mockito.any(FileEntity.class));
    }

    /**
     * Tests the {@link FileStorageService#uploadFile(MultipartFile)}
     *
     * @throws IOException when getting bytes for file fails
     */
    @Test(expectedExceptions = IOException.class)
    public void testUploadFileException() throws IOException {

        final TestContext testContext = new TestContext();
        Mockito.when(testContext.multipartFile.getBytes()).thenThrow(IOException.class);

        try {
            testContext.fileStorageService.uploadFile(testContext.multipartFile);
        } catch (Exception ex) {
            Mockito.verifyZeroInteractions(testContext.fileRepository);
            throw ex;
        }
    }

    /**
     * Tests the {@link FileStorageService#getFile(String)}
     *
     * @throws IOException when getting bytes for file fails
     */
    @Test
    public void testGetFile() throws IOException {
        final TestContext testContext = new TestContext();

        final FileEntity fileEntity = testContext.fileStorageService.getFile("test.txt");

        Assert.assertEquals(fileEntity.getFileName(), "test.doc");
        Assert.assertEquals(fileEntity.getFileType(), "application/msword");
        Assert.assertEquals(fileEntity.getSize(), Long.valueOf(100));
        Assert.assertEquals(fileEntity.getFileId(), Long.valueOf(1));
        Assert.assertNotNull(fileEntity.getData());
    }


    /**
     * Keeps common context.
     */
    private static class TestContext {

        /**
         * The {@link FileStorageService} under test.
         */
        @InjectMocks
        private FileStorageService fileStorageService;

        /**
         * The {@link FileRepository}.
         */
        @Mock
        private FileRepository fileRepository;

        /**
         * Mocked {@link MultipartFile}.
         */
        @Mock
        private MultipartFile multipartFile;

        /**
         * Sets up the TestContext.
         */
        private TestContext() throws IOException {
            MockitoAnnotations.initMocks(this);
            setUpMethodStubs();
        }

        /**
         * Sets up the method stubs.
         */
        private void setUpMethodStubs() throws IOException {
            Mockito.when(multipartFile.getContentType()).thenReturn("application/msword");
            Mockito.when(multipartFile.getOriginalFilename()).thenReturn("test.doc");
            Mockito.when(multipartFile.getSize()).thenReturn(12345L);
            Mockito.when(multipartFile.getBytes()).thenReturn(new byte[10]);
//            Mockito.when(fileRepository.findByFileName(Mockito.anyString())).thenReturn(buildFileEntity());
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
