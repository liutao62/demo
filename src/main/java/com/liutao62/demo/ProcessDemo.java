package com.liutao62.demo;

import java.util.*;

public class ProcessDemo {

    public static List<PCB> runQueue = new ArrayList<>();
    public static List<PCB> readyQueue = new ArrayList();
    public static List<PCB> readyQueueClone = new ArrayList();  //备份就绪队列
    public static List<PCB> waitQueue = new ArrayList();

    public static final int PROCESS_NUM = 10;       //进程数量

    public static int systemRunedTime = 0;          //系统运行时间

    public static final int SLICE_TIME = 3;         //时间片大小

    public static void main(String[] args) {

        for (int i = 0; i < PROCESS_NUM; i++) {
            PCB pcb = new PCB(i, i * 2
                    , (int) (Math.random() * 19 + 1), (int) (Math.random() * 10 + 1)
                    , 2, 0);
            readyQueue.add(pcb);
            readyQueueClone.add(new PCB(pcb.id, pcb.arrivalTime, pcb.runTime, pcb.prior, pcb.state, pcb.runedTime));
        }
        for (PCB pcb : readyQueue) {
            System.out.println(pcb);
        }
        System.out.println("----------");
        FCFS();
        //fcfs结束后重置系统运行时间
        systemRunedTime = 0;
        System.out.println("----------");
        noDeprivationPriority();
        systemRunedTime = 0;
        readyQueue.clear();
        for (PCB pcb : readyQueueClone) {
            readyQueue.add(pcb);
        }
        System.out.println("----------");
        SPF();
        systemRunedTime = 0;
        readyQueue.clear();
        for (PCB pcb : readyQueueClone) {
            readyQueue.add(pcb);
        }
        System.out.println("----------");
        timeSlice();
    }

    public static void FCFS() {
        System.out.println("先来先服务");
        System.out.println("进程号 到达时间 运行时间 等待时间 周转时间 结束时间");
        PCB temp;
        for (int i = 0; i < readyQueue.size(); i++) {
            temp = readyQueue.get(i);
            if (i == 0) {
                System.out.println(i + "\t\t" + temp.arrivalTime + "\t\t" + temp.runTime + "\t\t"
                        + 0 + "\t\t" + (temp.runTime - systemRunedTime) + "\t\t" + (systemRunedTime += temp.runTime));
            } else {
                System.out.println(i + "\t\t" + temp.arrivalTime + "\t\t" + temp.runTime + "\t\t"
                        + (systemRunedTime - temp.arrivalTime) + "\t\t" + (temp.runTime + systemRunedTime - temp.arrivalTime)
                        + "\t\t" + (systemRunedTime += temp.runTime));
            }
        }
    }

    public static void timeSlice() {
        System.out.println("时间片轮转");
        System.out.println("时间片大小：" + SLICE_TIME);
        PCB temp = new PCB();
        List<PCB> bufQueue = new ArrayList();

        List<Integer> timePionts = new ArrayList();
        for (PCB pcb : readyQueue) {
            timePionts.add(pcb.arrivalTime);
        }
        if (timePionts.size() == PROCESS_NUM) {
            //获取第一个到达进程的到达时间
            systemRunedTime += timePionts.get(0);
            timePionts.remove(0);
        }
        while (readyQueue.size() != 0) {
            //获取已到进程
            if (readyQueue.size() != 0) {
                for (PCB pcb : readyQueue) {
                    if (pcb.arrivalTime <= systemRunedTime && pcb.state == 2) {
                        bufQueue.add(pcb);
                    }
                }
            }

            if (bufQueue.size() != 0) {
                for (PCB pcb : bufQueue) {
                    if (pcb.state == 2) {
                        temp = pcb;
                        if (temp.runTime < SLICE_TIME && temp.runTime != 0) {
                            systemRunedTime += temp.runTime;
                            temp.turnTime = systemRunedTime - temp.arrivalTime;
                            temp.runedTime += temp.runTime;
                            temp.runTime = 0;
                            temp.state = 0;
                            waitQueue.add(temp);
                            readyQueue.remove(temp);
                        } else {
                            systemRunedTime += SLICE_TIME;
                            temp.turnTime = systemRunedTime - temp.arrivalTime;
                            temp.runTime -= SLICE_TIME;
                            temp.runedTime += SLICE_TIME;
                            temp.state = 3;
                        }
                        bufQueue.remove(temp);
                        System.out.println("当前运行：" + temp.print() + "结束时间 " + systemRunedTime);
                        break;
                    }
                }
            }else {
                bufQueue.clear();
                if (readyQueue.size() != 0) {
                    for (PCB pcb : readyQueue) {
                        if (pcb.state != 0) {
                            pcb.state = 2;
                        }
                    }
                }else break;

            }
            bufQueue.clear();
        }
        System.out.println("进程号 到达时间 运行时间 等待时间 周转时间 结束时间 已运行时间");
        for (PCB pcb : waitQueue)
        {
            System.out.println(pcb.id + "\t\t" + pcb.arrivalTime + "\t\t" + pcb.runTime + "\t\t"
                    + (pcb.turnTime - pcb.runTime) + "\t\t" + pcb.turnTime
                    + "\t\t" + (pcb.turnTime + pcb.arrivalTime) + "\t  " + pcb.runedTime);
        }

    }

    public static void noDeprivationPriority() {
        System.out.println("非剥夺优先");
        System.out.println("进程号 到达时间 运行时间 等待时间 周转时间 结束时间 优先级");
        PCB temp;
        List<PCB> bufQueue = new ArrayList();
        while (readyQueue.size() != 0) {
            int maxPriorIndex = -1;
            int tempPrior = 0;
            //筛选系统空闲时已到的进程并添加到bufQueue中
            for (int i = 0; i < readyQueue.size(); i++) {
                temp = readyQueue.get(i);
                if (temp.arrivalTime <= systemRunedTime) {
                    bufQueue.add(temp);
                }
            }
            //如果系统空闲时（空闲前有进程到达）
            if (bufQueue.size() != 0) {
                //对bufQueue进行遍历找出存在进程的最高优先级及其索引
                for (int i = 0; i < bufQueue.size(); i++) {
                    if (bufQueue.get(i).prior > tempPrior) {
                        tempPrior = bufQueue.get(i).prior;
                        maxPriorIndex = i;
                    }
                }
                temp = bufQueue.get(maxPriorIndex);
            } else {
                temp = readyQueue.get(0);
            }
            systemRunedTime += temp.runTime;
            temp.turnTime = systemRunedTime - temp.arrivalTime;
            readyQueue.remove(temp);
            System.out.println(temp.id + "\t\t" + temp.arrivalTime + "\t\t" + temp.runTime + "\t\t"
                    + (temp.turnTime - temp.runTime) + "\t\t" + temp.turnTime
                    + "\t\t" + systemRunedTime + "\t  " + temp.prior);
            //每次完毕清除缓存
            bufQueue.clear();
        }
    }

    public static void SPF() {
        System.out.println("最短优先");
        System.out.println("进程号 到达时间 运行时间 等待时间 周转时间 结束时间");
        PCB temp;
        List<PCB> bufQueue = new ArrayList();
        while (readyQueue.size() != 0) {
            int minRuntimeIndex = -1;
            int tempRuntime = 999;
            //筛选系统空闲时已到的进程并添加到bufQueue中
            for (int i = 0; i < readyQueue.size(); i++) {
                temp = readyQueue.get(i);
                if (temp.arrivalTime <= systemRunedTime) {
                    bufQueue.add(temp);
                }
            }
            //如果系统空闲时（空闲前有进程到达）
            if (bufQueue.size() != 0) {
                //对bufQueue进行遍历找出存在进程的最短运行时间及其索引
                for (int i = 0; i < bufQueue.size(); i++) {
                    if (bufQueue.get(i).runTime < tempRuntime) {
                        tempRuntime = bufQueue.get(i).runTime;
                        minRuntimeIndex = i;
                    }
                }
                temp = bufQueue.get(minRuntimeIndex);
            } else {
                temp = readyQueue.get(0);
            }
            systemRunedTime += temp.runTime;
            temp.turnTime = systemRunedTime - temp.arrivalTime;
            readyQueue.remove(temp);
            System.out.println(temp.id + "\t\t" + temp.arrivalTime + "\t\t" + temp.runTime + "\t\t"
                    + (temp.turnTime - temp.runTime) + "\t\t" + temp.turnTime
                    + "\t\t" + systemRunedTime);

            bufQueue.clear();
        }
    }
}

class PCB {
    public int id;          //进程id
    public int arrivalTime; //到达时间
    public int runTime;     //运行时间
    public int prior;       //优先级   1-10越大优先级越高
    public int state;       //状态    1：运行 2：就绪 3：等待 0：结束
    public int runedTime;   //已运行时间

    public int turnTime = 0;    //等待时间

    @Override
    public String toString() {
        return "PCB{" +
                "id=" + id +
                ", 到达时间=" + arrivalTime +
                ", 运行时间=" + runTime +
                ", 优先级=" + prior +
                ", 状态=" + (state == 1 ? "运行" : (state == 2 ? "就绪" : "等待")) +
                ", 已运行时间=" + runedTime +
                '}';
    }

    public String print() {
        return "PCB{" +
                "id=" + id +
                ", 到达时间=" + arrivalTime +
                ", 运行时间=" + runTime +
                ", 状态=" + (state == 1 ? "运行" : (state == 2 ? "就绪" : "等待")) +
                ", 已运行时间=" + runedTime +
                ", 周转时间=" + turnTime +
                '}';
    }

    public PCB(int id, int arrivalTime, int runTime, int prior, int state, int runedTime) {

        this.id = id;
        this.arrivalTime = arrivalTime;
        this.runTime = runTime;
        this.prior = prior;
        this.state = state;
        this.runedTime = runedTime;
    }

    public PCB() {
    }
}
