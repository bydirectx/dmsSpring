package ml.bondarev.dms.service;

import ml.bondarev.dms.model.Document;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    public List<Document> getListDocument();

    public List<Document> getFormatListDocument(int typeSearch, String searchString, int order, boolean ascending);

    public Resource getFile(String filename);

    public Document addDocument(MultipartFile file, String fileAuthor, int typeDoc) throws IOException;

    public Document updateDocument(Document document);

    public void removeDocument(int id);

    public Document getDocumentById(int id);
}
