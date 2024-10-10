
INSERT IGNORE INTO types VALUES (1, 'Argument');
INSERT IGNORE INTO types VALUES (2, 'Protiargument');


INSERT IGNORE INTO owners VALUES (1, 'Libor', 'Pavlicek', '123 Hollywood', 'Los Angeles', '6085551023');
INSERT IGNORE INTO owners VALUES (2, 'Adam', 'Adam', '123 Hollywood', 'Los Angeles', '6085551023');

INSERT IGNORE INTO pets VALUES (1, 'Chodec věnoval pozornost','Díval se na cestu před sebou', '2010-09-07', 1, 1);
INSERT IGNORE INTO pets VALUES (2, 'Obec porušila standard řádné péče','Neměla zpracovaný plán údržby', '2010-09-07', 1, 1);
INSERT IGNORE INTO pets VALUES (3, 'Porušil povinnost prevence','Neměl vhodnou obuv.', '2010-09-07', 1, 1);
INSERT IGNORE INTO pets VALUES (4, 'Byla výluka z plnění plánu údržby','čas pádu nastal v době, kdy údržba nemohla zajistit odpovídající stav dle plánu údržby v přiměřené lhůtě.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (5, 'Byla výstraha,','ČHMÚ varoval v den pádu o riziku námrazy.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (6, 'Chodec byl fyzicky i psychicky způsobilý,','se jedná o běžného čtyřicátníka, který šel ráno do práce.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (7, 'Chodec věnoval pozornost','se díval před sebe pod nohy.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (8, 'Obec měla zpracovaný plán údržby','protože byl schválen veřejnou vyhláškou.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (9, 'Obuv byla vhodná','podrážka měla hrubý vzorek', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (10, 'Stav chodníku byl vyhovující','v zimě bývá obvykle sníh a led všude.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (11, 'Stav chodníku nebyl vyhovující','bylo náledí.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (12, 'Zvolená trasa byla nebezpečná','obsahovala změnu terénu.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (13, 'Zvolená trasa byla riziková','trasa obsahovala místo vedoucí kolem kontejnerů bez pokrytí úklidem a s viditelnými zmrazky.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (14, 'Zvolená trasa byla známá','tudy chodec chodil do práce.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (15, 'Zvolená trasa nebyla bezpečná','vedla šikmo z vozovky.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (16, 'Závada byla překvapivá','protože neošetřená část byla zjistitelná jen se zvýšeným vizuálním úsilím.', '2010-09-07', 1, 2);
INSERT IGNORE INTO pets VALUES (17, 'Závada nebyla ojedinělá','v danou chvíli byla kluzká celá ulice', '2010-09-07', 1, 2);


INSERT IGNORE INTO visits VALUES (1, 1, '2013-01-01', 'Nevěnoval pozornost protože se díval do mobilu');
INSERT IGNORE INTO visits VALUES (2, 2, '2013-01-02', 'Měla plán údržby, který byl schválený zastupitelstvem pod 23/2012.');
INSERT IGNORE INTO visits VALUES (3, 3, '2013-01-03', 'Neporusil povinnost presence');
INSERT IGNORE INTO visits VALUES (4, 3, '2013-01-04', 'Mit vhodnou obuv neni obsahem povinnosti prevence.');
INSERT IGNORE INTO visits VALUES (5, 3, '2013-01-04', 'Obuv měla hrubou podrážku.');
INSERT IGNORE INTO visits VALUES (6, 4, '2013-01-04', 'Nebyla výluka, protože v době pádu již měl být dle plánu údržby stav odpovídající schůdnosti a lhůta k zajištění stavu byla přiměřená.');
INSERT IGNORE INTO visits VALUES (7, 5, '2013-01-04', 'Nebyla výstraha, protože varování ČHMÚ nebylo zveřejněno ve zpravodajství.');
INSERT IGNORE INTO visits VALUES (8, 6, '2013-01-04', 'Chodec nebyl způsobilý, protože to ráno zaspal, byl rozespalý, byl unavený a chvatně pospíchal do práce.');
INSERT IGNORE INTO visits VALUES (9, 7, '2013-01-04', 'Chodec nevěnoval pozornost, protože zrovna používal mobil.');
INSERT IGNORE INTO visits VALUES (10, 8, '2013-01-04', 'Obec neměla plán údržby, protože ve skutečnosti podle něj nepostupovala a plán údržby byl pouze formální (tzv. na papíře).');
INSERT IGNORE INTO visits VALUES (11, 8, '2013-01-04', 'Obec měla plán údržby, protože pluhovala a solila chodníky.');
INSERT IGNORE INTO visits VALUES (12, 9, '2013-01-04', 'Obuv nebyla vhodná, protože boty byly staré a podrážka byla značně opotřebovaná.');
INSERT IGNORE INTO visits VALUES (13, 17, '2013-01-04', 'Závada byla ojedinělá, protože část chodníků v danou chvíli již byla ošetřena a pouze část v místě nehody nebyla.');
