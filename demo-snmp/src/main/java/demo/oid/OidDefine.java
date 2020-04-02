package demo.oid;

public class OidDefine {
    public static final String OID_DEVICE_PARAM = "1.3.6.1.4.1.17518.3601";
    public static final String OID_NE_LIST = "1.3.6.1.4.1.17518.3602";
    public static final String OID_NE_ALARM_LIST = "1.3.6.1.4.1.17518.3603";

    //参数原值（没转义之前的），完整OID 1.3.6.1.4.1.17518.123.${网元id}.${参数id}.0.1
    public static final String OID_DEVICE_PARAM_VALUE = "0.1";
    //参数名称，完整OID 1.3.6.1.4.1.17518.123.${网元id}.${参数id}.0.2
    public static final String OID_DEVICE_PARAM_NAME = "0.2";
    //参数读写性，完整OID 1.3.6.1.4.1.17518.123.${网元id}.${参数id}.0.3
    public static final String OID_DEVICE_PARAM_RW = "0.3";
    //参数的网管显示值（转义之后的），完整OID 1.3.6.1.4.1.17518.123.${网元id}.${参数id}.0.3
    public static final String OID_DEVICE_PARAM_VALUE_LABEL = "0.4";
}
