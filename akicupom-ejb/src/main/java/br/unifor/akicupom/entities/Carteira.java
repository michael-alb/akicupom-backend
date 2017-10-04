package br.unifor.akicupom.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="carteira")
public class Carteira implements Serializable {

	private static final long serialVersionUID = -3982256456752493972L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column
	private int qtdCupons;

	/* Mapeamento Relacional */

	@OneToMany
	private List<Cupom> cupoms;
	
	/* Getter e Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtdCupons() {
		return qtdCupons;
	}

	public void setQtdCupons(int qtdCupons) {
		this.qtdCupons = qtdCupons;
	}

	public List<Cupom> getCupoms() {
		return cupoms;
	}

	public void setCupoms(List<Cupom> cupoms) {
		this.cupoms = cupoms;
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
		Carteira other = (Carteira) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", qtdCupons=" + qtdCupons + ", cupoms=" + cupoms + "]";
	}
}