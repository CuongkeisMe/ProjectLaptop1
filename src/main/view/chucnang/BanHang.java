package main.view.chucnang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.HoaDonTro;
import main.response.BanHangResponse;
import main.entity.KhachHang;
import main.entity.Voucher;
import main.entity.Vouchers;
import main.repository.BanHangSPRepositories;
import main.repository.HoaDonChiTietRepository;
import main.repository.HoaDonRepository;
import main.repository.KhachHangRepository;
import main.request.FindKhachHang;
import main.request.FindSanPham;
import main.response.HoaDonChiTietResponse;
import main.view.sanphamchitiet.BHChonKH;
import main.view.sanphamchitiet.ImeiChiTiet;
import main.view.sanphamchitiet.ImeiGioHang;

public class BanHang extends javax.swing.JInternalFrame {

    private DefaultTableModel dtmSP;
    private DefaultTableModel dfhoadon;
    private DefaultTableModel dtmGioHang;
    private BanHangSPRepositories banhangRepository;
    private HoaDonChiTietRepository hdctRepository;
    private HoaDonRepository hdsp;
    private BanHangResponse banHangResponse;
    private HoaDonTro hoaDonTro;
    private String currentInvoiceId;
    private List<Vouchers> vouchers;
    int index = -1;
    public boolean productExists;
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");

    public BanHang() {
        initComponents();
        cboHTTT.removeAllItems();
        cboHTTT.addItem("Tiền mặt");
        cboHTTT.addItem("Chuyển khoản");

        this.cauhinhForm();
        dtmSP = (DefaultTableModel) tblSP.getModel();
        dtmGioHang = (DefaultTableModel) tblGioHang.getModel();
        dfhoadon = (DefaultTableModel) tblHoaDonTro.getModel();
        banhangRepository = new BanHangSPRepositories();
        hdctRepository = new HoaDonChiTietRepository();
        hdsp = new HoaDonRepository();
        hoaDonTro = new HoaDonTro();
        banHangResponse = new BanHangResponse();
        this.showDataTableSP(banhangRepository.getAll(getFormSearch()));
        this.showDatahoadon(hdsp.getAllHoaDon());
        txtNameKH.setEnabled(false);
        txtSDTKH.setEnabled(false);
        txtTienTra.setEditable(false);
        txtTienSP.setEditable(false);
        txtTongTien.setEditable(false);
        txtGG.setEditable(false);
        txtMaHD.setEnabled(false);
        txtTenNV.setEnabled(false);
        initialize();

    }

    private void initialize() {
        // Cập nhật danh sách voucher theo loại giảm giá mặc định (tiền mặt)
        updateVoucherList(true); // Loại 0 cho giảm giá tiền mặt
        // Nếu cần, chọn voucher mặc định trong JComboBox (nếu có)
        cboMaVoucher.setSelectedIndex(0); // Chọn mục đầu tiên
    }

    private void updateVoucherList(boolean isTienMat) {
        List<Vouchers> vouchers;
        if (isTienMat) {
            vouchers = banhangRepository.getVouchersByType(0);
        } else {
            vouchers = banhangRepository.getVouchersByType(1);
        }

        // Cập nhật JComboBox
        cboMaVoucher.removeAllItems();
        for (Vouchers voucher : vouchers) {
            cboMaVoucher.addItem(voucher); // Thêm đối tượng Vouchers vào JComboBox
        }
    }

    private void handleVoucherSelection() {
        Vouchers selectedVoucher = (Vouchers) cboMaVoucher.getSelectedItem();
        if (selectedVoucher != null) {
            String maVoucher = selectedVoucher.getMaVoucher(); // Lấy mã voucher từ đối tượng
            // Xử lý mã voucher
        }
    }

    public void setCustomerName(String customerName) {
        txtSDTKH.setText(customerName);
    }

    public void setCustomerPhone(String customerPhone) {
        txtNameKH.setText(customerPhone);
    }

    private void updateChange() {
        try {
            // Lấy giá trị tổng tiền và số tiền khách hàng trả từ ô văn bản
            String totalText = txtTongTien.getText().replaceAll("[^\\d.,]", "");
            String amountPaidText = txtTienKH.getText().replaceAll("[^\\d.,]", "");

            // Sử dụng DecimalFormat để phân tích số tiền
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            decimalFormat.setParseBigDecimal(true);

            // Chuyển đổi văn bản thành số
            Number totalNumber = decimalFormat.parse(totalText);
            Number amountPaidNumber = decimalFormat.parse(amountPaidText);

            double total = totalNumber.doubleValue();
            double amountPaid = amountPaidNumber.doubleValue();

            // Tính số tiền cần trả lại
            double change = amountPaid - total;

            // Cập nhật số tiền trả lại với định dạng hàng nghìn
            if (change < 0) {
                txtTienTra.setText("Số tiền khách hàng trả không đủ");
            } else {
                txtTienTra.setText(decimalFormat.format(change));
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private void tongTien() {
        double total = 0.0;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            try {
                String priceStr = tblGioHang.getValueAt(i, 3).toString();
                NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
                Number priceNumber = numberFormat.parse(priceStr);
                double price = priceNumber.doubleValue();

                int quantity = Integer.parseInt(tblGioHang.getValueAt(i, 4).toString());

                total += price * quantity;
                txtTienSP.setText(decimalFormat.format(total));
            } catch (ParseException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ: " + e.getMessage());
                return;
            }
        }
    }

    private void updateTotal() {
        double total = 0.0;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            try {
                Object priceObj = tblGioHang.getValueAt(i, 3);
                Object quantityObj = tblGioHang.getValueAt(i, 4);

                if (priceObj == null || quantityObj == null) {
                    continue;
                }

                String priceStr = priceObj.toString().replaceAll("[^\\d.,]", "");
                NumberFormat numberFormat = new DecimalFormat("#,###.##");
                Number priceNumber = numberFormat.parse(priceStr);
                double price = priceNumber.doubleValue();

                int quantity = Integer.parseInt(quantityObj.toString());

                total += price * quantity;
            } catch (ParseException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ: " + e.getMessage());
                return;
            }
        }

        double discount = 0.0;
        try {
            String discountText = txtGG.getText().replaceAll("[^\\d.,]", "");
            Number discountNumber = new DecimalFormat("#,###.##").parse(discountText);
            discount = discountNumber.doubleValue();
        } catch (ParseException e) {
            discount = 0.0;
        }

        double finalTotal = total;
        if (discount < 100) {
            finalTotal = total - (total * (discount / 100));
        } else if (discount >= 100) {
            if (discount > total) {
                discount = 0.0;
            }
            finalTotal = total - discount;
        }

        txtTongTien.setText(decimalFormat.format(finalTotal));
    }

    private void applyVoucher() {
        // Lấy đối tượng Voucher được chọn từ JComboBox
        Vouchers selectedVoucher = (Vouchers) cboMaVoucher.getSelectedItem();

        // Kiểm tra xem có voucher nào được chọn không
        if (selectedVoucher == null) {
            txtGG.setText("0");
            return;
        }

        // Lấy mã voucher từ đối tượng Voucher
        String voucherCode = selectedVoucher.getMaVoucher().trim();

        // Kiểm tra mã voucher có hợp lệ hay không
        if (voucherCode.isEmpty()) {
            txtGG.setText("0");
            return;
        }

        // Gọi phương thức checkVoucher trên đối tượng banhangRepository
        float discount = banhangRepository.checkVoucher(voucherCode);

        if (discount == -1) {
            txtGG.setText("Đã hết hạn sử dụng");
        } else if (discount == -2) {
            txtGG.setText("Mã voucher không hợp lệ");
        } else if (discount == -3) {
            txtGG.setText("Lỗi hệ thống");
        } else if (discount > 0 && discount < 100) {
            String tienSPText = txtTienSP.getText().replaceAll("[^\\d.,]", "").trim();

            if (tienSPText.isEmpty()) {
                txtGG.setText("0");
                return;
            }

            float tienSP;
            try {
                // Sử dụng NumberFormat để phân tích số với dấu phẩy làm dấu thập phân
                NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
                Number number = format.parse(tienSPText);
                tienSP = number.floatValue();
            } catch (ParseException e) {
                txtGG.setText("0");
                return;
            }

            // Tính số tiền giảm giá
            double tienGG = tienSP * (discount / 100.0);

            // Định dạng số tiền giảm giá với phân cách hàng nghìn
            if (tienGG > 900000) {
                tienGG = 900000;
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                txtGG.setText(decimalFormat.format(tienGG));
            } else {
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                txtGG.setText(decimalFormat.format(tienGG));
            }
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            txtGG.setText(decimalFormat.format(discount));
        }
    }

    public String getSelectedInvoiceId() {
        int selectedRow = tblHoaDonTro.getSelectedRow();
        if (selectedRow >= 0) {
            return (String) tblHoaDonTro.getValueAt(selectedRow, 1); // Giả sử cột 1 chứa mã hóa đơn
        }
        return null;
    }

    public void showDataTableSP(ArrayList<BanHangResponse> list) {
        dtmSP.setRowCount(0);
        list.forEach(x -> dtmSP.addRow(new Object[]{
            x.getMaSanPham(), x.getTenSanPham(), x.getTenCPU(), x.getTenGPU(), x.getLoaiOCung(), x.getDungluongRam(),
            x.getKichThuoc(), x.getDungluongPin(), decimalFormat.format(x.getGiaBan()), x.getSoLuong()
        }));
    }

    public void showDataGioHang(ArrayList<HoaDonChiTietResponse> list) {
        dtmGioHang.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(x -> dtmGioHang.addRow(new Object[]{
            index.getAndIncrement(), x.getMaSanPham(), x.getTenSanPham(), decimalFormat.format(x.getGiaBan()), x.getSoLuong(), decimalFormat.format(x.getTongTien())
        }));
        updateTotal();
        tongTien();
    }

    public void showDatahoadon(ArrayList<HoaDonTro> list) {
        dfhoadon.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            HoaDonTro hd = list.get(i);
            dfhoadon.addRow(new Object[]{
                i + 1,
                hd.getMaHoaDon(),
                hd.getNgayTao(),
                hd.getMaNhanVien(),
                hd.getTinhTrangThanhToan() == 0 ? "Chờ Thanh Toán" : "Đã Thanh Toán",
                hd.getTinhTrang()});
        }
    }

    public FindSanPham getFormSearch() {
        FindSanPham fsp = new FindSanPham();
        fsp.setKeySearch1(txtSearch.getText());
        return fsp;
    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    public String layMaSPSelect() {
        int index = tblSP.getSelectedRow();
        return tblSP.getValueAt(index, 0).toString();
    }

    public String layMaSPSelectGioHang() {
        int index = -1;
        index = tblGioHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "lỗi");
            return null;
        }
        if (index != -1) {
            return (String) tblGioHang.getValueAt(index, 1);
        } else {
            return null;
        }
    }

    public int getIdByMa(String maSP) {
        for (BanHangResponse sanPham : banhangRepository.getAll(getFormSearch())) {
            if (sanPham.getMaSanPham().equals(maSP)) {
                return sanPham.getIdSanPham();
            }
        }
        return -1;
    }

    public int getIdHoaDonByMa(String maHoaDon) {
        for (int i = 0; i < tblHoaDonTro.getRowCount(); i++) {
            if (tblHoaDonTro.getValueAt(i, 1).equals(maHoaDon)) {
                for (HoaDonTro hoadon : hdsp.getAllHoaDon()) {
                    if (hoadon.getMaHoaDon().equals(maHoaDon)) {
                        return hoadon.getIdHoaDon();
                    }
                }
            }
        }
        return -1;
    }

    public String layMaHD() {
        index = tblHoaDonTro.getSelectedRow();
        String maHD = tblHoaDonTro.getValueAt(index, 1).toString();
        return maHD;
    }

    public float getGiaBan() {
        int index = tblGioHang.getSelectedRow();
        float giaBan = (float) hdctRepository.getAll(this.getIdHoaDonByMa(layMaHD())).get(index).getGiaBan();
        return giaBan;
    }

    public int getIdHDCT() {
        int index = tblGioHang.getSelectedRow();
        ArrayList<HoaDonChiTietResponse> hdctList = hdctRepository.getAll(getIdHoaDonByMa(layMaHD()));
        int idHDCT = hdctList.get(index).getIdHoaDonChiTiet();
        return idHDCT;
    }

    public int getSoLuong() {
        int index = tblSP.getSelectedRow();
        int soLuong = Integer.parseInt(tblSP.getValueAt(index, 9).toString());
        return soLuong;
    }

//    public int getSoLuongGioHang() {
//        int index = tblGioHang.getSelectedRow();
//        int soLuongGH = Integer.parseInt(tblGioHang.getValueAt(index, 4).toString());
//        return soLuongGH;
//    }
    public void refreshDataTable() {
        showDataGioHang(hdctRepository.getAll(this.getIdHoaDonByMa(layMaHD())));
        showDataTableSP(banhangRepository.getAll(getFormSearch()));
    }

    public int id_HDCT(int idhd, int idsp) {
        return banhangRepository.getIDHDCT_Click(idhd, idsp).get(0).getIdHDCT();
    }
    public static int layID_HDCT;

    public void addGioHang(float giaBan, Integer idSP, Integer soLuong) {
        try {
            Integer idHoaDon = this.getIdHoaDonByMa(layMaHD());
            productExists = banhangRepository.checkSanPhamGioHang(idHoaDon, idSP);
            if (productExists) {
                // cong don
                banhangRepository.updateSoLuongGioHang(idHoaDon, idSP, soLuong);
                JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm vào giỏ hàng thành công!");
                // them imei vaof imie da ban theo id hdct cu 
                layID_HDCT = id_HDCT(idHoaDon, idSP);
            } else {
                banHangResponse = new BanHangResponse();
                banHangResponse.setIdSanPham(idSP);
                banHangResponse.setGiaBan(giaBan);
                if (banhangRepository.addGioHang(idHoaDon, banHangResponse, soLuong)) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm vào giỏ hàng thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm sản phẩm vào giỏ hàng!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xử lý giỏ hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnSearch = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonTro = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtGG = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTienKH = new javax.swing.JTextField();
        txtTienTra = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNameKH = new javax.swing.JTextField();
        btnChon = new javax.swing.JButton();
        cboMaVoucher = new javax.swing.JComboBox<>();
        cboHTTT = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtTienSP = new javax.swing.JTextField();
        rdotm = new javax.swing.JRadioButton();
        rdopt = new javax.swing.JRadioButton();

        btnSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "CPU", "GPU", "Ổ cứng", "Ram", "Màn hình", "Pin", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSP.getTableHeader().setReorderingAllowed(false);
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSPMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);
        if (tblSP.getColumnModel().getColumnCount() > 0) {
            tblSP.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblSP.getColumnModel().getColumn(1).setMinWidth(130);
            tblSP.getColumnModel().getColumn(9).setPreferredWidth(40);
        }

        jLabel19.setText("Tìm kiếm");

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnSearchLayout = new javax.swing.GroupLayout(btnSearch);
        btnSearch.setLayout(btnSearchLayout);
        btnSearchLayout.setHorizontalGroup(
            btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .addGroup(btnSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        btnSearchLayout.setVerticalGroup(
            btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSearchLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tblHoaDonTro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày tạo", "Tên NV", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonTroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonTro);

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnTaoHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(38, 38, 38))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(324, 324, 324))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGioHangMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblGioHang.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblGioHang.getColumnModel().getColumn(2).setPreferredWidth(140);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn Hàng"));

        jLabel11.setText("Mã HĐ");

        jLabel12.setText("Tên NV");

        jLabel13.setText("Mã Voucher");

        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });

        jLabel14.setText("Giảm giá");

        txtGG.setForeground(new java.awt.Color(0, 0, 0));
        txtGG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGGKeyReleased(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Thành tiền");

        jLabel16.setText("Hình thức TT");

        jLabel17.setText("Tiền KH trả");

        jLabel18.setText("Tiền cần trả lại");

        txtTienKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKHKeyReleased(evt);
            }
        });

        txtTienTra.setForeground(new java.awt.Color(0, 0, 0));

        jButton6.setText("Thanh toán");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Làm mới");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(0, 0, 0));
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });
        txtTongTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongTienKeyReleased(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng:")));

        jLabel1.setText("SĐT");

        txtSDTKH.setText("Khách hàng lẻ");

        jLabel2.setText("Tên KH");

        txtNameKH.setText("Khách hàng lẻ");

        btnChon.setText("Chọn");
        btnChon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnChonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(txtNameKH))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChon)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnChon)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        cboMaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaVoucherActionPerformed(evt);
            }
        });

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboHTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTTTActionPerformed(evt);
            }
        });

        jLabel3.setText("Tổng tiền SP");

        txtTienSP.setForeground(new java.awt.Color(0, 0, 0));
        txtTienSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienSPKeyReleased(evt);
            }
        });

        buttonGroup1.add(rdotm);
        rdotm.setSelected(true);
        rdotm.setText("Voucher tiền mặt");
        rdotm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotmActionPerformed(evt);
            }
        });
        rdotm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdotmKeyReleased(evt);
            }
        });

        buttonGroup1.add(rdopt);
        rdopt.setText("Voucher giảm giá %");
        rdopt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(63, 63, 63)
                        .addComponent(jButton7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel12))
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                .addComponent(txtTenNV))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTienSP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTienKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTienTra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboHTTT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboMaVoucher, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(rdotm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdopt)
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTienSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(cboMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdotm)
                    .addComponent(rdopt))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txtGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(txtTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void tblSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMousePressed
        int indexHD = tblHoaDonTro.getSelectedRow();
        if (indexHD == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn !");
        } else {
            if (this.getSoLuong() == 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm này hiện đang hết hàng !");
            } else {
                if (evt.getClickCount() == 2) {
                    ImeiChiTiet imei = new ImeiChiTiet(this.layMaSPSelect(), this);
                    imei.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_tblSPMousePressed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        hdsp.themhoatro();
        showDatahoadon(hdsp.getAllHoaDon());
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked

    }//GEN-LAST:event_tblSPMouseClicked
    private void btnChonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonMousePressed
        // TODO add your handling code here:
        KhachHangRepository repository = new KhachHangRepository();
        ArrayList<KhachHang> customers = repository.getALLKH();

        BHChonKH dialog = new BHChonKH(this, banhangRepository);
        dialog.updateTable(customers);
        dialog.setVisible(true);

    }

    public void updateCustomerInfo(String phoneKH, String tenKH) {
        txtSDTKH.setText(phoneKH);
        txtNameKH.setText(tenKH);
    }

    private void saveSelectedInvoiceId() {
        int selectedRow = tblHoaDonTro.getSelectedRow();
        if (selectedRow >= 0) {
            currentInvoiceId = tblHoaDonTro.getValueAt(selectedRow, 1).toString(); // Lấy giá trị từ cột 1
            System.out.println("ID hóa đơn hiện tại: " + currentInvoiceId); // Kiểm tra giá trị ID hóa đơn
        } else {
            System.out.println("Chưa chọn dòng nào.");
        }

    }//GEN-LAST:event_btnChonMousePressed
    public String getCurrentInvoiceId() {
        // Kiểm tra xem currentInvoiceId có phải là null không
        if (currentInvoiceId == null) {
            System.out.println("Current Invoice ID is null");
        } else {
            System.out.println("Current Invoice ID: " + currentInvoiceId);
        }
        return currentInvoiceId;
    }
    private void tblHoaDonTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonTroMouseClicked
        this.showDataGioHang(hdctRepository.getAll(this.getIdHoaDonByMa(layMaHD())));
        int i = tblHoaDonTro.getSelectedRow();
        txtMaHD.setText(tblHoaDonTro.getValueAt(i, 1).toString());
        txtTenNV.setText(tblHoaDonTro.getValueAt(i, 3).toString());
        saveSelectedInvoiceId();
        banhangRepository.updateKhachHang(10, txtMaHD.getText());
    }//GEN-LAST:event_tblHoaDonTroMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.showDataTableSP(banhangRepository.getAll(getFormSearch()));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblGioHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMousePressed
        if (evt.getClickCount() == 2) {
            ImeiGioHang imeiGH = new ImeiGioHang(this.layMaSPSelectGioHang(), this);
            imeiGH.setVisible(true);
            imeiGH.ShowDataTable(this.layMaSPSelectGioHang(), getIdHDCT());
        }
    }//GEN-LAST:event_tblGioHangMousePressed

    private void txtGGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGGKeyReleased

        // Nếu radio button tiền mặt được chọn
        applyVoucher();
        updateTotal();

    }//GEN-LAST:event_txtGGKeyReleased

    private void txtTongTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongTienKeyReleased
        updateTotal();// TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienKeyReleased

    private void txtTienKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKHKeyReleased
        updateChange(); // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKHKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int i = tblHoaDonTro.getSelectedRow();
        int selectedIndex = cboHTTT.getSelectedIndex();
        Object selectedItem = cboMaVoucher.getSelectedItem();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán không");

        if (chon == JOptionPane.YES_OPTION) {
            boolean chuyenKhoan = cboHTTT.getItemAt(selectedIndex).equalsIgnoreCase("Chuyển khoản");

            if (selectedItem instanceof Vouchers) {
                Vouchers selectedVoucher = (Vouchers) selectedItem;
                int idVoucher = selectedVoucher.getIdVoucher();

                try {
                    // Sử dụng DecimalFormat để phân tích số với dấu phân cách hàng nghìn và dấu thập phân
                    DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
                    decimalFormat.setParseBigDecimal(true);

                    // Lấy số tiền từ các ô text và loại bỏ ký tự không phải số
                    String tienKhachTraText = chuyenKhoan ? "0" : txtTienKH.getText().replaceAll("[^\\d.,]", "");
                    String tongTienText = txtTongTien.getText().replaceAll("[^\\d.,]", "");

                    Number tienKhachTraNumber = decimalFormat.parse(tienKhachTraText);
                    Number tongTienNumber = decimalFormat.parse(tongTienText);

                    double tienKhachTra = tienKhachTraNumber.doubleValue();
                    double tongTien = tongTienNumber.doubleValue();

                    // Làm tròn số tiền để so sánh
                    tienKhachTra = Math.round(tienKhachTra * 100.0) / 100.0;
                    tongTien = Math.round(tongTien * 100.0) / 100.0;

                    // Đặt ngưỡng sai số nhỏ
                    double epsilon = 0.01;

                    if (chuyenKhoan) {
                        // Nếu là "Chuyển khoản", không cần kiểm tra tiền khách trả
                        int currentInvoiceId = Integer.parseInt(tblHoaDonTro.getValueAt(i, 0).toString());
                        txtTienKH.setEnabled(false);
                        String tienVoucherText = txtGG.getText().replaceAll("[^\\d.,]", "");
                        Number tienVoucherNumber = decimalFormat.parse(tienVoucherText);
                        double tienVoucher = tienVoucherNumber.doubleValue();

                        double thanhTien = tongTien - tienVoucher;
                        String phuongThucThanhToan = cboHTTT.getItemAt(selectedIndex);
                        int trangThaiThanhToan = 1;

                        boolean updated = banhangRepository.updateInvoiceStatus(this.currentInvoiceId, idVoucher, tongTien, tienVoucher, thanhTien, phuongThucThanhToan, trangThaiThanhToan);

                        if (updated) {
                            double tienCanTraLai = 0.0; // Không cần tính tiền trả lại khi chuyển khoản
                            txtTienTra.setText(decimalFormat.format(tienCanTraLai));

                            ArrayList<HoaDonTro> hoaDonList = hdsp.getAllHoaDon();

                            if (hoaDonList != null) {
                                this.showDatahoadon(hoaDonList);
                            } else {
                                JOptionPane.showMessageDialog(null, "Không thể lấy dữ liệu hóa đơn.");
                            }

                            JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                            showDatahoadon(hdsp.getAllHoaDon());
                            clearCartTable();
                            txtSDTKH.setText("Khách hàng lẻ");
                            txtNameKH.setText("Khách hàng lẻ");
                            txtMaHD.setText("");
                            txtTenNV.setText("");
                            txtTienKH.setText("");
                            txtTienTra.setText("");
                            txtTongTien.setText("");
                            txtTienSP.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật trạng thái hóa đơn.");
                        }
                    } else {
                        if (tienKhachTra + epsilon >= tongTien) {
                            int currentInvoiceId = Integer.parseInt(tblHoaDonTro.getValueAt(i, 0).toString());

                            // Lấy dữ liệu từ các ô text
                            String tienVoucherText = txtGG.getText().replaceAll("[^\\d.,]", "");
                            Number tienVoucherNumber = decimalFormat.parse(tienVoucherText);
                            double tienVoucher = tienVoucherNumber.doubleValue();

                            double thanhTien = tongTien - tienVoucher;
                            String phuongThucThanhToan = cboHTTT.getItemAt(selectedIndex);
                            int trangThaiThanhToan = 1;

                            boolean updated = banhangRepository.updateInvoiceStatus(this.currentInvoiceId, idVoucher, tongTien, tienVoucher, thanhTien, phuongThucThanhToan, trangThaiThanhToan);

                            if (updated) {
                                double tienCanTraLai = tienKhachTra - tongTien;
                                txtTienTra.setText(decimalFormat.format(tienCanTraLai));

                                ArrayList<HoaDonTro> hoaDonList = hdsp.getAllHoaDon();

                                if (hoaDonList != null) {
                                    this.showDatahoadon(hoaDonList);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Không thể lấy dữ liệu hóa đơn.");
                                }

                                JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                                showDatahoadon(hdsp.getAllHoaDon());
                                clearCartTable();
                                txtSDTKH.setText("Khách hàng lẻ");
                                txtNameKH.setText("Khách hàng lẻ");
                                txtMaHD.setText("");
                                txtTenNV.setText("");
                                txtTienKH.setText("");
                                txtTienTra.setText("");
                                txtTongTien.setText("");
                                txtTienSP.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật trạng thái hóa đơn.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Số tiền khách hàng trả không đủ!");
                        }
                    }
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ: " + e.getMessage());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Chưa chọn mã voucher hoặc không hợp lệ.");
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    private void clearCartTable() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng trong bảng
    }

    private void clearAllFields() {
        showDatahoadon(hdsp.getAllHoaDon());
        clearCartTable();
        txtSDTKH.setText("Khách hàng lẻ");
        txtNameKH.setText("Khách hàng lẻ");
        txtMaHD.setText("");
        txtTenNV.setText("");
        txtTienKH.setText("");
        txtTienTra.setText("");
        txtTongTien.setText("");
        txtTienSP.setText("");
    }

    private void handlePaymentMethodChange() {
        int selectedIndex = cboHTTT.getSelectedIndex();
        String selectedPaymentMethod = cboHTTT.getItemAt(selectedIndex);

        if (selectedPaymentMethod != null) {
            boolean isCashPayment = selectedPaymentMethod.equalsIgnoreCase("Tiền mặt");

            // Nếu chọn "Chuyển khoản", khóa ô nhập tiền khách hàng
            txtTienKH.setEnabled(isCashPayment);

            // Nếu cần, làm sạch giá trị ô nhập tiền khách hàng khi chuyển sang phương thức thanh toán khác
            if (!isCashPayment) {
                txtTienKH.setText("");
            }
        } else {
            // Xử lý trường hợp không có lựa chọn hợp lệ
            txtTienKH.setEnabled(true); // Có thể kích hoạt lại ô nhập tiền khách hàng hoặc xử lý theo yêu cầu
        }
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        txtSDTKH.setText("Khách hàng lẻ");
        txtNameKH.setText("Khách hàng lẻ");
        txtMaHD.setText("");
        txtTenNV.setText("");
        txtTienKH.setText("");
        txtTienTra.setText("");
        txtTongTien.setText("");
        txtTienSP.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cboMaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaVoucherActionPerformed
        applyVoucher();
        updateTotal();
    }//GEN-LAST:event_cboMaVoucherActionPerformed

    private void txtTienSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienSPKeyReleased
        // TODO add your handling code here:
        tongTien();
        applyVoucher();
    }//GEN-LAST:event_txtTienSPKeyReleased

    private void rdotmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotmActionPerformed
        // TODO add your handling code here:
        updateVoucherList(true); // Loại 0 cho giảm giá tiền mặt
        handleVoucherSelection();
        applyVoucher();
    }//GEN-LAST:event_rdotmActionPerformed

    private void rdoptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoptActionPerformed
        // TODO add your handling code here:
        updateVoucherList(false); // Loại 0 cho giảm giá tiền mặt
        handleVoucherSelection();
        applyVoucher();
    }//GEN-LAST:event_rdoptActionPerformed

    private void rdotmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdotmKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_rdotmKeyReleased

    private void cboHTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHTTTActionPerformed
        // TODO add your handling code here:
        handlePaymentMethodChange();
    }//GEN-LAST:event_cboHTTTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JPanel btnSearch;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<Vouchers> cboMaVoucher;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdopt;
    private javax.swing.JRadioButton rdotm;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonTro;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtGG;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNameKH;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTienKH;
    private javax.swing.JTextField txtTienSP;
    private javax.swing.JTextField txtTienTra;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
