create table reserve_equipment (
  id int not null auto_increment primary key,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  user_id int not null,
  equipment_id int not null,

  constraint reserve_equipment_fk_user foreign key (user_id) references user (id),
  constraint reserve_equipment_fk_equipment foreign key (equipment_id) references equipment (id)
);

insert into reserve_equipment (id, start_date, end_date, user_id, equipment_id) values
(1, '2019-10-10 10:00:00', '2019-10-10 11:00:00', 3, 1),
(2, '2019-11-10 09:30:00', '2019-11-10 10:30:00', 4, 2);
