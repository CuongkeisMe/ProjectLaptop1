/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

/**
 *
 * @author Admin
 */
public class Vouchers {

    private int idVoucher;
    private String maVoucher;

    // Constructor
    public Vouchers(int idVoucher, String maVoucher) {
        this.idVoucher = idVoucher;
        this.maVoucher = maVoucher;
    }

    // Getter and Setter
    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    @Override
    public String toString() {
        return maVoucher; // Hiển thị mã voucher trong JComboBox
    }
}


