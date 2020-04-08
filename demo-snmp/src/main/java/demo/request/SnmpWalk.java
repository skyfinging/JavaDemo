package demo.request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import demo.utils.SnmpUtil;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;


/**
 * 代码引用自https://www.iteye.com/blog/sjsky-1032772
 * SnmpWalk
 * @author Michael
 *
 */
public class SnmpWalk extends AbstractSnmpRequest{

    protected void send(Snmp snmp, CommunityTarget target, String oid) throws IOException {
        PDU pdu = createPdu(oid, PDU.GETNEXT);
        boolean finished = false;
        while (!finished) {
            ResponseEvent response = snmp.send(pdu, target);
            VariableBinding vb;
            PDU responsePDU = response.getResponse();
            if (null == responsePDU) {
                System.out.println("responsePDU == null");
                break;
            } else {
                vb = responsePDU.get(0);
            }
            // check finish
            finished = checkWalkFinished(new OID(oid), responsePDU, vb);
            if (!finished) {
                System.out.println(vb.getOid()+"--"+SnmpUtil.getChinese(vb.toValueString()));
                pdu.setRequestID(new Integer32(0));
                pdu.set(0, vb);
            }
        }
    }

    protected void send(Snmp snmp, CommunityTarget target, List<String> oids) throws IOException {
        if(oids==null)
            return;
        for(String oid : oids){
            send(snmp, target, oid);
        }
    }

    @Override
    protected void send(Snmp snmp, CommunityTarget target, Map<String, Object> oids) throws IOException {

    }

    private boolean checkWalkFinished(OID targetOID, PDU responsePDU,
                                      VariableBinding vb) {
        boolean finished = false;
        if (responsePDU.getErrorStatus() != 0) {
            System.out.println("responsePDU.getErrorStatus() != 0 ");
            System.out.println(responsePDU.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            System.out.println("vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            System.out.println("vb.getOid().size() < targetOID.size()");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            System.out.println("targetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            System.out.println("Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            System.out.println("Variable received is not "
                    + "lexicographic successor of requested " + "one:");
            System.out.println(vb.toString() + " <= " + targetOID);
            finished = true;
        }
        return finished;

    }


}