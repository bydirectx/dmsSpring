package ml.bondarev.dms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "document")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_author")
    private String fileAuthor;

    @Column(name = "date_uploading")
    private long dateUploading;

    @Column(name = "file_version")
    private float fileVersion;

    @ApiModelProperty(notes = "File extension")
    @Column(name = "ext")
    private String ext;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "type_doc")
    private int typeDoc;

    @Column(name = "size")
    private float size;


    public Document() {
    }

    public Document(String fileName, String fileAuthor, long dateUploading,
                    float fileVersion, String ext,
                    String filePath, int typeDoc, float size) {
        this.fileName = fileName;
        this.fileAuthor = fileAuthor;
        this.dateUploading = dateUploading;
        this.fileVersion = fileVersion;
        this.ext = ext;
        this.filePath = filePath;
        this.typeDoc = typeDoc;
        this.size = size;
    }

    public Document(String fileName, String fileAuthor, long dateUploading, int typeDoc) {
        this.fileName = fileName;
        this.fileAuthor = fileAuthor;
        this.dateUploading = dateUploading;
        this.typeDoc = typeDoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAuthor() {
        return fileAuthor;
    }

    public void setFileAuthor(String fileAuthor) {
        this.fileAuthor = fileAuthor;
    }

    public long getDateUploading() {
        return dateUploading;
    }

    public void setDateUploading(long dateUploading) {
        this.dateUploading = dateUploading;
    }

    public float getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(float fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(int typeDoc) {
        this.typeDoc = typeDoc;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id == document.id &&
                Float.compare(document.fileVersion, fileVersion) == 0 &&
                typeDoc == document.typeDoc &&
                dateUploading == document.dateUploading &&
                Float.compare(document.size, size) == 0 &&
                Objects.equals(fileName, document.fileName) &&
                Objects.equals(fileAuthor, document.fileAuthor) &&
                Objects.equals(ext, document.ext) &&
                Objects.equals(filePath, document.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, fileAuthor, dateUploading, fileVersion, ext, filePath, typeDoc, size);
    }
}