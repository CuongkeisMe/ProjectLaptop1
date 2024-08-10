package main.view.sanphamchitiet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.repository.BanHangSPRepositories;
import main.repository.HoaDonChiTietRepository;
import main.repository.SanPhamRepository;
import main.response.SanPhamResponse;
import main.view.chucnang.BanHang;

public class ImeiGioHang extends javax.swing.JFrame {

    private DefaultTableModel dtm;
    private BanHangSPRepositories banhangRepository;
    private HoaDonChiTietRepository hoadonchitietRepository;
    private SanPhamRepository sanphamRepository;
    private SanPhamResponse sanphamResponse;
    private BanHang BHV;
    ArrayList<String> deletedImei = new ArrayList<>();

    public ImeiGioHang() {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Quản lý Imei giỏ hàng");
        dtm = (DefaultTableModel) tblImeiGioHang.getModel();
        banhangRepository = new BanHangSPRepositories();
        hoadonchitietRepository = new HoaDonChiTietRepository();
        sanphamRepository = new SanPhamRepository();
        sanphamResponse = new SanPhamResponse();
        
    }

    public ImeiGioHang(String maSP, BanHang banHangView) {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Quản lý Imei giỏ hàng");
        dtm = (DefaultTableModel) tblImeiGioHang.getModel();
        banhangRepository = new BanHangSPRepositories();
        hoadonchitietRepository = new HoaDonChiTietRepository();
        sanphamRepository = new SanPhamRepository();
        sanphamResponse = new SanPhamResponse();
       
        BHV = banHangView;
    }

    public void ShowDataTable(String MaSP , Integer IDHDCT) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        banhangRepository.getImeiDaBanByMaSP(MaSP , IDHDCT).forEach(x -> dtm.addRow(new Object[]{
            index.getAndIncrement(), x.getMaSanPham(), x.getImeiDaBan(), false
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblImeiGioHang = new javax.swing.JTable();
        btnXoa1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblImeiGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Imei", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblImeiGioHang);

        btnXoa1.setText("Xóa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("CHỌN IMEI ĐỂ XÓA KHỎI GIỎ HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 322, Short.MAX_VALUE)
                        .addComponent(btnXoa1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa1)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        deletedImei.clear();
        for (int i = tblImeiGioHang.getRowCount() - 1; i >= 0; i--) {
            Boolean isChecked = (Boolean) tblImeiGioHang.getValueAt(i, 3);
            if (isChecked != null && isChecked) {
                String imeiXoa = (String) tblImeiGioHang.getValueAt(i, 2);
                deletedImei.add(imeiXoa);
            }
        }
        if (deletedImei.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn IMEI muốn xóa khỏi giỏ hàng!");
            return;
        }
        try {
            for (String imei : deletedImei) {
                hoadonchitietRepository.xoaImeiDaBan(imei);
                hoadonchitietRepository.updateTrangThaiImei(imei);
            }
            int soLuongGioHang = hoadonchitietRepository.getSoLuongGioHang(BHV.getIdHoaDonByMa(BHV.layMaHD()), BHV.getIdByMa(BHV.layMaSPSelectGioHang())) - deletedImei.size();
//          float giaBan = banhangRepository.getGiaBanByMa(BHV.layMaSPSelectGioHang());
            hoadonchitietRepository.updateSoLuongGioHang(soLuongGioHang, BHV.getIdHDCT());
            int idSanPham = BHV.getIdByMa(BHV.layMaSPSelectGioHang());
            int soLuongSanPham = hoadonchitietRepository.getSoLuongSanPham(BHV.getIdByMa(BHV.layMaSPSelectGioHang()));
            banhangRepository.updateSoLuong(soLuongSanPham + deletedImei.size(), idSanPham);
            if (soLuongGioHang == 0) {
                hoadonchitietRepository.deleteHDCT(BHV.getIdHDCT());  
            }
            JOptionPane.showMessageDialog(this, "Xóa khỏi giỏ hàng thành công !");
            BHV.refreshDataTable();
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa IMEI: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

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
            java.util.logging.Logger.getLogger(ImeiGioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImeiGioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImeiGioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImeiGioHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImeiGioHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoa1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblImeiGioHang;
    // End of variables declaration//GEN-END:variables
}
