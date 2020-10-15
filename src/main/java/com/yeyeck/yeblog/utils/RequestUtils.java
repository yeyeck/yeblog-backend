package com.yeyeck.yeblog.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class RequestUtils {

    private RequestUtils() {}

    public static String getRequestIp(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-Real-IP");
        if (isValidIP(ipAddress)) {
            return ipAddress;
        }
        ipAddress = request.getHeader("X-Forwarded-For");
        if (isValidIP(ipAddress)) {
            return ipAddress.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    private static boolean isValidIP(String ip) {
        return ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip);
    }
}
