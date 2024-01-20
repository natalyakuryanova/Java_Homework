import java.util.*;

public class Operations {

    private Set<Notebook> notebooks = new HashSet<>();
    private List<Filter> filterList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void printList(){
        for (Notebook notebook : notebooks){
            if (notebookIsCorrect(notebook)){
                System.out.println(notebook);
            }
        }
    }

    public boolean notebookIsCorrect(Notebook notebook){

        for (Filter filter : filterList){
            Object valueNotebook = null;

            if (filter.property.equals("name")){
                valueNotebook = notebook.getName();
            }else if (filter.property.equals("ram")){
                valueNotebook = notebook.getRAM();
            }else if (filter.property.equals("os")){
                valueNotebook = notebook.getOS();
            }else if (filter.property.equals("price")){
                valueNotebook = notebook.getPrice();
            }else if (filter.property.equals("model")){
                valueNotebook = notebook.getModel();
            }else {
                continue;
            }

            if (filter.value != null && !filter.value.equals(valueNotebook)){
                return false;
            }

            if (filter.maxValue != null && filter.maxValue < Double.parseDouble(Objects.toString(valueNotebook))){
                return false;
            }

            if (filter.minValue != null && filter.minValue > Double.parseDouble(Objects.toString(valueNotebook))){
                return false;
            }
        }

        return true;
    }
    public Operations(Set<Notebook> notebooks) {
        this.scanner = new Scanner(System.in);
        this.notebooks = notebooks;
    }

    public Operations(Set<Notebook> notebooks, List<Filter> filterList) {
        this.scanner = new Scanner(System.in);
        this.notebooks = notebooks;
        this.filterList = filterList;
    }

    public Set<String> quantitativeSelection(){
        Set<String> set = new HashSet<>();
        set.add("ram");
        set.add("price");

        return set;
    }

    public Set<String> stringSelection(){
        Set<String> set = new HashSet<>();

        set.add("name");
        set.add("os");
        set.add("model");

        return set;
    }

    public void start(){

        boolean flag = true;
        while (flag){
            String text = "Выберите операцию: \n " +
                "1. Добавить фильтр  \n " +
                "2. Вывести список ноутбуков \n " +
                "3. Завершить";

            System.out.println(text);
            String operation = scanner.next();

            if (operation.equals("3")){
                flag = false;
                scanner.close();
                continue;
            }
            else if (operation.equals("2")){
                printList();

            }else if(operation.equals("1")){
                Map<String, String> map = new LinkedHashMap<>();

                map.put("name", "1. Наименование");
                map.put("ram", "2. Объем оперативной памяти");
                map.put("os", "3. Операционная система");
                map.put("price", "4. Цена");
                map.put("model", "5. Модель");

                System.out.println("Выберите необходимое из списка: ");

                for (String value : map.values()) {
                    System.out.println(value);
                }
                
                int filter = scanner.nextInt();
                
                if (filter - 1 < 0 || filter - 1 > map.size() - 1){
                    System.out.println("Введено некорректное значение");
                    continue;
                }
                
                Set<String> keySet = map.keySet();
                List<String> listKeys = new ArrayList<String>(keySet);
                String property = listKeys.get(filter - 1);

                Filter filterObject = null;
                try {
                    if (quantitativeSelection().contains(property)){
                        filterObject = Filter.startGetting(scanner, property, true);
                    }else {
                        filterObject = Filter.startGetting(scanner, property, false);
                    }
                }catch (Exception e){
                    System.out.println("Ошибка при выборе фильтра");
                    continue;
                }
                if (filterObject != null){
                    System.out.println("Фильтр добавлен");
                    filterList.add(filterObject);
                }
            }
        }
    }
}


class Filter {

    Object value;
    Double minValue;
    Double maxValue;
    boolean isQuantitative;
    String property;

    public Filter(String property, boolean isQuantitative, Object value, Double minValue, Double maxValue) {
        this.property = property;
        this.isQuantitative = isQuantitative;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static Filter startGetting(Scanner scanner, String property, boolean isQuantitative) {

        if (isQuantitative) {

            String quest = "Выберите тип фильтра: " +
                    "\n 1. Значение" +
                    "\n 2. Меньше" +
                    "\n 3. Больше" +
                    "\n 4. Интервал";
            
            System.out.println(quest);
            String text = scanner.next();

            Filter filter = null;

            if (text.equals("1")) {
                System.out.println("Введите значение поиска: ");
                int getValue = scanner.nextInt();
                filter = new Filter(property, isQuantitative, getValue, null, null);
            } else if (text.equals("2")) {
                System.out.println("Введите максимальное предельное значение: ");
                double getValue = scanner.nextDouble();
                filter = new Filter(property, isQuantitative, null, null, getValue);
            } else if (text.equals("3")) {
                System.out.println("Введите минимальное предельное значение: ");
                double getValue = scanner.nextDouble();
                filter = new Filter(property, isQuantitative, null, getValue, null);
            } else if (text.equals("4")) {
                System.out.println("Введите минимальное предельное значение: ");
                double getMin = scanner.nextDouble();
                System.out.println("Введите максимальное предельное значение: ");
                double getMax = scanner.nextDouble();
                filter = new Filter(property, isQuantitative, null, getMin, getMax);
            }

            return filter;
        }

        System.out.println("Введите значение поиска: ");
        String getValue = scanner.next();
        return new Filter(property, isQuantitative, getValue, null, null);
    }
}