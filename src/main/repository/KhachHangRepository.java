
package main.repository;

import java.util.ArrayList;
import main.entity.KhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import main.config.DBConnect;
import main.request.FindKhachHang;
import main.response.KhachHangResponse;

public class KhachHangRepository {
    public ArrayList<KhachHang> getAll(FindKhachHang fkh){
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = """
                     SELECT 
                           [MaKhachHang]
                           ,[HoTen]
                           ,[NgaySinh]
                           ,[GioiTinh]
                           ,[SDT]
                           ,[Email]
                           ,[DiaChi]
                           ,[TrangThai]
                       FROM [dbo].[KhachHang]
                     WHERE [TrangThai] = 1
                     AND (
                        [MaKhachHang] LIKE ?
                        OR [HoTen] LIKE ?
                        OR [NgaySinh] LIKE ?
                        OR [GioiTinh] LIKE ?
                        OR [SDT] LIKE ?
                        OR [Email] LIKE ?
                        OR [DiaChi] LIKE ?
                     )
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setObject(1, "%" + fkh.getKeySearch() + "%");
            ps.setObject(2, "%" + fkh.getKeySearch() + "%");
            ps.setObject(3, "%" + fkh.getKeySearch() + "%");
            ps.setObject(4, "%" + fkh.getKeySearch() + "%");
            ps.setObject(5, "%" + fkh.getKeySearch() + "%");
            ps.setObject(6, "%" + fkh.getKeySearch() + "%");
            ps.setObject(7, "%" + fkh.getKeySearch() + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHang kh = KhachHang.builder()
                        .ma(rs.getString(1))
                        .ten(rs.getString(2))
                        .ngaySinh(rs.getDate(3))
                        .gioiTinh(rs.getBoolean(4))
                        .sdt(rs.getString(5))
                        .email(rs.getString(6))
                        .diaChi(rs.getString(7))
                        .trangThai(rs.getBoolean(8))
                        .build();
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public ArrayList<KhachHang> getALLKH() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = """
                     SELECT id_KhachHang
                           ,[MaKhachHang]
                           ,[HoTen]
                           
                           ,[SDT]
                           
                       FROM [dbo].[KhachHang]
                     
                     
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = KhachHang.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .sdt(rs.getString(4))
                        .build();
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<KhachHangResponse> getLSGD(FindKhachHang fls){
        ArrayList<KhachHangResponse> listLSGD = new ArrayList<>();
        String sql = """
                    SELECT    dbo.KhachHang.MaKhachHang, dbo.HoaDon.MaHoaDon, dbo.KhachHang.HoTen, dbo.KhachHang.SDT, dbo.KhachHang.DiaChi, dbo.HoaDon.NgayThanhToan, dbo.HoaDon.TongTien, dbo.HoaDon.TrangThai
                     FROM     dbo.HoaDon INNER JOIN
                              dbo.KhachHang ON dbo.HoaDon.id_KhachHang = dbo.KhachHang.id_KhachHang
                     where 
                          (dbo.KhachHang.MaKhachHang like ?
                           or dbo.HoaDon.MaHoaDon like ?
                           or dbo.KhachHang.HoTen like ?
                           or dbo.KhachHang.SDT like ?
                           or dbo.KhachHang.DiaChi like ?
                           or dbo.HoaDon.NgayThanhToan like ?
                           or dbo.HoaDon.TongTien like ? )
                     
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setObject(1, '%'+ fls.getKeySearch2() +'%');
            ps.setObject(2, '%'+ fls.getKeySearch2() +'%');
            ps.setObject(3, '%'+ fls.getKeySearch2() +'%');
            ps.setObject(4, '%'+ fls.getKeySearch2() +'%');
            ps.setObject(5, '%'+ fls.getKeySearch2() +'%');
            ps.setObject(6, '%'+ fls.getKeySearch2() +'%');
            ps.setObject(7, '%'+ fls.getKeySearch2() +'%');
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHangResponse khs = KhachHangResponse.builder()
                        .maKh(rs.getString(1))
                        .maHd(rs.getString(2))
                        .hoTen(rs.getString(3))
                        .sdt(rs.getString(4))
                        .diaChi(rs.getString(5))
                        .ngayThanhToan(rs.getDate(6))
                        .tongTien(rs.getInt(7))
                        .build();
                listLSGD.add(khs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLSGD;
    }
    
    public Boolean add(KhachHang kh){
        String sql = """
                     INSERT INTO [dbo].[KhachHang]
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
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getNgaySinh());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    
    public Boolean delete(String ma){
        String sql = """
                     UPDATE [dbo].[KhachHang]
                        SET [TrangThai] = 0
                      WHERE [MaKhachHang] = ?
                     """;
        int check = 0; 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check >0;
    }
    
    public Boolean update(KhachHang kh,String ma){
        String sql = """
                     UPDATE [dbo].[KhachHang]
                        SET [HoTen] = ?
                           ,[NgaySinh] = ?
                           ,[GioiTinh] = ?
                           ,[SDT] = ?
                           ,[Email] = ?
                           ,[DiaChi] = ?
                      WHERE [MaKhachHang] = ?
                     """;
        int check = 0; 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getNgaySinh());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getDiaChi());
            ps.setObject(7, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
}
