--
-- Name: tipo_item_comercializavel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_item_comercializavel (
    id bigint NOT NULL,
    nome varchar(64) NOT NULL
);

--
-- Data for Name: tipo_item_comercializavel; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipo_item_comercializavel (id, nome) VALUES (1, 'Produto');
INSERT INTO tipo_item_comercializavel (id, nome) VALUES (2, 'Serviço');


--
-- Name: tipo_item_comercializavel tipo_item_comercializavel_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_item_comercializavel ADD CONSTRAINT tipo_item_comercializavel_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

--
-- Name: item_comercializavel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE item_comercializavel (
    id bigint NOT NULL,
    nome varchar(128) NOT NULL,
    apresentacao varchar(128) NOT NULL,
    nome_completo varchar(256) NOT NULL,
    valor_venda double precision NOT NULL,
    id_tipo_item_comercializavel bigint NOT NULL,
    id_fabricante bigint,
    cean varchar(14),
    data_exclusao date
);

--
-- Data for Name: item_comercializavel; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: item_comercializavel item_comercializavel_cean_uk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_comercializavel ADD CONSTRAINT item_comercializavel_cean_uk UNIQUE (cean);


--
-- Name: item_comercializavel item_comercializavel_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_comercializavel ADD CONSTRAINT item_comercializavel_pk PRIMARY KEY (id);


--
-- Name: item_comercializavel item_comercializavel_id_tipo_item_comercializavel_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_comercializavel ADD CONSTRAINT item_comercializavel_id_tipo_item_comercializavel_fk FOREIGN KEY (id_tipo_item_comercializavel) REFERENCES tipo_item_comercializavel(id);
ALTER TABLE ONLY item_comercializavel ADD CONSTRAINT item_comercializavel_id_fabricante_fk FOREIGN KEY (id_fabricante) REFERENCES fabricante (id);

--
-- PostgreSQL database dump complete
--
