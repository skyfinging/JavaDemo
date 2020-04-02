package listener;

import mbean.ServerInfoMBean;

import javax.management.Notification;
import javax.management.NotificationListener;

public class JackChangeNameListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        if(!(handback instanceof ServerInfoMBean))
            return;
        ServerInfoMBean serverInfoMBean = (ServerInfoMBean) handback;
        serverInfoMBean.print();
        System.out.println(serverInfoMBean.getName()+","+notification.getMessage());
    }
}
