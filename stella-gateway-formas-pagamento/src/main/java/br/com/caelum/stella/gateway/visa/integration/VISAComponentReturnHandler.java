package br.com.caelum.stella.gateway.visa.integration;

import br.com.caelum.stella.gateway.core.ProblematicTransactionException;

/**
 * Respons�vel por verificar o retorno dado pelos componentes.
 * @author Alberto Pc
 *
 */
public class VISAComponentReturnHandler {

	private VISAIntegrationReturn retornoIntegracao;

	
	
	
	
	
	public VISAComponentReturnHandler(
			VISAIntegrationReturn resultadoIntegracao) {
		super();
		this.retornoIntegracao = resultadoIntegracao;
	}





	/**
	 * Verifica se a resposta de determinado componente para a solicita��o indica
	 * sucesso ou n�o.
	 * @throws ProblematicTransactionException caso o retorno indique problema
	 */
	public VISAIntegrationReturn check(){			
		if (retornoIntegracao.getLr()!=0 && retornoIntegracao.getLr()!=11) {
			throw new ProblematicTransactionException(retornoIntegracao.getArs(),retornoIntegracao);
		}		
		return retornoIntegracao;
	}
	
}
