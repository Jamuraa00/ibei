package it.uniroma3.model;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Stateless(name="administratorFacade")
public class AdministratorFacade {
	
    @PersistenceContext(unitName = "unit-progetto")
    private EntityManager em;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private final String IBEIDATA_PATH = "C:/Users/jamur/Documents/IbeiData/";
    
	public Administrator getAdministrator(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
		ois = new ObjectInputStream(new FileInputStream(IBEIDATA_PATH+username));
		Administrator administrator = (Administrator) ois.readObject();
		ois.close();
		return administrator;
	}
	
}