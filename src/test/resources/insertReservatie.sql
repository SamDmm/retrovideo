

insert into reservaties (klantid, filmid, reservatie) value ((select id from klanten where familienaam = 'test'), (select id from films where titel = 'test'), '17/12/18');
