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
import org.springframework.security.crypto.password.PasswordEncoder;

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
                                   BidRepository bidRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {

            // ✅ Seed user safely
            User user = userRepository.findByEmail("taiwo@example.com").orElse(null);

            if (user == null) {
                user = new User();
                user.setFirstName("Taiwo");
                user.setLastName("Olu");
                user.setEmail("taiwo@example.com");
                user.setPassword(passwordEncoder.encode("password123")); // hash password
                user.setPhone("08012345678");
                user.setUsername("Twinners");
                userRepository.save(user);
                System.out.println("✅ Test user inserted!");
            } else {
                System.out.println("ℹ️ User already exists, skipping insertion.");
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

            // ✅ Seed auctions
            if (auctionRepository.count() == 0) {
                itemsRepository.findAll().forEach(item -> {
                    Auction auction = new Auction();
                    auction.setItem(item);
                    auction.setStartTime(LocalDateTime.now());
                    auction.setEndTime(LocalDateTime.now().plusDays(3));
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


