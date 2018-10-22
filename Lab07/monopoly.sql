--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PlayerProp;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Property;
DROP TABLE IF EXISTS WinnerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Winner;


-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Winner (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE WinnerGame (
	gameID integer REFERENCES Game(ID), 
	winnerID integer REFERENCES Winner(ID),
	score integer
	);
	
CREATE TABLE Property (
	ID integer PRIMARY KEY,
	propertyName varchar(50)
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY,
	name varchar(50),
	cash integer,
	located integer REFERENCES Property(ID)
	);
	
CREATE TABLE PlayerProp (
	playerID integer REFERENCES Player(ID),
	propID integer REFERENCES Property(ID),
	numHouses integer,
	numHotels integer
);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Winner TO PUBLIC;
GRANT SELECT ON WinnerGame TO PUBLIC;
GRANT SELECT ON Property TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerProp TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Winner(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Winner VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Winner VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO WinnerGame VALUES (1, 1, 0.00);
INSERT INTO WinnerGame VALUES (1, 2, 0.00);
INSERT INTO WinnerGame VALUES (1, 3, 2350.00);
INSERT INTO WinnerGame VALUES (2, 1, 1000.00);
INSERT INTO WinnerGame VALUES (2, 2, 0.00);
INSERT INTO WinnerGame VALUES (2, 3, 500.00);
INSERT INTO WinnerGame VALUES (3, 2, 0.00);
INSERT INTO WinnerGame VALUES (3, 3, 5500.00);

INSERT INTO Property VALUES (1, 'GO');
INSERT INTO Property VALUES (2, 'Mediterranean Avenue');
INSERT INTO Property VALUES (3, 'Community Chest');
INSERT INTO Property VALUES (4, 'Baltic Avenue');
INSERT INTO Property VALUES (5, 'Income Tax');
INSERT INTO Property VALUES (6, 'Reading Railroad');
INSERT INTO Property VALUES (7, 'Oriental Avenue');
INSERT INTO Property VALUES (8, 'Chance');
INSERT INTO Property VALUES (9, 'Vermont Avenue');
INSERT INTO Property VALUES (10, 'Connecticut Avenue');

INSERT INTO Player VALUES (1, 'Bonnie', 2000, 1);
INSERT INTO Player VALUES (2, 'Meg', 6523, 4);
INSERT INTO Player VALUES (3, 'Jules', 3553, 3);
INSERT INTO Player VALUES (4, 'Joel', 652, 5);

INSERT INTO PlayerProp VALUES (1, 6, 0, 0);
INSERT INTO PlayerProp VALUES (2, 7, 0, 1);
INSERT INTO PlayerProp VALUES (2, 9, 0, 1);
INSERT INTO PlayerProp VALUES (2, 10, 4, 0);
INSERT INTO PlayerProp VALUES (3, 2, 0, 1);
INSERT INTO PlayerProp VALUES (3, 4, 0, 1);