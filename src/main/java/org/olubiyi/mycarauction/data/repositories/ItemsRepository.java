package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemsRepository extends JpaRepository<Items, UUID> {

    Optional<Items> findByMake(String make);

    Optional<Items> findByModel(String model); //
    // 👈 Another option
    Optional<Items> findByMakeAndModel(String make, String model);

    Items getItemByMake(String make);
}
