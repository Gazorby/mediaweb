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

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auth; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auth (
    login character varying(255) NOT NULL,
    passwd character varying(255) NOT NULL,
    isbibliothecaire boolean NOT NULL
);


ALTER TABLE public.auth OWNER TO postgres;

--
-- Name: document; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.document (
    name character varying NOT NULL,
    type character varying NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.document OWNER TO postgres;

--
-- Data for Name: auth; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auth VALUES ('jean', '1234', true);
INSERT INTO public.auth VALUES ('matthieu', '6542', false);
INSERT INTO public.auth VALUES ('xavier', '6521', true);


--
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.document VALUES ('Le chant du loup', 'dvd', 12);
INSERT INTO public.document VALUES ('Harry Potter et le coupe de feu', 'livre', 1354);
INSERT INTO public.document VALUES ('Thriller', 'CD', 32);


--
-- Name: auth auth_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auth
    ADD CONSTRAINT auth_pk PRIMARY KEY (login);


--
-- Name: document table_name_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT table_name_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

