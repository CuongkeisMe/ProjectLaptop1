package main.repository;

import java.util.ArrayList;
import main.entity.NhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import main.config.DBConnect;
import main.request.FindNhanVien;

public class NhanVienRepository {

    public ArrayList<NhanVien> getAll(FindNhanVien fnv) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                        SELECT 
                           [MaNhanVien]
                           ,[HoTen]
                           ,[NgaySinh]
                           ,[GioiTinh]
                           ,[SDT]
                           ,[Email]
                           ,[DiaChi]
                           ,[TrangThai]
                       FROM [dbo].[NhanVien] 
                     WHERE [TrangThai] = 1
                     AND(
                     [MaNhanVien] like ?
                     OR [HoTen] like ?
                     OR [NgaySinh] like ?
                     OR [GioiTinh] like ?
                     OR [SDT] like ?
                     OR [Email] like ?
                     OR [DiaChi] like ?
                     OR [TrangThai] like ?
                     )
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(2, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(3, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(4, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(5, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(6, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(7, '%'+ fnv.getKeySearch() +'%');
            ps.setObject(8, '%'+ fnv.getKeySearch() +'%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .ma(rs.getString(1))
                        .ten(rs.getString(2))
                        .ngaySinh(rs.getDate(3))
                        .gioiTinh(rs.getBoolean(4))
                        .sdt(rs.getString(5))
                        .email(rs.getString(6))
                        .diaChi(rs.getString(7))
                        .trangThai(rs.getBoolean(8))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public ArrayList<NhanVien> getNVDaXoa() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                        SELECT 
                           [MaNhanVien]
                           ,[HoTen]
                           ,[NgaySinh]
                           ,[GioiTinh]
                           ,[SDT]
                           ,[Email]
                           ,[DiaChi]
                           ,[TrangThai]
                       FROM [dbo].[NhanVien] 
                     WHERE [TrangThai] = 0
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .ma(rs.getString(1))
                        .ten(rs.getString(2))
                        .ngaySinh(rs.getDate(3))
                        .gioiTinh(rs.getBoolean(4))
                        .sdt(rs.getString(5))
                        .email(rs.getString(6))
                        .diaChi(rs.getString(7))
                        .trangThai(rs.getBoolean(8))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                        SELECT [id_NhanVien]
                           ,[MaNhanVien]
                           ,[HoTen]
                           ,[NgaySinh]
                           ,[GioiTinh]
                           ,[SDT]
                           ,[Email]
                           ,[DiaChi]
                           ,[TrangThai]
                       FROM [dbo].[NhanVien] 
                     WHERE [TrangThai] = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .ngaySinh(rs.getDate(4))
                        .gioiTinh(rs.getBoolean(5))
                        .sdt(rs.getString(6))
                        .email(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .trangThai(rs.getBoolean(9))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    public Boolean add(NhanVien nv) {
        String sql = """
                        INSERT INTO [dbo].[NhanVien]
                                   ([HoTen]
                                   ,[NgaySinh]
                                   ,[GioiTinh]
                                   ,[SDT]
                                   ,[Email]
                                   ,[DiaChi]
                                   ,[TrangThai])
                             VALUES(?,?,?,?,?,?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getTen());
            ps.setObject(2, nv.getNgaySinh());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getSdt());
            ps.setObject(5, nv.getEmail());
            ps.setObject(6, nv.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean delete(String ma) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [TrangThai] = 0
                      WHERE [MaNhanVien] = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
        public Boolean KhoiPhuc (String ma) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [TrangThai] = 1
                      WHERE [MaNhanVien] = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
        public Boolean update(NhanVien nv,String ma) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [HoTen] = ?
                           ,[NgaySinh] = ?
                           ,[GioiTinh] = ?
                           ,[SDT] = ?
                           ,[Email] = ?
                           ,[DiaChi] = ?
                      WHERE [MaNhanVien] = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getTen());
            ps.setObject(2, nv.getNgaySinh());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getSdt());
            ps.setObject(5, nv.getEmail());
            ps.setObject(6, nv.getDiaChi());
            ps.setObject(7, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
        
//        public ArrayList<NhanVien> Tim(String hoten, String gioiTinh,String sdt, String ma ) {
//        ArrayList<NhanVien> list = new ArrayList<>();
//        String sql = """
//                                          SELECT [id_NhanVien]
//                                                ,[MaNhanVien]
//                                                ,[HoTen]
//                                                ,[NgaySinh]
//                                                ,[GioiTinh]
//                                                ,[SDT]
//                                                ,[Email]
//                                                ,[DiaChi]
//                                                ,[TrangThai]
//                                            FROM [dbo].[NhanVien]
//                                          WHERE [TrangThai] = 1 
//                                        and [HoTen] like '%'+?+'%'  
//                                        and [GioiTinh] like '%'+?+'%' 
//                                        and [SDT] like '%'+?+'%' 
//                                        and [MaNhanVien] like '%'+?+'%'
//                                        """;
//        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, hoten);
//            ps.setObject(2, gioiTinh);
//            ps.setObject(3, sdt);
//            ps.setObject(4, ma);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                NhanVien nv = NhanVien.builder()
//                        .id(rs.getInt(1))
//                        .ma(rs.getString(2))
//                        .ten(rs.getString(3))
//                        .ngaySinh(rs.getDate(4))
//                        .gioiTinh(rs.getBoolean(5))
//                        .sdt(rs.getString(6))
//                        .email(rs.getString(7))
//                        .diaChi(rs.getString(8))
//                        .trangThai(rs.getBoolean(9))
//                        .build();
//                list.add(nv);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

        public ArrayList<NhanVien> Tim(String hoten, String gioiTinh,String sdt, String ma ) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                                          SELECT [id_NhanVien]
                                                ,[MaNhanVien]
                                                ,[HoTen]
                                                ,[NgaySinh]
                                                ,[GioiTinh]
                                                ,[SDT]
                                                ,[Email]
                                                ,[DiaChi]
                                                ,[TrangThai]
                                            FROM [dbo].[NhanVien]
                                          WHERE [TrangThai] = 1 
                                        and [HoTen] like '%'+?+'%'  
                                        and [GioiTinh] like '%'+?+'%' 
                                        and [SDT] like '%'+?+'%' 
                                        and [MaNhanVien] like '%'+?+'%'
                                        """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoten);
            ps.setObject(2, gioiTinh);
            ps.setObject(3, sdt);
            ps.setObject(4, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .ngaySinh(rs.getDate(4))
                        .gioiTinh(rs.getBoolean(5))
                        .sdt(rs.getString(6))
                        .email(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .trangThai(rs.getBoolean(9))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        public ArrayList<NhanVien> getAllNVKTK() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = """
                       SELECT nv.id_NhanVien,
                               nv.MaNhanVien,
                               nv.HoTen,
                               nv.NgaySinh,
                               nv.GioiTinh,
                               nv.SDT,
                               nv.Email,
                               nv.DiaChi,
                               nv.TrangThai
                        FROM NhanVien nv
                        LEFT JOIN TaiKhoan tk ON nv.id_NhanVien = tk.id_NhanVien
                        WHERE tk.id_NhanVien IS NULL;
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = NhanVien.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .ngaySinh(rs.getDate(4))
                        .gioiTinh(rs.getBoolean(5))
                        .sdt(rs.getString(6))
                        .email(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .trangThai(rs.getBoolean(9))
                        .build();
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
