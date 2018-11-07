package ml.bondarev.dms.service;

import ml.bondarev.dms.model.Document;
import ml.bondarev.dms.model.TypeDoc;
import ml.bondarev.dms.repository.DocumentDao;
import ml.bondarev.dms.repository.TypeDocDao;
import ml.bondarev.dms.utils.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);


    private String uploadPath = "R:\\Projects\\innowise\\Files";

    private final Path rootLocation = Paths.get(uploadPath);

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private TypeDocDao typeDocDao;


    @Override
    public List<Document> getListDocument() {
        return documentDao.findAll();
    }

    @Override
    public List<Document> getFormatListDocument(int typeSearch, String searchString, int order, boolean ascending) {

        List<TypeDoc> typeDocs = null;
        List<Document> documentList = null;

        if (typeSearch == 3) {
            typeDocs = typeDocDao.getListTypeDocByName(searchString);
            log.info(typeDocs.size() + " | " + typeDocs);
        }

        switch (typeSearch) {
            case 1: {
                documentList = documentDao.getListDocumentByFileName(searchString);
                break;
            }
            case 2: {
                documentList = documentDao.getListDocumentByFileAuthor(searchString);
                break;
            }
            case 3: {
                log.info("case 3");
                documentList = new ArrayList<>();

                for (int i = 0; i < typeDocs.size(); i++) {
                    documentList.addAll(documentDao.getListDocumentByTypeDoc(typeDocs.get(i).getId()));
                    log.info("size: " + documentList.size());
                }
                break;
            }
            case 4: {
                documentList = documentDao.getListDocumentByExt(searchString);
            }
        }

        return SortOrder.sortOrder(documentList, order, ascending);
    }

    @Override
    @Transactional
    public Resource getFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("You file is not readable or not exists");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error getting file");
        }
    }

    @Override
    @Transactional
    public Document addDocument(MultipartFile file, String fileAuthor, int typeDoc) throws IOException {
        Document resultDocument = new Document();

        File uploadDir = new File(uploadPath);
        String path = uploadDir.toPath().toString() + "\\" + file.getOriginalFilename();
        BasicFileAttributes attr = null;

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        file.transferTo(new File(path));

        attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);

        resultDocument.setDateUploading(System.currentTimeMillis());

        if (file.getOriginalFilename().length() > 80) {
            resultDocument.setFileName(file.getOriginalFilename().
                    substring(0, 70));
        } else {
            resultDocument.setFileName(file.getOriginalFilename().
                    substring(0, file.getOriginalFilename().lastIndexOf('.')));
        }
        resultDocument.setExt(file.getOriginalFilename().
                substring(file.getOriginalFilename().lastIndexOf('.')));
        resultDocument.setFilePath(uploadDir + "\\" + file.getOriginalFilename());
        resultDocument.setSize(attr.size() / 1024000F);
        resultDocument.setFileAuthor(fileAuthor);
        resultDocument.setTypeDoc(typeDoc);

        return documentDao.save(resultDocument);
    }

    @Override
    public Document updateDocument(Document document) {
        return documentDao.saveAndFlush(document);
    }

    @Override
    @Transactional
    public void removeDocument(int id) {
        File file = new File(documentDao.getOne(id).getFilePath());
        file.delete();

        documentDao.deleteById(id);
    }

    @Override
    public Document getDocumentById(int id) {
        return documentDao.getOne(id);
    }
}
