package br.com.NotaFiscal.Graphics;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.scene.image.Image;


public class ViewSobre {

	public void exibirSobre() {

		JPanel pai = new JPanel();
		pai.setLayout(new BoxLayout(pai, BoxLayout.Y_AXIS));
		
		JPanel tres = new JPanel();
		tres.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel eq = new JLabel();
		eq.setText(" Equipe: Gabriel de Oliveira - Luis Felipe");
		tres.add(eq);
		
		JPanel um = new JPanel();
		um.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel d = new JLabel();
		d.setText("Disciplina:"
				+ " Desenvolvimento Orientado a Objeto + Persistência de Dados");
		um.add(d);
		
		JPanel dois = new JPanel();
		dois.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel i = new JLabel();
		i.setText("<HTML>Informações sobre o Trabalho: "+
				"Trabalho desenvolvido com o intuito de, demostrar <BR>todo o conhecimento adquirido "
				+ "neste 5 Período. Utilizando o MYSQL Server <BR> como o Banco de Dados e o Hibernate como Mapeamento, todos os detalhes "
				+ "foram <BR>feitos com a orientação do professor Wellington.</HTML>");

		dois.add(i);

		JLabel fundo = new JLabel(new ImageIcon("imagem/Deadpool.gif"));
		fundo.setLayout(new FlowLayout());
		JFrame about = new JFrame("Sobre");

		pai.add(tres);
		pai.add(um);
		pai.add(dois);
		pai.add(fundo);
		
		about.add(pai);
		
		about.setVisible(true);
		about.pack();
		about.setLocationRelativeTo(null);
		
		
	}

	
	
}
