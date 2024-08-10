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

public class HoaDonChiTietResponse {
    private Integer idHoaDonChiTiet;
    private Integer idHoaDon;
    private Integer idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private Integer soLuong;
    private float giaBan;
    private float tongTien;
}
