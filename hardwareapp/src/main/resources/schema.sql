
create table if not exists hardware (
    id long auto_increment not null,
    code varchar(10) unique not null,
    product_name varchar(50) not null,
    price  decimal(10,2)  not null,
    product_type varchar(10) not null,
    available_num int not null,
    primary key (id)
    );

create table if not exists review (
  id long auto_increment not null,
  title varchar(70) not null,
  text varchar(200) not null,
  rating int not null,
  code_hardware varchar(10),
  primary key (id),
  foreign key (code_hardware) references hardware(code) on delete cascade
);

create table if not exists user (
    id identity,
    username varchar(100) not null unique,
    password varchar(1000) not null
    );
create table if not exists authority (
     id identity,
     authority_name varchar(100) not null unique
    );
create table if not exists user_authority (
  user_id bigint not null,
  authority_id bigint not null,
  constraint fk_user foreign key (user_id) references user(id),
  constraint fk_authority foreign key (authority_id) references authority(id)
);