package ml.bondarev.dms.utils;

import ml.bondarev.dms.model.Document;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SortOrderTest {

    @Test
    public void sortOrder() throws Exception {
        List<Document> documentsSource = new ArrayList<>();
        documentsSource.add(
                new Document("Test 1", "Test Author", System.currentTimeMillis(), 1));
        Thread.sleep(100);
        documentsSource.add(
                new Document("Test 2", "Test Author", System.currentTimeMillis(), 2));

        System.out.println(documentsSource.get(0).getDateUploading() + "\n" + documentsSource.get(1).getDateUploading());

        documentsSource = SortOrder.sortOrder(documentsSource, 1, true);

        if (documentsSource.get(0).getTypeDoc() < documentsSource.get(1).getTypeDoc()) {
            System.out.println("Pass");
        } else {
            throw new Exception("Not pass");
        }

        documentsSource = SortOrder.sortOrder(documentsSource, 1, false);

        if (documentsSource.get(0).getTypeDoc() > documentsSource.get(1).getTypeDoc()) {
            System.out.println("Pass");
        } else {
            throw new Exception("Not pass");
        }

        documentsSource = SortOrder.sortOrder(documentsSource, 2, true);

        if (documentsSource.get(0).getTypeDoc() < documentsSource.get(1).getTypeDoc()) {
            System.out.println("Pass");
        } else {
            throw new Exception("Not pass");
        }

        documentsSource = SortOrder.sortOrder(documentsSource, 2, false);

        if (documentsSource.get(0).getTypeDoc() > documentsSource.get(1).getTypeDoc()) {
            System.out.println("Pass");
        } else {
            throw new Exception("Not pass");
        }
    }
}