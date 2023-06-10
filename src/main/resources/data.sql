insert into book(book_name, author) values ('Legends of Archeus', 'Adam');
insert into book(book_name, author) values ('Tears of Kingdom', 'Gary');
insert into book(book_name, author) values ('Summer Time', 'Rowan');
insert into book(book_name, author) values ('Rabbit Journey', 'Mary');

insert into employees(employee_id, first_name, last_name) values (111, 'Ray', 'Charles');
insert into employees(employee_id, first_name, last_name) values (112, 'Gervonta', 'Davis');

insert into borrower(first_name, last_name, email, phone_number) values ('Ahmad', 'Saiful', 'ahmad@gmail.com', 01122233);
insert into borrower(first_name, last_name, email, phone_number) values ('Wan', 'Ahmad', 'wan@gmail.com', 01152924);
insert into borrower(first_name, last_name, email, phone_number) values ('Nik', 'Adam', 'nik@gmail.com', 01137566);
insert into borrower(first_name, last_name, email, phone_number) values ('Abu', 'Bakar', 'abu@gmail.com', 01169996);

INSERT INTO section(section_id, section_name, description, location)
VALUES (1, 'Fiction', 'This section contains a variety of fictional books.', 'First Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (2, 'Non-fiction', 'This section contains non-fictional books including biographies, history, etc.', 'Second Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (3, 'Children', 'This section is for children books covering a range of topics suitable for kids.', 'Ground Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (4, 'Sci-fi & Fantasy', 'Books in this section are all about science fiction and fantasy genres.', 'First Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (5, 'Computer Science', 'This section contains books on computer science and programming.', 'Third Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (6, 'Romance', 'This section contains romance novels, from historical romance to contemporary works.', 'Second Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (7, 'Mystery & Thriller', 'Books in this section will keep you on the edge of your seat with suspense, thrillers, and mystery novels.', 'Third Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (8, 'Business & Finance', 'This section contains books related to business, finance, and economics.', 'Fourth Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (9, 'Self-help', 'Self-help books including personal development, motivation, and psychology can be found in this section.', 'First Floor');

INSERT INTO section(section_id, section_name, description, location)
VALUES (10, 'Biography & Autobiography', 'Read about the lives of fascinating people in this section containing biographies and autobiographies.', 'Second Floor');

update book set borrower_id = 1
where book_id = 1;


update book set borrower_id = 2
where book_id = 2;

update book set borrower_id = 3
where book_id = 3;

update book set borrower_id = 2
where book_id = 4;