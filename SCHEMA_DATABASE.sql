-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 03-Nov-2019 às 16:33
-- Versão do servidor: 5.7.27-0ubuntu0.19.04.1
-- versão do PHP: 7.2.24-0ubuntu0.19.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Banco de dados: `finances_manager`
CREATE DATABASE IF NOT EXISTS finances_manager;
USE finances_manager;

-- --------------------------------------------------------
-- Estrutura da tabela `expenses`

CREATE TABLE `expenses` (
  `exp_id` bigint(20) NOT NULL,
  `exp_date_pay` date NOT NULL,
  `exp_description` varchar(255) NOT NULL,
  `exp_paid` varchar(255) NOT NULL,
  `exp_value_pay` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
-- Estrutura da tabela `revenues`

CREATE TABLE `revenues` (
  `rev_id` bigint(20) NOT NULL,
  `rev_date_receiveble` datetime NOT NULL,
  `rev_description` varchar(255) NOT NULL,
  `rev_receivebled` varchar(255) NOT NULL,
  `rev_value_receiveble` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
-- Estrutura da tabela `roles`

CREATE TABLE `roles` (
  `usr_username` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Extraindo dados da tabela `roles`
INSERT INTO `roles` (`usr_username`, `role`) VALUES
('suporte', 'ADM');

-- --------------------------------------------------------
-- Estrutura da tabela `users`

CREATE TABLE `users` (
  `usr_id` bigint(20) NOT NULL,
  `usr_name` varchar(255) NOT NULL,
  `usr_password` varchar(255) NOT NULL,
  `usr_username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Extraindo dados da tabela `users`
INSERT INTO `users` (`usr_id`, `usr_name`, `usr_password`, `usr_username`) VALUES
(1, 'suporte', 'd49f07ad467af059d37241322a0c9d7bbf0b18ed8ac23320a08b516578dfc241', 'suporte');


-- Índices para tabelas despejadas

-- --------------------------------------------------------
-- Índices para tabela `expenses`

ALTER TABLE `expenses`
  ADD PRIMARY KEY (`exp_id`);

-- Índices para tabela `revenues`
ALTER TABLE `revenues`
  ADD PRIMARY KEY (`rev_id`);

-- Índices para tabela `roles`
ALTER TABLE `roles`
  ADD PRIMARY KEY (`usr_username`);

-- Índices para tabela `users`
ALTER TABLE `users`
  ADD PRIMARY KEY (`usr_id`),
  ADD UNIQUE KEY `UK_22s01l2aw28k7su6b1mrr4m0u` (`usr_username`);

-- AUTO_INCREMENT de tabelas despejadas

-- --------------------------------------------------------
-- AUTO_INCREMENT de tabela `expenses`

ALTER TABLE `expenses`
  MODIFY `exp_id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT de tabela `revenues`

ALTER TABLE `revenues`
  MODIFY `rev_id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT de tabela `users`

ALTER TABLE `users`
  MODIFY `usr_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
