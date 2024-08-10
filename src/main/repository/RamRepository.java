package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.Ram;

public class RamRepository {
    public ArrayList<Ram> getAll(){
        ArrayList<Ram> listRam = new ArrayList<>();
        String sql = """
                     SELECT [id_Ram]
                           ,[MaRam]
                           ,[DungLuongRam]
                           ,[TrangThai]
                       FROM [dbo].[Ram]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ram ram = Ram.builder()
                        .IdRam(rs.getInt(1))
                        .MaRam(rs.getString(2))
                        .DungLuongRam(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listRam.add(ram);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listRam;
    }
    
    public Boolean add(Ram ram) {
        String sql = """
                     INSERT INTO [dbo].[Ram]
                                ([DungLuongRam]
                                ,[TrangThai])
                          VALUES
                                (?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ram.getDungLuongRam());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean update(Ram ram, Integer IdRam){
        String sql = """
                     UPDATE [dbo].[Ram]
                        SET [DungLuongRam] = ?
                      WHERE id_Ram = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ram.getDungLuongRam());
            ps.setObject(2, IdRam);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Ram getRamByMa(String maRam){
        String query = """
                      SELECT [id_Ram]
                            ,[MaRam]
                            ,[DungLuongRam]
                            ,[TrangThai]
                      FROM [dbo].[Ram]
                       WHERE [MaRam] = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maRam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ram ram = Ram.builder()
                        .IdRam(rs.getInt(1))
                        .MaRam(rs.getString(2))
                        .DungLuongRam(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                return ram;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
