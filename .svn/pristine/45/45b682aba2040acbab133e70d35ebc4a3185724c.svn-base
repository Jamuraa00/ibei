package it.uniroma3.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


@Stateless(name="supplierFacade")
public class SupplierFacade {
	
	@PersistenceContext(unitName = "unit-progetto")
    private EntityManager em;
	
	public void addSupplier(Supplier supplier) {
		em.persist(supplier);
	}
	
	public Supplier getSupplier(Long id) {
		Supplier supplier = em.find(Supplier.class, id);
		return supplier;
	}
	
	public void updateSupplier(Supplier supplier) {
        em.merge(supplier);
	}
	
    private void deleteSupplier(Supplier supplier) {
        em.remove(supplier);
    }

    public void deleteSupplier(Long id) {
    	Supplier supplier = em.find(Supplier.class, id);
        deleteSupplier(supplier);
	}
    
    public List<Supplier> getAllSuppliers( ){
    	CriteriaQuery<Supplier> cq = em.getCriteriaBuilder().createQuery(Supplier.class);
        cq.select(cq.from(Supplier.class));
        List<Supplier> suppliers = em.createQuery(cq).getResultList();
		return suppliers;
    }
    
    public List<Supplier> getSuppliersByPIva(String pIva) {
		Query query = em.createQuery("SELECT s FROM Supplier s WHERE s.pIva = :pIva");
		query.setParameter("pIva", pIva);
		List<Supplier> result = query.getResultList();
		if (result.size() == 0)
			return null;
		return result;
	}
    
}
