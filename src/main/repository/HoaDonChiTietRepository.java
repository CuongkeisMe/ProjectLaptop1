package main.repository;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;
import main.entity.HoaDonChiTiet;
import main.entity.HoaDonTro;
import main.response.HoaDonChiTietResponse;
import main.response.SanPhamReponse_BH;

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
            String sql = """
                         SELECT
                             HoaDon.MaHoaDon AS 'MaHoaDon',
                             SanPham.TenSanPham AS 'TenSanPham',
                             ImeiDaBan.Ma_Imei AS 'MaImei',
                             SanPham.GiaBan AS 'GiaBan'
                         FROM HoaDonChiTiet
                         full JOIN HoaDon ON HoaDon.id_HoaDon = HoaDonChiTiet.id_HoaDon
                         full JOIN ImeiDaBan ON ImeiDaBan.id_HDCT = HoaDonChiTiet.id_HDCT
                         full JOIN SanPham ON SanPham.id_SanPham = HoaDonChiTiet.id_SanPham
                         WHERE HoaDonChiTiet.id_HDCT = ?
                           AND HoaDonChiTiet.TrangThai = 1;
                         """;
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

    public ArrayList<SanPhamReponse_BH> LayImeiDaBanHuy(String maSP_HDCT, String maHD_HD) {
        String sql = """
                       SELECT dbo.ImeiDaBan.Ma_Imei, dbo.HoaDonChiTiet.id_HDCT, dbo.ImeiDaBan.id_ImeiDaBan
                                                                  FROM   dbo.HoaDon INNER JOIN
                                                                               dbo.HoaDonChiTiet ON dbo.HoaDon.id_HoaDon = dbo.HoaDonChiTiet.id_HoaDon INNER JOIN
                                                                               dbo.ImeiDaBan ON dbo.HoaDonChiTiet.id_HDCT = dbo.ImeiDaBan.id_HDCT INNER JOIN
                                                                               dbo.SanPham ON dbo.HoaDonChiTiet.id_SanPham = dbo.SanPham.id_SanPham
                                                                  WHERE SanPham.MaSanPham = ?
                                                                  AND HoaDon.MaHoaDon = ?
                     """;

        ArrayList<SanPhamReponse_BH> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP_HDCT);
            ps.setString(2, maHD_HD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamReponse_BH sp = SanPhamReponse_BH.builder()
                        .maImeiDaBan(rs.getString(1))
                        .idHoaDonChiTiet(rs.getInt(2))
                        .idImeiDaBan(rs.getInt(3))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public boolean updateSoLuongSPXoa(int soLuong, String maSP) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[SanPham]
                       SET [SoLuong] = ?
                     WHERE MaSanPham = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soLuong);
            ps.setObject(2, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<HoaDonChiTietResponse> getTongImeiTheoMaSP(String maSP) {
        String sql = """
                 SELECT COUNT(SanPham.id_SanPham) as 'Tong Imei Theo Ma SP'
                 FROM dbo.SanPham INNER JOIN
                      dbo.Imei ON dbo.SanPham.id_SanPham = dbo.Imei.id_SanPham
                 WHERE Imei.TrangThai = 1 and SanPham.MaSanPham = ?
                     """;
        ArrayList<HoaDonChiTietResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse response = HoaDonChiTietResponse.builder()
                        .soLuongImei(rs.getInt(1))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public boolean updateSoLuongHDCT_Huy(int idHD) {
        int check = 0;
        String sql = """
                   UPDATE HoaDonChiTiet 
                   SET SoLuong = 0
                   WHERE id_HoaDon = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, idHD);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public ArrayList<HoaDonTro> getHD_ClickTheoMa(String maHD_Click) {
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
                                                  hd.TrangThaiThanhToan= 0
                                             AND
                                                  hd.TrangThai=0
                                             AND
                                                  hd.MaHoaDon=?
                                             
                     """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, maHD_Click);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonTro hd = new HoaDonTro().builder()
                        .idHoaDon(rs.getInt(1))
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

    public boolean updateTongTien(float tongtien, String maHD) {
        int check = 0;
        String sql = """
                    update HoaDon
                    set TongTien = ?
                    where MaHoaDon = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, tongtien);
            ps.setObject(2, maHD);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public boolean XoaAllHDCTGioHang(int idHDCT) {
        int check = 0;
        String sql = """
                 delete HoaDonChiTiet 
                  where id_HDCT = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, idHDCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public ArrayList<HoaDonChiTietResponse> getHDCTClick_TheoMaHDMaSP(String maHD, String maSP) {
        String sql = """
                     SELECT  HoaDonChiTiet.id_HoaDon , HoaDonChiTiet.id_SanPham,SanPham.TenSanPham, HoaDonChiTiet.SoLuong
                     , HoaDonChiTiet.id_HDCT
                     FROM  dbo.HoaDon INNER JOIN
                                           dbo.HoaDonChiTiet ON dbo.HoaDon.id_HoaDon = dbo.HoaDonChiTiet.id_HoaDon INNER JOIN
                                           dbo.SanPham ON dbo.HoaDonChiTiet.id_SanPham = dbo.SanPham.id_SanPham
                     where SanPham.MaSanPham = ? and HoaDon.MaHoaDon = ?
                     """;
        ArrayList<HoaDonChiTietResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ps.setObject(2, maHD);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse response = HoaDonChiTietResponse.builder()
                        .idHoaDon(rs.getInt(1))
                        .idSanPham(rs.getInt(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .idHoaDonChiTiet(rs.getInt(5))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}
