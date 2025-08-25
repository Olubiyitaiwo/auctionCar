package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.enums.Status;
import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuctionRepository extends JpaRepository<Auction, UUID> {
    Optional<Auction> findByItem(Items item);

    List<Auction> findByStatusAndEndTimeBefore(Status status, LocalDateTime now);
}
