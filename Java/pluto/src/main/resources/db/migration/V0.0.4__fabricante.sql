--
-- Name: fabricante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE fabricante (
    id bigint NOT NULL,
    id_pessoa bigint NOT NULL,
    data_exclusao timestamp with time zone
);


--
-- Data for Name: fabricante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: fabricante fabricante_id_pessoa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fabricante
    ADD CONSTRAINT fabricante_id_pessoa_uk UNIQUE (id_pessoa);


--
-- Name: fabricante fabricante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fabricante
    ADD CONSTRAINT fabricante_pk PRIMARY KEY (id);


--
-- Name: fabricante fabricante_id_pessoa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fabricante
    ADD CONSTRAINT fabricante_id_pessoa_fk FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);


--
-- PostgreSQL database dump complete
--

