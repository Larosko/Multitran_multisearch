package org.seleniumhq.selenium;
/**
 * Created by Dasha on 29.11.2018.
 */

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class My_multitran_search {
    public static void main(String[] args) throws IOException {
        System.out.println("Type file name: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        File writeInFile = new File("C:\\Users\\Dasha\\IdeaProjects\\D_multitran\\src\\main\\java\\org\\seleniumhq\\selenium\\" + filename + ".txt");
        File readFromFile = new File("C:\\Users\\Dasha\\IdeaProjects\\D_multitran\\src\\main\\java\\org\\seleniumhq\\selenium\\abracadabra.txt");
//        FileReader fromFile = new FileReader(readFromFile);
        BufferedWriter out = new BufferedWriter(new FileWriter(writeInFile));
        BufferedReader br = new BufferedReader(new FileReader(readFromFile));
        String everything;
        ArrayList some_words2 = new ArrayList<String>();
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                some_words2.add(line);
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
        System.out.println(everything);
        System.out.println(some_words2);
//        String some_words2[] = scanner.nextLine();
//        String some_words[] = {"zerzaust", "Zungenspitze"};
        System.setProperty("webdriver.gecko.driver", "C:\\Dasha's projects\\fireFox driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        ArrayList<String> words = new ArrayList<String>();
//        for (String word : args) {
//            words.add(word);
//        }
        for (Object word : some_words2) {
            driver.navigate().to("https://www.multitran.ru/");
            WebElement dynamicElement;
            WebElement language = driver.findElement(By.name("l1"));
            language.findElement(By.cssSelector("option[value=\"3\"]")).click();
            WebElement language2 = driver.findElement(By.name("l2"));
            language2.findElement(By.cssSelector("option[value=\"2\"]")).click();
            dynamicElement = driver.findElement(By.name("s"));

            dynamicElement.sendKeys(word.toString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dynamicElement.submit();
            System.out.println(word);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                String copyText = driver.findElement(By.cssSelector("td[class='trans']")).getText();
                System.out.println(copyText);
                out.write(word + " - " + copyText);
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }
            out.newLine();

            System.out.println("Page title is: " + driver.getTitle() + "\n" + "Page lenght is: " + driver.getTitle().length());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        out.close();
        driver.quit();
    }
}

