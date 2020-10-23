package org.o7planning.demosqliteclass;

public class Sosim {
    private int id;
    private String so;
    private String gia;

    public Sosim() {
    }

    public Sosim(int id, String so, String gia) {
        this.id = id;
        this.so = so;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "Sosim{" +
                "id=" + id +
                ", so='" + so + '\'' +
                ", gia='" + gia + '\'' +
                '}';
    }
}
