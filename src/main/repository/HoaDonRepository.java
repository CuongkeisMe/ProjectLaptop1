/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.util.ArrayList;
import main.entity.HoaDonEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import main.config.DBConnect;
import main.entity.HoaDonChiTiet;
import main.entity.HoaDonTro;
import main.entity.ToanCuc;

/**
 *
 * @author Windows
 */
public class HoaDonRepository {
    public ArrayList<HoaDonEntity> getAll() {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql ="SELECT \n" +
                        "    HoaDon.MaHoaDon as 'MaHoaDon',\n" +
                        "    KhachHang.HoTen as 'kh',\n" +
                        "    Voucher.MaVoucher as 'MaVoucher',\n" +
                        "    NhanVien.HoTen as 'nv',\n" +
                        "    FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') as 'NgayThanhToan',\n" +
                        "    HoaDon.TongTien as 'TongTien',\n" +
                        "    HoaDon.TienVoucher as 'TienVoucher',\n" +
                        "    HoaDon.ThanhTien as 'ThanhTien',\n" +
                        "    HoaDon.PhuongThucThanhToan as 'PhuongThuc',\n" +
                        "    HoaDon.GhiChu as 'GhiChu',\n" +
                        "    HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'\n" +
                        "FROM \n" +
                        "    HoaDon\n" +
                        "JOIN \n" +
                        "    KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang\n" +
                        "JOIN \n" +
                        "    Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher\n" +
                        "JOIN \n" +
                        "    NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien where HoaDon.TrangThai=1;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv")); // Corrected to retrieve 'nv' from ResultSet
                hd.setNgayThanhToan(rs.getString("NgayThanhToan")); // Retrieve as a string
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);

            }
        } catch (Exception e) {
                e.printStackTrace(); // Added for debugging purposes
                return null;
            }
            return list;
    }
    public ArrayList<HoaDonChiTiet> getHdct(String MaHoaDon) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql = " SELECT DISTINCT\n" +
                         "    HoaDonChiTiet.id_HDCT,\n" +
                         "    HoaDon.MaHoaDon,\n" +
                         "    SanPham.TenSanPham,\n" +
                         "    HoaDonChiTiet.SoLuong,\n" +
                         "    HoaDonChiTiet.DonGia,\n" +
                         "    HoaDonChiTiet.TongTien\n" +
                         "FROM \n" +
                         "    HoaDonChiTiet\n" +
                         "INNER JOIN \n" +
                         "    HoaDon ON HoaDonChiTiet.id_HoaDon = HoaDon.id_HoaDon\n" +
                         "INNER JOIN \n" +
                         "    SanPham ON HoaDonChiTiet.id_SanPham = SanPham.id_SanPham\n" +
                         "WHERE \n" +
                         "    HoaDon.MaHoaDon = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaHoaDon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setId_HoaDonChiTiet(rs.getInt("id_HDCT"));
                hdct.setMaHoaDon(rs.getString("MaHoaDon"));
                hdct.setTenSanPham(rs.getString("TenSanPham"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getFloat("DonGia"));
                hdct.setTongTien(rs.getFloat("TongTien"));
                list.add(hdct);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
        return list;
    }

    public ArrayList<HoaDonEntity> TimKiem(String text) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "{CALL timKiemVanBan(?)}";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, text); // Set the parameter before executing
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv")); 
                hd.setNgayThanhToan(rs.getString("NgayThanhToan")); 
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Added for debugging purposes
            return null;
        }
        return list;
    }
    public ArrayList<HoaDonEntity> TimKiemTheoKhoang(Date NgayBatDau, Date NgayKetThuc) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnect.getConnection();
            String sql = "{CALL timKiemTheoKhoangNgay(?,?)}";
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(NgayBatDau.getTime()));
            ps.setDate(2, new java.sql.Date(NgayKetThuc.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv"));
                hd.setNgayThanhToan(rs.getString("NgayThanhToan"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Added for debugging purposes
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public ArrayList<HoaDonEntity> TimKiemTheoPhuongThuc(String phuongThuc) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "{CALL timKiemTheoCbo(?)}";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phuongThuc);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv"));
                hd.setNgayThanhToan(rs.getString("NgayThanhToan"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Added for debugging purposes
            return null;
        }
        return list;
    }
    public ArrayList<HoaDonEntity> TimKiemTheoPhuongThucVaNgay(String phuongThuc, Date NgayBatDau, Date NgayKetThuc) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement("{CALL timKiemTheoCboVaNgay(?,?,?)}")) {
            ps.setDate(1, new java.sql.Date(NgayBatDau.getTime()));
            ps.setDate(2, new java.sql.Date(NgayKetThuc.getTime()));
            ps.setString(3, phuongThuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonEntity hd = new HoaDonEntity();
                    hd.setMaHoaDon(rs.getString("MaHoaDon"));
                    hd.setTenKhachHang(rs.getString("kh"));
                    hd.setMaVoucher(rs.getString("MaVoucher"));
                    hd.setTenNhanVien(rs.getString("nv"));
                    hd.setNgayThanhToan(rs.getString("NgayThanhToan"));
                    hd.setTongTien(rs.getDouble("TongTien"));
                    hd.setTienVoucher(rs.getFloat("TienVoucher"));
                    hd.setThanhTien(rs.getFloat("ThanhTien"));
                    hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                    hd.setGhiChu(rs.getString("GhiChu"));
                    hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                    list.add(hd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Consider logging instead of printing
            return null; // Optionally, return an empty list instead
        }
        return list;
    }

    public ArrayList<HoaDonEntity> timKiemTheoVanBanNgay(String text, Date startDate, Date endDate) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBConnect.getConnection();
            String sql = "{CALL timKiemTheoVanBanNgay(?, ?, ?)}";
            ps = con.prepareStatement(sql);

            // Set parameters
            ps.setString(1, text);
            ps.setDate(2, startDate != null ? new java.sql.Date(startDate.getTime()) : null);
            ps.setDate(3, endDate != null ? new java.sql.Date(endDate.getTime()) : null);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv"));
                hd.setNgayThanhToan(rs.getString("NgayThanhToan")); // Ensure correct column name
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Added for debugging purposes
        } finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public ArrayList<HoaDonEntity> timKiemVanBanVaPhuongThuc(String text, String pt) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBConnect.getConnection();
            String sql = "{CALL timKiemVanBanVaPhuongThuc(?, ?)}";
            ps = con.prepareStatement(sql);
            // Set parameters
            ps.setString(1, text);
            ps.setString(2, pt);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv"));
                hd.setNgayThanhToan(rs.getString("NgayThanhToan")); // Ensure correct column name
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Added for debugging purposes
        } finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public ArrayList<HoaDonEntity> timKiemAll(String text, String pt, Date startDate, Date endDate) {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBConnect.getConnection();
            String sql = "{CALL timKiemAll(?, ?, ?, ?)}";
            ps = con.prepareStatement(sql);
            ps.setString(1, text);
            ps.setString(2, pt);
            ps.setDate(3, startDate != null ? new java.sql.Date(startDate.getTime()) : null);
            ps.setDate(4, endDate != null ? new java.sql.Date(endDate.getTime()) : null);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv"));
                hd.setNgayThanhToan(rs.getString("NgayThanhToan"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public ArrayList<HoaDonEntity> getRemove() {
        ArrayList<HoaDonEntity> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "SELECT " +
                         "    HoaDon.MaHoaDon as 'MaHoaDon', " +
                         "    KhachHang.HoTen as 'kh', " +
                         "    Voucher.MaVoucher as 'MaVoucher', " +
                         "    NhanVien.HoTen as 'nv', " +
                         "    FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') as 'NgayThanhToan', " +
                         "    HoaDon.TongTien as 'TongTien', " +
                         "    HoaDon.TienVoucher as 'TienVoucher', " +
                         "    HoaDon.ThanhTien as 'ThanhTien', " +
                         "    HoaDon.PhuongThucThanhToan as 'PhuongThuc', " +
                         "    HoaDon.GhiChu as 'GhiChu', " +
                         "    HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan' " +
                         "FROM " +
                         "    HoaDon " +
                         "JOIN " +
                         "    KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang " +
                         "JOIN " +
                         "    Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher " +
                         "JOIN " +
                         "    NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien " +
                         "WHERE " +
                         "    HoaDon.TrangThai = 0;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonEntity hd = new HoaDonEntity();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTenKhachHang(rs.getString("kh"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenNhanVien(rs.getString("nv"));
                hd.setNgayThanhToan(rs.getString("NgayThanhToan")); // Ensure proper date format in your system
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienVoucher(rs.getFloat("TienVoucher"));
                hd.setThanhTien(rs.getFloat("ThanhTien"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThuc"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                list.add(hd);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Log error for debugging
            return null;
        }
        return list;
    }
    public ArrayList<HoaDonTro> getAllHoaDon() {
        ArrayList<HoaDonTro> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql = """
                    SELECT 
                                                  hd.id_HoaDon, 
                                                  hd.MaHoaDon, 
                                                  hd.NgayThanhToan, 
                                                  nv.MaNhanVien,
                         						 hd.TrangThaiThanhToan, 
                                                  hd.TrangThai
                                                  
                                              FROM 
                                                  HoaDon hd
                                              LEFT JOIN 
                                                  NhanVien nv ON hd.id_NhanVien = nv.id_NhanVien
                                              WHERE                        
                                                  hd.TrangThaiThanhToan= 0;
                     """;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonTro hd = new HoaDonTro().builder()
                        .idHoaDon(rs.getInt(1)) // Assuming id_HoaDon is an integer
                        .maHoaDon(rs.getString(2))
                        .ngayTao(rs.getDate(3))
                        .maNhanVien(rs.getString(4))
                        .tinhTrangThanhToan(rs.getInt(5))
                        .tinhTrang(rs.getInt(6))
                        .build();

                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    public Boolean themhoatro() {
        int ma = ToanCuc.getIdNhanVien();
        System.out.println("Mã nhân viên đang sử dụng: " + ma);
        String sql = """
                     INSERT INTO [dbo].[HoaDon]
                               (                                                                                             
                                 [NgayThanhToan]
                                ,[id_NhanVien]
                                ,[TongTien]
                                ,[TienVoucher]
                                ,[ThanhTien]                     
                                ,[PhuongThucThanhToan]
                                ,[GhiChu]
                                ,[TrangThaiThanhToan]
                                ,[TrangThai])                   
                         values (?,?,0,0,0,'','',0,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, new Date());
            ps.setObject(2, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean deletehdtro() {
        String sql = """
                    DELETE FROM [dbo].[HoaDon]
                          WHERE TrangThai=0
                    
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    public boolean deleteHoaDon(String MaHoaDon) {
        try {
            Connection con = DBConnect.getConnection();
            String sql = "UPDATE HoaDon SET TrangThai = 0 WHERE MaHoaDon = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaHoaDon);
            int rowsUpdated = ps.executeUpdate();
            ps.close(); 
            con.close();
            return rowsUpdated > 0; 
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }
}

    
