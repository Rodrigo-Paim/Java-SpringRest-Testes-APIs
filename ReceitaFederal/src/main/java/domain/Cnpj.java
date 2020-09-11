package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="cnpj")

public class Cnpj implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Size
	private String cnpj;
	
	private String nome;
	private SituacaoCnpj situacao;
	
	public Cnpj() {
		
	}
	public Cnpj(String cnpj, String nome, SituacaoCnpj situacao) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.situacao = situacao;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public SituacaoCnpj getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoCnpj situacao) {
		this.situacao = situacao;
	}
	
	

}
