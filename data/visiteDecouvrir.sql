-- SEQUENCE: public."idPays"

-- DROP SEQUENCE public."idPays";

CREATE SEQUENCE pays_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER TABLE pays_id_seq OWNER TO postgres;

ALTER SEQUENCE pays_id_seq OWNED BY pays.id;

-- Table: public.pays

-- DROP TABLE public.pays;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

SET search_path = public, pg_catalog;
 
SET default_tablespace = '';
 
SET default_with_oids = false;



CREATE TABLE public.pays
(
    nom text,
    continent text,
    population text,
    langue text,
    capital text,
	id integer NOT NULL
);


ALTER TABLE public.pays OWNER to postgres;


ALTER TABLE ONLY pays ALTER COLUMN id SET DEFAULT nextval('pays_id_seq'::regclass);



INSERT INTO pays VALUES('France','Europe','67 millions','Francais','Paris');
INSERT INTO pays VALUES('Japon','Asie','127 millions','Japonnais','Tokyo');



ALTER TABLE ONLY pays
    ADD CONSTRAINT pays_pkey PRIMARY KEY (id);


	
	












