package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
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

import br.senai.sp.info.pweb.ianes.dao.CategoriaPatrimonioDao;
import br.senai.sp.info.pweb.ianes.models.Ambiente;
import br.senai.sp.info.pweb.ianes.models.CategoriaPatrimonio;



@Controller
@RequestMapping("/app/")
public class CategoriaPatrimonioController {
	
	@Autowired
	private CategoriaPatrimonioDao categoriaPatrimonioDao;
	
	@GetMapping("/categoria")
	public String abrirMenuCategorias(@RequestParam(required = false) Long id,Model model) {
      model.addAttribute("categoriaPatrimonio",categoriaPatrimonioDao.buscarTodos());
		
		return "categoria/menu";
	}
	
	@GetMapping("adm/categoria/deletar")
	public String deletar(@RequestParam(name = "id", required = true) Long id) {
		
		categoriaPatrimonioDao.deletar(categoriaPatrimonioDao.buscar(id));
		return "redirect:/app/adm/categoria";
	}
	
	@GetMapping("adm/categoria/novo")
	 String abrirFormNovaCategoria(Model model) {
		
		model.addAttribute("categoriaPatrimonio", new CategoriaPatrimonio());
		
		return "categoria/CadastroCategoria";
	}
	
	@GetMapping("adm/categoria/editar")
	public String abrirEdicao( @RequestParam(required = true) Long id, 
			HttpServletResponse response ,Model model) throws IOException {
		System.out.println(id);
		System.out.println(categoriaPatrimonioDao.buscar(id).getNome());
		model.addAttribute("categoriaPatrimonio", categoriaPatrimonioDao.buscar(id));
		
		return "categoria/CadastroCategoria";
	}
	
	
	@PostMapping("adm/categoria/salvar")
	public String salvar(@Valid CategoriaPatrimonio categoriaPatrimonio, BindingResult brCategoriaPatrimonio,
						Model model) {		
		
		//Verificando se o nome já existe
		if(categoriaPatrimonioDao.buscarPorNome(categoriaPatrimonio.getNome()) != null) {
			brCategoriaPatrimonio.addError(new FieldError("categoriaPatrimonio", "nome", "O nome já existe"));
		}
		
		//Reaizando validações dos campos
		if(brCategoriaPatrimonio.hasErrors()) {
			//Passamos o objeto pelo Model pois o nome do modelAttribute no  menu.jsp é "categoria"
			model.addAttribute("categoriaPatrimonio", categoriaPatrimonio);
			model.addAttribute("categorias", categoriaPatrimonioDao.buscarTodos());
			return "categoria/menu";
		}
		
		///Persistindo no banco de dados
		if(categoriaPatrimonio.getId() == null) {
			categoriaPatrimonioDao.persistir(categoriaPatrimonio);
		}else {
			CategoriaPatrimonio categoriaBanco = categoriaPatrimonioDao.buscar(categoriaPatrimonio.getId());
			BeanUtils.copyProperties(categoriaPatrimonio, categoriaBanco, "id");
			categoriaPatrimonioDao.alterar(categoriaBanco);
		}
		
		
		//Redireciona para pagina de categorias
		return "redirect:/app/categoria";
	}
	

}
