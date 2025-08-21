package org.olubiyi.mycarauction.controllers;

import lombok.AllArgsConstructor;
import org.olubiyi.mycarauction.data.models.Items;
import org.olubiyi.mycarauction.dtos.request.ItemRequestDto;
import org.olubiyi.mycarauction.dtos.response.ItemResponseDto;
import org.olubiyi.mycarauction.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Api/Item")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemsById(@PathVariable UUID id) {
        Items item = itemService.getItemById(id);
        ItemResponseDto dto = new ItemResponseDto(
                item.getId(),
                item.getMake(),
                item.getModel(),
                item.getYear(),
                item.getColor(),
                item.getMileage(),
                item.getImageUrl()
        );
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems(@RequestParam(required = false) List<String> ids) {
        List<Items> items = itemService.getAllItems();

        if (ids != null && !ids.isEmpty()) {
            List<UUID> uuidList = ids.stream()
                    .map(UUID::fromString)
                    .toList();

            items = items.stream()
                    .filter(item -> uuidList.contains(item.getId()))
                    .toList();
        }

        List<ItemResponseDto> dtoList = items.stream()
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getMake(),
                        item.getModel(),
                        item.getYear(),
                        item.getColor(),
                        item.getMileage(),
                        item.getImageUrl()
                ))
                .toList();

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/by-model")
    public ResponseEntity<ItemResponseDto> getItemByModel(@RequestParam String model) {
        Items item = itemService.getItemByModel(model);
        ItemResponseDto response = new ItemResponseDto(
                item.getId(),
                item.getMake(),
                item.getModel(),
                item.getYear(),
                item.getColor(),
                item.getMileage(),
                item.getImageUrl()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-model-and-by-make")
    public ResponseEntity<ItemResponseDto> getItemByMakeAndModel(@RequestParam String make, @RequestParam String model) {
        Items item = itemService.getItemByMakeAndModel(make, model);
        ItemResponseDto response = new ItemResponseDto(
                item.getId(),
                item.getMake(),
                item.getModel(),
                item.getYear(),
                item.getColor(),
                item.getMileage(),
                item.getImageUrl()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ItemResponseDto> createAuctionItem(@RequestBody ItemRequestDto request) {
        System.out.println("Received request: " + request);

        Items item = new Items();
        item.setMake(request.getMake());
        item.setModel(request.getModel());
        item.setYear(request.getYear());
        item.setColor(request.getColor());
        item.setMileage(request.getMileage());
        item.setImageUrl(request.getImageUrl());

        Items savedItem = itemService.createItem(item);

        ItemResponseDto response = new ItemResponseDto(
                savedItem.getId(),
                savedItem.getMake(),
                savedItem.getModel(),
                savedItem.getYear(),
                savedItem.getColor(),
                savedItem.getMileage(),
                savedItem.getImageUrl()
        );

        return ResponseEntity.ok(response);
    }

}
