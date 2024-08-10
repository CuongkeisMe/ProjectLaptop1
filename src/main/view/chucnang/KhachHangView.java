package main.view.chucnang;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import main.entity.KhachHang;
import main.repository.KhachHangRepository;
import main.request.FindKhachHang;
import main.response.KhachHangResponse;

public class KhachHangView extends javax.swing.JInternalFrame {

    private DefaultTableModel df;
    private KhachHangRepository rp;
    private DefaultTableModel dtmLichSu;

    private void showDataTB(ArrayList<KhachHang> list) {
        df.setRowCount(0);// xoa tat ca cac hang hien tai trong bang 
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(s -> df.addRow(new Object[]{
                        index.getAndIncrement(),
            s.getMa(), s.getTen(), s.getNgaySinh(), s.isGioiTinh() ? "Nam" : "Nữ",
            s.getSdt(), s.getEmail(), s.getDiaChi(), s.getTrangThai()?"Khách quen" : "Khách vãng lai"
        }));
    }
    
    private void showDataTableLichSu(ArrayList<KhachHangResponse> list){
        dtmLichSu.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(x -> dtmLichSu.addRow(new Object[]{
            index.getAndIncrement(), x.getMaKh(), x.getMaHd(), x.getHoTen(), x.getSdt(), x.getDiaChi(), x.getNgayThanhToan(), x.getTongTien()
        }));
    }

    private FindKhachHang getFormSearch(){
        FindKhachHang fkh = new FindKhachHang();
        fkh.setKeySearch(txtTimKH.getText());
        return fkh;
    }
    
    private FindKhachHang getFormSearch2(){
        FindKhachHang fls = new FindKhachHang();
        fls.setKeySearch2(txtTimLichSu.getText());
        return fls;
    }
    
    public KhachHangView() {
        initComponents();
        this.cauhinhForm();
        df = (DefaultTableModel) tbKhachHang.getModel();
        dtmLichSu = (DefaultTableModel) tbLichSuGiaoDich.getModel();
        rp = new KhachHangRepository();
        this.showDataTB(rp.getAll(getFormSearch()));
        this.showDataTableLichSu(rp.getLSGD(getFormSearch2()));
    }

    public void cauhinhForm() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    public void detail(int index) {
        KhachHang kh = rp.getAll(getFormSearch()).get(index);
        txtTen.setText(kh.getTen());
        dateNgaySinh.setDate(kh.getNgaySinh());
        rdNam.setSelected(kh.isGioiTinh());
        rdNu.setSelected(!kh.isGioiTinh());
        txtSdt.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());

    }

    private KhachHang getFormData() {
        return KhachHang.builder()
                .ten(txtTen.getText())
                .ngaySinh(dateNgaySinh.getDate())
                .gioiTinh(rdNam.isSelected())
                .sdt(txtSdt.getText())
                .email(txtEmail.getText())
                .diaChi(txtDiaChi.getText())
                .build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        btTimKH = new javax.swing.JButton();
        txtTimKH = new javax.swing.JTextField();
        btThem = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        btReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLichSuGiaoDich = new javax.swing.JTable();
        txtTimLichSu = new javax.swing.JTextField();
        btTimLichSu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtTen = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rdNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "stt", "mã KH", "tên KH", "Ngày sinh ", "Giới tính ", "Sdt ", "email", "Địa chỉ ", "Trạng thái"
            }
        ));
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachHang);

        btTimKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/9035973_search_circle_sharp_icon.png"))); // NOI18N
        btTimKH.setText("Tìm");
        btTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKHActionPerformed(evt);
            }
        });

        btThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/211872_person_add_icon.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btTimKH)
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(btThem)
                .addGap(111, 111, 111)
                .addComponent(btSua)
                .addGap(125, 125, 125)
                .addComponent(btXoa)
                .addGap(113, 113, 113)
                .addComponent(btReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThem)
                    .addComponent(btSua)
                    .addComponent(btXoa)
                    .addComponent(btReset))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Thông tin khách hàng ", jPanel1);

        tbLichSuGiaoDich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Mã HD", "Tên KH", "Sdt", "Địa chỉ ", "Ngày thanh toán ", "Tổng tiền "
            }
        ));
        jScrollPane2.setViewportView(tbLichSuGiaoDich);

        btTimLichSu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btTimLichSu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/icon/9035973_search_circle_sharp_icon.png"))); // NOI18N
        btTimLichSu.setText("Tìm");
        btTimLichSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimLichSuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 650, Short.MAX_VALUE)
                .addComponent(txtTimLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btTimLichSu)
                .addGap(58, 58, 58))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTimLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Lịch sử giao dịch ", jPanel2);

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên khách hàng");

        jLabel3.setText("SDT ");

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        jLabel4.setText("Giới tính ");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Email");

        jLabel7.setText("Địa chỉ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("QUẢN LÍ KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addGap(60, 60, 60)
                                .addComponent(rdNu))
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addGap(94, 94, 94)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdNam)
                    .addComponent(rdNu)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        detail(tbKhachHang.getSelectedRow());
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKHActionPerformed
        this.showDataTB(rp.getAll(getFormSearch()));
    }//GEN-LAST:event_btTimKHActionPerformed

    private void btTimLichSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimLichSuActionPerformed
       this.showDataTableLichSu(rp.getLSGD(getFormSearch2()));
    }//GEN-LAST:event_btTimLichSuActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

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
        
        if (this.isVisible()) {
            String diaChi = txtDiaChi.getText();
            String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠạẢảẤấẨẩẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳỴỵỶỷỸỹ\\s\\d]+$";//chứa chữ cái, số, khoảng trắng, dấu phẩy, dấu chấm, dấu gạch ngang và dấu gạch chéo.
            if (diaChi.length() > 0) {
                if (!diaChi.matches(regex)) {// địa chỉ sai định dạng
                    JOptionPane.showMessageDialog(this, "Sai định dạng, Nhập lại địa chỉ,vd: 123 Ha Noi");
                    txtDiaChi.requestFocus();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
                txtDiaChi.requestFocus();
                return;
            }
        }
        
        int chon = JOptionPane.showConfirmDialog(this, "Ban có chắc muốn thêm?");
        if (chon == 0) {
            rp.add(getFormData());
            JOptionPane.showMessageDialog(this, "thêm thành công");
            showDataTB(rp.getAll(getFormSearch()));
        }
    }//GEN-LAST:event_btThemActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        int index = tbKhachHang.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?");
        if (chon == 0) {
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Chua chon dong de xoa");
            } else {
                if (rp.delete(rp.getAll(getFormSearch()).get(index).getMa())) {
                    JOptionPane.showMessageDialog(this, "Xoa thanh cong");
                    showDataTB(rp.getAll(getFormSearch()));
                }
            }
        }

    }//GEN-LAST:event_btXoaActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        txtTen.setText("");
        dateNgaySinh.setDate(null);
        txtSdt.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }//GEN-LAST:event_btResetActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        int index = tbKhachHang.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa?");
        if (chon == 0) {
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
        }
    }//GEN-LAST:event_btSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btTimKH;
    private javax.swing.JButton btTimLichSu;
    private javax.swing.JButton btXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTable tbLichSuGiaoDich;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JTextField txtTimLichSu;
    // End of variables declaration//GEN-END:variables
}
