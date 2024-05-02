/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imlp;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import dao.IChiTietHoaDonDAO;
import dao.IHoaDonDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import utils.Enum.LoaiTrangThaiHoaDon;

/**
 *
 * @author dmx
 */
public class HoaDonDAO extends AbstractDAO<HoaDon> implements IHoaDonDAO<HoaDon> {

    /**
     *
     */
    private static final long serialVersionUID = -7274837092975495869L;
    private LocalDateTime timeNow = LocalDateTime.now();
    private String month_format = String.format("%02d", timeNow.getMonthValue());
    private String date_format = String.format("%02d", timeNow.getDayOfMonth());
    private String hour_format = String.format("%02d", timeNow.getHour());
    private String generateTime = Integer.toString(timeNow.getYear()).substring(2, 4) + month_format + date_format + hour_format;
    private IChiTietHoaDonDAO chiTietHoaDonDAO = utils.AppUtils.CHITIETHOADONDAO;
    private DecimalFormat formatter = new DecimalFormat("###,###đ");

    public HoaDonDAO() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    public HoaDon findLast() throws RemoteException {
        return em.createNamedQuery("HoaDon.Last", HoaDon.class)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public String generateID() throws RemoteException {
        HoaDon hoaDon = findLast();

        if (hoaDon != null) {
            String lastID = hoaDon.getMaHoaDon();
            if (checkSameDateTime(lastID)) {//truong hop cung ngay cung gio
                String increase_xxx = lastID.substring(10, 13);
                int generate_xxx = Integer.parseInt(increase_xxx);
                generate_xxx++;
                return "HD" + generateTime + String.format("%03d", generate_xxx);
            } else {//truong hop khac gio
                return "HD" + generateTime + String.format("%03d", 1);
            }
        }
        return "HD" + generateTime + String.format("%03d", 1);
    }

    public boolean checkSameDateTime(String lastID) throws RemoteException {
        String regex = lastID.substring(2, 10);
        return regex.equalsIgnoreCase(generateTime);
    }

    public boolean insertHoaDon(HoaDon hoaDon) throws RemoteException {
        hoaDon.setMaHoaDon(generateID());
        return insert(hoaDon);
    }

    public List<HoaDon> findOnOrder() throws RemoteException {
        return em.createNamedQuery("HoaDon.OnOrdering", HoaDon.class)
                .setParameter("trangThai", LoaiTrangThaiHoaDon.CHUA_THANH_TOAN)
                .getResultList();
    }

    public List<HoaDon> findByState(Enum e) throws RemoteException {
        return em.createNamedQuery("HoaDon.OnOrdering", HoaDon.class)
                .setParameter("trangThai", e)
                .getResultList();
    }

    public boolean updateStateById(String id, Enum state) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query query = em.createNamedQuery("HoaDon.updateStateById");
            query.setParameter("maHoaDon", id);
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

    public boolean updateBanById(String id, Ban ban) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query query = em.createNamedQuery("HoaDon.updateBanById");
            query.setParameter("maHoaDon", id);
            query.setParameter("ban", ban);
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

    @Override
    public void createInvoice(String maHoaDon, double tienKhachTra, double tienThua) {
        PdfWriter pdfWriter = null;
        HoaDon hoaDon = null;
        try {
            hoaDon = findById(maHoaDon, HoaDon.class);
        } catch (RemoteException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String path = "D:\\Dowload\\invoice.pdf";
            pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            PdfFont font = PdfFontFactory.createFont("D:\\Dowload\\OpenSans-VariableFont_wdth,wght.ttf", PdfEncodings.IDENTITY_H);
            float pageWidth = pdfDocument.getDefaultPageSize().getWidth() - 70;
            document.setFont(font);

            document.add(new Paragraph("KTQD Gò vấp - 244 Lê Văn Thọ").setTextAlignment(TextAlignment.CENTER).setBold().setMargin(0));
            document.add(new Paragraph("246, Lê Văn Thọ, Phường 11, Q.Gò Vấp").setTextAlignment(TextAlignment.CENTER).setMargin(0));
            document.add(new Paragraph("Thành phố Hồ Chí Minh    Hotline: 0902 777 600").setTextAlignment(TextAlignment.CENTER).setMargin(0));
            document.add(new Image(ImageDataFactory.create("D:\\Dowload\\logo_2.png")).setHorizontalAlignment(HorizontalAlignment.CENTER));
            document.add(new Paragraph("PHIẾU HÓA ĐƠN").setTextAlignment(TextAlignment.CENTER).setBold().setMargin(0));
            document.add(new Paragraph("Số: " + hoaDon.getMaHoaDon()).setTextAlignment(TextAlignment.CENTER).setBold().setMargin(0));
//          ---Ngày---
            LocalDateTime inputDateTime = LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String formattedDateTime = inputDateTime.format(DateTimeFormatter.ofPattern("hh:mma"));
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime createDate = LocalDateTime.parse(hoaDon.getNgayLapHoaDon().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String formatCreateDate = createDate.format(DateTimeFormatter.ofPattern("hh:mma"));
            Paragraph paragraphDate = new Paragraph().setMargin(0);
            paragraphDate.add(new Text("Ngày: ").setBold());
            paragraphDate.add(new Text(formatterDate.format(hoaDon.getNgayLapHoaDon()) + " (" + formatCreateDate + "  " + formattedDateTime + ")"));
            document.add(paragraphDate);
//          ---Bàn---
            Paragraph paragraphTable = new Paragraph().setMargin(0);
            paragraphTable.add(new Text("Bàn: ").setBold());
            paragraphTable.add(new Text(hoaDon.getBan().getMaBan()));
            document.add(paragraphTable);
//          ---Nhân viên---
            Paragraph paragraphEmp = new Paragraph().setMargin(0);
            paragraphEmp.add(new Text("Nhân viên: ").setBold());
            paragraphEmp.add(new Text(hoaDon.getNhanVien().getHoTen()));
            document.add(paragraphEmp);
//          ------Table-----
            Table table = new Table(6);
            table.setWidth(pageWidth);
            table.setMarginBottom(4f);
            table.addCell(new Paragraph("#").setBold());
            table.addCell(new Paragraph("Tên món").setBold());
            table.addCell(new Paragraph("SL").setBold());
            table.addCell(new Paragraph("ĐG").setBold());
            table.addCell(new Paragraph("KM (%)").setBold());
            table.addCell(new Paragraph("TT").setBold());
            int stt = 1;
            for (Object object : chiTietHoaDonDAO.getListByHoaDon(hoaDon)) {
                ChiTietHoaDon chiTietHoaDon = (ChiTietHoaDon) object;
                table.addCell(stt++ + "");
                table.addCell(chiTietHoaDon.getMon().getTenMon());
                table.addCell(chiTietHoaDon.getSoLuong() + "");
                table.addCell(formatter.format(chiTietHoaDon.getMon().getGiaBan()) + "");
                table.addCell(" ");
                table.addCell(formatter.format(chiTietHoaDon.getSoLuong() * chiTietHoaDon.getMon().getGiaBan()) + "");
            }
            document.add(table);
//          -----Tổng thanh toán----
            document.add(createCost(new Paragraph("Tổng thanh toán").setBold(), formatter.format(chiTietHoaDonDAO.TotalFoodCurrency(hoaDon)), pageWidth));
//          -----Còn phải thu----
            document.add(createCost(new Paragraph("Còn phải thu").setBold(), formatter.format(tienKhachTra - tienThua), pageWidth));
//          -----Tiền Khách trả----
            document.add(createCost(new Paragraph("Tiền khách trả").setBold(), formatter.format(tienKhachTra), pageWidth));
//          -----Tiền thừa----
            document.add(createCost(new Paragraph("Tiền thừa").setBold(), formatter.format(tienThua), pageWidth));

            document.add(new Paragraph("Quý khách vui lòng kiểm tra kỹ lại nội dung trước khi thanh toán! Trân trọng cảm ơn!").setTextAlignment(TextAlignment.CENTER).setBold());
            document.add(new Paragraph("HẸN GẶP LẠI QUÝ KHÁCH").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(20f));
            document.add(new Paragraph("Một sản phẩm của Team The Chosen Ones - NDK").setTextAlignment(TextAlignment.CENTER));
            document.close();

            SwingController controller = new SwingController();
            SwingViewBuilder factory = new SwingViewBuilder(controller);
            JPanel viewerComponentPanel = factory.buildViewerPanel();
            controller.getDocumentViewController().setAnnotationCallback(new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
            JFrame frame = new JFrame("PDF Viewer");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setExtendedState(MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            controller.openDocument(path);
            frame.add(viewerComponentPanel);
            frame.setVisible(true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PdfWriter
            if (pdfWriter != null) {
                try {
                    pdfWriter.close();
                    Files.deleteIfExists(Paths.get("D:\\Dowload\\invoice.pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Table createCost(IBlockElement label, String content, float width) {
        Table table = new Table(2);
        table.setWidth(width);
        table.setBorderLeft(Border.NO_BORDER);
        table.setBorderRight(Border.NO_BORDER);
        table.setBorderTop(Border.NO_BORDER);
        table.setBorderBottom(new SolidBorder(1f));
        Cell cell_1 = new Cell().add(label);
        Cell cell_2 = new Cell().add(new Paragraph(content).setTextAlignment(TextAlignment.RIGHT).setBold());
        cell_1.setBorder(Border.NO_BORDER);
        cell_2.setBorder(Border.NO_BORDER);
        table.addCell(cell_1);
        table.addCell(cell_2);
        table.setPaddingBottom(6f);

        return table;
    }

    @Override
    public List<HoaDon> findHoaDonTuNgayDenNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
        TypedQuery<HoaDon> query = em.createNamedQuery("HoaDon.findHoaDonTuNgayDenNgay", HoaDon.class);
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        return query.getResultList();
    }

    public int getTongHoaDon(NhanVien nv) throws RemoteException {
        int hd = 0;
        String ngay = generateTime.substring(0, 6);
        List<HoaDon> hoaDons = findAll(HoaDon.class);
        for (HoaDon h : hoaDons) {
            if (nv.getMaNV().equals(h.getNhanVien().getMaNV())) {
                if (ngay.equals(h.getMaHoaDon().substring(2, 8))) {
                    hd += 1;
                }
            }
        }
        return hd;
    }

    @Override
    public double getTongDoanhThu(NhanVien nv) throws RemoteException {
        double sum = 0.0;
        String day = generateTime.substring(0, 6);
        IChiTietHoaDonDAO tien = utils.AppUtils.CHITIETHOADONDAO;
        List<HoaDon> hd = findAll(HoaDon.class);
        for (HoaDon hd_total : hd) {
            if (nv.getMaNV().equals(hd_total.getNhanVien().getMaNV())) {
                if (day.equals(hd_total.getMaHoaDon().substring(2, 8))) {
                    sum += tien.TotalFoodCurrency(hd_total);
                }
//                Chưa áp mã khuyễn mãi và VAT
            }
        }
        return sum;
    }

    public DecimalFormat getFormatter() throws RemoteException {
        return formatter;
    }

    public List<HoaDon> findHoaDonTheoNgay(LocalDateTime ngay) throws RemoteException {
        List<HoaDon> list = findAll(HoaDon.class);
        List<HoaDon> listHoaDonTheoNgay = new ArrayList<>();
        String month_format = String.format("%02d", ngay.getMonthValue());
        String date_format = String.format("%02d", ngay.getDayOfMonth());
        String ngayString = Integer.toString(ngay.getYear()).substring(2, 4) + month_format + date_format;
        for (int i = 0; i < list.size(); i++) {
            String ngay_hoadon = list.get(i).getMaHoaDon().substring(2, 8);
            if (ngayString.equals(ngay_hoadon)) {
                listHoaDonTheoNgay.add(list.get(i));
            }
        }
        System.out.println(listHoaDonTheoNgay.size());
        return listHoaDonTheoNgay;
    }

    ;
    @Override
    public int getTongHoaDonTheoNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
        int soLuongHoaDon = 0;
        List<HoaDon> list = findHoaDonTuNgayDenNgay(ngayBatDau, ngayKetThuc);
        for (int i = 0; i < list.size(); i++) {
            soLuongHoaDon += 1;
        }
        return soLuongHoaDon;
    }

    @Override
    public double getTongTienHoaDonTheoNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
        Double total = 0.0;
        IChiTietHoaDonDAO dao = utils.AppUtils.CHITIETHOADONDAO;
        List<HoaDon> list = findHoaDonTuNgayDenNgay(ngayBatDau, ngayKetThuc);
        for (int i = 0; i < list.size(); i++) {
            try {
                total += dao.TotalFoodCurrency(list.get(i));
            } catch (RemoteException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }

    @Override
    public int getTongSoLuongMonTheoNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
        int soLuongMon = 0;
        List<HoaDon> list1 = findHoaDonTuNgayDenNgay(ngayBatDau, ngayKetThuc);
        IChiTietHoaDonDAO dao = utils.AppUtils.CHITIETHOADONDAO;
        for (int i = 0; i < list1.size(); i++) {
            List<ChiTietHoaDon> list2 = new ArrayList<>();
            try {
                list2 = dao.getListByHoaDon(list1.get(i));
            } catch (RemoteException ex) {
                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int j = 0; j < list2.size(); j++) {
                soLuongMon += list2.get(j).getSoLuong();
            }
        }
        return soLuongMon;
    }

    @Override
    public List<HoaDon> filterByDate(LocalDate date) {
        TypedQuery<HoaDon> query = em.createNamedQuery("HoaDon.filterByDate", HoaDon.class);
        query.setParameter("date", date);

        return query.getResultList();
    }

}
