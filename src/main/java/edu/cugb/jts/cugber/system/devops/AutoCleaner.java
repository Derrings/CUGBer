package edu.cugb.jts.cugber.system.devops;

import edu.cugb.jts.cugber.common.SystemTimeLimit;
import edu.cugb.jts.cugber.util.TimeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Calculating the execute time and call clean() method
 * to finish clean task.
 *  doItTime ——
 *  clean() —— An abstract method implements by subclass.
 *  setDoItTime() —— Enable users the ability to control
 * execute time.
 * @author Derrings
 */
@Slf4j
public abstract class AutoCleaner extends Thread{
    private SystemTimeLimit.SysTime doItTime = null;
    protected abstract void clean();

    public void setDoItTime(SystemTimeLimit.SysTime doItTime) {
        this.doItTime = doItTime;
    }

    @SneakyThrows
    @Override
    public void run() {
        if (doItTime == null) {
            doItTime = new SystemTimeLimit.SysTime(TimeUtil.nowToMidNight(), TimeUnit.SECONDS);
        }
        sleep(doItTime.calculateMilliSeconds());
        clean();
    }
}
