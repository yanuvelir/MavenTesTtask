import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Main {
    private static WebDriver driver;
    private static Object Clipboard;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        WebDriver driver = new ChromeDriver();
    // Переход на начальную страницу
        driver.get("https://coinzix.com/");
    // Развертывание страницы на весь экран
        driver.manage().window().maximize();

    // Поиск и нажатие на кнопку "Login"
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/header/div/div[2]/nav/ul/li[1]/a[1]")).click();

        // Устанавливаем задержку для обнаружения следующего элемента
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Находим поле ввода почты и заполняем его
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("yanuvelir@gmail.com");

        // Находим поле пароля и заполняем его
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("RW8jqHHMFkE2Kdw");

        // Задержка для появления элемента Cookie
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Разрешение всех Cookies
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/section/div/div/div[2]/button[2]")).click();

        // Нажатие на клавишу Log In
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/main/div/div[1]/form/div[3]/button")).click();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Переход на реферальную страницу
        driver.get("https://coinzix.com/account/referral");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector
                ("#__layout > div > main > div > div:nth-child(3) > div.light-page-widget__body > div > div > div > button:nth-child(2) > svg"))
                .click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            Object data = clipboard.getData(DataFlavor.stringFlavor);

            // Используя расширенный инструмент (Assert) фреймворка TestNG, производится сравнение
            // скопированного результата с результатом по умолчанию. Если результат негативный в консоль будет выведена ошибка
            Assert.assertEquals(data, "https://coinzix.com/refcode/xtmca5");

            // Вывод буфера памяти в консоль
            System.out.println(data);

            // Переход по скопированной реферальной ссылке на следующую страницу
            driver.get((String) data);

            Thread.sleep(3000);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //https://coinzix.com/refcode/xtmca5

        driver.quit();





    }
}
