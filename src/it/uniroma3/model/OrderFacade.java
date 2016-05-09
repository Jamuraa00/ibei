package it.uniroma3.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Stateless(name="orderFacade")
public class OrderFacade {
	
    @PersistenceContext(unitName = "unit-progetto")
    private EntityManager em;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private final String IBEIDATA_PATH = "C:/Users/jamur/Documents/IbeiData/";
    private final String seqNum = "seqNum";
    
	public void addOrder(Order order) throws FileNotFoundException, IOException {
		// Legge l'id più recente, lo scrive nell'ordine e ricrea il seq aggiornato +1
		File seqNumber = new File(IBEIDATA_PATH+seqNum);		
		ois = new ObjectInputStream(new FileInputStream(seqNumber));
		Long id = ois.readLong();
		order.setId(id);
		ois.close();
		seqNumber.delete();
		seqNumber = new File(IBEIDATA_PATH+seqNum);
		oos = new ObjectOutputStream(new FileOutputStream(seqNumber));
		id = id+1;
		oos.writeLong(id);
		oos.close();
		
		// Scrive il file dell'ordine
		File file = new File(IBEIDATA_PATH+order.getId());
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(order);
		oos.close();
		
		// Aggiunge l'id dell'ordine ai non evasi
		file = new File(IBEIDATA_PATH+"unevaded");
		List<Long> unevaded = new ArrayList<Long>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			unevaded = (ArrayList) ois.readObject();
		}
		catch (Exception e) {}
		unevaded.add(order.getId());
		
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(unevaded);
		oos.close();
		
		// Aggiunge l'id dell'ordine alla lista degli id degli ordini dell'utente
		file = new File(IBEIDATA_PATH+order.getCustomer().getUsername()+"_orders");
		List<Long> orderList = new ArrayList<Long>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			orderList = (ArrayList) ois.readObject();
		}
		catch (Exception e) {}
		orderList.add(order.getId());
		
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(orderList);
		oos.close();
	}
	
	public void removeOrder(Order order){
		File file = new File(IBEIDATA_PATH+order.getId());
		file.delete();
	}

	public void updateOrder(Order order) throws FileNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+order.getId());
		file.delete();
		file = new File(IBEIDATA_PATH+order.getId());
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(order);
		oos.close();
	}

	// Trova gli ordini di un utente
	public List<Order> getOrders(String username) throws ClassNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+username+"_orders");
		List<Order> orders = new LinkedList<Order>();
		ObjectInputStream ois2;
		Order tempOrder;
		List<Long> idList = new ArrayList<Long>();
		try{
			ois = new ObjectInputStream(new FileInputStream(file));
			idList = (ArrayList) ois.readObject();
		}
		catch (Exception e) { return null; }		
		
		for (Long tempId : idList) {
			ois2 = new ObjectInputStream(new FileInputStream(new File(IBEIDATA_PATH+tempId)));
			tempOrder = (Order) ois2.readObject();
			orders.add(tempOrder);
		}
		return orders;
	}
	
	public List<Order> getUnevadedOrders() throws FileNotFoundException, IOException, ClassNotFoundException{
		File file = new File(IBEIDATA_PATH+"unevaded");
		ois = new ObjectInputStream(new FileInputStream(file));
		ObjectInputStream ois2;
		List<Order> unevadedOrders = new LinkedList<Order>();
		Order tempOrder;
		List<Long> idList = new ArrayList<Long>();
		
		try{ idList = (ArrayList) ois.readObject(); }
		catch (Exception e) { return null; }
		
		for (Long tempId : idList) {
			ois2 = new ObjectInputStream(new FileInputStream(new File(IBEIDATA_PATH+tempId)));
			tempOrder = (Order) ois2.readObject();
			unevadedOrders.add(tempOrder);
		}
		return unevadedOrders;
	}
	
	public void evadeOrder(Order order) throws FileNotFoundException, IOException, ClassNotFoundException{
		ois = new ObjectInputStream(new FileInputStream(new File(IBEIDATA_PATH+"unevaded")));
		List<Long> idList = new ArrayList<Long>();
		try { idList = (ArrayList) ois.readObject(); }
		catch (Exception e) {}
		ois.close();
		
		idList.remove(order.getId());
		
		oos = new ObjectOutputStream(new FileOutputStream(new File(IBEIDATA_PATH+"unevaded")));
		oos.writeObject(idList);
		oos.close();
		
		order.setDataEvasione(new Date());
		updateOrder(order);
	}
	
	public Order getOrder(Long id) throws ClassNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+id);
		ois = new ObjectInputStream(new FileInputStream(file));
		Order tempOrder = (Order) ois.readObject();
		ois.close();
		return tempOrder;
	}
	
}