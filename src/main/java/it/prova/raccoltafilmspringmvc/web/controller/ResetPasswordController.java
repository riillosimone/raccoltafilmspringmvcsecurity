package it.prova.raccoltafilmspringmvc.web.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.raccoltafilmspringmvc.dto.ResetPasswordDTO;
import it.prova.raccoltafilmspringmvc.exception.PasswordDontMatchException;
import it.prova.raccoltafilmspringmvc.service.UtenteService;
import it.prova.raccoltafilmspringmvc.validation.ValidationWithPassword;

@Controller
@RequestMapping("/resetpassword")
public class ResetPasswordController {

	@Autowired
	private UtenteService utenteService;

	@GetMapping(value = "/prepareresetpassword")
	public String resetPassword(Model model) {

		
		
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			model.addAttribute("reset_password_attr", new ResetPasswordDTO());
			return "resetpassword";
		}
		return "index";
	}

	@PostMapping(value = "/executeresetpassword")
	public String resetPassword(
			@Validated(ValidationWithPassword.class) @ModelAttribute("reset_password_attr") ResetPasswordDTO passwordDTO,
			BindingResult result, RedirectAttributes redirect) {

		if (StringUtils.isNoneBlank(passwordDTO.getNuovaPassword())
				&& !passwordDTO.getNuovaPassword().equals(passwordDTO.getConfermaPassword())) {
			result.rejectValue("confermaPassword", "password.diverse");
		}

		if (result.hasErrors())
			return "resetpassword";

		
		
		try {

			utenteService.resetPass(passwordDTO.getVecchiaPassword(), passwordDTO.getNuovaPassword());

		} catch (PasswordDontMatchException e) {
			result.rejectValue("vecchiaPassword", "vecchiapassword.sbagliata");
			return "resetpassword";
		} catch (RuntimeException e) {
			redirect.addFlashAttribute("infoMessage", "Attenzione! Utente non trovato.");
			return "redirect:/executeLogout";
		}

		return "redirect:/executeLogout";
	}
}
