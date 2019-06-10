package listener;

import javax.management.Notification;
import javax.management.NotificationFilter;

public class JackGetNameFilter implements NotificationFilter {
    @Override
    public boolean isNotificationEnabled(Notification notification) {
        if(!notification.getType().equals("getName"))
            return false;
        return true;
    }
}
