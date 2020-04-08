package demo.utils;
import org.snmp4j.smi.*;


public class SnmpUtil {

    public static String getVbValue(VariableBinding vb){
        return getChinese(vb.toValueString());
    }

    public static String getChinese(String octetString) {    //snmp4j遇到中文直接转成16进制字符串
        if(octetString!=null && octetString.contains(":")) {
            try {
                String[] temps = octetString.split(":");
                byte[] bs = new byte[temps.length];
                for (int i = 0; i < temps.length; i++)
                    bs[i] = (byte) Integer.parseInt(temps[i], 16);
                return new String(bs, "UTF8");
            } catch (Exception e) {
                return octetString;
            }
        }else
            return  octetString;
    }
}
