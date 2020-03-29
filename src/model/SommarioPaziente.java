package model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import model.out.Misurazione;
import model.out.Paziente;

public class SommarioPaziente {

	private Paziente paziente;
	
	private List<Misurazione> misurazioni;
	
	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	
	public Misurazione getUltimaMisurazione() {
		if(misurazioni == null)
			return null;
		
		Misurazione res = misurazioni.stream()
				.filter((Misurazione m) -> m.getData() != null)
				.max((Misurazione a, Misurazione b) -> a.getData().compareTo(b.getData()))
				.get();
		
		return res;
	}
	
	public float getTemperaturaMedia() {
		if(misurazioni == null)
			return 0;
		
		double media = misurazioni.stream()
				.mapToDouble((Misurazione m) -> {
					return m.getTemperaturaCorporea();
				})
				.average().getAsDouble();
		
		return (float)media;
	}
	
	public Date getDataUltimoAggiornamento() {
		if(misurazioni == null)
			return null;
		
		Optional<Date> data = misurazioni.stream()
				.filter((Misurazione m) -> m.getData() != null)
				.map((Misurazione m) -> m.getData())
				.max( (Date a, Date b) -> a.compareTo(b));
		
		if(data.isPresent()) {
			return data.get();
		}else {
			return null;
		}
	}
	public List<Misurazione> getMisurazioni() {
		return misurazioni;
	}
	public void setMisurazioni(List<Misurazione> misurazioni) {
		this.misurazioni = misurazioni;
	}
	
	
	
}
