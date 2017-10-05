package br.unifor.akicupom.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="promocao")
public class Promocao implements Serializable {

	private static final long serialVersionUID = -461880334586264909L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String nome;

	@Column(nullable=false)
	private String descricao;

	@Column(nullable=false)
	private String dataValidade;
	
	@Column
	private Long valor_promocao;

	@Column(nullable=false)
	private String capa;
	
	@Column
	private boolean status;
	
	@Column(name="promo_cat")
	private String promoCategoria;
	
	/* Mapeamento Relacional */

	@OneToOne
	private Categoria categoria;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	/* Getters e Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Long getValor_promocao() {
		return valor_promocao;
	}

	public void setValor_promocao(Long valor_promocao) {
		this.valor_promocao = valor_promocao;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPromoCategoria() {
		return promoCategoria;
	}

	public void setPromoCategoria(String promoCategoria) {
		this.promoCategoria = promoCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Promocao other = (Promocao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Promocao [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataValidade=" + dataValidade
				+ ", valor_promocao=" + valor_promocao + ", capa=" + capa + ", status=" + status + ", promoCategoria="
				+ promoCategoria + ", categoria=" + categoria + ", fornecedor=" + fornecedor + "]";
	}
}