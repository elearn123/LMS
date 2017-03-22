-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: lmsapplication
-- ------------------------------------------------------
-- Server version	5.6.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarm_master`
--

DROP TABLE IF EXISTS `alarm_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alarm_master` (
  `alarm_master_id` int(10) NOT NULL AUTO_INCREMENT,
  `alarm_name` varchar(30) DEFAULT NULL,
  `time` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`alarm_master_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `all_preview_details`
--

DROP TABLE IF EXISTS `all_preview_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `all_preview_details` (
  `preview_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(25) NOT NULL,
  `preview_type` varchar(11) DEFAULT NULL,
  `preview_item_id` varchar(11) NOT NULL,
  `preview_date` datetime NOT NULL,
  PRIMARY KEY (`preview_details_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `anno_mgmt`
--

DROP TABLE IF EXISTS `anno_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anno_mgmt` (
  `notice_heading` varchar(25) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `body` longblob,
  `attachments` varchar(100) DEFAULT NULL,
  `postedby` varchar(25) DEFAULT NULL,
  `postedon` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_heading`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_defination`
--

DROP TABLE IF EXISTS `assessment_defination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_defination` (
  `assessment_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `no_of_questions` int(10) DEFAULT NULL,
  `degree_of_difficulty` varchar(20) NOT NULL,
  `question_type` varchar(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `created_by` varchar(25) NOT NULL,
  `generated_assessment` longblob,
  `asmt_generation_status` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`assessment_id`,`topic_id`,`degree_of_difficulty`,`question_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_group_registration`
--

DROP TABLE IF EXISTS `assessment_group_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_group_registration` (
  `group_id` varchar(100) NOT NULL,
  `assessment_id` int(11) NOT NULL,
  `date_registration` datetime DEFAULT NULL,
  `registered_by` varchar(100) DEFAULT NULL,
  `available_time` time DEFAULT NULL,
  `date_availability` datetime DEFAULT NULL,
  `registration_valid_till` datetime DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`group_id`,`assessment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_item_data`
--

DROP TABLE IF EXISTS `assessment_item_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_item_data` (
  `item_data_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_data_record_id` int(11) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `result_status` varchar(20) NOT NULL,
  `result_data` blob,
  `item_marks` int(10) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`item_data_record_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_management`
--

DROP TABLE IF EXISTS `assessment_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_management` (
  `assessment_id` int(11) NOT NULL AUTO_INCREMENT,
  `assessment_code` varchar(10) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `assessment_type` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `duration` int(10) DEFAULT NULL,
  `pass_marks` int(10) DEFAULT NULL,
  `question_perpage` int(10) DEFAULT NULL,
  `max_no_test_taken` int(10) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `asmt_generation_type` varchar(11) DEFAULT NULL,
  `refresh_on` datetime DEFAULT NULL,
  `refresh_by` varchar(60) DEFAULT NULL,
  `download_on` datetime DEFAULT NULL,
  `download_by` varchar(60) DEFAULT NULL,
  `downloaded_asmt` longblob,
  PRIMARY KEY (`assessment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_progress_status`
--

DROP TABLE IF EXISTS `assessment_progress_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_progress_status` (
  `test_id` int(11) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `assessment_id` int(10) DEFAULT NULL,
  `ques_per_page` int(5) DEFAULT NULL,
  `startdate` date NOT NULL,
  `starttime` time NOT NULL,
  `submitdate` date DEFAULT NULL,
  `submittime` time DEFAULT NULL,
  `restartdate` date DEFAULT NULL,
  `restarttime` time DEFAULT NULL,
  `time_left` int(10) DEFAULT NULL,
  `saved_state` blob,
  `submition_status` varchar(20) NOT NULL,
  `instanceno` int(5) DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_request_result_processing`
--

DROP TABLE IF EXISTS `assessment_request_result_processing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_request_result_processing` (
  `assessment_id` int(11) NOT NULL,
  `result_processing_status` varchar(50) DEFAULT NULL,
  `exam_completion_date` datetime DEFAULT NULL,
  `result_processing_request_date` datetime DEFAULT NULL,
  `result_processing_date` datetime DEFAULT NULL,
  `no_of_candidates_registered` int(11) NOT NULL DEFAULT '0',
  `no_of_candidates_appeared` int(11) NOT NULL DEFAULT '0',
  `no_of_candidates_compleated` int(11) NOT NULL DEFAULT '0',
  `schedule_start_date` date DEFAULT NULL,
  `schedule_start_time` time DEFAULT NULL,
  `schedule_end_date` date DEFAULT NULL,
  `schedule_end_time` time DEFAULT NULL,
  PRIMARY KEY (`assessment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_section_data`
--

DROP TABLE IF EXISTS `assessment_section_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_section_data` (
  `section_data_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `assessment_data_record_id` int(11) NOT NULL,
  `section_id` varchar(25) NOT NULL,
  `total_marks` int(10) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `no_of_questions` int(10) DEFAULT NULL,
  `no_of_questions_attempted` int(10) DEFAULT NULL,
  `no_of_correct_ans` int(10) DEFAULT NULL,
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  PRIMARY KEY (`section_data_record_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_usage`
--

DROP TABLE IF EXISTS `assessment_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_usage` (
  `student_id` varchar(25) NOT NULL,
  `unit_id` varchar(20) NOT NULL,
  `assessment_id` varchar(20) NOT NULL,
  `section_id` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `instance` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_user_registration`
--

DROP TABLE IF EXISTS `assessment_user_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_user_registration` (
  `student_id` varchar(100) NOT NULL,
  `assessment_id` int(11) NOT NULL,
  `date_registration` datetime DEFAULT NULL,
  `registered_by` varchar(100) DEFAULT NULL,
  `available_time` time DEFAULT NULL,
  `date_availability` datetime DEFAULT NULL,
  `registration_valid_till` datetime DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`assessment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_usermodel`
--

DROP TABLE IF EXISTS `assessment_usermodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_usermodel` (
  `assessment_data_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(25) NOT NULL,
  `unit_id` varchar(25) NOT NULL,
  `sco_id` varchar(25) NOT NULL,
  `assessment_id` varchar(25) NOT NULL,
  `assessment_type` varchar(25) NOT NULL,
  `assessment_date` date DEFAULT NULL,
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  PRIMARY KEY (`assessment_data_record_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `assignment_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) DEFAULT NULL,
  `submission_last_date` datetime DEFAULT NULL,
  `start_avail_date` datetime DEFAULT NULL,
  `end_avail_date` datetime DEFAULT NULL,
  `description` varchar(90) DEFAULT NULL,
  `lateflag` char(3) DEFAULT NULL,
  `late_allowed_till` varchar(20) DEFAULT NULL,
  `penalty_for_late` varchar(25) DEFAULT NULL,
  `full_mark` int(11) DEFAULT '0',
  `pass_mark` int(11) DEFAULT '0',
  PRIMARY KEY (`assignment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment_confirmation`
--

DROP TABLE IF EXISTS `assignment_confirmation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_confirmation` (
  `student_id` varchar(25) NOT NULL,
  `course_id` varchar(25) NOT NULL,
  `assignment_id` varchar(25) NOT NULL,
  `doc_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `confirm_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`,`assignment_id`,`doc_id`),
  UNIQUE KEY `doc_id` (`doc_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment_course_association`
--

DROP TABLE IF EXISTS `assignment_course_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_course_association` (
  `assignment_id` mediumint(9) NOT NULL,
  `course_id` mediumint(9) NOT NULL,
  PRIMARY KEY (`assignment_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment_document`
--

DROP TABLE IF EXISTS `assignment_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_document` (
  `assignment_id` mediumint(9) NOT NULL,
  `document_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `document_name` varchar(50) DEFAULT NULL,
  `size` varchar(25) DEFAULT NULL,
  `date_uploaded` datetime DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`document_id`,`assignment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment_returndoc`
--

DROP TABLE IF EXISTS `assignment_returndoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_returndoc` (
  `student_id` varchar(25) NOT NULL,
  `course_id` varchar(25) NOT NULL,
  `assignment_id` varchar(25) NOT NULL,
  `returndoc` varchar(90) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment_status`
--

DROP TABLE IF EXISTS `assignment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_status` (
  `student_id` varchar(25) NOT NULL,
  `course_id` varchar(25) NOT NULL,
  `assignment_id` varchar(25) NOT NULL,
  `status` varchar(12) DEFAULT NULL,
  `feedback` blob,
  `lock_status` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`,`assignment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment_student_association`
--

DROP TABLE IF EXISTS `assignment_student_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_student_association` (
  `student_id` varchar(25) NOT NULL,
  `course_id` mediumint(9) NOT NULL,
  `assignment_id` mediumint(9) NOT NULL,
  `document_name` varchar(70) NOT NULL,
  `doc_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `submission_date` datetime DEFAULT NULL,
  `size` varchar(25) DEFAULT NULL,
  `confirm_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`,`assignment_id`,`document_name`,`doc_id`),
  UNIQUE KEY `doc_id` (`doc_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batchmanagement`
--

DROP TABLE IF EXISTS `batchmanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batchmanagement` (
  `batchid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) DEFAULT NULL,
  `courseid` varchar(25) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  PRIMARY KEY (`batchid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batchuserinfo`
--

DROP TABLE IF EXISTS `batchuserinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batchuserinfo` (
  `batchid` varchar(25) NOT NULL,
  `userid` varchar(25) NOT NULL,
  `applicationno` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`batchid`,`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_course_asso`
--

DROP TABLE IF EXISTS `book_course_asso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_course_asso` (
  `course` mediumint(9) NOT NULL,
  `gradebook` varchar(50) NOT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`course`,`gradebook`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_item_mgmt`
--

DROP TABLE IF EXISTS `book_item_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_item_mgmt` (
  `book_id` mediumint(9) NOT NULL,
  `category` mediumint(9) NOT NULL,
  `item` mediumint(9) NOT NULL,
  `schedule_date` date DEFAULT NULL,
  `weightage` varchar(50) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`category`,`item`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_mgmt`
--

DROP TABLE IF EXISTS `book_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_mgmt` (
  `book_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) NOT NULL,
  `scale` varchar(100) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bulletin_board`
--

DROP TABLE IF EXISTS `bulletin_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bulletin_board` (
  `student_id` varchar(25) DEFAULT NULL,
  `heading` varchar(100) DEFAULT NULL,
  `notice_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `attachments` varchar(100) DEFAULT NULL,
  `body` longblob,
  `posted_on` datetime DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Active',
  `group_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar_authorization`
--

DROP TABLE IF EXISTS `calendar_authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_authorization` (
  `calendar_id` int(11) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  `usertype` varchar(10) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`student_id`,`calendar_id`),
  KEY `index_calendar_authorization` (`calendar_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar_events`
--

DROP TABLE IF EXISTS `calendar_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_id` int(11) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `date_modified` datetime NOT NULL,
  `createdby` varchar(25) NOT NULL,
  `public` varchar(10) NOT NULL DEFAULT 'F',
  `startdate` date NOT NULL,
  `starttime` time DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `calendar_desc` varchar(255) NOT NULL,
  `repeat_type` varchar(10) NOT NULL DEFAULT 'N',
  `reminder_type` varchar(10) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`event_id`),
  KEY `calendar_id` (`calendar_id`),
  KEY `event` (`calendar_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar_group_association`
--

DROP TABLE IF EXISTS `calendar_group_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_group_association` (
  `group_id` mediumint(9) NOT NULL,
  `calendar_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`calendar_id`),
  KEY `index_calendar_group_association` (`calendar_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar_keywords`
--

DROP TABLE IF EXISTS `calendar_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_keywords` (
  `calendar_id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_name` varchar(100) NOT NULL,
  `createdby` varchar(25) NOT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `date_modified` datetime NOT NULL,
  `status` varchar(25) DEFAULT NULL,
  `cal_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`calendar_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar_reminder`
--

DROP TABLE IF EXISTS `calendar_reminder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_reminder` (
  `event_id` int(11) NOT NULL,
  `reminder_id` int(11) NOT NULL AUTO_INCREMENT,
  `reminder_f` int(11) DEFAULT NULL,
  `reminder_e` int(11) DEFAULT NULL,
  PRIMARY KEY (`reminder_id`),
  KEY `reminder` (`event_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar_repeat`
--

DROP TABLE IF EXISTS `calendar_repeat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_repeat` (
  `repeat_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `repeating_every` int(11) DEFAULT NULL,
  `repeating_day` int(11) DEFAULT NULL,
  `repeating_month` int(11) DEFAULT NULL,
  PRIMARY KEY (`repeat_id`),
  KEY `repeat` (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `catalog_details`
--

DROP TABLE IF EXISTS `catalog_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_details` (
  `catalog_name` blob,
  `cat_description` longblob,
  `created_by` varchar(40) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `cat_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cat_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `catalog_entry_details`
--

DROP TABLE IF EXISTS `catalog_entry_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_entry_details` (
  `catalog_name` blob,
  `entry_name` varchar(200) DEFAULT NULL,
  `parent_name` varchar(40) DEFAULT NULL,
  `cat_description` longblob,
  `cat_id` mediumint(9) DEFAULT NULL,
  `entryid` int(9) NOT NULL AUTO_INCREMENT,
  `entity_type` varchar(7) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  PRIMARY KEY (`entryid`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `catalog_entry_entity`
--

DROP TABLE IF EXISTS `catalog_entry_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_entry_entity` (
  `catalog_name` blob,
  `entry_name` blob,
  `entity_type` varchar(7) DEFAULT NULL,
  `entity_name` varchar(40) DEFAULT NULL,
  `entity_description` longblob,
  `asscciation_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(40) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `entryid` int(9) NOT NULL DEFAULT '0',
  `cat_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`asscciation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category_management`
--

DROP TABLE IF EXISTS `category_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_management` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category_mgmt`
--

DROP TABLE IF EXISTS `category_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_mgmt` (
  `category_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `collection_course`
--

DROP TABLE IF EXISTS `collection_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection_course` (
  `course_id` varchar(25) NOT NULL,
  `collection_id` varchar(30) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competency_level`
--

DROP TABLE IF EXISTS `competency_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competency_level` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `descript` text,
  `model_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competency_model`
--

DROP TABLE IF EXISTS `competency_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competency_model` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `descript` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `confirmation_requisition`
--

DROP TABLE IF EXISTS `confirmation_requisition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `confirmation_requisition` (
  `id` varchar(25) NOT NULL,
  `user_id` varchar(25) NOT NULL,
  `access_allowed_till` datetime DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `entity_type` varchar(10) NOT NULL,
  `confirmation_requisition_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`confirmation_requisition_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `confirmation_result`
--

DROP TABLE IF EXISTS `confirmation_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `confirmation_result` (
  `student_id` varchar(25) NOT NULL,
  `course_id` varchar(25) NOT NULL,
  `topic_id` varchar(50) NOT NULL,
  `topic_name` varchar(50) NOT NULL,
  `assessment_name` varchar(50) NOT NULL,
  `confirmation_id` int(15) NOT NULL,
  `total_marks` int(10) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `pass_marks` int(10) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`confirmation_id`),
  KEY `index_essay_type_questions` (`student_id`,`confirmation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `content_management_object`
--

DROP TABLE IF EXISTS `content_management_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content_management_object` (
  `unit_id` mediumint(9) NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `upload_date` datetime DEFAULT NULL,
  `upload_by` varchar(25) NOT NULL,
  `content` longblob,
  `size` int(15) DEFAULT NULL,
  `fspath` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`unit_id`,`file_name`),
  KEY `unit_idd` (`upload_by`,`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_collection_asso`
--

DROP TABLE IF EXISTS `course_collection_asso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_collection_asso` (
  `collection_id` varchar(30) NOT NULL,
  `course_id` varchar(25) NOT NULL,
  `associatedby` varchar(40) DEFAULT NULL,
  `dateassociated` datetime DEFAULT NULL,
  PRIMARY KEY (`collection_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_collection_mgmt`
--

DROP TABLE IF EXISTS `course_collection_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_collection_mgmt` (
  `collection_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `collection_name` varchar(35) NOT NULL,
  `description` blob,
  `modifiedby` varchar(40) DEFAULT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`collection_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_component`
--

DROP TABLE IF EXISTS `course_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_component` (
  `Course_Id` varchar(50) NOT NULL,
  `Course_syllabus` varchar(10) NOT NULL,
  `Course_schedule` varchar(10) NOT NULL,
  `Course_resource` varchar(10) NOT NULL,
  `Course_assignmnt` varchar(10) NOT NULL,
  `Course_announcemnt` varchar(10) NOT NULL,
  `Course_gradebook` varchar(10) NOT NULL,
  PRIMARY KEY (`Course_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_datail_skin_management`
--

DROP TABLE IF EXISTS `course_datail_skin_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_datail_skin_management` (
  `Course_skin_id` varchar(100) NOT NULL,
  `Course_div_id` varchar(100) NOT NULL,
  `Course_style_id` varchar(100) NOT NULL,
  PRIMARY KEY (`Course_skin_id`,`Course_div_id`,`Course_style_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_definition`
--

DROP TABLE IF EXISTS `course_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_definition` (
  `course_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) NOT NULL,
  `type` varchar(25) NOT NULL,
  `session` varchar(25) DEFAULT NULL,
  `sdate` datetime DEFAULT NULL,
  `edate` datetime DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  `description` blob,
  `cpoints` varchar(20) DEFAULT NULL,
  `ttimes` varchar(20) DEFAULT NULL,
  `cost` varchar(20) DEFAULT NULL,
  `intructor` varchar(50) DEFAULT NULL,
  `intructor1` varchar(50) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_name1` varchar(100) DEFAULT NULL,
  `self_regis` varchar(10) DEFAULT NULL,
  `confirmation_reqd` varchar(10) DEFAULT NULL,
  `confirmed_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `course_id` (`course_id`),
  KEY `index_course_definition` (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_detail_style_management`
--

DROP TABLE IF EXISTS `course_detail_style_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_detail_style_management` (
  `Course_style_id` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL DEFAULT '',
  `property` varchar(100) NOT NULL,
  `property_value` varchar(100) DEFAULT NULL,
  `Course_skin_id` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`Course_style_id`,`property`,`Course_skin_id`,`category`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_instructor`
--

DROP TABLE IF EXISTS `course_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_instructor` (
  `instructor_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `fname` varchar(25) NOT NULL,
  `mname` varchar(25) DEFAULT NULL,
  `ename` varchar(25) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `alternatemail` varchar(25) DEFAULT NULL,
  `office_no` varchar(15) DEFAULT NULL,
  `residence_no` varchar(15) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `stime` varchar(15) DEFAULT NULL,
  `room` varchar(25) DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `affiliation` blob,
  PRIMARY KEY (`instructor_id`),
  KEY `index_course_instructor` (`instructor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_instructor_association`
--

DROP TABLE IF EXISTS `course_instructor_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_instructor_association` (
  `course_id` int(10) NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `show_association` varchar(10) DEFAULT NULL,
  `association_label` varchar(200) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_learner_login_info`
--

DROP TABLE IF EXISTS `course_learner_login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_learner_login_info` (
  `student_id` varchar(25) DEFAULT NULL,
  `course_id` varchar(25) DEFAULT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `topic_id` varchar(15) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_location`
--

DROP TABLE IF EXISTS `course_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_location` (
  `location_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `index_course_location` (`location_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_material`
--

DROP TABLE IF EXISTS `course_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_material` (
  `material_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `course_id` mediumint(9) NOT NULL,
  `material_name` varchar(50) DEFAULT NULL,
  `mtype` varchar(25) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `material` varchar(100) DEFAULT NULL,
  `uploadedby` varchar(25) DEFAULT NULL,
  `uploadedon` datetime DEFAULT NULL,
  `description` blob,
  PRIMARY KEY (`material_id`,`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_registration_info`
--

DROP TABLE IF EXISTS `course_registration_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_registration_info` (
  `student_id` varchar(25) NOT NULL DEFAULT '',
  `course_id` varchar(25) NOT NULL DEFAULT '',
  `draft_no` varchar(15) NOT NULL DEFAULT '',
  `amount` varchar(25) DEFAULT NULL,
  `dated` datetime DEFAULT NULL,
  `drawnon` varchar(50) DEFAULT NULL,
  `registration_no` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`,`draft_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_schedule`
--

DROP TABLE IF EXISTS `course_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_schedule` (
  `schedule_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `course_id` mediumint(9) NOT NULL,
  `schedule_name` varchar(50) NOT NULL,
  `location` varchar(50) DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  `sdate` date DEFAULT NULL,
  `edate` date DEFAULT NULL,
  `stime` varchar(10) DEFAULT NULL,
  `etime` varchar(10) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `matType` varchar(25) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `file_name` blob,
  `uploadedby` varchar(25) DEFAULT NULL,
  `uploadedon` datetime DEFAULT NULL,
  PRIMARY KEY (`schedule_id`,`course_id`),
  KEY `index_course_schedule` (`course_id`,`schedule_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_session`
--

DROP TABLE IF EXISTS `course_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_session` (
  `session_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `session_name` varchar(50) NOT NULL,
  `sdate` date DEFAULT NULL,
  `edate` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `index_course_session` (`session_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_skin_active`
--

DROP TABLE IF EXISTS `course_skin_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_skin_active` (
  `Course_skin_id` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_skin_management`
--

DROP TABLE IF EXISTS `course_skin_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_skin_management` (
  `Course_skin_id` varchar(100) NOT NULL,
  `Course_skin_title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Course_skin_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_style_management`
--

DROP TABLE IF EXISTS `course_style_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_style_management` (
  `Course_style_id` varchar(100) NOT NULL,
  `Course_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Course_style_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coursechapter`
--

DROP TABLE IF EXISTS `coursechapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursechapter` (
  `courseid` varchar(25) NOT NULL,
  `unitid` varchar(25) NOT NULL,
  `type` varchar(25) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `chapterno` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`courseid`,`unitid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `courseinfo`
--

DROP TABLE IF EXISTS `courseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courseinfo` (
  `course_id` varchar(25) NOT NULL,
  `csf` longblob,
  `date_last_upload` varchar(25) DEFAULT NULL,
  `upload_by` varchar(25) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coursemanagement`
--

DROP TABLE IF EXISTS `coursemanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursemanagement` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `noofhours` int(25) DEFAULT NULL,
  `minimum` varchar(25) DEFAULT NULL,
  `maximum` varchar(25) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `csformat`
--

DROP TABLE IF EXISTS `csformat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `csformat` (
  `unit_id` mediumint(9) NOT NULL,
  `csf` longblob,
  `date_last_upload` varchar(25) DEFAULT NULL,
  `upload_by` varchar(25) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `upload_target` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`unit_id`),
  KEY `UL` (`upload_by`,`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `css_reference`
--

DROP TABLE IF EXISTS `css_reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `css_reference` (
  `category` varchar(100) NOT NULL,
  `poperty` varchar(100) NOT NULL,
  `value_of_property` varchar(100) NOT NULL,
  PRIMARY KEY (`category`,`poperty`,`value_of_property`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dashboardmgmt`
--

DROP TABLE IF EXISTS `dashboardmgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dashboardmgmt` (
  `id` varchar(25) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `desktop_sharing_port`
--

DROP TABLE IF EXISTS `desktop_sharing_port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `desktop_sharing_port` (
  `sc_id` int(10) NOT NULL,
  `port_number` varchar(25) DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dynamic_info`
--

DROP TABLE IF EXISTS `dynamic_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynamic_info` (
  `session_id` varchar(50) DEFAULT NULL,
  `student_id` varchar(25) DEFAULT NULL,
  `logged_in_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_career_plan`
--

DROP TABLE IF EXISTS `employee_career_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_career_plan` (
  `emp_id` varchar(25) NOT NULL,
  `role_id` mediumint(9) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_job_role`
--

DROP TABLE IF EXISTS `employee_job_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_job_role` (
  `emp_id` varchar(25) NOT NULL,
  `role_id` mediumint(9) NOT NULL,
  `skill` mediumint(9) NOT NULL,
  `update_skill` char(1) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`role_id`,`skill`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_skill_profile`
--

DROP TABLE IF EXISTS `employee_skill_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_skill_profile` (
  `emp_id` varchar(25) NOT NULL,
  `skill_family` mediumint(9) NOT NULL,
  `skill` mediumint(9) NOT NULL,
  `competency_model` mediumint(9) NOT NULL,
  `competency_level` mediumint(9) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`skill_family`,`skill`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `essay_type_data`
--

DROP TABLE IF EXISTS `essay_type_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `essay_type_data` (
  `item_data_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_data_record_id` int(11) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `item_text` blob,
  `result_data` blob,
  `item_marks` int(10) DEFAULT NULL,
  `marks_obtained` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_data_record_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evaluation_level`
--

DROP TABLE IF EXISTS `evaluation_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation_level` (
  `level_id` int(11) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`level_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_details`
--

DROP TABLE IF EXISTS `event_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_details` (
  `student_id` varchar(255) DEFAULT NULL,
  `ondate` varchar(255) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `timestart` varchar(255) DEFAULT NULL,
  `timeend` varchar(255) DEFAULT NULL,
  `sco_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status` text,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum` (
  `forum_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `forum_name` varchar(40) DEFAULT NULL,
  `no_of_thread` int(10) DEFAULT NULL,
  `no_of_message` int(20) DEFAULT NULL,
  `last_message_posted` datetime DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `strself` varchar(10) DEFAULT NULL,
  `confirm_by` varchar(40) DEFAULT NULL,
  `confirmation_reqd` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`forum_id`),
  KEY `index_forum` (`forum_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_details_report`
--

DROP TABLE IF EXISTS `forum_details_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_details_report` (
  `forum_id` mediumint(9) NOT NULL,
  `thread_id` varchar(25) NOT NULL,
  `thread_name` varchar(25) DEFAULT NULL,
  `no_of_messages` varchar(10) DEFAULT NULL,
  `no_of_views` varchar(10) DEFAULT NULL,
  `last_message_posted` datetime DEFAULT NULL,
  `last_viewed_on` datetime DEFAULT NULL,
  PRIMARY KEY (`forum_id`,`thread_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_dynamic_information`
--

DROP TABLE IF EXISTS `forum_dynamic_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_dynamic_information` (
  `student_id` varchar(25) NOT NULL,
  `session_id` varchar(50) NOT NULL,
  `logged_in_at` datetime DEFAULT NULL,
  `forum_id` mediumint(9) NOT NULL,
  PRIMARY KEY (`student_id`,`session_id`,`forum_id`),
  KEY `forum` (`forum_id`,`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_message_body`
--

DROP TABLE IF EXISTS `forum_message_body`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_message_body` (
  `message_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `message` longblob,
  `forum_name` varchar(40) DEFAULT NULL,
  `thread_title` blob,
  `attachments` blob,
  `created_by` varchar(40) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `forum_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_registration_report`
--

DROP TABLE IF EXISTS `forum_registration_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_registration_report` (
  `forum_id` mediumint(9) NOT NULL,
  `user_id` varchar(25) NOT NULL,
  `registered_by` varchar(25) DEFAULT NULL,
  `no_of_posts` varchar(10) DEFAULT NULL,
  `no_of_views` varchar(10) DEFAULT NULL,
  `last_posted_on` datetime DEFAULT NULL,
  `last_viewed_on` datetime DEFAULT NULL,
  PRIMARY KEY (`forum_id`,`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_reply`
--

DROP TABLE IF EXISTS `forum_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_reply` (
  `thread_title` blob,
  `forum_name` varchar(40) DEFAULT NULL,
  `reply_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `reply_message` longblob,
  `created_by` varchar(40) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `attachment` longblob,
  `reply_title` longblob,
  `forum_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_skin_activation`
--

DROP TABLE IF EXISTS `forum_skin_activation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_skin_activation` (
  `current_skin` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_skin_detail_management`
--

DROP TABLE IF EXISTS `forum_skin_detail_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_skin_detail_management` (
  `skin_id` varchar(25) NOT NULL,
  `div_id` varchar(25) NOT NULL,
  `style_id` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`skin_id`,`div_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_skin_management`
--

DROP TABLE IF EXISTS `forum_skin_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_skin_management` (
  `skin_id` varchar(25) NOT NULL,
  `skin_title` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`skin_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_student_view`
--

DROP TABLE IF EXISTS `forum_student_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_student_view` (
  `forum_id` mediumint(9) NOT NULL,
  `thread_id` varchar(25) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  `last_viewed` datetime DEFAULT NULL,
  `no_of_views` varchar(25) DEFAULT NULL,
  `last_posted` datetime DEFAULT NULL,
  PRIMARY KEY (`forum_id`,`thread_id`,`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_style`
--

DROP TABLE IF EXISTS `forum_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_style` (
  `style_id` varchar(25) NOT NULL,
  `style_title` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`style_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_style_detail`
--

DROP TABLE IF EXISTS `forum_style_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_style_detail` (
  `style_id` varchar(25) NOT NULL,
  `category_id` varchar(25) NOT NULL,
  `property_id` varchar(25) NOT NULL,
  `value` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`style_id`,`category_id`,`property_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_summary_report`
--

DROP TABLE IF EXISTS `forum_summary_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_summary_report` (
  `forum_id` mediumint(9) NOT NULL,
  `forum_name` varchar(25) DEFAULT NULL,
  `date_created` varchar(25) DEFAULT NULL,
  `no_of_users` varchar(10) DEFAULT NULL,
  `no_of_threads` varchar(10) DEFAULT NULL,
  `no_of_messages` varchar(10) DEFAULT NULL,
  `no_of_views` varchar(10) DEFAULT NULL,
  `last_message_posted` datetime DEFAULT NULL,
  `last_viewed_on` datetime DEFAULT NULL,
  PRIMARY KEY (`forum_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_thread`
--

DROP TABLE IF EXISTS `forum_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_thread` (
  `forum_name` varchar(40) DEFAULT NULL,
  `thread_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `thread_title` blob,
  `no_of_message` int(10) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `no_of_views` int(20) DEFAULT NULL,
  `forum_id` mediumint(9) DEFAULT NULL,
  `parent_thread` mediumint(9) DEFAULT '0',
  PRIMARY KEY (`thread_id`)
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_thread_view`
--

DROP TABLE IF EXISTS `forum_thread_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_thread_view` (
  `forum_id` mediumint(9) NOT NULL,
  `thread_id` varchar(25) NOT NULL,
  `total_view` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`forum_id`,`thread_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forum_usage_report`
--

DROP TABLE IF EXISTS `forum_usage_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_usage_report` (
  `user_id` varchar(25) NOT NULL,
  `forum_id` mediumint(9) NOT NULL,
  `thread_id` varchar(25) NOT NULL,
  `no_of_posts` varchar(10) DEFAULT NULL,
  `thread_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`forum_id`,`thread_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grade_mgmt`
--

DROP TABLE IF EXISTS `grade_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_mgmt` (
  `course` mediumint(9) NOT NULL,
  `item` mediumint(9) NOT NULL,
  `student` varchar(50) NOT NULL,
  `attempt` varchar(25) NOT NULL,
  `marks` varchar(50) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`course`,`item`,`student`,`attempt`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grade_view`
--

DROP TABLE IF EXISTS `grade_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_view` (
  `course` mediumint(9) NOT NULL,
  `view` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_course_registration`
--

DROP TABLE IF EXISTS `group_course_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_course_registration` (
  `group_id` mediumint(9) NOT NULL,
  `course_id` mediumint(9) NOT NULL,
  `registration_by` varchar(25) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `access_allowed_till` datetime DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  `modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_forum_association`
--

DROP TABLE IF EXISTS `group_forum_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_forum_association` (
  `group_id` mediumint(9) NOT NULL,
  `forum_id` mediumint(9) NOT NULL,
  `registered_by` varchar(25) DEFAULT NULL,
  `date_registration` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`group_id`,`forum_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `groupaccessmanagement`
--

DROP TABLE IF EXISTS `groupaccessmanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupaccessmanagement` (
  `group_id` varchar(25) NOT NULL,
  `accesstime` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_page_management`
--

DROP TABLE IF EXISTS `home_page_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_page_management` (
  `unit_id` varchar(50) NOT NULL DEFAULT '',
  `home_title` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_page_type`
--

DROP TABLE IF EXISTS `home_page_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_page_type` (
  `unit_id` varchar(50) NOT NULL DEFAULT '',
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `identitymgmt`
--

DROP TABLE IF EXISTS `identitymgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identitymgmt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `ip` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `image_management`
--

DROP TABLE IF EXISTS `image_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_management` (
  `image_id` varchar(100) NOT NULL,
  `image_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instructor_role`
--

DROP TABLE IF EXISTS `instructor_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor_role` (
  `role_id` int(10) NOT NULL DEFAULT '0',
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `irdaadmin_user`
--

DROP TABLE IF EXISTS `irdaadmin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `irdaadmin_user` (
  `user_id` varchar(25) NOT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_analysis`
--

DROP TABLE IF EXISTS `item_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_analysis` (
  `student_id` varchar(25) NOT NULL,
  `unit_id` varchar(20) NOT NULL,
  `sco_id` varchar(20) NOT NULL,
  `assessment_id` varchar(20) NOT NULL,
  `section_id` varchar(20) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `result` varchar(20) DEFAULT NULL,
  `result_data` blob,
  `item_obtain_marks` decimal(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `instance` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_mgmt`
--

DROP TABLE IF EXISTS `item_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_mgmt` (
  `item_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL DEFAULT '',
  `item_name` varchar(50) NOT NULL,
  `max_marks` varchar(25) DEFAULT NULL,
  `pass_marks` varchar(25) DEFAULT NULL,
  `scale` varchar(25) DEFAULT NULL,
  `attempt` varchar(25) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `marks_scheme` varchar(15) DEFAULT NULL,
  `assignment_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_id`,`category_name`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `iteminfo`
--

DROP TABLE IF EXISTS `iteminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iteminfo` (
  `unit_id` varchar(25) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `launch` varchar(255) DEFAULT NULL,
  `parameterstring` varchar(255) DEFAULT NULL,
  `datafromlms` varchar(255) DEFAULT NULL,
  `prerequisites` varchar(255) DEFAULT NULL,
  `masteryscore` varchar(50) DEFAULT NULL,
  `maxtimeallowed` varchar(255) DEFAULT NULL,
  `timelimitaction` varchar(225) DEFAULT NULL,
  `sequence` int(10) DEFAULT NULL,
  `thelevel` int(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_mgmt`
--

DROP TABLE IF EXISTS `job_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_mgmt` (
  `job_id` varchar(25) NOT NULL DEFAULT '',
  `job_title` varchar(50) DEFAULT NULL,
  `job_type` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `object` varchar(50) DEFAULT NULL,
  `createdby` varchar(50) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_role`
--

DROP TABLE IF EXISTS `job_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_role` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `descript` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_skill_association`
--

DROP TABLE IF EXISTS `job_skill_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_skill_association` (
  `jr_id` mediumint(9) NOT NULL,
  `prof_id` mediumint(9) NOT NULL,
  PRIMARY KEY (`jr_id`,`prof_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job_trigger`
--

DROP TABLE IF EXISTS `job_trigger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_trigger` (
  `job_id` varchar(25) NOT NULL DEFAULT '',
  `trigger_id` varchar(25) NOT NULL DEFAULT '',
  `conversion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`job_id`,`trigger_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learner_login_info`
--

DROP TABLE IF EXISTS `learner_login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learner_login_info` (
  `student_id` varchar(25) DEFAULT NULL,
  `unit_id` varchar(25) DEFAULT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `topic_id` varchar(50) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL,
  KEY `index_learner_login_info` (`student_id`,`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learning_qualifier`
--

DROP TABLE IF EXISTS `learning_qualifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learning_qualifier` (
  `lqualifier` varchar(25) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `last_modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lqualifier`),
  KEY `usrID` (`created_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location_type`
--

DROP TABLE IF EXISTS `location_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_page_definition`
--

DROP TABLE IF EXISTS `login_page_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_page_definition` (
  `org_name` varchar(100) DEFAULT NULL,
  `org_logo` varchar(100) DEFAULT NULL,
  `portalname` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_page_management`
--

DROP TABLE IF EXISTS `login_page_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_page_management` (
  `login_skin_id` varchar(100) NOT NULL,
  `login_div_id` varchar(100) NOT NULL,
  `login_style_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_skin_id`,`login_div_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_skin_active`
--

DROP TABLE IF EXISTS `login_skin_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_skin_active` (
  `login_skin_id` varchar(100) DEFAULT NULL,
  `login_id` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mail_server_configuration`
--

DROP TABLE IF EXISTS `mail_server_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_server_configuration` (
  `configuration_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `ipaddress` varchar(20) NOT NULL,
  `server_port` int(11) NOT NULL,
  `status` varchar(8) NOT NULL DEFAULT 'Inactive',
  PRIMARY KEY (`configuration_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `matricdashboard`
--

DROP TABLE IF EXISTS `matricdashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matricdashboard` (
  `dashboard_id` varchar(25) NOT NULL,
  `matrics_id` varchar(25) NOT NULL,
  PRIMARY KEY (`dashboard_id`,`matrics_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `matricsmgmt`
--

DROP TABLE IF EXISTS `matricsmgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matricsmgmt` (
  `systemid` mediumint(9) NOT NULL AUTO_INCREMENT,
  `id` varchar(25) NOT NULL DEFAULT '',
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `textdata` blob,
  `numberdata` blob,
  `numberdata2` blob,
  `numberdata3` blob,
  `legenddata1` varchar(50) DEFAULT NULL,
  `legenddata2` varchar(50) DEFAULT NULL,
  `legenddata3` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `subtype` varchar(50) DEFAULT NULL,
  `width` int(6) NOT NULL DEFAULT '0',
  `height` int(6) NOT NULL DEFAULT '0',
  `yaxis` varchar(100) DEFAULT NULL,
  `xaxis` varchar(100) DEFAULT NULL,
  `color` int(10) NOT NULL DEFAULT '0',
  `transpose` varchar(25) DEFAULT NULL,
  `stacked` varchar(5) DEFAULT NULL,
  `dimension` varchar(60) NOT NULL DEFAULT 'TwoDimensional',
  PRIMARY KEY (`systemid`,`id`),
  UNIQUE KEY `systemid` (`systemid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mentor_port`
--

DROP TABLE IF EXISTS `mentor_port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mentor_port` (
  `sc_id` int(10) NOT NULL DEFAULT '0',
  `port_number` varchar(10) DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `mode` varchar(1) DEFAULT NULL,
  `VorF` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notice_activation_period`
--

DROP TABLE IF EXISTS `notice_activation_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_activation_period` (
  `notice_actv_period` mediumint(9) NOT NULL DEFAULT '30',
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `overall_marks`
--

DROP TABLE IF EXISTS `overall_marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `overall_marks` (
  `student_id` varchar(25) NOT NULL DEFAULT '',
  `course_id` varchar(25) NOT NULL DEFAULT '',
  `overallmarks` double DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `period_id` int(11) NOT NULL AUTO_INCREMENT,
  `periodname` varchar(25) NOT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`period_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `approval_by` varchar(50) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `period_id` varchar(20) DEFAULT NULL,
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan_employee_association`
--

DROP TABLE IF EXISTS `plan_employee_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_employee_association` (
  `plan_id` varchar(25) DEFAULT NULL,
  `emp_id` varchar(25) NOT NULL,
  `date` datetime DEFAULT NULL,
  `period_id` varchar(20) NOT NULL,
  PRIMARY KEY (`emp_id`,`period_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan_group_association`
--

DROP TABLE IF EXISTS `plan_group_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_group_association` (
  `plan_id` varchar(25) DEFAULT NULL,
  `group_id` varchar(25) NOT NULL,
  `date` datetime DEFAULT NULL,
  `period_id` varchar(20) NOT NULL,
  PRIMARY KEY (`group_id`,`period_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan_program_association`
--

DROP TABLE IF EXISTS `plan_program_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_program_association` (
  `plan_id` varchar(25) NOT NULL,
  `program_id` varchar(25) NOT NULL,
  PRIMARY KEY (`plan_id`,`program_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plugin_info`
--

DROP TABLE IF EXISTS `plugin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plugin_info` (
  `plugin_name` varchar(50) DEFAULT NULL,
  `mimetype` varchar(50) DEFAULT NULL,
  `program_id` varchar(50) DEFAULT NULL,
  `file_extension` varchar(5) DEFAULT NULL,
  `plugin_hosted_on` varchar(8) DEFAULT NULL,
  `plugin_software` varchar(100) DEFAULT NULL,
  `operating_system` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poll_sce`
--

DROP TABLE IF EXISTS `poll_sce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_sce` (
  `sceid` int(10) NOT NULL,
  `pollid` int(10) NOT NULL,
  `resultstatus` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `polling_choices`
--

DROP TABLE IF EXISTS `polling_choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `polling_choices` (
  `pollid` int(10) NOT NULL,
  `choiceid` int(11) NOT NULL AUTO_INCREMENT,
  `choicetext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`choiceid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `polling_details`
--

DROP TABLE IF EXISTS `polling_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `polling_details` (
  `pollid` int(10) NOT NULL AUTO_INCREMENT,
  `polltitle` varchar(50) NOT NULL,
  `polltext` varchar(50) DEFAULT NULL,
  `ismcchoice` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pollid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `polling_results`
--

DROP TABLE IF EXISTS `polling_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `polling_results` (
  `pollid` int(10) NOT NULL,
  `choiceid` int(10) NOT NULL,
  `totalvotes` int(100) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `presencemgmt`
--

DROP TABLE IF EXISTS `presencemgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presencemgmt` (
  `name` varchar(25) NOT NULL,
  `ip` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_assessment_association`
--

DROP TABLE IF EXISTS `program_assessment_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_assessment_association` (
  `program_id` varchar(20) NOT NULL,
  `assessment_id` varchar(20) NOT NULL,
  `date_availability` datetime NOT NULL,
  `registration_valid_till` datetime NOT NULL,
  `available_time` time NOT NULL,
  `total_access_time` int(11) NOT NULL DEFAULT '0',
  `created_by` varchar(30) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  PRIMARY KEY (`program_id`,`assessment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_course_association`
--

DROP TABLE IF EXISTS `program_course_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_course_association` (
  `program_id` varchar(20) NOT NULL,
  `course_id` varchar(20) NOT NULL,
  `course_study` varchar(10) NOT NULL,
  PRIMARY KEY (`program_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_evaluation`
--

DROP TABLE IF EXISTS `program_evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_evaluation` (
  `program_id` varchar(25) NOT NULL,
  `eval_type` varchar(6) NOT NULL,
  `pre_type` varchar(20) DEFAULT NULL,
  `pre_date` date DEFAULT NULL,
  `pre_start_time` time DEFAULT NULL,
  `pre_end_time` time DEFAULT NULL,
  `post_type` varchar(20) DEFAULT NULL,
  `post_date` date DEFAULT NULL,
  `post_start_time` time DEFAULT NULL,
  `post_end_time` time DEFAULT NULL,
  PRIMARY KEY (`program_id`,`eval_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_for_approval`
--

DROP TABLE IF EXISTS `program_for_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_for_approval` (
  `approver` varchar(25) DEFAULT NULL,
  `request_by` varchar(25) DEFAULT NULL,
  `request_for` varchar(25) NOT NULL,
  `program_id` varchar(25) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `plan_id` varchar(25) NOT NULL,
  PRIMARY KEY (`request_for`,`program_id`,`plan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_instance_management`
--

DROP TABLE IF EXISTS `program_instance_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_instance_management` (
  `program_type_id` varchar(50) NOT NULL,
  `program_id` varchar(50) NOT NULL,
  `program_name` varchar(50) NOT NULL,
  `program_desc` varchar(100) DEFAULT NULL,
  `cost` int(10) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `credit` varchar(25) DEFAULT NULL,
  `provider` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`program_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_resource_management`
--

DROP TABLE IF EXISTS `program_resource_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_resource_management` (
  `program_id` varchar(50) NOT NULL,
  `category_id` varchar(50) NOT NULL,
  `resource_id` varchar(50) NOT NULL,
  PRIMARY KEY (`program_id`,`category_id`,`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_schedule_management`
--

DROP TABLE IF EXISTS `program_schedule_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_schedule_management` (
  `program_id` varchar(50) NOT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `starttime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  PRIMARY KEY (`program_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_skill_association`
--

DROP TABLE IF EXISTS `program_skill_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_skill_association` (
  `program_id` varchar(25) NOT NULL,
  `skill_family` varchar(20) NOT NULL,
  `skill` varchar(20) NOT NULL,
  `start_level` varchar(20) NOT NULL,
  `end_level` varchar(20) NOT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`program_id`,`skill_family`,`skill`,`start_level`,`end_level`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_type_management`
--

DROP TABLE IF EXISTS `program_type_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_type_management` (
  `program_type_id` varchar(50) NOT NULL,
  `program_type_name` varchar(50) NOT NULL,
  `hours` varchar(25) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`program_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_name` varchar(50) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `email_id` varchar(25) DEFAULT NULL,
  `cperson` varchar(25) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`provider_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qb_metadata`
--

DROP TABLE IF EXISTS `qb_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_metadata` (
  `qb_id` varchar(60) NOT NULL,
  `item_id` int(11) NOT NULL,
  `metadatafield_name` varchar(20) DEFAULT NULL,
  `metadatafield_value` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qb_mgmt`
--

DROP TABLE IF EXISTS `qb_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_mgmt` (
  `qb_id` varchar(50) NOT NULL,
  `qb_name` varchar(60) DEFAULT NULL,
  `date_upload` datetime DEFAULT NULL,
  `upload_by` varchar(60) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `refresh_on` datetime DEFAULT NULL,
  `refresh_by` varchar(60) DEFAULT NULL,
  `download_on` datetime DEFAULT NULL,
  `download_by` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`qb_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qb_mgmt_item`
--

DROP TABLE IF EXISTS `qb_mgmt_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_mgmt_item` (
  `qb_id` varchar(50) NOT NULL,
  `assess_id` int(11) NOT NULL AUTO_INCREMENT,
  `assess_title` varchar(50) NOT NULL,
  `assess_text` longblob,
  PRIMARY KEY (`assess_id`)
) ENGINE=MyISAM AUTO_INCREMENT=639 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qb_topic_association`
--

DROP TABLE IF EXISTS `qb_topic_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_topic_association` (
  `qb_id` varchar(60) NOT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`qb_id`,`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `record_user`
--

DROP TABLE IF EXISTS `record_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record_user` (
  `user_id` varchar(25) NOT NULL,
  `program_id` varchar(25) NOT NULL,
  `period_id` varchar(25) NOT NULL,
  `attended` varchar(1) DEFAULT NULL,
  `completed` varchar(1) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `preattended` varchar(1) DEFAULT NULL,
  `preresults` varchar(1) DEFAULT NULL,
  `postattended` varchar(1) DEFAULT NULL,
  `postresults` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`program_id`,`period_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `remote_control_hub`
--

DROP TABLE IF EXISTS `remote_control_hub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remote_control_hub` (
  `sc_id` int(10) NOT NULL,
  `port_number` varchar(25) DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_field_details`
--

DROP TABLE IF EXISTS `report_field_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_field_details` (
  `field_id` varchar(50) NOT NULL,
  `field_description` longblob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_generated_report`
--

DROP TABLE IF EXISTS `report_generated_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_generated_report` (
  `report_id` varchar(50) NOT NULL,
  `report_name` varchar(50) NOT NULL,
  `report_description` longblob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_rptdetails`
--

DROP TABLE IF EXISTS `report_rptdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_rptdetails` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_module` varchar(40) NOT NULL,
  `report_file_name` varchar(40) NOT NULL,
  `rptdesign_file_name` varchar(40) DEFAULT NULL,
  `report_file` longblob,
  `report_file_size` varchar(25) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_management`
--

DROP TABLE IF EXISTS `resource_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_management` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` varchar(50) NOT NULL,
  `resource_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `role1` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sc_group_association`
--

DROP TABLE IF EXISTS `sc_group_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sc_group_association` (
  `sc_id` int(10) NOT NULL,
  `group_id` varchar(25) NOT NULL,
  PRIMARY KEY (`sc_id`,`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sc_user_association`
--

DROP TABLE IF EXISTS `sc_user_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sc_user_association` (
  `id` int(10) NOT NULL,
  `user_id` varchar(25) NOT NULL,
  `registered_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `id` (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scale_mgmt`
--

DROP TABLE IF EXISTS `scale_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_mgmt` (
  `scale_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `scale_name` varchar(50) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`scale_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scale_scheme`
--

DROP TABLE IF EXISTS `scale_scheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_scheme` (
  `scale_id` mediumint(9) NOT NULL,
  `grade_name` varchar(50) NOT NULL,
  `lower` int(11) DEFAULT NULL,
  `upper` int(11) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`scale_id`,`grade_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sce_streaming_conf`
--

DROP TABLE IF EXISTS `sce_streaming_conf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sce_streaming_conf` (
  `sc_id` int(10) NOT NULL,
  `video_device` varchar(100) DEFAULT NULL,
  `audio_device` varchar(100) DEFAULT NULL,
  `video_size` varchar(25) DEFAULT NULL,
  `local_view` varchar(10) DEFAULT NULL,
  `output_method` varchar(25) DEFAULT NULL,
  `input_port` int(10) DEFAULT NULL,
  `output_port` int(10) DEFAULT NULL,
  `encapsulation` varchar(25) DEFAULT NULL,
  `video_transcoding` varchar(25) DEFAULT NULL,
  `video_bitrate` varchar(25) DEFAULT NULL,
  `scale` varchar(25) DEFAULT NULL,
  `audio_transcoding` varchar(25) DEFAULT NULL,
  `audio_bitrate` varchar(25) DEFAULT NULL,
  `channels` varchar(25) DEFAULT NULL,
  `input_method` varchar(25) DEFAULT NULL,
  `ttl` varchar(25) DEFAULT NULL,
  `fps` varchar(25) DEFAULT NULL,
  `recording` varchar(10) DEFAULT NULL,
  `multicast` varchar(25) DEFAULT NULL,
  `mode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sce_streaming_conf_for_student`
--

DROP TABLE IF EXISTS `sce_streaming_conf_for_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sce_streaming_conf_for_student` (
  `sc_id` int(10) NOT NULL,
  `video_device` varchar(100) DEFAULT NULL,
  `audio_device` varchar(100) DEFAULT NULL,
  `video_size` varchar(25) DEFAULT NULL,
  `local_view` varchar(10) DEFAULT NULL,
  `output_method` varchar(25) DEFAULT NULL,
  `input_port` int(10) DEFAULT NULL,
  `output_port` int(10) DEFAULT NULL,
  `encapsulation` varchar(25) DEFAULT NULL,
  `video_transcoding` varchar(25) DEFAULT NULL,
  `video_bitrate` varchar(25) DEFAULT NULL,
  `scale` varchar(25) DEFAULT NULL,
  `audio_transcoding` varchar(25) DEFAULT NULL,
  `audio_bitrate` varchar(25) DEFAULT NULL,
  `channels` varchar(25) DEFAULT NULL,
  `input_method` varchar(25) DEFAULT NULL,
  `ttl` varchar(25) DEFAULT NULL,
  `fps` varchar(25) DEFAULT NULL,
  `recording` varchar(10) DEFAULT NULL,
  `multicast` varchar(25) DEFAULT NULL,
  `mode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sco_user_info`
--

DROP TABLE IF EXISTS `sco_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sco_user_info` (
  `student_id` varchar(25) NOT NULL,
  `unit_id` varchar(50) NOT NULL,
  `sco_id` varchar(200) NOT NULL,
  `sco_title` varchar(200) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skill_family`
--

DROP TABLE IF EXISTS `skill_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill_family` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `descript` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skill_profile`
--

DROP TABLE IF EXISTS `skill_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill_profile` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `descript` text,
  `model_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skill_profile_details`
--

DROP TABLE IF EXISTS `skill_profile_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill_profile_details` (
  `prof_id` mediumint(9) NOT NULL,
  `family_id` mediumint(9) NOT NULL,
  `skill_id` mediumint(9) NOT NULL,
  `level_id` mediumint(9) NOT NULL,
  PRIMARY KEY (`prof_id`,`family_id`,`skill_id`,`level_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `descript` text,
  `family_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skin_active`
--

DROP TABLE IF EXISTS `skin_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skin_active` (
  `skin_id` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skin_management`
--

DROP TABLE IF EXISTS `skin_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skin_management` (
  `skin_id` varchar(100) NOT NULL,
  `div_id` varchar(100) NOT NULL,
  `style_id` varchar(100) NOT NULL,
  PRIMARY KEY (`skin_id`,`div_id`,`style_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skin_management_title`
--

DROP TABLE IF EXISTS `skin_management_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skin_management_title` (
  `skin_id` varchar(100) NOT NULL,
  `skin_title` varchar(100) DEFAULT NULL,
  `skin_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`skin_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stassessment_question_details`
--

DROP TABLE IF EXISTS `stassessment_question_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stassessment_question_details` (
  `student_id` varchar(100) NOT NULL,
  `test_id` int(11) NOT NULL,
  `question_id` varchar(20) NOT NULL,
  `question_text` longblob,
  `correctAns` longblob,
  `ans_data` varchar(100) NOT NULL,
  `ans_text` longblob,
  `ans_status` varchar(200) NOT NULL,
  `item_marks` decimal(10,2) DEFAULT NULL,
  `item_obtain_marks` decimal(10,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stassessment_test_details`
--

DROP TABLE IF EXISTS `stassessment_test_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stassessment_test_details` (
  `student_id` varchar(100) NOT NULL,
  `test_id` int(11) NOT NULL AUTO_INCREMENT,
  `assessment_id` int(10) DEFAULT NULL,
  `confirmation_id` int(11) NOT NULL DEFAULT '0',
  `date` date DEFAULT NULL,
  `time_started` time DEFAULT NULL,
  `time_completed` time DEFAULT NULL,
  `total_no_of_ques` int(10) DEFAULT NULL,
  `no_of_ques_per_page` int(10) DEFAULT NULL,
  `no_of_ques_attempted` int(10) DEFAULT NULL,
  `no_of_coans` int(5) DEFAULT NULL,
  `duration` int(10) DEFAULT NULL,
  `full_marks` decimal(10,2) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `mail_status` varchar(200) DEFAULT NULL,
  `submition_status` varchar(20) NOT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=MyISAM AUTO_INCREMENT=135 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stassessment_topfive_result`
--

DROP TABLE IF EXISTS `stassessment_topfive_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stassessment_topfive_result` (
  `assessment_id` int(11) NOT NULL DEFAULT '0',
  `student_id` varchar(25) NOT NULL,
  `r_id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `full_marks` decimal(10,2) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `submition_status` varchar(20) NOT NULL,
  PRIMARY KEY (`assessment_id`,`r_id`,`test_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `strategy`
--

DROP TABLE IF EXISTS `strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategy` (
  `course_id` mediumint(9) NOT NULL,
  `csf` longblob,
  `date_last_upload` varchar(25) DEFAULT NULL,
  `upload_by` varchar(25) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `UL1` (`upload_by`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `streaming_server_mgmt`
--

DROP TABLE IF EXISTS `streaming_server_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `streaming_server_mgmt` (
  `sc_id` int(10) NOT NULL,
  `port_number` varchar(25) DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_creation_details`
--

DROP TABLE IF EXISTS `student_creation_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_creation_details` (
  `student_id` varchar(25) NOT NULL,
  `date_student_created` datetime DEFAULT NULL,
  `student_created_by` varchar(25) DEFAULT NULL,
  `admin_created_by` varchar(25) DEFAULT NULL,
  `last_modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `usrId1` (`student_created_by`),
  KEY `admin_created_by` (`admin_created_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_details`
--

DROP TABLE IF EXISTS `student_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_details` (
  `student_id` varchar(25) NOT NULL,
  `sname` varchar(50) NOT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `age` varchar(2) DEFAULT NULL,
  `edu_status` varchar(30) DEFAULT NULL,
  `experience` varchar(4) DEFAULT NULL,
  `question_preffered` varchar(5) DEFAULT NULL,
  `media_preffered` varchar(15) DEFAULT NULL,
  `learning_style` varchar(25) DEFAULT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `middle_name` varchar(25) DEFAULT NULL,
  `department` varchar(25) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `account_status` varchar(25) DEFAULT NULL,
  `strself` varchar(10) DEFAULT NULL,
  `photo` longblob,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_dynamic_information`
--

DROP TABLE IF EXISTS `student_dynamic_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_dynamic_information` (
  `student_id` varchar(25) NOT NULL,
  `session_id` varchar(100) NOT NULL,
  `logged_in_at` datetime DEFAULT NULL,
  `course_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`session_id`),
  KEY `std` (`course_id`,`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_group`
--

DROP TABLE IF EXISTS `student_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_group` (
  `group_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(25) NOT NULL,
  `group_created_date` datetime DEFAULT NULL,
  `group_created_by` varchar(25) NOT NULL,
  `last_modification_date` datetime DEFAULT NULL,
  `learning_style` varchar(25) DEFAULT NULL,
  `self_regis` varchar(10) DEFAULT NULL,
  `skin_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `grID` (`group_created_by`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_group_association`
--

DROP TABLE IF EXISTS `student_group_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_group_association` (
  `group_id` mediumint(9) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  PRIMARY KEY (`group_id`,`student_id`),
  KEY `grp_idd` (`student_id`,`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_ipaddress_association`
--

DROP TABLE IF EXISTS `student_ipaddress_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_ipaddress_association` (
  `sce_id` int(10) NOT NULL,
  `student_id` varchar(25) NOT NULL,
  `ipaddress` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`sce_id`,`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_login_time`
--

DROP TABLE IF EXISTS `student_login_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_login_time` (
  `session_id` varchar(100) NOT NULL,
  `student_id` varchar(25) DEFAULT NULL,
  `logged_in_at` datetime DEFAULT NULL,
  `logged_out_at` datetime DEFAULT NULL,
  KEY `std1` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_password`
--

DROP TABLE IF EXISTS `student_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_password` (
  `student_id` varchar(25) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_skin`
--

DROP TABLE IF EXISTS `student_skin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_skin` (
  `student_id` varchar(25) NOT NULL,
  `skin_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `style_management`
--

DROP TABLE IF EXISTS `style_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `style_management` (
  `skin_id` varchar(100) NOT NULL,
  `style_id` varchar(100) NOT NULL,
  `category` varchar(100) DEFAULT NULL,
  `property` varchar(100) NOT NULL,
  `property_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`style_id`,`property`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `syllabus_details`
--

DROP TABLE IF EXISTS `syllabus_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syllabus_details` (
  `topic_id` varchar(25) NOT NULL,
  `course_id` mediumint(9) NOT NULL,
  `parent_id` varchar(25) DEFAULT NULL,
  `topic_name` varchar(100) NOT NULL,
  `description` blob,
  PRIMARY KEY (`topic_id`,`course_id`),
  KEY `index_syllabus_details` (`topic_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `synchronous_collaboration`
--

DROP TABLE IF EXISTS `synchronous_collaboration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `synchronous_collaboration` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `chat` varchar(10) DEFAULT NULL,
  `sw` varchar(10) DEFAULT NULL,
  `ledsession` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `recordsession` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `session_id` varchar(25) DEFAULT NULL,
  `sp` varchar(10) DEFAULT NULL,
  `cobrowse` varchar(10) DEFAULT NULL,
  `sound` varchar(10) DEFAULT NULL,
  `video` varchar(10) DEFAULT NULL,
  `rsession` varchar(50) DEFAULT NULL,
  `self_regis` varchar(10) DEFAULT NULL,
  `confirmation_reqd` varchar(10) DEFAULT NULL,
  `confirmed_by` varchar(25) DEFAULT NULL,
  `desktop` char(10) DEFAULT NULL,
  `mentor_view` char(10) DEFAULT NULL,
  `student_view` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_aasessment_regis_report`
--

DROP TABLE IF EXISTS `temp_aasessment_regis_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_aasessment_regis_report` (
  `report_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `assessment_title` varchar(50) DEFAULT NULL,
  `available_time` varchar(30) DEFAULT NULL,
  `date_availability` date DEFAULT NULL,
  `access_allowed_till` date DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_asmtresult_scorm_model`
--

DROP TABLE IF EXISTS `temp_asmtresult_scorm_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_asmtresult_scorm_model` (
  `student_id` varchar(25) NOT NULL,
  `student_name` varchar(200) NOT NULL,
  `test_name` varchar(50) NOT NULL,
  `total_marks` int(10) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_asmtresult_user_model`
--

DROP TABLE IF EXISTS `temp_asmtresult_user_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_asmtresult_user_model` (
  `student_id` varchar(25) NOT NULL,
  `student_name` varchar(200) NOT NULL,
  `assessment_type` varchar(25) NOT NULL,
  `test_name` varchar(50) NOT NULL,
  `section` varchar(25) NOT NULL,
  `testdate` date DEFAULT NULL,
  `no_of_questions` int(10) DEFAULT NULL,
  `no_of_questions_attempted` int(10) DEFAULT NULL,
  `no_of_correct_ans` int(10) DEFAULT NULL,
  `total_marks` int(10) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `percent_marks` decimal(10,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_assessment_group_registration`
--

DROP TABLE IF EXISTS `temp_assessment_group_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_assessment_group_registration` (
  `group_id` varchar(100) NOT NULL,
  `assessment_id` varchar(100) NOT NULL,
  `date_registration` datetime DEFAULT NULL,
  `registered_by` varchar(100) DEFAULT NULL,
  `available_time` varchar(100) DEFAULT NULL,
  `date_availability` varchar(100) DEFAULT NULL,
  `registration_valid_till` varchar(100) DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_assessment_user_registration`
--

DROP TABLE IF EXISTS `temp_assessment_user_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_assessment_user_registration` (
  `student_id` varchar(100) NOT NULL,
  `assessment_id` varchar(100) NOT NULL,
  `date_registration` datetime DEFAULT NULL,
  `registered_by` varchar(100) DEFAULT NULL,
  `available_time` varchar(100) DEFAULT NULL,
  `date_availability` varchar(100) DEFAULT NULL,
  `registration_valid_till` varchar(100) DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_checkunit_consistency`
--

DROP TABLE IF EXISTS `temp_checkunit_consistency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_checkunit_consistency` (
  `filename` varchar(200) NOT NULL,
  `resource_id` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_mcontentobj_organization`
--

DROP TABLE IF EXISTS `temp_mcontentobj_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_mcontentobj_organization` (
  `item` varchar(25) NOT NULL,
  `item_title` varchar(25) NOT NULL,
  `content_object` varchar(200) NOT NULL,
  `file_last_uploaded` varchar(50) NOT NULL,
  `sizeof_file` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_mcontentobj_resource`
--

DROP TABLE IF EXISTS `temp_mcontentobj_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_mcontentobj_resource` (
  `resource_id` varchar(25) NOT NULL,
  `element_type` varchar(25) NOT NULL,
  `element_value` varchar(200) NOT NULL,
  `content_type` varchar(25) DEFAULT NULL,
  `adl_type` varchar(25) DEFAULT NULL,
  `file_last_uploaded` varchar(50) DEFAULT NULL,
  `sizefile` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_report_summary`
--

DROP TABLE IF EXISTS `temp_report_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_report_summary` (
  `reportid` int(10) NOT NULL AUTO_INCREMENT,
  `annexure` varchar(5) DEFAULT NULL,
  `percentage_marks` varchar(100) DEFAULT NULL,
  `no_of_employee` int(10) DEFAULT NULL,
  `total` int(10) DEFAULT NULL,
  PRIMARY KEY (`reportid`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_result_details`
--

DROP TABLE IF EXISTS `temp_result_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_result_details` (
  `report_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `number_of_ques` int(5) DEFAULT NULL,
  `ques_attempted` int(5) DEFAULT NULL,
  `number_of_correctans` int(5) DEFAULT NULL,
  `total_marks` decimal(10,2) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  `percentage_marks_obtained` decimal(10,2) DEFAULT NULL,
  `submition_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_result_report`
--

DROP TABLE IF EXISTS `temp_result_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_result_report` (
  `report_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `marks_obtained` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_scorm_initialization`
--

DROP TABLE IF EXISTS `temp_scorm_initialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_scorm_initialization` (
  `scoid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `lessonstatus` varchar(50) DEFAULT NULL,
  `lessonmode` varchar(255) DEFAULT NULL,
  `exit1` varchar(50) DEFAULT NULL,
  `entry1` varchar(50) DEFAULT NULL,
  `totaltime` varchar(13) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_student_details`
--

DROP TABLE IF EXISTS `temp_student_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_student_details` (
  `student_id` varchar(25) NOT NULL DEFAULT '',
  `password` varchar(25) DEFAULT NULL,
  `sname` varchar(50) DEFAULT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `age` varchar(2) DEFAULT NULL,
  `edu_status` varchar(30) DEFAULT NULL,
  `experience` varchar(4) DEFAULT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `middle_name` varchar(25) DEFAULT NULL,
  `department` varchar(25) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `account_status` varchar(25) DEFAULT NULL,
  `strself` varchar(10) DEFAULT NULL,
  `group_id` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_user_creation_report`
--

DROP TABLE IF EXISTS `temp_user_creation_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_user_creation_report` (
  `report_id` int(10) NOT NULL AUTO_INCREMENT,
  `spfno` varchar(10) DEFAULT NULL,
  `student_id` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `branch_code` varchar(50) DEFAULT NULL,
  `branch_name` varchar(50) DEFAULT NULL,
  `region_code` int(5) DEFAULT NULL,
  `region_name` varchar(50) DEFAULT NULL,
  `category` varchar(30) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `createdby` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_user_detailreport`
--

DROP TABLE IF EXISTS `temp_user_detailreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_user_detailreport` (
  `report_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `educational_status` varchar(50) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `experience` int(10) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_user_registration_report`
--

DROP TABLE IF EXISTS `temp_user_registration_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_user_registration_report` (
  `report_id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `unit_name` varchar(50) DEFAULT NULL,
  `access_allowed_till` date DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `top_forum_users`
--

DROP TABLE IF EXISTS `top_forum_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `top_forum_users` (
  `user_id` varchar(25) NOT NULL,
  `no_of_threads` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_management`
--

DROP TABLE IF EXISTS `topic_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_management` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_name` varchar(100) NOT NULL,
  `subtopic_name` varchar(100) NOT NULL,
  `added_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_material_details`
--

DROP TABLE IF EXISTS `topic_material_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_material_details` (
  `course_id` mediumint(9) NOT NULL,
  `topic_id` varchar(25) NOT NULL,
  `topicmatid` mediumint(9) NOT NULL AUTO_INCREMENT,
  `mtype` varchar(25) DEFAULT NULL,
  `uploadedby` varchar(25) DEFAULT NULL,
  `uploadedon` datetime DEFAULT NULL,
  `description` varchar(90) DEFAULT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`topicmatid`,`topic_id`,`course_id`),
  KEY `index_topic_material_details` (`topicmatid`,`topic_id`,`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=163 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `training_location`
--

DROP TABLE IF EXISTS `training_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_location` (
  `location_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(50) NOT NULL,
  `type_id` int(11) NOT NULL DEFAULT '0',
  `description` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trigger_mgmt`
--

DROP TABLE IF EXISTS `trigger_mgmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trigger_mgmt` (
  `trigger_id` varchar(25) NOT NULL DEFAULT '',
  `trigger_title` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `cronexp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`trigger_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_completion_sco`
--

DROP TABLE IF EXISTS `unit_completion_sco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_completion_sco` (
  `unit_id` varchar(40) DEFAULT NULL,
  `sco_id` varchar(40) DEFAULT NULL,
  `sco_title` varchar(200) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `marks` int(4) DEFAULT NULL,
  `time_spent` int(10) DEFAULT NULL,
  `total_time` int(10) DEFAULT NULL,
  `times_visited` int(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_creation_details`
--

DROP TABLE IF EXISTS `unit_creation_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_creation_details` (
  `unit_id` mediumint(9) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `created_by` varchar(25) NOT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `forum_name` varchar(50) DEFAULT NULL,
  `chat_name` varchar(50) DEFAULT NULL,
  `calender_name` varchar(50) DEFAULT NULL,
  `controll` varchar(2) DEFAULT NULL,
  `self_regis` varchar(10) DEFAULT NULL,
  `confirmation_reqd` varchar(10) DEFAULT NULL,
  `confirmed_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_details`
--

DROP TABLE IF EXISTS `unit_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_details` (
  `unit_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(50) NOT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=MyISAM AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_group_association`
--

DROP TABLE IF EXISTS `unit_group_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_group_association` (
  `group_id` mediumint(9) NOT NULL,
  `unit_id` mediumint(9) NOT NULL,
  `date_registration` datetime DEFAULT NULL,
  `registered_by` varchar(25) DEFAULT NULL,
  `access_allowed_till` datetime DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`group_id`,`unit_id`),
  KEY `grp1_idd` (`unit_id`,`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_prerequisite`
--

DROP TABLE IF EXISTS `unit_prerequisite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_prerequisite` (
  `unit_id` varchar(40) DEFAULT NULL,
  `pre_unit_id` varchar(40) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_student_association`
--

DROP TABLE IF EXISTS `unit_student_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_student_association` (
  `student_id` varchar(25) NOT NULL,
  `unit_id` mediumint(9) NOT NULL,
  `access_allowed_till` datetime DEFAULT NULL,
  `total_access_time` int(10) DEFAULT NULL,
  `date_registration` datetime DEFAULT NULL,
  `registered_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`unit_id`),
  KEY `student_id` (`student_id`),
  KEY `unit_id` (`unit_id`),
  KEY `course1_idd` (`registered_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_tab`
--

DROP TABLE IF EXISTS `unit_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_tab` (
  `course_id` varchar(50) DEFAULT NULL,
  `tab` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_course_registration`
--

DROP TABLE IF EXISTS `user_course_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_course_registration` (
  `student_id` varchar(25) NOT NULL,
  `course_id` mediumint(9) NOT NULL,
  `registration_by` varchar(25) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_forum_association`
--

DROP TABLE IF EXISTS `user_forum_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_forum_association` (
  `student_id` varchar(25) NOT NULL,
  `forum_id` mediumint(9) NOT NULL,
  `registered_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`forum_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_login_time`
--

DROP TABLE IF EXISTS `user_login_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login_time` (
  `session_id` varchar(50) DEFAULT NULL,
  `student_id` varchar(25) DEFAULT NULL,
  `logged_in_at` datetime DEFAULT NULL,
  KEY `std2` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_notes_details`
--

DROP TABLE IF EXISTS `user_notes_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notes_details` (
  `student_id` varchar(25) NOT NULL,
  `unit_id` varchar(50) NOT NULL,
  `unit_title` varchar(200) NOT NULL,
  `sco_id` varchar(200) NOT NULL,
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `note_details` blob,
  `note_date` datetime DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `attachment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=MyISAM AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` varchar(100) NOT NULL DEFAULT '',
  `role_id` int(10) NOT NULL DEFAULT '0',
  `comment` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `entered_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `user_role1` (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `useraccessmanagement`
--

DROP TABLE IF EXISTS `useraccessmanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccessmanagement` (
  `user_id` varchar(25) NOT NULL,
  `accesstime` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usergroup_course_registration`
--

DROP TABLE IF EXISTS `usergroup_course_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup_course_registration` (
  `student_id` varchar(25) NOT NULL,
  `course_id` mediumint(9) NOT NULL,
  `book_marks` varchar(25) DEFAULT NULL,
  `registration_by` varchar(25) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `access_allowed_till` varchar(20) DEFAULT NULL,
  `total_access_time` varchar(10) DEFAULT NULL,
  `modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userscoinfo`
--

DROP TABLE IF EXISTS `userscoinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userscoinfo` (
  `user_id` varchar(25) DEFAULT NULL,
  `unit_id` varchar(25) DEFAULT NULL,
  `sco_id` varchar(255) DEFAULT NULL,
  `launch` varchar(255) DEFAULT NULL,
  `parameterstring` varchar(255) DEFAULT NULL,
  `lessonstatus` varchar(50) DEFAULT NULL,
  `prerequisites` varchar(255) DEFAULT NULL,
  `exit1` varchar(50) DEFAULT NULL,
  `entry1` varchar(50) DEFAULT NULL,
  `credit` varchar(50) DEFAULT NULL,
  `masteryscore` varchar(50) DEFAULT NULL,
  `sequence` int(10) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `rawscore` varchar(50) DEFAULT NULL,
  `maxscore` varchar(50) DEFAULT NULL,
  `minscore` varchar(50) DEFAULT NULL,
  `lessonmode` varchar(50) DEFAULT NULL,
  `totaltime` varchar(13) DEFAULT NULL,
  `sessiontime` varchar(13) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-23  1:08:34
