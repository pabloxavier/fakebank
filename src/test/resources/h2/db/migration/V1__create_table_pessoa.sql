create table dbo.pessoa (
  cd_pessoa     int not null identity(1,1) primary key,
  nr_documento  char not null,
  tp_pessoa     char not null,
  nm_pessoa     varchar(80) not null,
  dt_nascimento date null,
  dt_abertura   date null
)