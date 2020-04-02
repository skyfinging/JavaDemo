package main.snmp.util;

import org.snmp4j.*;
import org.snmp4j.mp.StateReference;
import org.snmp4j.mp.StatusInformation;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.transport.DefaultTcpTransportMapping;

public class InformReceiver implements CommandResponder {
    private int port;
    private Snmp snmp;

    public InformReceiver(int port){
        this.port = port;
    }

    public void startListener() {
        try {
            TransportMapping transport = new DefaultTcpTransportMapping(
                    new TcpAddress(port));
            snmp = new Snmp(transport);
            snmp.addCommandResponder(this);
            snmp.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processPdu(CommandResponderEvent event) {
        System.out.print("receive Message ：");
        PDU pdu = event.getPDU();
        System.out.println(pdu);

        if(pdu.getType()==PDU.INFORM) {
            //如果是Inform消息，需要回应，如果不回应，那么客户端就会重复inform消息
            pdu.setErrorIndex(0);
            pdu.setErrorStatus(0);
            pdu.setType(PDU.RESPONSE);

            StatusInformation statusInformation = new StatusInformation();
            StateReference ref = event.getStateReference();
            try {
                event.getMessageDispatcher().returnResponsePdu(
                        event.getMessageProcessingModel(),
                        event.getSecurityModel(), event.getSecurityName(),
                        event.getSecurityLevel(), pdu,
                        event.getMaxSizeResponsePDU(), ref, statusInformation);
            } catch (MessageException ex) {
                ex.printStackTrace();
            }
        }
    }
}
