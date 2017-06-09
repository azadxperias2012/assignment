DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS country_lookup;
DROP TABLE IF EXISTS category_lookup;
DROP TABLE IF EXISTS company;

-- COMPANY TABLE
CREATE TABLE company(
	id VARCHAR(2) NOT NULL,
    budget DOUBLE NOT NULL,
	bid INT NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO company(id, budget, bid) VALUES("C1", 1, 10);
INSERT INTO company(id, budget, bid) VALUES("C2", 2, 30);
INSERT INTO company(id, budget, bid) VALUES("C3", 3, 5);

-- COUNTRY TABLE
CREATE TABLE country(
    country_code VARCHAR(2) NOT NULL,
    PRIMARY KEY(country_code)
);

INSERT INTO country(country_code) VALUES ("US");
INSERT INTO country(country_code) VALUES ("IN");
INSERT INTO country(country_code) VALUES ("FR");
INSERT INTO country(country_code) VALUES ("RU");

-- COUNTRY LOOKUP TABLE
CREATE TABLE country_lookup(
    company_id VARCHAR(2) NOT NULL,
	country_code VARCHAR(2) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company(id),
	FOREIGN KEY (country_code) REFERENCES country(country_code)
);

INSERT INTO country_lookup(company_id, country_code) VALUES ("C1", "US");
INSERT INTO country_lookup(company_id, country_code) VALUES ("C1", "FR");
INSERT INTO country_lookup(company_id, country_code) VALUES ("C2", "IN");
INSERT INTO country_lookup(company_id, country_code) VALUES ("C2", "US");
INSERT INTO country_lookup(company_id, country_code) VALUES ("C3", "US");
INSERT INTO country_lookup(company_id, country_code) VALUES ("C3", "RU");

-- CATEGORY TABLE
CREATE TABLE category(
    category_name VARCHAR(32) NOT NULL,
    PRIMARY KEY(category_name)
);

INSERT INTO category(category_name) VALUES ("Automobile");
INSERT INTO category(category_name) VALUES ("Finance");
INSERT INTO category(category_name) VALUES ("IT");

-- CATEGORY LOOKUP TABLE
CREATE TABLE category_lookup(
    company_id VARCHAR(2) NOT NULL,
	category_name VARCHAR(32) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company(id),
	FOREIGN KEY (category_name) REFERENCES category(category_name)
);

INSERT INTO category_lookup(company_id, category_name) VALUES ("C1", "Automobile");
INSERT INTO category_lookup(company_id, category_name) VALUES ("C1", "Finance");
INSERT INTO category_lookup(company_id, category_name) VALUES ("C2", "Finance");
INSERT INTO category_lookup(company_id, category_name) VALUES ("C2", "IT");
INSERT INTO category_lookup(company_id, category_name) VALUES ("C3", "IT");
INSERT INTO category_lookup(company_id, category_name) VALUES ("C3", "Automobile");

-- DB SNAPSHOT
-- http://localhost:8080/stockexchange/webapi/exchange/dbsnapshot
SELECT company.id AS CompanyId,
 (SELECT GROUP_CONCAT(country_code SEPARATOR ",") FROM country_lookup WHERE company_id = company.id) AS Countries,
 CONCAT(company.budget, '$') AS Budget, CONCAT(company.bid, ' cent') AS Bid,
 (SELECT GROUP_CONCAT(category_name SEPARATOR ",") FROM category_lookup WHERE company_id = company.id) AS Category
 FROM company; 
 
 
-- BASE TARGETING
SELECT a.id AS CompanyId, b.country_code AS Country, a.budget AS Budget, a.bid AS Bid, c.category_name AS Category
 FROM company a
  LEFT JOIN country_lookup b ON a.id = b.company_id && b.country_code = "US"
  LEFT JOIN category_lookup c ON a.id = c.company_id && c.category_name = "Automobile";

-- reset budget query
-- UPDATE company SET budget = 1 WHERE id = "C1";

-- EXCHANGE ENDPOINT
-- http://localhost:8080/stockexchange/webapi/exchange/matchcompany?countrycode=US&category=Automobile&basebid=10

-- LOGS SCREENSHOT
-- http://localhost:8080/stockexchange/webapi/exchange/logs_screenshot
