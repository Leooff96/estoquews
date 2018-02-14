package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublishWs {

	public static void main(String[] args) {
		EstoqueWs estoque = new EstoqueWs();
		Endpoint.publish("http://localhost:8090/estoquews", estoque);
		System.out.println("Ws publicado em http://localhost:8090/estoquews");
	}

}
