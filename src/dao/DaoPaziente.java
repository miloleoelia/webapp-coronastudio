package dao;

import java.util.List;

import model.out.Misurazione;
import model.out.Paziente;

public interface DaoPaziente {
	
	public List<Misurazione> getMisurazioni(String codiceFiscale) throws Exception;
	public List<Paziente> getPazienti() throws Exception;
	public Paziente getPaziente(String cf) throws Exception;
	public void insertMisurazione(Misurazione misurazione) throws Exception;

}
