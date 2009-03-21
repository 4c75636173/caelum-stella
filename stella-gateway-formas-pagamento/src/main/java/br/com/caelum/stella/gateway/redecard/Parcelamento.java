package br.com.caelum.stella.gateway.redecard;

import br.com.caelum.stella.gateway.core.InvalidCheckoutException;

public class Parcelamento {

	private TipoTransacao tipoTransacao;
	private int numeroDeParcelas;
	
	
		

	/**
	 * 
	 * @param tipoTransacao
	 * @param numeroDeParcelas
	 * @throws InvalidCheckoutException caso a combina��o tipoTransacao e numeroDeParcelas esteja errada.
	 */
	public Parcelamento(final TipoTransacao tipoTransacao, final int numeroDeParcelas) {
		super();
		if(tipoTransacao.equals(TipoTransacao.A_VISTA)){
			if(numeroDeParcelas>0){
				throw new InvalidCheckoutException("Para transa��o a vista o n�mero de parcelas deve ser igual a 0");
			}

		}	
		else{
			if(numeroDeParcelas<=0){
				throw new InvalidCheckoutException("Para transa��es com "+numeroDeParcelas+" parcela(s), use o tipo de transa��o a vista");
			}
		}
		this.tipoTransacao = tipoTransacao;
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	/**
	 * 
	 * @return Parcelamento j� configurado para transacao a vista.
	 */
	public static Parcelamento newParcelamentoAVista(){
		return new Parcelamento(TipoTransacao.A_VISTA,0);
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public int getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
		
	public String getNumeroDeParcelasComNoMinimoDoisDigitos(){					
		return String.format("%02d",numeroDeParcelas);
	}
	
	
}
