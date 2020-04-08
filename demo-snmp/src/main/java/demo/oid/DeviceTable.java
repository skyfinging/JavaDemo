package demo.oid;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.mo.*;
import org.snmp4j.smi.*;

/**
 * 代码引用自https://blog.csdn.net/iteye_4371/article/details/82514990
 *
 */
public class DeviceTable {

    private static DefaultMOTable<DefaultMOTableRow, MOColumn, DefaultMOMutableTableModel<DefaultMOTableRow>> table;
    public static final String tableEntryOid = OidDefine.OID_DEVICE_PARAM;


}
