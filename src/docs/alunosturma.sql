CREATE OR REPLACE VIEW public.alunosturma AS 
 SELECT a.id AS aluno_id,
    a.nome AS aluno_nome,
    t.nome AS turma_nome
   FROM aluno a,
    turma t
  WHERE a.turma_id = t.id;

ALTER TABLE public.alunosturma
  OWNER TO postgres;
