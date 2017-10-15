--
-- Name: estabelecimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE estabelecimento (
    id bigint NOT NULL,
    id_pessoa bigint NOT NULL,
    data_exclusao timestamp with time zone
);


--
-- Data for Name: estabelecimento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: estabelecimento estabelecimento_id_pessoa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estabelecimento
    ADD CONSTRAINT estabelecimento_id_pessoa_uk UNIQUE (id_pessoa);


--
-- Name: estabelecimento estabelecimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estabelecimento
    ADD CONSTRAINT estabelecimento_pk PRIMARY KEY (id);


--
-- Name: estabelecimento estabelecimento_id_pessoa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estabelecimento
    ADD CONSTRAINT estabelecimento_id_pessoa_fk FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);


--
-- PostgreSQL database dump complete
--

--
-- Data for Name: estabelecimento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO email (id, endereco) VALUES (2, 'contato@contato.com');
INSERT INTO telefone (id, numero, id_tipo_telefone) VALUES (2, '4833333333', 3);
INSERT INTO endereco (id, cep, logradouro, numero, complemento, referencia, bairro, id_municipio, id_tipo_endereco) VALUES (2, '88010500', 'Rua Anita Garibaldi', '', '', '', 'Centro', 4205407, 1);
INSERT INTO pessoa (id, razao_social, nome_fantasia, data_nascimento, cpf_cnpj, rg_ie, id_sexo, id_estado_civil, id_endereco, id_email, id_telefone, id_tipo_pessoa) VALUES (2, 'Empresa', 'Empresa', NULL, '88888888888888', '', NULL, NULL, 2, 2, 2, 2);
INSERT INTO pessoa_endereco (id_pessoa, id_endereco) VALUES (2, 2);
INSERT INTO pessoa_telefone(id_pessoa, id_telefone)	VALUES (2, 2);
INSERT INTO pessoa_email(id_pessoa, id_email) VALUES (2, 2);
INSERT INTO estabelecimento (id, id_pessoa, data_exclusao) VALUES (1, 2, NULL);
