package ml.bondarev.dms.utils;

import ml.bondarev.dms.model.Document;

import java.util.Date;
import java.util.List;

public class SortOrder {

    public static int compare(long o1, long o2) {
        if (o1 > o2) {
            return 1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return -1;
        }
    }

    public static List<Document> sortOrder(List<Document> documents, int order, boolean ascending) {
        List<Document> documentList = documents;

        if (order == 1 & ascending) {
            documentList.sort((d1, d2) ->
                    d1.getFileName().compareTo(d2.getFileName()));
        } else if (order == 1 & !ascending) {
            documentList.sort((d1, d2) ->
                    d2.getFileName().compareTo(d1.getFileName()));
        } else if (order == 2 && ascending) {
            documentList.sort((d1, d2) ->
                    new Date(d1.getDateUploading()).compareTo(new Date(d2.getDateUploading())));
        } else if (order == 2 && !ascending) {
            documentList.sort((d1, d2) ->
                    new Date(d2.getDateUploading()).compareTo(new Date(d1.getDateUploading())));
        };
        return documentList;
    }
}
