--
-- Name: classificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE classificacao (
    id bigint NOT NULL,
    nome varchar(64) NOT NULL
);

--
-- Data for Name: classificacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO classificacao (id, nome) VALUES (1, 'Sem Classificação');
INSERT INTO classificacao (id, nome) VALUES (2, 'Ouro');
INSERT INTO classificacao (id, nome) VALUES (3, 'Prata');
INSERT INTO classificacao (id, nome) VALUES (4, 'Bronze');


--
-- Name: classificacao classificacao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY classificacao ADD CONSTRAINT classificacao_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--