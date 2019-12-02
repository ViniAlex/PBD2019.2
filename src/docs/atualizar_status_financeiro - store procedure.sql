CREATE OR REPLACE FUNCTION atualizar_status_financeiro()
RETURNS void
AS'
BEGIN
	UPDATE financeiro f
	SET status = ''ATRASADO''
	WHERE f.dtaVencimento < CURRENT_DATE;

	UPDATE financeiro f
	SET status = ''PAGO''
	WHERE f.valorPago >= f.valorTotal;

END'
LANGUAGE plpgsql;

select count(*) from atualizar_status_financeiro()