package org.olubiyi.mycarauction.service;

import org.olubiyi.mycarauction.data.models.Items;
import java.util.List;
import java.util.UUID;

public interface ItemService {

    Items getItemById(UUID id);

    List<Items> getAllItems();

    Items getItemByMake(String make);

    Items getItemByModel(String model);

    Items getItemByMakeAndModel(String make, String model);

    Items createItem(Items item);
}
