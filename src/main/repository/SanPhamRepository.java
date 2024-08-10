package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.SanPham;
import main.request.FindSanPham;
import main.request.SanPhamRequest;
import main.response.SanPhamResponse;

public class SanPhamRepository {

    public ArrayList<SanPhamResponse> getAll(FindSanPham findSanPham) {
        ArrayList<SanPhamResponse> listSP = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.id_SanPham, dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham, dbo.SanPham.HinhAnh, 
                            dbo.CPU.TenCPU, dbo.GPU.TenGPU, dbo.OCung.LoaiOCung, dbo.Ram.DungLuongRam, 
                            dbo.ManHinh.KichThuoc, dbo.Pin.DungLuongPin, dbo.SanPham.SoLuong, 
                            dbo.SanPham.GiaNhap, dbo.SanPham.GiaBan, dbo.SanPham.TrangThai
                     FROM dbo.CPU 
                     INNER JOIN dbo.SanPham ON dbo.CPU.id_CPU = dbo.SanPham.id_CPU 
                     INNER JOIN dbo.GPU ON dbo.SanPham.id_GPU = dbo.GPU.id_GPU 
                     INNER JOIN dbo.ManHinh ON dbo.SanPham.id_ManHinh = dbo.ManHinh.id_ManHinh 
                     INNER JOIN dbo.OCung ON dbo.SanPham.id_OCung = dbo.OCung.id_OCung 
                     INNER JOIN dbo.Pin ON dbo.SanPham.id_Pin = dbo.Pin.id_Pin 
                     INNER JOIN dbo.Ram ON dbo.SanPham.id_Ram = dbo.Ram.id_Ram
                     WHERE dbo.SanPham.TrangThai = 1
                     AND (
                         (dbo.SanPham.MaSanPham LIKE ?
                         OR dbo.SanPham.TenSanPham LIKE ?
                         OR dbo.CPU.TenCPU LIKE ?
                         OR dbo.GPU.TenGPU LIKE ?
                         OR dbo.OCung.LoaiOCung LIKE ?
                         OR dbo.Ram.DungLuongRam LIKE ?
                         OR dbo.ManHinh.KichThuoc LIKE ?
                         OR dbo.Pin.DungLuongPin LIKE ?)
                     )
                     ORDER BY dbo.SanPham.id_SanPham DESC
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(2, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(3, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(4, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(5, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(6, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(7, "%" + findSanPham.getKeySearch1() + "%");
            ps.setObject(8, "%" + findSanPham.getKeySearch1() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse spResponse = SanPhamResponse.builder()
                        .IdSanPham(rs.getInt(1))
                        .MaSanPham(rs.getString(2))
                        .TenSanPham(rs.getString(3))
                        .HinhAnh(rs.getString(4))
                        .TenCPU(rs.getString(5))
                        .TenGPU(rs.getString(6))
                        .LoaiOCung(rs.getString(7))
                        .DungLuongRam(rs.getString(8))
                        .KichThuoc(rs.getString(9))
                        .DungLuongPin(rs.getString(10))
                        .SoLuong(rs.getInt(11))
                        .GiaNhap(rs.getFloat(12))
                        .GiaBan(rs.getFloat(13))
                        .build();
                listSP.add(spResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listSP;
    }

    public ArrayList<SanPhamResponse> timGia(float giaMin, float giaMax) {
        ArrayList<SanPhamResponse> listSP = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.id_SanPham, dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham, dbo.SanPham.HinhAnh, 
                            dbo.CPU.TenCPU, dbo.GPU.TenGPU, dbo.OCung.LoaiOCung, dbo.Ram.DungLuongRam, 
                            dbo.ManHinh.KichThuoc, dbo.Pin.DungLuongPin, dbo.SanPham.SoLuong, 
                            dbo.SanPham.GiaNhap, dbo.SanPham.GiaBan, dbo.SanPham.TrangThai
                     FROM dbo.CPU 
                     INNER JOIN dbo.SanPham ON dbo.CPU.id_CPU = dbo.SanPham.id_CPU 
                     INNER JOIN dbo.GPU ON dbo.SanPham.id_GPU = dbo.GPU.id_GPU 
                     INNER JOIN dbo.ManHinh ON dbo.SanPham.id_ManHinh = dbo.ManHinh.id_ManHinh 
                     INNER JOIN dbo.OCung ON dbo.SanPham.id_OCung = dbo.OCung.id_OCung 
                     INNER JOIN dbo.Pin ON dbo.SanPham.id_Pin = dbo.Pin.id_Pin 
                     INNER JOIN dbo.Ram ON dbo.SanPham.id_Ram = dbo.Ram.id_Ram
                     WHERE dbo.SanPham.TrangThai = 1
                     AND (
                         dbo.SanPham.GiaBan between ? and ?
                     )
                     ORDER BY dbo.SanPham.id_SanPham DESC
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, giaMin);
            ps.setObject(2, giaMax);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse spResponse = SanPhamResponse.builder()
                        .IdSanPham(rs.getInt(1))
                        .MaSanPham(rs.getString(2))
                        .TenSanPham(rs.getString(3))
                        .HinhAnh(rs.getString(4))
                        .TenCPU(rs.getString(5))
                        .TenGPU(rs.getString(6))
                        .LoaiOCung(rs.getString(7))
                        .DungLuongRam(rs.getString(8))
                        .KichThuoc(rs.getString(9))
                        .DungLuongPin(rs.getString(10))
                        .SoLuong(rs.getInt(11))
                        .GiaNhap(rs.getFloat(12))
                        .GiaBan(rs.getFloat(13))
                        .build();
                listSP.add(spResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listSP;
    }

    public ArrayList<SanPhamResponse> getAllDelete() {
        ArrayList<SanPhamResponse> listSPDelete = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.id_SanPham, dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham, dbo.SanPham.HinhAnh, dbo.CPU.TenCPU, dbo.GPU.TenGPU, dbo.OCung.LoaiOCung, dbo.Ram.DungLuongRam, dbo.ManHinh.KichThuoc, dbo.Pin.DungLuongPin, 
                                  dbo.SanPham.SoLuong, dbo.SanPham.GiaNhap, dbo.SanPham.GiaBan, dbo.SanPham.TrangThai
                     FROM   dbo.CPU INNER JOIN
                                  dbo.SanPham ON dbo.CPU.id_CPU = dbo.SanPham.id_CPU INNER JOIN
                                  dbo.GPU ON dbo.SanPham.id_GPU = dbo.GPU.id_GPU INNER JOIN                                  
                                  dbo.ManHinh ON dbo.SanPham.id_ManHinh = dbo.ManHinh.id_ManHinh INNER JOIN
                                  dbo.OCung ON dbo.SanPham.id_OCung = dbo.OCung.id_OCung INNER JOIN
                                  dbo.Pin ON dbo.SanPham.id_Pin = dbo.Pin.id_Pin INNER JOIN
                                  dbo.Ram ON dbo.SanPham.id_Ram = dbo.Ram.id_Ram
                     WHERE dbo.SanPham.TrangThai = 0
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse spResponse = SanPhamResponse.builder()
                        .IdSanPham(rs.getInt(1))
                        .MaSanPham(rs.getString(2))
                        .TenSanPham(rs.getString(3))
                        .HinhAnh(rs.getString(4))
                        .TenCPU(rs.getString(5))
                        .TenGPU(rs.getString(6))
                        .LoaiOCung(rs.getString(7))
                        .DungLuongRam(rs.getString(8))
                        .KichThuoc(rs.getString(9))
                        .DungLuongPin(rs.getString(10))
                        .SoLuong(rs.getInt(11))
                        .GiaNhap(rs.getFloat(12))
                        .GiaBan(rs.getFloat(13))
                        .build();
                listSPDelete.add(spResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listSPDelete;
    }

    public ArrayList<SanPhamResponse> getImeiByMaSP(String maSP) {
        ArrayList<SanPhamResponse> listImeiChiTiet = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.MaSanPham, dbo.Imei.Ma_Imei
                     FROM   dbo.Imei INNER JOIN
                            dbo.SanPham ON dbo.Imei.id_SanPham = dbo.SanPham.id_SanPham
                     WHERE dbo.SanPham.MaSanPham = ?
                     AND dbo.Imei.TrangThai = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse spr = SanPhamResponse.builder()
                        .MaSanPham(rs.getString(1))
                        .MaImei(rs.getString(2))
                        .build();
                listImeiChiTiet.add(spr);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listImeiChiTiet;
    }

    public Boolean add(SanPhamResponse sp) {
        String sql = """
                     INSERT INTO [dbo].[SanPham]
                                ([id_Ram]
                                ,[id_CPU]
                                ,[id_GPU]
                                ,[id_ManHinh]
                                ,[id_OCung]
                                ,[id_Pin]
                                ,[TenSanPham]
                                ,[HinhAnh]
                                ,[SoLuong]
                                ,[GiaNhap]
                                ,[GiaBan]
                                ,[TrangThai])
                          VALUES
                                (?,?,?,?,?,?,?,?,0,?,?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sp.getIdRam());
            ps.setObject(2, sp.getIdCPU());
            ps.setObject(3, sp.getIdGPU());
            ps.setObject(4, sp.getIdManHinh());
            ps.setObject(5, sp.getIdOCung());
            ps.setObject(6, sp.getIdPin());
            ps.setObject(7, sp.getTenSanPham());
            ps.setObject(8, sp.getHinhAnh());
            ps.setObject(9, sp.getGiaNhap());
            ps.setObject(10, sp.getGiaBan());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean update(SanPhamResponse sanphamReponse, Integer IdSP) {
        String sql = """
                     UPDATE [dbo].[SanPham]
                        SET [id_Ram] = ?
                           ,[id_CPU] = ?
                           ,[id_GPU] = ?
                           ,[id_ManHinh] = ?
                           ,[id_OCung] = ?
                           ,[id_Pin] = ?
                           ,[TenSanPham] = ?
                           ,[HinhAnh] = ?
                           ,[GiaNhap] = ?
                           ,[GiaBan] = ?
                      WHERE id_SanPham = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sanphamReponse.getIdRam());
            ps.setObject(2, sanphamReponse.getIdCPU());
            ps.setObject(3, sanphamReponse.getIdGPU());
            ps.setObject(4, sanphamReponse.getIdManHinh());
            ps.setObject(5, sanphamReponse.getIdOCung());
            ps.setObject(6, sanphamReponse.getIdPin());
            ps.setObject(7, sanphamReponse.getTenSanPham());
            ps.setObject(8, sanphamReponse.getHinhAnh());
            ps.setObject(9, sanphamReponse.getGiaNhap());
            ps.setObject(10, sanphamReponse.getGiaBan());
            ps.setObject(11, IdSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean delete(Integer IdSP) {
        String sql = """
                     UPDATE [dbo].[SanPham]
                        SET [TrangThai] = 0
                      WHERE id_SanPham = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, IdSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean updateQuantity(Integer soLuong, String maSP) {
        String sql = """
                     update SanPham set SoLuong = ?
                     where MaSanPham = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soLuong);
            ps.setObject(2, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean restore(Integer IdSP) {
        String sql = """
                     UPDATE [dbo].[SanPham]
                        SET [TrangThai] = 1
                      WHERE id_SanPham = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, IdSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public int getSoLuongByMa(String maSP) {
        int soLuong = 0;
        String sql = """
            SELECT COUNT(Imei.Ma_Imei) 
            FROM dbo.SanPham 
            INNER JOIN dbo.Imei ON dbo.Imei.id_SanPham = dbo.SanPham.id_SanPham
            WHERE dbo.SanPham.MaSanPham = ?
        """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return soLuong;
    }

    public int getSoLuong(String maSP) {
        int soLuong = 0;
        String sql = """
                     SELECT [SoLuong]
                     FROM [dbo].[SanPham]
                     WHERE dbo.SanPham.MaSanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return soLuong;
    }

    public Integer getIdSPByMa(String maSP) {
        String sql = """
                     SELECT id_SanPham 
                     FROM SanPham 
                     WHERE MaSanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_SanPham");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMaSPByID(Integer idSP) {
        String sql = """
                     SELECT MaSanPham 
                     FROM SanPham 
                     WHERE id_SanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MaSanPham");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
