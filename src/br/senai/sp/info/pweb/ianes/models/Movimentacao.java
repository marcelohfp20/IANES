package br.senai.sp.info.pweb.ianes.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "cod_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "cod_ambiente_origem")
	private Ambiente ambienteOrigem;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "cod_ambiente_destino")
	private Ambiente ambienteDestino;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "itemPatrimonio")
	private ItemDePatrimonio itemPatrimonio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ambiente getAmbienteOrigem() {
		return ambienteOrigem;
	}

	public void setAmbienteOrigem(Ambiente ambienteOrigem) {
		this.ambienteOrigem = ambienteOrigem;
	}

	public Ambiente getAmbienteDestino() {
		return ambienteDestino;
	}

	public void setAmbienteDestino(Ambiente ambienteDestino) {
		this.ambienteDestino = ambienteDestino;
	}

	public ItemDePatrimonio getItemPatrimonio() {
		return itemPatrimonio;
	}

	public void setItemPatrimonio(ItemDePatrimonio itemPatrimonio) {
		this.itemPatrimonio = itemPatrimonio;
	}

	
	
	

}