package com.example.TravelPlanner.controller;

import org.openqa.selenium.By;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.concurrent.TimeUnit;

@RestController
public class CrawlController {
    private WebDriver driver;
    List<WebElement> elements = new ArrayList<>();

    @PostMapping("/crawl")
    public String process(@RequestBody String url) {
        String imgSrc = "";
        long startTime = System.currentTimeMillis(); // 시작 시간 기록
//        driver = new HtmlUnitDriver();
//        HtmlUnitOptions options = new HtmlUnitOptions(driver);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-extensions"); // 확장 프로그램 비활성화
        options.addArguments("--no-sandbox"); // sandbox 모드 비활성화
        options.addArguments("--disable-dev-shm-usage"); // /dev/shm 사용 안함
        driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 암묵적 대기 시간 설정
            List<WebElement> crawled = getImgUrl(url);
            int startIndex = crawled.indexOf("http");
            crawled.forEach(x->
                System.out.println(x)
            );
            // 특정 요소 찾기 및 텍스트 가져오기
//            imgSrc = "https://img1.kakaocdn.net/cthumb/local/R0x420.q50/?fname=" + crawled.substring(startIndex);
//            System.out.println("이미지 링크: " + imgSrc);
//            System.out.println(crawled);


            long endTime = System.currentTimeMillis(); // 종료 시간 기록
            long totalTime = endTime - startTime; // 총 실행 시간 계산

            System.out.println("실행 시간: " + totalTime + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
        }
        return imgSrc;
    }

    private List<WebElement> getImgUrl(String url) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
        driver.get(url);
        //#mArticle > div.cont_essential > div:nth-child(1) > div.details_present > a > span
//        #mArticle > div.cont_essential > div:nth-child(1) > div.place_details > div > div.location_evaluation > a:nth-child(3) > span.color_b
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.details_present > a > span")));
        elements.add(element);
        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.place_details > div > div.location_evaluation > a:nth-child(3) > span.color_b")));
        elements.add(element2);
        return elements;
    }
}
