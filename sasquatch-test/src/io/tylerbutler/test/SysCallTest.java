package io.tylerbutler.test;

import io.tylerbutler.utils.SystemCall;

import java.util.Scanner;

public class SysCallTest {

    public static void main(String[] args) {
        SystemCall sc = new SystemCall();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Command");
        sc.SystemCall(scan.next());

    }
}
