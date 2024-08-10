/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaiKhoanRequest {

    private Integer id;
    private String maNhanVien;
    private Integer idNhanVien;
    private String userName;
    private String Pass;
    private int vaiTro;
    private int TrangThai;
    private Integer idVaiTRo;
}
