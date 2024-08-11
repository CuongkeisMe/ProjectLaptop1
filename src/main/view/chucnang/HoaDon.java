package main.view.chucnang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.HoaDonChiTiet;
import main.entity.HoaDonEntity;
import main.entity.ToanCuc;
import main.repository.HoaDonChiTietRepository;
import main.repository.HoaDonRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HoaDon extends javax.swing.JInternalFrame {

    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model1 = new DefaultTableModel();
    private DefaultTableModel model2 = new DefaultTableModel();
    private HoaDonRepository hdre = new HoaDonRepository();
    private int index;
    private int index2;
    int originalIdHoaDonChiTiet = ToanCuc.getIdHoaDonChiTiet();

    public HoaDon() {
        initComponents();
        this.cauhinhForm();
        model = (DefaultTableModel) tblHoaDon.getModel();
        model1 = (DefaultTableModel) tblHdct.getModel();
        model2 = (DefaultTableModel) tblHoaDon1.getModel();
        fillTable1();
        fillTable3();
        setForm0();
        txtNgayBatDau.setDateFormatString("dd-MM-yyyy");
        txtNgayKetThuc.setDateFormatString("dd-MM-yyyy");
    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

    }

    private void setForm0() {
        if (tblHoaDon.getRowCount() > 0) {
            index = 0;
            setForm();
            fillTable2();
            setSum();
            setForm2();
            loadSet();
        }
    }

    private void loadSet() {
        index = 0;
        setForm2();
    }

    private void setForm2() {
       if (model1 == null) {
           System.out.println("model1 is null");
           return;
       }
       if (index2 < 0 || index >= model1.getRowCount()) {
           return;
       }
       int columnCount = model1.getColumnCount();
       if (columnCount <= 0) {
           return;
       }
       DecimalFormatSymbols symbols = new DecimalFormatSymbols();
       symbols.setGroupingSeparator(',');
       symbols.setDecimalSeparator('.');
       DecimalFormat decimalFormat = new DecimalFormat("#,###.##", symbols);
       Object maHoaDon2 = model1.getValueAt(index, 0);
       txtMaHoaDon2.setText(maHoaDon2 != null ? maHoaDon2.toString() : "");
       Object tenSanPham = model1.getValueAt(index, 1);
       txtTenSanPham.setText(tenSanPham != null ? tenSanPham.toString() : "");
       Object soLuong = model1.getValueAt(index, 2);
       txtSoLuong.setText(soLuong != null ? soLuong.toString() : "");
       Object donGiaObj = model1.getValueAt(index, 3);
       if (donGiaObj != null) {
           try {
               double price = Double.parseDouble(donGiaObj.toString());
               txtDonGia.setText(decimalFormat.format(price));
           } catch (NumberFormatException e) {
               txtDonGia.setText(""); 
               e.printStackTrace(); 
           }
       } else {
           txtDonGia.setText(""); 
       }
    }


    private void setSum() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##", symbols);

        double sumCount = 0;
        double sumPrice = 0;
        int max = model.getRowCount();

        for (int i = 0; i < max; i++) {
            sumCount++; 
            sumPrice += Double.parseDouble(model.getValueAt(i, 7).toString());
        }
        txtCount.setText(String.valueOf(sumCount)); 
        txtPrice.setText(decimalFormat.format(sumPrice)); 
    }

    private void fillTable1() {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        model.setRowCount(0);
        list = hdre.getAll();
        for (HoaDonEntity hd : list) {
            Object[] rowData = hd.toTable();
            rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
            model.addRow(rowData);
        }
    }

    private void fillTable2() {
        String ma = txtMaHoaDon.getText();
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        model1.setRowCount(0);
        list = hdre.getHdct(ma);
        for (HoaDonChiTiet hdct : list) {
            model1.addRow(hdct.toTable2());
        }
    }

    private void fillTable3() {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        model2.setRowCount(0);
        list = hdre.getRemove();
        for (HoaDonEntity hd : list) {
            Object[] rowData = hd.toTable();
            rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
            model2.addRow(rowData);
        }
    }
    private void setForm() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##", symbols);
        txtMaHoaDon.setText(model.getValueAt(index, 0).toString());
        txtTenKh.setText(model.getValueAt(index, 1).toString());
        txtTenNv.setText(model.getValueAt(index, 3).toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            String dateString = model.getValueAt(index, 4).toString();
            Date date = dateFormat.parse(dateString);
            txtNgayThanhToan.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Float tongtien = Float.parseFloat(model.getValueAt(index, 5).toString());
        txtTongTien.setText(decimalFormat.format(tongtien));
        Float ThanhTien = Float.parseFloat(model.getValueAt(index, 7).toString());
        txtThanhTien.setText(decimalFormat.format(ThanhTien));
    }

    private void setForm1() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##", symbols);
        Object maHoaDon = model2.getValueAt(index, 0);
        txtMaHoaDon.setText(maHoaDon != null ? maHoaDon.toString() : "");
        Object tenKh = model2.getValueAt(index, 1);
        txtTenKh.setText(tenKh != null ? tenKh.toString() : "");
        Object tenNv = model2.getValueAt(index, 3);
        txtTenNv.setText(tenNv != null ? tenNv.toString() : "");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            String dateString = model2.getValueAt(index, 4).toString();
            if (dateString != null && !dateString.isEmpty()) {
                Date date = dateFormat.parse(dateString);
                txtNgayThanhToan.setDate(date);
            } else {
                txtNgayThanhToan.setDate(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object tongTienObj = model2.getValueAt(index, 5);
        if (tongTienObj != null) {
            try {
                Float tongtien = Float.parseFloat(tongTienObj.toString());
                txtTongTien.setText(decimalFormat.format(tongtien));
            } catch (NumberFormatException e) {
                txtTongTien.setText("");
            }
        } else {
            txtTongTien.setText("");
        }
        Object thanhTienObj = model2.getValueAt(index, 7);
        if (thanhTienObj != null) {
            try {
                Float thanhTien = Float.parseFloat(thanhTienObj.toString());
                txtThanhTien.setText(decimalFormat.format(thanhTien));
            } catch (NumberFormatException e) {
                txtThanhTien.setText("");
            }
        } else {
            txtThanhTien.setText("");
        }
    }

    private void clearForm() {
        txtMaHoaDon.setText("");
        txtTenKh.setText("");
        txtTenNv.setText("");
        txtNgayThanhToan.setDate(null);
        txtTongTien.setText("");
        txtThanhTien.setText("");
        txtMaHoaDon2.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        model1.setRowCount(0);
    }
    private void clearForm1(){
        txtMaHoaDon2.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
    }
    private boolean checkSearchText(String text) {
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa tìm văn bản cần tìm");
            return false;
        }
        if (isNumber(text)) {
            if (text.length() != 10) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số");
                return false;
            }
            if (!text.startsWith("0")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0");
                return false;
            }
            return true;
        } else {
            if (text.startsWith("HD") || text.startsWith("KH") || text.startsWith("NV")) {
                if (text.length() != 6) {
                    JOptionPane.showMessageDialog(null, "Sau HD, NV, KH phải có 4 số đằng sau!");
                    return false;
                }
                if (!isNumber(text.substring(2))) {
                    JOptionPane.showMessageDialog(null, "Sau HD, NV, KH phải là 4 chữ số!");
                    return false;
                }
                return true;
            }
            return true;
        }
    }

    private boolean isNumber(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void searchText(String text) {
        if (checkSearchText(text)) {
            ArrayList<HoaDonEntity> list = new ArrayList<>();
            model.setRowCount(0);
            list = hdre.TimKiem(text);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu!");
                return;
            } else {
                for (HoaDonEntity hd : list) {
                    Object[] rowData = hd.toTable();
                    rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                    model.addRow(rowData);
                }
            }
        } else {
            return;
        }
        setForm0();
        setSum();
    }

    private boolean checkDay(Date startDate, Date endDate) {
        if (startDate == null && endDate != null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Bắt Đầu");
            return false;
        } else if (startDate != null && endDate == null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Kết Thúc");
            return false;
        } else if (startDate == null && endDate == null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Bắt Đầu Và Ngày Kết Thúc");
            return false;
        }

        // Kiểm tra nếu ngày bắt đầu sau ngày kết thúc
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Không Hợp Lệ! Ngày Bắt Đầu Không Thể Sau Ngày Kết Thúc");
            return false;
        }

        return true;
    }

    private void searchDays(Date d1, Date d2) {
        if (checkDay(d1, d2)) {
            ArrayList<HoaDonEntity> list = new ArrayList<>();
            model.setRowCount(0);
            list = hdre.TimKiemTheoKhoang(d1, d2);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu!");
                return;
            } else {
                for (HoaDonEntity hd : list) {
                    Object[] rowData = hd.toTable();
                    rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                    model.addRow(rowData);
                }
            }
        } else {
            return;
        }
        setForm0();
        setSum();
    }

    private void searchCbos(String cbo) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        model.setRowCount(0);
        list = hdre.TimKiemTheoPhuongThuc(cbo);
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu!");
            return;
        } else {
            for (HoaDonEntity hd : list) {
                Object[] rowData = hd.toTable();
                rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                model.addRow(rowData);
            }
        }
        setForm0();
        setSum();
    }

    private void searchCboDay(Date d1, Date d2, String cbo) {
        if (checkDay(d1, d2)) {
            ArrayList<HoaDonEntity> list = new ArrayList<>();
            model.setRowCount(0);
            list = hdre.TimKiemTheoPhuongThucVaNgay(cbo, d1, d2);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu!");
                return;
            } else {
                for (HoaDonEntity hd : list) {
                    Object[] rowData = hd.toTable();
                    rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                    model.addRow(rowData);
                }
            }
        } else {
            return;
        }
        setForm0();
        setSum();
    }

    private void searchTextDay(Date d1, Date d2, String text) {
        if (checkDay(d1, d2) && checkSearchText(text)) {
            ArrayList<HoaDonEntity> list = new ArrayList<>();
            model.setRowCount(0);
            list = hdre.timKiemTheoVanBanNgay(text, d1, d2);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
                return;
            } else {
                for (HoaDonEntity hd : list) {
                    Object[] rowData = hd.toTable();
                    rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                    model.addRow(rowData);
                }
            }

        } else {
            return;
        }
        setForm0();
        setSum();
    }

    private void searchTextCbo(String text, String cbo) {
        if (checkSearchText(text)) {
            ArrayList<HoaDonEntity> list = new ArrayList<>();
            model.setRowCount(0);
            list = hdre.timKiemVanBanVaPhuongThuc(text, cbo);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
                return;
            } else {
                for (HoaDonEntity hd : list) {
                    Object[] rowData = hd.toTable();
                    rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                    model.addRow(rowData);
                }
            }
        } else {
            return;
        }
        setForm0();
        setSum();
    }

    private void searchAll(String text, String cbo, Date d1, Date d2) {
        if (checkDay(d1, d2) && checkSearchText(text)) {
            ArrayList<HoaDonEntity> list = new ArrayList<>();
            model.setRowCount(0);
            list = hdre.timKiemAll(text, cbo, d1, d2);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu!");
                return;
            } else {
                for (HoaDonEntity hd : list) {
                    Object[] rowData = hd.toTable();
                    rowData[10] = (Integer) rowData[10] == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
                    model.addRow(rowData);
                }
            }
        } else {
            return;
        }
        setForm0();
        setSum();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        txtTenNv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayThanhToan = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtVanBan = new javax.swing.JTextField();
        btnTimKiemTheoVanBan = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cboPt = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtMaHoaDon2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHdct = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtPrice = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtCount = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon1 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRANG CHỦ");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên Khách Hàng");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Tên Nhân Viên Tạo");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Mã Hoá Đơn");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Ngày Thanh Toán");

        txtNgayThanhToan.setDateFormatString("dd-MM-yyyy");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tổng Tiền");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/5452459_arrow_direction_door_emergency_exit_icon.png"))); // NOI18N
        jButton3.setText("Xuất");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/11211468_eraser_clear_remove_tool_rubber_icon.png"))); // NOI18N
        btnClear.setText("Làm Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm  Văn Bản"));

        txtVanBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVanBanFocusGained(evt);
            }
        });

        btnTimKiemTheoVanBan.setText("Tìm Kiếm");
        btnTimKiemTheoVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoVanBanActionPerformed(evt);
            }
        });

        jLabel18.setText("Ngày Từ");

        txtNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusGained(evt);
            }
        });

        jLabel19.setText("Đến");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiemTheoVanBan)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiemTheoVanBan)
                            .addComponent(txtVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm "));

        jLabel14.setText("Phương Thức Thanh Toán");

        cboPt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Chuyển Khoản", "Tiền mặt" }));
        cboPt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(159, 159, 159))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPt, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá Đơn Chi Tiết"));

        jLabel20.setText("Mã Hoá Đơn");

        jLabel21.setText("Tên Sản Phẩm");

        jLabel22.setText("Số Lượng");

        tblHdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hoá Đơn", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHdct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHdctMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHdct);
        if (tblHdct.getColumnModel().getColumnCount() > 0) {
            tblHdct.getColumnModel().getColumn(0).setResizable(false);
            tblHdct.getColumnModel().getColumn(1).setResizable(false);
            tblHdct.getColumnModel().getColumn(2).setResizable(false);
            tblHdct.getColumnModel().getColumn(3).setResizable(false);
            tblHdct.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel23.setText("Đơn Giá");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1001, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Thành Tiền");

        txtThanhTien.setToolTipText("");
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hoá Đơn", "Tên Khách Hàng", "Mã Voucher", "Tên Nhân Viên", "Ngày Thanh Toán", "Tổng Tiền", "Tiền Voucher", "Thành Tiền", "Phương Thức ", "Ghi Chú", "Trạng Thái "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(4).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(5).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(6).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(7).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(8).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(9).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(10).setResizable(false);
        }

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        jLabel27.setText("Tổng Hoá Đơn");

        jLabel28.setText("Tổng Giá Trị");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lịch Sử Hoá Đơn", jPanel6);

        tblHoaDon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hoá Đơn", "Tên Khách Hàng", "Mã Voucher", "Tên Nhân Viên", "Ngày Thanh Toán", "Tổng Tiền", "Tiền Voucher", "Thành Tiền", "Phương Thức ", "Ghi Chú", "Trạng Thái "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon1);
        if (tblHoaDon1.getColumnModel().getColumnCount() > 0) {
            tblHoaDon1.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(4).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(5).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(6).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(7).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(8).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(9).setResizable(false);
            tblHoaDon1.getColumnModel().getColumn(10).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hoá Đơn Đã Xoá", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(736, 736, 736)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenNv, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel8))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClear)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 108, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 568, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 569, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClear)
                        .addGap(9, 9, 9)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel24)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 416, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 416, Short.MAX_VALUE)))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Tìm Kiếm");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTimKiemTheoVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoVanBanActionPerformed
        // TODO add your handling code here:
        String pt = (String) cboPt.getSelectedItem();
        String text = txtVanBan.getText();
        Date d1 = txtNgayBatDau.getDate();
        Date d2 = txtNgayKetThuc.getDate();
        if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() == null && txtNgayKetThuc.getDate() == null && cboPt.getSelectedIndex() == 0) {
            searchText(text);
        } else if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() == 0) {
            searchTextDay(d1, d2, text);
        } else if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() == null && txtNgayKetThuc.getDate() == null && cboPt.getSelectedIndex() != 0) {
            searchTextCbo(text, pt);
        } else if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() != 0) {
            searchAll(text, pt, d1, d2);
        } else if (txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() == 0) {
            searchDays(d1, d2);
        } else if (txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() != 0) {
            searchCboDay(d1, d2, pt);
        }

    }//GEN-LAST:event_btnTimKiemTheoVanBanActionPerformed

    private void cboPtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPtActionPerformed
        // TODO add your handling code here:
        String text = txtVanBan.getText();
        String pt = (String) cboPt.getSelectedItem();
        Date d1 = txtNgayBatDau.getDate();
        Date d2 = txtNgayKetThuc.getDate();
        if (txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() == null && txtNgayKetThuc.getDate() == null && cboPt.getSelectedIndex() != 0) {
            searchCbos(pt);
        } else if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() == null && txtNgayKetThuc.getDate() == null && cboPt.getSelectedIndex() != 0) {
            searchTextCbo(text, pt);
        } else if (txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() != 0) {
            searchCboDay(d1, d2, pt);
        } else if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() != 0) {
            searchAll(text, pt, d1, d2);
        } else if (txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() == null && txtNgayKetThuc.getDate() == null && cboPt.getSelectedIndex() == 0) {
            fillTable1();

        } else if (!txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() == null && txtNgayKetThuc.getDate() == null && cboPt.getSelectedIndex() == 0) {
            searchText(text);
        } else if (txtVanBan.getText().isEmpty() && txtNgayBatDau.getDate() != null && txtNgayKetThuc.getDate() != null && cboPt.getSelectedIndex() == 0) {
            searchDays(d1, d2);

        }
    }//GEN-LAST:event_cboPtActionPerformed

    private void tblHdctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHdctMouseClicked
        index2 = tblHdct.getSelectedRow();
        if (evt.getClickCount() == 1) {
            setForm2();
        } else if (evt.getClickCount() == 2) {
            String MaHoaDon = tblHdct.getValueAt(index2, 0).toString();
            int underscoreIndex = MaHoaDon.indexOf('_');
            String HoaDonChiTiet = MaHoaDon.substring(underscoreIndex + 1);
            int idHDCT = Integer.parseInt(HoaDonChiTiet);
            main.view.chucnang.HoaDonChiTiet dialog = new main.view.chucnang.HoaDonChiTiet((JFrame) SwingUtilities.getWindowAncestor(this), true, idHDCT);
            dialog.setIdHDCT(idHDCT);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_tblHdctMouseClicked

    private void txtVanBanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVanBanFocusGained
        // TODO add your handling code here:
        txtVanBan.setText("");
        fillTable1();
    }//GEN-LAST:event_txtVanBanFocusGained

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        index = tblHoaDon.getSelectedRow();
        setForm();
        fillTable2();
        loadSet();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon1MouseClicked
        // TODO add your handling code here:
        index = tblHoaDon1.getSelectedRow();
        fillTable3();
        clearForm1();
        setForm1();
        fillTable2();
        loadSet();
    }//GEN-LAST:event_tblHoaDon1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ArrayList<HoaDonEntity> arr = new ArrayList<>();
        arr = hdre.getAll(); // Giả sử hdre đã được khởi tạo và phương thức getAll() trả về danh sách hóa đơn
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh Sach");

        // Tạo tiêu đề cột
        Row headerRow = sheet.createRow(0);
        String[] headers = {"STT", "Mã Hoá Đơn", "Tên Khách Hàng", "Mã Voucher", "Tên Nhân Viên",
            "Ngày Thanh Toán", "Tổng Tiền", "Tiền Voucher", "Thành Tiền",
            "Phương Thức", "Ghi Chú", "Trạng Thái"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i, CellType.STRING);
            cell.setCellValue(headers[i]);
        }

        for (int i = 0; i < arr.size(); i++) {
            HoaDonEntity hd = arr.get(i);
            Row row = sheet.createRow(i + 1); // Bắt đầu từ hàng 1 để để lại hàng 0 cho tiêu đề

            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(i + 1);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(hd.getMaHoaDon());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(hd.getTenKhachHang());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(hd.getMaVoucher());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(hd.getTenNhanVien());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(hd.getNgayThanhToan());

            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(hd.getTongTien());

            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(hd.getTienVoucher());

            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellValue(hd.getThanhTien());

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue(hd.getPhuongThucThanhToan());

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(hd.getGhiChu());

            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(hd.getTrangThaiThanhToan() == 0 ? "Chưa Thanh Toán" : "Đã Thanh Toán");
        }
        File file = new File("D:\\ProjectLaptop1\\ProjectLaptop1//danhsach.xlsx");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
            JOptionPane.showMessageDialog(null, "In thành công D:\\ProjectLaptop1\\ProjectLaptop1//danhsach.xlsx");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: File không tìm thấy.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: Không thể ghi file.");
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBatDauFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnTimKiemTheoVanBan;
    private javax.swing.JComboBox<String> cboPt;
    private javax.swing.JButton jButton3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblHdct;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDon1;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaHoaDon2;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private com.toedter.calendar.JDateChooser txtNgayThanhToan;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenNv;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtVanBan;
    // End of variables declaration//GEN-END:variables

}
