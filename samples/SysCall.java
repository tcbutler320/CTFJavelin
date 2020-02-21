
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

Listing 1 (above): The file JavaRunCommand.java shows how you can run an external system command from within a Java program.
        How our Java exec code works

        The first thing you do is specify the command you want to run by supplying this command to the Runtime class. Because you can't create your own instance of the Runtime class, you first use the getRuntime method to access the current runtime environment and then invoke the Runtime exec method. This returns a Process object.

        Everything else you do involves methods of the Process object. In this case, because we're running the "ps -ef" command on a Unix system, we just need to read the output of the command. Reading the standard error probably isn't required in this case, but I thought at the very least it was at least worth showing, if not good programming practice.

        I convert the input streams with the InputStreamReader and BufferedReader so I can use the readLine() method of the BufferedReader class. Because I use these classes, this application will not compile properly with an older JDK 1.0.x compiler (these classes weren't available in 1.0.x).
        Download the "Java exec" example source code

        I could go on at length about this topic, but the best thing I can recommend is that you download the source code and work with it for a while. Try running different commands to see if you can get them to work properly, and try to run a command that requires input (this will be a bit more complicated).

        To download the JavaRunCommand.java source code shown in Listing 1, click here. Once the file is displayed in your browser you can select the File | Save As ... option of your browser to save the code to your local filesystem.
        java
        system
        shell
        runtime
        run
        java exec
        java
        execute
        exec
        command
        bufferedreader

        Java exec: How to execute a system command pipeline in Java
        Perl “exec”: How to execute system commands
        A Java class that writes to and reads from a remote socket
        Java exec - execute system processes with Java ProcessBuilder and Process (part 2)
        Java exec: Execute system processes with Java ProcessBuilder and Process (part 1)
        Java exec - execute system processes with Java ProcessBuilder and Process (part 3)

        books i’ve written
        [Scala Cookbook] 	[How I Sold My Business] 	[A Survival Guide for New Consultants] 	[Functional Programming, Simplified]

        Steve Sarkisian, University of Colorado football coach (or not)
        First day I could walk for 15 minutes
        Latest idea for a story
        Luke Skywalker black and white “life sketch”
        The Chicken Oil Company, Bryan (College Station), Texas
        Polar bear photos (mom and cub)

        Comments

        Submitted by Ananth (not verified) on August 20, 2009 - 5:37am
        Permalink
        Running system command remotely

        Hi
        Is it possible to run the same command on a remote machine?
        I am currently able to run it only on my local machine. Can I give the login details and IP address of a different machine and execute a system command remotely?

        Thanks

        Regards
        Ananth

        Log in to post comments

        Submitted by alvin on August 20, 2009 - 1:47pm

        In reply to Running system command remotely by Ananth (not verified)
        Permalink
        Typically to run a command on

        Typically to run a command on a remote machine, what you do is set up a program on that machine that "listens" on a TCP/IP port for the commands you want to run. Then, from a client machine, you call that server and say "run this command". You can either program your server software to listen on a certain port, or use inetd/xinetd to listen on a port for you.

        I've described how to set up a command server/listener here. Although that server example is in Ruby, all the same ideas hold true.

        Then, in another other tutorial I describe how to create a Java client that can call a server.

        If you search this site for "java socket client" there are a couple of other examples as well.

        I hope this helps!

        Log in to post comments

        Submitted by Sumit (not verified) on August 23, 2009 - 1:41am
        Permalink
        Java comand

        Hi, my name is sumit vadgama.i am T.Y.B.C.A. Student.i have problem when i type java comand in cmd , the option -hotspot not worked and also not under stand the mean of that.have any one to idea about this? Pls help me for this.

        Log in to post comments

        Submitted by suyog (not verified) on September 13, 2009 - 11:48pm
        Permalink
        Query regarding above article..

        hello alvin,
        thanks for such really nice article, it helps me a lot.

        I have one query,,,,

        Suppose i want to execute 'ls' command for a specific directory on linux.
        When i use above code (with replacing command to 'ls') to display list. It displays the files & folders within current directory from where i had executed the code....

        But I want to execute 'ls' for another directory which may be in the same directory hierarchy or in different...

        What should I add to code above , to accomplish this...

        Thank you
        Suyog

        Log in to post comments

        Submitted by alvin on September 14, 2009 - 8:29am

        In reply to Java comand by Sumit (not verified)
        Permalink
        Missed your comment

        I'm sorry, I missed your comment earlier. I hope you found the solution to your problem, but if not, please try to give me more information, and I'll be glad to help.

        Log in to post comments

        Submitted by alvin on September 14, 2009 - 8:33am

        In reply to Query regarding above article.. by suyog (not verified)
        Permalink
        ls in a different directory

        To execute your ls command in a different directory, you just need to add the directory name as an argument to your command, like this:

        Process p = Runtime.getRuntime().exec("ls /tmp");

        That command will list the contents of the /tmp directory on a Linux system. For a more detailed listing, just add a few more options to the ls command, like this:

        Process p = Runtime.getRuntime().exec("ls -al /tmp");

        Log in to post comments

        Submitted by suyog (not verified) on September 15, 2009 - 1:09am
        Permalink
        Listing files & folders from home directory..??

        Hello Alvin,
        Thanks for your reply..

        I am trying to clear my doubts here as :

        Suppose I am in the directory /home/Abc/xyz/pqr.
        Now if I execute command Runtime.getRuntime().exec("ls");
        it displays files & folders inside 'pqr'.

        But I want listing from /home/Abc , without giving absolute path..
        So, do I need to execute 'cd' command which will get you to the home directory & then directly execute above 'ls' command to list all files & folders inside home directory .. ???

        (I tried above , but not working & giving error as
        Cannot run program "cd": java.io.IOException: error=2, No such file or directory)

        Log in to post comments

        Submitted by alvin on September 15, 2009 - 10:15am

        In reply to Listing files & folders from home directory..?? by suyog (not verified)
        Permalink
        Something happens with the cd

        Something happens with the cd command here that doesn't work, and I'm not sure I fully understand it right now. I think it has to do with the fact that cd isn't really a command that exists on the filesystem, like the ls command, but instead is built into the shell.

        I just ran a few tests, and I could run multiple ls commands like this:

        Process p = Runtime.getRuntime().exec("ls -l /tmp ; ls -l /home");

        But I could not get the combination of a cd and ls command to work like this:

        Process p = Runtime.getRuntime().exec("cd /tmp ; ls -l");

        (Sorry, I'm not an expert here. I know there are things in other languages like chdir method calls, and I think that's what my next suggestion relates to.)

        If you really just want to execute the ls command in a different directory you might be able to use this other exec method signature, which lets you specify a different initial directory:

public Process exec(String[] cmdarray,
        String[] envp,
        File dir)
        throws IOException

        I haven't tested that yet, but I just read about that method on the Runtime javadoc page.

        Usually what I do is when I need to exec multiple commands is put those commands into a shell script, and then exec that shell script. I think you're okay to get a directory listing like this, but if you want to do much more I think you'll need to take that approach.

        A very interesting note on that Runtime page is that the ProcessBuilder class is now the recommended way to run external processes. I have a little too much on my plate today to dig into this right away, but I'll make a note to look at this class, and update this article.

        Log in to post comments

        Submitted by suyog (not verified) on September 16, 2009 - 12:11am
        Permalink
        Hello Alvin, Thank

        Hello Alvin,
        Thank you very much for your response. I am studying this material/topic, & if I found any potential information then i ll definitely post here...

        Thanks & Regards
        Suyog

        Log in to post comments

        Submitted by harry (not verified) on September 19, 2009 - 7:01am
        Permalink
        execute shell commands with pipes in java

        hi, i am not able to execute shell commands with pipes in java

        any ideas?????????????

        Log in to post comments

        Submitted by alvin on September 19, 2009 - 10:07am

        In reply to execute shell commands with pipes in java by harry (not verified)
        Permalink
        How to exec a pipeline command

        Sorry for the delay, I finally had some time to look at this. If you change this line of code:

        Process p = Runtime.getRuntime().exec("ps -ef");

        to these two lines of code:

        String[] cmd = { "/bin/sh", "-c", "ps aux | wc -l" };
        Process p = Runtime.getRuntime().exec(cmd);

        you can run external commands in a pipe.

        The thing I didn't know until digging into this just now is that when you run exec, you aren't actually running your commands in a shell, you're really just running them (with no shell environment). So to use a feature like a pipe (pipeline) -- which is a shell feature -- you have to invoke a shell, and then run your commands inside that shell.

        That's what I'm doing in the two lines of code above, invoking a shell, and then running the "ps auxx | wc -l" command pipeline in that shell.

        Very cool, I appreciate everyone's questions here. I just kept avoiding this problem myself, but with enough people asking, I finally dug into this.

        Log in to post comments

        Submitted by alvin on September 19, 2009 - 10:11am

        In reply to Hello Alvin, Thank by suyog (not verified)
        Permalink
        Run multiple shell commands with exec

        suyog -- In case you didn't get my answer from my other comment, you should be able to solve your question like this:

        String[] cmd = { "/bin/sh", "-c", "cd /var; ls -l" };
        Process p = Runtime.getRuntime().exec(cmd);

        As mentioned in my other comment, the solution lies in running your commands inside an actual Unix shell.

        Log in to post comments

        Submitted by harry (not verified) on September 19, 2009 - 10:14am

        In reply to Run multiple shell commands with exec by alvin
        Permalink
        thanks !! :) it helped me a

        thanks !! :) it helped me a lot

        Log in to post comments

        Submitted by ravi (not verified) on October 6, 2009 - 12:45pm

        In reply to Run multiple shell commands with exec by alvin
        Permalink
        Hello Alvin, Wonderful post I

        Hello Alvin,

        Wonderful post I believe. Thanks for sharing your experience on this.

        I have one query as of now:-

        how can I execute the query on remote machine? I have seen few other posts from you, but in one of the posts you have written a Ruby Script for achieving few things, since Ruby Script is not available for any Unix based machines, I just wanted to know how I can do a telnet session to a Unix machine and execute the commands remotely from my application running on Windows machine.

        Regards,
        Ravi

        Log in to post comments

        Submitted by Rama (not verified) on October 21, 2009 - 6:34am
        Permalink
        Run .pl file from java

        Hi,

        I want run my perl file which is present in unix box from my jsp code so I have written like
        Process process=Runtime.getRuntime().exec("./sso_example.pl");

        but I am not able run this ,any help pls?

        Log in to post comments

        Submitted by Pac (not verified) on October 27, 2009 - 2:43pm
        Permalink
        Thank you so much

        Thank you very much Alvin your comment about piping with java exec just saved my life, if there is a God I'm sure he will reward you with beautiful women and lots of money. Thanks again!!

        Log in to post comments

        Submitted by alvin on October 28, 2009 - 10:59am

        In reply to Run .pl file from java by Rama (not verified)
        Permalink
        Rama -- Sorry for the delay

        Rama -- Sorry for the delay in replying, I have been very busy lately. There are two parts to your answer. The first part is easy: Make sure your Perl script is executable (i.e., do a "chmod +x" on your script), and make sure it runs just fine from the command line.

        Second, the easiest way to get it working from your JSP is to specify the full path to the Perl script instead of using a relative path. That is, if your Perl script is in a directory named /web/sites/myapplication, then you should call it with that directory, like this:

        /web/sites/myapplication/sso_example.pl

        Using the "./sso_example.pl" command implies that the Perl script is in the "current directory". With a web application I don't know what the "home" directory is, but it's either going to be the JVM's home directory, your web container's bin directory, or possibly your web application's directory.

        (Sorry I don't know this answer ... I'm too lazy/busy to dig through the servlet specification, and I don't have any Java web apps running right now that I can test this with. I did just look at my JSP CGI parameters tutorial, and there I can see that the "Real Path" is the directory of my web app, but that doesn't help much.)

        If you really want to know this ... if you have your Java web app running in a JVM, just add this line of code to a JSP you can hit (put it inside of some PRE tags), and it will help you see figure out what your JSP's home directory is:

        System.getProperties().list(System.out);

        (I just pulled that line of code from my Java system properties tutorial.)

        Sorry, that got a little long-winded, but I hope it helps.

        Log in to post comments

        Submitted by Cory (not verified) on November 10, 2009 - 5:03pm
        Permalink
        Very Helpful

        This is a very helpful post. Thank you for sharing.

        Log in to post comments

        Submitted by Dauren Mussa (not verified) on November 12, 2009 - 8:02am
        Permalink
        What about cmd command in Windows

        Hi everyone.
        Well, I'm trying to run JAR command for creating Java ARchive from Java code.
        So, I already done to generate classes from WSDL and compile them successfully, but now i have some trouble. I can not run JAR command. Because, before calling JAR, I have to run "cd C:\temp\" and then "jar cf client.jar @classes.list".
        Two commands! So... Please, who can give an advise here? I really need help!

        Log in to post comments

        Submitted by alvin on November 12, 2009 - 9:35am

        In reply to What about cmd command in Windows by Dauren Mussa (not verified)
        Permalink
        I'm sorry, I don't have a

        I'm sorry, I don't have a Windows system these days, so I can't try to test anything. Before I learned how to do all of this, I used to call a shell script on Unix and Linux systems whenever I needed to run multiple commands, so maybe you can do the same thing on a Windows system, and call a Windows batch file to run your commands?

        Log in to post comments

        Submitted by Emmitt (not verified) on December 25, 2009 - 10:05pm
        Permalink
        Thanks

        Its 11 o'clock p.m. on Christmas. I haven't slept in two days and have been trying to work on a program like this for a while now. Thanks to you I figured out what I needed for it to run properly. If it wasn't for you I would still need to stay up and finish this.

        Thank you and God Bless

        Log in to post comments

        Submitted by Martin H. H. (not verified) on January 21, 2010 - 2:22am
        Permalink
        Wildcards

        To try out this command I tried a ls command with wildcard *
        But I could not get it to work.
        A simple "ls /home/martin" works fine, but not "ls /home/martin/*.java"

        Log in to post comments

        Submitted by alvin on February 8, 2010 - 8:08am
        Permalink
        New Java exec tutorial

        A quick note here that I've created a new tutorial on how to execute system commands from Java using ProcessBuilder and Process. That article not only uses these newer Java classes, but it also resolves the issues of reading the standard output and standard error streams from the system command you want to execute.
        I strongly recommend that you use the code from that article, as opposed to the code in this article, in particular because it handles the output and error streams properly.

        Log in to post comments

        Submitted by alvin on March 10, 2010 - 8:35pm

        In reply to Wildcards by Martin H. H. (not verified)
        Permalink
        Java exec with wildcards

        I don't know if this will work, I haven't tried anything with wildcards yet, but if it's going to work, you'll have to run your command in a shell, as shown above in the "How to exec a pipeline command" comment.

        In short, wildcard characters are a shell feature, so you'll need to exec a shell, and then give your command to the shell, as I wrote about in that comment.

        Sorry I can't be more help right now, I'm currently traveling, but I hope that points you in the right direction. Also, sorry for the delay.

        Log in to post comments

        Submitted by Jay (not verified) on March 22, 2010 - 4:42pm
        Permalink
        wildcard works with

        wildcard works with bash

        String[] cmd = { "/bin/bash", "-c", "cd /var; ls *.java" };
        Process p = Runtime.getRuntime().exec(cmd);

        Log in to post comments

        Submitted by joe (not verified) on May 10, 2010 - 10:01am
        Permalink
        Setting Java Classpath

        I'm trying to use the method signature below to set a long classpath for executing a separate java application from within my own. It seems like the classpath is failing to be set any ideas?
        exec(String[] cmdarray, String[] envp)

        used:
        exec("(fully qualified)/java (classname) -args", environment);

        where environment = {"CLASSPATH=(classpath)"} and a few other environment variables.

        any ideas would be much appreciated.

        Log in to post comments

        Submitted by alvin on May 10, 2010 - 8:52pm

        In reply to Setting Java Classpath by joe (not verified)
        Permalink
        Java CLASSPATH

        Have you tried using -cp or -classpath to set the classpath, and -D to set your other properties? That might work, so then your command is just something like this:

        java -cp [classpath-here] -Dfoo=bar MyJavaApp.jar

        It seems like that should work.

        If not, try the approach where you actually launch a shell, and then run your Java app inside the shell. The shell approach is shown in the comments above, and look something like this:

        String[] cmd = { "/bin/sh", "-c", "cd /var; ls -l" };
        Process p = Runtime.getRuntime().exec(cmd);

        Instead of the cd and ls commands shown there, you would set your CLASSPATH and other environment variables, then run your Java app.

        I hope that helps!

        Log in to post comments

        Submitted by betty (not verified) on May 12, 2010 - 5:32am
        Permalink
        command of exec

        i want to use a program of java which run commands of shell. i have tried with the suggestions have given above but i doen't worck with all the commands???? "as: sudo virsh, ?????"
        so thinks for the answer!!!!

        Log in to post comments

        Submitted by alvin on May 12, 2010 - 2:44pm

        In reply to command of exec by betty (not verified)
        Permalink
        Java exec with sudo

        For an example like that, where you want to exec the sudo command, you're going to need to properly work with the input and output streams. To do that, you'll have to use my newer (and more complicated code), as shown in my "Java exec with Process and ProcessBuilder" article. I specifically created that project so I could run the sudo command from a Java exec method call.

        I don't know if the Apache exec project handles something like the sudo command, but you may want to look at it as well.

        Log in to post comments

        Submitted by Anonymous (not verified) on May 28, 2010 - 4:35pm
        Permalink
        Command line freeze when using java exec

        Hi,I am running exec on window. I am trying to open the cmd and input the command. The code worked. I also try to type: cd .. or dir for testing it worked too.
        However I was trying to input a command ( business sentitive, I cannot postup) . This command work when I manually type in cmd. However when using code to execute, the windows freeze ? I looked like as it is waiting for something. Any idea to why this is happening or way to resolve this ?

        Thanks

        Log in to post comments

        Submitted by caze (not verified) on June 1, 2010 - 9:50am
        Permalink
        runtime exec() doesn't work for network directory

        hi , i am trying to access the network drive using runtime exec(),

        but i am getting IO Exception .

        here is the part of code :
        ------------------------------cut from code-----------------------------
        commands= new String[] {"cmd /c C:\\APP\\Adl2\\bin\\runpx.cmd http://2IND05724.LED.XYZ.com:7779/ServerService \"\\\\abcd\\mysig\\file.zip\" -monitor"};
        Process p = Runtime.getRuntime().exec(commands);

        ------------------------------- end of cut--------------------------------

        in the above code "\\abce is common network drive

        can any one help with this ..

        Log in to post comments

        Submitted by alvin on June 3, 2010 - 2:50pm

        In reply to runtime exec() doesn't work for network directory by caze (not verified)
        Permalink
        Windows cmd problem

        I'm sorry, I can't help with this Windows command (I don't have a Windows system), but I thought I'd post your comment in case anyone else can help.

        That being said, if your code appears to be locking up with no input or output, please look at my new Java exec article, which gets around the problems of this more basic code. Or, look at the Apache exec project.

        Log in to post comments

        Submitted by alvin on June 11, 2011 - 2:35pm
        Permalink
        Final comment for this post

        I haven't closed off comments for a tutorial before, but in this case, I am.

        As mentioned in my last comment, the techniques in my Java exec with ProcessBuilder and Process tutorial solves all the problems I've seen reported here.

        Or, if you just want to get something to work and don't mind that the code is a little older, take a look at the Apache exec project.

        Log in to post comments

        java
        java applets
        java faqs
        misc content
        java source code
        test projects
        lejos

        Perl
        perl faqs
        programs
        perl recipes
        perl tutorials


        Unix
        man (help) pages
        unix by example
        tutorials

        source code
        warehouse
        java examples
        drupal examples

        misc
        privacy policy
        terms & conditions
        subscribe
        unsubscribe
        wincvs tutorial
        function point
        analysis (fpa)
        fpa tutorial



        Other
        contact me
        rss feed
        my photos
        life in alaska
        how i sold my business
        living in talkeetna, alaska
        my bookmarks
        inspirational quotes
        source code snippets

        This website uses cookies: learn more

        alvinalexander.com is owned and operated by Valley Programming, LLC

        In regards to links to Amazon.com, “As an Amazon Associate
        I (Valley Programming) earn from qualifying purchases”
