--
-- Name: conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE conta_receber (
    id bigint NOT NULL,
    data timestamp with time zone NOT NULL,
    valor_total double precision NOT NULL,
    id_usuario bigint NOT NULL,
    id_venda bigint,
    id_cliente bigint NOT NULL,
    data_quitacao date,
    data_exclusao timestamp with time zone
);



--
-- Data for Name: conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: conta_receber conta_receber_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_receber   ADD CONSTRAINT conta_receber_pk PRIMARY KEY (id);


--
-- Name: conta_receber conta_receber_id_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_receber  ADD CONSTRAINT conta_receber_id_cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id);


--
-- Name: conta_receber conta_receber_id_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_receber  ADD CONSTRAINT conta_receber_id_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id);


--
-- Name: conta_receber conta_receber_id_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_receber  ADD CONSTRAINT conta_receber_id_venda_fk FOREIGN KEY (id_venda) REFERENCES venda(id);


--
-- PostgreSQL database dump complete
--

--
-- Name: parcela_conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE parcela_conta_receber (
    id bigint NOT NULL,
    valor double precision NOT NULL,
    data_vencimento date NOT NULL,
    data_quitacao date,
    id_conta_receber bigint NOT NULL
);

--
-- Data for Name: parcela_conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: parcela_conta_receber parcela_conta_receber_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcela_conta_receber ADD CONSTRAINT parcela_conta_receber_pk PRIMARY KEY (id);


--
-- Name: parcela_conta_receber parcela_conta_receber_id_conta_receber_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcela_conta_receber ADD CONSTRAINT parcela_conta_receber_id_conta_receber_fk FOREIGN KEY (id_conta_receber) REFERENCES conta_receber(id);


--
-- PostgreSQL database dump complete
--


--
-- Name: parcela; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE parcela (
    id bigint NOT NULL,
    quantidade integer NOT NULL
);

--
-- Data for Name: parcela; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO parcela (id, quantidade) VALUES (1, 1);


--
-- Name: parcela parcela_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcela  ADD CONSTRAINT parcela_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

