create database wikiABS;

use wikiABS;

CREATE TABLE User_Roles
(
	ROLE_ID int not null AUTO_INCREMENT,
	ROLE varchar(32) not null,
    USERNAME VARCHAR(32) NOT NULL,
    UNIQUE KEY uni_username_role (ROLE,USERNAME),   
	PRIMARY KEY (ROLE_ID)
);	

CREATE TABLE User
(
	USER_ID int not null AUTO_INCREMENT,
    CREATED_TIME timestamp,
    MODIFIED_TIME timestamp,
	FIRST_NAME varchar(64) not null,
	LAST_NAME varchar(64) not null,
	USERNAME varchar(32) not null,
    PASSWORD varchar(64) not null,
    ENABLED tinyint not null default 1,
    EMAIL varchar(64) null,    
	PRIMARY KEY (USER_ID)
);

CREATE TABLE Product
(
	PRODUCT_ID int not null AUTO_INCREMENT,
    CREATED_TIME timestamp,
    MODIFIED_TIME timestamp,
	PRODUCT_NAME varchar(64) not null,    
	PRIMARY KEY (PRODUCT_ID)
);

CREATE TABLE Article
(
	ARTICLE_ID int NOT NULL AUTO_INCREMENT,
    CREATED_TIME timestamp,
    MODIFIED_TIME timestamp,
	TITLE varchar(128) not null,
	DESCRIPTION text,
    TAG text,
    PRODUCT_ID int DEFAULT NULL,
    CREATED_BY_USER_ID int not null,
	MODIFIED_BY_USER_ID int null,
    FOREIGN KEY (CREATED_BY_USER_ID) REFERENCES User(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES Product(PRODUCT_ID),
	PRIMARY KEY (ARTICLE_ID)
); 

CREATE TABLE Attachment
(
	ATTACHMENT_ID int not null AUTO_INCREMENT,
    CREATED_TIME timestamp,
    MODIFIED_TIME timestamp,
	ATTACHMENT_TITLE varchar(256) not null,
	ACTUAL_FILENAME varchar(256) not null,
	ATTACHMENT longblob not null,
    ATTACHMENT_TYPE varchar(32) not null,
    ARTICLE_ID int null,
    FOREIGN KEY (ARTICLE_ID) REFERENCES Article(ARTICLE_ID),
	PRIMARY KEY (ATTACHMENT_ID)
);	


drop database wikiABS;

select * from article;
select * from product;
select * from user;
select * from user_roles;
select * from attachment;

delete from user_roles where role_id = 2;
delete from article where article_id = 9;
delete from attachment where attachment_id in (1,2,3,4,5,6,7,8,9);

select username,password, enabled from user where username='bm';
select username, role from user_roles where username='bm';