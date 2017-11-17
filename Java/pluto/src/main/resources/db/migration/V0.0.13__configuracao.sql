--
-- Name: configuracao_email; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE configuracao_email (
    id bigint NOT NULL,
    host_name varchar(128) NOT NULL,
    port integer NOT NULL,
    ssl boolean NOT NULL,
    user_name varchar(256) NOT NULL,
    password varchar(256) NOT NULL,
    emitter varchar(128) NOT NULL,
    subject varchar(128) NOT NULL,
    message varchar(2048) NOT NULL
);

--
-- Data for Name: configuracao_email; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO configuracao_email (id, host_name, port, ssl, user_name, password, emitter, subject, message) VALUES (1, 'smtp.gmail.com', 465, true, 'seu.email@gmail.com', 'suasenha', 'Empresa', 'Contato', 'Obrigado pela preferência.

Para lhe atender melhor, gostaríamos que você retorna-se este e-mail respondendo as seguintes questões:
O serviço foi executado como solicitado?
Você esta satisfeito com o serviço?
Deseja realizar algum comentário?

Obrigado.');


--
-- Name: configuracao_email configuracao_email_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_email ADD CONSTRAINT configuracao_email_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--
--
-- Name: configuracao_classificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE configuracao_classificacao (
    id bigint NOT NULL,
    periodo integer NOT NULL,
    valor_ouro double precision NOT NULL,
    frequencia_ouro integer NOT NULL,
    recencia_ouro integer NOT NULL,
    valor_prata double precision NOT NULL,
    frequencia_prata integer NOT NULL,
    recencia_prata integer NOT NULL
);

--
-- Data for Name: configuracao_classificacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO configuracao_classificacao (id, periodo, valor_ouro, frequencia_ouro, recencia_ouro, valor_prata, frequencia_prata, recencia_prata) VALUES (1, 6, 10000, 6, 30, 5000, 3, 90);


--
-- Name: configuracao_classificacao configuracao_classificacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_classificacao  ADD CONSTRAINT configuracao_classificacao_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


