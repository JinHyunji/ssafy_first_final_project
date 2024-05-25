DROP DATABASE IF EXISTS first_final_project;
CREATE DATABASE first_final_project;

USE first_final_project;

CREATE TABLE `user` (
    `user_id` VARCHAR(20) PRIMARY KEY,
    `password` VARCHAR(20) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    `email` VARCHAR(50) NOT NULL
);

CREATE TABLE alarm (
	`alarm_id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(20),
    `start_time` VARCHAR(20) NOT NULL,
    `end_time` VARCHAR(20) NOT NULL,
    `term` INT NOT NULL,
    `cycle` VARCHAR(7) NOT NULL,
    `exer_type` VARCHAR(20) NOT NULL,
    `video_id` VARCHAR(20),
    `img` VARCHAR(100),
    `user_id` VARCHAR(20) NOT NULL,
    `activate` VARCHAR(5) DEFAULT 'true',
    FOREIGN KEY(user_id) REFERENCES user (user_id)
);

CREATE TABLE `template` (
	`temp_id` INT PRIMARY KEY,
	`exer_type` VARCHAR(20) NOT NULL,
    `video_id` VARCHAR(20),
	`img` VARCHAR(100),
    `count` INT DEFAULT 0
);

INSERT INTO template(temp_id, exer_type, video_id) VALUES 
(1, '기타', ''),
(2, '목', 'KurrgZEGhrs'),
(3, '손목', 'PE1fnYPZno0'),
(4, '복근', 'mUnSpfItRf0'),
(5, '호흡', 'dZewQEbQQM0');
