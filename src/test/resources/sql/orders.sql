insert into orders(customer_id, created_at, last_updated_at, version) values (1, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);
insert into orders(customer_id, created_at, last_updated_at, version) values (2, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);
insert into orders(customer_id, created_at, last_updated_at, version) values (3, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);

insert into order_items(order_id, product_id, amount, unit_price, created_at, last_updated_at, version) values (1, 1, 3, 100, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);
insert into order_items(order_id, product_id, amount, unit_price, created_at, last_updated_at, version) values (1, 2, 3, 150, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);

insert into order_items(order_id, product_id, amount, unit_price, created_at, last_updated_at, version) values (2, 2, 3, 150, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);
insert into order_items(order_id, product_id, amount, unit_price, created_at, last_updated_at, version) values (2, 3, 3, 200, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);

insert into order_items(order_id, product_id, amount, unit_price, created_at, last_updated_at, version) values (3, 3, 3, 200, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);
insert into order_items(order_id, product_id, amount, unit_price, created_at, last_updated_at, version) values (3, 1, 3, 100, '2020-07-24 04:00', '2020-07-24 04:00:00', 0);