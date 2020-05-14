SELECT * from AGENT;
SELECT * from ALIEN;
SELECT * from BOGLODITE;
SELECT * from FALTAGENT;
SELECT * from FORDON;
SELECT * from INNEHAR_FORDON;
SELECT * from INNEHAR_UTRUSTNING;
SELECT * from KOMMUNIKATION;
SELECT * from OMRADE;
SELECT * from OMRADESCHEF;
SELECT * from PLATS;
SELECT * from SQUID;
SELECT * from TEKNIK;
SELECT * from UTRUSTNING;
SELECT * from VAPEN;
SELECT * from WORM;
SELECT LOSENORD FROM ALIEN;

select * from alien;
select namn from alien where losenord = 'blomma';

select namn from alien where alien_id = '1';

select omrade.benamning from alien, plats, omrade where
plats = plats_id and finns_i = omrades_id and
alien_id = 1;

select agent.namn from 

use mib;

select omrades_id from alien, plats, omrade where " +
                                "plats = plats_id and finns_i = omrades_id and " +
                                "alien_id = 1;