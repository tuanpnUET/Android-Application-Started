package com.qlns;

public class NhanSu {
    private String id;
    private String HoTen;
    private String NgaySinh;
    private String DiaChi;

    public NhanSu(String id, String hoTen, String ngaySinh, String diaChi) {
        this.id = id;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhanSu{" +
                "id='" + id + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", NgaySinh='" + NgaySinh + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                '}';
    }
}
