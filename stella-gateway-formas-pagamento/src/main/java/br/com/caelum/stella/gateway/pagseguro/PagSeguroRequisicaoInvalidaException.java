package br.com.caelum.stella.gateway.pagseguro;

/**
 * Exception que deve ser lan�ada quando for enviado uma requisi��o maliciosa tentando
 * burlar o pagamento
 * @author Alberto Pc
 *
 */
public class PagSeguroRequisicaoInvalidaException extends RuntimeException {

	public PagSeguroRequisicaoInvalidaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PagSeguroRequisicaoInvalidaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
