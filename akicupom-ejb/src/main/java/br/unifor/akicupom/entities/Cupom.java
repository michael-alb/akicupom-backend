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
@Table(name="cupom")
public class Cupom implements Serializable {

	private static final long serialVersionUID = -3600807981321208989L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private String nome;	

	@Column(name="data_validade", nullable=false)
	private String dataValidade;
	
	@Column(name="data_geracao", nullable=false)
	private String dataGeracao; 
	
	@Column(name="cod_cupom", nullable=false)
	private String codigoCupom;
	
	/* Mapeamento Relacional */
	
	@OneToOne
	private Promocao promocao;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Carteira carteira;

	/* Getters e Setters */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getCodigoCupom() {
		return codigoCupom;
	}

	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
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
		Cupom other = (Cupom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cupom [id=" + id + ", titulo=" + titulo + ", nome=" + nome + ", dataValidade=" + dataValidade
				+ ", dataGeracao=" + dataGeracao + ", codigoCupom=" + codigoCupom + ", promocao=" + promocao
				+ ", usuario=" + usuario + ", carteira=" + carteira + "]";
	}
}