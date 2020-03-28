package dao;

import java.util.List;


import model.Misurazione;

public interface DaoPaziente {
	
	public List<Misurazione> getMisurazioni(String codiceFiscale) throws Exception;
	public List<Misurazione> getAllMisurazioni() throws Exception;
	public void insertMisurazione(Misurazione misurazione) throws Exception;

}
