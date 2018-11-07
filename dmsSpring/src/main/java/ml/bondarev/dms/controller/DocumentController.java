package ml.bondarev.dms.controller;

import ml.bondarev.dms.model.Document;
import ml.bondarev.dms.service.DocumentService;
import ml.bondarev.dms.utils.MediaTypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/documents"})
public class DocumentController {

    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(method = RequestMethod.GET)
    public List<Document> getDocuments(@RequestParam(name = "typeSearch") String typeSearch,
                                       @RequestParam(name = "searchString") String searchString,
                                       @RequestParam(name = "order") String order,
                                       @RequestParam(name = "ascending") String ascending) {
        log.info("getDocuments| typeSearch: " + typeSearch + " searchString: " + searchString +
                " order: " + order + " ascending: " + ascending);
        List<Document> documentList = documentService.getFormatListDocument(
                Integer.parseInt(typeSearch), searchString, Integer.parseInt(order), Boolean.parseBoolean(ascending));

        return documentList;
    }

    @RequestMapping(value = "/getfile", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> saveFileById(@RequestParam(name = "docId") int id) throws IOException {
        Document document = documentService.getDocumentById(id);

        String filePath = document.getFilePath();
        String fileName = document.getFileName() + document.getExt();

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        Path path = Paths.get(filePath);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        log.info("File is being issued. FileName: " + fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .contentType(mediaType)
                .contentLength(data.length)
                .body(resource);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    void addDocument(@RequestParam("file") MultipartFile file, @RequestParam("author") String fileAuthor, @RequestParam("td") int typeDoc) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            documentService.addDocument(file, fileAuthor, typeDoc);
        }

        log.info("Successful adding a list of documents: " + file.getOriginalFilename());
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Document updateDocument(@PathVariable("id") int id, @RequestBody Document document) {
        document.setId(id);
        documentService.updateDocument(document);

        log.info("Successful updating a list of documents: " + document.getFileName() + document.getExt());

        return document;
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public Document getDocument(@PathVariable("id") String id) {

        log.info("Issuance of the document: " + id);

        return documentService.getDocumentById(Integer.parseInt(id));
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocument(@PathVariable("id") int id) {
        documentService.removeDocument(id);

        log.info("Successful removing of the document: " + id);
    }
}