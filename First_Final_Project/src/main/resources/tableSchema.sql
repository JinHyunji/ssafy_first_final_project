create database first_final_project;

use first_final_project;

CREATE TABLE user (
    `user_id` VARCHAR(20) PRIMARY KEY,
    `password` VARCHAR(20) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    `email` VARCHAR(50) NOT NULL
);

SELECT * FROM USER;

CREATE TABLE alarm (
	`alarm_id` int PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(20),
    `start_time` varchar(20) not null,
    `end_time` varchar(20) not null,
    `term` int not null,
    `cycle` varchar(7) not null,
    `exer_type` VARCHAR(20) not null,
    `video_id` VARCHAR(20),
    `img` VARCHAR(100),
    `user_id` varchar(20) not null,
    foreign key(user_id) REFERENCES user (user_id)
);

select * from alarm;

create table exer_info (
	`info_id` int PRIMARY KEY AUTO_INCREMENT,
    `content` text,
    `reference` varchar(50),
    `link` varchar(100)
);

select * from exer_info;

create table `template` (
	`temp_id` int PRIMARY KEY AUTO_INCREMENT,
	`exer_type` VARCHAR(20) not null,
    `video_id` VARCHAR(20),
	`img` VARCHAR(100),
    `count` int default 0
);

select * from template;