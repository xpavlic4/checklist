INSERT INTO vets VALUES (default, 'Ben', 'Matlock');
INSERT INTO vets VALUES (default, 'Ally', 'McBeal');

INSERT INTO types VALUES (default, 'Argument');
INSERT INTO types VALUES (default, 'Protiargument');


INSERT INTO owners VALUES (default, 'Libor', 'Pavlicek', '123 Hollywood', 'Los Angeles', '6085551023');
INSERT INTO owners VALUES (default, 'Adam', 'Adam', '123 Hollywood', 'Los Angeles', '6085551023');

INSERT INTO pets VALUES (default, 'Chodec věnoval pozornost','Díval se na cestu před sebou', '2010-09-07', 1, 1);
INSERT INTO pets VALUES (default, 'Obec porušila standard řádné péče','Neměla zpracovaný plán údržby', '2010-09-07', 1, 1);
INSERT INTO pets VALUES (default, 'Porušil povinnost prevence','Neměl vhodnou obuv.', '2010-09-07', 1, 1);
INSERT INTO pets VALUES (default, 'Byla výluka z plnění plánu údržby','čas pádu nastal v době, kdy údržba nemohla zajistit odpovídající stav dle plánu údržby v přiměřené lhůtě.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Byla výstraha,','ČHMÚ varoval v den pádu o riziku námrazy.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Chodec byl fyzicky i psychicky způsobilý,','se jedná o běžného čtyřicátníka, který šel ráno do práce.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Chodec věnoval pozornost','se díval před sebe pod nohy.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Obec měla zpracovaný plán údržby','protože byl schválen veřejnou vyhláškou.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Obuv byla vhodná','podrážka měla hrubý vzorek', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Stav chodníku byl vyhovující','v zimě bývá obvykle sníh a led všude.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Stav chodníku nebyl vyhovující','bylo náledí.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Zvolená trasa byla nebezpečná','obsahovala změnu terénu.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Zvolená trasa byla riziková','trasa obsahovala místo vedoucí kolem kontejnerů bez pokrytí úklidem a s viditelnými zmrazky.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Zvolená trasa byla známá','tudy chodec chodil do práce.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Zvolená trasa nebyla bezpečná','vedla šikmo z vozovky.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Závada byla překvapivá','protože neošetřená část byla zjistitelná jen se zvýšeným vizuálním úsilím.', '2010-09-07', 1, 2);
INSERT INTO pets VALUES (default, 'Závada nebyla ojedinělá','v danou chvíli byla kluzká celá ulice', '2010-09-07', 1, 2);


INSERT INTO visits VALUES (default, 1, '2013-01-01', 'Nevěnoval pozornost protože se díval do mobilu', 'Nevěnoval pozornost ', 'se díval do mobilu');
INSERT INTO visits VALUES (default, 2, '2013-01-02', 'Měla plán údržby, který byl schválený zastupitelstvem pod 23/2012.','Měla plán údržby, který byl schválený zastupitelstvem pod 23/2012.', '');
INSERT INTO visits VALUES (default, 3, '2013-01-03', 'Neporusil povinnost presence','Neporusil povinnost presence', '');
INSERT INTO visits VALUES (default, 3, '2013-01-04', 'Mit vhodnou obuv neni obsahem povinnosti prevence.','Mit vhodnou obuv neni obsahem povinnosti prevence.', '');
INSERT INTO visits VALUES (default, 3, '2013-01-04', 'Obuv měla hrubou podrážku.','Obuv měla hrubou podrážku.', '');
INSERT INTO visits VALUES (default, 4, '2013-01-04', 'Nebyla výluka, protože v době pádu již měl být dle plánu údržby stav odpovídající schůdnosti a lhůta k zajištění stavu byla přiměřená.','Nebyla výluka', 'v době pádu již měl být dle plánu údržby stav odpovídající schůdnosti a lhůta k zajištění stavu byla přiměřená.');
INSERT INTO visits VALUES (default, 5, '2013-01-04', 'Nebyla výstraha, protože varování ČHMÚ nebylo zveřejněno ve zpravodajství.','Nebyla výstraha', 'varování ČHMÚ nebylo zveřejněno ve zpravodajství.');
INSERT INTO visits VALUES (default, 6, '2013-01-04', 'Chodec nebyl způsobilý, protože to ráno zaspal, byl rozespalý, byl unavený a chvatně pospíchal do práce.','Chodec nebyl způsobilý', 'to ráno zaspal, byl rozespalý, byl unavený a chvatně pospíchal do práce.');
INSERT INTO visits VALUES (default, 7, '2013-01-04', 'Chodec nevěnoval pozornost, protože zrovna používal mobil.','Chodec nevěnoval pozornost', 'zrovna používal mobil.');
INSERT INTO visits VALUES (default, 8, '2013-01-04', 'Obec neměla plán údržby, protože ve skutečnosti podle něj nepostupovala a plán údržby byl pouze formální (tzv. na papíře).','Obec neměla plán údržby', 've skutečnosti podle něj nepostupovala a plán údržby byl pouze formální (tzv. na papíře).');
INSERT INTO visits VALUES (default, 8, '2013-01-04', 'Obec měla plán údržby, protože pluhovala a solila chodníky.','Obec měla plán údržby', 'pluhovala a solila chodníky.');
INSERT INTO visits VALUES (default, 9, '2013-01-04', 'Obuv nebyla vhodná, protože boty byly staré a podrážka byla značně opotřebovaná.','Obuv nebyla vhodná', 'boty byly staré a podrážka byla značně opotřebovaná.');
INSERT INTO visits VALUES (default, 17, '2013-01-04', 'Závada byla ojedinělá, protože část chodníků v danou chvíli již byla ošetřena a pouze část v místě nehody nebyla.','Závada byla ojedinělá', 'část chodníků v danou chvíli již byla ošetřena a pouze část v místě nehody nebyla.');
