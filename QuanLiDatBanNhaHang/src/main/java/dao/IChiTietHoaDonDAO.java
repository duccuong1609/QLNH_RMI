/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author dmx
 */
public interface IChiTietHoaDonDAO<T> extends GenericDAO<T>,Remote {

    Double TotalFoodCurrency(HoaDon hoaDon) throws RemoteException;

    List<ChiTietHoaDon> getListByHoaDon(HoaDon hoaDon) throws RemoteException;

    List<ChiTietHoaDon> getListBySoLuong(int soLuong) throws RemoteException;

    boolean deleteChiTiet(ChiTietHoaDon t) throws RemoteException;
}
