package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.models.TipoUsuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;
import br.senai.sp.info.pweb.ianes.utils.ProjetoStorage;



@Controller
public class UsuarioController {
	@Autowired
	private UsuarioDao usuarioDAO;
	@Autowired
	private ServletContext context;
	
	@Autowired
	private ProjetoStorage storage;
	
	@GetMapping(value = {"/", ""})
	public String abrirLogin(Model model) {
		System.out.println(context.getRealPath(""));
		return "index";
	}
	
	@GetMapping("/app/adm/usuario")
	public String abrirLista(Model model) {
		model.addAttribute("usuarios", usuarioDAO.buscarTodos());
		return "usuario/Lista";
	
	}
	
	@GetMapping("/app/adm/usuario/deletar")
	public String deletar(@RequestParam(required = true) Long id, HttpServletResponse response) throws IOException {
		
		Usuario usuarioADeletar = usuarioDAO.buscar(id);
		usuarioDAO.deletar(usuarioADeletar);
		
		return "redirect:/app/adm/usuario";
	}

	@GetMapping("/app/usuario/alterarSenha")
	public String alterarSenha(@Valid Usuario usuario, BindingResult brUsuario,
		//	@RequestParam(name = "confirmacaoSenha", required = true) String confirmarSenha,
			@RequestParam(name = "senhaAntiga", required = true) String senhaAntiga,
			HttpSession session) {

		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		
		Usuario usuarioAuxiliar = new Usuario();
		usuarioAuxiliar.setSenha(senhaAntiga);
		usuarioAuxiliar.hashearSenha();
		
		
		if(!usuarioAuxiliar.getSenha().equals(usuarioAutenticado.getSenha())) {
			brUsuario.addError(new FieldError("usuario", "senha", "A senha antiga não está correta"));
		}
		
		//if(!confirmarSenha.equals(usuario.getSenha())) {
		//	brUsuario.addError(new FieldError("usuario", "senha", "As senhas não coincidem"));
		//}
		
		if(brUsuario.hasErrors() || brUsuario.hasFieldErrors()) {
			return "usuario/alterar";
		}

		BeanUtils.copyProperties(usuarioAutenticado, usuario, "senha");
		usuario.hashearSenha();
		usuarioDAO.alterar(usuario);
		
		return "redirect:/app";
	}
	
	@GetMapping("/app/adm/usuario/editar")
	public String abrirEdicao( @RequestParam(required = true) Long id, 
			HttpServletResponse response ,Model model) throws IOException {
		System.out.println(id);
		System.out.println(usuarioDAO.buscar(id).getNome());
		model.addAttribute("usuario", usuarioDAO.buscar(id));
		
		return "usuario/CadastrarUsuario";
	
	}
	
	
	@PostMapping( value = {"/app/adm/usuario/salvar"})
	public String salvar(@Valid  Usuario usuario,  BindingResult brUsuario, @RequestParam(name="isAdministrador",required = false)Boolean ADM,
	  @RequestPart(name = "foto", required = false) MultipartFile foto) throws IOException{
		
		//Verificando se é CADASTRO
		if(usuario.getId() == null) {
			System.out.println("Teste");
			//Chegando se e-mail já está sendo utilizado
			if(usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
				
				brUsuario.addError(new FieldError("usuario", "email", "O e-mail selecionado já esta em uso"));
			}
			if(brUsuario.hasErrors()) {
				System.out.println("Erros");
				System.out.println(brUsuario);
				return "usuario/CadastrarUsuario";
		
					}
			
			
			
			//Verificando se o checkbox foi marcado através da checagem de valor nulo
			if(ADM != null) {
				System.out.println("É administrador: ");
				usuario.setTipo(TipoUsuario.ADMINISTRADOR);
			}else {
				System.out.println("não é administrador: ");
				usuario.setTipo(TipoUsuario.COMUM);
			}
			//CASO CADASTRO...
			usuario.hashearSenha();
			usuarioDAO.persistir(usuario);
		}
		
		//CASO ALTERAÇÃO
				else {
					//PEga o usuário do HIBERNATE através do id do form para poder alterá-lo
					Usuario usuarioBanco = usuarioDAO.buscar(usuario.getId());
					usuarioBanco.setNome(usuario.getNome());
					usuarioBanco.setSobrenome(usuario.getSobrenome());
					usuarioBanco.setTipo(usuario.getTipo());
					
					usuarioDAO.alterar(usuarioBanco);
				}
		//Logs
		System.out.println(storage);
		System.out.println(usuario);
		System.out.println(foto);
		
		//Tentando aramzenar a foto
		try {
			
	 storage.armazenarFotoDePerfil("foto_" + usuario.getId(),foto.getBytes());
	 System.out.println("passei");
		
	}catch (IOException e) {
		e.printStackTrace();
	}
	
	 return "Teste";
	}
	@GetMapping("/app/adm/usuario/novo")
	 String abrirFormNovoUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/CadastrarUsuario";
	}
			
		@PostMapping({"/usuario/autenticar"})
		public String autenticar(@Valid Usuario usuario, BindingResult brUsuario, HttpSession session) {

			usuario.hashearSenha();
			Usuario usuarioBuscado = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
			if(usuarioBuscado == null) {
				brUsuario.addError(new FieldError("usuario", "email", "O e-mail ou senha incorretos"));
			}
				
			if(brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
				System.out.println("Deu erro");
				System.out.println(brUsuario);
				return "index";
			}
				
			//Aplicando o usuário na sessão
			session.setAttribute("usuarioAutenticado", usuarioBuscado);
				
			return "redirect:/app/";
		}
		@GetMapping({"/app"})
		public String app() {
			return "Teste";
		}
		
		
			@GetMapping({"/sair"})
			public String logout(HttpSession session) {
				session.invalidate();
				return "redirect:/";
			}

		

	
	
	
}

