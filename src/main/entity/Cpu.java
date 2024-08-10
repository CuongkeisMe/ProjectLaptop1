package main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString

public class Cpu {
    private Integer IdCPU;
    private String MaCPU;
    private String TenCPU;
    private Integer TrangThai;
}
