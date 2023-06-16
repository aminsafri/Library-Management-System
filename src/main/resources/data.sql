insert into book(book_name, author) values ('Legends of Archeus', 'Adam');
insert into book(book_name, author) values ('Tears of Kingdom', 'Gary');
insert into book(book_name, author) values ('Summer Time', 'Rowan');
insert into book(book_name, author) values ('Rabbit Journey', 'Mary');

insert into employee(first_name, last_name) values ('Ray', 'Charles');
insert into employee(first_name, last_name) values ('Gervonta', 'Davis');

insert into borrower(first_name, last_name, email, phone_number) values ('Ahmad', 'Saiful', 'ahmad@gmail.com', 01122233);
insert into borrower(first_name, last_name, email, phone_number) values ('Wan', 'Ahmad', 'wan@gmail.com', 01152924);
insert into borrower(first_name, last_name, email, phone_number) values ('Nik', 'Adam', 'nik@gmail.com', 01137566);
insert into borrower(first_name, last_name, email, phone_number) values ('Abu', 'Bakar', 'abu@gmail.com', 01169996);

INSERT INTO copy (start_date, end_date, status) VALUES ('2023-08-21', '2023-09-21', 'Not Available');
INSERT INTO copy (start_date, end_date, status) VALUES ('2023-08-25', '2023-09-25', 'Not Available');
INSERT INTO copy (start_date, end_date, status) VALUES ('2023-08-30', '2023-09-30', 'Not Available');
INSERT INTO copy (start_date, end_date, status) VALUES ('2023-09-05', '2023-10-05', 'Not Available');

insert into copy(start_date, end_date, status) values (null, null, 'Available');
insert into copy(start_date, end_date, status) values (null, null, 'Available');
insert into copy(start_date, end_date, status) values (null, null, 'Available');
insert into copy(start_date, end_date, status) values (null, null, 'Available');

update copy set book_id = 1 where copy_id = 1;
update copy set book_id = 1 where copy_id = 2;
update copy set book_id = 2 where copy_id = 3;
update copy set book_id = 2 where copy_id = 4;
update copy set book_id = 2 where copy_id = 5;
update copy set book_id = 3 where copy_id = 6;
update copy set book_id = 3 where copy_id = 7;
update copy set book_id = 4 where copy_id = 8;

update copy set borrower_id = 1 where copy_id = 1;
update copy set borrower_id = 3 where copy_id = 2;
update copy set borrower_id = 2 where copy_id = 3;
update copy set borrower_id = 1 where copy_id = 4;

INSERT INTO section(section_name, description, location)
VALUES ( 'Fiction', 'This section contains a variety of fictional books.', 'First Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Non-fiction', 'This section contains non-fictional books including biographies, history, etc.', 'Second Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Children', 'This section is for children books covering a range of topics suitable for kids.', 'Ground Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Sci-fi & Fantasy', 'Books in this section are all about science fiction and fantasy genres.', 'First Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Computer Science', 'This section contains books on computer science and programming.', 'Third Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Romance', 'This section contains romance novels, from historical romance to contemporary works.', 'Second Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Mystery & Thriller', 'Books in this section will keep you on the edge of your seat with suspense, thrillers, and mystery novels.', 'Third Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Business & Finance', 'This section contains books related to business, finance, and economics.', 'Fourth Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Self-help', 'Self-help books including personal development, motivation, and psychology can be found in this section.', 'First Floor');

INSERT INTO section(section_name, description, location)
VALUES ( 'Biography & Autobiography', 'Read about the lives of fascinating people in this section containing biographies and autobiographies.', 'Second Floor');


insert into employee_section(employee_id, section_id) values (001,1);
insert into employee_section(employee_id, section_id) values (001,2);
insert into employee_section(employee_id, section_id) values (002,3);
insert into employee_section(employee_id, section_id) values (002,4);

update book set section_id = 1
where book_id = 1;

update book set section_id = 2
where book_id = 2;

update book set section_id = 3
where book_id = 3;

update book set section_id = 2
where book_id = 4;