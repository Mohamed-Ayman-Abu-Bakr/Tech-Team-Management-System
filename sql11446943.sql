-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql11.freesqldatabase.com
-- Generation Time: Oct 27, 2021 at 11:02 PM
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
(1, 'alsdkfj', 'alskdfjlaksd', '01126190605', 'asld3fj@gmail.com');

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
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `name`, `username`, `password`, `position`, `email`, `birthdate`, `phone`) VALUES
(1, 'Manager', 'manager', 'manager', 'Management', 'mohamed16130@stemegypt.edu.eg', '2001-02-28', '01126190605'),
(2, 'Dev', 'employee', 'employee', 'Development', 'mohamed16130@stemegypt.edu.eg', '2000-01-01', '01126190605');

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
(1, 'meeting', '2021-10-26', '10:30:00', 'Development');

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
(1, 'sldkjasdd', 'description', '2021-10-26', 'Mobile App', '1', '1', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `Tasks`
--

CREATE TABLE `Tasks` (
  `task_id` int(11) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `task_description` varchar(255) NOT NULL,
  `deadline_date` date NOT NULL,
  `task_status` varchar(255) NOT NULL DEFAULT 'Not Done'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Tasks`
--

INSERT INTO `Tasks` (`task_id`, `employee_id`, `task_name`, `task_description`, `deadline_date`, `task_status`) VALUES
(1, '2', 'task', 'asldkfj', '2021-10-26', 'In Progress');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `meetings`
--
ALTER TABLE `meetings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Projects`
--
ALTER TABLE `Projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Tasks`
--
ALTER TABLE `Tasks`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
