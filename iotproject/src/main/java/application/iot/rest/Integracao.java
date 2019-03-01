package application.iot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import application.iot.repository.GasRepo;

@RestController
public class Integracao {

	@Autowired
	private GasRepo gasRepo;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "verificarJanela", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> verificarJanela() {
		return new ResponseEntity<String>(new Gson().toJson(gasRepo.verificarJanela()), HttpStatus.OK);
	}
}
