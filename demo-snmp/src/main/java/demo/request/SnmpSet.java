package demo.request;

import demo.utils.SnmpUtil;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.VariableBinding;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SnmpSet extends AbstractSnmpRequest {
    @Override
    protected void send(Snmp snmp, CommunityTarget target, String oid) throws IOException {
    }

    @Override
    protected void send(Snmp snmp, CommunityTarget target, List<String> oids) throws IOException {
    }

    @Override
    protected void send(Snmp snmp, CommunityTarget target, Map<String, Object> oids) throws IOException {
        PDU pdu = createPdu(oids, PDU.SET);
        sendPdu(snmp, target, pdu);
    }


}
