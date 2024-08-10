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

public class OCung {
    private Integer IdOCung;
    private String MaOCung;
    private String LoaiOCung;
    private Integer TrangThai;
}
