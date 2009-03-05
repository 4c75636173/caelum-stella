package br.com.caelum.stella.gateway.visa;

/**
 * Essa classe cont�m informa��es que sempre s�o retornadas depois da integra��o.
 * @author Alberto Pc
 *
 */
public class BasicDataReturn {

	private int lr;
	private String tid;
	private String ars;
	
	
	
	public BasicDataReturn(int lr, String tid, String ars) {
		super();
		this.lr = lr;
		this.tid = tid;
		this.ars = ars;
	}
	public int getLr() {
		return lr;
	}
	public String getTid() {
		return tid;
	}
	public String getArs() {
		return ars;
	}
	
	
}
