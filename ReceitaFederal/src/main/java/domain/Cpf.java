package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="cpf")

public class Cpf implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Size(min = 11, max = 11)
	private String cpf;
	
	private String nome;
	
	private SituacaoCpf situacao;
	
	
	public Cpf() {
		
	}
	public Cpf(String cpf, String nome, SituacaoCpf situacao) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.situacao = situacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SituacaoCpf getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCpf situacao) {
		this.situacao = situacao;
	}
	

}
