package br.com.NotaFiscal.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.NotaFiscal.dao.JPAUtil;
import br.com.NotaFiscal.dao.NotaDAO_Hibernate;
import br.com.NotaFiscal.modelo.NotaFiscal;

public class ViewIndicadores {
	
	public void exibirIndicadores(){

		NotaDAO_Hibernate teste = new NotaDAO_Hibernate();
		
		JFrame Indicadores = new JFrame("Indicadores");
		Indicadores.setVisible(true);
		Indicadores.setSize(900,200);
		Indicadores.setLocationRelativeTo(null);
		  
		Object[] colunasIndicadores = new String[] {"Indicadores", "Valor"};
		Object[][] valorIndicadores = new Object[][]{
			{"Total de Notas Fiscais cadastradas", teste.resultadoTotal()},
			{"Média de valor das Notas", "R$ " + teste.resultadoMedia()},
			{"Média de valor dos itens nas Notas", "R$ " + teste.MediaItens()},
			{"Maior valor de nota", "R$ " + teste.MaiorValor()},
			{"Estado com maior numero de notas emitidas", teste.estadoMaiorNotas()},
			{"Estado com maior numero de notas como destinatário",teste.EstadoMaiorDestinatario()},
			{"CNPJ/Nome da empresa que é a maior compradora em volume de vendas", teste.cnpjMaiorCompradora()},
			{"CNPJ/Nome da empresa que é a maior vendedora em volume de vendas", teste.cnpjMaiorVendedora()},
			{"Total de notas com valor superior a 10mil", teste.totalNotasSuperior()},
			{"Total de notas com mais de 10 itens", teste.notaMais10()},
		};
		
		DefaultTableModel modelIndicadores = new DefaultTableModel(valorIndicadores, colunasIndicadores);
		JTable tabelaIndicadores = new JTable();
		tabelaIndicadores.setModel(modelIndicadores);
		tabelaIndicadores.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 12));
		tabelaIndicadores.setEnabled(false);
		
		JScrollPane  painelTabela = new JScrollPane();
		painelTabela.setViewportView(tabelaIndicadores);	
	
		
		Indicadores.add(painelTabela);
		
	}
	
}
