create table certificates
(
  certificate_id       BIGSERIAL PRIMARY KEY,
  name                 VARCHAR(100),
  description          VARCHAR(1000),
  price                numeric,
  date_of_creation     date,
  date_of_modification date,
  duration             int
);

create table tags
(
  tag_id BIGSERIAL PRIMARY KEY,
  name   VARCHAR(100)
);

create table certificate_tag
(
  certificate_id BIGSERIAL,
  tag_id         BIGSERIAL,
  FOREIGN KEY (certificate_id) references certificates (certificate_id) on update cascade on DELETE cascade,
  FOREIGN KEY (tag_id) references tags (tag_id) on update cascade on DELETE cascade
);


insert into certificates (name, description, price, date_of_creation, date_of_modification, duration)
VALUES ('Robbo', 'machine', 26.23, '2015-07-02', '2017-09-20', 26);

insert into certificates (name, description, price, date_of_creation, date_of_modification, duration)
VALUES ('Studge', 'dancer', 15.15, '2015-07-02', '2017-09-20', 15);

insert into tags(name)
values ('splash');
insert into tags(name)
values ('king');

insert into certificate_tag (certificate_id, tag_id)
VALUES (1, 1);
insert into certificate_tag (certificate_id, tag_id)
VALUES (1, 2);
insert into certificate_tag (certificate_id, tag_id)
VALUES (2, 2);