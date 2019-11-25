CREATE OR REPLACE VIEW public.alunosturma AS 
 SELECT a.nome AS aluno_nome,
    t.nome AS turma_nome
   FROM aluno a,
    turma t
  WHERE a.id = t.id;

ALTER TABLE public.alunosturma
  OWNER TO postgres;

