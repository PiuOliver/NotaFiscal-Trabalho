package br.com.NotaFiscal.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="EMITENTE_DESTINATARIO")
@Entity(name = "emitenteDestinatario")
public class EmitenteDestinatario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "CNPJ_CPF", nullable = false, length = 14)
    private String CnpjCpf;
	
	@Column(name = "SOCIAL_NOME", nullable = false, length = 200)
    private String socialNome;
	
	@Column(name = "INS_ESTADUAL", nullable = false, length = 20)
    private Integer inscricao;
	
	@Column(name = "ESTADO", nullable = false, length = 2)
    private String estado;

	//----------------------------------------------------------------------------------------------------//
	
	public EmitenteDestinatario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmitenteDestinatario(String cnpjCpf, String socialNome, Integer inscricao, String estado) {
		super();
		CnpjCpf = cnpjCpf;
		this.socialNome = socialNome;
		this.inscricao = inscricao;
		this.estado = estado;
	}

	public String getCnpjCpf() {
		return CnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		CnpjCpf = cnpjCpf;
	}

	public String getSocialNome() {
		return socialNome;
	}

	public void setSocialNome(String socialNome) {
		this.socialNome = socialNome;
	}

	public Integer getInscricao() {
		return inscricao;
	}

	public void setInscricao(Integer inscricao) {
		this.inscricao = inscricao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CnpjCpf == null) ? 0 : CnpjCpf.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((inscricao == null) ? 0 : inscricao.hashCode());
		result = prime * result + ((socialNome == null) ? 0 : socialNome.hashCode());
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
		EmitenteDestinatario other = (EmitenteDestinatario) obj;
		if (CnpjCpf == null) {
			if (other.CnpjCpf != null)
				return false;
		} else if (!CnpjCpf.equals(other.CnpjCpf))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (inscricao == null) {
			if (other.inscricao != null)
				return false;
		} else if (!inscricao.equals(other.inscricao))
			return false;
		if (socialNome == null) {
			if (other.socialNome != null)
				return false;
		} else if (!socialNome.equals(other.socialNome))
			return false;
		return true;
	}
	
	
    
	
}
