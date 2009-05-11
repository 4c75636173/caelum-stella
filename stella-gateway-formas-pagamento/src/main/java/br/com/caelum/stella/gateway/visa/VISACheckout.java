package br.com.caelum.stella.gateway.visa;

import java.math.BigDecimal;

import br.com.caelum.stella.gateway.core.BigDecimalFormatter;
import br.com.caelum.stella.gateway.core.CartaoCredito;

/**
 * Cont�m as informa��es necess�rias para processar uma compra atrav�s do VISA
 * 
 * @author Alberto
 * 
 */
public class VISACheckout {

	private String orderId;
	private String order;
	private String free;
	private BigDecimal price;
	private VISAFormaPagamento formaPagamento;	
	
	

	public VISACheckout(String orderId, String order, String free,
			BigDecimal price, VISAFormaPagamento formaPagamento) {
		super();
		this.orderId = orderId;
		this.order = order;
		this.free = free;
		this.price = price;
		this.formaPagamento = formaPagamento;
		
		
	}

	VISACheckout(String orderId, String order, String free,
			String unformattedPrice, VISAFormaPagamento formaPagamento) {
		super();
		this.orderId = orderId;
		this.order = order;
		this.free = free;
		this.price = calculaPrecoDividoPorCem(unformattedPrice);
		this.formaPagamento = formaPagamento;
		
	}

	private BigDecimal calculaPrecoDividoPorCem(String unformattedPrice) {
		return new BigDecimalFormatter()
				.stringInCentsToBigDecimal(unformattedPrice);
	}




	/**
	 * 
	 * @return pre�o formatado em cent�simos.
	 */
	public String getValorFormatado() {
		return new BigDecimalFormatter().bigDecimalToStringInCents(this.price);
	}

	/**
	 * informacoes de parcelamento do checkout
	 * 
	 * @return
	 */
	public VISAFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	/**
	 * Retorna a descricao completa da compra, referente ao campo oficial order
	 * 
	 * @return
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Campo livre, qualquer informacao adicional que queria ser
	 * disponibilizada.
	 * 
	 * @return
	 */
	public String getFree() {
		return free;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getPrice() {
		return price;
	}

}
