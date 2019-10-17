create table user (
  id int not null auto_increment primary key,
  name varchar(50) not null,
  email varchar(50) not null,
  cpf varchar(50) not null,
  type varchar(50) not null
);

insert into user (id, name, email, cpf, type) values
(1, 'Carlos Alves', 'carlos.alves@gmail.com', '448.548.854-12', 'PROFESSOR'),
(2, 'Tiago Santos', 'tiago.santos@gmail.com', '325.845.554-44', 'PROFESSOR'),
(3, 'Rafael Gomes', 'rafael.gomes@gmail.com', '236.887.541-56', 'ALUNO'),
(4, 'João Andrade', 'joao.andrade@gmail.com', '558.874.651-99', 'ALUNO'),
(5, 'Igor Neves', 'igor.neves@gmail.com', '985.232.478-59', 'ALUNO');
