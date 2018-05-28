package br.senai.sp.info.pweb.ianes.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "patrimonio")
public class Patrimonio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="usuario_id",nullable=false)
	private Usuario usuario;
	
	@Column(length = 40, nullable = false, unique = false)
	@Size(min = 1, max = 40, message = "{Size}")
	@NotNull
	private String nome;
	
	@Column(nullable = false)
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "categoria_id")
	private CategoriaPatrimonio categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public CategoriaPatrimonio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPatrimonio categoria) {
		this.categoria = categoria;
	}

	

}
