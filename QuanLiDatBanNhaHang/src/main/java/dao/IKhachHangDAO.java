/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.KhachHang;
import entity.NhanVien;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Laptop
 * @param <T>
 */
public interface IKhachHangDAO<T> extends GenericDAO<T>,Remote {
    KhachHang findByPhoneNumber(String phoneNumber) throws RemoteException;
}
