/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imlp;

import dao.IChiTietHoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 *
 * @author dmx
 */
public class ChiTietHoaDonDAO extends AbstractDAO<ChiTietHoaDon> implements IChiTietHoaDonDAO<ChiTietHoaDon> {

    public ChiTietHoaDonDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double TotalFoodCurrency(HoaDon hoaDon) throws RemoteException{
        List<ChiTietHoaDon> details = getListByHoaDon(hoaDon);
        Double total = 0.0;
        for (int i = 0; i < details.size(); i++) {
            total += details.get(i).getSoLuong() * details.get(i).getMon().getGiaBan();
        }
        return total;
    }

    ;

    public List<ChiTietHoaDon> getListByHoaDon(HoaDon hoaDon) throws RemoteException{
        return em.createNamedQuery("ChiTietHoaDon.HoaDon", ChiTietHoaDon.class)
                .setParameter("hoaDon", hoaDon)
                .getResultList();
    }

    ;

    @Override // lấy danh sách số lượng tương ứng với món trong chi tiết hoá đơn
    public List<ChiTietHoaDon> getListBySoLuong(int soLuong) throws RemoteException{
        return em.createNamedQuery("ChiTietHoaDon.DS_SoLuong", ChiTietHoaDon.class)
                .setParameter("soLuong", soLuong)
                .getResultList();
    }

    @Override
    public boolean deleteChiTiet(ChiTietHoaDon t) throws RemoteException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
;

    

}
