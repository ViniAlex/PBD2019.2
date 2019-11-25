CREATE OR REPLACE VIEW public.solicipendencias AS 
 SELECT s.nome,
    s.id AS solici_id,
    s.status AS status_solicitacao,
    s.tiposolicitacao AS tipsolicitacao
   FROM solicitacao s
  WHERE s.status::text = 'Pendente'::text;

ALTER TABLE public.solicipendencias
  OWNER TO postgres;
