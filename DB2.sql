/*
 Navicat MySQL Data Transfer
 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100137
 Source Host           : localhost:3306
 Source Schema         : db2
 Target Server Type    : MySQL
 Target Server Version : 100137
 File Encoding         : 65001
 Date: 2/12/2020 22:55:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `enroll`;
DROP TABLE IF EXISTS `enroll2`;
DROP TABLE IF EXISTS `assign`;
DROP TABLE IF EXISTS `mentors`;
DROP TABLE IF EXISTS `mentees`;
DROP TABLE IF EXISTS `material`;
DROP TABLE IF EXISTS `meetings`;
DROP TABLE IF EXISTS `time_slot`;
DROP TABLE IF EXISTS `groups`;
DROP TABLE IF EXISTS `admins`;
DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `parents`;
DROP TABLE IF EXISTS `users`;

-- ----------------------------
-- Table structure for users
-- ----------------------------

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for parents
-- ----------------------------

CREATE TABLE `parents` (
  `parent_id` int(11) NOT NULL,
  PRIMARY KEY (`parent_id`),
  CONSTRAINT `parent_user` FOREIGN KEY (`parent_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for students
-- ----------------------------

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `student_parent` (`parent_id`),
  CONSTRAINT `student_user` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_parent` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`parent_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for admins
-- ----------------------------

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  PRIMARY KEY (`admin_id`),
  CONSTRAINT `admins_user` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for groups
-- ----------------------------

CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mentor_grade_req` int(11) DEFAULT NULL,
  `mentee_grade_req` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for time_slot
-- ----------------------------

CREATE TABLE `time_slot` (
  `time_slot_id` int(11) NOT NULL AUTO_INCREMENT,
  `day_of_the_week` varchar(255) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`time_slot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for meetings
-- ----------------------------

CREATE TABLE `meetings` (
  `meet_id` int(11) NOT NULL AUTO_INCREMENT,
  `meet_name` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `time_slot_id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `announcement` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`meet_id`),
  KEY `meeting_group` (`group_id`),
  KEY `meeting_time_slot` (`time_slot_id`),
  CONSTRAINT `meeting_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE CASCADE,
  CONSTRAINT `meeting_time_slot` FOREIGN KEY (`time_slot_id`) REFERENCES `time_slot` (`time_slot_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for material
-- ----------------------------

CREATE TABLE `material` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `assigned_date` date NOT NULL,
  `notes` text,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mentees
-- ----------------------------

CREATE TABLE `mentees` (
  `mentee_id` int(11) NOT NULL,
  PRIMARY KEY (`mentee_id`),
  CONSTRAINT `mentee_student` FOREIGN KEY (`mentee_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mentors
-- ----------------------------

CREATE TABLE `mentors` (
  `mentor_id` int(11) NOT NULL,
  PRIMARY KEY (`mentor_id`),
  CONSTRAINT `mentor_student` FOREIGN KEY (`mentor_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enroll
-- ----------------------------

CREATE TABLE `enroll` (
  `meet_id` int(11) NOT NULL,
  `mentee_id` int(11) NOT NULL,
  PRIMARY KEY (`meet_id`,`mentee_id`),
  KEY `enroll_mentee` (`mentee_id`),
  CONSTRAINT `enroll_mentee` FOREIGN KEY (`mentee_id`) REFERENCES `mentees` (`mentee_id`) ON DELETE CASCADE,
  CONSTRAINT `enroll_meetings` FOREIGN KEY (`meet_id`) REFERENCES `meetings` (`meet_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enroll2
-- ----------------------------

CREATE TABLE `enroll2` (
  `meet_id` int(11) NOT NULL,
  `mentor_id` int(11) NOT NULL,
  PRIMARY KEY (`meet_id`,`mentor_id`),
  KEY `enroll2_mentor` (`mentor_id`),
  CONSTRAINT `enroll2_mentor` FOREIGN KEY (`mentor_id`) REFERENCES `mentors` (`mentor_id`) ON DELETE CASCADE,
  CONSTRAINT `enroll2_meetings` FOREIGN KEY (`meet_id`) REFERENCES `meetings` (`meet_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for assign
-- ----------------------------

CREATE TABLE `assign` (
  `meet_id` int(11) NOT NULL,
  `material_id` int(11) NOT NULL,
  PRIMARY KEY (`meet_id`,`material_id`),
  KEY `assign_material` (`material_id`),
  KEY `assign_meetings` (`meet_id`),
  CONSTRAINT `assign_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE CASCADE,
  CONSTRAINT `assign_meetings` FOREIGN KEY (`meet_id`) REFERENCES `meetings` (`meet_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (id, email, password, name, phone) VALUES (1, 'admin@gmail.com', 'password', 'admin', '0123456789');
INSERT INTO admins (admin_id) VALUES (1);

INSERT INTO users (id, email, password, name, phone) VALUES (2, 'parent0@gmail.com', 'password', 'parent0', '0000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (3, 'parent1@gmail.com', 'password', 'parent1', '1111111111');
INSERT INTO users (id, email, password, name, phone) VALUES (4, 'parent2@gmail.com', 'password', 'parent2', '2222222222');
INSERT INTO users (id, email, password, name, phone) VALUES (5, 'parent3@gmail.com', 'password', 'parent3', '3333333333');
INSERT INTO users (id, email, password, name, phone) VALUES (6, 'parent4@gmail.com', 'password', 'parent4', '4444444444');
INSERT INTO users (id, email, password, name, phone) VALUES (7, 'parent5@gmail.com', 'password', 'parent5', '5555555555');
INSERT INTO users (id, email, password, name, phone) VALUES (8, 'parent6@gmail.com', 'password', 'parent6', '6666666666');
INSERT INTO users (id, email, password, name, phone) VALUES (9, 'parent7@gmail.com', 'password', 'parent7', '7777777777');
INSERT INTO users (id, email, password, name, phone) VALUES (10, 'parent8@gmail.com', 'password', 'parent8', '8888888888');
INSERT INTO users (id, email, password, name, phone) VALUES (11, 'parent9@gmail.com', 'password', 'parent9', '9999999999');

INSERT INTO parents (parent_id) VALUES (2);
INSERT INTO parents (parent_id) VALUES (3);
INSERT INTO parents (parent_id) VALUES (4);
INSERT INTO parents (parent_id) VALUES (5);
INSERT INTO parents (parent_id) VALUES (6);
INSERT INTO parents (parent_id) VALUES (7);
INSERT INTO parents (parent_id) VALUES (8);
INSERT INTO parents (parent_id) VALUES (9);
INSERT INTO parents (parent_id) VALUES (10);
INSERT INTO parents (parent_id) VALUES (11);

INSERT INTO users (id, email, password, name, phone) VALUES (12, 'student00@gmail.com', 'password', 'student00', '0000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (13, 'student01@gmail.com', 'password', 'student01', '0111111111');
INSERT INTO users (id, email, password, name, phone) VALUES (14, 'student02@gmail.com', 'password', 'student02', '0222222222');
INSERT INTO users (id, email, password, name, phone) VALUES (15, 'student10@gmail.com', 'password', 'student10', '1000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (16, 'student11@gmail.com', 'password', 'student11', '1111111111');
INSERT INTO users (id, email, password, name, phone) VALUES (17, 'student20@gmail.com', 'password', 'student20', '2000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (18, 'student30@gmail.com', 'password', 'student30', '3000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (19, 'student40@gmail.com', 'password', 'student40', '4000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (20, 'student50@gmail.com', 'password', 'student50', '5000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (21, 'student51@gmail.com', 'password', 'student51', '5111111111');
INSERT INTO users (id, email, password, name, phone) VALUES (22, 'student60@gmail.com', 'password', 'student60', '6000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (23, 'student70@gmail.com', 'password', 'student70', '7000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (24, 'student80@gmail.com', 'password', 'student80', '8000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (25, 'student90@gmail.com', 'password', 'student90', '9000000000');
INSERT INTO users (id, email, password, name, phone) VALUES (26, 'student91@gmail.com', 'password', 'student91', '9111111111');
INSERT INTO users (id, email, password, name, phone) VALUES (27, 'student92@gmail.com', 'password', 'student91', '9222222222');

INSERT INTO students (student_id, grade, parent_id) VALUES (12, 6, 2);
INSERT INTO students (student_id, grade, parent_id) VALUES (13, 9, 2);
INSERT INTO students (student_id, grade, parent_id) VALUES (14, 7, 2);
INSERT INTO students (student_id, grade, parent_id) VALUES (15, 8, 3);
INSERT INTO students (student_id, grade, parent_id) VALUES (16, 9, 3);
INSERT INTO students (student_id, grade, parent_id) VALUES (17, 6, 4);
INSERT INTO students (student_id, grade, parent_id) VALUES (18, 9, 5);
INSERT INTO students (student_id, grade, parent_id) VALUES (19, 10, 6);
INSERT INTO students (student_id, grade, parent_id) VALUES (20, 11, 7);
INSERT INTO students (student_id, grade, parent_id) VALUES (21, 7, 7);
INSERT INTO students (student_id, grade, parent_id) VALUES (22, 12, 8);
INSERT INTO students (student_id, grade, parent_id) VALUES (23, 9, 9);
INSERT INTO students (student_id, grade, parent_id) VALUES (24, 9, 10);
INSERT INTO students (student_id, grade, parent_id) VALUES (25, 6, 11);
INSERT INTO students (student_id, grade, parent_id) VALUES (26, 12, 11);
INSERT INTO students (student_id, grade, parent_id) VALUES (27, 12, 11);

INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (6, 'Group 6', 6, 9, NULL);
INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (7, 'Group 7', 7, 10, NULL);
INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (8, 'Group 8', 8, 11, NULL);
INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (9, 'Group 9', 9, 12, 6);
INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (10, 'Group 10', 10, NULL, 7);
INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (11, 'Group 11', 11, NULL, 8);
INSERT INTO groups (group_id, name, description, mentor_grade_req, mentee_grade_req) VALUES (12, 'Group 12', 12, NULL, 9);

INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (1, 'Saturday', '16:00:00', '17:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (2, 'Saturday', '16:30:00', '17:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (3, 'Saturday', '17:00:00', '18:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (4, 'Saturday', '17:30:00', '18:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (5, 'Saturday', '18:00:00', '19:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (6, 'Saturday', '18:30:00', '19:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (7, 'Saturday', '19:00:00', '20:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (8, 'Saturday', '19:30:00', '20:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (9, 'Sunday', '16:00:00', '17:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (10, 'Sunday', '16:30:00', '17:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (11, 'Sunday', '17:00:00', '18:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (12, 'Sunday', '17:30:00', '18:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (13, 'Sunday', '18:00:00', '19:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (14, 'Sunday', '18:30:00', '19:30:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (15, 'Sunday', '19:00:00', '20:00:00');
INSERT INTO time_slot (time_slot_id, day_of_the_week, start_time, end_time) VALUES (16, 'Sunday', '19:30:00', '20:30:00');

INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (1, 'Math', '2020-05-02', 8, 9, 'Announcement 1', 6);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (2, 'English', '2020-05-02', 1, 9, 'Announcement 2', 7);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (3, 'Math', '2020-05-02', 3, 9, 'Announcement 3', 8);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (4, 'English', '2020-05-02', 4, 9, 'Announcement 4', 9);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (5, 'English', '2020-05-02', 5, 9, 'Announcement 5', 6);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (6, 'Math', '2020-05-02', 5, 9, 'Announcement 6', 7);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (7, 'English', '2020-05-02', 3, 9, 'Announcement 7', 8);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (8, 'Math', '2020-05-02', 5, 9, 'Announcement 8', 9);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (9, 'Math', '2020-05-03', 11, 9, 'Announcement 9', 6);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (10, 'English', '2020-05-03', 15, 9, 'Announcement 10', 7);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (11, 'Math', '2020-05-03', 9, 9, 'Announcement 11', 8);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (12, 'English', '2020-05-03', 10, 9, 'Announcement 12', 9);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (13, 'English', '2020-05-09', 5, 9, 'Announcement 1', 6);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (14, 'Math', '2020-05-09', 6, 9, 'Announcement 2', 7);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (15, 'English', '2020-05-09', 2, 9, 'Announcement 3', 8);
INSERT INTO meetings (meet_id, meet_name, date, time_slot_id, capacity, announcement, group_id) VALUES (16, 'Math', '2020-05-09', 1, 9, 'Announcement 4', 9);

INSERT INTO mentees(mentee_id) VALUES (12);
INSERT INTO mentees(mentee_id) VALUES (13);
INSERT INTO mentees(mentee_id) VALUES (14);
INSERT INTO mentees(mentee_id) VALUES (15);
INSERT INTO mentees(mentee_id) VALUES (17);
INSERT INTO mentees(mentee_id) VALUES (18);
INSERT INTO mentees(mentee_id) VALUES (21);
INSERT INTO mentees(mentee_id) VALUES (23);
INSERT INTO mentees(mentee_id) VALUES (24);
INSERT INTO mentees(mentee_id) VALUES (25);

INSERT INTO mentors(mentor_id) VALUES (13);
INSERT INTO mentors(mentor_id) VALUES (16);
INSERT INTO mentors(mentor_id) VALUES (18);
INSERT INTO mentors(mentor_id) VALUES (19);
INSERT INTO mentors(mentor_id) VALUES (20);
INSERT INTO mentors(mentor_id) VALUES (22);
INSERT INTO mentors(mentor_id) VALUES (23);
INSERT INTO mentors(mentor_id) VALUES (24);
INSERT INTO mentors(mentor_id) VALUES (26);
INSERT INTO mentors(mentor_id) VALUES (27);

INSERT INTO enroll(meet_id, mentee_id) VALUES (1,12);
INSERT INTO enroll(meet_id, mentee_id) VALUES (2,12);
INSERT INTO enroll(meet_id, mentee_id) VALUES (2,14);
INSERT INTO enroll(meet_id, mentee_id) VALUES (2,21);
INSERT INTO enroll(meet_id, mentee_id) VALUES (3,12);
INSERT INTO enroll(meet_id, mentee_id) VALUES (3,14);
INSERT INTO enroll(meet_id, mentee_id) VALUES (3,15);
INSERT INTO enroll(meet_id, mentee_id) VALUES (3,25);
INSERT INTO enroll(meet_id, mentee_id) VALUES (4,13);
INSERT INTO enroll(meet_id, mentee_id) VALUES (4,15);
INSERT INTO enroll(meet_id, mentee_id) VALUES (4,17);
INSERT INTO enroll(meet_id, mentee_id) VALUES (4,18);
INSERT INTO enroll(meet_id, mentee_id) VALUES (4,23);
INSERT INTO enroll(meet_id, mentee_id) VALUES (5,25);
INSERT INTO enroll(meet_id, mentee_id) VALUES (8,13);
INSERT INTO enroll(meet_id, mentee_id) VALUES (8,17);
INSERT INTO enroll(meet_id, mentee_id) VALUES (8,21);
INSERT INTO enroll(meet_id, mentee_id) VALUES (8,23);
INSERT INTO enroll(meet_id, mentee_id) VALUES (11,15);
INSERT INTO enroll(meet_id, mentee_id) VALUES (11,17);
INSERT INTO enroll(meet_id, mentee_id) VALUES (12,13);
INSERT INTO enroll(meet_id, mentee_id) VALUES (12,18);
INSERT INTO enroll(meet_id, mentee_id) VALUES (12,24);
INSERT INTO enroll(meet_id, mentee_id) VALUES (12,25);
INSERT INTO enroll(meet_id, mentee_id) VALUES (13,17);

INSERT INTO enroll2(meet_id, mentor_id) VALUES (1,13);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (1,16);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (1,27);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (2,27);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (3,20);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (3,26);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (3,27);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (4,22);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (4,26);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (4,27);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (5,13);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (5,18);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (5,19);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (5,24);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (6,19);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (6,20);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (8,22);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (9,13);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (9,16);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (9,18);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (9,23);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (9,26);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (11,20);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (12,22);
INSERT INTO enroll2(meet_id, mentor_id) VALUES (13,13);

INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (1,'Math1','Scott Gordon','Math','www.Math1.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (2,'English1','Joe Gelsomini','English','www.English1.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (3,'Math2','Scott Gordon','Math','www.Math2.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (4,'English2','Joe Gelsomini','English','www.English2.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (5,'Math3','Scott Gordon','Math','www.Math3.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (6,'English3','Joe Gelsomini','English','www.English3.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (7,'Math4','Scott Gordon','Math','www.Math4.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (8,'English4','Joe Gelsomini','English','www.English4.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (9,'Math5','Scott Gordon','Math','www.Math5.com','2020-03-30','Study the material');
INSERT INTO material(material_id, title, author, type, url, assigned_date, notes) VALUES (10,'English5','Joe Gelsomini','English','www.English5.com','2020-03-30','Study the material');

INSERT INTO assign(meet_id, material_id) VALUES (1,1);
INSERT INTO assign(meet_id, material_id) VALUES (2,2);
INSERT INTO assign(meet_id, material_id) VALUES (3,3);
INSERT INTO assign(meet_id, material_id) VALUES (4,4);
INSERT INTO assign(meet_id, material_id) VALUES (5,6);
INSERT INTO assign(meet_id, material_id) VALUES (6,5);
INSERT INTO assign(meet_id, material_id) VALUES (7,8);
INSERT INTO assign(meet_id, material_id) VALUES (8,7);
INSERT INTO assign(meet_id, material_id) VALUES (9,9);
INSERT INTO assign(meet_id, material_id) VALUES (10,10);
INSERT INTO assign(meet_id, material_id) VALUES (11,1);
INSERT INTO assign(meet_id, material_id) VALUES (12,2);
INSERT INTO assign(meet_id, material_id) VALUES (13,4);
INSERT INTO assign(meet_id, material_id) VALUES (14,3);
INSERT INTO assign(meet_id, material_id) VALUES (15,6);
INSERT INTO assign(meet_id, material_id) VALUES (16,5);