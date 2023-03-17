 ALTER TABLE public.contrato_compra ADD contrato_minuta_id bigint;

 ALTER TABLE public.contrato_compra
 add constraint fk_contrato_compra_contrato_minuta foreign key (contrato_minuta_id) references contrato_minuta (id);