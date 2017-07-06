package br.com.NotaFiscal.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "CODIGO_PRODUTO", nullable = false)
	private int codigo;
	
	@Column(name = "DESCRICAO_PRODUTO", length = 200)
	private String descricao;
	
	@Column(name = "PRECO_PRODUTO", scale = 5)
	private double preco;
	
	@Column(name = "QUANTIDADE_PRODUTO")
	private int qnt;
	
	private double valorTotal;
	
	@ManyToMany(mappedBy = "item", cascade= CascadeType.ALL)
	private List<NotaFiscal> nf = new ArrayList<>();

	
	public Item(long id, int codigo, String descricao, double preco, int qnt, double valorTotal) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.qnt = qnt;
		this.valorTotal = valorTotal;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQnt() {
		return qnt;
	}

	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<NotaFiscal> getNf() {
		return nf;
	}

	public void setNf(List<NotaFiscal> nf) {
		this.nf = nf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + qnt;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (codigo != other.codigo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (qnt != other.qnt)
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}
	
	public Double valorProdutoTotal(){
		
		valorTotal = preco * qnt;
		
		return valorTotal;
		
	}

	public void addNota(NotaFiscal notaFiscal) {
		nf.add(notaFiscal);
		
	}
	
}
