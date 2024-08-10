/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

/**
 *
 * @author Windows
 */
public class HoaDonChiTiet {
    private int id_HoaDonChiTiet;
    private String MaHoaDon;
    private String TenSanPham;
    private String MaImei;
    private int SoLuong;
    private float DonGia;
    private float TongTien;
    private float GiaTien;
    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id_HoaDonChiTiet, String MaHoaDon, String TenSanPham, String MaImei, int SoLuong, float DonGia, float TongTien, float GiaTien) {
        this.id_HoaDonChiTiet = id_HoaDonChiTiet;
        this.MaHoaDon = MaHoaDon;
        this.TenSanPham = TenSanPham;
        this.MaImei = MaImei;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.TongTien = TongTien;
        this.GiaTien = GiaTien;
    }
    
   

    public int getId_HoaDonChiTiet() {
        return id_HoaDonChiTiet;
    }

    public void setId_HoaDonChiTiet(int id_HoaDonChiTiet) {
        this.id_HoaDonChiTiet = id_HoaDonChiTiet;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getMaImei() {
        return MaImei;
    }

    public void setMaImei(String MaImei) {
        this.MaImei = MaImei;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public float getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(float GiaTien) {
        this.GiaTien = GiaTien;
    }
    
    public Object[] toTable2(){
        String MaHdCT = MaHoaDon + '_' +id_HoaDonChiTiet;
        return new Object[]{
          MaHdCT,TenSanPham,SoLuong,DonGia,TongTien  
        };
    }
    public Object[] toTable1(){
        return new Object[]{
          MaHoaDon,TenSanPham,MaImei,GiaTien  
        };
    }
}
