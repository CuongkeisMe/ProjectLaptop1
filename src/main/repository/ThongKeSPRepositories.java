package main.repository;

import com.raven.chart.ModelChart;
import java.math.BigDecimal;
import main.config.DBConnect;
import main.entity.ThongKe;
import main.request.FindTKSanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ThongKeSPRepositories {

    public ArrayList<ThongKe> getAll(FindTKSanPham fsp) {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = """
                    SELECT 
                        SanPham.MaSanPham AS 'MaSP',
                        SanPham.TenSanPham AS 'TenSP',
                        SanPham.GiaBan AS 'GiaSP',
                        SanPham.SoLuong AS 'TongSoLuongSP',
                        ISNULL(SUM(HoaDonChiTiet.SoLuong), 0) AS 'SoSPDaBan',
                        ISNULL(SUM(HoaDonChiTiet.TongTien), 0) AS 'DoanhThu'
                    FROM 
                        SanPham
                    LEFT JOIN 
                        HoaDonChiTiet ON SanPham.id_SanPham = HoaDonChiTiet.id_SanPham
                    WHERE 
                        SanPham.MaSanPham LIKE ? OR SanPham.TenSanPham LIKE ?
                    GROUP BY 
                        SanPham.MaSanPham, 
                        SanPham.TenSanPham, 
                        SanPham.GiaBan, 
                        SanPham.SoLuong;
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + fsp.getKeySearch() + "%");
            ps.setString(2, "%" + fsp.getKeySearch() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = ThongKe.builder()
                        .maSP(rs.getString("MaSP"))
                        .tenSP(rs.getString("TenSP"))
                        .giaSP(rs.getFloat("GiaSP"))
                        .tongSP(rs.getInt("TongSoLuongSP"))
                        .daBan(rs.getInt("SoSPDaBan"))
                        .doanhThu(rs.getFloat("DoanhThu"))
                        .build();
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ThongKe> searchDate(FindTKSanPham fsp) {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = """
        SELECT 
                SanPham.MaSanPham AS 'MaSP',
                SanPham.TenSanPham AS 'TenSP',
                SanPham.GiaBan AS 'GiaSP',
                SanPham.SoLuong AS 'TongSoLuongSP',
                ISNULL(SUM(HoaDonChiTiet.SoLuong), 0) AS 'SoSPDaBan',
                ISNULL(SUM(HoaDonChiTiet.TongTien), 0) AS 'DoanhThu'
            FROM 
                SanPham
            LEFT JOIN 
                HoaDonChiTiet ON SanPham.id_SanPham = HoaDonChiTiet.id_SanPham
            LEFT JOIN 
                HoaDon ON HoaDonChiTiet.id_HoaDon = HoaDon.id_HoaDon
            WHERE 
                HoaDon.NgayThanhToan BETWEEN ? AND ?
            GROUP BY 
                SanPham.MaSanPham, 
                SanPham.TenSanPham, 
                SanPham.GiaBan, 
                SanPham.SoLuong;
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
                        .maSP(rs.getString("MaSP"))
                        .tenSP(rs.getString("TenSP"))
                        .giaSP(rs.getFloat("GiaSP"))
                        .tongSP(rs.getInt("TongSoLuongSP"))
                        .daBan(rs.getInt("SoSPDaBan"))
                        .doanhThu(rs.getFloat("DoanhThu"))
                        .build();
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

 

    public static List<ModelChart> fetchData() {
        List<ModelChart> data = new ArrayList<>();
        String query = """
                SELECT 
                    DATEPART(MONTH, HD.NgayThanhToan) AS Thang,
                    SUM(HD.ThanhTien) AS DoanhThuThang
                FROM 
                    HoaDon HD
                GROUP BY 
                    DATEPART(MONTH, HD.NgayThanhToan)
                ORDER BY 
                    Thang;
                """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("Thang");
                double revenue = rs.getDouble("DoanhThuThang");
                System.out.println("Month: " + month + ", Revenue: " + revenue); // Kiểm tra kết quả
                data.add(new ModelChart(String.valueOf(month), new double[]{revenue}));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int getTotalProducts() throws SQLException {
        String query = "SELECT COUNT(*) FROM SanPham";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            rs.next();

            return rs.getInt(1);
        }
    }

    public static int getTotalProductsSold() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(SoLuong), 0) AS TongSoSanPhamDaBanHomNay
                   FROM HoaDonChiTiet HDCT
                   JOIN HoaDon HD ON HDCT.id_HoaDon = HD.id_HoaDon
                   WHERE CAST(HD.NgayThanhToan AS DATE) = CAST(GETDATE() AS DATE);
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("TongSoSanPhamDaBanHomNay");
            } else {
                return 0; // Trả về 0 nếu không có dữ liệu
            }
        }
    }

    public static int getTotalProductsSoldThang() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(SoLuong), 0) AS TongSoSanPhamDaBanThangNay
                   FROM HoaDonChiTiet HDCT
                   JOIN HoaDon HD ON HDCT.id_HoaDon = HD.id_HoaDon
                   WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE())
                   AND MONTH(HD.NgayThanhToan) = MONTH(GETDATE());
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("TongSoSanPhamDaBanThangNay");
            } else {
                return 0; // Trả về 0 nếu không có dữ liệu
            }
        }
    }

    public static int getTotalProductsSoldNam() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(SoLuong), 0) AS TongSoSanPhamDaBanTrongNam
                   FROM HoaDonChiTiet HDCT
                   JOIN HoaDon HD ON HDCT.id_HoaDon = HD.id_HoaDon
                   WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE());
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("TongSoSanPhamDaBanTrongNam");
            } else {
                return 0; // Trả về 0 nếu không có dữ liệu
            }
        }
    }

    public static BigDecimal getTotalProductsDT() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(HDCT.TongTien), 0) AS DoanhThuNgay
                   FROM SanPham SP
                   JOIN HoaDonChiTiet HDCT ON SP.id_SanPham = HDCT.id_SanPham
                   JOIN HoaDon HD ON HDCT.id_HoaDon = HD.id_HoaDon
                   WHERE CAST(HD.NgayThanhToan AS DATE) = CAST(GETDATE() AS DATE);
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("DoanhThuNgay");
            } else {
                return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không có dữ liệu
            }
        }
    }

    public static BigDecimal getTotalProductsDTT() throws SQLException {
        String query = """
                   	SELECT ISNULL(SUM(HD.ThanhTien), 0) AS DoanhThuThang
                   FROM SanPham SP
                   JOIN HoaDonChiTiet HDCT ON SP.id_SanPham = HDCT.id_SanPham
                   JOIN HoaDon HD ON HDCT.id_HoaDon = HD.id_HoaDon
                   WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE())
                   AND MONTH(HD.NgayThanhToan) = MONTH(GETDATE());
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("DoanhThuThang");
            } else {
                return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không có dữ liệu
            }
        }
    }

    public static BigDecimal getTotalProductsDTN() throws SQLException {
        String query = """
                   	SELECT ISNULL(SUM(ThanhTien), 0) AS TongDoanhThuNamNay
                        FROM HoaDon
                        WHERE YEAR(NgayThanhToan) = YEAR(GETDATE());
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("TongDoanhThuNamNay");
            } else {
                return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không có dữ liệu
            }
        }
    }

    public static BigDecimal getTotalRevenueDate() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(ThanhTien), 0) AS TongDoanhThuHomNay
                   FROM HoaDon
                   WHERE CAST(NgayThanhToan AS DATE) = CAST(GETDATE() AS DATE);
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("TongDoanhThuHomNay");
            } else {
                return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không có dữ liệu
            }
        }
    }

    public static BigDecimal getTotalRevenueMonth() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(ThanhTien), 0) AS TongDoanhThuThangNay
                   FROM HoaDon
                   WHERE YEAR(NgayThanhToan) = YEAR(GETDATE()) 
                   AND MONTH(NgayThanhToan) = MONTH(GETDATE());
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("TongDoanhThuThangNay");
            } else {
                return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không có dữ liệu
            }
        }
    }

    public static BigDecimal getTotalRevenueYear() throws SQLException {
        String query = """
                   SELECT ISNULL(SUM(ThanhTien), 0) AS TongDoanhThuNamNay
                   FROM HoaDon
                   WHERE YEAR(NgayThanhToan) = YEAR(GETDATE());
                   """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("TongDoanhThuNamNay");
            } else {
                return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không có dữ liệu
            }
        }
    }
// Phương thức lấy top 5 sản phẩm bán chạy nhất

    public ArrayList<ThongKe> getTop5BestSellingProducts() {
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = """
                SELECT TOP 5
                    SanPham.MaSanPham AS 'MaSP',
                    SanPham.TenSanPham AS 'TenSP',
                    SanPham.GiaBan AS 'GiaSP',
                    SanPham.SoLuong AS 'TongSoLuongSP',
                    ISNULL(SUM(HoaDonChiTiet.SoLuong), 0) AS 'SoSPDaBan',
                    ISNULL(SUM(HoaDonChiTiet.TongTien), 0) AS 'DoanhThu'
                FROM 
                    SanPham
                LEFT JOIN 
                    HoaDonChiTiet ON SanPham.id_SanPham = HoaDonChiTiet.id_SanPham
                GROUP BY 
                    SanPham.MaSanPham, 
                    SanPham.TenSanPham, 
                    SanPham.GiaBan, 
                    SanPham.SoLuong
                ORDER BY 
                    SoSPDaBan DESC;
                """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = ThongKe.builder()
                        .maSP(rs.getString("MaSP"))
                        .tenSP(rs.getString("TenSP"))
                        .giaSP(rs.getFloat("GiaSP"))
                        .tongSP(rs.getInt("TongSoLuongSP"))
                        .daBan(rs.getInt("SoSPDaBan"))
                        .doanhThu(rs.getFloat("DoanhThu"))
                        .build();
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
