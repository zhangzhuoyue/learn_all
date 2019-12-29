package 测试用例.b4互斥锁如何用一把锁保护多个资源.没有关联关系使用细粒度锁;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(123,"2134");
        Thread thread1 = new Thread(() -> {
            account.withdraw(12);
        });
        Thread thread2 = new Thread(() -> {
            account.getBalance();
        });
        thread1.start();
        thread2.start();
    }
}
