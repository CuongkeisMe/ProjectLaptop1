package main.view.chucnang;

import com.toedter.calendar.demo.DateChooserPanel;
import java.awt.JobAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.KhachHang;
import main.entity.NhanVien;
import main.repository.NhanVienRepository;
import main.request.FindKhachHang;
import main.request.FindNhanVien;

public class NhanVienView extends javax.swing.JInternalFrame {

    private DefaultTableModel df;
    private DefaultTableModel dfDaXoa;
    private NhanVienRepository rp;

    public NhanVienView() {
        initComponents();
        this.cauhinhForm();
        df = (DefaultTableModel) tbNhanVien.getModel();
        dfDaXoa = (DefaultTableModel) tbNhanVienDelete.getModel();
        rp = new NhanVienRepository();
//        cboTim.removeAllItems();       
//        cboTim.addItem("Họ tên");
//        cboTim.addItem("Số Điện Thoại");
//        cboTim.addItem("Giới Tính");
//        cboTim.addItem("Ma");
        this.showDataTB(rp.getAll(getFormSearch()));
        this.showDataTB2();
    }

    private void showDataTB(ArrayList<NhanVien> list) {
        df.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(s -> df.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMa(), s.getTen(),
            s.getNgaySinh(), s.isGioiTinh() ? "Nam" : "Nữ",
            s.getDiaChi(), s.getSdt(), s.getEmail(),
            s.getTrangThai() ? "Đang Làm" : "Đã Nghỉ"
        }));
    }

    private void showDataTB2() {
        dfDaXoa.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        rp.getNVDaXoa().forEach(s -> dfDaXoa.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMa(), s.getTen(),
            s.getNgaySinh(), s.isGioiTinh() ? "Nam" : "Nữ",
            s.getDiaChi(), s.getSdt(), s.getEmail(),
            s.getTrangThai() ? "Đang Làm" : "Đã Nghỉ"
        }));
    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    private FindNhanVien getFormSearch() {
        FindNhanVien fnv = new FindNhanVien();
        fnv.setKeySearch(txtTim.getText());
        return fnv;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        btThem = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        btReset = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        btTim = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNhanVienDelete = new javax.swing.JTable();
        btKhoiPhuc = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });

        jLabel2.setText("Địa chỉ ");

        jLabel3.setText("Tên nhân viên ");

        jLabel4.setText("Giới tính ");

        jLabel5.setText("Ngày sinh");

        jLabel7.setText("SDT");

        jLabel8.setText("Email");

        txtTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenFocusLost(evt);
            }
        });
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusLost(evt);
            }
        });
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        txtSdt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSdtFocusLost(evt);
            }
        });
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");
        rdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdNu);
        rdNu.setText("Nữ");
        rdNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuActionPerformed(evt);
            }
        });

        dateNgaySinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateNgaySinhFocusLost(evt);
            }
        });

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ ", "SDT", "Email", "Trạng thái"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        btThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/9035989_person_add_sharp_icon.png"))); // NOI18N
        btThem.setText("Thêm ");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/1564525_arrow_load_refresh_reload_icon.png"))); // NOI18N
        btSua.setText("Sửa");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/211836_trash_icon.png"))); // NOI18N
        btXoa.setText("Xóa");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/9104197_loading_refresh_reload_update_wait_icon.png"))); // NOI18N
        btReset.setText("Reset");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });

        txtTim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimFocusGained(evt);
            }
        });
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        btTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/9035973_search_circle_sharp_icon.png"))); // NOI18N
        btTim.setText("Tìm");
        btTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addComponent(btThem)
                                .addGap(122, 122, 122)
                                .addComponent(btSua)
                                .addGap(122, 122, 122)
                                .addComponent(btXoa)
                                .addGap(122, 122, 122)
                                .addComponent(btReset))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btTim, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThem)
                    .addComponent(btSua)
                    .addComponent(btXoa)
                    .addComponent(btReset))
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Nhân Viên Đang làm việc", jPanel2);

        tbNhanVienDelete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ ", "SDT", "Email", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tbNhanVienDelete);

        btKhoiPhuc.setText("Khôi Phục");
        btKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btKhoiPhuc)
                .addGap(84, 84, 84))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btKhoiPhuc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân Viên Đã Nghỉ Việc", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdNam)
                        .addGap(48, 48, 48)
                        .addComponent(rdNu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(574, 574, 574)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(37, 37, 37)
                            .addComponent(jLabel5))
                        .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdNam)
                    .addComponent(rdNu)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("QUẢN LÍ NHÂN VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void btTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimActionPerformed
//        String timKiemTheo = cboTim.getSelectedItem().toString();
//        
//        switch (timKiemTheo) {    
//            case "Họ tên":
//                showDataTB( rp.Tim(txtTim.getText(), "", "", ""));
//                break;
//                
//            case "Số Điện Thoại":
//                showDataTB( rp.Tim("", "", txtTim.getText() ,""));
//                break;
//                
//            case "Giới Tính":
//                String gt="0";
//                if (txtTim.getText().equalsIgnoreCase("nam")) {
//                    gt = "1";
//                }
//                showDataTB( rp.Tim("", gt, "", ""));
//                break;
//                
//            case "Ma":
//                showDataTB( rp.Tim("", "", "", txtTim.getText()));
//                break;
//        }
        this.showDataTB(rp.getAll(getFormSearch()));
    }//GEN-LAST:event_btTimActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1FocusGained

    private void txtTimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimFocusGained
        txtTim.setText("");
        showDataTB(rp.getAll(getFormSearch()));
    }//GEN-LAST:event_txtTimFocusGained

    private void txtTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenFocusLost

    }//GEN-LAST:event_txtTenFocusLost

    private void txtDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusLost

    }//GEN-LAST:event_txtDiaChiFocusLost

    private void txtSdtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSdtFocusLost

    }//GEN-LAST:event_txtSdtFocusLost

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost

    }//GEN-LAST:event_txtEmailFocusLost

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        detail(tbNhanVien.getSelectedRow());
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        if (this.isVisible()) {
            String ten = txtTen.getText();
            String regex = "^[a-zA-Z\\sàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+$";
            // chứa chữ cái và khoảng trắng
            if (ten.length() > 0) {
                if (!ten.matches(regex)) {// tên sai định dạng
                    JOptionPane.showMessageDialog(this, "Sai định dạng, Nhập lại tên,Vd: Đỗ Thị Thúy Ly");
                    txtTen.requestFocus();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tên không được để trống");
                txtTen.requestFocus();
                return;
            }
        }

        if (this.isVisible()) {
            Date selectDate = dateNgaySinh.getDate();
            Date currentDate = new Date();
            if (selectDate == null) {
                JOptionPane.showMessageDialog(this, "vui lòng chọn ngày");
                dateNgaySinh.requestFocus();
                return;
            } else if (selectDate.after(currentDate)) {
                JOptionPane.showMessageDialog(this, "Ngày đã chọn k được lớn hơn ngày hôm nay");
                dateNgaySinh.requestFocus();
                return;
            } else {
//                JOptionPane.showMessageDialog(this, "Ngày đã chọn hợp lệ ");

            }
        }
        if (this.isVisible()) {
            String diaChi = txtDiaChi.getText();
            String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠạẢảẤấẨẩẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳỴỵỶỷỸỹ\\s\\d]+$";//chứa chữ cái, số, khoảng trắng, dấu phẩy, dấu chấm, dấu gạch ngang và dấu gạch chéo.
            if (diaChi.length() > 0) {
                if (!diaChi.matches(regex)) {// địa chỉ sai định dạng
                    JOptionPane.showMessageDialog(this, "Sai định dạng, Nhập lại địa chỉ, vd: 123 Ha Noi");
                    txtDiaChi.requestFocus();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
                txtDiaChi.requestFocus();
                return;
            }
        }

        if (this.isVisible()) {
            String sdt = txtSdt.getText();
            String regex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";//chứa số điện thoại 10 số hay 11 số, đầu 09 or +84
            if (sdt.length() > 0) {
                if (!sdt.matches(regex)) {// địa chỉ sai định dạng
                    JOptionPane.showMessageDialog(this, "Sai định dạng, Nhập lại số điện thoại,VD: 0976766682 hoặc +84961659480");
                    txtSdt.requestFocus();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
                txtSdt.requestFocus();
                return;
            }
        }

        if (this.isVisible()) {
            String email = txtEmail.getText();
            String regex = "^(?=[A-Za-z0-9._%+-]{1,64}@)[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9.-]{1,255}\\.[A-Za-z]{2,63}$";//chứa chữ cái, số, khoảng trắng, dấu phẩy, dấu chấm, dấu gạch ngang và dấu gạch chéo.
            if (email.length() > 0) {
                if (!email.matches(regex)) {// địa chỉ sai định dạng
                    JOptionPane.showMessageDialog(this, "Sai định dạng, Nhập lại Email,Vd: doly862005@gmail.com");
                    txtEmail.requestFocus();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Email không được để trống");
                txtEmail.requestFocus();
                return;
            }
        }

        int chon = JOptionPane.showConfirmDialog(this, "bạn đã chắc chắn muốn thêm chưa ?");
        if (chon == 0) {
            if (rp.add(getFormData())) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                showDataTB(rp.getAll(getFormSearch()));
            }
        }
    }//GEN-LAST:event_btThemActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        int index = tbNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chua chon dong de sua");
        } else {
            if (rp.update(getFormData(), rp.getAll(getFormSearch()).get(index).getMa())) {
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                showDataTB(rp.getAll(getFormSearch()));
            } else {
                JOptionPane.showMessageDialog(this, "Sua that bai");
            }
        }
    }//GEN-LAST:event_btSuaActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        int index = tbNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chua chon dong de xoa");
        } else {
            if (rp.delete(rp.getAll(getFormSearch()).get(index).getMa())) {
                JOptionPane.showMessageDialog(this, "xoa thanh cong");
                showDataTB(rp.getAll(getFormSearch()));
                this.showDataTB2();
            } else {
                JOptionPane.showMessageDialog(this, "Xoa that bail");
            }
        }
    }//GEN-LAST:event_btXoaActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        txtTen.setText("");
        dateNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
    }//GEN-LAST:event_btResetActionPerformed

    private void btKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKhoiPhucActionPerformed
        int index = tbNhanVienDelete.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn khôi phục ? ");
        if (chon == 0) {
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Chua chon dong de khoi phuc");

            } else {
                if (rp.KhoiPhuc(rp.getNVDaXoa().get(index).getMa())) {
                    JOptionPane.showMessageDialog(this, "Khôi phục thanh công");
                    this.showDataTB2();
                    this.showDataTB(rp.getAll(getFormSearch()));
                }
            }

        }

    }//GEN-LAST:event_btKhoiPhucActionPerformed

    private void dateNgaySinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateNgaySinhFocusLost

    }//GEN-LAST:event_dateNgaySinhFocusLost

    private void detail(int index) {
        NhanVien nv = rp.getAll(getFormSearch()).get(index);
        txtTen.setText(nv.getTen());
        dateNgaySinh.setDate(nv.getNgaySinh());
        rdNam.setSelected(nv.isGioiTinh());
        rdNu.setSelected(!nv.isGioiTinh());
        txtDiaChi.setText(nv.getDiaChi());
        txtSdt.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
    }

    private NhanVien getFormData() {
        return NhanVien.builder()
                .ten(txtTen.getText())
                .ngaySinh(dateNgaySinh.getDate())
                .gioiTinh(rdNam.isSelected())
                .diaChi(txtDiaChi.getText())
                .sdt(txtSdt.getText())
                .email(txtEmail.getText())
                .build();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btKhoiPhuc;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btTim;
    private javax.swing.JButton btXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTable tbNhanVienDelete;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
