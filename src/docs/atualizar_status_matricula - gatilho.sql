CREATE OR REPLACE FUNCTION atualizar_status_matricula()
RETURNS TRIGGER
AS'
BEGIN
UPDATE matricula m
	SET status = ''APROVADO''
	FROM aluno a, media e
	WHERE a.id = e.aluno_id AND a.id = m.aluno_id AND (e.situacao = ''AM - APROVADO POR MÉDIA'' OR e.situacao = ''AP - APROVADO'');

UPDATE matricula m
	SET status = ''REPROVADO''
	FROM aluno a, media e
	WHERE a.id = e.aluno_id AND a.id = m.aluno_id AND e.situacao = ''RP - REPROVADO'';

UPDATE matricula m
	SET status = ''NÃO DEFINIDO''
	FROM aluno a, media e
	WHERE a.id = e.aluno_id AND a.id = m.aluno_id AND e.situacao = ''NÃO DEFINIDO'';

RETURN NEW;
END'
LANGUAGE plpgsql;


CREATE TRIGGER atualizar_status_matricula
BEFORE INSERT OR UPDATE
ON media
FOR EACH ROW
EXECUTE PROCEDURE atualizar_status_matricula();
