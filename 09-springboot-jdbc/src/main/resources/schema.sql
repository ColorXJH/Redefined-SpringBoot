
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `fruit`;
CREATE TABLE `fruit` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `fruitName` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

