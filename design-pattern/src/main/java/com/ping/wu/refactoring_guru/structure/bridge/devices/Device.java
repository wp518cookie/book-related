package com.ping.wu.refactoring_guru.structure.bridge.devices;

/**
 * @author wuping
 * @date 2020-11-05
 */

public interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}
