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
insert into bookstore.Book(isbn, title, author, price, description) values ('1020304050', 'The Art of Software Testing','Glenford J. Myers', 55, 'The classic, landmark work on software testing The hardware and software of computing have changed markedly in the three decades since the first edition of The Art of Software Testing...'); 
insert into bookstore.Book(isbn, title, author, price, description) values ('1122334455', 'Scala in Depth','Joshua D. Suereth', 56.90, 'Scala in Depth is a unique new book designed to help you integrate Scala effectively into your development process...'); 
insert into bookstore.Book(isbn, title, author, price, description) values ('1342357586', 'Programming Interviews Exposed','John Mongan', 45.90, 'Be prepared for your next job interview with this tried-and-true advice In today s tight job market, competition for programming jobs is hotter than ever. This third edition of a popular guide to programming interviews...'); 
insert into bookstore.Book(isbn, title, author, price, description) values ('6857465342', 'Introduction to Software Testing','Paul Ammann',159.90, 'Extensively class tested, this text takes an innovative approach to explaining the process of software testing: it defines testing...');
insert into bookstore.Book(isbn, title, author, price, description) values ('8932928120', 'REST in Practice: Hypermedia and Systems Architecture','Jim Webber',89.89, 'In this insightful book, three SOA experts provide a down-to-earth explanation of REST and demonstrate how you can develop simple and elegant distributed hypermedia ...');
insert into bookstore.Book(isbn, title, author, price, description) values ('2902910291', 'Apache CXF Web Service Development','Naveen Balani',39, 'Learn how to design and develop SOAP and RESTful web services using the Apache CXF framework...');

