drop table user1;
drop table user3;
drop table user4;
CREATE TABLE `user1`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(30)  NOT NULL,
    `age`  smallint(1)   NULL,
    `addr` varchar(255) NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`) USING BTREE
);

drop table user2;
CREATE TABLE `user2`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(30)  NULL,
    `age`  smallint(1)   NULL,
    `addr` varchar(255) NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`) USING BTREE
);

CREATE TABLE `user3`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(30)  NULL,
    `age`  smallint(1)   NULL,
    `addr` varchar(255) NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`) USING BTREE
)
;

CREATE TABLE `user4`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(30)  NULL,
    `age`  smallint(1)   NULL,
    `addr` varchar(255) NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`) USING BTREE
)
;
