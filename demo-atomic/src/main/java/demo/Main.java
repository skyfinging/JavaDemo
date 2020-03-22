package demo;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1, "a");
        User user2 = new User(2, "b");
        User user3 = new User(1, "a");

        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(user1);
        reference.getAndSet(user3);
        System.out.println(user1.equals(user3));
        System.out.println(reference.compareAndSet(user1, user2));
        System.out.println(reference.get());
        System.out.println(reference.compareAndSet(user3, user2));

        System.out.println("AtomicStampedReference");
        AtomicStampedReference<User> reference1 = new AtomicStampedReference<>(user1, 1);
        int stamp = reference1.getStamp();
        System.out.println(reference1.compareAndSet(user1, user2, stamp, stamp + 1));
        stamp = reference1.getStamp();
        System.out.println(reference1.compareAndSet(user1, user3, stamp, stamp + 1));

        System.out.println(reference1.compareAndSet(user2, user3, stamp - 1, stamp + 1));
        System.out.println(reference1.compareAndSet(user2, user3, stamp, stamp + 1));
    }
}