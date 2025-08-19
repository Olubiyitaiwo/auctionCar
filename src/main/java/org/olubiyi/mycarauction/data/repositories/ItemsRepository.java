package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, String> {

}
