-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 02, 2017 at 02:53 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlynhahang`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date DEFAULT NULL,
  `status` int(3) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `username`, `password`, `created_at`, `updated_at`, `status`) VALUES
(1, 'admin', 'admin', 'admin', '2017-06-18', '2017-06-20', 1),
(2, 'Xuân hùng ', 'xuanhung', '1', '2017-06-20', '2017-06-20', 1),
(17, 'Vũ Minh Tiến', 'tiencongchua', '123', '2017-06-20', '2017-06-20', 1),
(18, 'Tiến Công Chúa', 'tiencongchua2210', '1', '2017-06-21', '2017-06-21', 1),
(19, '1', '1', '1', '2017-06-23', '2017-06-23', 1),
(20, 'Tiến Công Chúa', '2', '2', '2017-06-26', '2017-06-26', 1);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `created_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `created_at`, `update_at`, `status`) VALUES
(4, 'Món ăn', '2017-06-19', '2017-06-20', 1),
(5, 'Hải sản', '2017-06-19', '2017-06-20', 1),
(6, 'Nước ngọt', '2017-06-19', '2017-06-20', 1),
(7, 'Bia', '2017-06-19', '2017-06-20', 1);

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `id` int(11) NOT NULL,
  `name` char(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `img_url` varchar(100) CHARACTER SET latin1 NOT NULL,
  `unit_price` float NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `status` int(3) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`id`, `name`, `category_id`, `description`, `img_url`, `unit_price`, `created_at`, `updated_at`, `status`) VALUES
(1, 'Thịt nướng', 4, 'Thịt cháy bà Tiến.', 'image01', 20000, '2017-06-19', '2017-06-19', 1),
(2, 'Gà nướng', 4, 'Gà nướng mật ong.', 'image01.png', 250000, '2017-06-19', '2017-06-20', 1),
(3, 'Coca Cola', 6, 'Đồ uống giải khát', 'image02.png', 10000, '2017-06-19', '2017-06-19', 1),
(4, 'Mực một nắng', 5, 'Mực ngon mà thối lắm.', 'img03.png', 500000, '2017-06-19', '2017-06-19', 1),
(6, 'Mực xào', 5, 'Mực ngon', 'imh06.png', 100000, '2017-06-19', '2017-06-19', 1),
(8, 'Beer Hà Nội', 7, 'Bia tươi', 'png', 10000, '2017-06-19', '2017-06-19', 1),
(9, 'Vịt quay', 4, 'Vừa ăn xong Quang ạ.', '123.png', 150000, '2017-06-19', '2017-06-19', 1),
(10, 'Gà luộc', 4, 'Gà mai ăn.', '1234.png', 100000, '2017-06-19', '2017-06-19', 1),
(11, 'Mắm tôm', 4, 'Nhập đi.', '12345.png', 10000, '2017-06-19', '2017-06-19', 1),
(12, 'Gà quay', 4, 'gà quay', 'ga.jpg', 120000, '2017-06-20', '2017-06-26', 1),
(13, 'bia sài gòn', 7, 'bia sài gòn', '/saigon.jpg', 100000, '2017-06-20', '2017-06-20', 1),
(14, 'bia hà nội', 7, 'bia hà nội\n', '/biahanoi.jpg', 150000, '2017-06-20', '2017-06-20', 1),
(15, 'pessi', 6, 'sản phẩm quốc tế', '/pessi.jpg', 10000, '2017-06-20', '2017-06-20', 1),
(16, 'cơm niêu singapo', 4, 'cơm niêu', '/comnieusingapo.jpg', 350000, '2017-06-20', '2017-06-20', 1),
(17, 'canh chua thịt băm', 5, 'canh chua thịt băm', '/canhchua.jpg', 20000, '2017-06-20', '2017-06-20', 1),
(18, 'gà', 4, 'gà quay', 'ga.jpg', 200000, '2017-06-20', '2017-06-20', 1),
(20, 'chả cuốn', 4, 'chả cuốn lá lốt', 'cha.jpg', 100000, '2017-06-20', '2017-06-20', 1),
(21, 'gà rang muối', 4, 'gà rang muối', 'ga.jpg', 100000, '2017-06-20', '2017-06-20', 1),
(22, 'ếch xào', 4, 'ếch xào xả ớt', 'ech.jpg', 50000, '2017-06-20', '2017-06-20', 1),
(23, 'Bia 333', 7, 'Bia 333 sài gòn', 'Bia333.jpg', 150000, '2017-06-21', '2017-06-21', 1),
(24, 'tôm hùm', 5, 'tôm hùm biển', 'tom123.jpg', 210000, '2017-06-21', '2017-06-27', 1),
(25, 'trà đá', 6, 'trà đá vỉa hè', 'trada.jpg', 30000, '2017-06-21', '2017-06-21', 1),
(26, 'bia sài gòn', 7, 'bia sài gòn chất lượng cao', 'biasaigon.jpg', 10000, '2017-06-21', '2017-06-26', 1),
(27, 'sứa', 5, 'sứa biển', 'sua.jpg', 500000, '2017-06-21', '2017-06-21', 1),
(28, 'mực nướng', 5, 'mực biển một nắng', 'mucnuong.jpg', 150000, '2017-06-21', '2017-06-26', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `unit_price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` float NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderdetail`
--

INSERT INTO `orderdetail` (`id`, `order_id`, `food_id`, `unit_price`, `quantity`, `total_price`, `created_at`, `updated_at`, `status`) VALUES
(55, 15, 3, 10000, 7, 70000, '2017-07-02', '2017-07-02', 1),
(56, 15, 4, 500000, 2, 1000000, '2017-07-02', '2017-07-02', 1),
(57, 15, 10, 100000, 1, 100000, '2017-07-02', '2017-07-02', 0),
(58, 15, 11, 10000, 1, 10000, '2017-07-02', '2017-07-02', 0),
(59, 16, 4, 500000, 1, 500000, '2017-07-02', '2017-07-02', 1),
(60, 16, 6, 100000, 1, 100000, '2017-07-02', '2017-07-02', 1),
(61, 16, 8, 10000, 1, 10000, '2017-07-02', '2017-07-02', 0),
(62, 17, 8, 10000, 1, 10000, '2017-07-02', '2017-07-02', 0),
(63, 18, 3, 10000, 1, 10000, '2017-07-02', '2017-07-02', 0),
(64, 19, 9, 150000, 1, 150000, '2017-07-02', '2017-07-02', 2);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date DEFAULT NULL,
  `total_price` float NOT NULL,
  `discount` int(11) NOT NULL,
  `real_price` float NOT NULL,
  `table_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `created_at`, `updated_at`, `total_price`, `discount`, `real_price`, `table_id`, `status`) VALUES
(15, '2017-07-02', '2017-07-02', 1070000, 20, 856000, 7, 1),
(16, '2017-07-02', '2017-07-02', 600000, 0, 600000, 7, 1),
(17, '2017-07-02', '2017-07-02', 10000, 0, 10000, 7, 0),
(18, '2017-07-02', '2017-07-02', 10000, 0, 10000, 1, 0),
(19, '2017-07-02', '2017-07-02', 150000, 0, 150000, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE `tables` (
  `id` int(11) NOT NULL,
  `name` char(50) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '1',
  `current_orders` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`id`, `name`, `created_at`, `updated_at`, `status`, `current_orders`) VALUES
(1, 'Bàn 1', '2017-06-20', '2017-07-02', 1, NULL),
(2, 'Bàn 2', '2017-06-20', '2017-07-02', 2, NULL),
(3, 'Bàn 3', '2017-06-20', '2017-06-26', 1, NULL),
(4, 'Bàn 4', '2017-06-20', '2017-06-20', 1, NULL),
(5, 'Bàn 5', '2017-06-20', '2017-06-26', 1, NULL),
(6, 'Bàn 6', '2017-06-20', '2017-06-20', 1, NULL),
(7, 'Bàn 7', '2017-06-20', '2017-07-02', 1, NULL),
(8, 'Bàn 8', '2017-06-20', '2017-06-20', 1, NULL),
(9, 'Bàn 9', '2017-06-20', '2017-06-20', 1, NULL),
(10, 'Bàn 10', '2017-06-20', '2017-06-20', 1, NULL),
(11, 'Bàn 11', '2017-06-20', '2017-06-20', 1, NULL),
(12, 'Bàn 12', '2017-06-20', '2017-06-20', 1, NULL),
(13, 'Bàn 13', '2017-06-20', '2017-06-20', 1, NULL),
(14, 'Bàn 14', '2017-06-20', '2017-06-20', 1, NULL),
(15, 'Bàn 15', '2017-06-20', '2017-06-20', 1, NULL),
(16, 'Bàn 16', '2017-06-20', '2017-06-20', 1, NULL),
(17, 'Bàn 17', '2017-06-20', '2017-06-20', 1, NULL),
(18, 'Bàn 18', '2017-06-20', '2017-06-20', 1, NULL),
(19, 'Bàn 19', '2017-06-20', '2017-06-20', 1, NULL),
(20, 'Bàn 20', '2017-06-20', '2017-06-20', 1, NULL),
(21, 'Bàn 21', '2017-06-20', '2017-06-20', 1, NULL),
(22, 'Bàn 22', '2017-06-20', '2017-06-20', 1, NULL),
(23, 'Bàn 23', '2017-06-20', '2017-06-20', 1, NULL),
(24, 'Bàn 24', '2017-06-20', '2017-06-20', 1, NULL),
(25, 'Bàn 25', '2017-06-20', '2017-06-20', 1, NULL),
(26, 'Bàn 26', '2017-06-20', '2017-06-20', 1, NULL),
(27, 'Bàn 27', '2017-06-20', '2017-06-20', 1, NULL),
(28, 'Bàn 28', '2017-06-20', '2017-06-20', 1, NULL),
(29, 'Bàn 29', '2017-06-20', '2017-06-20', 1, NULL),
(30, 'Bàn 30', '2017-06-20', '2017-06-20', 1, NULL),
(31, 'Bàn 31', '2017-06-20', '2017-06-20', 1, NULL),
(32, 'Bàn 32', '2017-06-20', '2017-06-20', 1, NULL),
(33, 'Bàn 33', '2017-06-20', '2017-06-20', 1, NULL),
(34, 'Bàn 34', '2017-06-20', '2017-06-20', 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`) USING BTREE;

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idFood` (`food_id`) USING BTREE,
  ADD KEY `idOrder` (`order_id`) USING BTREE;

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `table_id` (`table_id`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `orderdetail`
--
ALTER TABLE `orderdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `foods`
--
ALTER TABLE `foods`
  ADD CONSTRAINT `foods_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `foods` (`id`),
  ADD CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`table_id`) REFERENCES `tables` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
