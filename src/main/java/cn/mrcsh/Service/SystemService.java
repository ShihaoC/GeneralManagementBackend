package cn.mrcsh.Service;

import cn.mrcsh.Entity.CPU;
import cn.mrcsh.Entity.Memory;

import java.util.List;

public interface SystemService {
    List<CPU> getCPUInfo();

    List<Memory> getMemoryInfo();
}
