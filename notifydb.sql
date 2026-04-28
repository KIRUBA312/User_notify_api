create database notifydb;
use notifydb;

create table user(
	id bigint auto_increment primary key,
    username varchar(50),
    email varchar(100)
);

create table notification_preference(
	id bigint auto_increment primary key,
    user_id bigint,
    email_enabled boolean,
    sms_enabled boolean,
    push_enabled boolean,
    foreign key(user_id) references user(id)
);

create table notification(
	id bigint auto_increment primary key,
    user_id bigint,
    message varchar(255),
    type varchar(20),
    sent_at timestamp,
    foreign key (user_id) references user(id)
);

INSERT INTO user (username, email) VALUES ('admin', 'admin@gmail.com');
show tables;
select * from notification;
select * from notification_preference;
select * from user;
DELETE FROM notification_preference WHERE id = 7;
alter table notification add status varchar(20);