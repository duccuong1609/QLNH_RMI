/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Mon;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Laptop
 */
public interface IMonDAO<T> extends GenericDAO<T>,Remote{
    List<Mon> findService() throws RemoteException;
    Map<Mon,Long> findPopular() throws RemoteException;
    List<Mon> findOthers() throws RemoteException;
}
