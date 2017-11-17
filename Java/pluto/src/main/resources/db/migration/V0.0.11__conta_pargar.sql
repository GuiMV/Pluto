--
-- Name: conta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE conta_pagar (
    id bigint NOT NULL,
    data timestamp with time zone NOT NULL,
    valor_total double precision NOT NULL,
    id_usuario bigint NOT NULL,
    id_pessoa bigint NOT NULL,
    data_quitacao date,
    data_exclusao timestamp with time zone
);



--
-- Data for Name: conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: conta_pagar conta_pagar_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_pagar   ADD CONSTRAINT conta_pagar_pk PRIMARY KEY (id);


--
-- Name: conta_pagar conta_pagar_id_pessoa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_pagar  ADD CONSTRAINT conta_pagar_id_pessoa_fk FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);


--
-- Name: conta_pagar conta_pagar_id_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conta_pagar  ADD CONSTRAINT conta_pagar_id_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id);



--
-- PostgreSQL database dump complete
--

--
-- Name: parcela_conta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE parcela_conta_pagar (
    id bigint NOT NULL,
    valor double precision NOT NULL,
    data_vencimento date NOT NULL,
    data_quitacao date,
    id_conta_pagar bigint NOT NULL
);

--
-- Data for Name: parcela_conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: parcela_conta_pagar parcela_conta_pagar_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcela_conta_pagar ADD CONSTRAINT parcela_conta_pagar_pk PRIMARY KEY (id);


--
-- Name: parcela_conta_pagar parcela_conta_pagar_id_conta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcela_conta_pagar ADD CONSTRAINT parcela_conta_pagar_id_conta_pagar_fk FOREIGN KEY (id_conta_pagar) REFERENCES conta_pagar(id);


--
-- PostgreSQL database dump complete
--