/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import main.config.DBConnect;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.entity.ThongKe;
import main.request.FindTKSanPham;

/**
 *
 * @author Admin
 */
public class ThongKeKHRepositories {

    public ArrayList<ThongKe> getAll(FindTKSanPham fsp) {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = "SELECT KhachHang.MaKhachHang AS 'MaKH', KhachHang.HoTen AS 'TenKH', "
                + "COUNT(HoaDon.id_HoaDon) AS 'SoLuongMua', SUM(HoaDon.ThanhTien) AS 'TongTienThanhToan' "
                + "FROM HoaDon "
                + "JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang "
                + "WHERE KhachHang.MaKhachHang LIKE ? OR KhachHang.HoTen LIKE ? "
                + "GROUP BY KhachHang.MaKhachHang, KhachHang.HoTen";;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + fsp.getKeySearchKh() + "%");
            ps.setObject(2, "%" + fsp.getKeySearchKh() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = ThongKe.builder()
                        .maKH(rs.getString(1))
                        .tenKH(rs.getString(2))
                        .soLuongMH(rs.getInt(3))
                        .tongTienTT(rs.getFloat(4))
                        .build();
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
