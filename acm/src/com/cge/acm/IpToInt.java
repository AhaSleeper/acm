package com.cge.acm;

/**
 * ip地址转为int整数
 */
public class IpToInt {
    public static int ipToInt(String ip) {
        //取ip的各段
        String[] ipStr = ip.split("\\.");
        int rs = 0;
        for(int i=0; i<ipStr.length; i++){
            //将ip的每一段解析为int，并根据位置左移8位
            int intSlice = Integer.parseInt(ipStr[i]) << 8 * i;
            rs = rs | intSlice;
        }
        return rs;
    }

    public static String intToIp(int ipInt){
        String[] ipString = new String[4];
        for(int i=0; i<4; i++){
            int pos = i * 8;
            int and = ipInt & (255 << pos);
            ipString[i] = String.valueOf(and >>> pos);
        }
        return String.join(".", ipString);
    }
    public static void main(String[] args){
        System.out.println("ipToInt="+ipToInt("192.168.1.9"));
        System.out.println("intToIp="+intToIp(151103680));
    }
}
