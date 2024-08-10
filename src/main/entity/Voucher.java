/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.util.Date;

/**
 *
 * @author Windows
 */
public class Voucher {
    private String MaVoucher;
    private String NguoiTao;
    private String NgayPhatHanh;
    private String NgayHetHan;
    private int SoLuong;
    private float GiaTriVoucher;
    private float GiaTriToiDa;
    private String MoTa;
    private int Loai;
    private float DieuKien;
    private int TrangThai;
    public Voucher() {
    }

    public Voucher(String MaVoucher, String NguoiTao, String NgayPhatHanh, String NgayHetHan, int SoLuong, float GiaTriVoucher, float GiaTriToiDa, String MoTa, int Loai, float DieuKien, int TrangThai) {
        this.MaVoucher = MaVoucher;
        this.NguoiTao = NguoiTao;
        this.NgayPhatHanh = NgayPhatHanh;
        this.NgayHetHan = NgayHetHan;
        this.SoLuong = SoLuong;
        this.GiaTriVoucher = GiaTriVoucher;
        this.GiaTriToiDa = GiaTriToiDa;
        this.MoTa = MoTa;
        this.Loai = Loai;
        this.DieuKien = DieuKien;
        this.TrangThai = TrangThai;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

    public String getNgayPhatHanh() {
        return NgayPhatHanh;
    }

    public void setNgayPhatHanh(String NgayPhatHanh) {
        this.NgayPhatHanh = NgayPhatHanh;
    }

    public String getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(String NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaTriVoucher() {
        return GiaTriVoucher;
    }

    public void setGiaTriVoucher(float GiaTriVoucher) {
        this.GiaTriVoucher = GiaTriVoucher;
    }

    public float getGiaTriToiDa() {
        return GiaTriToiDa;
    }

    public void setGiaTriToiDa(float GiaTriToiDa) {
        this.GiaTriToiDa = GiaTriToiDa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getLoai() {
        return Loai;
    }

    public void setLoai(int Loai) {
        this.Loai = Loai;
    }

    public float getDieuKien() {
        return DieuKien;
    }

    public void setDieuKien(float DieuKien) {
        this.DieuKien = DieuKien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
   
    public Object[] toVoucher(){
        return new Object[]{
          MaVoucher,NguoiTao,NgayPhatHanh,NgayHetHan,SoLuong,GiaTriVoucher,GiaTriToiDa,DieuKien,Loai,MoTa,TrangThai  
        };
    }
    
}
