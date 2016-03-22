package it.uniroma3.model;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@Stateless(name="customerFacade")
public class CustomerFacade {
	
    @PersistenceContext(unitName = "unit-progetto")
    private EntityManager em;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private final String IBEIDATA_PATH = "C:/Users/jamur/Documents/IbeiData/";
    
    public Customer addCustomer(Customer customer) throws FileNotFoundException, IOException{
    	File file = new File(IBEIDATA_PATH+customer.getUsername());
    	oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(customer);
		oos.close();
		
		File file2 = new File(IBEIDATA_PATH+customer.getUsername()+"_orders");
		oos = new ObjectOutputStream(new FileOutputStream(file2));
		oos.close();
		
    	return customer;
    }
    
	public Customer getCustomerByUsername(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
		try{
			ois = new ObjectInputStream(new FileInputStream(IBEIDATA_PATH+username));
			Customer customer = (Customer) ois.readObject();
			ois.close();
			
//			oos = new ObjectOutputStream(new FileOutputStream(IBEIDATA_PATH+"admin"));
//			Administrator admin = new Administrator("admin","admin","1234");
//			admin.setId(Long.parseLong("1234"));
//			admin.setUsername("admin");
//			admin.setPassword("admin");
//			oos.writeObject(admin);
//			oos.close();
			
			return customer;
		}
		catch(Exception e){return null;}
	}
	
	public Customer updateCustomer(Customer customer) throws FileNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+customer.getUsername());
		file.delete();
		File newFile = new File(IBEIDATA_PATH+customer.getUsername());
    	oos = new ObjectOutputStream(new FileOutputStream(newFile));
		oos.writeObject(customer);
		oos.close();
    	return customer;
	}
	
	public Customer getCustomerById(Long id){
		return null;
	}
	
}