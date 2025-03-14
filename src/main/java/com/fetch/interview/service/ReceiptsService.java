package com.fetch.interview.service;

import com.fetch.interview.dto.request.Receipt;
import com.fetch.interview.model.ReceiptPoint;
import com.fetch.interview.repository.ReceiptsRepository;
import com.fetch.interview.service.helpers.ReceiptPointsCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service classes hold the business logic of the application. These classes are abstracted from the underlying data
 * store by the repository layer.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptsService {

    private final ReceiptsRepository repository;

    /**
     * Processes a receipt and stores the points awarded for it.
     * @param receipt The {@link Receipt} object from the request
     * @return The ID assigned to the receipt.
     */
    public String processReceipts(Receipt receipt) {
        long points = ReceiptPointsCalculator.calculatePoints(receipt);
        ReceiptPoint receiptPoint = new ReceiptPoint();
        receiptPoint.setPoints(points);
        return repository.insert(receiptPoint).getId();
    }

    /**
     * Fetches the points awarded for an already processed receipt.
     * @param receiptId The ID of the receipt
     * @return The amount of points awarded to the receipt or {@code null} if no receipt was found for the ID.
     */
    public Long fetchPoints(String receiptId) {
        ReceiptPoint receiptPoint = repository.findById(receiptId);
        if(receiptPoint == null) {
            log.error("No Points found for Receipt ID: " + receiptId);
            return null;
        }
        return receiptPoint.getPoints();
    }

}
