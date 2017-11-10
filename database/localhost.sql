-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 26, 2016 at 07:43 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bloodbank`
--
CREATE DATABASE IF NOT EXISTS `bloodbank` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bloodbank`;

-- --------------------------------------------------------

--
-- Table structure for table `bloodbank_details`
--

CREATE TABLE IF NOT EXISTS `bloodbank_details` (
  `bloodbank_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `place` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` text NOT NULL,
  PRIMARY KEY (`bloodbank_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `bloodbank_details`
--

INSERT INTO `bloodbank_details` (`bloodbank_id`, `name`, `place`, `phone`, `email`) VALUES
(1, 'rotary', 'Mangalore', '1234567890', 'rotarymangalore@gmail.com'),
(5, 'Lions Club', 'Mangalore', '1234567890', 'lionsmangalore@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `donor_details`
--

CREATE TABLE IF NOT EXISTS `donor_details` (
  `donor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `blood_group` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `state` varchar(100) NOT NULL,
  `place` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`donor_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `donor_details`
--

INSERT INTO `donor_details` (`donor_id`, `name`, `blood_group`, `email`, `phone`, `state`, `place`, `dob`, `gender`, `username`) VALUES
(15, 'guru', 'O+', 'guru@gmail.com', '9535618933', 'Karnataka', 'karwar', '2016-03-10', 'Male', 'guru'),
(16, 'guru', 'O+', 'guru@gmail.com', '9535618933', 'Karnataka', 'karwar', '2016-03-10', 'Male', 'guru'),
(17, 'abrar', 'o+', 'abrar@gmail.com', '9535618933', 'Karnataka', 'karwar', '2016-03-10', 'Male', 'sathish123'),
(18, 'Abhin', 'A+', 'asas', '1234567890', 'Karnataka', 'karwar', '2016-03-10', 'Male', 'abhin'),
(19, 'sudheer', 'O+', 'asas', '1234567890', 'Karnataka', 'karwar', '2016-03-10', 'Male', 'sudheer'),
(20, 'sanket', 'B+', 'asas', '1234567890', 'Karnataka', 'Karwar', '2016-03-10', 'Male', 'sanket');

-- --------------------------------------------------------

--
-- Table structure for table `feedbacks`
--

CREATE TABLE IF NOT EXISTS `feedbacks` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` text NOT NULL,
  `donor_id` int(11) NOT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `feedbacks`
--

INSERT INTO `feedbacks` (`feedback_id`, `date`, `description`, `donor_id`) VALUES
(1, '2016-08-09', ' hh', 1),
(2, '2016-03-12', 'sadada', 17),
(3, '2016-03-20', 'Feedback', 17);

-- --------------------------------------------------------

--
-- Table structure for table `hospital_details`
--

CREATE TABLE IF NOT EXISTS `hospital_details` (
  `hospital_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `place` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` text NOT NULL,
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `hospital_details`
--

INSERT INTO `hospital_details` (`hospital_id`, `name`, `place`, `phone`, `email`) VALUES
(7, 'Alvas', 'Moodbidri', '1234567890', 'alvas@gmail.com'),
(10, 'KMC', 'Mangalore', '1234567890', 'kmc@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

CREATE TABLE IF NOT EXISTS `login_details` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`username`, `password`, `type`) VALUES
('admin', 'admin', 'admin'),
('guru', '123456', 'user'),
('sath', '123456', 'user'),
('sathi', '123456', 'user'),
('sathia', '123456', 'user'),
('sathish123', 'sathish123', 'user');
--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
