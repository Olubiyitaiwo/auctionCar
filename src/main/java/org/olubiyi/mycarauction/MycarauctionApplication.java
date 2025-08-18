package org.olubiyi.mycarauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.olubiyi.mycarauction.data.repositories")
//@EnableMongoRepositories(basePackages = "org.olubiyi.mycarauction.data.mongo")
public class MycarauctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycarauctionApplication.class, args);
    }

}
