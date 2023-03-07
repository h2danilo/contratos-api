CREATE TABLE public.contrato_minuta
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 CACHE 1 ),
    chave_integracao character varying(100),
    created_at character varying(255),
    created_by character varying(255),
    updated_at character varying(255),
    updated_by character varying(255),
    descricao character varying(100) NOT NULL,
    inativo boolean DEFAULT false,
    filial_id bigint,
    CONSTRAINT contrato_minuta_pkey PRIMARY KEY (id)
);