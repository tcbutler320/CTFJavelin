package io.tylerbutler.tools;

import io.tylerbutler.utils.*;

public class TerminalNavigation {
    SystemCall sysCall = new SystemCall();
    String out;

    public String getLs() {
        out = sysCall.SystemCall("ls");
        return out;
    }

    public String getPwd() {
        out = sysCall.SystemCall("pwd");
        return out;
    }

    public String mkdir() {
        out = sysCall.SystemCall("mkdir");
        return out;
    }
}
