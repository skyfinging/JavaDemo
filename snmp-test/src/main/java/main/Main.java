package main;

import main.snmp.util.InformReceiver;
import main.snmp.util.InformSender;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args){

        InformReceiver receiver = new InformReceiver(10062);
        receiver.startListener();
        System.out.println("snmp start listener");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }


        InformSender sender = new InformSender();
        try {
            sender.Connect();
            Target target = InformSender.getTargetV2("tcp:127.0.0.1/10062","");

            //发送inform
            boolean result = sender.sendInform(target);
            if(result==true)
                System.out.println("snmp send inform succ.");
            else
                System.out.println("snmp send inform fail.");

            //发送trap,trap只管发送不管结果
            result = sender.sendTrap(target);
            if(result)
                System.out.println("snmp send trap succ.");
            else
                System.out.println("snmp send trap fail.");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { }
    }
}
