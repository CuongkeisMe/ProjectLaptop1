package main.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class KhachHangResponse {
    private String maKh; 
    private String maHd;
    private String hoTen; 
    private String sdt; 
    private String diaChi;
    private Date ngayThanhToan; 
    private Integer tongTien;
}
