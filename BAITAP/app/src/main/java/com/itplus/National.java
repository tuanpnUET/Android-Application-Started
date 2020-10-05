package com.itplus;

public class National {
    private int flag;
    private String name;
    private String population;
    private String area;
    public National(int flag, String name, String pop, String Area){
        this.flag = flag;
        this.name = name;
        this.population = pop;
        this.area = Area;
    }
    public void setPopulation(String population) {
        this.population = population;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getFlag() {
        return flag;
    }
}
