 ALTER TABLE public.contrato_compra
 add constraint fk_contrato_compra_unidade_medida foreign key (unidade_medida_codigo) references unidade_medida (codigo);