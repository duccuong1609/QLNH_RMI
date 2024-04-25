/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imlp;

import dao.IBanDAO;
import entity.Ban;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Laptop
 */
public class BanDAO extends AbstractDAO<Ban> implements IBanDAO<Ban> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3777492875958785162L;

	public BanDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Ban> findByFloor(int floor) throws RemoteException {
        TypedQuery<Ban> query = em.createNamedQuery("Ban.findByFloor", Ban.class);
        query.setParameter("maTang", floor);
        return query.getResultList();
    }

    public List<Object[]> groupByMaTang() throws RemoteException {
        return em.createNamedQuery("Ban.groupByMaTang", Object[].class).getResultList();
    }

    public List<Ban> findTableByStateAndFloor(Enum state, int floor) throws RemoteException {
        TypedQuery<Ban> query = em.createNamedQuery("Ban.findTableByStateAndFloor", Ban.class);
        query.setParameter("trangThai", state);
        query.setParameter("maTang", floor);
        return query.getResultList();
    }

    public List<Ban> findTableByState(Enum state) throws RemoteException{
        TypedQuery<Ban> query = em.createNamedQuery("Ban.findTableByState", Ban.class);
        query.setParameter("trangThai", state);
        return query.getResultList();
    }

    public boolean updateStateById(String id, Enum state) throws RemoteException{
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query query = em.createNamedQuery("Ban.updateStateById");
            query.setParameter("maBan", id);
            query.setParameter("trangThai", state);
            int rowsUpdated = query.executeUpdate();
            transaction.commit();
            return rowsUpdated > 0;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}
