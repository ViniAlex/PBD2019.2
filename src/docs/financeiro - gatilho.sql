CREATE OR REPLACE FUNCTION gerar_financeiro()
RETURNS TRIGGER
AS'
BEGIN
	IF TG_OP = ''INSERT'' THEN
		INSERT INTO financeiro (id, dtavencimento, status, valormensalidade, valorpago, qtdparcela, 
       valortotal, matricula_id)
		VALUES (NEW.id, NEW.dtaVencimento, ''NÃO DEFINIDO'', NEW.valormensalidade, NEW.valormensalidade, 11, NEW.valortotal, NEW.id);
		
	ELSIF TG_OP = ''UPDATE'' THEN
		UPDATE financeiro
		SET valormensalidade = NEW.valormensalidade, valortotal = NEW.valortotal
		WHERE id = NEW.id;
	END IF;
	
RETURN NEW;
END'
LANGUAGE plpgsql;

CREATE TRIGGER gerar_financeiro
AFTER INSERT OR UPDATE
ON matricula
FOR EACH ROW
EXECUTE PROCEDURE gerar_financeiro();