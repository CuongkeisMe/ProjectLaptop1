package main.entity;

import lombok.Builder;

@Builder

public class TaiKhoanEtity {

    private Integer id;
    private Integer idNhanVien;
    private int idvaitro;
    private String userName;
    private String Pass;
    private int vaiTro;
    private int TrangThai;
    private String maNhanVien;
    private String tenNhanVien;

    public TaiKhoanEtity() {
    }

    public TaiKhoanEtity(Integer id, Integer idNhanVien, int idvaitro, String userName, String Pass, int vaiTro, int TrangThai, String maNhanVien, String tenNhanVien) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idvaitro = idvaitro;
        this.userName = userName;
        this.Pass = Pass;
        this.vaiTro = vaiTro;
        this.TrangThai = TrangThai;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdvaitro() {
        return idvaitro;
    }

    public void setIdvaitro(int idvaitro) {
        this.idvaitro = idvaitro;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

 

}
