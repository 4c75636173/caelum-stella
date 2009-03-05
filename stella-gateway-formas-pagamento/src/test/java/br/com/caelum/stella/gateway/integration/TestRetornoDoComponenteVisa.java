package br.com.caelum.stella.gateway.integration;

import javax.servlet.http.HttpServletRequest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import br.com.caelum.stella.gateway.visa.ComponentReturnHandler;
import br.com.caelum.stella.gateway.visa.integration.IntegrationReturn;

/**
 * Testa os possiveis de retorno
 * @author Alberto Pc
 *
 */
public class TestRetornoDoComponenteVisa {

	@Test
	public void testRetornoValidoDoCheckoutComLrIgualA00() {
		Mockery mockery = new Mockery();
		final HttpServletRequest request = mockery.mock(HttpServletRequest.class);		
		mockery.checking(new Expectations(){
			{				
				atLeast(1).of(request).getParameter("lr");will(returnValue("00"));								
			}
		});
		new ComponentReturnHandler(criarRetornoDeIntegracao(request)).check();		
		mockery.assertIsSatisfied();
	}

	@Test
	public void testRetornoValidoDoCheckoutComLrIgualA11() {
		Mockery mockery = new Mockery();
		final HttpServletRequest request = mockery.mock(HttpServletRequest.class);		
		mockery.checking(new Expectations(){
			{
				atLeast(1).of(request).getParameter("lr");will(returnValue("11"));																
			}
		});	
		new ComponentReturnHandler(criarRetornoDeIntegracao(request)).check();		
		mockery.assertIsSatisfied();
	}	
	
	private IntegrationReturn criarRetornoDeIntegracao(final HttpServletRequest request) {
		return new IntegrationReturn(){

			public String getArs() {
				// TODO Auto-generated method stub
				return "N�o deve ser utilizado";
			}

			public int getLr() {
				// TODO Auto-generated method stub
				return Integer.valueOf(request.getParameter("lr"));
			}

			public String getTid() {
				// TODO Auto-generated method stub
				return "432743896";
			}
			
		};
	}
	
}
