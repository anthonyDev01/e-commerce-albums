package com.api.ecomerce.controller;

import com.api.ecomerce.documentation.TransactionDocumentation;
import com.api.ecomerce.dto.response.TransactionAlbumResponseDto;
import com.api.ecomerce.dto.response.TransactionMetricsResponseDto;
import com.api.ecomerce.dto.response.TransactionUserMetricsResponse;
import com.api.ecomerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Override
    @GetMapping("/album/metrics")
    public ResponseEntity<TransactionAlbumResponseDto> getTransactionMetricsByAlbum(){
        return ResponseEntity.ok(transactionService.findTransactionMetricsByAlbum());
    }


}
