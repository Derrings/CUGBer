package edu.cugb.jts.cugber.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class TimeUtil {
    public static int nowToMidNight() {
        Calendar nowTime = Calendar.getInstance();
        int hour = nowTime.get(Calendar.HOUR_OF_DAY);
        int minute = nowTime.get(Calendar.MINUTE);
        int second = nowTime.get(Calendar.SECOND);
        int total = 24 * 3600 - hour * 3600 - minute * 60 - second;
        log.debug(total + "");
        return total;
    }
}
