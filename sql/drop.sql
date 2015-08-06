alter table Chambre drop constraint FK_ii7teigi7o2vw9x1e6ypbvgaw
alter table Enfant drop constraint FK_evct0xayck0q16x5dglh6jnar
alter table Enfant drop constraint FK_b3e453tqcl64uhpj7lw0ift2k
alter table Enfant drop constraint FK_5ho4f6qcgy7eyvib7832i1ljj
alter table Famille drop constraint FK_6x3ukx5a2whssi5k41lhek7ul
alter table FamilleAccueil drop constraint FK_qxirdjdt8v0qjodusqgfeksk1
alter table Famille_Chambre drop constraint FK_2016dhn9lku68h1d75vy1ubco
alter table Famille_Chambre drop constraint FK_3gijihmcsur0c1m7gxn5ymqk4
alter table Famille_periodesSouhaitees drop constraint FK_kr79t281hqi7gfg6jbpsw6xs5
alter table Famille_tranchesAges drop constraint FK_bs9s3m1cmy2mgkgfkcto7q6rr
alter table INFORMATIONS_HABITATION drop constraint FK_d18dtqqiao27ubldu9mlnvylv
alter table Inscripteur drop constraint FK_2tevf3tiuj72veiqtg234d7b2
alter table Inscripteur drop constraint FK_8vm6x3nj8aprsoutkvs46wpsx
alter table MembreFamille drop constraint FK_2e2pegpaypxtf7vaaw42cy2cs
alter table MembreFamille drop constraint FK_ha65o6308qujlc14q8aqdh4jc
alter table Payeur drop constraint FK_akxu2mgwi3latixlfsn89qhnq
alter table Payeur drop constraint FK_lseakpl4fvvr4ot7vsvbyhc58
alter table ResponsableLegal drop constraint FK_ho1q3r7llywciv5sdhcgavukx
alter table Sejour drop constraint FK_euccxh4qn2mauxq3t8gtil34r
alter table Sejour drop constraint FK_lqrqvqs3ifgnx4dha7xkpt579
alter table Sejour drop constraint FK_9goskutbmwqss0mle4mp5ps96
alter table Sejour drop constraint FK_jh4lpkravwbrqp9xoh1mdap08
alter table Utilisateur_Groupe drop constraint FK_t67b6nnekv5d7wym3u1j16poa
alter table Utilisateur_Groupe drop constraint FK_8kb3wn07t5qwb9tgtft01bl59
alter table VOYAGE_ACCOMPAGNATEUR drop constraint FK_d65wa5e58aucod8qaxcu1vqqj
alter table VOYAGE_ACCOMPAGNATEUR drop constraint FK_1xbim917thrw81xt8a664xf1x
drop table if exists ACCOMPAGNATEUR cascade
drop table if exists Chambre cascade
drop table if exists Commune cascade
drop table if exists Enfant cascade
drop table if exists Famille cascade
drop table if exists FamilleAccueil cascade
drop table if exists Famille_Chambre cascade
drop table if exists Famille_periodesSouhaitees cascade
drop table if exists Famille_tranchesAges cascade
drop table if exists Groupe cascade
drop table if exists INFORMATIONS_HABITATION cascade
drop table if exists Inscripteur cascade
drop table if exists MembreFamille cascade
drop table if exists Payeur cascade
drop table if exists ResponsableInscripteur cascade
drop table if exists ResponsableLegal cascade
drop table if exists Sejour cascade
drop table if exists Utilisateur cascade
drop table if exists Utilisateur_Groupe cascade
drop table if exists VOYAGE_ACCOMPAGNATEUR cascade
drop table if exists Voyage cascade
drop sequence hibernate_sequence
