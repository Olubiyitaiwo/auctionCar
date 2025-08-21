CREATE TABLE items (
                       id UUID PRIMARY KEY,
                       make VARCHAR(255) NOT NULL,
                       model VARCHAR(255) NOT NULL,
                       year INT NOT NULL,
                       color VARCHAR(100),
                       mileage DOUBLE PRECISION,
                       image_url VARCHAR(500)
);
