package org.olubiyi.mycarauction;

import org.olubiyi.mycarauction.data.models.User;
import org.olubiyi.mycarauction.data.models.Items;
import org.olubiyi.mycarauction.data.models.Auction;
import org.olubiyi.mycarauction.data.models.Bid;
import org.olubiyi.mycarauction.data.repositories.UserRepository;
import org.olubiyi.mycarauction.data.repositories.ItemsRepository;
import org.olubiyi.mycarauction.data.repositories.AuctionRepository;
import org.olubiyi.mycarauction.data.repositories.BidRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class MycarauctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycarauctionApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDatabase(UserRepository userRepository,
                                   ItemsRepository itemsRepository,
                                   AuctionRepository auctionRepository,
                                   BidRepository bidRepository) {
        return args -> {

            // ✅ Seed user
            User user;
            if (userRepository.count() == 0) {
                user = new User();
                user.setFirstName("Taiwo");
                user.setLastName("Olu");
                user.setEmail("taiwo@example.com");
                user.setPassword("password123"); // TODO: encode with PasswordEncoder
                user.setPhone("08012345678");
                user.setUsername("Twinners");
                userRepository.save(user);
                System.out.println("✅ Test user inserted!");
            } else {
                user = userRepository.findAll().get(0);
            }

            final User seededUser = user;

            // ✅ Seed items
            if (itemsRepository.count() == 0) {
                Items item1 = new Items();
                item1.setMake("Toyota");
                item1.setModel("Corolla");
                item1.setYear(2019);
                item1.setColor("White");
                item1.setMileage(12000);
                itemsRepository.save(item1);

                Items item2 = new Items();
                item2.setMake("Honda");
                item2.setModel("Civic");
                item2.setYear(2020);
                item2.setColor("Black");
                item2.setMileage(15000);
                itemsRepository.save(item2);

                System.out.println("✅ Test items inserted!");
            }

            // ✅ Seed auctions with all required fields
            if (auctionRepository.count() == 0) {
                itemsRepository.findAll().forEach(item -> {
                    Auction auction = new Auction();
                    auction.setItem(item);
                    auction.setSeller(seededUser);                  // Must have a seller
                    auction.setStartTime(LocalDateTime.now());
                    auction.setEndTime(LocalDateTime.now().plusDays(3));
                    auction.setReservedPrice(BigDecimal.valueOf(2000));
                    auction.setCurrentPrice(BigDecimal.valueOf(1500));
                    auction.setStatus(org.olubiyi.mycarauction.data.enums.Status.Live); // Must have status
                    auctionRepository.save(auction);
                });
                System.out.println("✅ Test auctions inserted!");
            }

            // ✅ Seed bids
            if (bidRepository.count() == 0) {
                auctionRepository.findAll().forEach(auction -> {
                    Bid bid = new Bid();
                    bid.setAmount(BigDecimal.valueOf(1500 + Math.random() * 1000));
                    bid.setUser(seededUser);
                    bid.setAuction(auction);
                    bidRepository.save(bid);
                });
                System.out.println("✅ Test bids inserted!");
            }

        };
    }
}

