package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringChecker {

    private String string;
    private boolean isNewLine;
    private boolean isEOF;
    ReentrantLock locker;
    Condition condition;

    StringChecker() {
        string = null;
        isNewLine = false;
        isEOF = false;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public boolean ReadString(BufferedReader br) {
        locker.lock();
        try {
            while (isNewLine) {
                condition.await();
            }
            string = br.readLine();
            condition.signalAll();
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            string = null;
        } finally {
            isNewLine = true;
            isEOF = string == null;
            locker.unlock();
        }
        return isEOF;
    }

    public String CheckString(String substring) {
        locker.lock();
        String res = null;
        try {
            while (!isNewLine) {
                condition.await();
            }
            Pattern pattern = Pattern.compile(substring);
            if (getString() != null) {
                Matcher matcher = pattern.matcher(getString());
                if (matcher.find()) {
                    res = getString();
                }
            }
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            isNewLine = false;
            locker.unlock();
        }
        return res;
    }

    public String getString() {
        return string;
    }

    public boolean getIsEOF() {
        return isEOF;
    }

}


public class Main {

    static String FindSourceCodeFile(String szDir, String target) {
        File rootDir = new File(szDir);
        List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();
        Collections.addAll(fileTree, Objects.requireNonNull(rootDir.listFiles()));
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));
            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }
        for (String ss : result) {
            if (ss.contains(target)) {
                return ss;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        StringChecker stringChecker = new StringChecker();
        Thread reader = new Thread(new Reader(stringChecker), "Reader Thread");
        Thread checker = new Thread(new Checker(stringChecker, "import"), "Checker Thread");
        reader.start();
        checker.start();
        try {
            reader.join();
            checker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class Reader implements Runnable {
    StringChecker sc;

    Reader(StringChecker sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        final String dir = System.getProperty("user.dir");
        String name = Main.class.getName();
        name = name.replace('.', '/') + ".java";
        File file = new File(dir);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist!");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Not a directory!");
        }
        String filePath = Main.FindSourceCodeFile(dir, name);
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(Objects.requireNonNull(filePath)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            if (sc.ReadString(br)) {
                break;
            }
        }
    }
}

class Checker implements Runnable {
    StringChecker sc;
    private final String substring;

    Checker(StringChecker sc, String substring) {
        this.sc = sc;
        this.substring = substring;
    }

    @Override
    public void run() {
        String res;
        while (!sc.getIsEOF()) {
            res = sc.CheckString(substring);
            if (res != null) {
                System.out.println("Found string that matches given substring: " + res);
            }
        }
    }
}

/* OUTPUT:

    Found string that matches given substring: import java.io.BufferedReader;
    Found string that matches given substring: import java.io.File;
    Found string that matches given substring: import java.io.IOException;
    Found string that matches given substring: import java.nio.file.Files;
    Found string that matches given substring: import java.nio.file.Paths;
    Found string that matches given substring: import java.util.*;
    Found string that matches given substring: import java.util.concurrent.locks.Condition;
    Found string that matches given substring: import java.util.concurrent.locks.ReentrantLock;
    Found string that matches given substring: import java.util.regex.Matcher;
    Found string that matches given substring: import java.util.regex.Pattern;
    Found string that matches given substring:         Thread checker = new Thread(new Checker(stringChecker, "import"), "Checker Thread");

 */
