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

import br.senai.sp.info.pweb.ianes.dao.ambienteDAO;
import br.senai.sp.info.pweb.ianes.models.Ambiente;
import br.senai.sp.info.pweb.ianes.models.Usuario;


@Controller
@RequestMapping("/app/")
public class AmbienteController {

	@Autowired
	private ambienteDAO ambienteDao;
	
	@GetMapping("/ambiente")
	public String abrirMenuAmbiente(@RequestParam(name = "id", required = false) Long id,
			Model model) {
		
		model.addAttribute("ambientes",ambienteDao.buscarTodos());
		
		return "ambiente/menu";
}
	@GetMapping("adm/ambiente/editar")
	public String abrirEdicao( @RequestParam(required = true) Long id, 
			HttpServletResponse response ,Model model) throws IOException {
		System.out.println(id);
		System.out.println(ambienteDao.buscar(id).getNome());
		model.addAttribute("ambiente", ambienteDao.buscar(id));
		
		return "ambiente/CadastroAmbiente";
	}
	
	
	@GetMapping("adm/ambiente/deletar")
	public String deletar(@RequestParam(required = true) Long id) {
		
		Ambiente ambiente = (Ambiente) ambienteDao.buscar(id);
		ambienteDao.deletar(ambiente);
		return "redirect:/app/adm/ambiente";
	}
	
	@GetMapping("adm/ambiente/novo")
	 String abrirFormNovoAmbiente(Model model) {
		
		model.addAttribute("ambiente", new Ambiente());
		
		return "ambiente/CadastroAmbiente";
	}

	@PostMapping("adm/ambiente/salvar")
	public String salvar (@Valid Ambiente ambiente, BindingResult brAmbiente,Model model) {
		
		if (ambiente.getId() == null) {
			if (ambienteDao.buscarPorNome(ambiente.getNome()) != null) {
				brAmbiente.addError(new FieldError("ambiente", "nome", "Esse ambiente já existe"));
			}

			// Realizando validações
			if (brAmbiente.hasErrors()) {
				System.out.println("Erro");
				System.out.println(brAmbiente);
				model.addAttribute("ambiente", ambiente);
				return "ambiente/CadastroAmbiente";
			}
		} else {
			if (brAmbiente.hasFieldErrors("nome")) {
				model.addAttribute("ambiente", ambiente);
				model.addAttribute("ambientes", ambienteDao.buscarTodos());
				return "ambiente/CadastroAmbiente";
			}
		}

		if (ambiente.getId() == null) {
			ambienteDao.persistir(ambiente);
		} else {
			Ambiente ambienteBanco = ambienteDao.buscar(ambiente.getId());
			/* setar as propriedades de um obj para outro, menos o id */
			BeanUtils.copyProperties(ambiente, ambienteBanco, "id");
			ambienteDao.alterar(ambienteBanco);
		}

		System.out.println("ambiente salvo!");
		return "redirect:/app/ambiente";
	}
}
