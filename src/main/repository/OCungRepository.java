package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.OCung;

public class OCungRepository {
    public ArrayList<OCung> getAll(){
        ArrayList<OCung> listOCung = new ArrayList<>();
        String sql = """
                     SELECT [id_OCung]
                           ,[MaOCung]
                           ,[LoaiOCung]
                           ,[TrangThai]
                       FROM [dbo].[OCung]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                OCung oc = OCung.builder()
                        .IdOCung(rs.getInt(1))
                        .MaOCung(rs.getString(2))
                        .LoaiOCung(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listOCung.add(oc);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listOCung;
    }
    
    public Boolean add(OCung ocung) {
        String sql = """
                     INSERT INTO [dbo].[OCung]
                                ([LoaiOCung]
                                ,[TrangThai])
                          VALUES
                                (?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ocung.getLoaiOCung());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean update(OCung ocung, Integer IdOcung){
        String sql = """
                     UPDATE [dbo].[OCung]
                        SET [LoaiOCung] = ?
                      WHERE id_OCung = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ocung.getLoaiOCung());
            ps.setObject(2, IdOcung);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public OCung getOCungByMa(String maOCung){
        String query = """
                      SELECT [id_OCung]
                             [MaOCung]
                            ,[LoaiOCung]
                            ,[TrangThai]
                      FROM [dbo].[OCung]
                       WHERE [MaOCung] = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maOCung);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OCung oc = OCung.builder()
                        .IdOCung(rs.getInt(1))
                        .MaOCung(rs.getString(2))
                        .LoaiOCung(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                return oc;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
