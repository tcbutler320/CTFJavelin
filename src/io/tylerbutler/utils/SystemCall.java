package io.tylerbutler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SystemCall gets input from tools in io.tylerbutler.tools and runs them as bash commands
 */
public class SystemCall {

    /**
     * @param argument
     * @return
     */


    public String SystemCall(String argument) {

        String s = null;
        String fullString = null;

        try {

            Process p = Runtime.getRuntime().exec(argument);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                fullString += s;
            }
            System.out.println("where am i");
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.out.println("This is the final String, Lets test \n"+s);
        } catch (IOException e) {
            System.out.println("{!} Error:\n");
            e.printStackTrace();
            System.exit(-1);
        }

        return s;
    }

}