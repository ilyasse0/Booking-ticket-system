CREATE TABLE ticket_order (
                              id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              total DECIMAL(10, 2) NOT NULL,
                              quantity BIGINT NOT NULL,
                              placed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              customer_id BIGINT NOT NULL,
                              event_id BIGINT, -- Now defined soft reference to event in another service
                              CONSTRAINT fk_order_customer FOREIGN KEY (customer_id)
                                  REFERENCES customer(id) ON DELETE CASCADE
);
