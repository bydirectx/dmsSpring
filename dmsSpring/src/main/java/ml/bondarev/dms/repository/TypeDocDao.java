package ml.bondarev.dms.repository;

import ml.bondarev.dms.model.TypeDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TypeDocDao extends JpaRepository<TypeDoc, Integer> {

    @Query("select td from TypeDoc td where td.name =:name")
    public TypeDoc getTypeDocByName(@Param("name") String typeDocName);

    @Query("select td from TypeDoc td where td.name like %:name%")
    public List<TypeDoc> getListTypeDocByName(@Param("name") String name);
}