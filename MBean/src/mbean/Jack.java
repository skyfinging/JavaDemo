package mbean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Jack extends NotificationBroadcasterSupport implements  JackMBean {
    private static long seq = 0L;
    private String name;

    @Override
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;

        //广播事件
        seq++;
        Notification notification = new Notification("setName", this ,seq, System.currentTimeMillis(), "oldName:"+oldName+" -> newName:"+name);
        sendNotification(notification);
    }

    @Override
    public String getName() {
        seq++;
        //广播事件
        Notification notification = new Notification("getName", this ,seq, System.currentTimeMillis(), "getName:"+name);
        sendNotification(notification);
        return name;
    }
}
