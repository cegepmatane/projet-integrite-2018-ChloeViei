PGDMP             
            v           lieuDecouvrir    9.6.10    9.6.10     K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            N           1262    24585    lieuDecouvrir    DATABASE     �   CREATE DATABASE "lieuDecouvrir" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Canada.1252' LC_CTYPE = 'French_Canada.1252';
    DROP DATABASE "lieuDecouvrir";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            O           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    4                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            P           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    2                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                  false            Q           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                       false    1            �            1259    24586    pays    TABLE     �   CREATE TABLE public.pays (
    "idPays" integer NOT NULL,
    nom text,
    continent text,
    population text,
    langue text,
    capital text
);
    DROP TABLE public.pays;
       public         postgres    false    4            H          0    24586    pays 
   TABLE DATA               U   COPY public.pays ("idPays", nom, continent, population, langue, capital) FROM stdin;
    public       postgres    false    186   �
       �           2606    24593    pays Pays_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pays
    ADD CONSTRAINT "Pays_pkey" PRIMARY KEY ("idPays");
 :   ALTER TABLE ONLY public.pays DROP CONSTRAINT "Pays_pkey";
       public         postgres    false    186    186            H   �   x�M��
�@���S�J�R�"^� ���M������X��}3S�*���]������� ��'$v�$�����0T�O���6�v}ְ �3�����1��`{���;��oK�;��E)�s7T�@XaMj��1">ݓ=�     