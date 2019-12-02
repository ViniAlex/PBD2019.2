CREATE OR REPLACE FUNCTION atualizar_situacao_media()
  RETURNS void AS
$BODY$
BEGIN

UPDATE media  
SET situacao = 'RP - REPROVADO' WHERE mediaf < 5 AND isrec = true;

UPDATE media  
SET situacao = 'AM - APROVADO POR MÉDIA' WHERE mediap >= 7;

UPDATE media  
SET situacao = 'NÃO DEFINIDO' WHERE mediap < 7 AND isrec = false;

UPDATE media  
SET situacao = 'AP - APROVADO' WHERE mediaf >= 5 AND mediap < 7 AND isrec = true;

UPDATE media  
SET mediaf = mediap WHERE rec = 0;

END $BODY$
  LANGUAGE plpgsql;

 select count(*) from atualizar_situacao_media()
