package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemsRepository extends JpaRepository<Items, UUID> {

    List<Items> findByMake(String make);

    List<Items> findByModel(String model);

    List<Items> findByMakeAndModel(String make, String model); // ✅ Correct
}
