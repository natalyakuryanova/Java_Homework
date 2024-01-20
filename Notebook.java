import java.util.ArrayList;
import java.util.List;

public class Notebook {

    private String name;
    private int ram;
    private String os;
    private int price;
    private String model;

    public Notebook(String name, int ram, String os, int price, String model) {
        this.name = name;
        this.ram = ram;
        this.os = os;
        this.price = price;
        this.model = model;
    }

    public static List<String> propertiesForFilter(){
        List<String> list = new ArrayList<>();
        list.add("ram");
        list.add("os");
        list.add("price");
        list.add("model");

        return list;
    }

    @Override
    public String toString() {
        return "Ноутбук: (" + name + "): " +
                "количество опретивной памяти:" + ram +
                ", операционная система: " + os + '\'' +
                ", цена: " + price +
                ", модель: " + model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRAM() {
        return ram;
    }

    public void setRAM(int ram) {
        this.ram = ram;
    }

    public String getOS() {
        return os;
    }

    public void setOS(String os) {
        this.os = os;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}