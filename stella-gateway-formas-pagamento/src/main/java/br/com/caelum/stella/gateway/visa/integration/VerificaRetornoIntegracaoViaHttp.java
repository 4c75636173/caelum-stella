package br.com.caelum.stella.gateway.visa.integration;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.stella.gateway.core.IntegrationHandler;
import br.com.caelum.stella.gateway.core.ProblematicTransactionException;
import br.com.caelum.stella.gateway.visa.ComponentReturnHandler;

/**
 * Classe que verifica os possiveis retornos das integra��es.
 * @author Alberto Pc
 *
 */
public class VerificaRetornoIntegracaoViaHttp implements
		IntegrationHandler<IntegrationReturn> {

	private HttpServletRequest request;
	private HttpReturnBuilder httpRetornoBuilder;

	/**
	 * 
	 * @param request request que contem as informa��es
	 * @param httpRetornoBuilder builder especifico para a ocasi�o.
	 */
	public VerificaRetornoIntegracaoViaHttp(final HttpServletRequest request,
			final HttpReturnBuilder httpRetornoBuilder) {
		super();
		this.request = request;
		this.httpRetornoBuilder = httpRetornoBuilder;
	}
	
	/**
	 * @return RetornoIntegracao
	 * @throws ProblematicTransactionException caso a resposta n�o contenha c�digo v�lido.
	 */
	public IntegrationReturn handle() {
		// TODO Auto-generated method stub
		IntegrationReturn retornoIntegracao = httpRetornoBuilder.buildRetorno(request);		
		return new ComponentReturnHandler(retornoIntegracao).check();
	}

}
