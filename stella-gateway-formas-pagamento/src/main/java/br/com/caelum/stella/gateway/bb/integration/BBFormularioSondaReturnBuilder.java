package br.com.caelum.stella.gateway.bb.integration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.vidageek.mirror.Mirror;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.caelum.stella.gateway.bb.Situacao;
import br.com.caelum.stella.gateway.bb.TipoTransacao;
import br.com.caelum.stella.gateway.core.BigDecimalFormatter;
import br.com.caelum.stella.gateway.core.CalendarFormatter;
import br.com.caelum.stella.gateway.core.EnumComCodigoFinder;
import br.com.caelum.stella.gateway.core.IntegrationFailedException;
import br.com.caelum.stella.gateway.core.ReturnBuilder;

public enum BBFormularioSondaReturnBuilder implements
		ReturnBuilder<BBFormularioSondaReturn, String> {

	HTML("01") {

		public BBFormularioSondaReturn buildRetorno(String source) {
			// TODO Auto-generated method stub
			throw new IntegrationFailedException(
					"Essa forma ainda n�o esta implementada");
		}

	},

	XML("02") {

		/**
		 *  Aqui, substitui o caminho do dtd no xml e depois parsea. 
		 */
		public BBFormularioSondaReturn buildRetorno(String source) {
			// TODO Auto-generated method stub
			
			try {
				source = source.replace("lojavirtual.dtd",new File(BBFormularioSondaReturnBuilder.class.getResource("/lojavirtual.dtd").toURI()).getPath());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				throw new IntegrationFailedException("N�o foi possivel substituir o caminho para a lojavirtual.dtd",e);
			}
			return createFormularioSondaReturn(parseaXMLEMontaFormularioInicialSondaAPartirDosAtributos(source));

		}

		private RespostaInicialFormularioSonda parseaXMLEMontaFormularioInicialSondaAPartirDosAtributos(
				String source) {
			/*
			 * implementado do jeito mais b�sico, n�o achei valido jogar o Jaxb s� para isso.
			 */
			try {				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = dbf.newDocumentBuilder();
				Document document = docBuilder.parse(new ByteArrayInputStream(source
						.getBytes()));
				NodeList entradas = document.getElementsByTagName("ENTRADA");
				RespostaInicialFormularioSonda formularioSonda = new RespostaInicialFormularioSonda();
				for(int i=0;i<entradas.getLength();i++){
					Node node = entradas.item(i);
					Mirror.on(formularioSonda).set().field(node.getAttributes().item(0).getNodeValue()).withValue(node.getAttributes().item(1).getNodeValue());
				}
				return formularioSonda;
				
			} catch (Exception e) {
				// como so vou lan�ar a exception, caso ela ocorra, n�o vale a pena tratar uma por uma.
				throw new IntegrationFailedException(e);
			}
		}

	},

	STRING("03") {

		public BBFormularioSondaReturn buildRetorno(String source) {
			// TODO Auto-generated method stub
			String refTran = source.substring(0, 17);
			String valor = source.substring(17, 32);
			String idConv = source.substring(32, 38);
			String tipoTransacao = source.substring(38, 39);
			String situacao = source.substring(39, 41);
			String dataPagamento = source.substring(41);
			return createFormularioSondaReturn(new RespostaInicialFormularioSonda(
					refTran, valor, idConv, tipoTransacao, situacao,
					dataPagamento));
		}

	};

	private String codigo;

	private BBFormularioSondaReturnBuilder(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return codigo;
	}

	protected BBFormularioSondaReturn createFormularioSondaReturn(
			RespostaInicialFormularioSonda formularioSonda) {
		return new BBFormularioSondaReturn(new BBBasicDataReturn(
				formularioSonda.getIdConv(), formularioSonda.getRefTran()),
				new BigDecimalFormatter().stringInCentsToBigDecimal(formularioSonda
						.getValor()), new EnumComCodigoFinder()
						.descobreAEnumPeloCodigo(TipoTransacao.class,
								formularioSonda.getTpPagamento()),
				new EnumComCodigoFinder().descobreAEnumPeloCodigo(
						Situacao.class, formularioSonda.getSituacao()),
				CalendarFormatter.stringToCalendar(formularioSonda
						.getDataPagamento(), "ddMMyyyy"));
	}

}
