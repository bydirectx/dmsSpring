package ml.bondarev.dms.service;

import ml.bondarev.dms.model.Document;
import ml.bondarev.dms.repository.DocumentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceImplTest {

    @Mock
    private DocumentDao documentDao;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Test
    public void addDocument() throws IOException {
        byte[] content = { 0, 5, 3, 8, 10, 4, 0};
        MultipartFile multipartFile = new MockMultipartFile("testfile.txt", "testfile.txt", "text/plain", content);

        Mockito.when(documentService.addDocument(multipartFile, "Test", 1)).thenReturn(new Document());
    }
}