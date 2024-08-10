package main.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {
    public static boolean save(File src){
        File dst = new File("src\\main\\images", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs(); // tao thu muc
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("src\\main\\images", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
