package br.com.caelum.stella.gateway.pagseguro;

/**
 * Exception que � lan�ada quando a requisi��o � feita diretamente pelo browser
 * e n�o pelo Robo da PagSeguro.
 * @author Alberto Pc
 *
 */
public class NaoEhRoboPagSeguroException extends RuntimeException {

	public NaoEhRoboPagSeguroException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
