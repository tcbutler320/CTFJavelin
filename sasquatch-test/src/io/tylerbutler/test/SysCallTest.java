package io.tylerbutler.test;

import io.tylerbutler.tools.TerminalNavigation;
import io.tylerbutler.utils.*;

public class SysCallTest {

    public static void main(String[] args) {

//        Test if a featured tool can pass argument and print to console
//        **************************************************************
//        *** un comment below for a basic console test of a tool *****
//        SystemCall sc = new SystemCall();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter Command");
//        sc.SystemCall(scan.next());

        SystemCall sc = new SystemCall();                             // create instance of system call
        TerminalNavigation termNav = new TerminalNavigation();        // create instance of terminal nav
        System.out.println("LS Command");
        sc.SystemCall(termNav.getLs());                               // use terminal nav to run ls command
        System.out.println("PWD Command");
        sc.SystemCall(termNav.getPwd());                               // use terminal nav to run ls command

    }
}
