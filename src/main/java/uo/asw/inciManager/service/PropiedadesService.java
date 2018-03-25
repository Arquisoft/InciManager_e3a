package uo.asw.inciManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Categoria;
import uo.asw.dbManagement.model.Propiedad;
import uo.asw.inciManager.repository.CategoriaRepository;
import uo.asw.inciManager.repository.PropiedadRepository;

@Service
public class PropiedadesService {

	@Autowired
	private PropiedadRepository p;

	public void addPropiedad(Propiedad p1) {
		p.save(p1);
	}
	
	
}
