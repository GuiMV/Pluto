--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE funcionario (
    id bigint NOT NULL,
    id_pessoa bigint NOT NULL,
    data_exclusao timestamp with time zone,
    salario_fixo double precision NOT NULL,
    comissao double precision NOT NULL
);


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: funcionario funcionario_id_pessoa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_id_pessoa_uk UNIQUE (id_pessoa);


--
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (id);


--
-- Name: funcionario funcionario_id_pessoa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_id_pessoa_fk FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);


--
-- PostgreSQL database dump complete
--

