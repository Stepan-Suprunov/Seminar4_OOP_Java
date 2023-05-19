package Sample01;

import java.util.Arrays;

public class Sample01 {

    public static void main(String[] args) {

        SimpleAccount simpleAccount1 = new SimpleAccount(3001, 10500);
        int simpleAccount1Id = simpleAccount1.getId();
        System.out.println(simpleAccount1);

        UniversalAccount universalAccount1 = new UniversalAccount("AA11BB", 1100.15);
        System.out.println(universalAccount1);
        UniversalAccount universalAccount2 = new UniversalAccount(5002, 1100.15);
        System.out.println(universalAccount2);
        UniversalAccount universalAccount3 =
                new UniversalAccount(new CustomAccountIdentifier(111, "AA"), 1100.15);
        System.out.println(universalAccount3);

        Account<CustomAccountIdentifier> account1 =
                new Account<>(new CustomAccountIdentifier(111, "AA"), 10500);
        CustomAccountIdentifier accountId1 = account1.getId();
        Account<String> account2 = new Account<>("AAA11BB", 10500);
        Account<Integer> account3 = new Account<>(1133, 10500);

        Integer[] array1 = {1, 8, -5, 12, 0, -9};
        String[] array2 = {"AAA", "BBB", "CCCC", "DDDD"};

        Object[] newArray = (Integer[]) ArrayUtils.replaceTwoElements(array1, 1, 2);

        String[] newArray2 = ArrayUtils.replaceTwoElementsV2(array2, 1, 2);
        System.out.println(Arrays.toString(newArray2));
    }
}

class ArrayUtils {

    public static Object[] replaceTwoElements(Object[] array, int index1, int index2) {

        Object[] newArray = array.clone();
        if (index1 >= newArray.length || index2 >= newArray.length || index1 < 0 || index2 < 0)
            return null;
        Object obj = newArray[index1];
        newArray[index1] = newArray[index2];
        newArray[index2] = obj;
        return newArray;
    }

    public static <T> T[] replaceTwoElementsV2(T[] array, int index1, int index2) {

        T[] newArray = array.clone();
        if (index1 >= newArray.length || index2 >= newArray.length || index1 < 0 || index2 < 0)
            return null;
        T obj = newArray[index1];
        newArray[index1] = newArray[index2];
        newArray[index2] = obj;
        return newArray;
    }
}

class Account<T> {

    private final T id;
    private double amount;

    public T getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account(T id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сумма средств на счёте #%s : %.2f руб.", id, amount);
    }
}

class UniversalAccount {

    private final Object id;
    private double amount;

    public Object getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UniversalAccount(Object id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сумма средств на счёте #%s : %.2f руб.", id, amount);
    }
}

class CustomAccountIdentifier {

    private int id;
    private String prefix;

    public int getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public CustomAccountIdentifier(int id, String prefix) {
        this.id = id;
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "CustomAccountIdentifier{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}

class SimpleAccount {
    private final int id;
    private double amount;

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SimpleAccount(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сумма средств на счёте #%s : %.2f руб.", id, amount);
    }
}

