package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dao.DaoPaziente;
import model.Misurazione;

@RestController
@RequestMapping(path = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, 
								produces = MediaType.APPLICATION_JSON_VALUE)
public class Api {
	
//	@PersistenceContext
//	private EntityManager em;
//	
	@Autowired
    private DaoPaziente dao;
	
	@GetMapping("/misurazioni/{cf}")
	public List<Misurazione> getMisurazioni(@PathVariable("cf") String cf) throws Exception {
		return dao.getMisurazioni(cf);
	}
	
	@PutMapping("/misurazioni")
	public ResponseEntity<?> creaMisurazione(@RequestBody Misurazione misurazione) throws Exception {
		
		dao.insertMisurazione(misurazione);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

}
