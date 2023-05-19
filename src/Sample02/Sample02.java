package Sample02;

public class Sample02 {

    public static void main(String[] args) {

        DebetAccount<Entity> entityDebetAccount1 =
                new DebetAccount<>(new Entity("Рога и Копыта", "112233000"), 20000000);
        System.out.println(entityDebetAccount1);

        CreditAccount<Person> personCreditAccount1 =
                new CreditAccount<>(new Person("Иванов", "Андрей", "11122222"), 20);
        System.out.println(personCreditAccount1);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Transaction<Account<?>> transaction1 = new Transaction<>(entityDebetAccount1, personCreditAccount1, 20000);
        transaction1.execute();

        Transaction<Account<?>> transaction2 = new Transaction<>(entityDebetAccount1, personCreditAccount1, 30000);
        transaction2.execute();
    }
}

class Transaction<T extends Account<? extends PersonalData>> {

    private final T from;
    private final T to;
    private final double amount;

    public Transaction(T from, T to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public void execute() {

        if (from.getAmount() >= amount) {
            System.out.printf("Осуществлён перевод средств между счетами на сумму %.2f руб.\n", amount);
            System.out.printf("Счёт списания: #%s: %.2 руб.\nСчет зачисления: #%s: %.2 руб.\n",
                    from.getData(), from.getAmount(), to.getData(), to.getAmount());
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            System.out.println("Текущее состояние счетов:");
            System.out.println(from);
            System.out.println(to);
        } else {
            System.out.println("Операция невозможна, недостаточно средств на счёте");
        }
    }
}

class DebetAccount<T extends PersonalData> extends Account<T> {

    public DebetAccount(T data, double amount) {
        super(data, amount);
    }
}

class CreditAccount<T extends PersonalData> extends Account<T> {

    public CreditAccount(T data, double amount) {
        super(data, amount);
    }
}

interface PersonalData {

    String getINN();
}

abstract class Account<T extends PersonalData> {

    private final T data;
    private double amount;

    public T getData() {
        return data;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account(T data, double amount) {
        this.data = data;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s; %.2f руб.", data, amount);
    }
}

/**
 * Физическое лицо
 */
class Person implements PersonalData {

    private final String lastName;
    private final String firstName;
    private final String inn;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getINN() {
        return inn;
    }

    public Person(String lastName, String firstName, String inn) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.inn = inn;
    }

    @Override
    public String toString() {
        return String.format("%s; %s %s", inn, lastName, firstName);
    }
}

/**
 * Юридическое лицо
 */
class Entity implements PersonalData {

    private final String name;
    private final String inn;

    public String getName() {
        return name;
    }

    public String getINN() {
        return inn;
    }

    public Entity(String name, String inn) {
        this.name = name;
        this.inn = inn;
    }

    @Override
    public String toString() {
        return String.format("%s; %s", inn, name);
    }
}
