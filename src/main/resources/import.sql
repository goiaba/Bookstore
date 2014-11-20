--
-- JBoss, Home of Professional Open Source
-- Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into Member (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212')


insert into bookstore.Book(isbn, title, author, price) values ('1020304050', 'The Art of Software Testing','Glenford J. Myers', 55); 
insert into bookstore.Book(isbn, title, author, price) values ('1122334455', 'Scala in Depth','Joshua D. Suereth', 56.90); 
insert into bookstore.Book(isbn, title, author, price) values ('1342357586', 'Programming Interviews Exposed','John Mongan', 45.90); 
insert into bookstore.Book(isbn, title, author, price) values ('6857465342', 'Introduction to Software Testing','Paul Ammann',159.90);
insert into bookstore.Book(isbn, title, author, price) values ('8932928120', 'Rest in Practice','Jim Webber',89.89);
insert into bookstore.Book(isbn, title, author, price) values ('2902910291', 'Apache CXF Web Service Development','Naveen Balani',39);

