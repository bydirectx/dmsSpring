package ml.bondarev.dms.controller;

import ml.bondarev.dms.model.TypeDoc;
import ml.bondarev.dms.service.TypeDocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/typedoc"})
public class TypeDocController {

    private static final Logger log = LoggerFactory.getLogger(TypeDocController.class);

    @Autowired
    private TypeDocService typeDocService;


    @RequestMapping(method = RequestMethod.GET)
    public List<TypeDoc> getTypeDocs() {
        return typeDocService.getListTypeDoc();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addTypeDoc(@RequestBody TypeDoc typeDoc) {
        typeDocService.addTypeDoc(typeDoc);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public TypeDoc getTypeDoc(@PathVariable("id") int id) {
        log.info("Get typedoc by id: " + id);

        return typeDocService.getTypeDocById(id);
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public TypeDoc getTypeDocByName(@PathVariable("name") String name) {
        log.info("Get typedoc by name: " + name);

        return typeDocService.getTypeDocByName(name);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTypeDoc(@PathVariable("id") int id) {
        typeDocService.removeTypeDoc(id);
    }
}
