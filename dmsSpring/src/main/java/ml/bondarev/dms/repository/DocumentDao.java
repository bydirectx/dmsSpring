package ml.bondarev.dms.repository;

import ml.bondarev.dms.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

    @Query("SELECT doc FROM Document doc WHERE  doc.fileName like %:fileName%")
    public List<Document> getListDocumentByFileName(@Param("fileName") String fileName);

    @Query("SELECT doc FROM Document doc WHERE doc.fileAuthor like %:fileAuthor%")
    public List<Document> getListDocumentByFileAuthor(@Param("fileAuthor") String fileAuthor);

    @Query("SELECT doc FROM Document doc WHERE doc.typeDoc =:typeDoc")
    public List<Document> getListDocumentByTypeDoc(@Param("typeDoc") int typeDoc);

    @Query("SELECT doc from Document doc WHERE doc.ext like %:ext%")
    public List<Document> getListDocumentByExt(@Param("ext") String ext);

}
