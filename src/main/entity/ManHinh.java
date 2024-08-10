package main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ManHinh {
    private Integer IdManHinh;
    private String MaManHinh;
    private String KichThuoc;
    private Integer TrangThai;
}
