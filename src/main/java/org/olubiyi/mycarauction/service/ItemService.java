package org.olubiyi.mycarauction.service;

import org.olubiyi.mycarauction.data.models.Items;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    Items getItemById(UUID id);

    List<Items> getAllItems();

    List<Items> getItemByMake(String make);

    List<Items> getItemByModel(String model);

    List<Items> getItemByMakeAndModel(String make, String model);

    Items createItem(Items item);
}
