package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.ManHinh;

public class ManHinhRepository {

    public ArrayList<ManHinh> getAll() {
        ArrayList<ManHinh> listManHinh = new ArrayList<>();
        String sql = """
                     SELECT [id_ManHinh]
                           ,[MaManHinh]
                           ,[KichThuoc]
                           ,[TrangThai]
                       FROM [dbo].[ManHinh]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ManHinh mh = ManHinh.builder()
                        .IdManHinh(rs.getInt(1))
                        .MaManHinh(rs.getString(2))
                        .KichThuoc(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listManHinh.add(mh);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listManHinh;
    }

    public Boolean add(ManHinh manhinh) {
        String sql = """
                     INSERT INTO [dbo].[ManHinh]
                                ([KichThuoc]
                                ,[TrangThai])
                          VALUES
                                (?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, manhinh.getKichThuoc());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Boolean update(ManHinh manhinh, Integer IdManhinh) {
        String sql = """
                     UPDATE [dbo].[ManHinh]
                        SET [KichThuoc] = ?
                      WHERE id_ManHinh = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, manhinh.getKichThuoc());
            ps.setObject(2, IdManhinh);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ManHinh getManHinhByMa(String maManHinh) {
        String query = """
                      SELECT [id_ManHinh]
                            ,[MaManHinh]
                            ,[KichThuoc]
                            ,[TrangThai]
                      FROM [dbo].[ManHinh]
                      WHERE [MaManHinh] = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maManHinh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ManHinh manhinh = ManHinh.builder()
                        .IdManHinh(rs.getInt(1))
                        .MaManHinh(rs.getString(2))
                        .KichThuoc(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                return manhinh;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
