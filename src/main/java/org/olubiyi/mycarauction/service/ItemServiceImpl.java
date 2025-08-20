package org.olubiyi.mycarauction.service;

import lombok.RequiredArgsConstructor;
import org.olubiyi.mycarauction.data.models.Items;
import org.olubiyi.mycarauction.data.repositories.ItemsRepository;
import org.olubiyi.mycarauction.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;

    @Override
    public Items getItemById(UUID id) {
        return itemsRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
    }

    @Override
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    @Override
    public Items getItemByMake(String make) {
        return itemsRepository.findByMake(make)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with name:" + make));
    }

    @Override
    public Items getItemByModel(String model) {
        return itemsRepository.findByModel(model)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with name:" + model));
    }

    @Override
    public Items getItemByMakeAndModel(String make, String model) {
        return itemsRepository.findByMakeAndModel(make, model)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with name:" + make + " and model:" + model));
    }

    @Override
    public Items createItem(Items item) {
        return itemsRepository.save(item);
    }

}

