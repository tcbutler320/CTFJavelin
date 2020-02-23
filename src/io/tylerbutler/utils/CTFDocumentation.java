package io.tylerbutler.utils;

import io.tylerbutler.app.*;
import io.tylerbutler.app.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CTFDocumentation {

    // TODO add documentation methods to set up workspace when option "new project" is selected

    // TODO method to check if workspace created, if not, then create project workspace
    // include folder and files like target, enumeration, flags, ect
    // consder some kind of JSON format

    // On user click file -- new, have user select project directory
    public static void newProject(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(new Stage());
        // TODO get user directory input and set global project settings
    }

    public static void CreateFile(String[] args) {
        try {
            File myObj = new File("test-project.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

