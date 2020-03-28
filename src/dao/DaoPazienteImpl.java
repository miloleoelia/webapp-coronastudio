package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Misurazione;

public class DaoPazienteImpl implements DaoPaziente {
	
	@Autowired
    private ServletContext context;
	
	public List<Misurazione> getAllMisurazioni() throws Exception {
		String pathMisurazioni = context.getRealPath("/WEB-INF/DATI/MISURAZIONI/");
		File dirMisurazioni = new File(pathMisurazioni);
		
		List<Misurazione> misurazioni = new ArrayList<>();
		
		for(String cf : dirMisurazioni.list()) {
			misurazioni.addAll(
					getMisurazioni(cf)
					);
		}
		
		return misurazioni;
	}

	public List<Misurazione> getMisurazioni(String codiceFiscale) throws Exception {
		String pathFilePaziente = context.getRealPath("/WEB-INF/DATI/MISURAZIONI/" + codiceFiscale + "/misurazioni.json");
		File fileMisurazioni = new File(pathFilePaziente);
		
		ObjectMapper om = new ObjectMapper();
		
		List<Misurazione> misurazioni = null;
		if(fileMisurazioni.exists()) {
			misurazioni = om.readValue(fileMisurazioni, new TypeReference<List<Misurazione>>(){});
		}else {
			misurazioni = new ArrayList<Misurazione>();
		}
		
		return misurazioni;
	}

	public void insertMisurazione(Misurazione misurazione) throws JsonGenerationException, JsonMappingException, IOException {
		String codiceFiscale = misurazione.getPaziente().getCodiceFiscale();
		String pathMisurazioni = context.getRealPath("/WEB-INF/DATI/MISURAZIONI/" + codiceFiscale + "/");
		File dirMisurazioni = new File(pathMisurazioni);
		
		if(!dirMisurazioni.exists()) {
			dirMisurazioni.mkdirs();
		}
		
		String pathFilePaziente = pathMisurazioni + "misurazioni.json";
		File fileMisurazioni = new File(pathFilePaziente);
		
		ObjectMapper om = new ObjectMapper();
		
		List<Misurazione> misurazioni = null;
		if(fileMisurazioni.exists()) {
			misurazioni = om.readValue(fileMisurazioni, new TypeReference<List<Misurazione>>(){});
		}else {
			misurazioni = new ArrayList<Misurazione>();
		}
		
		misurazioni.add(misurazione);
		
		om.writeValue(fileMisurazioni, misurazioni);
	}

}
