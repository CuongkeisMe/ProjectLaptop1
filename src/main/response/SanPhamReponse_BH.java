
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
public class SanPhamReponse_BH {
    private int idSanPham; 
    private String maSanPham; 
    private String tenSanPham; 
    private int soLuong;
    private long giaBan; 
    private int trangThai;  
    private int tongImeiSP;
   
    private int idImei;
    private String MaImei;
    private int trangThaiImei;
    // imei đã bán và hdct 
    private int idHoaDonChiTiet; 
    private int idImeiDaBan; 
    private String maImeiDaBan;
   
    private int idHoaDon ;
}
