/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imlp;

import dao.ITaiKhoanDAO;
import entity.TaiKhoan;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author dmx
 */
public class TaiKhoanDAO extends AbstractDAO<TaiKhoan> implements ITaiKhoanDAO<TaiKhoan>,Serializable{

	public TaiKhoanDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
