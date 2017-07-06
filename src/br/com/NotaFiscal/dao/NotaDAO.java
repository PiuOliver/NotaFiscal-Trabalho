package br.com.NotaFiscal.dao;

import java.util.List;

import br.com.NotaFiscal.Graphics.ViewIndicadores;
import br.com.NotaFiscal.modelo.EmitenteDestinatario;
import br.com.NotaFiscal.modelo.Item;
import br.com.NotaFiscal.modelo.NotaFiscal;

public interface NotaDAO {
	
		NotaFiscal inserir (NotaFiscal nota);
		
		NotaFiscal atualizar(NotaFiscal nota);

	    List<NotaFiscal> listarTodos();
	    
	    Item inserirItem (Item item);
	    
	    List<Item> listarItens();
	    
	    EmitenteDestinatario em = new EmitenteDestinatario();

		
	    
}
