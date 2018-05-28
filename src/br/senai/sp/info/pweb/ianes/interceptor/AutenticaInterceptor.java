package br.senai.sp.info.pweb.ianes.interceptor;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.pweb.ianes.models.TipoUsuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;



@Component
public class AutenticaInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean necessitaAutenticacao = request.getRequestURI().contains("/app");
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		boolean usuarioEstaAutenticado = usuarioAutenticado != null;
		boolean necessitaSerAdministrador = request.getRequestURI().contains("/adm");
	
		
		
		//CASO A P�GINA PRECISE DE AUTENTICA��O
		if(necessitaAutenticacao) {
			System.out.println("Necessita de autentica��o");
			//CASO O USU�RIO ESTEJA LOGADO
			if(usuarioEstaAutenticado) {	
				System.out.println("Tudo normal, continunado...");
				//CASO O USU�RIO PRECISE SER ADMINSTRADOR
				if(necessitaSerAdministrador && usuarioAutenticado.getTipo() != TipoUsuario.ADMINISTRADOR) {
				response.sendError(403);
				System.out.println("Precisa ser adm !");
					return false;
				}
				
			}
			//Caso o usu�rio n�o esteja autenticado
			else {
				response.sendError(401);
				return false;
	}
		
		}
		return true;

	
}
}

	

	

	

	
