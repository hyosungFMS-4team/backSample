package com.example.TravelPlanner.controller;

import org.openqa.selenium.By;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CrawlController {
    private WebDriver driver;

    @PostMapping("/crawl")
    public List<List<String>> process(@RequestBody List<Map<String, String>> crawlData) {
        List<List<String>> valuesList = new ArrayList<>();

        long startTime = System.currentTimeMillis(); // 시작 시간 기록
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-extensions"); // 확장 프로그램 비활성화
        options.addArguments("--no-sandbox"); // sandbox 모드 비활성화
        options.addArguments("--disable-dev-shm-usage"); // /dev/shm 사용 안함
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30)); // WebDriverWait 객체 생성

        try {
            for (Map<String, String> place : crawlData) {
                String placeUrl = place.get("placeUrl");
                List<String> result = getValues(placeUrl, driver, wait); // WebDriverWait 객체와 driver 객체를 매개변수로 전달하여 호출
                valuesList.add(result);
            }

            long endTime = System.currentTimeMillis(); // 종료 시간 기록
            long totalTime = endTime - startTime; // 총 실행 시간 계산
            System.out.println("실행 시간: " + totalTime + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return valuesList;
    }

    private List<String> getValues(String url, WebDriver driver, WebDriverWait wait) throws InterruptedException {
        driver.get(url);
        List<String> values1 = new ArrayList<>();

        // 두 개의 요소를 동시에 가져오기
        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.place_details > div > div.location_evaluation > a:nth-child(3) > span.color_b")));
        WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.place_details > div > div.location_evaluation > a:nth-child(3) > span.color_g")));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.details_present > a > span")));

            int fnameIndex = element.getAttribute("style").indexOf("?fname=");
            values1.add("https://img1.kakaocdn.net/cthumb/local/R0x420.q50/?fname="+element.getAttribute("style").substring(fnameIndex+7).replace("\");",""));
            values1.add(element2.getText());
            values1.add(element3.getText());
        }catch (Exception e) {
            values1.add("");
            values1.add(element2.getText());
        }
        // 요소들이 나타내는 값들을 2차원 배열의 리스트에 추가
        return values1;
    }

}
