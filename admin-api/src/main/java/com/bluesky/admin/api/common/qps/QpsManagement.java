package com.bluesky.admin.api.common.qps;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.qps
 * @date 2019/7/26 14:27
 */
public final class QpsManagement {
    private static final Map<String, AtomicInteger> QPS_MAP = new ConcurrentHashMap<>();
    static {
        new ThreadPoolExecutor(1, 1 ,5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1)).execute(()->{
            try{
                while (!Thread.currentThread().isInterrupted()){
                    TimeUnit.SECONDS.sleep(1);
                    QPS_MAP.clear();
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                Thread.interrupted();
            }
        });
    }

    /**
     * 添加aps次数
     * @param qpsName
     */
    public static void addAndIncrement(String qpsName){
        QPS_MAP.computeIfAbsent(qpsName, k->new AtomicInteger(0)).getAndIncrement();
    }

    /**
     *
     * @param name
     * @return
     */
    public static Integer getQpsByName(String name){
        return QPS_MAP.getOrDefault(name, new AtomicInteger(0)).get();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("===============test");
        new Thread(()->{
            while (true){
                addAndIncrement("ttt");
            }
        }).start();

        new Thread(()->{
            while (true){
                System.out.println("ttt qps: "+ getQpsByName("ttt") + "/s");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.in.read();
    }
}
