package main.response;

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

public class BanHangResponse {
    // Bang san pham
    private Integer idSanPham;
    private Integer idImei;
    private String maSanPham;
    private String tenSanPham;
    private String tenCPU;
    private String tenGPU;
    private String loaiOCung;
    private String dungluongRam;
    private String kichThuoc;
    private String dungluongPin;
    private float giaBan;
    private Integer soLuong;
    private String ImeiDaBan;
    private String Imei;
    private Integer idHDCT;
    private Integer TrangThai;
}
