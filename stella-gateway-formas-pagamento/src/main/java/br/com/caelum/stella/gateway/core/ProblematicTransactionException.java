package br.com.caelum.stella.gateway.core;



/**
 * Exception que deve ser lan�ada quando o retorno da integra��o n�o
 * for o que deveria ser. Ex: a transa��o falhou...
 * @author Alberto Pc
 *
 */
public class ProblematicTransactionException extends RuntimeException {
	
	private Object someReturn;
		
		
	public ProblematicTransactionException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProblematicTransactionException(String message,Object someReturn) {
		super(message);
		this.someReturn = someReturn;
		// TODO Auto-generated constructor stub
	}

	public ProblematicTransactionException(String message, Throwable throwable,Object someReturn) {
		super(message, throwable);
		this.someReturn = someReturn;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return gerado pela tentativa de transa��o.
	 */
	public Object getSomeReturn() {
		return someReturn;
	}
	
	
	

	
	
	
	

}
