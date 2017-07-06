package br.com.NotaFiscal;

import br.com.NotaFiscal.modelo.EmitenteDestinatario;
import br.com.NotaFiscal.modelo.NotaFiscal;

public interface Event<T> {

	public void notify(T info);

	
}
