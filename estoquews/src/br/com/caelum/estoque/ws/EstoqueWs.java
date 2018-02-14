package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWs {
	ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosItens")
	@WebResult(name="item")
	@ResponseWrapper(localName="itens")
	@RequestWrapper(localName="listaItens")
	public List<Item> getItens(@WebParam(name="filtros") Filtros filtros) {
		System.out.println("getItens():");
		return dao.todosItens(filtros.getLista());
	}
	
	@WebMethod(operationName="cadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(
			@WebParam(name="tokenUsuario", header=true)TokenUsuario token,
			@WebParam(name="item")Item item) throws AutorizacaoException {
		
		System.out.println("cadastrarItem(item): "+item+" token: "+token);
		
		boolean valido = new TokenDao().ehValido(token);
		if(!valido) {
			throw new AutorizacaoException("Autorizacao Falhou");
		}
		
		 new ItemValidador(item).validate();
		
		this.dao.cadastrar(item);
		return item;
	}
	
}
