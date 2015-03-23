drop database if exists librosdb;
create database librosdb;
use librosdb;

create table users (
	username	varchar(20) not null primary key,
	userpass	char(32) not null,
	name		varchar(70) not null
);
 
create table user_roles (
	username			varchar(20) not null,
	rolename 			varchar(20) not null,
	foreign key(username) references users(username) on delete cascade,
	primary key (username, rolename)
);
 
 CREATE TABLE autores (
  idautor int not null auto_increment primary key,
  nombre varchar (60) not null
 );

CREATE TABLE Libros (
  LibroID int not null auto_increment primary key,
  Titulo varchar (100) not null,
  Lengua varchar (100) not null,
  Edicion varchar (100) not null,
  FechaEdicion varchar (100) not null,
  FechaImpresion varchar (100) not null,
  Editorial varchar (100) not null
 
);

CREATE Table relacionlibrosautores (
idautor integer not null,
idlibro integer not null,
foreign key (idautor) references autores(idautor) on delete cascade,
foreign key (idlibro) references Libros(LibroID) on delete cascade
);

CREATE TABLE Reviews (
  ReviewID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR (20) NOT NULL,
  Nombre VARCHAR (70) NOT NULL,
  IDLibro INTEGER NOT NULL,
  Contenido VARCHAR (500) NOT NULL,
  last_modified			timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  creation_timestamp		datetime not null default current_timestamp,
  FOREIGN KEY (Username) REFERENCES users(username) ON DELETE CASCADE,
  FOREIGN KEY (IDLibro) REFERENCES Libros(LibroID) ON DELETE CASCADE
);