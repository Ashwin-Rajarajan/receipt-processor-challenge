package com.fetch.interview.api;

import com.fetch.interview.dto.request.Receipt;
import com.fetch.interview.dto.response.BaseResponse;
import com.fetch.interview.dto.response.BaseErrorResponse;
import com.fetch.interview.dto.response.FetchPointsResponse;
import com.fetch.interview.dto.response.ProcessReceiptResponse;
import com.fetch.interview.service.ReceiptsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * The API layer is abstracted from the business logic by the service layer. The API classes only handle interactions
 * with the clients.
 */
@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptsApi {

    private final ReceiptsService receiptsService;

    @PostMapping("/process")
    public ResponseEntity<BaseResponse> processReceipts(@Valid @RequestBody Receipt receipt, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BaseErrorResponse.builder().description("The receipt is invalid.").build());
        }
        String id = receiptsService.processReceipts(receipt);
        return ResponseEntity.ok(ProcessReceiptResponse.builder().id(id).build());
    }

    @GetMapping("/{receiptId}/points")
    public ResponseEntity<BaseResponse> fetchPointsForReceipt(@PathVariable("receiptId") String receiptId) {
        Long points = receiptsService.fetchPoints(receiptId);
        if(points == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseErrorResponse.builder().description("No receipt found for that ID.").build());
        }
        return ResponseEntity.ok(FetchPointsResponse.builder().points(points).build());
    }
}
