--
-- Name: cor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cor (
  id bigint NOT NULL,
  nome varchar(64) NOT NULL
);

--
-- Name: cor cor_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cor ADD CONSTRAINT cor_pk PRIMARY KEY (id);


--
-- Name: modelo_veiculo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE modelo_veiculo (
  id bigint NOT NULL,
  id_fabricante bigint NOT NULL,
  nome varchar(64) NOT NULL
);


--
-- Name: modelo_veiculo modelo_veiculo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY modelo_veiculo ADD CONSTRAINT modelo_veiculo_pk PRIMARY KEY (id);


--
-- Name: modelo_veiculo modelo_veiculo_id_fabricante_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY modelo_veiculo ADD CONSTRAINT modelo_veiculo_id_fabricante_fk FOREIGN KEY (id_fabricante) REFERENCES fabricante(id);


--
-- Name: pessoa_veiculo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pessoa_veiculo (
  id bigint NOT NULL,
  id_pessoa bigint NOT NULL,
  id_modelo_veiculo bigint NOT NULL,
  id_cor bigint NOT NULL,
  placa character(7) NOT NULL
);


--
-- Name: pessoa_veiculo pessoa_veiculo_id_pessoa_id_modelo_veiculo_placa_uk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_veiculo ADD CONSTRAINT pessoa_veiculo_id_pessoa_id_modelo_veiculo_placa_uk UNIQUE (id_pessoa, id_modelo_veiculo, placa);


--
-- Name: pessoa_veiculo pessoa_veiculo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_veiculo ADD CONSTRAINT pessoa_veiculo_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

