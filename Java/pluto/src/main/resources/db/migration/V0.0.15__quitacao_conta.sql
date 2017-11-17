ALTER TABLE item_orcamento ADD COLUMN id_funcionario bigint;


ALTER TABLE ONLY item_orcamento ADD CONSTRAINT item_orcamento_id_funcionario_fk FOREIGN KEY (id_funcionario) REFERENCES funcionario(id);

--
-- Name: quitacao_parcela_conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE quitacao_parcela_conta_receber (
  id bigint NOT NULL,
  id_parcela_conta_receber bigint NOT NULL,
  id_forma_pagamento bigint NOT NULL,
  valor double precision NOT NULL
);


ALTER TABLE quitacao_parcela_conta_receber OWNER TO postgres;

--
-- Data for Name: quitacao_parcela_conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: quitacao_parcela_conta_receber quitacao_parcela_conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quitacao_parcela_conta_receber ADD CONSTRAINT quitacao_parcela_conta_receber_pk PRIMARY KEY (id);


--
-- Name: quitacao_parcela_conta_receber quitacao_parcela_conta_receber_id_forma_pagamento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quitacao_parcela_conta_receber ADD CONSTRAINT quitacao_parcela_conta_receber_id_forma_pagamento_fk FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento(id);


--
-- Name: quitacao_parcela_conta_receber quitacao_parcela_conta_receber_id_parcela_conta_receber_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quitacao_parcela_conta_receber ADD CONSTRAINT quitacao_parcela_conta_receber_id_parcela_conta_receber_fk FOREIGN KEY (id_parcela_conta_receber) REFERENCES parcela_conta_receber(id);


--
-- PostgreSQL database dump complete
--

--
-- Name: quitacao_parcela_conta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE quitacao_parcela_conta_pagar (
  id bigint NOT NULL,
  id_parcela_conta_pagar bigint NOT NULL,
  id_forma_pagamento bigint NOT NULL,
  valor double precision NOT NULL
);


ALTER TABLE quitacao_parcela_conta_pagar OWNER TO postgres;

--
-- Data for Name: quitacao_parcela_conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: quitacao_parcela_conta_pagar quitacao_parcela_conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quitacao_parcela_conta_pagar ADD CONSTRAINT quitacao_parcela_conta_pagar_pk PRIMARY KEY (id);


--
-- Name: quitacao_parcela_conta_pagar quitacao_parcela_conta_pagar_id_forma_pagamento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quitacao_parcela_conta_pagar ADD CONSTRAINT quitacao_parcela_conta_pagar_id_forma_pagamento_fk FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento(id);


--
-- Name: quitacao_parcela_conta_pagar quitacao_parcela_conta_pagar_id_parcela_conta_pagar_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quitacao_parcela_conta_pagar ADD CONSTRAINT quitacao_parcela_conta_pagar_id_parcela_conta_pagar_fk FOREIGN KEY (id_parcela_conta_pagar) REFERENCES parcela_conta_pagar(id);


--
-- PostgreSQL database dump complete
--

