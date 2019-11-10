create table login (
  email varchar(50) not null,
  password varchar(255) not null,

  constraint user_uk_email unique key (email)
);

insert into login (email, password) values
('carlos.alves@gmail.com', '1234'),
('tiago.santos@gmail.com', '1234'),
('rafael.gomes@gmail.com', '1234'),
('joao.andrade@gmail.com', '1234'),
('igor.neves@gmail.com', '1234');
