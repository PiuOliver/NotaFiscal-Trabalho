package br.com.NotaFiscal.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

import br.com.NotaFiscal.Event;
import br.com.NotaFiscal.dao.NotaDAO;
import br.com.NotaFiscal.dao.NotaDAO_Hibernate;
import br.com.NotaFiscal.modelo.EmitenteDestinatario;
import br.com.NotaFiscal.modelo.Item;
import br.com.NotaFiscal.modelo.NotaFiscal;

import java.util.Random;

public class ViewCadastro {
	
	public void exibirCadastrar(Event<NotaFiscal> event){
		
		NotaDAO dao = new NotaDAO_Hibernate();
		
		NotaFiscal i = new NotaFiscal();
		
		Item it = new Item();
		
		Date data = new Date();
		Date dataE = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		

		Random r = new Random();
		int n = r.nextInt(5);
		dataE.setDate(data.getDate() + n);	
		
		
		Object[] colunas = new String[]{"Código","Nome","Preço","Qnt","Total"};
		Object[][] dados = new Object[][]{
			
		};

		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		
		
		JTable tabela = new JTable(dados, colunas);
		tabela.setModel(model);
		
		JScrollPane painelTabela = new JScrollPane();
		painelTabela.setPreferredSize(new Dimension(500,200));
		painelTabela.setViewportView(tabela);	
		
		JFrame cadastro = new JFrame("Cadastro");
		cadastro.setVisible(true);
		cadastro.setSize(880,450);
		cadastro.setLocationRelativeTo(null);
		
		
		//PAINEL 2
		JPanel filho = new JPanel();
		filho.setLayout(new BoxLayout(filho, BoxLayout.X_AXIS));
		//NUMERO DA NOTA
		filho.add(new JLabel("Nº da nota: " ));
		JTextField codigo = new JTextField(14);
		codigo.setText(""+i.getCodigo());
		filho.add(codigo);
		//MODELO
		filho.add((new JLabel(" Modelo: ")));
		JTextField desc = new JTextField(14);
		desc.setText(i.getModelo());
		desc.setEditable(false);
		filho.add(desc);
		
		JPanel filho2 = new JPanel();
		filho2.setLayout(new BoxLayout(filho2, BoxLayout.Y_AXIS));
		filho2.setLayout(new FlowLayout(FlowLayout.LEFT));
		//NATUREZA
		filho2.add((new JLabel("Natureza: ")));
		JTextField natu = new JTextField(10);
		natu.setText(i.getNatureza());
		natu.setEditable(false);
		filho2.add(natu);
		//DATA OPERAÇÃO
		filho2.add((new JLabel(" Data Operação: ")));
		JTextField dataOp = new JTextField(10);
		dataOp.setText(df.format(data));
		dataOp.setEditable(false);
		filho2.add(dataOp);
		//DATA EMISSÃO
		filho2.add((new JLabel(" Data Emissão: ")));
		JTextField dataEm = new JTextField(10);
		dataEm.setText(df.format(dataE));
		dataEm.setEditable(false);
		filho2.add(dataEm);
		
		JPanel pai2 = new JPanel();
		pai2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pai2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pai2.setBorder(BorderFactory.createTitledBorder("  ::: INFORMAÇÕES :::  "));
		pai2.add(filho);
		pai2.add(filho2);
		//FIM DO PAINEL 2
		
		//PAINEL 3
		JPanel filho3 = new JPanel();
		filho3.setLayout(new BoxLayout(filho3, BoxLayout.X_AXIS));
		//CNPJ / CPF
		filho3.add(new JLabel("CNPJ/CPF: " ));
		JTextField CnpjCpf = new JTextField(14);
		filho3.add(CnpjCpf);
		//RAZÃO SOCIAL
		filho3.add((new JLabel(" Razão social/Nome: ")));
		JTextField nome = new JTextField(14);
		filho3.add(nome);
		
		JPanel filho4 = new JPanel();
		filho4.setLayout(new BoxLayout(filho4, BoxLayout.Y_AXIS));
		filho4.setLayout(new FlowLayout(FlowLayout.LEFT));
		//INSC. ESTADUAL
		filho4.add((new JLabel("Inscrição Estadual: ")));
		JTextField inscri = new JTextField(10);
		filho4.add(inscri);
		//ESTADO
		filho4.add((new JLabel(" Estado: ")));
		JTextField estado = new JTextField(10);
		filho4.add(estado);
		//QNT ITENS
//		filho4.add((new JLabel(" Qnt. Itens: ")));
//		JTextField qnt = new JTextField(10);
//		filho4.add(qnt);
//		//VALOR
//		filho4.add((new JLabel(" Valor: ")));
//		JTextField valor = new JTextField(10);
//		filho4.add(valor);
		
		JPanel pai3 = new JPanel();
		pai3.setLayout(new FlowLayout(FlowLayout.CENTER));
		pai3.setBorder(BorderFactory.createLineBorder(Color.black));
		pai3.setBorder(BorderFactory.createTitledBorder("  ::: EMITENTE :::  "));
		pai3.add(filho3);
		pai3.add(filho4);
		//FIM DO PAINEL 3
		
		//PAINEL 3
				JPanel filho7 = new JPanel();
				filho7.setLayout(new BoxLayout(filho7, BoxLayout.X_AXIS));
				//CNPJ / CPF
				filho7.add(new JLabel("CNPJ/CPF: " ));
				JTextField CnpjCpf1 = new JTextField(14);
				filho7.add(CnpjCpf1);
				//RAZÃO SOCIAL
				filho7.add((new JLabel(" Razão social/Nome: ")));
				JTextField nome1 = new JTextField(14);
				filho7.add(nome1);
				
				JPanel filho8 = new JPanel();
				filho8.setLayout(new BoxLayout(filho8, BoxLayout.Y_AXIS));
				filho8.setLayout(new FlowLayout(FlowLayout.LEFT));
				//INSC. ESTADUAL
				filho8.add((new JLabel("Inscrição Estadual: ")));
				JTextField inscri1 = new JTextField(10);
				filho8.add(inscri1);
				//ESTADO
				filho8.add((new JLabel(" Estado: ")));
				JTextField estado1 = new JTextField(10);
				filho8.add(estado1);
				//QNT ITENS
				
				JPanel pai5 = new JPanel();
				pai5.setLayout(new FlowLayout(FlowLayout.CENTER));
				pai5.setBorder(BorderFactory.createLineBorder(Color.black));
				pai5.setBorder(BorderFactory.createTitledBorder("  ::: DESTINATÁRIO :::  "));
				pai5.add(filho7);
				pai5.add(filho8);
		
		//PAINEL 4
				
		//CAMPO TEXTO
		JPanel filho5 = new JPanel();
		filho5.setLayout(new BoxLayout(filho5, BoxLayout.X_AXIS));
		filho5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JTextArea text = new JTextArea(5,60);
		filho5.add(text);
		//BOTÃO ADD
		JPanel filho6 = new JPanel();
		filho6.setLayout(new BoxLayout(filho6, BoxLayout.X_AXIS));
		
		JPanel pai4 = new JPanel();
		pai4.setLayout(new BoxLayout(pai4, BoxLayout.Y_AXIS));
		pai4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pai4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pai4.setBorder(BorderFactory.createTitledBorder("  ::: OBSERVAÇÕES :::  "));
		pai4.add(filho5);
		pai4.add(filho6);
		
				
		JPanel filho9= new JPanel();
		filho9.add(new JLabel("Código Item: "));
		JTextField codItem = new JTextField(5);
		filho9.add(codItem);
		filho9.add(new JLabel("Nome: "));
		JTextField DescricaoItem = new JTextField(10);
		filho9.add(DescricaoItem);
		filho9.add(new JLabel("Preço: "));
		JTextField precoItem = new JTextField(5);
		filho9.add(precoItem);
		filho9.add(new JLabel("Qnt Itens: "));
		JTextField qntItens = new JTextField(5);
		filho9.add(qntItens);
		
		//BOTÃO ADD
		JPanel filho10 = new JPanel();
		filho10.setLayout(new BoxLayout(filho10, BoxLayout.X_AXIS));
		JButton addItem = new JButton("Adicionar");
		filho10.add(addItem);//CRIAR FUNÇÃO PARA BOTÃO
		//BOTÃO DELETE
		JButton delItem = new JButton("Delete");
		filho10.add(delItem);//CRIAR FUNÇÃO PARA BOTÃO
		
		
		JPanel pai7 = new JPanel();
		pai7.setLayout(new BoxLayout(pai7, BoxLayout.X_AXIS));
		pai7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pai7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pai7.setBorder(BorderFactory.createTitledBorder("  ::: ITENS :::  "));
		pai7.add(filho9);
		pai7.add(filho10);
		
		JPanel pai6 = new JPanel();
		pai6.setLayout(new BoxLayout(pai6, BoxLayout.Y_AXIS));
		pai6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pai6.add(painelTabela);
		
		JPanel botao = new JPanel();
		botao.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JButton salvar = new JButton("Salvar");
		botao.add(salvar);	
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ((codigo.getText().length() == 0)
				  ||(desc.getText().length() == 0)
				  ||(natu.getText().length() == 0)
				  ||(CnpjCpf.getText().length() == 0)
				  ||(CnpjCpf1.getText().length() == 0)
				  ||(nome.getText().length() == 0)
				  ||(nome1.getText().length() == 0)
				  ||(inscri.getText().length() == 0)
				  ||(inscri1.getText().length() == 0)
				  ||(estado.getText().length() == 0)
				  ||(estado.getText().length() == 0)
				  ||(qntItens.getText().length() == 0)
				  ||(precoItem.getText().length() == 0)
				  ||(text.getText().length() == 0)) {
				
					JOptionPane.showMessageDialog(null, "Dados inválidos! Por Favor, Preencha todos os campos");
				
				} else {
				
				int qntde = Integer.parseInt(qntItens.getText());
				Double v = Double.parseDouble(precoItem.getText());
				Double qValor = v * qntde;
					
				Integer cod = Integer.parseInt(codigo.getText());
				Date dOP = new Date();
				Date dEM =  dataE;
				String CC = CnpjCpf1.getText();
				String name = nome1.getText();
				Integer in = Integer.parseInt(inscri1.getText());
				String t = text.getText();
				
				
				String es = estado1.getText();
				
				String n = nome.getText();
				String Cf = CnpjCpf.getText();
				Integer ins = Integer.parseInt(inscri.getText());
				String est = estado.getText();
				
				String name1 = nome1.getText();
				String CC1 = CnpjCpf1.getText();
				Integer in1 = Integer.parseInt(inscri1.getText());
				String es1 = estado1.getText();
				
				
				String natureza = natu.getText();
				String modelo = desc.getText();
				String obs = text.getText();
				
				Integer c = Integer.parseInt(codItem.getText());
				String dI = DescricaoItem.getText();
				
				
				NotaFiscal i = new NotaFiscal();
				i.setCodigo(cod);
				i.setValor(v);
				i.setModelo(i.getModelo());
				i.setNatureza(i.getNatureza());
				i.setDataEmissao(dEM);
				i.setDataOperacao(dOP);
				i.setTexto(t);
				i.setQuantidadeItens(qntde);
				i.setModelo(modelo);
				i.setTexto(obs);
				
				Item it = new Item();
				it.setCodigo(c);
				it.setDescricao(dI);
				it.setPreco(v);
				it.setQnt(qntde);
				it.setValorTotal(qValor);
				
				
				
				EmitenteDestinatario ed = new EmitenteDestinatario();
				ed.setCnpjCpf(Cf);
				ed.setEstado(est);
				ed.setInscricao(ins);
				ed.setSocialNome(n);
			
				i.setDestinatario(ed);
			
				EmitenteDestinatario de = new EmitenteDestinatario();
				de.setCnpjCpf(CC1);
				de.setEstado(es1);
				de.setInscricao(in1);
				de.setSocialNome(name1);
				
				i.setEmitende(de);
				
				
				JOptionPane.showMessageDialog(salvar, "Salvo com Sucesso");
				
				event.notify(i);
				cadastro.dispose();

//				dao.inserirItem(it);
				
				i.addIem(it);
				
				dao.inserir(i);
				}
						
			}
		});
		
		JButton cancelar = new JButton("Cancelar");
		botao.add(cancelar);	
		
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int n = JOptionPane.showConfirmDialog(cancelar, "Tem certeza que deseja cancelar está Operação?", "Cancelar", JOptionPane.YES_NO_OPTION);
				
				if(n == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(cancelar, "Cancelado com Sucesso");
					
				}
					cadastro.dispose();
			}
		});
		
		addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Integer c = Integer.parseInt(codItem.getText());
				String n = DescricaoItem.getText();
				int qntde = Integer.parseInt(qntItens.getText());
				Double v = Double.parseDouble(precoItem.getText());
				Double qValor = v * qntde;
				
				model.addRow(new Object[]{
						c.toString(),
						n.toString(),
						"R$" + v.toString(),
						qntde,
						"R$" + qValor.toString(),
				});
				
			}
		});
		
		delItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1){
				int selectRow = tabela.getSelectedRow();
				model.removeRow(tabela.getSelectedRow());
				}
			}
		});
		
		//PAINEL PAI
		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		painel.add(pai2);
		painel.add(pai3);
		painel.add(pai5);
		painel.add(pai4);
		painel.add(pai6);
		painel.add(pai7);
		painel.add(botao);

		cadastro.add(painel);
		cadastro.setExtendedState(Frame.MAXIMIZED_BOTH);
	}

}