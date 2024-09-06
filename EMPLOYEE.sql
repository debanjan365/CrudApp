DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `SALARY` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
insert  into `employee`(`ID`,`NAME`,`AGE`,`SALARY`) values (1,'Peter','abc@gmail.com',90000),(2,'Dave','def@gmail.com',6000);
