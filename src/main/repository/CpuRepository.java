package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.Cpu;

public class CpuRepository {
    public ArrayList<Cpu> getAll(){
        ArrayList<Cpu> listCPU = new ArrayList<>();
        String sql = """
                     SELECT [id_CPU]
                           ,[MaCPU]
                           ,[TenCPU]
                           ,[TrangThai]
                       FROM [dbo].[CPU]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cpu cpu = Cpu.builder()
                        .IdCPU(rs.getInt(1))
                        .MaCPU(rs.getString(2))
                        .TenCPU(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listCPU.add(cpu);
            }     
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listCPU;
    }
    
    public Boolean add(Cpu cpu) {
        String sql = """
                     INSERT INTO [dbo].[CPU]
                                ([TenCPU]
                                ,[TrangThai])
                          VALUES
                                (?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, cpu.getTenCPU());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean update(Cpu cpu, Integer IdCpu){
        String sql = """
                     UPDATE [dbo].[CPU]
                        SET [TenCPU] = ?
                      WHERE id_CPU = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, cpu.getTenCPU());
            ps.setObject(2, IdCpu);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Cpu getCpuByMa(String maCPU){
        String query = """
                      SELECT [id_CPU]
                            ,[MaCPU]
                            ,[TenCPU]
                            ,[TrangThai]
                        FROM [dbo].[CPU]
                       WHERE [MaCPU] = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maCPU);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cpu cpu = Cpu.builder()
                        .IdCPU(rs.getInt(1))
                        .MaCPU(rs.getString(2))
                        .TenCPU(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                return cpu;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
