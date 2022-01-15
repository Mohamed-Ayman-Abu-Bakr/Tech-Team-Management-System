-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql11.freesqldatabase.com
-- Generation Time: Jan 15, 2022 at 02:38 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql11446943`
--

-- --------------------------------------------------------

--
-- Table structure for table `Clients`
--

CREATE TABLE `Clients` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Number` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Clients`
--

INSERT INTO `Clients` (`id`, `Name`, `Number`, `Email`, `Address`) VALUES
(3, 'Mohamed Ayman', '01126190605', 'mohameddalash01@gmail.com', 'Address'),
(7, 'Client', '01119348229', 'client@cmient.com', 'addreesssss'),
(8, 'Client 3', '01114528339', 'clienttt@gmail.com', 'jfejfodsiof');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `phone` varchar(255) NOT NULL,
  `completed_tasks` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `name`, `username`, `password`, `position`, `email`, `birthdate`, `phone`, `completed_tasks`) VALUES
(1, 'Manager', 'manager', 'manager', 'Management', 'mohamed161@stemegypt.edu.eg', '2001-02-28', '01126190605', 0),
(4, 'Ahmed', 'boLPuz1gnX', 'bXY1MBuO4h', 'Testing', 'haniajed79@gmail.com', '2000-01-27', '01122222222', 2),
(5, 'employee', 'employee', 'employee', 'Development', 'haniajed79@gmail.com', '2000-01-01', '01119348244', 2),
(6, 'Employee', 'ElWKLlAHBF', 'kWxSqicqdC', 'Development', 'haniajed79@gmail.com', '2000-01-04', '01119348229', 0),
(7, 'Mohamed', 'OpmHkr6od0', '2qiRk6cEoq', 'Development', 'm.raslan97@gmail.com', '2000-01-01', '01001412578', 0),
(9, 'Newbie', 'HH2aiinyMj', 'T59X6ueH7F', 'Backend web development', 'mohameddalash01@gmail.com', '2001-02-28', '01126190605', 0),
(16, 'Hanya Adel', '8FLNegFGBu', 'ocbK4FKcod', 'UX design', 'haniajed79@gmail.com', '2000-01-01', '01119348229', 0),
(17, 'M', 'OdApj1QzRa', 's1lgErZu6V', 'Development', 'mm@gmail.com', '2000-01-01', '01231231225', 0),
(18, 'Mohamed', 'l3fpkCIjaG', 'Y2p4xLbMXV', 'Development', 'mohamed@gmail.com', '2000-01-01', '01234567891', 0);

-- --------------------------------------------------------

--
-- Table structure for table `meetings`
--

CREATE TABLE `meetings` (
  `id` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Day` date NOT NULL,
  `Time` time NOT NULL,
  `Department` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meetings`
--

INSERT INTO `meetings` (`id`, `Title`, `Day`, `Time`, `Department`) VALUES
(1, 'meeting1', '2021-10-26', '10:30:00', 'Development'),
(2, 'meeting', '2021-12-15', '12:00:00', 'Backend web development'),
(3, 'meeting 4', '2021-12-23', '12:09:00', 'Development'),
(8, 'mreeting 5', '2022-01-02', '10:30:00', 'Testing');

-- --------------------------------------------------------

--
-- Table structure for table `Projects`
--

CREATE TABLE `Projects` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `projectDescription` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `client_name` varchar(255) NOT NULL,
  `Manager_name` varchar(255) NOT NULL,
  `cost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Projects`
--

INSERT INTO `Projects` (`id`, `title`, `projectDescription`, `date`, `type`, `client_name`, `Manager_name`, `cost`) VALUES
(2, 'To-do_list', 'a simple todo list', '2021-11-11', 'Desktop App', '1', '1', 2000),
(3, 'snake game 3', 'a nokia snake game 54', '2021-11-18', 'Game', '1', '1', 200),
(4, 'project', 'project 1', '2021-12-23', 'Desktop App', '1', '1', 12),
(5, 'Project', 'project1', '2021-12-07', 'Desktop App', '1', '1', 13),
(6, 'prooo', 'project 3', '2021-12-14', 'Web App', '3', '1', 24),
(7, 'New Project Updated', 'Updated Description for New Project', '2022-01-01', 'Embedded Sys. App', '3', '1', 3000),
(9, 'yty', 'ghhgh', '2021-12-26', 'Game', '7', '1', 656556);

-- --------------------------------------------------------

--
-- Table structure for table `Tasks`
--

CREATE TABLE `Tasks` (
  `task_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `task_description` varchar(255) NOT NULL,
  `deadline_date` date NOT NULL,
  `task_status` varchar(255) NOT NULL DEFAULT 'Not Done'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Tasks`
--

INSERT INTO `Tasks` (`task_id`, `employee_id`, `project_id`, `task_name`, `task_description`, `deadline_date`, `task_status`) VALUES
(7, 4, 7, 'New Task Updated 2', 'Description for new task Updated', '2021-12-24', 'Verified'),
(10, 4, 4, 'Task3', 'Do the task', '2021-12-15', 'not complete'),
(11, 15, 4, 'Taskk', 'Taskkk', '2021-12-06', 'not complete');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Clients`
--
ALTER TABLE `Clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meetings`
--
ALTER TABLE `meetings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Projects`
--
ALTER TABLE `Projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Tasks`
--
ALTER TABLE `Tasks`
  ADD PRIMARY KEY (`task_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Clients`
--
ALTER TABLE `Clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `meetings`
--
ALTER TABLE `meetings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `Projects`
--
ALTER TABLE `Projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `Tasks`
--
ALTER TABLE `Tasks`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
