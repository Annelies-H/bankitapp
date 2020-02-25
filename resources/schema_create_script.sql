CREATE SCHEMA IF NOT EXISTS `bankitapp` DEFAULT CHARACTER SET utf8 ;
USE `bankitapp` ;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminpassword';
GRANT ALL PRIVILEGES ON bankitapp.* TO 'admin'@'localhost';