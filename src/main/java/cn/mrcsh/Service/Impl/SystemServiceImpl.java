package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.CPU;
import cn.mrcsh.Entity.Memory;
import cn.mrcsh.Service.SystemService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class SystemServiceImpl implements SystemService {


    public List<Memory> getMem() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 椎内存使用情况
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();


        // 总的物理内存
        String totalMemorySize = new DecimalFormat("#.##")
                .format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024);
        // 剩余的物理内存
        String freePhysicalMemorySize = new DecimalFormat("#.##")
                .format(osmxb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024);
        // 已使用的物理内存
        String usedMemory = new DecimalFormat("#.##").format(
                (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024);

        List<Memory> memories = new ArrayList<>();
        double used = 1.0 - Double.valueOf(freePhysicalMemorySize) / Double.valueOf(totalMemorySize);
        used*=100;
        String usedResult = String.valueOf(used).substring(0,String.valueOf(used).indexOf("."));
        memories.add(new Memory("总内存",totalMemorySize+"G"));
        memories.add(new Memory("已使用内存",usedMemory+"G"));
        memories.add(new Memory("剩余内存",freePhysicalMemorySize+"G"));
        memories.add(new Memory("内存占用率",usedResult+"%"));
        return memories;
    }

    private List<CPU> getCpus() throws InterruptedException {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        TimeUnit.SECONDS.sleep(1);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        String cores = String.valueOf(processor.getLogicalProcessorCount()); // 核心数
        String usage = new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu); // 使用率
        String user_usage = new DecimalFormat("#.##%").format(user * 1.0 / totalCpu); // 用户使用率
        String wait_usage = new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu); // 等待率
        String lazy_usage = new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu);// 空闲率
        List<CPU> list = new ArrayList<>();
        list.add(new CPU("核心数", cores));
        list.add(new CPU("使用率", usage));
        list.add(new CPU("用户使用率", user_usage));
        list.add(new CPU("空闲率", lazy_usage));
        return list;
    }

    @Override
    public List<CPU> getCPUInfo() {
        try {
            return getCpus();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Memory> getMemoryInfo() {
        return getMem();
    }
}
