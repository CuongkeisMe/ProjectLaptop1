package main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class Imei {
    private Integer IdImei;
    private Integer IdSanPham;
    private String MaImei;
    private Integer TrangThai;

    public Imei(String MaImei) {
        this.MaImei = MaImei;
    }
    
}
