package com.portifolio.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portifolio.web.models.Cadastra;
import com.portifolio.web.repository.CadastraRepository;

@Controller
public class CadastraController {	
		
	@RequestMapping(value="/cadastrar", method = RequestMethod.GET)
	public ModelAndView abrirIndex(){
		ModelAndView mv = new ModelAndView("cadastra");
		
		System.out.println("Passou aqui no mudança de pagina");
		return mv;
		
	}
	
	@Autowired 
	private CadastraRepository cr;

	// POST que cadastra empresa
		@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
		public String form(@Valid Cadastra cadastra, BindingResult result, RedirectAttributes attributes) {
		System.out.println("Passou aqui no cadastro");
			
			if (result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos");
				return "redirect:/cadastrar";
			}

			cr.save(cadastra);
			attributes.addFlashAttribute("mensagem", "Pessoa cadastrada com sucesso!");
			return "redirect:/cadastrar";
		}
		
		
		// GET que lista as empresa
				@RequestMapping("/lista-cadastro")
				public ModelAndView listaCadastro() {
					ModelAndView mv = new ModelAndView("cadastros/lista-cadastro");
					Iterable<Cadastra> cadastra = cr.findAll();
					mv.addObject("cadastra", cadastra);
					return mv;
				}
				
		// GET que mostra os detalhes da empresa
		@RequestMapping("/cadastra/{id}")
		public ModelAndView detalhesCadastro(@PathVariable("id") long id) {
			Optional<Cadastra> cadastra = Optional.ofNullable(cr.findById(id));
			ModelAndView mv = new ModelAndView("cadastros/lista-cadastro");
			mv.addObject("cadastra", cadastra);

			Optional<Cadastra> cadastra1 = Optional.ofNullable(cr.findById(id));
			mv.addObject("cadastra", cadastra1);

			return mv;

		}
		
		
		//GET que deleta empresa
		@RequestMapping("/deletarCadastro")
		public String deletarCadastro(long id) {
			cr.deleteById(id);
			return "redirect:/lista-cadastro";
			
		}
		
		
		
		// Métodos que atualizam empresa
		
		// GET que chama o FORM de edição da empresa
		// GET que chama o FORM de edição do funcionário
		@RequestMapping("/editar-cadastro")
		public ModelAndView editarCadastro(long id) {
			Cadastra cadastra = cr.findById(id);
			ModelAndView mv = new ModelAndView("cadastros/update-cadastro");
			mv.addObject("cadastra", cadastra);
			return mv;
		}	
		
						
		// POST do FORM que atualiza a vaga
		@RequestMapping(value = "/editar-cadastro", method = RequestMethod.POST)
		public String updateVaga(@Valid Cadastra cadastra, BindingResult result, RedirectAttributes attributes) {
			cr.save(cadastra);
			attributes.addFlashAttribute("success", "Vaga alterada com sucesso!");
			return "redirect:/lista-cadastro";
		}

		
		
		
				
	
}
