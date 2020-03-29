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

import model.out.Misurazione;
import model.out.Paziente;

public class DaoPazienteImpl implements DaoPaziente {

	@Autowired
	private ServletContext context;

	public List<Paziente> getPazienti() throws Exception {
		List<Paziente> pazienti = new ArrayList<>();
		String pathDatiPazienti = context.getRealPath("/WEB-INF/DATI/PAZIENTI/");
		File dirDatiPazienti = new File(pathDatiPazienti);

		if (dirDatiPazienti.exists()) {

			ObjectMapper om = new ObjectMapper();

			for (String cf : dirDatiPazienti.list()) {
				String pathFilePaziente = pathDatiPazienti + cf + "/paziente.json";
				File filePaziente = new File(pathFilePaziente);
				if (filePaziente.exists()) {
					pazienti.add(om.readValue(filePaziente, Paziente.class));
				}
			}
		}

		return pazienti;
	}
	
	
	public Paziente getPaziente(String cf) throws Exception {
		Paziente res = null;
		String pathDatiPazienti = context.getRealPath("/WEB-INF/DATI/PAZIENTI/");
		File dirDatiPazienti = new File(pathDatiPazienti);

		if (dirDatiPazienti.exists()) {

			ObjectMapper om = new ObjectMapper();
			String pathFilePaziente = pathDatiPazienti + cf + "/paziente.json";
			File filePaziente = new File(pathFilePaziente);
			if (filePaziente.exists()) {
				res = om.readValue(filePaziente, Paziente.class);
			}
		}

		return res;
	}

	public List<Misurazione> getMisurazioni(String codiceFiscale) throws Exception {
		String pathFileMisurazioni = context
				.getRealPath("/WEB-INF/DATI/PAZIENTI/" + codiceFiscale + "/misurazioni.json");
		File fileMisurazioni = new File(pathFileMisurazioni);

		ObjectMapper om = new ObjectMapper();

		List<Misurazione> misurazioni = null;
		if (fileMisurazioni.exists()) {
			misurazioni = om.readValue(fileMisurazioni, new TypeReference<List<Misurazione>>() {
			});
		} else {
			misurazioni = new ArrayList<Misurazione>();
		}

		return misurazioni;
	}

	public void insertMisurazione(Misurazione misurazione)
			throws JsonGenerationException, JsonMappingException, IOException {
		String codiceFiscale = misurazione.getPaziente().getCodiceFiscale();
		String pathPazienti = context.getRealPath("/WEB-INF/DATI/PAZIENTI/" + codiceFiscale + "/");
		File dirDatiPazienti = new File(pathPazienti);

		if (!dirDatiPazienti.exists()) {
			dirDatiPazienti.mkdirs();
		}

		ObjectMapper om = new ObjectMapper();

		String pathFilePaziente = pathPazienti + "paziente.json";
		File filePaziente = new File(pathFilePaziente);

		if (!filePaziente.exists()) {
			om.writeValue(filePaziente, misurazione.getPaziente());
		}

		String pathFileMisurazioni = pathPazienti + "misurazioni.json";
		File fileMisurazioni = new File(pathFileMisurazioni);

		List<Misurazione> misurazioni = null;
		if (fileMisurazioni.exists()) {
			misurazioni = om.readValue(fileMisurazioni, new TypeReference<List<Misurazione>>() {
			});
		} else {
			misurazioni = new ArrayList<Misurazione>();
		}

		misurazioni.add(misurazione);

		om.writeValue(fileMisurazioni, misurazioni);
	}

}
