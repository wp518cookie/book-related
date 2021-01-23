package com.ping.wu.refactoring_guru.structure.bridge.remotes;

import com.ping.wu.refactoring_guru.structure.bridge.devices.Device;

/**
 * @author wuping
 * @date 2020-11-06
 */

public class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device device) {
        super.device = device;
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
