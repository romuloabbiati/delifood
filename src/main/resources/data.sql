INSERT INTO restaurant (id, post_code, place, name) VALUES
(1L, 'SE17 2TQ', 'Old York Square', 'Daffodil Mulligan'),
(2L, 'SW17 0RT', 'Tooting Bec Park', 'Lamberts');

INSERT INTO customer (id, post_code, place, name) VALUES
(1L, 'SW17 0QL', 'Flat 48B', 'Romulo Abbiati');

INSERT INTO product (id, availability, name, unit_value, restaurant_id) VALUES
(1L, true, 'Eggs Florentine', 7.0, 1L),
(2L, true, 'Eggs Benedict', 7.5, 1L),
(3L, true, 'Roasted Cod with Asparagus', 10.0, 2L);

INSERT INTO bag (id, payment_type, closed, total_price, customer_id) VALUES
(1L, 0, false, 0.0, 1L);