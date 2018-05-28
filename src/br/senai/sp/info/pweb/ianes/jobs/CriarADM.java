package br.senai.sp.info.pweb.ianes.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.models.TipoUsuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;



@Component
public class CriarADM implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UsuarioDao usuarioDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
System.out.println("CADASTRANDO USUÁRIO ADMINISTRADOR PADRÃO...");
		
		Usuario usuario = new Usuario();
		usuario.setEmail("admin@email.com");
		usuario.setSenha("admin123");
		usuario.setNome("Administrador");
		usuario.setSobrenome("do Sistema");
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		usuario.hashearSenha();
		
		//Inserir no banco de dados
		System.out.println("Verificando se ADMINISTRADOR EXISTE...");
		
		if(usuarioDAO.buscarPorEmail(usuario.getEmail()) == null) {
			usuarioDAO.persistir(usuario);
		}else {
			System.out.println("Usuário já existe, prosseguindo com a aplicação normalmente");
		}
		
	}
		
		
	}


