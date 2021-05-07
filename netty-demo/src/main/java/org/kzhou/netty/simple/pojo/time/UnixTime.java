package org.kzhou.netty.simple.pojo.time;

import java.util.Date;

/**
 * @Description: 时间协议类
 * @author: Admin
 * @date: 2021年05月07日 15:09
 */
public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }

}
