package edu.cugb.jts.cugber.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SystemTimeLimit {

    @Data
    @AllArgsConstructor
    public static class SysTime {
        private final int time;
        private final TimeUnit unit;
        public long calculateMilliSeconds() {
            long millis = unit.toMillis(time);
            log.debug(millis + "");
            return millis;
        }
    }
    // Default system time limit.
    public final static SysTime DEFAULT_LOSE_REMAINING_TIME = new SysTime(30, TimeUnit.DAYS);
    public final static SysTime DEFAULT_GO_TOGETHER_REMAINING_TIME = new SysTime(15, TimeUnit.DAYS);
    public final static SysTime DEFAULT_QUEST_REMAINING_TIME = new SysTime(10, TimeUnit.DAYS);
    public final static SysTime DEFAULT_ADMIN_BEHAVIOUR_LOG_REMAINING_TIME = new SysTime(365, TimeUnit.DAYS);
    public final static SysTime DEFAULT_USER_FEEDBACK_REMAINING_TIME = new SysTime(365, TimeUnit.DAYS);
    public final static SysTime USER_TOKEN_REMAINING_TIME = new SysTime(10, TimeUnit.HOURS);
}
