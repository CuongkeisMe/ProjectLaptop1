package main.entity;

import java.util.Date;
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

public class HoaDonTro {
    private Integer idHoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private String maNhanVien;
    private Integer tinhTrangThanhToan;
    private Integer tinhTrang;
}
