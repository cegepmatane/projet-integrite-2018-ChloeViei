--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.10
-- Dumped by pg_dump version 9.6.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: lieuDecouvrir; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "lieuDecouvrir" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Canada.1252' LC_CTYPE = 'French_Canada.1252';


ALTER DATABASE "lieuDecouvrir" OWNER TO postgres;

\connect "lieuDecouvrir"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- Name: journaliser(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.journaliser() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

DECLARE 
	description text;
	objetAvant text;
	objetApres text;
	operation text;
BEGIN
	objetAvant := '';
	objetApres := '';
	
	IF TG_OP = 'UPDATE' THEN
		IF TG_TABLE_NAME = 'pays' THEN
			objetAvant := '{'||OLD.nom||','||OLD.continent||','||OLD.population||','||OLD.langue||','||OLD.capital||'}';
			objetApres := '{'||NEW.nom||','||NEW.continent||','||NEW.population||','||NEW.langue||','||NEW.capital||'}';
		END IF;
		IF TG_TABLE_NAME = 'lieu' THEN
			objetAvant := '{'||OLD.nom||','||OLD.type||','||OLD.detail||','||OLD.pays||','||OLD.superficie||'}';
			objetApres := '{'||NEW.nom||','||NEW.type||','||NEW.detail||','||NEW.pays||','||NEW.superficie||'}';
		END IF;
		operation := 'MODIFIER';
	END IF;
	
	IF TG_OP = 'INSERT' THEN
	IF TG_TABLE_NAME = 'lieu' THEN
			objetAvant := '{}';
			objetApres := '{'||NEW.nom||','||NEW.type||','||NEW.detail||','||NEW.pays||','||NEW.superficie||'}';
		END IF;
		IF TG_TABLE_NAME = 'pays' THEN
			objetAvant := '{}';
			objetApres := '{'||NEW.nom||','||NEW.continent||','||NEW.population||','||NEW.langue||','||NEW.capital||'}';
		END IF;
		operation := 'AJOUTER';
	END IF;
	
	IF TG_OP = 'DELETE' THEN
		IF TG_TABLE_NAME = 'pays' THEN
			objetAvant := '{'||OLD.nom||','||OLD.continent||','||OLD.population||','||OLD.langue||','||OLD.capital||'}';
			objetApres := '{}';
		END IF;
		IF TG_TABLE_NAME = 'lieu' THEN
			objetAvant := '{'||OLD.nom||','||OLD.type||','||OLD.detail||','||OLD.pays||','||OLD.superficie||'}';
			objetApres := '{}';
		END IF;
		operation := 'SUPPRIMER';
	END IF;
	
	description := objetAvant || ' -> ' || objetApres;
	INSERT into journal(moment, operation, objet, description) VALUES(NOW(), operation, TG_TABLE_NAME, description);
	
	IF TG_OP = 'DELETE' THEN
		return OLD;
	END IF;
	
	return NEW;
END

$$;


ALTER FUNCTION public.journaliser() OWNER TO postgres;

--
-- Name: surveillance(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.surveillance() RETURNS trigger
    LANGUAGE plpgsql
    AS $_$

DECLARE 
BEGIN
	-- Réalise les insert a minuit tous les jours
	SELECT cron.schedule('0 0 * * *', $$ INSERT INTO surveillancelieu(moment, nombrelieu, moyennesuperficie, checksumnom) 
		VALUES(NOW(), (SELECT COUNT(id) FROM lieu),(SELECT AVG(superficie) FROM lieu), (SELECT MD5(string_agg(nom,'-')) FROM lieu))$$);
	
	SELECT cron.schedule('0 0 * * *', $$ INSERT INTO surveillancepays(moment, nombrepays, checksumnom) 
		VALUES(NOW(), (SELECT COUNT(id) FROM lieu),(SELECT MD5(string_agg(nom,'-')) FROM lieu))$$);
	
	RETURN NEW;
END

$_$;


ALTER FUNCTION public.surveillance() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal (
    id integer NOT NULL,
    moment timestamp with time zone NOT NULL,
    operation text NOT NULL,
    objet text NOT NULL,
    description text
);


ALTER TABLE public.journal OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_id_seq OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_id_seq OWNED BY public.journal.id;


--
-- Name: lieu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lieu (
    id integer NOT NULL,
    nom text,
    type text,
    detail text,
    pays integer,
    superficie integer
);


ALTER TABLE public.lieu OWNER TO postgres;

--
-- Name: lieu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lieu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lieu_id_seq OWNER TO postgres;

--
-- Name: lieu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lieu_id_seq OWNED BY public.lieu.id;


--
-- Name: pays; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pays (
    nom text,
    continent text,
    population text,
    langue text,
    capital text,
    id integer NOT NULL
);


ALTER TABLE public.pays OWNER TO postgres;

--
-- Name: pays_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pays_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pays_id_seq OWNER TO postgres;

--
-- Name: pays_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pays_id_seq OWNED BY public.pays.id;


--
-- Name: surveillancelieu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.surveillancelieu (
    id integer NOT NULL,
    moment date,
    nombrelieu integer,
    moyennesuperficie integer,
    checksumnom text
);


ALTER TABLE public.surveillancelieu OWNER TO postgres;

--
-- Name: surveillancelieu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.surveillancelieu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.surveillancelieu_id_seq OWNER TO postgres;

--
-- Name: surveillancelieu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.surveillancelieu_id_seq OWNED BY public.surveillancelieu.id;


--
-- Name: surveillancelieuparpays; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.surveillancelieuparpays (
    id integer NOT NULL,
    moment date,
    nombrelieu integer,
    moyennesuperficie integer,
    checksumnom text
);


ALTER TABLE public.surveillancelieuparpays OWNER TO postgres;

--
-- Name: surveillancelieuparpays_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.surveillancelieuparpays_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.surveillancelieuparpays_id_seq OWNER TO postgres;

--
-- Name: surveillancelieuparpays_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.surveillancelieuparpays_id_seq OWNED BY public.surveillancelieuparpays.id;


--
-- Name: surveillancepays; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.surveillancepays (
    id integer NOT NULL,
    moment date,
    nombrepays integer,
    checksumnom text
);


ALTER TABLE public.surveillancepays OWNER TO postgres;

--
-- Name: surveillancepays_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.surveillancepays_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.surveillancepays_id_seq OWNER TO postgres;

--
-- Name: surveillancepays_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.surveillancepays_id_seq OWNED BY public.surveillancepays.id;


--
-- Name: journal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal ALTER COLUMN id SET DEFAULT nextval('public.journal_id_seq'::regclass);


--
-- Name: lieu id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lieu ALTER COLUMN id SET DEFAULT nextval('public.lieu_id_seq'::regclass);


--
-- Name: pays id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pays ALTER COLUMN id SET DEFAULT nextval('public.pays_id_seq'::regclass);


--
-- Name: surveillancelieu id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surveillancelieu ALTER COLUMN id SET DEFAULT nextval('public.surveillancelieu_id_seq'::regclass);


--
-- Name: surveillancelieuparpays id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surveillancelieuparpays ALTER COLUMN id SET DEFAULT nextval('public.surveillancelieuparpays_id_seq'::regclass);


--
-- Name: surveillancepays id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surveillancepays ALTER COLUMN id SET DEFAULT nextval('public.surveillancepays_id_seq'::regclass);


--
-- Data for Name: journal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.journal VALUES (45, '2018-09-28 16:51:56.936458-04', 'AJOUTER', 'pays', '{} -> {Islande,Europe,,,Reykjavik}');
INSERT INTO public.journal VALUES (46, '2018-09-28 16:52:23.957113-04', 'AJOUTER', 'lieu', NULL);
INSERT INTO public.journal VALUES (47, '2018-09-28 16:54:18.035851-04', 'MODIFIER', 'pays', '{Islande,Europe,,,Reykjavik} -> {Islande,Europe,,Islandais,Reykjavik}');
INSERT INTO public.journal VALUES (48, '2018-09-28 16:54:25.708085-04', 'AJOUTER', 'lieu', NULL);
INSERT INTO public.journal VALUES (49, '2018-09-28 16:55:22.452206-04', 'MODIFIER', 'lieu', NULL);
INSERT INTO public.journal VALUES (50, '2018-09-28 16:57:55.180521-04', 'MODIFIER', 'pays', '{Islande,Europe,,Islandais,Reykjavik} -> {Islande,Europe,300 000,Islandais,Reykjavik}');


--
-- Name: journal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.journal_id_seq', 50, true);


--
-- Data for Name: lieu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.lieu VALUES (1, 'Plitvie', 'Lacs dans le parc national', '', 20, NULL);
INSERT INTO public.lieu VALUES (2, 'Nishinomaru', 'Jardin de style japonais', 'Se situe autour du chateau d''Osaka', 30, NULL);
INSERT INTO public.lieu VALUES (3, 'Machu Picchu', 'Citadelle Inca', '', 29, 325);
INSERT INTO public.lieu VALUES (6, 'Ushuaia', 'Ville astrale', '', 3, NULL);
INSERT INTO public.lieu VALUES (16, 'Le Lac Myvatn', '', '', 35, NULL);
INSERT INTO public.lieu VALUES (17, 'Blue Lagoon', 'Sources chandes', '', 35, NULL);


--
-- Name: lieu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lieu_id_seq', 17, true);


--
-- Data for Name: pays; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pays VALUES ('Allemagne', 'Europe', '90 millions', 'Allemand', 'Berlin', 8);
INSERT INTO public.pays VALUES ('Lituanie', 'Europe', '3 millions', 'Lituanien', 'Vilnius', 24);
INSERT INTO public.pays VALUES ('Perou', 'Amerique', '32 millions', 'Espagnol/Quechua/Aymara', 'Lima', 29);
INSERT INTO public.pays VALUES ('Croatie', 'Europe', '4 millions', 'Croate', 'Zagreb', 20);
INSERT INTO public.pays VALUES ('Japon', 'Asie', '127 millions', 'Japonais', 'Tokyo', 30);
INSERT INTO public.pays VALUES ('France', 'Europe', '67 millions', 'Francais', 'Paris', 4);
INSERT INTO public.pays VALUES ('Mexique', 'Amerique', '124 millions', 'Espagnol', 'Mexico', 32);
INSERT INTO public.pays VALUES ('Argentine', 'Amerique', '44 millions', 'Espagnol', 'Buenos Aires', 17);
INSERT INTO public.pays VALUES ('Argentine', 'Amerique', '43 millions', 'Espagnol', 'Buenos Aires', 3);
INSERT INTO public.pays VALUES ('Taiwan', 'Asie', '23 millions', 'Chinois', 'Taipei', 26);
INSERT INTO public.pays VALUES ('Vietnam', 'Asie', 'rfer', 'Vietnamien', 'Hanoi', 11);
INSERT INTO public.pays VALUES ('Venzuela', 'Amerique', '31 millions', 'Espagnol', 'Caracas', 28);
INSERT INTO public.pays VALUES ('Egypte', 'Afrique', '94 millions', 'Arabe', 'Le Caire', 25);
INSERT INTO public.pays VALUES ('Islande', 'Europe', '300 000', 'Islandais', 'Reykjavik', 35);


--
-- Name: pays_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pays_id_seq', 35, true);


--
-- Data for Name: surveillancelieu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.surveillancelieu VALUES (1, '2018-09-27', 4, 167, '91d6e5a196bf284b749e4e64ecb44911');


--
-- Name: surveillancelieu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.surveillancelieu_id_seq', 1, true);


--
-- Data for Name: surveillancelieuparpays; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.surveillancelieuparpays VALUES (12, '2018-09-27', 4, 167, '91d6e5a196bf284b749e4e64ecb44911');


--
-- Name: surveillancelieuparpays_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.surveillancelieuparpays_id_seq', 12, true);


--
-- Data for Name: surveillancepays; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.surveillancepays VALUES (1, '2018-09-27', 13, 'aa3ae14dd6548b5c8c29fa06b60b463e');


--
-- Name: surveillancepays_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.surveillancepays_id_seq', 1, true);


--
-- Name: journal journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);


--
-- Name: lieu lieu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lieu
    ADD CONSTRAINT lieu_pkey PRIMARY KEY (id);


--
-- Name: pays pays_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pays
    ADD CONSTRAINT pays_pkey PRIMARY KEY (id);


--
-- Name: surveillancelieu surveillancelieu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surveillancelieu
    ADD CONSTRAINT surveillancelieu_pkey PRIMARY KEY (id);


--
-- Name: surveillancelieuparpays surveillancelieuparpays_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surveillancelieuparpays
    ADD CONSTRAINT surveillancelieuparpays_pkey PRIMARY KEY (id);


--
-- Name: surveillancepays surveillancepays_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surveillancepays
    ADD CONSTRAINT surveillancepays_pkey PRIMARY KEY (id);


--
-- Name: fki_one_pays_to_many_lieu; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_one_pays_to_many_lieu ON public.lieu USING btree (pays);


--
-- Name: lieu evenementajouterlieu; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementajouterlieu BEFORE INSERT ON public.lieu FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: pays evenementajouterpays; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementajouterpays BEFORE INSERT ON public.pays FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: lieu evenementeffacerlieu; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementeffacerlieu BEFORE DELETE ON public.lieu FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: pays evenementeffacerpays; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementeffacerpays BEFORE DELETE ON public.pays FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: lieu evenementmodifierlieu; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementmodifierlieu BEFORE UPDATE ON public.lieu FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: pays evenementmodifierpays; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementmodifierpays BEFORE UPDATE ON public.pays FOR EACH ROW EXECUTE PROCEDURE public.journaliser();


--
-- Name: lieu one_pays_to_many_lieu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lieu
    ADD CONSTRAINT one_pays_to_many_lieu FOREIGN KEY (pays) REFERENCES public.pays(id);


--
-- PostgreSQL database dump complete
--

