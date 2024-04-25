/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imlp;

import dao.INhanVienDAO;
import entity.NhanVien;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author Laptop
 */
public class NhanVienDAO extends AbstractDAO<NhanVien> implements INhanVienDAO<NhanVien>,Serializable {

	public NhanVienDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
       
}
