package it.prova.raccoltafilmspringmvc.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.raccoltafilmspringmvc.dto.RegistaDTO;
import it.prova.raccoltafilmspringmvc.exception.RegistaConFilmException;
import it.prova.raccoltafilmspringmvc.model.Regista;
import it.prova.raccoltafilmspringmvc.service.RegistaService;

@Controller
@RequestMapping(value = "/regista")
public class RegistaController {

	@Autowired
	private RegistaService registaService;
	

	@GetMapping
	public ModelAndView listAllRegisti() {
		ModelAndView mv = new ModelAndView();
		List<Regista> registi = registaService.listAllElements();
		// trasformiamo in DTO
		mv.addObject("registi_list_attribute", RegistaDTO.createRegistaDTOListFromModelList(registi));
		mv.setViewName("regista/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createRegista(Model model) {
		model.addAttribute("insert_regista_attr", new RegistaDTO());
		return "regista/insert";
	}

	@PostMapping("/save")
	public String saveRegista(@Valid @ModelAttribute("insert_regista_attr") RegistaDTO registaDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "regista/insert";
		}
		registaService.inserisciNuovo(registaDTO.buildRegistaModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/regista";
	}

	@GetMapping("/search")
	public String searchRegista() {
		return "regista/search";
	}

	@PostMapping("/list")
	public String listRegisti(RegistaDTO registaExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Regista> registi = registaService
				.findByExampleWithPagination(registaExample.buildRegistaModel(), pageNo, pageSize, sortBy).getContent();

		model.addAttribute("registi_list_attribute", RegistaDTO.createRegistaDTOListFromModelList(registi));
		return "regista/list";
	}

	@GetMapping("/edit/{idRegista}")
	public String editRegista(@PathVariable(required = true) Long idRegista, Model model) {
		model.addAttribute("edit_regista_attr",
				RegistaDTO.buildRegistaDTOFromModel(registaService.caricaSingoloElemento(idRegista)));
		return "regista/edit";
	}

	@PostMapping("/update")
	public String updateRegista(@Valid @ModelAttribute("edit_regista_attr") RegistaDTO registaDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "regista/edit";
		}
		registaService.aggiorna(registaDTO.buildRegistaModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/regista";
	}

	@GetMapping("/show/{idRegista}")
	public String show(@PathVariable(required = true) Long idRegista, Model model) {
		model.addAttribute("show_regista_attr",
				RegistaDTO.buildRegistaDTOFromModel(registaService.caricaSingoloElemento(idRegista)));
		return "regista/show";
	}

	@GetMapping("/delete/{idRegista}")
	public String delete(@PathVariable(required = true) Long idRegista, Model model) {
		model.addAttribute("delete_regista_attr",
				RegistaDTO.buildRegistaDTOFromModel(registaService.caricaSingoloElemento(idRegista)));
		return "regista/delete";
	}

	@PostMapping("/delete")
	public String delete(Long idRegista, Model model,RedirectAttributes redirectAttrs) {
		
		try {
			registaService.rimuovi(idRegista);
		} catch (RegistaConFilmException e) {
			model.addAttribute("errorMessage","Attenzione! Il regista che vuoi eliminare ha una lista di film associati.");
			model.addAttribute("delete_regista_attr",
					RegistaDTO.buildRegistaDTOFromModel(registaService.caricaSingoloElemento(idRegista)));
			return "regista/delete";
		}
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/regista";
	}

//	@GetMapping("/listafilm/{idRegista}")
//	public String listaFilm (Long idRegista, Model model) {
//		List <Film> filmDelRegista = filmService.listaFilmDiUnRegista(idRegista);
//		model.addAttribute("film_list_attribute", FilmDTO.createFilmDTOListFromModelList(filmDelRegista, true));
//		return "regista/listafilm";
//	}
	
//	@PostMapping("/deleteall")
//	public String eliminaTuttiIfilm(Long idRegista, RedirectAttributes redirectAttrs) {
//		//chi devo richiamare????
//	}
	
	
	@GetMapping(value = "/searchRegistiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchRegista(@RequestParam String term) {

		List<Regista> listaRegistaByTerm = registaService.cercaByCognomeENomeILike(term);
		return buildJsonResponse(listaRegistaByTerm);
	}
	

	private String buildJsonResponse(List<Regista> listaRegisti) {
		JsonArray ja = new JsonArray();

		for (Regista registaItem : listaRegisti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", registaItem.getId());
			jo.addProperty("label", registaItem.getNome() + " " + registaItem.getCognome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}