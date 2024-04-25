/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Laptop
 * @param <T>
 */
public interface GenericDAO<T> extends Remote{

    boolean insert(T t) throws RemoteException;

    boolean update(T t) throws RemoteException;

    <T> boolean delete(String id, Class<T> claxx) throws RemoteException;

    <T> T findById(String id, Class<T> t) throws RemoteException;

    <T> List<T> findAll(Class<T> t) throws RemoteException;
}
