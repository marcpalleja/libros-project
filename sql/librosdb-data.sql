source librosdb-schema.sql;

insert into users values('admin', MD5('admin'), 'Admin');
insert into user_roles values ('admin', 'admin');
insert into users values('test', MD5('test'), 'Test');
insert into user_roles values ('test', 'registered');


insert into autores (nombre)  values ('Carlos Ruiz Zafón');
insert into autores (nombre)  values ('JK Rowling');
insert into autores (nombre)  values ('Antonio Machado');
insert into autores (nombre)  values ('George R.R. Martin');
insert into autores (nombre)  values ('Quim Monzó');


INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Circo Maximo', 'Castellano', 'Primera', DATE '2013-03-11', DATE '2013-02-09', 'Planeta');
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('El Primer Heroi', 'Catalan', 'Primera', DATE '2014-02-12', DATE '2013-12-06', 'Ediciones B, S.A.');
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Los Asesinos Del Emperador', 'Castellano', 'Primera', DATE '2013-01-15', DATE '2013-01-02', 'Planeta' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('El Hobbit: La Desolacion De Smaug', 'Castellano', 'Primera', DATE '2013-12-09', DATE '2013-10-19', 'Minotauro' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Juego De Tronos: Cancion De Hielo y Fuego Vol.1', 'Castellano', 'Primera', DATE '2012-10-05', DATE '2012-07-16', 'GIGAMESH OMNIUM' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Juego De Tronos: Cancion De Hielo y Fuego Vol.1', 'Castellano', 'Segunda', DATE '2013-01-14', DATE '2013-01-03', 'GIGAMESH OMNIUM' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Juego De Tronos: Cancion De Hielo y Fuego Vol.4', 'Castellano', 'Primera', DATE '2013-04-12', DATE '2013-03-28', 'GIGAMESH OMNIUM' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Juego De Tronos: Cancion De Hielo y Fuego Vol.5', 'Castellano', 'Primera', DATE '2014-01-09', DATE '2013-12-05', 'GIGAMESH OMNIUM' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('Juego De Tronos: Cancion De Hielo y Fuego Vol.5', 'Ingles', 'Primera', DATE '2014-01-09', DATE '2013-12-05', 'GIGAMESH OMNIUM' );
INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES('El Origen De Los Dioses', 'Castellano', 'Primera', DATE '1999-02-11', DATE '1999-02-02', 'MR' );

INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 1, 'Circo Máximo es la historia de Trajano y su gobierno, guerras y traiciones, lealtades insobornables e historias de amor imposibles. Hay una vestal, un juicio, inocentes acusados, un abogado brillante, mensajes cifrados, fortalezas inexpugnables, dos aurigas rivales, gladiadores y tres carreras de cuadrigas. Hay un caballo especial, diferente a todos, leyes antiguas olvidadas, sacrificios humanos, amargura y terror, pero también destellos de nobleza y esperanza.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('test', 'Test', 2, 'El llibre més esperat de Martí Gironell. Una aventura fundacional plena de reptes i amenaces, en la qual el lector descobrirà un món nou i fascinant.  Fa més de cinc mil anys, un home va ser capaç d’anar més enllà de la seva pròpia terra. Ynatsé és escollit pels déus per protegir el seu poblat i trobar el remei a un mal que assola la seva comunitat, el Clan dels Cavalls.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 3, '18 de septiembre del año 96 d. C. Un plan perfecto. Un día diseñado para escribir la Historia, pero cuando todo sale mal la Historia ya no se escribe? se improvisa: una guerra civil, las fieras del Coliseo, la guardia pretoriana, traiciones, envenenamientos, delatores y poetas, combates en la arena, ejecuciones sumarísimas, el último discípulo de Cristo, el ascenso y caída de una dinastía imperial, locura y esperanza, la erupción de Vesubio, un puñado de gladiadores.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 4, 'El tercero de los volúmenes escritos, ilustrados y diseñados por los equipos de Weta y el departamento artístico de El Hobbit incluye más de mil imágenes del rodaje, diseños digitales y fotogramas de la película además de comentarios personales de los artistas, los directores de la película y el reparto que nos revelarán las historias que se esconden detrás de las cámaras.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 5, 'En un mundo cuyas estaciones pueden durar decenios y en el que retazos de una magia inmemorial y olvidada surgen en los rincones más sombríos y maravillosos, la traición y la lealtad, la compasión y la sed de venganza, el amor y el poder hacen del juego de tronos una poderosa trampa que atrapará en sus fauces a los personajes... y al lector.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 6, 'En un mundo cuyas estaciones pueden durar decenios y en el que retazos de una magia inmemorial y olvidada surgen en los rincones más sombríos y maravillosos, la traición y la lealtad, la compasión y la sed de venganza, el amor y el poder hacen del juego de tronos una poderosa trampa que atrapará en sus fauces a los personajes... y al lector.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 7, 'Canción de hielo y fuego: Libro cuarto. La novela río más espectacular jamás escrita. Mientras los vientos del otoño desnudan los árboles, las últimas cosechas se pudren en los pocos campos que no han sido devastados por la guerra, y por los ríos teñidos de rojo bajan cadáveres de todos los blasones y estirpes. Y aunque casi todo Poniente yace extenuado, en diversos rincones florecen nuevas e inquietantes intrigas que ansían nutrirse de los despojos de un reino moribundo.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 8, 'Canción de hielo y fuego: Libro quinto La novela río más espectacular jamás escrita Daenerys Targaryen intenta mitigar el rastro de sangre y fuego que dejó en las Ciudades Libres al erradicar la esclavitud en Meereen. Mientras, un enano parricida, un príncipe de incógnito, un capitán implacable y un enigmático caballero acuden a la llamada de los dragones desde el otro lado del mar Angosto, ajenos al peligro que se cierne sobre el Norte, y que solo las menguadas huestes.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 9, 'Canción de hielo y fuego: Libro quinto La novela río más espectacular jamás escrita Daenerys Targaryen intenta mitigar el rastro de sangre y fuego que dejó en las Ciudades Libres al erradicar la esclavitud en Meereen. Mientras, un enano parricida, un príncipe de incógnito, un capitán implacable y un enigmático caballero acuden a la llamada de los dragones desde el otro lado del mar Angosto, ajenos al peligro que se cierne sobre el Norte, y que solo las menguadas huestes.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('test', 'Test', 8, 'Rebasa las barreras de los géneros como si nunca hubieran existido: Danza de dragones marca su consagración definitiva entre los más grandes creadores de la historia de la literatura, más allá de cualquier distinciónde etiquetas.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('test', 'Test', 9, 'Rebasa las barreras de los géneros como si nunca hubieran existido: Danza de dragones marca su consagración definitiva entre los más grandes creadores de la historia de la literatura, más allá de cualquier distinciónde etiquetas.');
INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES('admin', 'Admin', 10, 'Oviedo, Anno Domini 842. La reconquista pende de un hilo. Negras amenazas se ciernen sobre el Reino de Asturias. El rey Alfonso II, en su lecho de muerte, ha designado heredero al noble Ramiro. Pero una importante facción de la aristocracia conspira contra el elegido: el magnate Nepociano, viejo disidente, vuelve de su destierro y encabeza un golpe de estado. ');

INSERT INTO relacionlibrosautores VALUES (1,1);
INSERT INTO relacionlibrosautores VALUES (1,3);
INSERT INTO relacionlibrosautores VALUES (2,2);
INSERT INTO relacionlibrosautores VALUES (3,4);
INSERT INTO relacionlibrosautores VALUES (4,5);
INSERT INTO relacionlibrosautores VALUES (4,6);
INSERT INTO relacionlibrosautores VALUES (4,7);
INSERT INTO relacionlibrosautores VALUES (4,8);
INSERT INTO relacionlibrosautores VALUES (4,9);
INSERT INTO relacionlibrosautores VALUES (5,10);