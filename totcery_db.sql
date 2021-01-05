drop database totcery_db;
drop user harsha;

create user harsha with password 'admin123';
create database totcery_db with template=template0 owner=harsha;
\connect totcery_db;

alter default privileges grant all on tables to harsha;
alter default privileges grant all on sequences to harsha;

create table tot_users(
user_id integer primary key not null,
full_name varchar(20) not null,
email varchar(30) not null,
password text not null,
mobile_number varchar(30) not null,
user_type varchar(30) not null
);

create table user_address(
address_id integer primary key not null,
user_id integer not null,
state varchar(20) not null,
city varchar(20) not null,
area varchar(50) not null,
society_name varchar(30) not null,
respective_block varchar(30) not null,
flat_number varchar(40) not null,
parent_address varchar(10)
);
alter table user_address add constraint add_users_fk
foreign key (user_id) references tot_users(user_id)


create table products(
product_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
discount_price integer not null,
product_name varchar(20) not null,
original_price integer not null,
location_ref varchar(30) not null
);
alter table products add constraint pro_users_fk
foreign key (user_id) references tot_users(user_id);

create table tot_transactions(
transaction_id integer primary key not null,
product_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(50),
transaction_date bigint not null
);
alter table tot_transactions add constraint trans_pro_fk
foreign key (product_id) references tot_products(product_id);
alter table tot_transactions add constraint trans_users_fk
foreign key (user_id) references tot_users(user_id);

create sequence tot_users_seq increment 1 start 1;
create sequence tot_products_seq increment 1 start 1;
create sequence tot_transactions_seq increment 1 start 1000
