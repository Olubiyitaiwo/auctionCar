package org.olubiyi.mycarauction.controllers;

import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.dtos.request.AuctionRequestDto;
import org.olubiyi.mycarauction.dtos.response.AuctionResponseDto;
import org.olubiyi.mycarauction.service.Auctionservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Auction")
@RequiredArgsConstructor
public class AuctionController {
    private final Auctionservice auctionservice;

    @GetMapping("/{id}")
    public ResponseEntity<AuctionResponseDto> getAuctionById(@PathVariable String id) {
        return ResponseEntity.ok(auctionservice.getAuctionById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuctionResponseDto>> getAllAuctions() {
        List<AuctionResponseDto> response = auctionservice.getAllAuctions();
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<AuctionResponseDto> createAuction(@RequestBody AuctionRequestDto auctionRequestDto) {
        AuctionResponseDto response = auctionservice.createAuction(auctionRequestDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuctionResponseDto> updateAuction(
            @PathVariable String id,
            @RequestBody AuctionRequestDto auctionRequestDto) {
        AuctionResponseDto response = auctionservice.updateAuction(id, auctionRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuctionResponseDto> deleteAuctions(@PathVariable String id) {
        AuctionResponseDto response = auctionservice.deleteAuction(id);
        return ResponseEntity.ok(response);
    }
}
