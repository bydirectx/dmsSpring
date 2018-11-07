package ml.bondarev.dms.controller;

import com.google.common.collect.ImmutableList;
import ml.bondarev.dms.model.Document;
import ml.bondarev.dms.service.DocumentService;
import ml.bondarev.dms.service.TypeDocService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerTest {

    @Mock
    private TypeDocService typeDocService;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;


    @Test
    public void getDocuments() {
        when(documentController.getDocuments("1", "test", "1", "true")).thenReturn(ImmutableList.of());

        verify(documentService).getFormatListDocument(1, "test", 1, true);
    }

    @Test
    public void saveFileById() {
    }

    @Test
    public void addDocument() {
    }

    @Test
    public void updateDocument() {
    }

    @Test
    public void getDocument() {
    }

    @Test
    public void deleteDocument() {
    }
}