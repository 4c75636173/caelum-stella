package br.com.caelum.stella.gateway.visa;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.stella.gateway.core.InvalidCheckoutException;

public class TestParcelamento {

	@Test
	public void testFormatacaoCodigoParcelamentoComVisaElectron(){		
		Assert.assertEquals("A001",new Parcelamento(TipoTransacao.DEBITO,1).getCodigoDePagamento());
	}
	
	@Test
	public void testFormatacaoCodigoParcelamentoComVisaElectronOuCreditoAVisaEMaisDeUmaParcela(){
		/*
		 * nao sei direito como testar as exceptions j� que por enquanto s� tem um tipo... esse n�o � o melhor jeito, mas fica como solu��o temporaria...
		 */
		try{
			new Parcelamento(TipoTransacao.DEBITO,2);
		}
		catch(InvalidCheckoutException numeroDeParcelasMaiorQuePermitidoException){			
			Assert.assertEquals("Com d�bito ou cr�dito a vista s� pode pagar em 1 parcela",numeroDeParcelasMaiorQuePermitidoException.getMessage());
		}
		try{
			new Parcelamento(TipoTransacao.CREDITO_A_VISTA,2);
		}
		catch(InvalidCheckoutException numeroDeParcelasMaiorQuePermitidoException){
			Assert.assertEquals("Com d�bito ou cr�dito a vista s� pode pagar em 1 parcela",numeroDeParcelasMaiorQuePermitidoException.getMessage());
		}		
	}
	
	@Test
	public void testFormatacaoCodigoComNumeroDeParcelasIgualAZero(){
		try{
			new Parcelamento(TipoTransacao.CREDITO_A_VISTA,0);
		}
		catch(InvalidCheckoutException numeroDeParcelasMaiorQuePermitidoException){
			Assert.assertEquals("O n�mero de parcelas n�o pode ser igual ou menor que 0",numeroDeParcelasMaiorQuePermitidoException.getMessage());
		}		
	}
	
	@Test
	public void testFormatacaoCodigoComJurosDaAdministradora(){
		Assert.assertEquals("3012",new Parcelamento(TipoTransacao.PARCELADO_COM_JUROS_DA_ADMINISTRADORA,12).getCodigoDePagamento());
	}
	
	@Test
	public void testFormatacaoCodigoComJurosDaLoja(){
		Assert.assertEquals("2012",new Parcelamento(TipoTransacao.PARCELADO_COM_JUROS_DA_LOJA,12).getCodigoDePagamento());
	}	
	
	@Test
	public void testFormatacaoCodigoComCreditoAVisa(){
		Assert.assertEquals("1001",new Parcelamento(TipoTransacao.CREDITO_A_VISTA,1).getCodigoDePagamento());
	}	
		
	
}
