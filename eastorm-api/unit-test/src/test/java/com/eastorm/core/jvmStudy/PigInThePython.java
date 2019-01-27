package com.eastorm.core.jvmStudy;

import java.util.ArrayList;
import java.util.List;

/**
 * 7.8头猪
 *
 *
 * -Xms2g -Xmx2g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Xmn25m
 * 9.3
 *
 * -Xms2g -Xmx2g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Xmn256m
 * 8.6
 *
 * -Xms1g -Xmx1g -XX:+UseParallelGC -XX:+UseParNewGC -Xmn768m
 * 8.8
 *
 *  -Xms1g -Xmx1g -XX:+UseParallelGC  -Xmn76m
 *  8.8
 *
 *  -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Xmn25m
 *  8.7
 * Created by ASUS on 2018/3/18.
 */
public class PigInThePython {
    static volatile List pigs=new ArrayList();
    static volatile int pigsEaten=0;
    static final int ENOUGH_PIGS=500;

    public static void main(String[] args) {
        new PigEater().start();
        new PigDigester().start();
    }

    static class PigEater extends Thread{

        @Override
        public void run() {
            while(true){
                pigs.add(new byte[32*1024*1024]);

                if(pigsEaten>ENOUGH_PIGS){
                    return;
                }

                takeANap(100);
            }
        }
    }

    static class PigDigester extends Thread{

        @Override
        public void run() {

            long start=System.currentTimeMillis();


            while(true){

                takeANap(2000);
                pigsEaten+=pigs.size();
                System.out.println("pigsEaten:"+pigsEaten);
                pigs=new ArrayList();
                if(pigsEaten>ENOUGH_PIGS){
                    System.out.format("Digested %d pigs in %d ms.%n", pigsEaten, System.currentTimeMillis() - start);
                    return;
                }
            }
        }
    }

    static void takeANap(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
