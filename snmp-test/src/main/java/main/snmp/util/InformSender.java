package main.snmp.util;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultTcpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InformSender {

    private Snmp snmp;

    public void Connect() throws IOException {
        TransportMapping<?> mapping = new DefaultTcpTransportMapping();
        snmp = new Snmp(mapping);
    }

    private static  Variable getSysUpTimeVariable(long currentTime){
        TimeTicks result = new TimeTicks(0);
        long i = 0L;
        if(i == 0L || currentTime == 0L){
        }else{
            result.setValue((currentTime - i)/10);
        }
        return result;
    }

    public static Target getTargetV2(final String address, final String community){
        Address targetAddress = GenericAddress.parse(address);
        String str = community==null?"":community;
        Target target = new CommunityTarget(targetAddress, new OctetString(str));
        target.setAddress(targetAddress);
        target.setRetries(3);       //当发送trap的时候不生效，发送Inform的时候会生效
        target.setTimeout(1 * 1000);//等待回应时间
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

    private List<VariableBinding> getVariable(){
        List<VariableBinding> variables = new ArrayList<>();
        VariableBinding upTimeVB = new VariableBinding();
        upTimeVB.setOid(SnmpConstants.sysUpTime);
        Variable upTimeVar = getSysUpTimeVariable(System.currentTimeMillis());
        upTimeVB.setVariable(upTimeVar);
        variables.add(upTimeVB);
        return variables;
    }

    public boolean sendInform(Target target) throws IOException {
        List<VariableBinding> variables = getVariable();
        return sendInform(target, variables);
    }

    private boolean sendInform(Target target, List<VariableBinding> variables) throws IOException {
        PDU pdu = new PDU();
        for(VariableBinding vb : variables) {
            pdu.add(vb);
        }

        ResponseEvent response = snmp.inform(pdu, target);
        if(response==null)
            return true;
        if(response.getResponse()==null)
            return false;
        if(response.getError()!=null)
            return false;
        return true;
    }

    public boolean sendTrap(Target target) throws IOException {
        List<VariableBinding> variables = getVariable();
        return sendTrap(target, variables);
    }

    private boolean sendTrap(Target target, List<VariableBinding> variables) throws IOException {
        PDU pdu = new PDU();
        pdu.setType(PDU.TRAP);
        for(VariableBinding vb : variables) {
            pdu.add(vb);
        }

        snmp.send(pdu, target);
        return true;
    }
}
