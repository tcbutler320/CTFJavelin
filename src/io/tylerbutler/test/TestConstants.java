package io.tylerbutler.test;

import io.tylerbutler.tools.Nmap;

public class TestConstants {

    public static final String NMAP = " root@kali $ nmap -A -T4 192.168.1.1"+
        "\nStarting Nmap ( http://nmap.org )"+
        "\nNmap scan report for felix (127.0.0.1)"+
        "\nThe 1640 ports scanned but not shown below are in state: closed)"+
        "\nPORT     STATE SERVICE    VERSION"+
        "\n21/tcp   open  ftp        WU-FTPD wu-2.6.1-20"+
        "/n22/tcp   open  ssh        OpenSSH 3.1p1 (protocol 1.99)"+
        "/n53/tcp   open  domain     ISC BIND 9.2.1"+
        "\n79/tcp   open  finger     Linux fingerd"+
        "\n111/tcp  open  rpcbind    2 (rpc #100000)"+
        "\n443/tcp  open  ssl/http   Apache httpd 2.0.39 ((Unix) mod_perl/1.99_04-dev)"+
        "\n515/tcp  open  printer"+
        "\n631/tcp  open  ipp        CUPS 1.1"+
        "\n953/tcp  open  rndc?"+
        "\n5000/tcp open  ssl/ftp    WU-FTPD wu-2.6.1-20"+
        "\n5001/tcp open  ssl/ssh    OpenSSH 3.1p1 (protocol 1.99)"+
        "\nDevice type: general purpose"+
        "\nRunning: Linux 2.4.X|2.5.X"+
        "\nOS details: Linux Kernel 2.4.0 - 2.5.20"+
        "\nNmap finished: 1 IP address (1 host up) scanned in 42.494 seconds";


    public static final String MAINOUTPUT_PRE_PROCESSING_EXAMPLE = "root@kali $ nmap -A -T4 192.168.1.1\n" +
            "\n" +
            "Starting Nmap ( http://nmap.org )\n" +
            "Nmap scan report for felix (127.0.0.1)\n" +
            "(The 1640 ports scanned but not shown below are in state: closed)\n" +
            "PORT     STATE SERVICE    VERSION\n" +
            "21/tcp   open  ftp        WU-FTPD wu-2.6.1-20\n" +
            "22/tcp   open  ssh        OpenSSH 3.1p1 (protocol 1.99)\n" +
            "53/tcp   open  domain     ISC BIND 9.2.1\n" +
            "79/tcp   open  finger     Linux fingerd\n" +
            "111/tcp  open  rpcbind    2 (rpc #100000)\n" +
            "443/tcp  open  ssl/http   Apache httpd 2.0.39 ((Unix) mod_perl/1.99_04-dev)\n" +
            "515/tcp  open  printer\n" +
            "631/tcp  open  ipp        CUPS 1.1\n" +
            "953/tcp  open  rndc?\n" +
            "5000/tcp open  ssl/ftp    WU-FTPD wu-2.6.1-20\n" +
            "5001/tcp open  ssl/ssh    OpenSSH 3.1p1 (protocol 1.99)\n" +
            "5002/tcp open  ssl/domain ISC BIND 9.2.1\n" +
            "5003/tcp open  ssl/finger Linux fingerd\n" +
            "6000/tcp open  X11        (access denied)\n" +
            "8000/tcp open  http-proxy Junkbuster webproxy\n" +
            "8080/tcp open  http       Apache httpd 2.0.39 ((Unix) mod_perl/1.99_04-dev)\n" +
            "8081/tcp open  http       Apache httpd 2.0.39 ((Unix) mod_perl/1.99_04-dev)\n" +
            "Device type: general purpose\n" +
            "Running: Linux 2.4.X|2.5.X\n" +
            "OS details: Linux Kernel 2.4.0 - 2.5.20\n" +
            "\n" +
            "Nmap finished: 1 IP address (1 host up) scanned in 42.494 seconds";

}
