create table reserve_laboratory (
  id int not null auto_increment primary key,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  user_id int not null,
  laboratory_id int not null,

  constraint reserve_laboratory_fk_user foreign key (user_id) references user (id),
  constraint reserve_laboratory_fk_laboratory foreign key (laboratory_id) references laboratory (id)
);

insert into reserve_laboratory (id, start_date, end_date, user_id, laboratory_id) values
(1, '2019-10-20 10:00:00', '2019-10-20 15:00:00', 1, 2),
(2, '2019-11-20 09:30:00', '2019-11-20 13:30:00', 2, 1);
