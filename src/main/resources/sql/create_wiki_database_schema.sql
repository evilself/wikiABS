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

CREATE TABLE SECURITY_INFO
(
	SECURITY_INFO_ID int not null AUTO_INCREMENT,
	SECURITY_QUESTION varchar(128) not null,
    SECURITY_ANSWER VARCHAR(64) NOT NULL,
    USERNAME varchar(32) not null,
	PRIMARY KEY (SECURITY_INFO_ID)
);	

CREATE TABLE User
(
	USER_ID int not null AUTO_INCREMENT,
    CREATED_TIME datetime not null default now(),
    MODIFIED_TIME timestamp not null,
	FIRST_NAME varchar(64) not null,
	LAST_NAME varchar(64) not null,
	USERNAME varchar(32) not null,
    PASSWORD varchar(64) not null,
    ENABLED tinyint not null default 1,
    EMAIL varchar(64) null,    
    USER_ROLE_ID bigint(20) not null default 2,
	PRIMARY KEY (USER_ID)
);

CREATE TABLE Product
(
	PRODUCT_ID int not null AUTO_INCREMENT,
    CREATED_TIME datetime not null default now(),
    MODIFIED_TIME timestamp not null,
	PRODUCT_NAME varchar(64) not null,
    PRODUCT_IDENTITY varchar(64) not null,
    PRODUCT_DESCRIPTION varchar(256) null, 
    CUSTOM tinyint not null default 0,
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
    CONTENT_TYPE varchar(64) not null,
    ARTICLE_ID int null,
    FOREIGN KEY (ARTICLE_ID) REFERENCES Article(ARTICLE_ID),
	PRIMARY KEY (ATTACHMENT_ID)
);	

insert into product (PRODUCT_DESCRIPTION, PRODUCT_IDENTITY, PRODUCT_NAME) values ("Risk Management and Tracking", "CompliancePro", "CompliancePro");
insert into product (PRODUCT_DESCRIPTION, PRODUCT_IDENTITY, PRODUCT_NAME) values ("Imaging and Tracking", "BankManagerElite", "BankManagerElite");
insert into product (PRODUCT_DESCRIPTION, PRODUCT_IDENTITY, PRODUCT_NAME) values ("Loan Origination", "CompliancePro_Loans", "CompliancePro Loans and Deposits");

insert into user_roles (ROLE, USERNAME) values ("ADMIN", "admin");
insert into user (FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, USER_ROLE_ID) values ("Admin", "Adminov", '$2a$10$HCjaHWSbeObVjSWdJkq2n.udUSxSYnuN2FpBvQ9iOYxbTtgGpeMaa', "admin", 1);
insert into security_info (SECURITY_QUESTION, SECURITY_ANSWER, USERNAME) values ("What is the best car?", "Porsche", "admin");
drop database wikiABS;

select * from article;
select * from product;
select * from user;
select * from user_roles;
select * from attachment;
select * from security_info;

delete from user_roles where role_id = 2;
delete from article where article_id = 9;
delete from attachment where attachment_id in (1);
delete from product where product_id =4;

