--
-- Name: forma_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE forma_pagamento (
    id bigint NOT NULL,
    nome varchar(64) NOT NULL
);


--
-- Data for Name: forma_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: forma_pagamento forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY forma_pagamento ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--
--
-- Name: status_orcamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE status_orcamento (
    id bigint NOT NULL,
    nome varchar(64) NOT NULL
);


--
-- Data for Name: status_orcamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO status_orcamento (id, nome) VALUES (1, 'Aberto');
INSERT INTO status_orcamento (id, nome) VALUES (2, 'Cancelado');
INSERT INTO status_orcamento (id, nome) VALUES (3, 'Liberado');
INSERT INTO status_orcamento (id, nome) VALUES (4, 'Concluído');
INSERT INTO status_orcamento (id, nome) VALUES (5, 'Expirado');


--
-- Name: status_orcamento status_orcamento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY status_orcamento ADD CONSTRAINT status_orcamento_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


--
-- Name: veiculo_orcamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE veiculo_orcamento (
    id bigint NOT NULL,
    id_modelo_veiculo bigint NOT NULL,
    id_cor bigint NOT NULL,
    placa character(7) NOT NULL
);


--
-- Data for Name: veiculo_orcamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: veiculo_orcamento veiculo_orcamento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo_orcamento ADD CONSTRAINT veiculo_orcamento_pk PRIMARY KEY (id);


--
-- Name: veiculo_orcamento veiculo_orcamento_id_modelo_veiculo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo_orcamento ADD CONSTRAINT veiculo_orcamento_id_modelo_veiculo_fk FOREIGN KEY (id_modelo_veiculo) REFERENCES modelo_veiculo(id);


--
-- PostgreSQL database dump complete
--


--
-- Name: orcamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE orcamento (
    id bigint NOT NULL,
    data timestamp with time zone NOT NULL,
    id_status_orcamento bigint NOT NULL,
    id_usuario bigint NOT NULL,
    id_cliente bigint NOT NULL,
    id_veiculo_orcamento bigint NOT NULL,
    descricao varchar(256) NOT NULL,
    id_forma_pagamento bigint NOT NULL,
    valor_total double precision NOT NULL
);


--
-- Data for Name: orcamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: orcamento orcamento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orcamento ADD CONSTRAINT orcamento_pk PRIMARY KEY (id);


--
-- Name: orcamento orcamento_id_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orcamento ADD CONSTRAINT orcamento_id_cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id);


--
-- Name: orcamento orcamento_id_forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orcamento ADD CONSTRAINT orcamento_id_forma_pagamento_fk FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento(id);


--
-- Name: orcamento orcamento_id_status_orcamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orcamento ADD CONSTRAINT orcamento_id_status_orcamento_fk FOREIGN KEY (id_status_orcamento) REFERENCES status_orcamento(id);


--
-- Name: orcamento orcamento_id_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orcamento ADD CONSTRAINT orcamento_id_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id);


--
-- Name: orcamento orcamento_id_veiculo_orcamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orcamento ADD CONSTRAINT orcamento_id_veiculo_orcamento_fk FOREIGN KEY (id_veiculo_orcamento) REFERENCES veiculo_orcamento(id);


--
-- PostgreSQL database dump complete
--


--
-- Name: item_orcamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE item_orcamento (
    id bigint NOT NULL,
    id_orcamento bigint NOT NULL,
    id_item_comercializavel bigint NOT NULL,
    quantidade double precision NOT NULL,
    valor_venda double precision NOT NULL,
    valor_total double precision NOT NULL
);


--
-- Data for Name: item_orcamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: item_orcamento item_orcamento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_orcamento ADD CONSTRAINT item_orcamento_pk PRIMARY KEY (id);


--
-- Name: item_orcamento item_orcamento_id_item_comercializavel_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_orcamento ADD CONSTRAINT item_orcamento_id_item_comercializavel_fk FOREIGN KEY (id_item_comercializavel) REFERENCES item_comercializavel(id);


--
-- Name: item_orcamento item_orcamento_id_orcamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_orcamento ADD CONSTRAINT item_orcamento_id_orcamento_fk FOREIGN KEY (id_orcamento) REFERENCES orcamento(id);


--
-- PostgreSQL database dump complete
--

