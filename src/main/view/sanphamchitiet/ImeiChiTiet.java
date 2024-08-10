package main.view.sanphamchitiet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.entity.Imei;
import main.repository.BanHangSPRepositories;
import main.repository.HoaDonChiTietRepository;
import main.repository.ImeiRepository;
import main.repository.SanPhamRepository;
import main.response.BanHangResponse;
import main.response.HoaDonChiTietResponse;
import main.response.SanPhamResponse;
import main.view.chucnang.BanHang;

public class ImeiChiTiet extends javax.swing.JFrame {

    private DefaultTableModel dtm;
    private SanPhamRepository sanphamRepository;
    private SanPhamResponse sanphamResponse;
    private BanHangResponse banhangResponse;
    private HoaDonChiTietResponse hdctResponse;
    private BanHang BHV;
    private BanHangSPRepositories banhangRepository;
    private HoaDonChiTietRepository hdctRepository;
    private ImeiRepository imeiRepository;
    ArrayList<String> selectedImei = new ArrayList<>();

    public ImeiChiTiet() {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Quản lý Imei");
        dtm = (DefaultTableModel) tblQuanLyImeiBH.getModel();
        sanphamRepository = new SanPhamRepository();
        sanphamResponse = new SanPhamResponse();
        hdctResponse = new HoaDonChiTietResponse();
        banhangRepository = new BanHangSPRepositories();
        hdctRepository = new HoaDonChiTietRepository();
        imeiRepository = new ImeiRepository();
        this.ShowDataTable(sanphamResponse.getMaSanPham());
    }

    public ImeiChiTiet(String maSP, BanHang banhangView) {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Quản lý Imei");
        dtm = (DefaultTableModel) tblQuanLyImeiBH.getModel();
        sanphamRepository = new SanPhamRepository();
        sanphamResponse = new SanPhamResponse();
        banhangResponse = new BanHangResponse();
        hdctResponse = new HoaDonChiTietResponse();
        banhangRepository = new BanHangSPRepositories();
        hdctRepository = new HoaDonChiTietRepository();
        imeiRepository = new ImeiRepository();
        this.ShowDataTable(maSP);
        BHV = banhangView;
    }

    private void ShowDataTable(String MaSP) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        banhangRepository.getImeiByMaSP(MaSP).forEach(x -> dtm.addRow(new Object[]{
            index.getAndIncrement(), x.getMaSanPham(), x.getImei(), false
        }));
    }

    private int getIdImei(String maImei) {
        for (int i = 0; i < tblQuanLyImeiBH.getRowCount(); i++) {
            if (tblQuanLyImeiBH.getValueAt(i, 2).equals(maImei)) {
                for (Imei imei : imeiRepository.getAll()) {
                    if (imei.getMaImei().equals(maImei)) {
                        return imei.getIdImei();
                    }
                }
            }
        }
        return -1;
    }

    private String layMaImei() {
        int index = tblQuanLyImeiBH.getSelectedRow();
        String maImei = (String) tblQuanLyImeiBH.getValueAt(index, 2);
        return maImei;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLyImeiBH = new javax.swing.JTable();
        btnSelect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblQuanLyImeiBH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblQuanLyImeiBH);

        btnSelect.setText("Chọn");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("CHỌN IMEI ĐỂ THÊM VÀO GIỎ HÀNG");

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelect)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSelect)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        selectedImei.clear();
        for (int i = 0; i < tblQuanLyImeiBH.getRowCount(); i++) {
            Boolean isChecked = (Boolean) tblQuanLyImeiBH.getValueAt(i, 3);
            if (isChecked != null && isChecked) {
                String imei = (String) tblQuanLyImeiBH.getValueAt(i, 2);
                selectedImei.add(imei);
            }
        }
        if (selectedImei.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Imei muốn bán!");
        } else {
            BHV.addGioHang(banhangRepository.getGiaBanByMa(tblQuanLyImeiBH.getValueAt(0, 1).toString()),
                    BHV.getIdByMa(BHV.layMaSPSelect()), selectedImei.size()
            );
            // lay id hdct vau them vaof
            if (!BHV.productExists) {
                int idHDCT = hdctRepository.getIDHDCT_VuaThem().get(0).getIdHoaDonChiTiet();
                // them imei cho imei db cua k cong don 
                for (String imei : selectedImei) {
                    hdctRepository.addImeiDaBan(idHDCT, imei);
                    Integer idImei = this.getIdImei(imei);
                    hdctRepository.updateTrangThaiImei(idImei);
                }
            } else {
                // them imei cho imei db cua co cong don 
                for (String imei : selectedImei) {
                    hdctRepository.addImeiDaBan(BHV.layID_HDCT, imei);
                    Integer idImei = this.getIdImei(imei);
                    hdctRepository.updateTrangThaiImei(idImei);
                }
                
            }
            int soLuongMoi = hdctRepository.getSoLuongSanPham(BHV.getIdByMa(BHV.layMaSPSelect())) - selectedImei.size();
            banhangRepository.updateSoLuong(soLuongMoi, BHV.getIdByMa(BHV.layMaSPSelect()));
            BHV.refreshDataTable();
            dispose();
        }
    }//GEN-LAST:event_btnSelectActionPerformed

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
            java.util.logging.Logger.getLogger(ImeiChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImeiChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImeiChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImeiChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImeiChiTiet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblQuanLyImeiBH;
    // End of variables declaration//GEN-END:variables
}
