package com.fetch.interview.repository;

import com.fetch.interview.model.ReceiptPoint;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Repository class for holding the receipt points - having a separate repository class provides abstraction to the
 * calling classes about what database is being used. This code can be updated from the in-memory map to a real database
 * by just updating this file without any changes to the API or Service classes.
 */
@Repository
public class ReceiptsRepository {

    //Using a LinkedHashMap here was a conscious choice to maintain insertion order.
    private final Map<String, ReceiptPoint> database = new LinkedHashMap<>();

    //Response from this method could be null - the @Nullable annotation warns the callers to handle it.
    @Nullable
    public ReceiptPoint findById(String receiptId) {
        return database.get(receiptId);
    }

    public ReceiptPoint insert(ReceiptPoint receiptPoint) {
        String id = UUID.randomUUID().toString();
        //In the rare event of an ID collision, regenerate ID:
        while(database.containsKey(id)) {
            id = UUID.randomUUID().toString();
        }
        receiptPoint.setId(id);
        database.put(id, receiptPoint);
        return receiptPoint;
    }

}
