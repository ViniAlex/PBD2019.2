CREATE OR REPLACE FUNCTION calcular_media()
  RETURNS void AS
$BODY$
BEGIN

UPDATE media  
SET mediaf = (mediap + rec)/2 WHERE mediap < 7;

UPDATE media  
SET mediaf = mediap WHERE mediap >= 7;

END $BODY$
  LANGUAGE plpgsql;

 select count(*) from calcular_media()