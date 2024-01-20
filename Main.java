import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Notebook> set = new HashSet<>();
        set.add(new Notebook("Notebook № 1", 8, "Windows", 92000, "Acer"));
        set.add(new Notebook("Notebook № 2", 16, "IOS", 120000, "MacBook Air"));
        set.add(new Notebook("Notebook № 3", 32, "Windows10", 71000, "Asus"));
        set.add(new Notebook("Notebook № 4", 64, "IOS", 100000, "MacBook Pro"));

        Operations operation = new Operations(set);
        operation.start();
    }
} 
    

