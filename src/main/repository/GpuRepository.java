package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.Gpu;

public class GpuRepository {
    public ArrayList<Gpu> getAll(){
        ArrayList<Gpu> listGpu = new ArrayList<>();
        String sql = """
                     SELECT [id_GPU]
                           ,[MaGPU]
                           ,[TenGPU]
                           ,[TrangThai]
                       FROM [dbo].[GPU]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Gpu gpu = Gpu.builder()
                        .IdGPU(rs.getInt(1))
                        .MaGPU(rs.getString(2))
                        .TenGPU(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                listGpu.add(gpu);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listGpu;
    }
    
    public Boolean add(Gpu gpu) {
        String sql = """
                     INSERT INTO [dbo].[GPU]
                                ([TenGPU]
                                ,[TrangThai])
                          VALUES
                                (?,1)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, gpu.getTenGPU());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Boolean update(Gpu gpu, Integer IdGpu){
        String sql = """
                     UPDATE [dbo].[GPU]
                        SET [TenGPU] = ?
                      WHERE id_GPU = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, gpu.getTenGPU());
            ps.setObject(2, IdGpu);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public Gpu getGpuByMa(String maGPU){
        String query = """
                      SELECT [id_GPU]
                            ,[MaGPU]
                            ,[TenGPU]
                            ,[TrangThai]
                        FROM [dbo].[GPU]
                       WHERE [MaGPU] = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maGPU);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gpu gpu = Gpu.builder()
                        .IdGPU(rs.getInt(1))
                        .MaGPU(rs.getString(2))
                        .TenGPU(rs.getString(3))
                        .TrangThai(rs.getInt(4))
                        .build();
                return gpu;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
