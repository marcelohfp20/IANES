package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
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

import br.senai.sp.info.pweb.ianes.dao.ItemDePatrimonioDAO;
import br.senai.sp.info.pweb.ianes.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.dao.ambienteDAO;
import br.senai.sp.info.pweb.ianes.models.Ambiente;
import br.senai.sp.info.pweb.ianes.models.ItemDePatrimonio;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;
import br.senai.sp.info.pweb.ianes.models.TipoUsuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Controller
@RequestMapping("/app")
public class ItemDePatrimonioController {

	@Autowired
	private ItemDePatrimonioDAO itemDePatrimonioDAO;

	@Autowired
	private ambienteDAO ambienteDAO;

	@Autowired
	private PatrimonioDAO PatrimonioDAO;

	@RequestMapping("/itemDePatrimonio/novo")
	public String abrePaginaNovo(Model model) {
		
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		model.addAttribute("patrimonios", PatrimonioDAO.buscarTodos());
		model.addAttribute("itemDePatrimonio",new ItemDePatrimonio());
		return "Item/cadastro";
	}

	@PostMapping("/itemDePatrimonio/salvar")
	public String salvar(@Valid ItemDePatrimonio itemDePatrimonio, BindingResult brItem, Model model,
			HttpSession session) {

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		System.out.println("usuario " + autenticado);

			if (brItem.hasErrors()) {
				System.out.println("BindingResult ItemDePatrimonio " + brItem);
				model.addAttribute("ambientes", ambienteDAO.buscarTodos());
				model.addAttribute("patrimonios", PatrimonioDAO.buscarTodos());
				return "Item/cadastro";
			}

			if (itemDePatrimonio.getId() == null) {

				if (brItem.hasErrors()) {
					System.out.println("BindingResult ItemDePatrimonio => " + brItem);
					model.addAttribute("ItemDePatrimonio", itemDePatrimonio);
					return "item/cadastro";

				} else {
					itemDePatrimonio.setUsuario(autenticado);
					itemDePatrimonioDAO.persistir(itemDePatrimonio);

					return "redirect:/app/itemDePatrimonio";
				}
			}
			return "Item/menu";
			
		}
	

	@GetMapping("/adm/itemDePatrimonio/deletar")
	public String deletar(@RequestParam(required = true) Long id) {
		ItemDePatrimonio itemDePatrimonio = (ItemDePatrimonio) itemDePatrimonioDAO.buscar(id);
		itemDePatrimonioDAO.deletar(itemDePatrimonio);

		return "redirect:/app/itemDePatrimonio";
	}

	@GetMapping("/itemDePatrimonio")
	public String listarItem(Model model) {
		model.addAttribute("itemDePatrimonio", itemDePatrimonioDAO.buscarTodos());
		return "Item/menu";

	}


}
