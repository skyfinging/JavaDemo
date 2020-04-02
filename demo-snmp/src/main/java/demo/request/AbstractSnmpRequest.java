package demo.request;

import demo.utils.SnmpProtocolEnum;
import demo.utils.SnmpUtil;
import lombok.Getter;
import lombok.Setter;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.AbstractTransportMapping;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Getter
@Setter
public abstract class AbstractSnmpRequest {

    protected SnmpProtocolEnum protocol = SnmpProtocolEnum.UDP;
    protected int port = 161;
    protected int snmpVersion = SnmpConstants.version1;
    protected long timeout = 2*1000000L;
    protected int retry = 3;
    protected String community = "public";

    public <T> void  doRequest(String ip, T oid) throws IOException {
        String address =  protocol.getName() + ":" + ip + "/" + port;
        CommunityTarget target = createCommunityTarget(address, community, snmpVersion, timeout, retry);
        AbstractTransportMapping transport = null;
        Snmp snmp = null;
        try {
            transport = protocol.getTransportMapping();
            transport.listen();
            snmp = new Snmp(transport);

            // 发送同步消息
            send(snmp, target, oid);
        }finally {
            closeSnmp(snmp,transport);
        }
    }

    private <T> void send(Snmp snmp, CommunityTarget target, T oid) throws IOException {
        if(oid instanceof String){
            send(snmp, target, (String) oid);
        }else if(oid instanceof List){
            send(snmp, target, (List<String>) oid);
        }else if(oid instanceof Map) {
            send(snmp, target, (Map) oid);
        }else
            System.out.println("not support:"+oid.getClass());
    }

    protected abstract void send(Snmp snmp, CommunityTarget target, String oid) throws IOException;

    protected abstract void send(Snmp snmp, CommunityTarget target, List<String> oids) throws IOException;

    protected abstract void send(Snmp snmp, CommunityTarget target, Map<String,Object> oids) throws IOException;

    protected void sendPdu(Snmp snmp, CommunityTarget target, PDU pdu) throws IOException {
        ResponseEvent response = snmp.send(pdu, target);
        PDU responsePdu = response.getResponse();
        if (responsePdu == null) {
            System.out.println("请求超时");
        } else {
            Vector<? extends VariableBinding> vbVect = responsePdu.getVariableBindings();
            if (vbVect.size() == 0) {
                System.out.println(" 返回数据为空 ");
            } else {
                vbVect.iterator().forEachRemaining(vb->
                        System.out.println(vb.getOid()+" = "+ SnmpUtil.getVbValue(vb)));
            }
        }
    }

    public static CommunityTarget createCommunityTarget(String address,
                                                        String community, int version, long timeOut, int retry) {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(targetAddress);
        target.setVersion(version);
        target.setTimeout(timeOut); // milliseconds
        target.setRetries(retry);
        return target;
    }

    public static void closeSnmp(Snmp snmp, TransportMapping transport){
        if (snmp != null) {
            try {
                snmp.close();
            } catch (IOException ex1) {
                snmp = null;
            }
        }
        if (transport != null) {
            try {
                transport.close();
            } catch (IOException ex2) {
                transport = null;
            }
        }
    }

    protected static PDU createPdu(List<String> oids, int pduType) {
        PDU pdu = new PDU();
        for(String oid: oids)
            pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(pduType);
        return pdu;
    }

    protected static PDU createPdu(String oid, int pduType) {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(pduType);
        return pdu;
    }

    protected static PDU createPdu(Map<String, Object> oids, int pduType){
        PDU pdu = new PDU();
        pdu.setType(pduType);
        Iterator<Map.Entry<String,Object>> iterator = oids.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String oid = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof Integer)
                pdu.add(new VariableBinding(new OID(oid), new Counter32((Integer)value)));
            else
                pdu.add(new VariableBinding(new OID(oid), new OctetString(""+value)));
        }
        return pdu;
    }
}
