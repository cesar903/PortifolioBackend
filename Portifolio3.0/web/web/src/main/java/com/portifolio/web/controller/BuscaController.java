package com.portifolio.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portifolio.web.repository.CadastraRepository;
import com.portifolio.web.models.Cadastra;



@Controller
public class BuscaController {
	@Autowired
	private CadastraRepository cr;

	private Object String;
	
	//POST
	@RequestMapping(value = "/lista-cadastro", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("cadastros/lista-cadastro");
		String mensagem = "Resultados da busca por " + buscar;
		
		
		if(nome.equals("nomecadastro")) {
			mv.addObject("cadastra", cr.findByNomes(buscar));
			
		}
		
		mv.addObject("mensagem", mensagem);
		return mv;
	}


}
