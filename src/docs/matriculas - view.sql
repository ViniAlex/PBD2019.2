CREATE OR REPLACE VIEW matriculas AS
SELECT m.id AS matri_id,
m.status AS status_matricula,	
	a.nome AS aluno_nome,
	t.nome AS turma_nome	
FROM matricula m, aluno a, turma t
WHERE a.id = m.aluno_id
AND t.id = m.turma_id;
 