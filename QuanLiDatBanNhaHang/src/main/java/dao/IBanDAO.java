/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Ban;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Laptop
 */
public interface IBanDAO<T> extends GenericDAO<T>,Remote {

    boolean updateStateById(String id, Enum state) throws RemoteException;

    List<Ban> findByFloor(int floor) throws RemoteException;

    List<Ban> findTableByStateAndFloor(Enum state, int floor) throws RemoteException;

    List<Ban> findTableByState(Enum state) throws RemoteException;

    List<Object[]> groupByMaTang() throws RemoteException;
}
