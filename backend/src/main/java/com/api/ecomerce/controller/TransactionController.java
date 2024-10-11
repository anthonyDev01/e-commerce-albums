package com.api.ecomerce.controller;

import com.api.ecomerce.documentation.TransactionDocumentation;
import com.api.ecomerce.dto.response.TransactionAlbumResponseDto;
import com.api.ecomerce.dto.response.TransactionMetricsResponseDto;
import com.api.ecomerce.dto.response.TransactionUserMetricsResponse;
import com.api.ecomerce.model.Transaction;
import com.api.ecomerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController implements TransactionDocumentation {
    private final TransactionService transactionService;

    @Override
    @GetMapping("/metrics")
    public ResponseEntity<TransactionMetricsResponseDto> getTransactionMetrics(){
        return ResponseEntity.ok(transactionService.findTransactionMetrics());
    }

    @Override
    @GetMapping("/users/metrics")
    public ResponseEntity<TransactionUserMetricsResponse> getTransactionMetricsByUser(){
        return ResponseEntity.ok(transactionService.findTransactionMetricsByUser());
    }


    @GetMapping("/users/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(transactionService.findAllUserTransaction(userId));
    }


    @Override
    @GetMapping("/album/metrics")
    public ResponseEntity<TransactionAlbumResponseDto> getTransactionMetricsByAlbum(){
        return ResponseEntity.ok(transactionService.findTransactionMetricsByAlbum());
    }


}
