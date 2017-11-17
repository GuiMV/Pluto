--
-- Name: historico_classificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE historico_classificacao (
    id bigint NOT NULL,
    mes integer NOT NULL,
    ano integer NOT NULL,
    id_classificacao bigint NOT NULL,
    id_cliente bigint NOT NULL
);

--
-- Data for Name: historico_classificacao; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: historico_classificacao historico_classificacao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historico_classificacao ADD CONSTRAINT historico_classificacao_pk PRIMARY KEY (id);


--
-- Name: historico_classificacao historico_classificacao_id_classificacao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historico_classificacao ADD CONSTRAINT historico_classificacao_id_classificacao_fk FOREIGN KEY (id_classificacao) REFERENCES classificacao(id);


--
-- Name: historico_classificacao historico_classificacao_id_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historico_classificacao  ADD CONSTRAINT historico_classificacao_id_cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id);


--
-- PostgreSQL database dump complete
--