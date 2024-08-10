package main.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SanPhamRequest {
    private Integer IdRam;
    private Integer IdCPU;
    private Integer IdGPU;
    private Integer IdManHinh;
    private Integer IdOCung;
    private Integer IdPin;
    private String TenSanPham;
    private String HinhAnh;
    private Integer SoLuong;
    private Integer GiaNhap;
    private Integer GiaBan;
    private Integer TrangThai;
}
