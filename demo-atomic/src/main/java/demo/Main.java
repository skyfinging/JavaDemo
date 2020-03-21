package demo;

import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1,"a");
        User user2 = new User(2,"b");
        User user3 = new User(1,"a");

        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(user1);
        reference.getAndSet(user3);
        System.out.println(user1.equals(user3));
        System.out.println(reference.compareAndSet(user1, user2));
        System.out.println(reference.get());
    }
}
