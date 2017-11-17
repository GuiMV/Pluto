--
-- PostgreSQL database dump complete
--


--
-- Name: veiculo_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE veiculo_venda (
    id bigint NOT NULL,
    id_modelo_veiculo bigint NOT NULL,
    id_cor bigint NOT NULL,
    placa character(7) NOT NULL
);


--
-- Data for Name: veiculo_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: veiculo_venda veiculo_venda_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo_venda ADD CONSTRAINT veiculo_venda_pk PRIMARY KEY (id);


--
-- Name: veiculo_venda veiculo_venda_id_modelo_veiculo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo_venda ADD CONSTRAINT veiculo_venda_id_modelo_veiculo_fk FOREIGN KEY (id_modelo_veiculo) REFERENCES modelo_veiculo(id);


--
-- PostgreSQL database dump complete
--


--
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE venda (
    id bigint NOT NULL,
    data timestamp with time zone NOT NULL,
    id_orcamento bigint NULL,
    id_usuario bigint NOT NULL,
    id_cliente bigint NOT NULL,
    id_veiculo_venda bigint NOT NULL,
    descricao varchar(256) NOT NULL,
    valor_total double precision NOT NULL
);


--
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: venda venda_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ADD CONSTRAINT venda_pk PRIMARY KEY (id);


--
-- Name: venda venda_id_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ADD CONSTRAINT venda_id_cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id);


--
-- Name: venda venda_id_orcamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ADD CONSTRAINT venda_id_orcamento_fk FOREIGN KEY (id_orcamento) REFERENCES orcamento(id);


--
-- Name: venda venda_id_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ADD CONSTRAINT venda_id_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id);


--
-- Name: venda venda_id_veiculo_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ADD CONSTRAINT venda_id_veiculo_venda_fk FOREIGN KEY (id_veiculo_venda) REFERENCES veiculo_venda(id);


--
-- PostgreSQL database dump complete
--


--
-- Name: item_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE item_venda (
    id bigint NOT NULL,
    id_venda bigint NOT NULL,
    id_item_comercializavel bigint NOT NULL,
    quantidade double precision NOT NULL,
    valor_venda double precision NOT NULL,
    valor_total double precision NOT NULL,
    id_funcionario bigint,
    comissao double precision NOT NULL
);


--
-- Data for Name: item_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: item_venda item_venda_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_venda ADD CONSTRAINT item_venda_pk PRIMARY KEY (id);


--
-- Name: item_venda item_venda_id_item_comercializavel_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_venda ADD CONSTRAINT item_venda_id_item_comercializavel_fk FOREIGN KEY (id_item_comercializavel) REFERENCES item_comercializavel(id);


--
-- Name: item_venda item_venda_id_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_venda ADD CONSTRAINT item_venda_id_venda_fk FOREIGN KEY (id_venda) REFERENCES venda(id);


--
-- Name: item_venda item_venda_id_funcionario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_venda ADD CONSTRAINT item_venda_id_funcionario_fk FOREIGN KEY (id_funcionario) REFERENCES funcionario(id);

--
-- PostgreSQL database dump complete
--