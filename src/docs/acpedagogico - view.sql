CREATE OR REPLACE VIEW acpedagogicos AS 
SELECT ac.id AS acp_id, 
ac.data AS acp_data,
ac.descricao AS acp_desc,
ac.status AS acp_status,
a.nome AS aluno_nome
  FROM acpedagogico ac, aluno a
  where a.id = aluno_id;
 
