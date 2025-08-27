-- V1__init.sql
-- Initial schema for mycarauction

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ============================
-- USERS TABLE
-- ============================
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       username VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================
-- ITEMS TABLE
-- ============================
CREATE TABLE items (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       make VARCHAR(255) NOT NULL,
                       model VARCHAR(255) NOT NULL,
                       year INT NOT NULL,
                       color VARCHAR(100),
                       mileage DOUBLE PRECISION,
                       image_url VARCHAR(500)
);

-- ============================
-- AUCTIONS TABLE
-- ============================
CREATE TABLE auctions (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          item_id UUID NOT NULL,
                          seller_id UUID NOT NULL,
                          winner_id UUID NULL,
                          start_time TIMESTAMP,
                          end_time TIMESTAMP,
                          reserved_price NUMERIC(12,2),
                          current_price NUMERIC(12,2),
                          status VARCHAR(50),

                          CONSTRAINT fk_auction_item FOREIGN KEY (item_id) REFERENCES items(id),
                          CONSTRAINT fk_auction_seller FOREIGN KEY (seller_id) REFERENCES users(id),
                          CONSTRAINT fk_auction_winner FOREIGN KEY (winner_id) REFERENCES users(id)
);

-- ============================
-- BIDS TABLE
-- ============================
CREATE TABLE bids (
                      id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                      auction_id UUID NOT NULL,
                      bidder_id UUID NOT NULL,
                      amount NUMERIC(12,2) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                      CONSTRAINT fk_bid_auction FOREIGN KEY (auction_id) REFERENCES auctions(id),
                      CONSTRAINT fk_bid_bidder FOREIGN KEY (bidder_id) REFERENCES users(id)
);
