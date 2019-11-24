CREATE OR REPLACE FUNCTION criar_log()
RETURNS TRIGGER
AS'
BEGIN

IF TG_OP = ''INSERT'' THEN
 	INSERT INTO log (data, autor, alteracao, tabela, anterior, atual) 
 	VALUES (now(), user, TG_OP, TG_RELNAME, ''Novo Registro'', NEW);
 ELSE
 	INSERT INTO log (data, autor, alteracao, tabela, anterior, atual)
	VALUES (now(), user, TG_OP, TG_RELNAME, OLD, NEW)

 END IF;
RETURN NEW;
END '
LANGUAGE plpgsql;

CREATE OR UPDATE TRIGGER log_pessoa
BEFORE INSERT OR UPDATE OR DELETE
ON pessoa
FOR EACH ROW
EXECUTE PROCEDURE criar_log();


