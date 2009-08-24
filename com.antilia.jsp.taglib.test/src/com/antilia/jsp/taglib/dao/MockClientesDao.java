/**
 * 
 */
package com.antilia.jsp.taglib.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.antilia.common.dao.impl.ListQuerableUpdatableDao;
import com.antilia.jsp.taglib.beans.Persona;
import com.antilia.jsp.taglib.beans.Sexo;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class MockClientesDao extends ListQuerableUpdatableDao<Persona> implements IClientesDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static MockClientesDao instance;
	
	private static final String[] fnames = {
		"Anna", 
		"Maria",
		"Carla",
		"Carlotta",
		"Ulrike",		
		};
	
	private static final String[] mnames = {
		"Frank", 
		"Julius",
		"Michael",
		"Otto", 
		"Roco",
		"Ricard",
		"Roland",
		};
	
	private static final String[] lastnames = {
		"Amor",
		"Anorak",
		"Barreiro",
		"Borroto",
		"Filibustero", 
		"Dominguez",
		"Jubilatus",
		"Kunst",
		"Hunter",
		"Morales",
		"Martinez",
		"Orujo",
		"Pascual",
		"Perez",
		"Reinhart",
		"Ricard"
		};
	
	public static List<Persona> createPersonas() {
		List<Persona> clientes = new ArrayList<Persona>();
		for(String name: fnames) {
			for(String lastName: lastnames) {
				Persona persona = new Persona();
				persona.setNombre(name);				
				persona.setApellidos(lastName);
				persona.setFechaNacimiento(new Date());
				persona.setNif("CDFSA1");
				persona.setFechaNif(new Date());
				persona.setCodigoPostal("CDFSA1");
				persona.setDomicilio("Kenedy Allee 166");			
				persona.setSexo(Sexo.M);
				persona.setCodigoPostal("DFFD-211");
				persona.setLocalidad("Frankfurt Main");
				persona.setProfesion("Zapatero");
				persona.setEmail(name+"."+lastName+"@web.com");
				persona.setNacionalidad("Alemana");
				persona.setLugarNacimiento("Alemania");
				persona.setMumeroFiscal("XCSAS1111");
				persona.setTelefono("131323111");
				clientes.add(persona);
				
			}
		}
		
		for(String name: mnames) {
			for(String lastName: lastnames) {
				Persona persona = new Persona();
				persona.setNombre(name);
				persona.setApellidos(lastName);
				persona.setFechaNacimiento(new Date());
				persona.setNif("CDFSA1");				
				persona.setFechaNif(new Date());
				persona.setCodigoPostal("CDFSA1");
				persona.setDomicilio("Bertillon 166");	
				persona.setSexo(Sexo.M);
				persona.setCodigoPostal("DFFD-211");									
				persona.setLocalidad("Frankfurt Main");
				persona.setProfesion("Zapatero");
				persona.setEmail(name+"."+lastName+"@web.com");
				persona.setNacionalidad("Alemana");
				persona.setLugarNacimiento("Alemania");
				persona.setMumeroFiscal("XCSAS1111");
				persona.setTelefono("131323111");
				clientes.add(persona);
				
			}
		}
		return clientes;
	}
	
	public MockClientesDao() {
		super(createPersonas());
	}
	
	/**
	 * @return the instance
	 */
	public static MockClientesDao getInstance() {
		if(instance == null)
			instance = new MockClientesDao();
		return instance;
	}

}
