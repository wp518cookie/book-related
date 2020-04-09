package com.ping.wu.facade;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class FacadePattern {
    private Cpu cpu;
    private Ssd ssd;
    private Ddr ddr;
    public FacadePattern() {
        this.cpu = new Cpu();
        this.ssd = new Ssd();
        this.ddr = new Ddr();
    }

    public void open() {
        this.cpu.startCpu();
        this.ssd.startSsd();
        this.ddr.startDdr();
    }
}
