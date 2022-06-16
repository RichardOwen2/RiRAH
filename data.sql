-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2022 at 01:46 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `data`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Role` varchar(5) NOT NULL DEFAULT 'user',
  `nama` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nomor` varchar(25) NOT NULL,
  `alamat` varchar(150) NOT NULL,
  `Account Created` date NOT NULL DEFAULT current_timestamp(),
  `RiRAHPay` int(12) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `Role`, `nama`, `email`, `nomor`, `alamat`, `Account Created`, `RiRAHPay`) VALUES
(0, 'RiRAH', '', 'admin', ' ', ' ', ' ', ' ', '2022-05-31', 7650000),
(1, 'Richard', 'kucing123', 'user', 'Richard Owen Hoan', '11211075@student.itk.ac.id', '085752694322', 'Balikpapan, Jl.Dahlia barat blok i-4 No.4', '2022-05-31', 47249750),
(2, 'Helmi', 'suamiadel', 'user', 'Helmi', '11211043@student.itk.ac.id', ' 085346901814', 'Jl. Mars Gg Venus RT 77 RW 88 No 99 Kel Pluto Kec Bima Sakti Prov Muara Badak', '2022-05-31', 1320000),
(3, 'Arwin', 'arwin123', 'user', 'Arwin Marinta', '11211019@itk.ac.id', '082158729742', 'Jl. Giri rejo 2, banyumas KM 15 ', '2022-06-06', 3372150),
(4, 'AliRajab39', 'bismillah03', 'user', 'Nur Ali Rajab', '11211067@student.itk.ac.id', '081253611869', 'Sotek, RT. 10 Kec. Penajam, Kab.Penajam Paser Utara', '2022-06-16', 10000000),
(17, 'bapak', 'adwdwadwa', 'user', 'dwwad', 'dwawad', 'dwadwa', 'wdadwa', '2022-06-15', 50000),
(18, 'segrsgesged', 'gseegegg', 'user', 'ssegsegseg', 'sgeegseg', 'seggeeg', 'seggegegesge', '2022-06-16', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(5) NOT NULL,
  `date` date NOT NULL,
  `diskonpersen` int(3) NOT NULL,
  `diskonharga` int(20) NOT NULL,
  `idbarang` int(5) NOT NULL,
  `jumlah` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `date`, `diskonpersen`, `diskonharga`, `idbarang`, `jumlah`) VALUES
(0, '2022-06-12', 20, 100000, 1, 24),
(4, '2022-06-13', 30, 30000, 3, 34),
(5, '2022-06-16', 5, 250000, 1, 50),
(6, '2022-06-17', 10, 1000000, 2, 80);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `harga` int(15) NOT NULL,
  `stock` int(4) NOT NULL,
  `detail` varchar(250) NOT NULL,
  `Item Created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `nama`, `harga`, `stock`, `detail`, `Item Created`) VALUES
(1, 'Golden', 7500, 3893, 'Undangan ini bernama Golden \r\nyang bertema Emas diatas kegelapan yang \r\nmemiliki arti dimanapun kamu\r\nberada ketika dirimu memiliki potensi dan \r\nkamu mampu mempertahankan kelebihan itu percayalah dirimu akan\r\nselalu bersinar sekalipun digelap', '2022-06-13 15:01:52'),
(2, 'Black And White', 9200, 8648, 'Undangan ini bernama Black and White yang bertemakan sisi elegan yang memiliki arti semua orang mempunyai sisi misteriusnya masing-masing yang tidak diketahui oleh orang lain kecuali pasangan dia sendiri, dan dari undangan ini sendiri menyampaikan pe', '2022-06-14 14:30:35'),
(3, 'Rose Love', 6500, 6820, 'Undangan ini bernama Rose Love yang bertemakan kebahagiaan dan penuh cinta yang memiliki arti kehidupan bisa selayaknya di surga ketika dua seorang insan mampu bersama dikala suka dan duka menjalaninya dengan penuh iklas dan damai. ', '2022-06-14 14:36:27');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id` int(5) NOT NULL,
  `Nomor` varchar(60) NOT NULL,
  `username` varchar(30) NOT NULL,
  `Tanggal` date NOT NULL DEFAULT current_timestamp(),
  `idItem` int(5) NOT NULL,
  `Jumlah` int(3) NOT NULL,
  `Harga` int(15) NOT NULL,
  `Pembayaran` varchar(10) NOT NULL,
  `status` varchar(30) NOT NULL DEFAULT 'Menunggu Konfirmasi',
  `lastConfirm` date NOT NULL DEFAULT current_timestamp(),
  `code` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `Nomor`, `username`, `Tanggal`, `idItem`, `Jumlah`, `Harga`, `Pembayaran`, `status`, `lastConfirm`, `code`) VALUES
(4, '080101-44160622-1230053', 'Richard', '2022-06-01', 1, 123, 876375, 'RiRAHPay', 'Menunggu Konfirmasi', '2022-06-16', 0),
(6, '080201-01160622-0600051', 'Helmi', '2022-06-16', 1, 60, 427500, 'COD', 'Menunggu Konfirmasi', '2022-06-16', 0),
(7, '190103-02160622-9550004', 'Richard', '2022-06-16', 3, 955, 6207500, 'Gopay', 'Menunggu Konfirmasi', '2022-06-16', 0),
(13, '180302-44160622-0230005', 'Arwin', '2022-06-16', 2, 23, 211600, 'RiRAHPay', 'Menunggu Konfirmasi', '2022-06-16', 0),
(14, '450301-44160622-0500051', 'Arwin', '2022-06-16', 1, 50, 356250, 'RiRAHPay', 'Pesanan sedang diproses', '2022-06-16', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `namaPria` varchar(30) NOT NULL,
  `namaWanita` varchar(30) NOT NULL,
  `namaAyahPria` varchar(30) NOT NULL,
  `namaIbuPria` varchar(30) NOT NULL,
  `namaAyahWanita` varchar(30) NOT NULL,
  `namaIbuWanita` varchar(30) NOT NULL,
  `tanggalAcara` varchar(25) NOT NULL,
  `alamatAcara` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `namaPria`, `namaWanita`, `namaAyahPria`, `namaIbuPria`, `namaAyahWanita`, `namaIbuWanita`, `tanggalAcara`, `alamatAcara`) VALUES
(1, 'Richard', 'awertg', 'aretgw', 'arew', 'aretg', 'aretwg', 'aretg', 'aretwg arew', 'raewt'),
(2, 'Helmi', 'Helmi', 'Sappzen', 'Asep', 'Wina', 'Syuaep', 'Tukiyem', '25-06-2022 12:00 PM', 'Jl Survivor Gg Alim No 9'),
(3, 'Arwin', 'Arwin Marinta', 'Cheriza', 'Helmi', 'sappzen', 'Rajab', 'Fanny', '30-06-2022 06:00 PM', 'Grand City Blok 9 No 18');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nama` (`nama`),
  ADD UNIQUE KEY `nama_2` (`nama`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Nomor` (`Nomor`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
