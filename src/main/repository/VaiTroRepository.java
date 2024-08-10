/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.VaiTroEntity;


public class VaiTroRepository {
    public ArrayList<VaiTroEntity> getAll(){
    ArrayList<VaiTroEntity> list = new ArrayList<>();
    String sql="""
               SELECT [id_VaiTro]
                     ,[LoaiVaiTro]
                 FROM [dbo].[VaiTro]
               """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VaiTroEntity vt = VaiTroEntity.builder()
                        .idVaiTro(rs.getInt(1))
                        .LoaiVaiTro(rs.getInt(2))
                        .build();
                list.add(vt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
