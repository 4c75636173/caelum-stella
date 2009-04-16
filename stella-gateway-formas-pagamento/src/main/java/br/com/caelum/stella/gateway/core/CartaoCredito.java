package br.com.caelum.stella.gateway.core;

import java.util.Calendar;

/**
 * Classe que mantem as informa��es de cart�o de cr�dito.
 * @author Alberto Pc
 *
 */
public class CartaoCredito {

	private long ccn;
	private Calendar exp;
	private int cvv2;
	
	
	
	public CartaoCredito(long ccn, Calendar exp, int cvv2) {
		super();
		this.ccn = ccn;
		this.exp = exp;
		this.cvv2 = cvv2;
	}
	public long getCcn() {
		return ccn;
	}
	public Calendar getExp() {
		return exp;
	}
	public int getCvv2() {
		return cvv2;
	}
	
	/**
	 * 
	 * @return Data de expira��o do cartao formatada para aamm
	 */
	public String getExpFormatado(){
		return String.format("%1$tm%1$ty",this.exp);
	}	
	
}
