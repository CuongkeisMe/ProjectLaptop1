package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.Pin;

public class PinRepository {
    public ArrayList<Pin> getAll(){
        ArrayList<Pin> listPin = new ArrayList<>();
        String sql = """
                     SELECT [id_Pin]
                           ,[MaPin]
                           ,[DungLuongPin]
                           ,[TrangThai]
                       FROM [dbo].[Pin]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pin pin = Pin.builder()
                        .IdPin(rs.getInt(1))
                        .MaPin(rs.getString(2))
                        .DungLuongPin(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listPin.add(pin);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listPin;
    }
    
    public Boolean add(Pin pin) {
        String sql = """
                     INSERT INTO [dbo].[Pin]
                                ([DungLuongPin]
                                ,[TrangThai])
                          VALUES
                                (?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pin.getDungLuongPin());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean update(Pin pin, Integer IdPin){
        String sql = """
                     UPDATE [dbo].[Pin]
                        SET [DungLuongPin] = ?
                      WHERE id_Pin = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pin.getDungLuongPin());
            ps.setObject(2, IdPin);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Pin getPinByMa(String maPin){
        String query = """
                     SELECT [id_Pin]
                           ,[MaPin]
                           ,[DungLuongPin]
                           ,[TrangThai]
                     FROM [dbo].[Pin]
                       WHERE [MaPin] = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maPin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pin pin = Pin.builder()
                        .IdPin(rs.getInt(1))
                        .MaPin(rs.getString(2))
                        .DungLuongPin(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                return pin;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
