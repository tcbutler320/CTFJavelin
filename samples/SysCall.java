
alvin alexander
        favorite books
        A Survival Guide for New Consultants
        my book at amazon
        categories

        alaska (25)
        android (138)
        best practices (63)
        career (50)
        colorado (21)
        cvs (27)
        design (33)
        drupal (120)
        eclipse (6)
        funny (3)
        gadgets (108)
        git (15)
        intellij (4)
        java (429)
        jdbc (26)
        swing (74)
        jsp (9)
        latex (26)
        linux/unix (289)
        mac os x (315)
        mysql (54)
        ooa/ood (11)
        perl (156)
        php (97)
        postgresql (17)
        programming (43)
        ruby (56)
        scala (640)
        sencha (23)
        servlets (10)
        technology (84)
        testing (13)
        uml (24)
        zen (47)

        Running system commands in Java applications
        By Alvin Alexander. Last updated: June 3 2016

        UPDATE: This article has been replaced by my newer "Java exec with ProcessBuilder and Process" article. While the Java code shown in this tutorial works on simple "Java exec" cases, the new article shows how to properly read the output streams from your system command in Java threads, and also how to write to your command's standard input, if necessary.

        Feel free to read this article for background/legacy information, but I strongly recommend that you use the source code I'm sharing in my newer "Java exec" article, because it resolves the standard input, output, and error problems that I didn't handle properly in the code below.

        Introduction

        I've read a lot about Java but one of the things I rarely see discussed is how you should go about running external system commands. Of course, you probably don't read much about this because it takes away from the portability of Java applications. For instance, if you write a Java application on a Unix system, you might be interested in running the "ps -ef" command, and reading the output of the command. For Unix systems this is great, but unfortunately, this same program won't work on a Windows system because the ps command isn't available on Windows.

        Well, we're going to forget about portability for this article, and demonstrate a method that can be used to run system commands. We've received a lot of requests about this topic, so here goes.
        Discussion (Runtime exec and Process)

        Executing a system command is relatively simple - once you've seen it done the first time. It involves the use of two Java classes, the Runtime class and the Process class. Basically, you use the exec method of the Runtime class to run the command as a separate process. Invoking the exec method returns a Process object for managing the subprocess. Then you use the getInputStream() and getErrorStream() methods of the Process object to read the normal output of the command, and the error output of the command. What you do with the output of the command executed is entirely up to you and the application you're creating.

        (Note: There is also a getOutputStream() method that you can use to write to the process, but we won't cover that method in this article. We'll cover that and a few other advanced features in a future article.)
        A Java exec example

        The code shown in Listing 1 provides a working example of our "Java exec" technique in a file named JavaRunCommand.java.

import java.io.*;

public class JavaRunCommand {

    public static void main(String args[]) {

        String s = null;

        try {

            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("ps -ef");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}