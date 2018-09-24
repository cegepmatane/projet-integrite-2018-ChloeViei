 --
 -- PostgreSQL database dump
 --
 
 -- Dumped from database version 9.6.4
 -- Dumped by pg_dump version 9.6.4 


 SET statement_timeout = 0;
 SET lock_timeout = 0;
 SET idle_in_transaction_session_timeout = 0;
 SET client_encoding = 'UTF8';
 SET standard_conforming_strings = on;
 SET check_function_bodies = false;
 SET client_min_messages = warning;
 SET row_security = off;

 
 --
 -- Name: lieuDecouvrir; Type: DATABASE; Schema: -; Owner: postgres
 --

 ALTER DATABASE lieuDecouvrir OWNER TO postgres;

 
--
-- Name: journaliser(); Type: FUNCTION; Schema: public; Owner: postgres
--

 CREATE FUNCTION journaliser() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE 
	description text;
	objetAvant text;
	objetAprès text;
	operation text;
BEGIN
	
	objetAvant := '';
	objetApres := '';
	
	IF TG_OP = 'UPDATE' THEN
		objetAvant := '{'||OLD.nom||','||OLD.continent||','||OLD.population||','||OLD.langue||','||OLD.capital||'}';
		objetApres := '{'||NEW.nom||','||NEW.continent||','||NEW.population||','||NEW.langue||','||NEW.capital||'}';
		operation := 'MODIFIER';
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		objetAvant := '{}';
		objetApres := '{'||NEW.nom||','||NEW.continent||','||NEW.population||','||NEW.langue||','||NEW.capital||'}';
		operation := 'AJOUTER';
	END IF;
	
	IF TG_OP = 'DELETE' THEN
		objetAvant := '{'||OLD.nom||','||OLD.continent||','||OLD.population||','||OLD.langue||','||OLD.capital||'}';
		objetApres := '{}';
		operation := 'SUPPRIMER';
	END IF;
	
	desciption := objetAvant || ' -> ' || objetApres;
	INSERT into journal(moment, operation, objet, description) VALUES(NOW(), operation, 'pays', description);
	
	IF TG_OP = 'DELETE' THEN
		return OLD;
	END IF;
	
	return NEW;
END
$$;
 ALTER FUNCTION public.journaliser() OWNER TO postgres;

 
 
 --
 -- Name: lieu; Type: TABLE; Schema: public; Owner: postgres
 --

CREATE TABLE lieu (
    id integer NOT NULL,
    nom text,
    type text,
    detail text,
    pays integer
);

ALTER TABLE lieu OWNER TO postgres;


CREATE SEQUENCE lieu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
 ALTER TABLE lieu_id_seq OWNER TO postgres;
 
 ALTER SEQUENCE lieu_id_seq OWNED BY lieu.id;
 

-- DROP SEQUENCE public."idPays";

CREATE SEQUENCE pays_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER TABLE pays_id_seq OWNER TO postgres;

ALTER SEQUENCE pays_id_seq OWNED BY pays.id;


--
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

 CREATE TABLE journal (
    id integer NOT NULL,
    moment timestamp with time zone NOT NULL,
    operation text NOT NULL,
    description text,
    objet text NOT NULL
);
 ALTER TABLE journal OWNER TO postgres;
 
 
--
-- Name: journal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

 CREATE SEQUENCE journal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ALTER TABLE journal_id_seq OWNER TO postgres;
 
--
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

 ALTER SEQUENCE journal_id_seq OWNED BY journal.id;




--
-- Name: pays; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pays
(
    nom text,
    continent text,
    population text,
    langue text,
    capital text,
	id integer NOT NULL
);


ALTER TABLE .pays OWNER to postgres;


 --
 -- Name: pays_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
 --
 
 CREATE SEQUENCE pays_id_seq
     START WITH 1
     INCREMENT BY 1
     NO MINVALUE
     NO MAXVALUE
     CACHE 1;
 
 
 ALTER TABLE pays_id_seq OWNER TO postgres;
 
 --
 -- Name: pays_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
 --
 
 ALTER SEQUENCE pays_id_seq OWNED BY pays.id;


ALTER TABLE ONLY pays ALTER COLUMN id SET DEFAULT nextval('pays_id_seq'::regclass);
ALTER TABLE ONLY lieu ALTER COLUMN id SET DEFAULT nextval('lieu_id_seq'::regclass);
ALTER TABLE ONLY journam ALTER COLUMN id SET DEFAULT nextval('journal_id_seq'::regclass);


INSERT INTO pays VALUES('France','Europe','67 millions','Francais','Paris');
INSERT INTO pays VALUES('Japon','Asie','127 millions','Japonnais','Tokyo');



ALTER TABLE ONLY pays
    ADD CONSTRAINT pays_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY lieu
    ADD CONSTRAINT lieu_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);
    
    
CREATE INDEX fki_one_pays_to_many_lieu ON lieu USING btree (pays);

CREATE TRIGGER evenementajouterpays BEFORE INSERT ON pays FOR EACH ROW EXECUTE PROCEDURE journaliser();

CREATE TRIGGER evenementmodifierpays BEFORE UPDATE ON pays FOR EACH ROW EXECUTE PROCEDURE journaliser();

CREATE TRIGGER evenementeffacerpays BEFORE DELETE ON pays FOR EACH ROW EXECUTE PROCEDURE journaliser();


ALTER TABLE ONLY lieu
    ADD CONSTRAINT one_pays_to_many_lieu FOREIGN KEY (pays) REFERENCES pays(id);
