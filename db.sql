--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1 (Debian 11.1-3.pgdg90+1)
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE mediaweb;
--
-- Name: mediaweb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE mediaweb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE mediaweb OWNER TO postgres;

\connect mediaweb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: document; Type: TABLE; Schema: public; Owner: DEVERDUX
--

CREATE TABLE public.document (
    name character varying(255),
    type character varying(255),
    id integer NOT NULL,
    subscriber character varying(255)
);


ALTER TABLE public.document OWNER TO "DEVERDUX";

--
-- Name: subscriber; Type: TABLE; Schema: public; Owner: DEVERDUX
--

CREATE TABLE public.subscriber (
    login character varying(255) NOT NULL,
    passwd character varying(255),
    isbibliothecaire boolean
);


ALTER TABLE public.subscriber OWNER TO "DEVERDUX";

--
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: DEVERDUX
--

INSERT INTO public.document VALUES ('Le chant du loup', 'dvd', 321, NULL);
INSERT INTO public.document VALUES ('Thriller', 'cd', 32, 'jean');
INSERT INTO public.document VALUES ('Harry Potter et la coupe de feu', 'livre', 211, 'jean');


--
-- Data for Name: subscriber; Type: TABLE DATA; Schema: public; Owner: DEVERDUX
--

INSERT INTO public.subscriber VALUES ('jean', '1234', false);
INSERT INTO public.subscriber VALUES ('xavier', '6412', true);
INSERT INTO public.subscriber VALUES ('matthieu', '3558', true);


--
-- Name: document document_pk; Type: CONSTRAINT; Schema: public; Owner: DEVERDUX
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pk PRIMARY KEY (id);


--
-- Name: subscriber subscriber_pk; Type: CONSTRAINT; Schema: public; Owner: DEVERDUX
--

ALTER TABLE ONLY public.subscriber
    ADD CONSTRAINT subscriber_pk PRIMARY KEY (login);


--
-- Name: document_id_uindex; Type: INDEX; Schema: public; Owner: DEVERDUX
--

CREATE UNIQUE INDEX document_id_uindex ON public.document USING btree (id);


--
-- Name: subscriber_login_uindex; Type: INDEX; Schema: public; Owner: DEVERDUX
--

CREATE UNIQUE INDEX subscriber_login_uindex ON public.subscriber USING btree (login);


--
-- Name: document fk_subscriber; Type: FK CONSTRAINT; Schema: public; Owner: DEVERDUX
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT fk_subscriber FOREIGN KEY (subscriber) REFERENCES public.subscriber(login) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

