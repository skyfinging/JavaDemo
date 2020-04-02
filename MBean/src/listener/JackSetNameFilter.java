package listener;

import javax.management.Notification;
import javax.management.NotificationFilter;

public class JackSetNameFilter implements NotificationFilter {
    @Override
    public boolean isNotificationEnabled(Notification notification) {
        if(!notification.getType().equals("setName"))
            return false;
        return true;
    }
}
