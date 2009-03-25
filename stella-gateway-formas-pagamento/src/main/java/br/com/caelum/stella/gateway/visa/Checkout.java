package br.com.caelum.stella.gateway.visa;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.caelum.stella.gateway.core.CartaoCredito;
import br.com.caelum.stella.gateway.core.InvalidCheckoutException;
import br.com.caelum.stella.gateway.visa.integration.TIDGenerator;
import br.com.caelum.stella.gateway.visa.integration.VISAPriceFormatter;

/**
 * Cont�m as informa��es necess�rias para processar uma compra atrav�s do VISA
 * 
 * @author Alberto
 * 
 */
public class Checkout {

	/*
	 * Talvez seja bom validar todos os dados antes... mas ainda estou pensando
	 * nisso
	 */

	private String orderId;
	private String order;
	private String free;
	private BigDecimal price;
	private Parcelamento parcelamento;
	private CartaoCredito cartao;	

	public Checkout(final String orderId, final String order,
			final String free, final BigDecimal price,
			final Parcelamento parcelamento, final CartaoCredito cartao) {
		super();
		this.orderId = orderId;
		this.order = order;
		this.free = free;
		this.price = price;
		this.parcelamento = parcelamento;
		this.cartao = cartao;
	}
	
	public Checkout(final String orderId, final String order,
			final String free, final String unformattedPrice,
			final Parcelamento parcelamento, final CartaoCredito cartao) {
		super();
		this.orderId = orderId;
		this.order = order;
		this.free = free;
		this.price = calculaPrecoDividoPorCem(unformattedPrice);
		this.parcelamento = parcelamento;
		this.cartao = cartao;
	}	

	
	private BigDecimal calculaPrecoDividoPorCem(String unformattedPrice) {
		//verificar esse arredondamento.
		return VISAPriceFormatter.convertToNormalValue(unformattedPrice);
	}			
	




	public CartaoCredito getCartao() {
		return cartao;
	}

	/**
	 * 
	 * @return pre�o formatado em cent�simos.
	 */
	public String getFormattedPrice() {
		return String.format("%.0f", this.price.multiply(new BigDecimal(100)));
	}
		

	/**
	 * informacoes de parcelamento do checkout
	 * 
	 * @return
	 */
	public Parcelamento getParcelamento() {
		return parcelamento;
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
	 * @param descricaoDaCompra
	 * @return
	 */
	public Checkout withDescricaoDaCompra(String descricaoDaCompra) {
		this.order = descricaoDaCompra;
		return this;
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

	/**
	 * identificador unico gerado para cada compra.
	 * 
	 * @param data
	 *            data que serve de referencia para o calculo do tid
	 * @param numeroDeAfiliacao
	 *            numeroDeAfiliacao que serve de base para o calculo.
	 * @return
	 * @throws InvalidCheckoutException
	 *             caso o parcelamento n�o tenha sido definido
	 */
	public String getTid(Calendar data, String numeroDeAfiliacao) {
		return new TIDGenerator(this.parcelamento,data,numeroDeAfiliacao).getTid();

	}

}
