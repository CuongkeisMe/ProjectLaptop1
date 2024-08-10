package main.repository;

import main.config.DBConnect;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import main.entity.ThongKe;
import main.request.FindTKSanPham;

public class ThongKeDTRepositories {

    public ArrayList<ThongKe> getAll(FindTKSanPham fsp) {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = """
                  SELECT 
                                          HoaDon.MaHoaDon AS MaHD,
                                          NhanVien.MaNhanVien AS MaNV,
                                          NhanVien.HoTen AS TenNV,
                                          HoaDon.NgayThanhToan AS NgayThanhToan,
                                          HoaDon.TongTien AS TongTien
                                    FROM 
                                          HoaDon
                                    JOIN 
                                          NhanVien ON HoaDon.id_NhanVien = NhanVien.id_NhanVien
                                    WHERE 
                                          (HoaDon.MaHoaDon LIKE ? 
                                          OR NhanVien.MaNhanVien LIKE ? 
                                          OR NhanVien.HoTen LIKE ?)
                                     AND TrangThaiThanhToan =1;
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + fsp.getKeySearchDT() + "%");
            ps.setObject(2, "%" + fsp.getKeySearchDT() + "%");
            ps.setObject(3, "%" + fsp.getKeySearchDT() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = ThongKe.builder()
                        .maHD(rs.getString(1))
                        .maNV(rs.getString(2))
                        .tenNV(rs.getString(3))
                        .ngayTT(rs.getDate(4))
                        .tongTien(rs.getFloat(5))
                        .build();
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ThongKe> searchDateDT(FindTKSanPham fsp) {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = """
        SELECT 
            HoaDon.MaHoaDon AS MaHD,
            NhanVien.MaNhanVien AS MaNV,
            NhanVien.HoTen AS TenNV,
            HoaDon.NgayThanhToan AS NgayThanhToan,
            HoaDon.TongTien AS TongTien
        FROM 
            HoaDon
        JOIN 
            NhanVien ON HoaDon.id_NhanVien = NhanVien.id_NhanVien
        WHERE 
            HoaDon.NgayThanhToan BETWEEN ? AND ?
    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Chuyển đổi java.util.Date thành java.sql.Date
            java.sql.Date startSqlDate = new java.sql.Date(fsp.getStartDate().getTime());
            java.sql.Date endSqlDate = new java.sql.Date(fsp.getEndDate().getTime());

            // Thiết lập giá trị cho các tham số ngày
            ps.setDate(1, startSqlDate);
            ps.setDate(2, endSqlDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = ThongKe.builder()
                        .maHD(rs.getString("MaHD"))
                        .maNV(rs.getString("MaNV"))
                        .tenNV(rs.getString("TenNV"))
                        .ngayTT(rs.getDate("NgayThanhToan"))
                        .tongTien(rs.getFloat("TongTien"))
                        .build();
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ThongKe ThongKeChart(int month, int year) {
        String sql = """
                  SELECT 
                                          SUM(hd.ThanhTien) AS TongTienHang,
                                          MONTH(hd.NgayThanhToan) AS Thang
                                      FROM HoaDon hd
                                      JOIN HoaDonChiTiet hdct ON hd.id_HoaDon = hdct.id_HoaDon
                                      WHERE MONTH(hd.NgayThanhToan) = ?
                                        AND YEAR(hd.NgayThanhToan) = ?
                                      GROUP BY MONTH(hd.NgayThanhToan);
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, month);
            ps.setObject(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setDoanhThu(rs.getFloat(1));
                tk.setNgayTao(rs.getInt(2));
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Object[]> listYear() {
        String sql = """
                  	    				  SELECT DISTINCT YEAR(hd.NgayThanhToan) AS NamTao
                            FROM HoaDon hd
                            JOIN HoaDonChiTiet hdct ON hd.id_HoaDon = hdct.id_HoaDon;
                   """;
        String cols[] = {"NamTao"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }



}
