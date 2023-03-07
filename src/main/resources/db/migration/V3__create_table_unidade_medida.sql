CREATE TABLE public.unidade_medida
(
    codigo character varying(4) NOT NULL,
    chave_integracao character varying(100),
    created_at character varying(255),
    created_by character varying(255),
    updated_at character varying(255),
    updated_by character varying(255),
    descricao character varying(100) NOT NULL,
    CONSTRAINT unidade_medida_pkey PRIMARY KEY (codigo)
 );