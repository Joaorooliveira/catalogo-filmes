
INSERT INTO diretor (nome, data_nascimento, criado_em, atualizado_em) VALUES
('Christopher Nolan', '1970-07-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Steven Spielberg', '1946-12-18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Quentin Tarantino', '1963-03-27', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Martin Scorsese', '1942-11-17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Greta Gerwig', '1983-08-04', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Hayao Miyazaki', '1941-01-05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Stanley Kubrick', '1928-07-26', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Denis Villeneuve', '1967-10-03', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jordan Peele', '1979-02-21', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bong Joon-ho', '1969-09-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO filme (nome, descricao, genero, ano_lancamento, avaliacao, assistido, favorito, diretor_id, criado_em, atualizado_em) VALUES
('Interestelar', 'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço.', 'FICCAO', '2014-11-06', 5, true, true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jurassic Park', 'Um parque temático com dinossauros clonados sofre uma grande falha de energia.', 'FICCAO', '1993-06-11', 5, true, true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pulp Fiction', 'A vida de dois assassinos da máfia, um boxeador e um casal se entrelaçam.', 'DRAMA', '1994-10-14', 5, true, false, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('O Lobo de Wall Street', 'A verdadeira história de Jordan Belfort e sua ascensão e queda.', 'COMEDIA', '2013-12-25', 4, true, false, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Lady Bird', 'As aventuras de uma jovem vivendo no norte da Califórnia por um ano.', 'DRAMA', '2017-11-03', 4, true, false, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('A Viagem de Chihiro', 'Uma garota de 10 anos entra no mundo dos espíritos.', 'ANIMACAO', '2001-07-20', 5, true, true, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('O Iluminado', 'Uma família vai para um hotel isolado para o inverno.', 'TERROR', '1980-05-23', 5, true, true, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Duna', 'Adaptação do romance de ficção científica do autor Frank Herbert.', 'FICCAO', '2021-10-22', 4, true, false, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Corra!', 'Um jovem afro-americano visita os pais brancos de sua namorada no fim de semana.', 'TERROR', '2017-02-24', 5, true, true, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Parasita', 'Ganância e discriminação de classe ameaçam o relacionamento de duas famílias.', 'DRAMA', '2019-05-30', 5, true, true, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO lista (titulo, criado_em, atualizado_em) VALUES
('Maratona Fim de Semana: Aclamados pela Crítica', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO lista_filme (lista_id, filme_id) VALUES
(1, 1),  -- Interestelar
(1, 3),  -- Pulp Fiction
(1, 6),  -- A Viagem de Chihiro
(1, 9),  -- Corra!
(1, 10); -- Parasita