package demo.utils;

import org.snmp4j.transport.AbstractTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.function.Supplier;

public enum  SnmpProtocolEnum {
    UDP("udp", SnmpProtocolEnum::getUdpTransportMapping);

    private String name;
    private Supplier<AbstractTransportMapping> supplier;

    SnmpProtocolEnum(String name, Supplier<AbstractTransportMapping> supplier){
        this.name = name;
        this.supplier = supplier;
    }

    public String getName(){
        return name;
    }

    public AbstractTransportMapping getTransportMapping() {
        return supplier.get();
    }

    private static AbstractTransportMapping getUdpTransportMapping(){
        AbstractTransportMapping udp = null;
        try {
            udp = new DefaultUdpTransportMapping();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return udp;
    }
}
