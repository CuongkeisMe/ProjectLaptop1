package main.form;

import javax.swing.JOptionPane;
import main.config.DBConnect;
import java.awt.Color;
import main.view.chucnang.BanHang;
import main.view.chucnang.HoaDon;
import main.view.chucnang.KhachHangView;
import main.view.chucnang.KhuyenMai;
import main.view.chucnang.NhanVienView;
import main.view.chucnang.SanPhamView;
import main.view.chucnang.TaiKhoan;
import main.view.chucnang.ThongKe;
import main.view.chucnang.TrangChu;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import main.config.DBConnect;
import main.entity.ToanCuc;

public class Menu extends javax.swing.JFrame {

    TrangChu trangchuForm = new TrangChu();
    HoaDon hoadonForm = new HoaDon();
    KhachHangView khachhangForm = new KhachHangView();
    NhanVienView nhanvienForm = new NhanVienView();
    BanHang banhangForm = new BanHang();
    KhuyenMai khuyenmaiForm = new KhuyenMai();
    TaiKhoan taikhoanForm = new TaiKhoan();
    ThongKe thongkeForm = new ThongKe();
    SanPhamView sanphamForm = new SanPhamView();
    Color ClickedColor = new Color(102, 102, 102);
    Color DefaultColor = new Color(51, 51, 51);

    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.hienthi();
        if (!checkchuadn()) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Đăng Nhập");
            System.exit(0);
        }
        hienThiForm();
    }
    
    public void hienthi() {
        String tenNhanVien = ToanCuc.getTenNhanVien();
        int vaiTro = ToanCuc.getVaiTro();
        String vaiTroText = vaiTro == 0 ? "Nhân Viên" : "Admin";
        labeltennv.setText("Tên: " + tenNhanVien);
        LabalVaiTro.setText("Vai trò: " + vaiTroText);

    }

    private boolean checkchuadn() {
        return ToanCuc.getVaiTro() != -1;
    }

    private void DefaultColorForm() {
        trangchuPanel.setBackground(DefaultColor);
        sanphamPanel.setBackground(DefaultColor);
        banhangPanel.setBackground(DefaultColor);
        khuyenmaiPanel.setBackground(DefaultColor);
        hoadonPanel1.setBackground(DefaultColor);
        khachhangPanel.setBackground(DefaultColor);
        nhanvienPanel.setBackground(DefaultColor);
        taikhoanPanel.setBackground(DefaultColor);
        thongkePanel.setBackground(DefaultColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        labeltennv = new javax.swing.JLabel();
        LabalVaiTro = new javax.swing.JLabel();
        trangchuPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sanphamPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        banhangPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        khachhangPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        khuyenmaiPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        thongkePanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        DangXuatPane = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        hoadonPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        taikhoanPanel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        nhanvienPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        mainPanel = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidePanel.setBackground(new java.awt.Color(51, 51, 51));
        sidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 21, 21));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/user-24.png"))); // NOI18N

        labeltennv.setBackground(new java.awt.Color(255, 255, 255));
        labeltennv.setForeground(new java.awt.Color(255, 255, 255));
        labeltennv.setText("jLabel12");

        LabalVaiTro.setForeground(new java.awt.Color(255, 255, 255));
        LabalVaiTro.setText("jLabel13");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labeltennv, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabalVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labeltennv, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabalVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        sidePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 70));

        trangchuPanel.setBackground(new java.awt.Color(51, 51, 51));
        trangchuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trangchuPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                trangchuPanelMousePressed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/home-24.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trang Chủ");

        javax.swing.GroupLayout trangchuPanelLayout = new javax.swing.GroupLayout(trangchuPanel);
        trangchuPanel.setLayout(trangchuPanelLayout);
        trangchuPanelLayout.setHorizontalGroup(
            trangchuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trangchuPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        trangchuPanelLayout.setVerticalGroup(
            trangchuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trangchuPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(trangchuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(trangchuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 220, 50));

        sanphamPanel.setBackground(new java.awt.Color(51, 51, 51));
        sanphamPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanphamPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sanphamPanelMousePressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/product-24.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sản Phẩm");

        javax.swing.GroupLayout sanphamPanelLayout = new javax.swing.GroupLayout(sanphamPanel);
        sanphamPanel.setLayout(sanphamPanelLayout);
        sanphamPanelLayout.setHorizontalGroup(
            sanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        sanphamPanelLayout.setVerticalGroup(
            sanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(sanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(sanphamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        banhangPanel.setBackground(new java.awt.Color(51, 51, 51));
        banhangPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banhangPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                banhangPanelMousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/buy-24.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bán Hàng");

        javax.swing.GroupLayout banhangPanelLayout = new javax.swing.GroupLayout(banhangPanel);
        banhangPanel.setLayout(banhangPanelLayout);
        banhangPanelLayout.setHorizontalGroup(
            banhangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banhangPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        banhangPanelLayout.setVerticalGroup(
            banhangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banhangPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(banhangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(banhangPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        khachhangPanel.setBackground(new java.awt.Color(51, 51, 51));
        khachhangPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khachhangPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khachhangPanelMousePressed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/conference-24.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Khách Hàng");

        javax.swing.GroupLayout khachhangPanelLayout = new javax.swing.GroupLayout(khachhangPanel);
        khachhangPanel.setLayout(khachhangPanelLayout);
        khachhangPanelLayout.setHorizontalGroup(
            khachhangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        khachhangPanelLayout.setVerticalGroup(
            khachhangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(khachhangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(khachhangPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        khuyenmaiPanel.setBackground(new java.awt.Color(51, 51, 51));
        khuyenmaiPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khuyenmaiPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khuyenmaiPanelMousePressed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/procent-badge-24.png"))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Khuyến Mại");

        javax.swing.GroupLayout khuyenmaiPanelLayout = new javax.swing.GroupLayout(khuyenmaiPanel);
        khuyenmaiPanel.setLayout(khuyenmaiPanelLayout);
        khuyenmaiPanelLayout.setHorizontalGroup(
            khuyenmaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenmaiPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        khuyenmaiPanelLayout.setVerticalGroup(
            khuyenmaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenmaiPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(khuyenmaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(khuyenmaiPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        thongkePanel.setBackground(new java.awt.Color(51, 51, 51));
        thongkePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongkePanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                thongkePanelMousePressed(evt);
            }
        });

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/accept-database-24.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Thống Kê");

        javax.swing.GroupLayout thongkePanelLayout = new javax.swing.GroupLayout(thongkePanel);
        thongkePanel.setLayout(thongkePanelLayout);
        thongkePanelLayout.setHorizontalGroup(
            thongkePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        thongkePanelLayout.setVerticalGroup(
            thongkePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkePanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(thongkePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(thongkePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 220, 50));
        sidePanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 186, 10));

        DangXuatPane.setBackground(new java.awt.Color(51, 51, 51));
        DangXuatPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatPaneMouseClicked(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/info-2-24.png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Đăng Xuất");

        javax.swing.GroupLayout DangXuatPaneLayout = new javax.swing.GroupLayout(DangXuatPane);
        DangXuatPane.setLayout(DangXuatPaneLayout);
        DangXuatPaneLayout.setHorizontalGroup(
            DangXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DangXuatPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        DangXuatPaneLayout.setVerticalGroup(
            DangXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DangXuatPaneLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(DangXuatPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(DangXuatPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 240, -1));

        hoadonPanel1.setBackground(new java.awt.Color(51, 51, 51));
        hoadonPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hoadonPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hoadonPanel1MousePressed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/list-2-24.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Hóa Đơn");

        javax.swing.GroupLayout hoadonPanel1Layout = new javax.swing.GroupLayout(hoadonPanel1);
        hoadonPanel1.setLayout(hoadonPanel1Layout);
        hoadonPanel1Layout.setHorizontalGroup(
            hoadonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoadonPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        hoadonPanel1Layout.setVerticalGroup(
            hoadonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoadonPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(hoadonPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(hoadonPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        taikhoanPanel.setBackground(new java.awt.Color(51, 51, 51));
        taikhoanPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taikhoanPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taikhoanPanelMousePressed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/access-24.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Tài Khoản");

        javax.swing.GroupLayout taikhoanPanelLayout = new javax.swing.GroupLayout(taikhoanPanel);
        taikhoanPanel.setLayout(taikhoanPanelLayout);
        taikhoanPanelLayout.setHorizontalGroup(
            taikhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taikhoanPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        taikhoanPanelLayout.setVerticalGroup(
            taikhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taikhoanPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(taikhoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(taikhoanPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, -1, -1));

        nhanvienPanel.setBackground(new java.awt.Color(51, 51, 51));
        nhanvienPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhanvienPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhanvienPanelMousePressed(evt);
            }
        });

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/user-24.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Nhân Viên");

        javax.swing.GroupLayout nhanvienPanelLayout = new javax.swing.GroupLayout(nhanvienPanel);
        nhanvienPanel.setLayout(nhanvienPanelLayout);
        nhanvienPanelLayout.setHorizontalGroup(
            nhanvienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        nhanvienPanelLayout.setVerticalGroup(
            nhanvienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(nhanvienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        sidePanel.add(nhanvienPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));
        sidePanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 673, 190, 10));

        getContentPane().add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 780));

        mainPanel.setPreferredSize(new java.awt.Dimension(1050, 800));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1083, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1083, 776));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trangchuPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trangchuPanelMouseClicked
        mainPanel.removeAll();
        mainPanel.add(trangchuForm).setVisible(true);
    }//GEN-LAST:event_trangchuPanelMouseClicked

    private void khachhangPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khachhangPanelMouseClicked
        mainPanel.removeAll();
        mainPanel.add(khachhangForm).setVisible(true);
    }//GEN-LAST:event_khachhangPanelMouseClicked

    private void thongkePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongkePanelMouseClicked
        mainPanel.removeAll();
        mainPanel.add(thongkeForm).setVisible(true);
    }//GEN-LAST:event_thongkePanelMouseClicked

    private void khuyenmaiPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenmaiPanelMouseClicked
        mainPanel.removeAll();
        mainPanel.add(khuyenmaiForm).setVisible(true);
    }//GEN-LAST:event_khuyenmaiPanelMouseClicked

    private void hoadonPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoadonPanel1MouseClicked
        hoadonForm = new HoaDon();
        mainPanel.removeAll();
        mainPanel.add(hoadonForm).setVisible(true);
    }//GEN-LAST:event_hoadonPanel1MouseClicked

    private void taikhoanPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taikhoanPanelMouseClicked
        int vaiTro = ToanCuc.getVaiTro();
        if (vaiTro == 1) {
            mainPanel.removeAll();
            mainPanel.add(taikhoanForm).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn Không Có Quyền Ở Chức Năng Này");
        }
    }//GEN-LAST:event_taikhoanPanelMouseClicked

    private void nhanvienPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvienPanelMouseClicked
        int vaiTro = ToanCuc.getVaiTro();
        if (vaiTro == 1) {
            mainPanel.removeAll();
            mainPanel.add(nhanvienForm).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn Không Có Quyền Ở Chức Năng Này");
        }
    }//GEN-LAST:event_nhanvienPanelMouseClicked

    private void DangXuatPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatPaneMouseClicked
        DangNhap dn = new DangNhap();
        dn.setVisible(true);
        dispose();
    }//GEN-LAST:event_DangXuatPaneMouseClicked

    private void sanphamPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanphamPanelMouseClicked
        sanphamForm = new SanPhamView();
        mainPanel.removeAll();
        mainPanel.add(sanphamForm).setVisible(true);
    }//GEN-LAST:event_sanphamPanelMouseClicked

    private void banhangPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banhangPanelMouseClicked
        banhangForm = new BanHang();
        mainPanel.removeAll();
        mainPanel.add(banhangForm).setVisible(true);
    }//GEN-LAST:event_banhangPanelMouseClicked

    private void trangchuPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trangchuPanelMousePressed
        this.DefaultColorForm();
        trangchuPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_trangchuPanelMousePressed

    private void sanphamPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanphamPanelMousePressed
        this.DefaultColorForm();
        sanphamPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_sanphamPanelMousePressed

    private void banhangPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banhangPanelMousePressed
        this.DefaultColorForm();
        banhangPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_banhangPanelMousePressed

    private void khachhangPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khachhangPanelMousePressed
        this.DefaultColorForm();
        khachhangPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_khachhangPanelMousePressed

    private void khuyenmaiPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenmaiPanelMousePressed
        this.DefaultColorForm();
        khuyenmaiPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_khuyenmaiPanelMousePressed

    private void thongkePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongkePanelMousePressed
        this.DefaultColorForm();
        thongkePanel.setBackground(ClickedColor);
    }//GEN-LAST:event_thongkePanelMousePressed

    private void hoadonPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoadonPanel1MousePressed
        this.DefaultColorForm();
        hoadonPanel1.setBackground(ClickedColor);
    }//GEN-LAST:event_hoadonPanel1MousePressed

    private void nhanvienPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvienPanelMousePressed
        this.DefaultColorForm();
        nhanvienPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_nhanvienPanelMousePressed

    private void taikhoanPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taikhoanPanelMousePressed
        this.DefaultColorForm();
        taikhoanPanel.setBackground(ClickedColor);
    }//GEN-LAST:event_taikhoanPanelMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DangXuatPane;
    private javax.swing.JLabel LabalVaiTro;
    private javax.swing.JPanel banhangPanel;
    private javax.swing.JPanel hoadonPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel khachhangPanel;
    private javax.swing.JPanel khuyenmaiPanel;
    private javax.swing.JLabel labeltennv;
    private javax.swing.JDesktopPane mainPanel;
    private javax.swing.JPanel nhanvienPanel;
    private javax.swing.JPanel sanphamPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel taikhoanPanel;
    private javax.swing.JPanel thongkePanel;
    private javax.swing.JPanel trangchuPanel;
    // End of variables declaration//GEN-END:variables

    private void hienThiForm() {
        mainPanel.removeAll();
        mainPanel.add(trangchuForm).setVisible(true);
        mainPanel.setBackground(ClickedColor);
    }

}
