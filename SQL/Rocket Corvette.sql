-- phpMyAdmin SQL Dump
-- version 4.0.0
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Nov 05, 2018 at 10:38 AM
-- Server version: 5.5.39-log
-- PHP Version: 5.4.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dzXXXXX`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `custID` int(11) NOT NULL,
  `custFirstName` varchar(30) NOT NULL,
  `custLastName` varchar(30) NOT NULL,
  `custZipcode` int(11) NOT NULL,
  `custCity` varchar(30) NOT NULL,
  `custState` char(2) NOT NULL,
  `custStreet` varchar(30) NOT NULL,
  PRIMARY KEY (`custID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custID`, `custFirstName`, `custLastName`, `custZipcode`, `custCity`, `custState`, `custStreet`) VALUES
(18600, 'Boris', 'Grishenko', 92831, 'Fullerton', 'CA', '1127 East Ash Ave'),
(18601, 'Seifer', 'Almasy', 60629, 'Chicago', 'IL', '4200 W 55th St'),
(18602, 'Xenia', 'Onatopp', 8846, 'Middlesex', 'NJ', '200A Wood Avenue'),
(18603, 'Rob', 'Zombie', 1830, 'Havervill', 'MA', '280 Main St');

-- --------------------------------------------------------

--
-- Table structure for table `customerphone`
--

CREATE TABLE IF NOT EXISTS `customerphone` (
  `custID` int(11) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  PRIMARY KEY (`phoneNumber`,`custID`),
  KEY `custID` (`custID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customerphone`
--

INSERT INTO `customerphone` (`custID`, `phoneNumber`) VALUES
(18600, '714 871-1605'),
(18601, '773 498-2642'),
(18601, '773 735-0774'),
(18602, '908 753-8777'),
(18603, '978 372-4174');

-- --------------------------------------------------------

--
-- Table structure for table `includes`
--

CREATE TABLE IF NOT EXISTS `includes` (
  `orderID` int(11) NOT NULL,
  `partNumber` varchar(10) NOT NULL,
  `quantityOrdered` int(11) NOT NULL,
  PRIMARY KEY (`orderID`,`partNumber`),
  KEY `partNumber` (`partNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `includes`
--

INSERT INTO `includes` (`orderID`, `partNumber`, `quantityOrdered`) VALUES
(387001, 'B1572', 1),
(387001, 'C1671', 1),
(387002, 'A7816', 1),
(387002, 'B1572', 3),
(387002, 'B2504', 1),
(387003, 'A1506', 1),
(387003, 'B1572', 2),
(387004, 'B0454', 1),
(387005, 'C1671', 1),
(387005, 'C3571', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orderrecord`
--

CREATE TABLE IF NOT EXISTS `orderrecord` (
  `orderID` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `orderTime` varchar(10) NOT NULL,
  `comments` varchar(60) DEFAULT NULL,
  `customer` int(11) NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `customer` (`customer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderrecord`
--

INSERT INTO `orderrecord` (`orderID`, `orderDate`, `orderTime`, `comments`, `customer`) VALUES
(387001, '2018-10-15', '14:02', 'Send a C2 catalog', 18600),
(387002, '2018-10-31', '13:54', NULL, 18601),
(387003, '2018-11-03', '04:03', 'Send an extra gasket', 18602),
(387004, '2018-11-04', '15:32', 'NEVER sell anything to this guy again', 18603),
(387005, '2018-11-05', '17:23', NULL, 18601);

-- --------------------------------------------------------

--
-- Table structure for table `part`
--

CREATE TABLE IF NOT EXISTS `part` (
  `partNumber` varchar(10) NOT NULL,
  `PartName` varchar(50) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `coreCharge` decimal(5,2) NOT NULL,
  `shippingCost` decimal(5,2) NOT NULL,
  `QuantityInStock` int(11) NOT NULL,
  PRIMARY KEY (`partNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `part`
--

INSERT INTO `part` (`partNumber`, `PartName`, `price`, `coreCharge`, `shippingCost`, `QuantityInStock`) VALUES
('A1506', 'CARBURETOR WCFB', '649.95', '100.00', '22.00', 7),
('A1518', 'CARBURETOR AFB', '895.95', '100.00', '20.00', 2),
('A7816', 'STARTER', '259.95', '50.00', '25.00', 4),
('B0454', 'ALTERNATOR', '224.95', '50.00', '22.00', 1),
('B1572', 'CARBURETOR KIT', '27.95', '0.00', '5.95', 24),
('B2504', 'DISTRIBUTOR', '895.95', '50.00', '17.00', 3),
('C1671', 'CARBURETOR QJET', '259.95', '50.00', '21.00', 5),
('C3571', 'FIBERGLASS PANELS SET', '4750.95', '0.00', '100.00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `supplies`
--

CREATE TABLE IF NOT EXISTS `supplies` (
  `vendorID` int(11) NOT NULL,
  `partNumber` varchar(10) NOT NULL,
  `isDropShip` char(1) NOT NULL,
  PRIMARY KEY (`vendorID`,`partNumber`),
  KEY `partNumber` (`partNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplies`
--

INSERT INTO `supplies` (`vendorID`, `partNumber`, `isDropShip`) VALUES
(10254, 'A7816', 'N'),
(10254, 'B0454', 'N'),
(10254, 'B2504', 'N'),
(10255, 'A1506', 'N'),
(10255, 'A1518', 'N'),
(10255, 'B1572', 'N'),
(10255, 'C1671', 'N'),
(10256, 'B2504', 'N'),
(10256, 'C3571', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE IF NOT EXISTS `vendor` (
  `vendorID` int(11) NOT NULL,
  `vendorAddress` varchar(30) NOT NULL,
  `vendorName` varchar(30) NOT NULL,
  PRIMARY KEY (`vendorID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`vendorID`, `vendorAddress`, `vendorName`) VALUES
(10254, 'Reedsville, PA 17084', 'Corvette America'),
(10255, 'Sawyer MI 49125', 'Corvette Central'),
(10256, 'Burr Ridge, IL 60527', 'Corvette Mike');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customerphone`
--
ALTER TABLE `customerphone`
  ADD CONSTRAINT `customerphone_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`);

--
-- Constraints for table `includes`
--
ALTER TABLE `includes`
  ADD CONSTRAINT `includes_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `orderrecord` (`orderID`),
  ADD CONSTRAINT `includes_ibfk_2` FOREIGN KEY (`partNumber`) REFERENCES `part` (`partNumber`);

--
-- Constraints for table `orderrecord`
--
ALTER TABLE `orderrecord`
  ADD CONSTRAINT `orderrecord_ibfk_1` FOREIGN KEY (`customer`) REFERENCES `customer` (`custID`);

--
-- Constraints for table `supplies`
--
ALTER TABLE `supplies`
  ADD CONSTRAINT `supplies_ibfk_1` FOREIGN KEY (`vendorID`) REFERENCES `vendor` (`vendorID`),
  ADD CONSTRAINT `supplies_ibfk_2` FOREIGN KEY (`partNumber`) REFERENCES `part` (`partNumber`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
