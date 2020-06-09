CREATE SCHEMA IF NOT EXISTS `bankitapp` DEFAULT CHARACTER SET utf8 ;
USE `bankitapp` ;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminpassword';
GRANT ALL PRIVILEGES ON bankitapp.* TO 'admin'@'localhost';

SET @@global.time_zone = '+02:00';
SET @@session.time_zone = '+02:00';

CREATE USER 'bankit'@'localhost' IDENTIFIED BY 'bankitpassword';
GRANT ALL PRIVILEGES ON bankitapp.* TO 'bankit'@'localhost';