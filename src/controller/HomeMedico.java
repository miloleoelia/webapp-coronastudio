package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DaoPaziente;
import model.SommarioPaziente;
import model.out.Misurazione;
import model.out.Paziente;

@Controller
public class HomeMedico {
	
	@Autowired
    private DaoPaziente dao;

	@RequestMapping("/")
	public String home(Model m) throws Exception {
		
		List<SommarioPaziente> sommarioPazienti = dao.getPazienti()
				.stream()
				.map((Paziente paziente) -> {
					SommarioPaziente res = new SommarioPaziente();
					
					res.setPaziente(paziente);
					
					return res;
				})
				.collect(Collectors.toList());
		
		for(SommarioPaziente s : sommarioPazienti) {
			s.setMisurazioni(
					dao.getMisurazioni(s.getPaziente().getCodiceFiscale())
					);
		}
		
		m.addAttribute("listaSommarioPazienti", sommarioPazienti);
		return "home-medico";
	}
	
}
