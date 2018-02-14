package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault")
public class AutorizacaoException extends Exception {
	private static final long serialVersionUID = 1L;


	public AutorizacaoException(String string) {
		super(string);
	}

	
	public InfoFault getFaultInfo() {
		return new InfoFault( "Token Invalido", new Date());
	}
}
