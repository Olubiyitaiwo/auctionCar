package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, String> {
    List<Items> id(String id);
}
