

create table large_category (
id int primary key auto_increment,
name varchar(20)
);


create table medium_category (
id int primary key auto_increment,
parent_id int,
name varchar(20),
foreign key medium_category(parent_id) references large_category(id)
);

insert into large_category (name) value ('거실');
insert into large_category (name) value ('침실');
insert into large_category (name) value ('주방');
insert into large_category (name) value ('드레스룸');

select * from medium_category;

insert into medium_category (parent_id, name) values ( 1 , '소파' ); -- 소파, 테이블, 의자, tv콘솔
insert into medium_category (parent_id, name) values ( 1 , '테이블' );
insert into medium_category (parent_id, name) values ( 1 , '의자' );
insert into medium_category (parent_id, name) values ( 1 , 'tv콘솔' );

insert into medium_category (parent_id, name) values ( 2 , '침대' );
insert into medium_category (parent_id, name) values ( 2 , '매트리스' );
insert into medium_category (parent_id, name) values ( 2 , '협탁' );

insert into medium_category (parent_id, name) values ( 3 , '식탁');
insert into medium_category (parent_id, name) values ( 3 , '의자');

insert into medium_category (parent_id, name) values ( 4 , '행거');
insert into medium_category (parent_id, name) values ( 4 , '수납장');
insert into medium_category (parent_id, name) values ( 4 , '화장대');

CREATE table all_category (
id int not null primary key auto_increment,
lg_ct_id int not null,
md_ct_id int not null,
foreign key (lg_ct_id) references large_category(id) on UPDATE cascade,
foreign key (md_ct_id) references medium_category(id) on UPDATE cascade
);

CREATE table product_info(
id int not null primary key auto_increment,
category_id int not null references all_category(id) on UPDATE cascade,
reg_id varchar(100) not null references manager(id),
name VARCHAR(200) not null,
price int not null,
detail TEXT null,
discount int not null,
upload_date timestamp,
view_count int not null default 0,
etc1 varchar(100) null,
etc2 varchar(100) null
);

CREATE table product_option(
id int not null primary key auto_increment,
product_id int not null references product_info(id) on delete cascade,
name varchar(100) not null,
surcharge int not null default 0,
image_url TEXT null
);

create table product_images(
id int not null primary key auto_increment,
product_id int not null references product_info(id) on delete cascade,
image_url TEXT not null
);
