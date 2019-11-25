CREATE OR REPLACE VIEW vw_turma
AS SELECT a.nome AS aluno_nome, 
t.nome AS turma_nome
FROM aluno a, turma t
WHERE a.id = t.id;

ALTER TABLE vw_turma
OWNER TO postgres;
