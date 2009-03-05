package br.com.caelum.stella.gateway.visa;

import br.com.caelum.stella.gateway.core.ProblematicTransactionException;
import br.com.caelum.stella.gateway.visa.integration.IntegrationReturn;

/**
 * Respons�vel por verificar o retorno dado pelos componentes.
 * @author Alberto Pc
 *
 */
public class ComponentReturnHandler {

	private IntegrationReturn retornoIntegracao;

	
	
	
	
	
	public ComponentReturnHandler(
			IntegrationReturn resultadoIntegracao) {
		super();
		this.retornoIntegracao = resultadoIntegracao;
	}





	/**
	 * Verifica se a resposta de determinado componente para a solicita��o indica
	 * sucesso ou n�o.
	 * @throws ProblematicTransactionException caso o retorno indique problema
	 */
	public IntegrationReturn check(){			
		if (retornoIntegracao.getLr()!=0	&& retornoIntegracao.getLr()!=11) {
			throw new ProblematicTransactionException(retornoIntegracao.getArs());
		}		
		return retornoIntegracao;
	}
	
}
