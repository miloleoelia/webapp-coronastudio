package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoPaziente;
import model.SommarioPaziente;
import model.out.Misurazione;

@Controller
@RequestMapping("/pazienti")
public class Pazienti {
	
	@Autowired
    private DaoPaziente dao;
	
	@RequestMapping("/{cf}")
	public String dettaglioPaziente(@PathVariable("cf") String cf, Model m) throws Exception {
		
		SommarioPaziente sommario = new SommarioPaziente();
		sommario.setPaziente(
				dao.getPaziente(cf)
				);
		sommario.setMisurazioni(
				dao.getMisurazioni(cf)
				.stream()
				.sorted((Misurazione a, Misurazione b) -> {
					if(a.getData() == null || b.getData() == null)
						return 1;
					return a.getData().compareTo(b.getData());
				})
				.collect(Collectors.toList())
				);
		
		m.addAttribute("sommario", sommario);
		
		preparaGrafico(sommario, m);
		
		return "dettaglio-paziente";
	}

	private void preparaGrafico(SommarioPaziente sommario, Model m) throws Exception {
		List<Misurazione> misurazioniPerGrafico = sommario.getMisurazioni()
				.stream()
				.filter((Misurazione mis) -> mis.getData() != null)
				.sorted((Misurazione a, Misurazione b) -> {
					return a.getData().compareTo(b.getData());
				})
				.collect(Collectors.toList());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM hh:mm");
		List<String> labelsGrafico = misurazioniPerGrafico
				.stream()
				.map((Misurazione mis) -> {
					Date data = mis.getData();
					return df.format(data);
				})
				.collect(Collectors.toList());
				
		
		List<Double> valoriGrafico = misurazioniPerGrafico
				.stream()
				.mapToDouble((Misurazione mis) -> {
					return (double)mis.getTemperaturaCorporea();
				})
				.boxed()
				.collect(Collectors.toList());
		
		ObjectMapper om = new ObjectMapper();
		
		m.addAttribute("labelsGrafico", om.writeValueAsString(labelsGrafico));
		m.addAttribute("valoriGrafico", om.writeValueAsString(valoriGrafico));
	}

	

}
