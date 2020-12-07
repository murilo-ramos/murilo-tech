CREATE DATABASE `company`;

CREATE TABLE `employees` (
  `id`     varchar(100) NOT NULL,
  `name`   varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone`  varchar(255) DEFAULT NULL,
  `hire_date`  datetime DEFAULT NULL,
  `import_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;