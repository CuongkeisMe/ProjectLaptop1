/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;
import java.util.ArrayList;
import main.entity.Voucher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.config.DBConnect;

/**
 *
 * @author Windows
 */
public class VoucherRepository {
    public ArrayList<Voucher> getAll(){
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "SELECT \n" +
                    "    MaVoucher AS 'MaVoucher',\n" +
                    "    NhanVien.HoTen AS 'HoTen',\n" +
                    "    FORMAT(Voucher.NgayPhatHanh, 'dd-MM-yyyy') AS 'NgayPhatHanh',\n" +
                    "    FORMAT(Voucher.NgayHetHan, 'dd-MM-yyyy') AS 'NgayHetHan',\n" +
                    "    Voucher.SoLuong AS 'SoLuong',\n" +
                    "    Voucher.MucGiaTri AS 'MucGiaTri',\n" +
                    "    Voucher.GiaTriToiDa AS 'GiaTriToiDa',\n" +
                    "    Voucher.LoaiVoucher AS 'Loai',\n" +
                    "    Voucher.DieuKien AS 'DieuKien',\n" +
                    "    Voucher.MoTa AS 'MoTa',\n" +
                    "    Voucher.TrangThaiHoatDong AS 'TrangThai'\n" +
                    "FROM \n" +
                    "    Voucher\n" +
                    "JOIN \n" +
                    "    NhanVien ON NhanVien.id_NhanVien = Voucher.id_NhanVien Where Voucher.TrangThai=1;";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVoucher(rs.getString("MaVoucher"));
                vc.setNguoiTao(rs.getString("HoTen"));
                vc.setNgayPhatHanh(rs.getString("NgayPhatHanh")); // Changed to getString to match the formatted date
                vc.setNgayHetHan(rs.getString("NgayHetHan")); // Changed to getString to match the formatted date
                vc.setSoLuong(rs.getInt("SoLuong"));
                vc.setGiaTriVoucher(rs.getFloat("MucGiaTri"));
                vc.setGiaTriToiDa(rs.getFloat("GiaTriToiDa"));
                vc.setLoai(rs.getInt("Loai"));
                vc.setDieuKien(rs.getFloat("DieuKien"));
                vc.setMoTa(rs.getString("MoTa"));
                vc.setTrangThai(rs.getInt("TrangThai"));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    }
   public boolean addVoucher(Voucher vc,int id_NhanVien) {
        String sql = "INSERT INTO Voucher (id_NhanVien, MaVoucher, LoaiVoucher, MoTa, NgayPhatHanh, NgayHetHan, SoLuong, MucGiaTri, GiaTriToiDa, DieuKien,TrangThaiHoatDong) VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
        try (Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_NhanVien);
            ps.setString(2, vc.getMaVoucher());
            ps.setInt(3, vc.getLoai());
            ps.setString(4, vc.getMoTa());
            java.sql.Date ngayPhatHanh = java.sql.Date.valueOf(vc.getNgayPhatHanh());
            java.sql.Date ngayHetHan = java.sql.Date.valueOf(vc.getNgayHetHan());
            ps.setDate(5, ngayPhatHanh);
            ps.setDate(6, ngayHetHan);
            ps.setInt(7, vc.getSoLuong());
            ps.setFloat(8, vc.getGiaTriVoucher());
            ps.setFloat(9, vc.getGiaTriToiDa());
            ps.setFloat(10, vc.getDieuKien());
            ps.setFloat(11, vc.getTrangThai());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
   public boolean deleteVoucher(String MaVoucher){
       String sql ="UPDATE Voucher SET  Voucher.TrangThai = 0 WHERE MaVoucher = ?;";
       try {
           Connection con = DBConnect.getConnection();
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, MaVoucher);
           ps.execute();
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
       return true;
   }
   public ArrayList<Voucher> getOn() {
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "SELECT " +
                     "    MaVoucher AS 'MaVoucher', " +
                     "    NhanVien.HoTen AS 'HoTen', " +
                     "    FORMAT(Voucher.NgayPhatHanh, 'dd-MM-yyyy') AS 'NgayPhatHanh', " +
                     "    FORMAT(Voucher.NgayHetHan, 'dd-MM-yyyy') AS 'NgayHetHan', " +
                     "    Voucher.SoLuong AS 'SoLuong', " +
                     "    Voucher.MucGiaTri AS 'MucGiaTri', " +
                     "    Voucher.GiaTriToiDa AS 'GiaTriToiDa', " +
                     "    Voucher.LoaiVoucher AS 'Loai', " +
                     "    Voucher.DieuKien AS 'DieuKien', " +
                     "    Voucher.MoTa AS 'MoTa', " +
                     "    Voucher.TrangThaiHoatDong AS 'TrangThai' " +
                     "FROM " +
                     "    Voucher " +
                     "JOIN " +
                     "    NhanVien ON NhanVien.id_NhanVien = Voucher.id_NhanVien " +
                     "WHERE " +
                     "    Voucher.TrangThaiHoatDong=1 and Voucher.TrangThai=1;";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVoucher(rs.getString("MaVoucher"));
                vc.setNguoiTao(rs.getString("HoTen"));
                vc.setNgayPhatHanh(rs.getString("NgayPhatHanh"));
                vc.setNgayHetHan(rs.getString("NgayHetHan"));
                vc.setSoLuong(rs.getInt("SoLuong"));
                vc.setGiaTriVoucher(rs.getFloat("MucGiaTri"));
                vc.setGiaTriToiDa(rs.getFloat("GiaTriToiDa"));
                vc.setLoai(rs.getInt("Loai"));
                vc.setDieuKien(rs.getFloat("DieuKien"));
                vc.setMoTa(rs.getString("MoTa"));
                vc.setTrangThai(rs.getInt("TrangThai"));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    }
   public ArrayList<Voucher> getOff() {
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "SELECT " +
                     "    MaVoucher AS 'MaVoucher', " +
                     "    NhanVien.HoTen AS 'HoTen', " +
                     "    FORMAT(Voucher.NgayPhatHanh, 'dd-MM-yyyy') AS 'NgayPhatHanh', " +
                     "    FORMAT(Voucher.NgayHetHan, 'dd-MM-yyyy') AS 'NgayHetHan', " +
                     "    Voucher.SoLuong AS 'SoLuong', " +
                     "    Voucher.MucGiaTri AS 'MucGiaTri', " +
                     "    Voucher.GiaTriToiDa AS 'GiaTriToiDa', " +
                     "    Voucher.LoaiVoucher AS 'Loai', " +
                     "    Voucher.DieuKien AS 'DieuKien', " +
                     "    Voucher.MoTa AS 'MoTa', " +
                     "    Voucher.TrangThaiHoatDong AS 'TrangThai' " +
                     "FROM " +
                     "    Voucher " +
                     "JOIN " +
                     "    NhanVien ON NhanVien.id_NhanVien = Voucher.id_NhanVien " +
                     "WHERE " +
                     "    Voucher.TrangThaiHoatDong=0 and Voucher.TrangThai=1;";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVoucher(rs.getString("MaVoucher"));
                vc.setNguoiTao(rs.getString("HoTen"));
                vc.setNgayPhatHanh(rs.getString("NgayPhatHanh"));
                vc.setNgayHetHan(rs.getString("NgayHetHan"));
                vc.setSoLuong(rs.getInt("SoLuong"));
                vc.setGiaTriVoucher(rs.getFloat("MucGiaTri"));
                vc.setGiaTriToiDa(rs.getFloat("GiaTriToiDa"));
                vc.setLoai(rs.getInt("Loai"));
                vc.setDieuKien(rs.getFloat("DieuKien"));
                vc.setMoTa(rs.getString("MoTa"));
                vc.setTrangThai(rs.getInt("TrangThai"));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    }
   public ArrayList<Voucher> search(String text) {
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "exec sp_TimKiemVoucher ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, text);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Voucher vc = new Voucher();
                    vc.setMaVoucher(rs.getString("MaVoucher"));
                    vc.setNguoiTao(rs.getString("HoTen"));
                    vc.setNgayPhatHanh(rs.getString("NgayPhatHanh")); 
                    vc.setNgayHetHan(rs.getString("NgayHetHan")); 
                    vc.setSoLuong(rs.getInt("SoLuong"));
                    vc.setGiaTriVoucher(rs.getFloat("MucGiaTri"));
                    vc.setGiaTriToiDa(rs.getFloat("GiaTriToiDa"));
                    vc.setLoai(rs.getInt("Loai"));
                    vc.setDieuKien(rs.getFloat("DieuKien"));
                    vc.setMoTa(rs.getString("MoTa"));
                    vc.setTrangThai(rs.getInt("TrangThai"));
                    list.add(vc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return list;
    }
    public ArrayList<Voucher> getRemove(){
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "SELECT " +
             "    MaVoucher AS 'MaVoucher', " +
             "    NhanVien.HoTen AS 'HoTen', " +
             "    FORMAT(Voucher.NgayPhatHanh, 'dd-MM-yyyy') AS 'NgayPhatHanh', " +
             "    FORMAT(Voucher.NgayHetHan, 'dd-MM-yyyy') AS 'NgayHetHan', " +
             "    Voucher.SoLuong AS 'SoLuong', " +
             "    Voucher.MucGiaTri AS 'MucGiaTri', " +
             "    Voucher.GiaTriToiDa AS 'GiaTriToiDa', " +
             "    Voucher.LoaiVoucher AS 'Loai', " +
             "    Voucher.DieuKien AS 'DieuKien', " +
             "    Voucher.MoTa AS 'MoTa', " +
             "    Voucher.TrangThaiHoatDong AS 'TrangThai' " +
             "FROM Voucher " +
             "JOIN NhanVien ON NhanVien.id_NhanVien = Voucher.id_NhanVien " +
             "WHERE Voucher.TrangThai = 0;";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVoucher(rs.getString("MaVoucher"));
                vc.setNguoiTao(rs.getString("HoTen"));
                vc.setNgayPhatHanh(rs.getString("NgayPhatHanh")); 
                vc.setNgayHetHan(rs.getString("NgayHetHan")); 
                vc.setSoLuong(rs.getInt("SoLuong"));
                vc.setGiaTriVoucher(rs.getFloat("MucGiaTri"));
                vc.setGiaTriToiDa(rs.getFloat("GiaTriToiDa"));
                vc.setLoai(rs.getInt("Loai"));
                vc.setDieuKien(rs.getFloat("DieuKien"));
                vc.setMoTa(rs.getString("MoTa"));
                vc.setTrangThai(rs.getInt("TrangThai"));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    }
    public boolean updateVoucher(Voucher vc) {
        String sql = "UPDATE Voucher SET LoaiVoucher=?, MoTa=?, NgayPhatHanh=?, NgayHetHan=?, SoLuong=?, MucGiaTri=?, GiaTriToiDa=?, DieuKien=?, TrangThaiHoatDong=? WHERE MaVoucher=?";
        try (Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, vc.getLoai());
            ps.setString(2, vc.getMoTa());
            java.sql.Date ngayPhatHanh = java.sql.Date.valueOf(vc.getNgayPhatHanh());
            java.sql.Date ngayHetHan = java.sql.Date.valueOf(vc.getNgayHetHan());
            ps.setDate(3, ngayPhatHanh);
            ps.setDate(4, ngayHetHan);
            ps.setInt(5, vc.getSoLuong());
            ps.setFloat(6, vc.getGiaTriVoucher());
            ps.setFloat(7, vc.getGiaTriToiDa());
            ps.setFloat(8, vc.getDieuKien());
            ps.setFloat(9, vc.getTrangThai());
            ps.setString(10, vc.getMaVoucher());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
        return true;
    }
}
