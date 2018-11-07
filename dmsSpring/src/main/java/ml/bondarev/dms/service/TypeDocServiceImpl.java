package ml.bondarev.dms.service;

import ml.bondarev.dms.model.TypeDoc;
import ml.bondarev.dms.repository.TypeDocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDocServiceImpl implements TypeDocService {

    @Autowired
    private TypeDocDao typeDocDao;

    @Override
    public List<TypeDoc> getListTypeDoc() {
        return typeDocDao.findAll();
    }

    @Override
    public TypeDoc addTypeDoc(TypeDoc typeDoc) {
        TypeDoc typeDocTemp = typeDocDao.getTypeDocByName(typeDoc.getName());
        if (typeDocTemp != null) {
            return typeDoc;
        } else {
            return typeDocDao.save(typeDoc);
        }
    }

    @Override
    public TypeDoc updateTypeDoc(TypeDoc typeDoc) {
        return typeDocDao.save(typeDoc);
    }

    @Override
    public void removeTypeDoc(int id) {
        typeDocDao.deleteById(id);
    }

    @Override
    public TypeDoc getTypeDocById(int id) {
        return typeDocDao.getOne(id);
    }

    @Override
    public TypeDoc getTypeDocByName(String name) {
        return typeDocDao.getTypeDocByName(name);
    }
}
