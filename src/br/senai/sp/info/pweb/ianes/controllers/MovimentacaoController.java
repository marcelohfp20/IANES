package br.senai.sp.info.pweb.ianes.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.mail.imap.protocol.Item;

import br.senai.sp.info.pweb.ianes.dao.ItemDePatrimonioDAO;
import br.senai.sp.info.pweb.ianes.dao.MovimentacaoDAO;
import br.senai.sp.info.pweb.ianes.dao.ambienteDAO;
import br.senai.sp.info.pweb.ianes.models.ItemDePatrimonio;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Controller
@RequestMapping("/app")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	@Autowired
	private ambienteDAO ambienteDAO;
	
	@Autowired
	private ItemDePatrimonioDAO itemDePatrimonioDAO;
	
	@GetMapping({"/movimentacao"})
	public String abrirListaMovimentacoes(Model model, @RequestParam(required = true) Long idItem) {
		
		System.out.println(idItem);
		ItemDePatrimonio item = (ItemDePatrimonio) itemDePatrimonioDAO.buscar(idItem);
		model.addAttribute("item", item);
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		model.addAttribute("itens", itemDePatrimonioDAO.buscarTodos());
		model.addAttribute("movimentacoes", movimentacaoDAO.buscarPorItem(item));
		model.addAttribute("movimentacao", new Movimentacao());
		System.out.println("Marcelinho: " + idItem);
		return "movimentacao/menu";
	}
	
	@GetMapping({"/movimentacao/nova"})
	public String abrirFormMovimentacao(Model model, @RequestParam(name = "idItem" ,required = true) Long idItem) {
		ItemDePatrimonio item = (ItemDePatrimonio) itemDePatrimonioDAO.buscar(idItem);
		model.addAttribute("item", item);
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		model.addAttribute("itens", itemDePatrimonioDAO.buscarTodos());
		model.addAttribute("movimentacoes", movimentacaoDAO.buscarPorItem(item));
		model.addAttribute("movimentacao", new Movimentacao());
		System.out.println("Marcelinho: " + idItem);
		model.addAttribute("idItem",idItem);
		return "movimentacao/cadastro";
		
	}
	
	@PostMapping("/movimentacao/salvar")
	public String salvar(@Valid Movimentacao movimentacao, BindingResult brMovimentacao, Model model, 
			HttpSession session,@RequestParam (name="idItem",required=true) Long idItem) {
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		System.out.println("Marcelinho: " + idItem);

		if (brMovimentacao.hasErrors()) {
			System.out.println("ERRO");
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());
			model.addAttribute("itens", itemDePatrimonioDAO.buscarTodos());
			return "movimentacao/cadastro";
		}
		
		if (movimentacao.getId() == null) {
			if (brMovimentacao.hasErrors()) {
				model.addAttribute("movimentacao", movimentacao);
				return "movimentacao/cadastro";
			} else {

				//atualiza movimentação
				movimentacao.setData(new Date());
				movimentacao.setUsuario(autenticado);
				
				ItemDePatrimonio itemAMovimentar = (ItemDePatrimonio) itemDePatrimonioDAO.buscar(idItem);
				System.out.println(itemAMovimentar.getId());
				movimentacao.setItemPatrimonio(itemAMovimentar);

				movimentacao.setAmbienteOrigem(((ItemDePatrimonio) itemAMovimentar).getAmbiente());
				
				if (movimentacao.getAmbienteOrigem().equals(((ItemDePatrimonio) itemAMovimentar).getAmbiente())) {
					brMovimentacao.addError(new FieldError("movimentacao", "ambiente", "Ambientes semelhantes!"));
				} 					

				((ItemDePatrimonio) itemAMovimentar).setAmbiente(movimentacao.getAmbienteDestino());

				System.out.println("movimentacao: " + movimentacao.toString());
				
				itemDePatrimonioDAO.alterar(itemAMovimentar);
				movimentacaoDAO.persistir(movimentacao);
				return "redirect:/app/itemDePatrimonio";
				
			}			
		} else {
			throw new RuntimeException("Você não pode modificar este item!");
		}
		
	}
}
