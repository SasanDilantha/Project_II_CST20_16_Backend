--
-- Dumping data for table `farm`
--

INSERT INTO `farm` (`farm_id`, `farm_name`, `farm_location`, `farm_code`) VALUES
(1, 'Evil Farm', 'Kandy', 'KAN-7023'),
(2, 'Sula Farm', 'Kagalle', 'KAG-7211'),
(3, 'Niwa Farm', 'Colombo', 'COL-7814');


--
-- Dumping data for table `placement`
--

INSERT INTO `placement` (`placement_id`, `placement_code`, `farm_id`) VALUES
(1, 'KAN/PL/4240/1', 1),
(2, 'KAN/PL/4240/2', 1),
(3, 'KAN/PL/4240/3', 1),
(4, 'KAN/PL/4240/4', 1),
(5, 'KAN/PL/4240/5', 1),
(6, 'COL/PL/8272/1', 3),
(7, 'COL/PL/8272/2', 3);