--
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE empresa (
    id bigint NOT NULL,
    id_pessoa bigint NOT NULL,
    data_exclusao timestamp with time zone
);


--
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: empresa empresa_id_pessoa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_id_pessoa_uk UNIQUE (id_pessoa);


--
-- Name: empresa empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pk PRIMARY KEY (id);


--
-- Name: empresa empresa_id_pessoa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_id_pessoa_fk FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);


--
-- PostgreSQL database dump complete
--

