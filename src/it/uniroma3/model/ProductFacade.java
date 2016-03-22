package it.uniroma3.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

@Stateless(name="productFacade")
public class ProductFacade {
	
    @PersistenceContext(unitName = "unit-progetto")
    private EntityManager em;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private final String IBEIDATA_PATH = "C:/Users/jamur/Documents/IbeiData/";
    
	public void addProduct(Product product) throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(IBEIDATA_PATH+product.getName());
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(product);
		oos.close();
		
		file = new File(IBEIDATA_PATH+"catalog");
		ois = new ObjectInputStream(new FileInputStream(file));
		LinkedList<Product> catalog = (LinkedList<Product>) ois.readObject();
		ois.close();
//		LinkedList<Product> catalog = new LinkedList<Product>();
		catalog.add(product);
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(catalog);
		oos.close();
	}
	
	public Product getProduct(String name) {
		try{
			ois = new ObjectInputStream(new FileInputStream(IBEIDATA_PATH+name));
			Product product = (Product) ois.readObject();
			ois.close();
			return product;
		}
		catch(Exception e){return null;}
	}
	
	public List<Product> getAllProducts() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Product> products = new LinkedList<Product>();
        ois = new ObjectInputStream(new FileInputStream(new File(IBEIDATA_PATH+"catalog")));
//        Product tempProduct;
//        try{
//        	tempProduct = (Product) ois.readObject();
//        }
//        catch (Exception e){tempProduct = null;}
//        while (tempProduct != null){
//        	products.add(tempProduct);
//        	try{
//        		tempProduct = (Product) ois.readObject();
//        	}
//        	catch(Exception e){tempProduct = null;}
//        }
        
        products = (LinkedList<Product>) ois.readObject();
        
		return products;
	}
	
	public List<Product> findByCategory(String category) throws FileNotFoundException, ClassNotFoundException, IOException{
		List<Product> products = new LinkedList<Product>();
		List<Product> result = new LinkedList<Product>();
        ois = new ObjectInputStream(new FileInputStream(IBEIDATA_PATH+"catalog"));
        products = (List<Product>) ois.readObject();
        for (Product temp : products)
        	if (temp.getCategory().equals(category))
        		result.add(temp);
        return result;
	}

	public void updateProduct(Product product) throws FileNotFoundException, IOException {
        new FileOutputStream(IBEIDATA_PATH+product.getName()).close();
		oos = new ObjectOutputStream(new FileOutputStream(IBEIDATA_PATH+product.getName()));
        oos.writeObject(product);
        oos.close();
	}
	
    private void deleteProduct(Product product) {
        File file = new File(IBEIDATA_PATH+product.getName());
        file.delete();
    }

	public void deleteProduct(String name) {
		File file = new File(IBEIDATA_PATH+name);
        file.delete();
	}

}