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
		
		File file = new File(IBEIDATA_PATH+order.getId());
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(order);
		oos.close();
		file = new File(IBEIDATA_PATH+"unevaded");
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(order);
		oos.close();
		
		file = new File(IBEIDATA_PATH+order.getCustomer().getUsername()+"_orders");
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeLong(order.getId());
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

	public List<Order> getOrders(String username) throws ClassNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+username+"_orders");
		ois = new ObjectInputStream(new FileInputStream(file));
		ObjectInputStream ois2;
		List<Order> orders = new LinkedList<Order>();
		Order tempOrder;
		Long tempId;
		try{
			tempId = ois.readLong();
		}catch(Exception e){tempId = null;}
		while (tempId != null){
			ois2 = new ObjectInputStream(new FileInputStream(new File(IBEIDATA_PATH+tempId)));
			tempOrder = (Order) ois2.readObject();
			orders.add(tempOrder);
			try{
				tempId = ois.readLong();
			}catch(Exception e){tempId = null;}
		}
		return orders;
	}
	
	public List<Order> getUnevadedOrders() throws FileNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+"unevaded");
		ois = new ObjectInputStream(new FileInputStream(file));
		List<Order> unevadedOrders = new LinkedList<Order>();
		Order tempOrder;
		try{
			tempOrder = (Order) ois.readObject();
		}catch(Exception e){tempOrder = null;}
		while (tempOrder != null){
			unevadedOrders.add(tempOrder);
			try{
				tempOrder = (Order) ois.readObject();
			}catch (Exception e){tempOrder = null;}
		}
		return unevadedOrders;
	}
	
	public void evadeOrder(Order order) throws FileNotFoundException, IOException{
		List<Order> unevaded = getUnevadedOrders();
		unevaded.remove(order);
		order.setDataEvasione(new Date());
		updateOrder(order);
		File file = new File(IBEIDATA_PATH+"unevaded");
		file.delete();
		File file2 = new File(IBEIDATA_PATH+"unevaded");
		oos = new ObjectOutputStream(new FileOutputStream(file2));
		for (Order tempOrder: unevaded){
			if (!tempOrder.isSame(order))
				oos.writeObject(tempOrder);
		}
		oos.close();
	}
	
	public Order getOrder(Long id) throws ClassNotFoundException, IOException{
		File file = new File(IBEIDATA_PATH+id);
		ois = new ObjectInputStream(new FileInputStream(file));
		Order tempOrder = (Order) ois.readObject();
		ois.close();
		return tempOrder;
	}
	
}