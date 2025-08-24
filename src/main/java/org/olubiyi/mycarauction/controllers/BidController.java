package org.olubiyi.mycarauction.controllers;



import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.dtos.request.BidRequestDto;
import org.olubiyi.mycarauction.dtos.response.BidResponseDto;
import org.olubiyi.mycarauction.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @PostMapping
    public ResponseEntity<BidResponseDto> createBid(@RequestBody BidRequestDto request) {
        return ResponseEntity.ok(bidService.createBid(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidResponseDto> getBidById(@PathVariable UUID id) {
        return ResponseEntity.ok(bidService.getBidById(id));
    }
}

