create table equipment (
  id int not null auto_increment primary key,
  name varchar(50) not null,
  laboratory_id int not null,

  constraint equipment_fk_laboratory foreign key (laboratory_id) references laboratory (id)
);

insert into equipment (id, name, laboratory_id) values
(1, 'Computador 1', 1),
(2, 'Computador 2', 1),
(3, 'Computador 3', 1),
(4, 'Computador 4', 1),
(5, 'Computador 5', 1),
(6, 'Computador 6', 1),
(7, 'Computador 7', 1),
(8, 'Computador 8', 1),
(9, 'Computador 9', 1),
(10, 'Computador 10', 1),
(11, 'Computador 11', 2),
(12, 'Computador 12', 2),
(13, 'Computador 13', 2),
(14, 'Computador 14', 2),
(15, 'Computador 15', 2),
(16, 'Computador 16', 2),
(17, 'Computador 17', 2),
(18, 'Computador 18', 2),
(19, 'Computador 19', 2),
(20, 'Computador 20', 2);
