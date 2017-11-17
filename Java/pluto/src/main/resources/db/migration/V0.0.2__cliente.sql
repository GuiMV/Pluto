--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cliente (
    id bigint NOT NULL,
    id_pessoa bigint NOT NULL,
    data_exclusao timestamp with time zone,
    id_classificacao bigint NOT NULL DEFAULT 1
);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: cliente cliente_id_pessoa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_id_pessoa_uk UNIQUE (id_pessoa);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pk PRIMARY KEY (id);


--
-- Name: cliente cliente_id_pessoa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_id_pessoa_fk FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);




ALTER TABLE ONLY cliente ADD CONSTRAINT cliente_id_classificacao_fk FOREIGN KEY (id_classificacao) REFERENCES classificacao(id);
--
-- PostgreSQL database dump complete
--

