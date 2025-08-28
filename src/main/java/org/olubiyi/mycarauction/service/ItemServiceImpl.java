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
    public List<Items> getItemByMake(String make) {
        List<Items> items = itemsRepository.findByMake(make);
        if (items.isEmpty()) {
            throw new ItemNotFoundException("No items found with make: " + make);
        }
        return items;
    }

    @Override
    public List<Items> getItemByModel(String model) {
        List<Items> items = itemsRepository.findByModel(model);
        if (items.isEmpty()) {
            throw new ItemNotFoundException("No items found with model: " + model);
        }
        return items;
    }

    @Override
    public List<Items> getItemByMakeAndModel(String make, String model) {
        List<Items> items = itemsRepository.findByMakeAndModel(make, model);
        if (items.isEmpty()) {
            throw new ItemNotFoundException("No items found with make: " + make + " and model: " + model);
        }
        return items;
    }

    @Override
    public Items createItem(Items item) {
        return itemsRepository.save(item);
    }
}
