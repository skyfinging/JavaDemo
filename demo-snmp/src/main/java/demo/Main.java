package demo;

import demo.oid.DeviceTable;
import demo.oid.OidDefine;
import demo.request.SnmpGet;
import demo.request.SnmpSet;
import demo.request.SnmpWalk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String ip = "10.10.107.25";


//        queryNeParamValue(ip, "1.3.6.1.4.1.17518.3601.4336.3878.0.1");
//        queryNeParamValue(ip, "1.3.6.1.4.1.17518.3601.4336.3878.0.2");
//        queryNeParamValue(ip, "1.3.6.1.4.1.17518.3601.4336.3878.0.3");
//        queryNeParamValue(ip, "1.3.6.1.4.1.17518.3601.4336.3878.0.4");
//        setNeParamValue(ip);
//        walkNeParam(ip, 4336,null);
//        getNeName(ip, 4336);
//        getNeList(ip);
        getNeAlarmList(ip);
//        reQueryParamList(ip, 4319);

//        snmpGet.doRequest(ip, OidDefine.OID_NE_ALARM_LIST+".4311.1.0.3");


    }

    public static void queryNeParamValue(String ip, String oid) throws IOException {
        //查询网元参数值
        SnmpGet snmpGet = new SnmpGet();
        snmpGet.doRequest(ip, oid);
        System.out.println("\n");
    }

    public static void setNeParamValue(String ip) throws IOException {
        //设置网元参数值
        Map<String, Object> map = new HashMap<>();
        Integer neId = 4336;
        Integer paramId = 3878;
        map.put("1.3.6.1.4.1.17518.3601.4336.3878.0.1", "13800000000");
        SnmpSet snmpSet = new SnmpSet();
        snmpSet.doRequest(ip,map);
    }

    public static void walkNeParam(String ip, Integer neId, Integer paramid) throws IOException {
        //遍历指定网元的某个参数
        SnmpWalk tester = new SnmpWalk();
        if(paramid!=null)
            tester.doRequest(ip, "1.3.6.1.4.1.17518.3601."+neId+"."+paramid);
        else
            tester.doRequest(ip, "1.3.6.1.4.1.17518.3601."+neId);
    }

    public static void getNeName(String ip, Integer neId) throws IOException {
        //查询网元的名称
        SnmpGet snmpGet = new SnmpGet();
        snmpGet.doRequest(ip, OidDefine.OID_NE_LIST+"."+neId+".0.1");
        System.out.println("\n");
    }

    public static void getNeList(String ip) throws IOException {
        //遍历网元列表
        SnmpWalk tester = new SnmpWalk();
        tester.doRequest(ip, "1.3.6.1.4.1.17518.3602");
        System.out.println("\n");
    }

    public static void getNeAlarmList(String ip) throws IOException {
        SnmpWalk tester = new SnmpWalk();
        tester.doRequest(ip, "1.3.6.1.4.1.17518.3603.4336");
        System.out.println("\n");
    }

    public static void reQueryParamList(String ip, Integer neId) throws IOException {
        //重查网元的参数列表
        Map<String, Object> map = new HashMap<>();
        map.put("1.3.6.1.4.1.17518.3604.0", neId);
        SnmpSet snmpSet = new SnmpSet();
        snmpSet.doRequest(ip, map);
    }
}
