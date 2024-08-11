package main.repository;

import java.util.ArrayList;
import main.config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import main.entity.Voucher;
import main.entity.Vouchers;
import main.request.FindSanPham;
import main.response.BanHangResponse;

public class BanHangSPRepositories {

    public ArrayList<BanHangResponse> getAll(FindSanPham findSanPham) {
        ArrayList<BanHangResponse> listSP = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.id_SanPham, dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham,  
                            dbo.CPU.TenCPU, dbo.GPU.TenGPU, dbo.OCung.LoaiOCung, dbo.Ram.DungLuongRam, 
                            dbo.ManHinh.KichThuoc, dbo.Pin.DungLuongPin, dbo.SanPham.SoLuong, 
                            dbo.SanPham.GiaBan, dbo.SanPham.TrangThai
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
                BanHangResponse bhResponse = BanHangResponse.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .tenCPU(rs.getString(4))
                        .tenGPU(rs.getString(5))
                        .loaiOCung(rs.getString(6))
                        .dungluongRam(rs.getString(7))
                        .kichThuoc(rs.getString(8))
                        .dungluongPin(rs.getString(9))
                        .soLuong(rs.getInt(10))
                        .giaBan(rs.getFloat(11))
                        .build();
                listSP.add(bhResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listSP;
    }

    public ArrayList<BanHangResponse> getImeiByMaSP(String maSP) {
        ArrayList<BanHangResponse> listImeiChiTiet = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.id_SanPham, dbo.Imei.id_Imei, dbo.SanPham.MaSanPham, dbo.Imei.Ma_Imei, dbo.Imei.TrangThai
                     FROM   dbo.Imei INNER JOIN
                                  dbo.SanPham ON dbo.Imei.id_SanPham = dbo.SanPham.id_SanPham
                     WHERE dbo.SanPham.MaSanPham = ? 
                     AND dbo.Imei.TrangThai = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangResponse spr = BanHangResponse.builder()
                        .idSanPham(rs.getInt(1))
                        .idImei(rs.getInt(2))
                        .maSanPham(rs.getString(3))
                        .Imei(rs.getString(4))
                        .build();
                listImeiChiTiet.add(spr);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listImeiChiTiet;
    }

    public ArrayList<BanHangResponse> getImeiDaBanByMaSP(String maSP, Integer idHDCT) {
        ArrayList<BanHangResponse> listImeiChiTiet = new ArrayList<>();
        String sql = """
                     SELECT dbo.HoaDonChiTiet.id_HDCT, dbo.SanPham.MaSanPham, dbo.ImeiDaBan.Ma_Imei, dbo.ImeiDaBan.TrangThai
                     FROM   dbo.HoaDonChiTiet INNER JOIN
                                  dbo.ImeiDaBan ON dbo.HoaDonChiTiet.id_HDCT = dbo.ImeiDaBan.id_HDCT INNER JOIN
                                  dbo.SanPham ON dbo.HoaDonChiTiet.id_SanPham = dbo.SanPham.id_SanPham
                     			 WHERE MaSanPham = ?
                     			 AND dbo.HoaDonChiTiet.id_HDCT = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ps.setObject(2, idHDCT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangResponse spr = BanHangResponse.builder()
                        .idHDCT(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .ImeiDaBan(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listImeiChiTiet.add(spr);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listImeiChiTiet;
    }

    public int getSoLuongByMa(String maSP) {
        int soLuong = 0;
        String sql = """
            SELECT COUNT(Imei.Ma_Imei) 
            FROM dbo.SanPham 
            INNER JOIN dbo.Imei ON dbo.Imei.id_SanPham = dbo.SanPham.id_SanPham
            WHERE dbo.SanPham.MaSanPham = ?
            AND dbo.Imei.TrangThai = 1
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

    public Boolean addGioHang(Integer idHoaDon, BanHangResponse bhResponse, int soLuong) {
        String sql = """
                     INSERT INTO [dbo].[HoaDonChiTiet]
                                ([id_HoaDon]
                                ,[id_SanPham]
                                ,[SoLuong]
                                ,[DonGia]
                                ,[TongTien]
                                ,[TrangThai])
                          VALUES
                                (?,?,?,?,?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ps.setObject(2, bhResponse.getIdSanPham());
            ps.setObject(3, soLuong);
            ps.setObject(4, bhResponse.getGiaBan());
            ps.setObject(5, soLuong * bhResponse.getGiaBan());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public float getGiaBanByMa(String maSP) {
        String sql = """
                 SELECT [GiaBan]
                   FROM [dbo].[SanPham]
                   WHERE MaSanPham = ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;  // Nếu không tìm thấy hoặc có lỗi
    }

    public Boolean updateSoLuong(Integer soLuong, Integer idSP) {
        String sql = """
                     UPDATE [dbo].[SanPham]
                        SET [SoLuong] = ?
                      WHERE [id_SanPham] = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soLuong);
            ps.setObject(2, idSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean checkSanPhamGioHang(Integer idHD, Integer idSP) {
        String sql = """
                     SELECT SoLuong FROM HoaDonChiTiet 
                     WHERE id_HoaDon = ? 
                     AND id_SanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            ps.setInt(2, idSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSoLuongGioHang(Integer idHD, Integer idSP, Integer soLuong) {
        String sql = """
                     UPDATE HoaDonChiTiet 
                     SET SoLuong = SoLuong + ? 
                     WHERE id_HoaDon = ? 
                     AND id_SanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setInt(2, idHD);
            ps.setInt(3, idSP);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateKH(int idKhachHang, String maHDon) {
        String sql = "UPDATE HoaDon SET id_KhachHang=? WHERE MaHoaDon=?";
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKhachHang); // Cập nhật id khách hàng
            ps.setString(2, maHDon);   // Cập nhật id hóa đơn
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean updateKhachHang(int idKhachHang, String maHoaDon) {
        String sql = "UPDATE HoaDon SET id_KhachHang=? WHERE MaHoaDon=?";
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKhachHang); // Cập nhật id khách hàng
            ps.setString(2, maHoaDon);  // Cập nhật mã hóa đơn
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<Vouchers> getVouchersByType(int voucherType) {
        List<Vouchers> vouchers = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();  // Kết nối cơ sở dữ liệu
            String sql = "SELECT id_Voucher, MoTa FROM Voucher WHERE NgayHetHan > ? AND LoaiVoucher = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(new java.util.Date().getTime())); // Truyền ngày hiện tại vào câu truy vấn
            stmt.setInt(2, voucherType); // Truyền loại voucher vào câu truy vấn
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idVoucher = rs.getInt("id_Voucher");
                String maVoucher = rs.getString("MoTa");
                vouchers.add(new Vouchers(idVoucher, maVoucher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vouchers;
    }

    public float checkVoucher(String voucherCode) {
        float discount = 0;
        String sql = "SELECT MucGiaTri, NgayHetHan FROM Voucher WHERE MoTa = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, voucherCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin mã voucher
                float mucGiaTri = rs.getFloat("MucGiaTri");
                Date ngayHetHan = rs.getDate("NgayHetHan");

                // Kiểm tra ngày hết hạn
                if (ngayHetHan.after(new java.util.Date())) {
                    discount = mucGiaTri; // Mã voucher hợp lệ
                } else {
                    discount = -1; // Mã voucher đã hết hạn
                }
            } else {
                discount = -2; // Mã voucher không tồn tại
            }

        } catch (Exception e) {
            e.printStackTrace();
            discount = -3; // Lỗi hệ thống hoặc cơ sở dữ liệu
        }
        return discount;
    }

    public boolean updateInvoiceStatus(String idHoaDon, int idVoucher, double tongTien, double tienVoucher, double thanhTien, String phuongThucThanhToan, int trangThaiThanhToan) {
        boolean isUpdated = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnect.getConnection();

            // Câu lệnh SQL cập nhật
            String sql = "  UPDATE HoaDon SET id_Voucher=?, TongTien=?, TienVoucher=?, ThanhTien=?, PhuongThucThanhToan=?, TrangThaiThanhToan=?,GhiChu= N'Đã thanh toán' WHERE [MaHoaDon]=?";
            ps = con.prepareStatement(sql);

            // Set giá trị cho các tham số
            ps.setInt(1, idVoucher);
            ps.setDouble(2, tongTien);
            ps.setDouble(3, tienVoucher);
            ps.setDouble(4, thanhTien);
            ps.setString(5, phuongThucThanhToan);
            ps.setInt(6, trangThaiThanhToan);  // Set giá trị trạng thái thanh toán
            ps.setString(7, idHoaDon);

            // Thực hiện cập nhật
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }

    //
    public ArrayList<BanHangResponse> getIDHDCT_Click(Integer idHd, Integer idSP) {
        ArrayList<BanHangResponse> listImeiChiTiet = new ArrayList<>();
        String sql = """
                    select id_HDCT from HoaDonChiTiet where id_HoaDon = ? and id_SanPham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHd);
            ps.setObject(2, idSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangResponse spr = BanHangResponse.builder()
                        .idHDCT(rs.getInt(1))
                        .build();
                listImeiChiTiet.add(spr);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listImeiChiTiet;
    }
    
    public ArrayList<BanHangResponse> getAllsp() {
        ArrayList<BanHangResponse> listSP = new ArrayList<>();
        String sql = """
                     SELECT dbo.SanPham.id_SanPham, dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham,  
                            dbo.CPU.TenCPU, dbo.GPU.TenGPU, dbo.OCung.LoaiOCung, dbo.Ram.DungLuongRam, 
                            dbo.ManHinh.KichThuoc, dbo.Pin.DungLuongPin, dbo.SanPham.SoLuong, 
                            dbo.SanPham.GiaBan, dbo.SanPham.TrangThai
                     FROM dbo.CPU 
                     INNER JOIN dbo.SanPham ON dbo.CPU.id_CPU = dbo.SanPham.id_CPU 
                     INNER JOIN dbo.GPU ON dbo.SanPham.id_GPU = dbo.GPU.id_GPU 
                     INNER JOIN dbo.ManHinh ON dbo.SanPham.id_ManHinh = dbo.ManHinh.id_ManHinh 
                     INNER JOIN dbo.OCung ON dbo.SanPham.id_OCung = dbo.OCung.id_OCung 
                     INNER JOIN dbo.Pin ON dbo.SanPham.id_Pin = dbo.Pin.id_Pin 
                     INNER JOIN dbo.Ram ON dbo.SanPham.id_Ram = dbo.Ram.id_Ram
                     WHERE dbo.SanPham.TrangThai = 1                    
                     ORDER BY dbo.SanPham.id_SanPham DESC
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {       
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangResponse bhResponse = BanHangResponse.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .tenCPU(rs.getString(4))
                        .tenGPU(rs.getString(5))
                        .loaiOCung(rs.getString(6))
                        .dungluongRam(rs.getString(7))
                        .kichThuoc(rs.getString(8))
                        .dungluongPin(rs.getString(9))
                        .soLuong(rs.getInt(10))
                        .giaBan(rs.getFloat(11))
                        .build();
                listSP.add(bhResponse);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listSP;
    }
}
