package ml.bondarev.dms.service;

import ml.bondarev.dms.model.TypeDoc;

import java.util.List;

public interface TypeDocService {

    public List<TypeDoc> getListTypeDoc();

    public TypeDoc addTypeDoc(TypeDoc typeDoc);

    public TypeDoc updateTypeDoc(TypeDoc typeDoc);

    public void removeTypeDoc(int id);

    public TypeDoc getTypeDocById(int id);

    public TypeDoc getTypeDocByName(String name);
}
