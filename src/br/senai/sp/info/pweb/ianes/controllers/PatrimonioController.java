package br.senai.sp.info.pweb.ianes.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.ianes.dao.CategoriaPatrimonioDao;
import br.senai.sp.info.pweb.ianes.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;
import br.senai.sp.info.pweb.ianes.models.TipoUsuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Controller
@RequestMapping("/app")
public class PatrimonioController {
	
	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	@Autowired
	private CategoriaPatrimonioDao categoriaDAO;
	
	@RequestMapping("/adm/patrimonio/novo")
	public String abrePaginaNovo(Model model) {
		
		model.addAttribute("patrimonio", new Patrimonio());
		model.addAttribute("categorias", categoriaDAO.buscarTodos());
		return "Patrimonio/CadastroPatrimonio";
		
	}

	
	@PostMapping("/patrimonio/salvar")
	public String salvar(@Valid Patrimonio patrimonio, BindingResult brPatrimonio, Model model, HttpSession session) {

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		System.out.println("usuario " + autenticado);
		if (autenticado.getTipo() != TipoUsuario.ADMINISTRADOR) {
			return "Patrimonio/menu";
		} else {
			if(brPatrimonio.hasErrors()) {
				System.out.println("BindingResult Patrimonio " + brPatrimonio);
				model.addAttribute("categorias", categoriaDAO.buscarTodos());
				return "Patrimonio/CadastroPatrimonio";
			}			
			
			if(patrimonio.getId() == null) {

				if(brPatrimonio.hasErrors()) {
					System.out.println("BindingResult Patrimonio => " + brPatrimonio);
					model.addAttribute("patrimonio", patrimonio);
					return "Patrimonio/CadastroPatrimonio";
					
				} else {
					patrimonio.setDataCadastro(new Date());
					patrimonio.setUsuario(autenticado);
					patrimonioDAO.persistir(patrimonio);
					
					return "redirect:/app/patrimonio";
				}				
			} else {
				Patrimonio patrimonioAlterado = (Patrimonio) patrimonioDAO.buscar(patrimonio.getId());
				
				if (autenticado.getTipo() != TipoUsuario.ADMINISTRADOR) {
					throw new RuntimeException("Você não pode modificar esse patrimônio!");
				}
				
				patrimonioAlterado.setNome(patrimonio.getNome());
				patrimonioAlterado.setCategoria(patrimonio.getCategoria());
				patrimonioDAO.alterar(patrimonioAlterado);
				return "redirect:/app/patrimonio";
			}	
		}
	}
	
	@GetMapping("/adm/patrimonio/deletar")
	public String deletar(@RequestParam(required=  true) Long id) {
		
		Patrimonio patrimonios = (Patrimonio) patrimonioDAO.buscar(id);
		patrimonioDAO.deletar(patrimonios);
		
		return "redirect:/app/patrimonio";
		
	}
	
	@GetMapping("/patrimonio")
	public String listarUsuarios(Model model) {
		
		model.addAttribute("patrimonios", patrimonioDAO.buscarTodos());
		return "Patrimonio/Menu";
		
	}
	
	@GetMapping("/adm/patrimonio/alterar")
	public String abreAlterar(@RequestParam(required = true) Long id, Model model) {
		
		model.addAttribute("patrimonio", patrimonioDAO.buscar(id));
		model.addAttribute("categorias", categoriaDAO.buscarTodos());
		return "Patrimonio/CadastroPatrimonio";
		
	}
}