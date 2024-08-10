package main.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SanPham {
    private Integer IdSanPham;
    private Integer IdRam;
    private Integer IdCPU;
    private Integer IdGPU;
    private Integer IdManHinh;
    private Integer IdOCung;
    private Integer IdPin;
    private String MaSanPham;
    private String TenSanPham;
    private String HinhAnh;
    private Integer SoLuong;
    private float GiaNhap;
    private float GIaBan;
    private Integer TrangThai;

    public SanPham(Integer IdRam, Integer IdCPU, Integer IdGPU, Integer IdManHinh, Integer IdOCung, Integer IdPin, String MaSanPham, String TenSanPham, String HinhAnh, Integer SoLuong, float GiaNhap, float GIaBan, Integer TrangThai) {
        this.IdRam = IdRam;
        this.IdCPU = IdCPU;
        this.IdGPU = IdGPU;
        this.IdManHinh = IdManHinh;
        this.IdOCung = IdOCung;
        this.IdPin = IdPin;
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.HinhAnh = HinhAnh;
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GIaBan = GIaBan;
        this.TrangThai = TrangThai;
    }

    public SanPham(Integer IdRam, Integer IdCPU, Integer IdGPU, Integer IdManHinh, Integer IdOCung, Integer IdPin, String TenSanPham, String HinhAnh, Integer SoLuong, float GiaNhap, float GIaBan, Integer TrangThai) {
        this.IdRam = IdRam;
        this.IdCPU = IdCPU;
        this.IdGPU = IdGPU;
        this.IdManHinh = IdManHinh;
        this.IdOCung = IdOCung;
        this.IdPin = IdPin;
        this.TenSanPham = TenSanPham;
        this.HinhAnh = HinhAnh;
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GIaBan = GIaBan;
        this.TrangThai = TrangThai;
    }
    
}
