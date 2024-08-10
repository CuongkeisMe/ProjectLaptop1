
package main.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NhanVien {
    private Integer id; 
    private String ma;
    private String ten;
    private Date ngaySinh; 
    private boolean gioiTinh;  
    private String sdt; 
    private String email; 
    private String diaChi;
    private Boolean trangThai; 
    
}
