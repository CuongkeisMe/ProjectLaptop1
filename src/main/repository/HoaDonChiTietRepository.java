package main.repository;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;
import main.entity.HoaDonChiTiet;
import main.response.HoaDonChiTietResponse;

public class HoaDonChiTietRepository {

public ArrayList<HoaDonChiTietResponse> getAll(Integer idHoaDon) {
        ArrayList<HoaDonChiTietResponse> listHDCT = new ArrayList<>();
        String sql = """
                 SELECT dbo.HoaDonChiTiet.id_HDCT, dbo.HoaDon.id_HoaDon, dbo.SanPham.id_SanPham, dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham, dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.DonGia, dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia AS TongTien
                 FROM   dbo.HoaDon
                 INNER JOIN dbo.HoaDonChiTiet ON dbo.HoaDon.id_HoaDon = dbo.HoaDonChiTiet.id_HoaDon
                 INNER JOIN dbo.SanPham ON dbo.HoaDonChiTiet.id_SanPham = dbo.SanPham.id_SanPham
                 WHERE dbo.HoaDon.id_HoaDon = ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse hdctResponse = HoaDonChiTietResponse.builder()
                        .idHoaDonChiTiet(rs.getInt(1))
                        .idHoaDon(rs.getInt(2))
                        .idSanPham(rs.getInt(3))
                        .maSanPham(rs.getString(4))
                        .tenSanPham(rs.getString(5))
                        .soLuong(rs.getInt(6))
                        .giaBan(rs.getFloat(7))
                        .tongTien(rs.getFloat(8))
                        .build();
                listHDCT.add(hdctResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listHDCT;
    }

    public Boolean addImeiDaBan(Integer idHDCT, String maImeiDaBan) {
        String sql = """
                     INSERT INTO [dbo].[ImeiDaBan]
                                ([id_HDCT]
                                ,[Ma_Imei])
                          VALUES
                                (?,?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHDCT);
            ps.setObject(2, maImeiDaBan);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean xoaImeiDaBan(String maImeiDaBan) {
        String sql = """
                     DELETE FROM [dbo].[ImeiDaBan]
                           WHERE [Ma_Imei] = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maImeiDaBan);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean updateTrangThaiImei(Integer idImei) {
        String sql = """
                    UPDATE [dbo].[Imei]
                       SET [TrangThai] = 0
                     WHERE id_Imei = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean updateTrangThaiImei(String maImei) {
        String sql = """
                    UPDATE [dbo].[Imei]
                       SET [TrangThai] = 1
                     WHERE Ma_Imei = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean updateSoLuongGioHang(Integer soLuong, Integer idHDCT) {
        String sql = """
                     UPDATE [dbo].[HoaDonChiTiet]
                        SET [SoLuong] = ?
                      WHERE id_HDCT = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soLuong);
            ps.setObject(2, idHDCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<HoaDonChiTiet> getHdct(int id_HoaDon) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "SELECT\n"
                    + "    HoaDon.MaHoaDon AS 'MaHoaDon',\n"
                    + "    SanPham.TenSanPham AS 'TenSanPham',\n"
                    + "    ImeiDaBan.Ma_Imei AS 'MaImei',\n"
                    + "    SanPham.GiaBan AS 'GiaBan'\n"
                    + "FROM HoaDonChiTiet\n"
                    + "INNER JOIN HoaDon ON HoaDon.id_HoaDon = HoaDonChiTiet.id_HoaDon\n"
                    + "INNER JOIN ImeiDaBan ON ImeiDaBan.id_HDCT = HoaDonChiTiet.id_HDCT\n"
                    + "INNER JOIN SanPham ON SanPham.id_SanPham = HoaDonChiTiet.id_SanPham\n"
                    + "WHERE HoaDonChiTiet.id_HDCT = ? \n"
                    + "  AND HoaDonChiTiet.TrangThai = 1;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_HoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHoaDon(rs.getString("MaHoaDon"));
                hdct.setTenSanPham(rs.getString("TenSanPham"));
                hdct.setMaImei(rs.getString("MaImei"));
                hdct.setGiaTien(rs.getFloat("GiaBan"));
                list.add(hdct);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public Boolean deleteHDCT(Integer idHDCT) {
        int check = 0;
        String sql = """
                     DELETE FROM [dbo].[HoaDonChiTiet]
                     WHERE [id_HDCT] = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHDCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public int getSoLuongGioHang(int idHD, int idSP) {
        int soLuong = 0;
        String sql = """
                     SELECT SoLuong FROM dbo.HoaDonChiTiet 
                     WHERE id_HoaDon = ? 
                     AND id_SanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            ps.setInt(2, idSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuong;
    }
    
    public int getSoLuongSanPham(Integer idSP) {
        int soLuong = 0;
        String sql = """
                     SELECT [SoLuong]
                     FROM [dbo].[SanPham]
                     WHERE dbo.SanPham.id_SanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return soLuong;
    }

    public ArrayList<HoaDonChiTietResponse> getIDHDCT_VuaThem() {
        ArrayList<HoaDonChiTietResponse> listHDCT = new ArrayList<>();
        String sql = """
                 select top 1 id_HDCT from HoaDonChiTiet order by id_HDCT desc
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse hdctResponse = HoaDonChiTietResponse.builder()
                        .idHoaDonChiTiet(rs.getInt(1))
                        .build();
                listHDCT.add(hdctResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listHDCT;
    }

    
}
