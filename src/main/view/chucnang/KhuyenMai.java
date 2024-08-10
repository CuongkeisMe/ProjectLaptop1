/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package main.view.chucnang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.ToanCuc;
import main.entity.Voucher;
import main.repository.VoucherRepository;

/**
 *
 * @author Windows
 */
public class KhuyenMai extends javax.swing.JInternalFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DefaultTableModel model3 = new DefaultTableModel();
    VoucherRepository re = new VoucherRepository();
    int index;
    int index1;
    int index2;
    int index3;

    /**
     * Creates new form KhuyenMai
     */
    public KhuyenMai() {
        initComponents();
        this.cauhinhForm();
        model = (DefaultTableModel) tblVoucher.getModel();
        model1 = (DefaultTableModel) tblVoucher1.getModel();
        model2 = (DefaultTableModel) tblVoucher2.getModel();
        model3 = (DefaultTableModel) tblVoucher3.getModel();
        load();
        load1();
        load2();
        load3();
        txtNgayPhatHanh.setDateFormatString("dd-MM-yyyy");
        txtNgayHetHan.setDateFormatString("dd-MM-yyyy");
        if (model.getRowCount() > 0) {
            index = 0;
            setForm();
        }
        txtNguoiTao.setEnabled(false);
        txtMaVoucher.setEnabled(false);
    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    private void load() {
        ArrayList<Voucher> list = re.getAll();
        for (Voucher vc : list) {
            Object[] rowData = vc.toVoucher();
            rowData[8] = (int) rowData[8] == 0 ? "Tiền Mặt" : "Phần Trăm";
            rowData[10] = (int) rowData[10] == 0 ? "Hết Hạn" : "Đang Diễn Ra";
            model.addRow(rowData);
        }
    }

    private void load1() {
        ArrayList<Voucher> list = re.getOn();
        for (Voucher vc : list) {
            Object[] rowData = vc.toVoucher();
            rowData[8] = (int) rowData[8] == 0 ? "Tiền Mặt" : "Phần Trăm";
            rowData[10] = (int) rowData[10] == 0 ? "Hết Hạn" : "Đang Diễn Ra";
            model1.addRow(rowData);
        }
    }

    private void load2() {
        ArrayList<Voucher> list = re.getOff();
        for (Voucher vc : list) {
            Object[] rowData = vc.toVoucher();
            rowData[8] = (int) rowData[8] == 0 ? "Tiền Mặt" : "Phần Trăm";
            rowData[10] = (int) rowData[10] == 0 ? "Hết Hạn" : "Đang Diễn Ra";
            model2.addRow(rowData);
        }
    }

    private void load3() {
        ArrayList<Voucher> list = re.getRemove();
        for (Voucher vc : list) {
            Object[] rowData = vc.toVoucher();
            rowData[8] = (int) rowData[8] == 0 ? "Tiền Mặt" : "Phần Trăm";
            rowData[10] = (int) rowData[10] == 0 ? "Hết Hạn" : "Đang Diễn Ra";
            model3.addRow(rowData);
        }
    }

    private void search(String text) {
        ArrayList<Voucher> list = re.search(text);
        for (Voucher vc : list) {
            Object[] rowData = vc.toVoucher();
            rowData[8] = (int) rowData[8] == 0 ? "Tiền Mặt" : "Phần Trăm";
            rowData[10] = (int) rowData[10] == 0 ? "Hết Hạn" : "Đang Diễn Ra";
            model.addRow(rowData);
        }
    }

    private void setForm() {
        txtMaVoucher.setText(model.getValueAt(index, 0).toString());
        txtNguoiTao.setText(model.getValueAt(index, 1).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Adjust the date format as needed
            Date ngayPhatHanh = dateFormat.parse(model.getValueAt(index, 2).toString());
            txtNgayPhatHanh.setDate(ngayPhatHanh);
            Date ngayHetHan = dateFormat.parse(model.getValueAt(index, 3).toString());
            txtNgayHetHan.setDate(ngayHetHan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtSoLuong.setText(model.getValueAt(index, 4).toString());
        txtGiaTriVoucher.setText(model.getValueAt(index, 5).toString());
        txtGiaTriToiDa.setText(model.getValueAt(index, 6).toString());
        txtDieuKien.setText(model.getValueAt(index, 7).toString());
        if (model.getValueAt(index, 8).toString().equalsIgnoreCase("Tiền Mặt")) {
            rdoTienMat.setSelected(true);
        } else {
            rdoPhanTram.setSelected(true);
        }
        txtMoTa.setText(model.getValueAt(index, 9).toString());
        if (model.getValueAt(index, 10).toString().equalsIgnoreCase("Đang Diễn Ra")) {
            rdoDienRa.setSelected(true);
        } else {
            rdoHetHan.setSelected(true);
        }
    }

    private void setForm1() {
        txtMaVoucher.setText(model1.getValueAt(index, 0).toString());
        txtNguoiTao.setText(model1.getValueAt(index, 1).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Adjust the date format as needed
            Date ngayPhatHanh = dateFormat.parse(model1.getValueAt(index, 2).toString());
            txtNgayPhatHanh.setDate(ngayPhatHanh);
            Date ngayHetHan = dateFormat.parse(model1.getValueAt(index, 3).toString());
            txtNgayHetHan.setDate(ngayHetHan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtSoLuong.setText(model1.getValueAt(index, 4).toString());
        txtGiaTriVoucher.setText(model1.getValueAt(index, 5).toString());
        txtGiaTriToiDa.setText(model1.getValueAt(index, 6).toString());
        txtDieuKien.setText(model1.getValueAt(index, 7).toString());
        if (model.getValueAt(index, 8).toString().equalsIgnoreCase("Tiền Mặt")) {
            rdoTienMat.setSelected(true);
        } else {
            rdoPhanTram.setSelected(true);
        }
        txtMoTa.setText(model1.getValueAt(index, 9).toString());
        if (model1.getValueAt(index, 10).toString().equalsIgnoreCase("Đang Diễn Ra")) {
            rdoDienRa.setSelected(true);
        } else {
            rdoHetHan.setSelected(true);
        }
    }

    private void setForm2() {
        txtMaVoucher.setText(model2.getValueAt(index, 0).toString());
        txtNguoiTao.setText(model2.getValueAt(index, 1).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Adjust the date format as needed
            Date ngayPhatHanh = dateFormat.parse(model2.getValueAt(index, 2).toString());
            txtNgayPhatHanh.setDate(ngayPhatHanh);
            Date ngayHetHan = dateFormat.parse(model2.getValueAt(index, 3).toString());
            txtNgayHetHan.setDate(ngayHetHan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtSoLuong.setText(model2.getValueAt(index, 4).toString());
        txtGiaTriVoucher.setText(model2.getValueAt(index, 5).toString());
        txtGiaTriToiDa.setText(model2.getValueAt(index, 6).toString());
        txtDieuKien.setText(model2.getValueAt(index, 7).toString());
        if (model2.getValueAt(index, 8).toString().equalsIgnoreCase("Tiền Mặt")) {
            rdoTienMat.setSelected(true);
        } else {
            rdoPhanTram.setSelected(true);
        }
        txtMoTa.setText(model2.getValueAt(index, 9).toString());
        if (model2.getValueAt(index, 10).toString().equalsIgnoreCase("Đang Diễn Ra")) {
            rdoDienRa.setSelected(true);
        } else {
            rdoHetHan.setSelected(true);
        }
    }

    private void setForm3() {
        txtMaVoucher.setText(model3.getValueAt(index, 0).toString());
        txtNguoiTao.setText(model3.getValueAt(index, 1).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Adjust the date format as needed
            Date ngayPhatHanh = dateFormat.parse(model3.getValueAt(index, 2).toString());
            txtNgayPhatHanh.setDate(ngayPhatHanh);
            Date ngayHetHan = dateFormat.parse(model3.getValueAt(index, 3).toString());
            txtNgayHetHan.setDate(ngayHetHan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtSoLuong.setText(model3.getValueAt(index, 4).toString());
        txtGiaTriVoucher.setText(model3.getValueAt(index, 5).toString());
        txtGiaTriToiDa.setText(model3.getValueAt(index, 6).toString());
        txtDieuKien.setText(model3.getValueAt(index, 7).toString());
        if (model3.getValueAt(index, 8).toString().equalsIgnoreCase("Tiền Mặt")) {
            rdoTienMat.setSelected(true);
        } else {
            rdoPhanTram.setSelected(true);
        }
        txtMoTa.setText(model3.getValueAt(index, 9).toString());
        if (model3.getValueAt(index, 10).toString().equalsIgnoreCase("Đang Diễn Ra")) {
            rdoDienRa.setSelected(true);
        } else {
            rdoHetHan.setSelected(true);
        }
    }

    private void clear() {
        txtMaVoucher.setText(null);
        txtNguoiTao.setText(null);
        txtMoTa.setText(null);
        txtNgayPhatHanh.setDate(null);
        txtNgayHetHan.setDate(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        txtSoLuong.setText(null);
        txtGiaTriToiDa.setText(null);
        txtGiaTriVoucher.setText(null);
        txtDieuKien.setText(null);

    }

    private Voucher getForm() {
        Voucher vc = new Voucher();
        vc.setMaVoucher(txtMaVoucher.getText());
        vc.setMoTa(txtMoTa.getText());
        vc.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        vc.setGiaTriVoucher(Float.parseFloat(txtGiaTriVoucher.getText()));
        vc.setGiaTriToiDa(Float.parseFloat(txtGiaTriToiDa.getText()));
        vc.setDieuKien(Float.parseFloat(txtDieuKien.getText()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanh = format.format(txtNgayPhatHanh.getDate());
        String ngayHetHan = format.format(txtNgayHetHan.getDate());
        vc.setNgayPhatHanh(ngayPhatHanh);
        vc.setNgayHetHan(ngayHetHan);
        vc.setLoai(rdoTienMat.isSelected() ? 0 : 1);
        vc.setTrangThai(rdoHetHan.isSelected() ? 0 : 1);
        return vc;
    }

    private boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean check() {
        if (txtMaVoucher.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Voucher không được để trống");
            txtMaVoucher.requestFocus();
            return false;
        }
        ArrayList<Voucher> list = re.getAll();
        for (Voucher vc : list) {
            if (vc.getMaVoucher().equals(txtMaVoucher.getText())) {
                JOptionPane.showMessageDialog(this, "Trùng Mã Voucher!Mời Nhập Lại!");
                return false;
            }
        }
        if (txtNguoiTao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Người Tạo không được để trống");
            txtNguoiTao.requestFocus();
            return false;
        }
        if (txtMoTa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mô Tả không được để trống");
            txtMoTa.requestFocus();
            return false;
        }
        if (txtNgayPhatHanh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày Phát Hành không được để trống");
            txtNgayPhatHanh.requestFocus();
            return false;
        }
        if (txtNgayHetHan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày Hết Hạn không được để trống");
            txtNgayHetHan.requestFocus();
            return false;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Lượng không được để trống");
            txtSoLuong.requestFocus();
            return false;
        }
        if (!isNumeric(txtSoLuong.getText())) {
            JOptionPane.showMessageDialog(this, "Số Lượng phải là số hợp lệ");
            txtSoLuong.requestFocus();
            return false;
        }
        if (txtGiaTriVoucher.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá Trị Voucher không được để trống");
            txtGiaTriVoucher.requestFocus();
            return false;
        }
        if (!isNumeric(txtGiaTriVoucher.getText())) {
            JOptionPane.showMessageDialog(this, "Giá Trị Voucher phải là số hợp lệ");
            txtGiaTriVoucher.requestFocus();
            return false;
        }
        if (txtGiaTriToiDa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá Trị Tối Đa không được để trống");
            txtGiaTriToiDa.requestFocus();
            return false;
        }
        if (!isNumeric(txtGiaTriToiDa.getText())) {
            JOptionPane.showMessageDialog(this, "Giá Trị Tối Đa phải là số hợp lệ");
            txtGiaTriToiDa.requestFocus();
            return false;
        }
        if (txtDieuKien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điều Kiện không được để trống");
            txtDieuKien.requestFocus();
            return false;
        }
        if (!isNumeric(txtDieuKien.getText())) {
            JOptionPane.showMessageDialog(this, "Giá Trị Điều Kiện phải là số hợp lệ");
            txtDieuKien.requestFocus();
            return false;
        }
        if (!rdoTienMat.isSelected() && !rdoPhanTram.isSelected()) {
            JOptionPane.showMessageDialog(this, "Phương Thức không được để trống");
            rdoTienMat.requestFocus();
            return false;
        }
        if (!rdoHetHan.isSelected() && !rdoDienRa.isSelected()) {
            JOptionPane.showMessageDialog(this, "Trạng Thái không được để trống");
            rdoHetHan.requestFocus();
            return false;
        }

        try {
            float giaTriVoucher = Float.parseFloat(txtGiaTriVoucher.getText());
            float giaTriToiDa = Float.parseFloat(txtGiaTriToiDa.getText());
            float dieuKien = Float.parseFloat(txtDieuKien.getText());

            if (giaTriVoucher > giaTriToiDa) {
                JOptionPane.showMessageDialog(this, "Giá Trị Voucher Không Được Lớn Giá Trị Tối Đa!");
                return false;
            }

            if (dieuKien < 0 || giaTriToiDa < 0 || giaTriVoucher < 0) {
                JOptionPane.showMessageDialog(this, "Giá Trị Voucher, Điều Kiện, Giá Trị Tối Đa không được nhỏ hơn 0!");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số. Vui lòng kiểm tra lại các giá trị số.");
            return false;
        }

        return true;
    }

    private void update() {
        if (check()) {
            Voucher vc = getForm();
            int qs = JOptionPane.showConfirmDialog(this, "Bạn Chắc Chắn Muốn Sửa Voucher Này?");
            if (qs == JOptionPane.YES_OPTION) {
                if (re.updateVoucher(vc)) {
                    JOptionPane.showMessageDialog(this, "Sửa Thành Công!");
                    model.setRowCount(0);
                    model1.setRowCount(0);
                    model2.setRowCount(0);
                    load();
                    load1();
                    load2();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Thất Bại!");
                    return;
                }
            } else {
                return;
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel2 = new javax.swing.JLabel();
        txtMaVoucher = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtNgayPhatHanh = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtNgayHetHan = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoucher1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblVoucher2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblVoucher3 = new javax.swing.JTable();
        rdoTienMat = new javax.swing.JRadioButton();
        rdoPhanTram = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtGiaTriVoucher = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaTriToiDa = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        rdoHetHan = new javax.swing.JRadioButton();
        rdoDienRa = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setPreferredSize(new java.awt.Dimension(1100, 800));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jLabel2.setText("Mã Voucher");

        jLabel3.setText("Người Tạo");

        jLabel4.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel5.setText("Ngày Phát Hành");

        jLabel6.setText("Ngày Hết Hạn");

        jLabel7.setText("Số Lượng");

        jLabel8.setText("Phương Thức");

        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Voucher", "Người Tạo", "Ngày Phát Hành", "Ngày Hết Hạn", "Số Lượng", "Giá Trị Voucher", "Giá Trị Tối Đa", "Điều Kiện", "Phương Thức", "Mô Tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVoucher);
        if (tblVoucher.getColumnModel().getColumnCount() > 0) {
            tblVoucher.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher.getColumnModel().getColumn(5).setResizable(false);
            tblVoucher.getColumnModel().getColumn(6).setResizable(false);
            tblVoucher.getColumnModel().getColumn(7).setResizable(false);
            tblVoucher.getColumnModel().getColumn(8).setResizable(false);
            tblVoucher.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1073, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tất Cả", jPanel1);

        tblVoucher1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Voucher", "Người Tạo", "Ngày Phát Hành", "Ngày Hết Hạn", "Số Lượng", "Giá Trị Voucher", "Giá Trị Tối Đa", "Điều Kiện", "Phương Thức", "Mô Tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucher1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVoucher1);
        if (tblVoucher1.getColumnModel().getColumnCount() > 0) {
            tblVoucher1.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(5).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(6).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(7).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(8).setResizable(false);
            tblVoucher1.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1073, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Voucher Đang Diễn Ra", jPanel2);

        tblVoucher2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Voucher", "Người Tạo", "Ngày Phát Hành", "Ngày Hết Hạn", "Số Lượng", "Giá Trị Voucher", "Giá Trị Tối Đa", "Điều Kiện", "Phương Thức", "Mô Tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucher2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblVoucher2);
        if (tblVoucher2.getColumnModel().getColumnCount() > 0) {
            tblVoucher2.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(5).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(6).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(7).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(8).setResizable(false);
            tblVoucher2.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1072, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Voucher Hết Hạn", jPanel3);

        tblVoucher3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Voucher", "Người Tạo", "Ngày Phát Hành", "Ngày Hết Hạn", "Số Lượng", "Giá Trị Voucher", "Giá Trị Tối Đa", "Điều Kiện", "Phương Thức", "Mô Tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucher3MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblVoucher3);
        if (tblVoucher3.getColumnModel().getColumnCount() > 0) {
            tblVoucher3.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(5).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(6).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(7).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(8).setResizable(false);
            tblVoucher3.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1072, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Voucher Đã Xoá", jPanel5);

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setText("Tiền Mặt");

        buttonGroup1.add(rdoPhanTram);
        rdoPhanTram.setText("Phần Trăm");

        jLabel9.setText("Giá Trị Voucher");

        jLabel10.setText("Giá Trị Tối Đa");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/32378_add_plus_icon.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/1582587_arrow_refresh_reload_rotate icon_icon.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/5049209_bin_delete_remove_trash_icon.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/11211468_eraser_clear_remove_tool_rubber_icon.png"))); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
        });

        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel12.setText("Trạng Thái");

        buttonGroup2.add(rdoHetHan);
        rdoHetHan.setText("Hết Hạn");

        buttonGroup2.add(rdoDienRa);
        rdoDienRa.setText("Đang Diễn Ra");

        jLabel11.setText("Điều Kiện");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtMaVoucher))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayPhatHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel10)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtGiaTriToiDa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING))))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaTriVoucher)
                            .addComponent(txtDieuKien))
                        .addGap(62, 62, 62))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(31, 31, 31)
                        .addComponent(rdoHetHan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDienRa))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoTienMat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoPhanTram)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(319, 319, 319))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addComponent(txtNgayPhatHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoTienMat)
                                .addComponent(rdoPhanTram))
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnUpdate))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaTriVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(rdoHetHan)
                                    .addComponent(rdoDienRa)
                                    .addComponent(txtGiaTriToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa)
                                .addGap(14, 14, 14)
                                .addComponent(btnClear))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        String tenNhanVien = ToanCuc.getTenNhanVien();
        clear();
        txtNguoiTao.setText(tenNhanVien);
        txtMaVoucher.setEnabled(true);
        txtNguoiTao.setEnabled(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (check()) {
            Voucher vc = getForm();
            int idNhanVien = ToanCuc.getId();
            int qs = JOptionPane.showConfirmDialog(this, "Bạn Chắc Chắn Muốn Tạo Voucher Này?");
            if (qs == JOptionPane.YES_OPTION) {
                if (re.addVoucher(vc, idNhanVien)) {
                    JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
                    model.setRowCount(0);
                    load();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại!");
                    return;
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (txtMaVoucher.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Voucher Để Xoá!");
            return;
        } else {
            int qs = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xoá Voucher Này?");
            if (qs == JOptionPane.YES_OPTION) {
                String maVoucher = txtMaVoucher.getText();
                if (re.deleteVoucher(maVoucher)) {
                    JOptionPane.showMessageDialog(this, "Xoá Thành Công");
                    model.setRowCount(0);
                    model3.setRowCount(0);
                    load();
                    load3();
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Thất Bại");
                    return;
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String text = txtTimKiem.getText();
        if (!text.isEmpty()) {
            model.setRowCount(0);
            search(text);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Điền Dữ Liệu Cần Tìm Kiếm");
            return;
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblVoucher2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucher2MouseClicked
        // TODO add your handling code here:
        index = tblVoucher2.getSelectedRow();
        setForm2();
    }//GEN-LAST:event_tblVoucher2MouseClicked

    private void tblVoucher1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucher1MouseClicked
        // TODO add your handling code here:
        index = tblVoucher1.getSelectedRow();
        setForm1();
    }//GEN-LAST:event_tblVoucher1MouseClicked

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        // TODO add your handling code here:
        index = tblVoucher.getSelectedRow();
        setForm();
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void tblVoucher3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucher3MouseClicked
        // TODO add your handling code here:
        index = tblVoucher3.getSelectedRow();
        setForm3();
    }//GEN-LAST:event_tblVoucher3MouseClicked

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        // TODO add your handling code here:
        txtTimKiem.setText(null);
        model.setRowCount(0);
        load();
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoDienRa;
    private javax.swing.JRadioButton rdoHetHan;
    private javax.swing.JRadioButton rdoPhanTram;
    private javax.swing.JRadioButton rdoTienMat;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTable tblVoucher1;
    private javax.swing.JTable tblVoucher2;
    private javax.swing.JTable tblVoucher3;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtGiaTriToiDa;
    private javax.swing.JTextField txtGiaTriVoucher;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextArea txtMoTa;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private com.toedter.calendar.JDateChooser txtNgayPhatHanh;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
