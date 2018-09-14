-- SEQUENCE: public."idPays"

CREATE TABLE distinction (
    id integer NOT NULL,
    nom integer,
    adresse text,
    detail text,
    pays integer
);

ALTER TABLE distinction OWNER TO postgres;


CREATE SEQUENCE lieu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
 ALTER TABLE lieu_id_seq OWNER TO postgres;
 
 ALTER SEQUENCE distinction_id_seq OWNED BY distinction.id;
 

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

ALTER TABLE ONLY lieu ALTER COLUMN id SET DEFAULT nextval('lieu_id_seq'::regclass);



INSERT INTO pays VALUES('France','Europe','67 millions','Francais','Paris');
INSERT INTO pays VALUES('Japon','Asie','127 millions','Japonnais','Tokyo');



ALTER TABLE ONLY pays
    ADD CONSTRAINT pays_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY lieu
    ADD CONSTRAINT lieu_pkey PRIMARY KEY (id);
    
    
CREATE INDEX fki_one_pays_to_many_lieu ON lieu USING btree (pays);

ALTER TABLE ONLY distinction
    ADD CONSTRAINT one_pays_to_many_lieu FOREIGN KEY (pays) REFERENCES pays(id);


	
	












