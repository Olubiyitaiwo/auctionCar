package org.olubiyi.mycarauction.controllers;

import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.dtos.request.AuctionRequestDto;
import org.olubiyi.mycarauction.dtos.response.AuctionResponseDto;
import org.olubiyi.mycarauction.service.Auctionservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Auction")
@RequiredArgsConstructor
public class AuctionController {

    private final Auctionservice auctionservice;

//    @GetMapping("/{id}")
//    public ResponseEntity<AuctionResponseDto> getAuctionById(@PathVariable String id) {
//        return ResponseEntity.ok(auctionservice.getAuctionById(id));
//    }

//    @GetMapping
//    public ResponseEntity<List<AuctionResponseDto>> getAllAuctions() {
//        return ResponseEntity.ok(auctionservice.getAllAuctions());
//    }

    @PostMapping
    public ResponseEntity<?> createAuction(@RequestBody AuctionRequestDto auctionRequestDto) {
        try {
            AuctionResponseDto response = auctionservice.createAuction(auctionRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuction(@PathVariable String id, @RequestBody AuctionRequestDto auctionRequestDto) {
        try {
            AuctionResponseDto response = auctionservice.updateAuction(id, auctionRequestDto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteAuction(@PathVariable String id) {
//        try {
//            AuctionResponseDto response = auctionservice.deleteAuction(id);
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }
}
