package br.com.NotaFiscal.modelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;



@Table(name="NOTA_FISCAL")
@Entity(name="notafiscal")

public class NotaFiscal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "CODIGO", nullable = false, unique = false)
	private int codigo;
	
	private String modelo = "Modelo 1-A";
	
	private String natureza = "Venda";
	
	@Column(name = "VALOR", precision = 8 , scale = 2 , nullable=false , unique=false)
    private Double valor;
	
	@Column(name = "DATA_OPERACAO", nullable = false)
    private Date dataOperacao = new Date();
	
	@Column(name = "DATA_EMISSAO")
    private Date dataEmissao;
	
    private int quantidadeItens;
    
	@Column(name = "INF_COMPLEMENTAR", length = 2048)
	private String texto;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "emitende_id", nullable = false, foreignKey = @ForeignKey (name = "FK_DES"))
	private EmitenteDestinatario emitende;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "destinatario_id", nullable = false, foreignKey = @ForeignKey (name = "FK_DES"))
	private EmitenteDestinatario destinatario;
	
	@ManyToMany(cascade ={
			CascadeType.PERSIST, 
			CascadeType.MERGE})
	private List<Item> item;
	
	//----------------------------------------------------------------------------------------------------//
	
	public NotaFiscal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotaFiscal(long id, int codigo, String modelo, String natureza, Double valor, Date dataOperacao,
			Date dataEmissao, int quantidadeItens, String texto, EmitenteDestinatario emitende,
			EmitenteDestinatario destinatario, List<Item> item) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.modelo = modelo;
		this.natureza = natureza;
		this.valor = valor;
		this.dataOperacao = dataOperacao;
		this.dataEmissao = dataEmissao;
		this.quantidadeItens = quantidadeItens;
		this.texto = texto;
		this.emitende = emitende;
		this.destinatario = destinatario;
		this.item = item;
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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public EmitenteDestinatario getEmitende() {
		return emitende;
	}

	public void setEmitende(EmitenteDestinatario emitende) {
		this.emitende = emitende;
	}

	public EmitenteDestinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(EmitenteDestinatario destinatario) {
		this.destinatario = destinatario;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((dataEmissao == null) ? 0 : dataEmissao.hashCode());
		result = prime * result + ((dataOperacao == null) ? 0 : dataOperacao.hashCode());
		result = prime * result + ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result + ((emitende == null) ? 0 : emitende.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((natureza == null) ? 0 : natureza.hashCode());
		result = prime * result + quantidadeItens;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		NotaFiscal other = (NotaFiscal) obj;
		if (codigo != other.codigo)
			return false;
		if (dataEmissao == null) {
			if (other.dataEmissao != null)
				return false;
		} else if (!dataEmissao.equals(other.dataEmissao))
			return false;
		if (dataOperacao == null) {
			if (other.dataOperacao != null)
				return false;
		} else if (!dataOperacao.equals(other.dataOperacao))
			return false;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (emitende == null) {
			if (other.emitende != null)
				return false;
		} else if (!emitende.equals(other.emitende))
			return false;
		if (id != other.id)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (natureza == null) {
			if (other.natureza != null)
				return false;
		} else if (!natureza.equals(other.natureza))
			return false;
		if (quantidadeItens != other.quantidadeItens)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public void addIem(Item it) {
		if (this.getItem() == null) {
			this.item = new ArrayList<>();
		}
		this.item.add(it);
		it.addNota(this);
	}

}