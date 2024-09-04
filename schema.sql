create database ecommerceOrderManagementSystem;
use ecommerceOrderManagementSystem;

create table products(
	product_id int primary key auto_increment,
    name varchar(50) not null,
    description text,
    price decimal(10,2) not null,
    quantity_in_stock int not null
);

create table customers(
	customer_id int primary key auto_increment,
    name varchar(50) not null,
    email varchar(50) unique,
    phone bigint not null,
    address text,
    constraint checkPhone check(phone>=1000000000 and phone<=99999999999),
    constraint checkEmail check(email like '%@%.%')
);
create table orders(
	order_id int primary key auto_increment,
    customer_id int not null,
    order_date timestamp default current_timestamp,
    total_amount decimal(10,2) not null,
    status enum('pending','confirmed','shipped','delivered','cancelled') default 'pending',
    foreign key(customer_id) references customers(customer_id) on delete cascade
);

create table order_items(
	order_item_id int primary key auto_increment,
    order_id int not null,
    product_id int not null,
    quantity int not null,
    price decimal(10,2) not null,
	foreign key(order_id) references orders(order_id) on delete cascade,
    foreign key(product_id) references products(product_id) on delete cascade
);
