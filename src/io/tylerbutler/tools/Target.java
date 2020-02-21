package io.tylerbutler.tools;

public class Target {

    private String ip;
    private int uniqueTargetId;
    private String alias;
    private String details;
    private double difficulty;
    private int totalPorts;
    private int sumOpenPorts;
    private int sumClosePorts;
    private String[] targetDetails;

    public void Target(String ip, String alias, String details, double difficulty, int totalPorts, int sumOpenPorts, int sumClosePorts) {
        this.ip = ip;
        this.alias = alias;
        this.details = details;
        this.difficulty = difficulty;
        this.totalPorts = totalPorts;
        this.sumOpenPorts = sumOpenPorts;
        this.sumClosePorts = sumClosePorts;
    }

    // TODO Make method to return formated target details
    private String[] getTargetDetails(int uniqueTargetId){

        return targetDetails;
    }




}
