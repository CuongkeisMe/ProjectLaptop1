package main.repository;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.KhachHang;
import main.entity.NhanVien;
import main.entity.TaiKhoanEtity;
import main.entity.ToanCuc;
import main.request.TaiKhoanRequest;

import main.view.chucnang.TaiKhoan;

public class TaiKhoanRepository {

    public ArrayList<main.entity.TaiKhoanEtity> getAll() {
        ArrayList<main.entity.TaiKhoanEtity> list = new ArrayList<>();
        String sql = """
                            SELECT 
                                                 tk.id_TaiKhoan, 
                                                 tk.UserName, 
                                                 tk.Pass, 
                                                 vt.LoaiVaiTro, 
                                                 tk.TrangThai, 
                                                 nv.id_NhanVien,  
                                                 nv.MaNhanVien,
                                                 nv.HoTen
                                             FROM dbo.TaiKhoan tk
                                             left   JOIN dbo.VaiTro vt ON tk.id_VaiTro = vt.id_VaiTro
                                             left   JOIN dbo.NhanVien nv ON tk.id_NhanVien = nv.id_NhanVien
                        			 where tk.TrangThai=1;
                    
   
               """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                main.entity.TaiKhoanEtity tk = main.entity.TaiKhoanEtity.builder()
                        .id(rs.getInt("id_TaiKhoan"))
                        .idNhanVien(rs.getInt("id_NhanVien"))
                        .userName(rs.getString("UserName"))
                        .Pass(rs.getString("Pass"))
                        .vaiTro(rs.getInt("LoaiVaiTro")) // ánh xạ với LoaiVaiTro từ bảng VaiTro
                        .TrangThai(rs.getInt("TrangThai"))
                        .maNhanVien(rs.getString("MaNhanVien"))
                        .tenNhanVien(rs.getString("HoTen"))
                        .build();
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<main.entity.TaiKhoanEtity> getAlltk() {
        ArrayList<main.entity.TaiKhoanEtity> list = new ArrayList<>();
        String sql = """
                     SELECT 
                         tk.id_TaiKhoan, 
                         tk.UserName, 
                         tk.Pass, 
                         vt.LoaiVaiTro, 
                         tk.TrangThai, 
                         nv.id_NhanVien,  
                         nv.MaNhanVien,
                         nv.HoTen
                     FROM 
                         dbo.TaiKhoan tk
                         LEFT JOIN dbo.VaiTro vt 
                             ON tk.id_VaiTro = vt.id_VaiTro
                         LEFT JOIN dbo.NhanVien nv 
                             ON tk.id_NhanVien = nv.id_NhanVien
                     WHERE 
                         tk.TrangThai = 0;
               """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                main.entity.TaiKhoanEtity tk = main.entity.TaiKhoanEtity.builder()
                        .id(rs.getInt("id_TaiKhoan"))
                        .idNhanVien(rs.getInt("id_NhanVien"))
                        .userName(rs.getString("UserName"))
                        .Pass(rs.getString("Pass"))
                        .vaiTro(rs.getInt("LoaiVaiTro")) // ánh xạ với LoaiVaiTro từ bảng VaiTro
                        .TrangThai(rs.getInt("TrangThai"))
                        .maNhanVien(rs.getString("MaNhanVien"))
                        .tenNhanVien(rs.getString("HoTen"))
                        .build();
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean addtk(TaiKhoanRequest tk) {
        String sql = """
                       INSERT INTO [dbo].[TaiKhoan]
                                  ([id_Nhanvien]
                                  ,[id_VaiTro]
                                  ,[UserName]
                                  ,[Pass]
                                  ,[TrangThai])
                            VALUES
                            
                                  (?,?,?,?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tk.getIdNhanVien());
            ps.setObject(2, tk.getIdVaiTRo());
            ps.setObject(3, tk.getUserName());
            ps.setObject(4, tk.getPass());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean deletetk(Integer id) {
        String sql = """
                     UPDATE [dbo].[TaiKhoan]
                        SET [TrangThai] = 0
                      WHERE id_TaiKhoan=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean khoiphuc(Integer id) {
        String sql = """
                     UPDATE [dbo].[TaiKhoan]
                        SET [TrangThai] = 1
                      WHERE id_TaiKhoan=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    public Boolean updatetk(TaiKhoanRequest tk, Integer id) {
        String sql = """
                       UPDATE [dbo].[TaiKhoan]
                                               SET 
                                                  [id_VaiTro] = ?
                                                  ,[UserName] = ?
                                                  ,[Pass] = ?
                                                  
                                             WHERE id_TaiKhoan=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tk.getIdVaiTRo());
            ps.setObject(2, tk.getUserName());
            ps.setObject(3, tk.getPass());
            ps.setObject(4, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<TaiKhoanEtity> timKiem(String keyword) {
        ArrayList<TaiKhoanEtity> list = new ArrayList<>();
        String sql = """
                 SELECT tk.id_TaiKhoan, tk.id_Nhanvien, tk.id_VaiTro, tk.UserName, tk.Pass, tk.TrangThai,
                                            nv.MaNhanVien, nv.HoTen
                                     FROM TaiKhoan tk
                                     LEFT JOIN NhanVien nv ON tk.id_NhanVien = nv.id_NhanVien
                                     WHERE tk.UserName LIKE ? 
                                        OR nv.MaNhanVien LIKE ? 
                                        OR nv.HoTen LIKE ? 
                                        OR tk.Pass LIKE ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoanEtity tk = TaiKhoanEtity.builder()
                        .id(rs.getInt("id_TaiKhoan"))
                        .idNhanVien(rs.getInt("id_Nhanvien"))
                        .idvaitro(rs.getInt("id_VaiTro"))
                        .userName(rs.getString("UserName"))
                        .Pass(rs.getString("Pass"))
                        .TrangThai(rs.getInt("TrangThai"))
                        .maNhanVien(rs.getString("MaNhanVien"))
                        .tenNhanVien(rs.getString("HoTen"))
                        .build();
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
