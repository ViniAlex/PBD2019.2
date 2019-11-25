-- View: public.solicipendencias

-- DROP VIEW public.solicipendencias;

CREATE OR REPLACE VIEW public.solicipendencias AS 
 SELECT p.nome AS pessoa_nome,
    s.status AS status_solicitacao,
    s.tiposolicitacao AS tipsolicitacao
   FROM solicitacao s,
    pessoa p
  WHERE s.status::text = 'Pendente'::text;

ALTER TABLE public.solicipendencias
  OWNER TO postgres;
