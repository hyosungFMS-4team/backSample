package com.example.TravelPlanner.controller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class CrawlController {
    private WebDriver driver;

    private static final String url = "https://place.map.kakao.com/279490770";

    @GetMapping("/test")
    public void process() {
//        System.setProperty("webdriver.chrome.driver", "/Users/gijeong-gim/Desktop/hyosungedu/web/backend/Spring/crawl/chromedriver");
        //크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)

        driver = new ChromeDriver();
        //브라우저 선택
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");       //팝업안띄움
        options.addArguments("headless");                       //브라우저 안띄움
        options.addArguments("--disable-gpu");			//gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
        driver = new ChromeDriver(options);
        try {
            String crawled = getImgUrl();
            int startIndex = crawled.indexOf("http");

            String imgSrc = "https://img1.kakaocdn.net/cthumb/local/R0x420.q50/?fname="+crawled.substring(startIndex);
            System.out.println(imgSrc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();	//탭 닫기
        driver.quit();	//브라우저 닫기
    }


    /**
     * data가져오기
     */
    private String getImgUrl() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));

        driver.get(url);    //브라우저에서 url로 이동한다.
        Thread.sleep(30); //브라우저 로딩될때까지 잠시 기다린다.

        //#mArticle > div.cont_essential > div:nth-child(1) > div.details_present > a > span.bg_present
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.details_present > a > span.bg_present")));
        WebElement element = driver.findElement(By.cssSelector("#mArticle > div.cont_essential > div:nth-child(1) > div.details_present > a > span.bg_present"));
        return element.getAttribute("style");
    }
    // //t1.kakaocdn.net/thumb/T800x0.q50/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fplace%2F03C55F4734174AE387216CC0E560F793
    // https://img1.kakaocdn.net/cthumb/local/R0x420.q50/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fplace%2F03C55F4734174AE387216CC0E560F793

}