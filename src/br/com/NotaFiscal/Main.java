package br.com.NotaFiscal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import imagem.*;
import javafx.css.SimpleStyleableFloatProperty;
import br.com.NotaFiscal.Graphics.ViewCadastro;
import br.com.NotaFiscal.Graphics.ViewEditar;
import br.com.NotaFiscal.Graphics.ViewIndicadores;
import br.com.NotaFiscal.Graphics.ViewSobre;
import br.com.NotaFiscal.dao.DAOException;
import br.com.NotaFiscal.dao.JPAUtil;
import br.com.NotaFiscal.dao.NotaDAO;
import br.com.NotaFiscal.dao.NotaDAO_Hibernate;
import br.com.NotaFiscal.modelo.EmitenteDestinatario;
import br.com.NotaFiscal.modelo.NotaFiscal;

public class Main  {

	public static void main(String[] args) throws FileNotFoundException, FontFormatException, IOException, DAOException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		NotaDAO dao = new NotaDAO_Hibernate();
		
		Object[] colunas = new String[] {"Código","Modelo", "Inscrição", "Data-Emissão", "Data-Operação", "CNPJ/CPF", "Emitente","Destinatário", "Estado", "Quantidade", "Valor","Texto"};
		Object[][] dados = new Object[][]{
			
		};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		
		for(NotaFiscal notas : dao.listarTodos()){
			model.addRow(new Object[]{
					notas.getCodigo(),
					notas.getModelo(),
					notas.getEmitende().getInscricao(),
					df.format(notas.getDataEmissao()),
					df.format(notas.getDataOperacao()),
					notas.getEmitende().getCnpjCpf(),
					notas.getEmitende().getSocialNome(),
					notas.getDestinatario().getSocialNome(),
					notas.getEmitende().getEstado(),
					notas.getQuantidadeItens(),
					notas.getValor(),
					notas.getTexto(),
				});
			}
		
		
		JTable tabela = new JTable();
		tabela.setModel(model);
		tabela.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 12));
		
		JFrame Janela = new JFrame();
		Janela.setTitle("Trabalho Modular");
		Janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Janela.setLocationRelativeTo(null);
		
		JMenuItem eMS = new JMenuItem("Sair");
		eMS.setToolTipText("Sair do Aplicativo");
		eMS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		JMenuItem eMSb = TelaSobre();
		
		JMenuItem eMI = TelaIndicadores();
		
		JMenuBar menubar = Menu(eMS, eMSb, eMI);
		
		JButton btnAdd = new JButton(new ImageIcon("imagem/add.png"));
		JButton btnModify = new JButton(new ImageIcon("imagem/modify.png"));
		JButton btnDelete = new JButton(new ImageIcon("imagem/delete.png"));
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ViewCadastro().exibirCadastrar(new Event<NotaFiscal>() {

					@Override
					public void notify(NotaFiscal i) {
						model.addRow(new Object[]{
								i.getCodigo(),
								i.getModelo(),
								i.getEmitende().getInscricao(),
								df.format(i.getDataEmissao()),
								df.format(i.getDataOperacao()),
								i.getEmitende().getCnpjCpf(),
								i.getDestinatario().getSocialNome(),
								i.getEmitende().getSocialNome(),
								i.getDestinatario().getEstado(),
								i.getQuantidadeItens(),
								i.getValor(),
								i.getTexto(),
								
						});
						
					}
			});	
			}
			});
			
		
		btnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				try{
					
					int selectRow = tabela.getSelectedRow();
					
					Integer codigo = Integer.parseUnsignedInt(model.getValueAt(selectRow, 0).toString());
			        
					NotaFiscal item = new NotaDAO_Hibernate().buscaPorCodigo(codigo);
					
					
			        new ViewEditar().exibirEditar(item, new Event<NotaFiscal>() {
			        	public void notify(NotaFiscal info) {
							model.setValueAt(info.getCodigo(), selectRow, 0);
							model.setValueAt(info.getModelo(), selectRow, 1);
							
							model.setValueAt(info.getEmitende().getInscricao(), selectRow, 2);
							
							model.setValueAt(df.format(info.getDataEmissao()), selectRow, 3);
							model.setValueAt(df.format(info.getDataOperacao()), selectRow, 4);
							
							model.setValueAt(info.getEmitende().getCnpjCpf(), selectRow, 5);
							model.setValueAt(info.getEmitende().getSocialNome(), selectRow, 6);
							model.setValueAt(info.getEmitende().getEstado(), selectRow, 7);
							
							model.setValueAt(info.getDestinatario().getCnpjCpf(), selectRow, 8);
							model.setValueAt(info.getDestinatario().getSocialNome(), selectRow, 6);
							model.setValueAt(info.getDestinatario().getEstado(), selectRow, 7);
							
							model.setValueAt(info.getQuantidadeItens(), selectRow, 11);
							model.setValueAt(info.getValor(), selectRow, 10);
							model.setValueAt(info.getTexto(), selectRow, 11);
			        	}
					
				});
			        
				}catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Erro ao adicionar linha: "+ ex.getMessage());
				}
				
			}});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int n = JOptionPane.showConfirmDialog(btnDelete, "Tem certeza que deseja deletar esta nota fiscal?","Atenção", JOptionPane.YES_NO_OPTION);
				
				
				if(n == JOptionPane.YES_OPTION){
					if(tabela.getSelectedRow() != -1){
						
						int selectRow = tabela.getSelectedRow();
						
						Integer codigo = Integer.parseUnsignedInt(model.getValueAt(selectRow, 0).toString());
				        
						NotaFiscal item = new NotaDAO_Hibernate().remover(codigo);
						
						JOptionPane.showMessageDialog(btnDelete, "Excluido com Sucesso");
						model.removeRow(tabela.getSelectedRow());
						
					}
				}	
			}
		});
		
		JToolBar toolbar = new JToolBar("Applications");
		toolbar.add(btnAdd);
		toolbar.add(btnModify);
		toolbar.add(btnDelete);
		
		
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(tabela);
		
		Janela.add(jsp, BorderLayout.CENTER);
		Janela.setSize(800,300);
		Janela.setVisible(true);
		Janela.setJMenuBar(menubar);
		Janela.getContentPane().add(toolbar, BorderLayout.PAGE_START);
		

	}

	private static JMenuBar Menu(JMenuItem eMS, JMenuItem eMSb, JMenuItem eMI) {
		JMenu arquivo = new JMenu("Arquivo");
		arquivo.setMnemonic(KeyEvent.VK_A);
		arquivo.add(eMS);
		
		JMenu cadastro = new JMenu("Cadastro");
		cadastro.setMnemonic(KeyEvent.VK_C);
		cadastro.add(eMI);
		
		JMenu sobre = new JMenu("Sobre");
		sobre.setMnemonic(KeyEvent.VK_S);
		sobre.add(eMSb);
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(arquivo);
		menubar.add(cadastro);
		menubar.add(sobre);
		return menubar;
	}
	
	private static JMenuItem TelaIndicadores(){
		JMenuItem eMI = new JMenuItem("Tela de Indicadores");
		eMI.setMnemonic(KeyEvent.VK_I);
		eMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewIndicadores().exibirIndicadores();;
			}
		});
		return eMI;
	}

	private static JMenuItem TelaSobre() {
		JMenuItem eMSb = new JMenuItem("Sobre");
		eMSb.setMnemonic(KeyEvent.VK_S);
		
		eMSb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewSobre().exibirSobre();
				
			}
		});
		
		return eMSb;
	}

}
	
