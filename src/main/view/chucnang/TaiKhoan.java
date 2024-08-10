package main.view.chucnang;

import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.NhanVien;
import main.repository.NhanVienRepository;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import main.entity.TaiKhoanEtity;
import main.entity.VaiTroEntity;
import main.repository.TaiKhoanRepository;
import main.repository.VaiTroRepository;
import main.request.TaiKhoanRequest;

public class TaiKhoan extends javax.swing.JInternalFrame {

    private DefaultTableModel df;
    private DefaultTableModel dfkhd;
    private TaiKhoanRepository tkrp;
    private NhanVienRepository nvrp;
    private VaiTroRepository vt;

    public TaiKhoan() {

        initComponents();
        this.cauhinhForm();
        df = (DefaultTableModel) tblViewTaiKhoan.getModel();
        dfkhd = (DefaultTableModel) tblTaiKoanKHD.getModel();
        tkrp = new TaiKhoanRepository();
        nvrp = new NhanVienRepository();
        vt = new VaiTroRepository();
        this.showdataTb(tkrp.getAll());
        this.showdataTbkhd(tkrp.getAlltk());
        this.loadCbb(nvrp.getAllNVKTK());
        this.loadCbbvt(vt.getAll());

    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    private void showdataTb(ArrayList<main.entity.TaiKhoanEtity> list) {
        df.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            main.entity.TaiKhoanEtity tk = list.get(i);
            df.addRow(new Object[]{
                tk.getId(),
                tk.getMaNhanVien(),
                tk.getUserName(),
                tk.getPass(),
                tk.getVaiTro() == 0 ? "Nhân Viên" : "Admin",
                tk.getTrangThai() == 1 ? "Đang Hoạt Động" : "Ngừng Hoạt Động",});
        }

    }

    private void showdataTbkhd(ArrayList<main.entity.TaiKhoanEtity> list) {
        dfkhd.setRowCount(0);
        list.forEach(s -> dfkhd.addRow(new Object[]{
            s.getId(),
            s.getMaNhanVien(),
            s.getUserName(),
            s.getPass(),
            s.getVaiTro() == 0 ? "Nhân Viên" : "Admin",
            s.getTrangThai() == 1 ? "Đang Hoạt Động" : "Ngừng Hoạt Động",}));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaKhau = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbovaitro = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbomanv = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTimkiem = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViewTaiKhoan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTaiKoanKHD = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tên Tài Khoản");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Mật Khẩu");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Vai trò");

        cbovaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Chọn Mã Nhân Viên Sử Dụng TK");

        cbomanv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbomanv, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(cbomanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbovaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbovaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tìm Kiếm");

        txtTimkiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimkiemFocusGained(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnSua)
                        .addGap(40, 40, 40)
                        .addComponent(btnXoa)
                        .addGap(42, 42, 42)
                        .addComponent(btnLamMoi)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Quản lý Tài Khoản");

        tblViewTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã Nhân Viên", "Tên Tài Khoản", "Mật Khẩu", "Vai Trò", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblViewTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblViewTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblViewTaiKhoan);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 320, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài Khoản Đang Hoạt Động", jPanel4);

        tblTaiKoanKHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã Nhân Viên", "Tên Tài Khoản", "Mật Khẩu ", "Vai Trò", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKoanKHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKoanKHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTaiKoanKHD);

        btnKhoiPhuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnKhoiPhuc.setText("Khôi Phục Tài Khoản");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKhoiPhuc)
                .addGap(0, 454, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài Khoản Ngừng Hoạt Động", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Sửa Không");
        if (chon == 0) {

            int index = tblViewTaiKhoan.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Chưa Chọn Dòng Để Sửa");
            } else {
                if (tkrp.updatetk(getformdata(), tkrp.getAll().get(index).getId())) {
                    JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                    showdataTb(tkrp.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Thất Bại");
                }
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        int chon = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Làm Mới Không");
        if (chon == 0) {

            txtMaKhau.setText("");
            txtTenTaiKhoan.setText("");
            txtTimkiem.setText("");
            buttonGroup1.clearSelection();
            buttonGroup2.clearSelection();
            cbomanv.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblViewTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblViewTaiKhoanMouseClicked
        detail(tblViewTaiKhoan.getSelectedRow());
    }//GEN-LAST:event_tblViewTaiKhoanMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không");
        if (chon == 0) {
            int index = tblViewTaiKhoan.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Dòng");
            } else {
                if (tkrp.deletetk(tkrp.getAll().get(index).getId())) {
                    JOptionPane.showMessageDialog(this, "Xóa Thành Công");
                    showdataTb(tkrp.getAll());
                    showdataTbkhd(tkrp.getAlltk());
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String keyword = txtTimkiem.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.");
            return;
        }

        ArrayList<TaiKhoanEtity> resultList = tkrp.timKiem(keyword);
        if (resultList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
        } else {
            showdataTb(resultList);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimkiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimkiemFocusGained
        txtTimkiem.setText("");
        showdataTb(tkrp.getAll());

    }//GEN-LAST:event_txtTimkiemFocusGained

    private void tblTaiKoanKHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKoanKHDMouseClicked
        detailtkkhd(tblTaiKoanKHD.getSelectedRow());
    }//GEN-LAST:event_tblTaiKoanKHDMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Thêm Hay Không ?");
        if (chon == 0) {
            if (!checktrong()) {
                TaiKhoanRequest tk = getformdata();
                boolean success = tkrp.addtk(tk);
                if (success) {
                    showdataTb(tkrp.getAll());
                    JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                    this.loadCbb(nvrp.getAllNVKTK());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed

        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Khôi Phục Không");
        if (chon == 0) {
            int index = tblTaiKoanKHD.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Dòng");
            } else {
                if (tkrp.khoiphuc(tkrp.getAlltk().get(index).getId())) {
                    JOptionPane.showMessageDialog(this, "Khôi Phục Thành Công");
                    showdataTb(tkrp.getAll());
                    showdataTbkhd(tkrp.getAlltk());
                } else {
                    JOptionPane.showMessageDialog(this, "Khôi Phục Thất Bại");
                }
            }
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed
    private void detail(int index) {
        main.entity.TaiKhoanEtity tk = tkrp.getAll().get(index);
        txtTenTaiKhoan.setText(tk.getUserName());
        txtMaKhau.setText(tk.getPass());
        int trangThai = tk.getTrangThai();
        int vaiTro = tk.getVaiTro();

        // Cập nhật ComboBox vai trò
        String vaiTroText = vaiTro == 0 ? "Nhân Viên" : "Admin";
        cbovaitro.setSelectedItem(vaiTroText);

        // Cập nhật ComboBox mã nhân viên
        cbomanv.setSelectedItem(tk.getMaNhanVien());

    }

    private void detailtkkhd(int index) {
        main.entity.TaiKhoanEtity tk = tkrp.getAll().get(index);
        txtTenTaiKhoan.setText(tk.getUserName());
        txtMaKhau.setText(tk.getPass());
        int trangThai = tk.getTrangThai();
        int vaiTro = tk.getVaiTro();
        cbovaitro.setSelectedItem(String.valueOf(tk.getVaiTro())); // Chọn VaiTro trong ComboBox
        cbomanv.setSelectedItem(tk.getMaNhanVien());
    }

    public void loadCbb(ArrayList<NhanVien> list) {
        cbomanv.removeAllItems();
        for (NhanVien x : list) {
            cbomanv.addItem(x.getMa());
        }
    }

    public void loadCbbvt(ArrayList<VaiTroEntity> list) {
        cbovaitro.removeAllItems();
        for (VaiTroEntity x : list) {
            String vaiTro;
            if (x.getIdVaiTro() == 1) {
                vaiTro = "Nhân Viên";
                cbovaitro.addItem(vaiTro);
            } else if (x.getIdVaiTro() == 2) {
                vaiTro = "Admin";
                cbovaitro.addItem(vaiTro);
            }

        }
    }

    private TaiKhoanRequest getformdata() {
        TaiKhoanRequest nvrepuest = new TaiKhoanRequest();
        nvrepuest.setUserName(txtTenTaiKhoan.getText());
        nvrepuest.setPass(txtMaKhau.getText());
        int index = cbovaitro.getSelectedIndex();
        nvrepuest.setIdVaiTRo(vt.getAll().get(index).getIdVaiTro());
        int indexManv = cbomanv.getSelectedIndex();
        String maNhanVien = (String) cbomanv.getSelectedItem();
        for (NhanVien nv : nvrp.getAll()) {
            if (nv.getMa().equals(maNhanVien)) {
                nvrepuest.setIdNhanVien(nv.getId());
                break;
            }
        }
        return nvrepuest;
    }

    private boolean checktrong() {
        if (txtTenTaiKhoan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Tài Khoản");
            txtTenTaiKhoan.requestFocus();
            return true;
        } else if (txtMaKhau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Mật Khẩu ");
            txtMaKhau.requestFocus();
            return true;
        }
//        if (buttonGroup1.getSelection() == null) {
//            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Chức Vụ Người Dùng");
//            rdoAdmin.requestFocus();
//            return true;
//        }
        if (cbomanv.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tất Cả Nhân Viên Đã Có tài Khoản");
            cbomanv.requestFocus();
            return true;
        } else if (cbomanv.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Mã Nhân Viên Sở Hữu Tài Khoản");
            cbomanv.requestFocus();
            return true;
        }
        return false;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbomanv;
    private javax.swing.JComboBox<String> cbovaitro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblTaiKoanKHD;
    private javax.swing.JTable tblViewTaiKhoan;
    private javax.swing.JTextField txtMaKhau;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
