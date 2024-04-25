/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imlp;

import java.rmi.RemoteException;

import dao.IKhachHangDAO;
import entity.KhachHang;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Laptop
 */
public class KhachHangDAO extends AbstractDAO<KhachHang> implements IKhachHangDAO<KhachHang> {
    
    public KhachHangDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang findByPhoneNumber(String phoneNumber) throws RemoteException{
        TypedQuery<KhachHang> query = em.createNamedQuery("KhachHang.findByPhoneNumber", KhachHang.class);
        query.setParameter("sdt", phoneNumber);
        
        return !query.getResultList().isEmpty() ? query.getResultList().get(0) : null;
    }
    
}
