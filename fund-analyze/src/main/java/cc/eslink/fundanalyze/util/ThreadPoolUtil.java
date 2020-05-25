package cc.eslink.fundanalyze.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName ThreadPoolUtil
 *@Description 自定义线程池
 *@Author zeng.yakun (0178)
 *@Date 2019/11/29 9:49
 *@Version 1.0
 **/
public class ThreadPoolUtil {

    private static final Log logger = LogFactory.getLog(ThreadPoolUtil.class);

    private static final int poolSize = Runtime.getRuntime().availableProcessors() * 2;

    // 任务超过队列，抛出RejectedExecutionException
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(poolSize, poolSize,
            0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(65535),
            new ThreadPoolExecutor.AbortPolicy());

    public static void execute(Runnable command) {
        try {
            pool.execute(command);
        } catch (Exception e) {
            logger.info(String.format("线程池状态：%s", pool.toString()), e);
            throw e;
        }
    }
}
