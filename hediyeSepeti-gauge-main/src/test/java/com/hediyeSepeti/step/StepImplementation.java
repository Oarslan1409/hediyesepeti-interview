package com.hediyeSepeti.step;

import com.thoughtworks.gauge.Step;
import com.hediyeSepeti.methods.Methods;

public class StepImplementation {
    Methods methods;

    public StepImplementation() {
        methods = new Methods();
    }

    @Step("<key> elementine tikla")
    public void clickElement(String key) {
        methods.clickElement(methods.getBy(key));
    }

    @Step("<number> saniye bekle")
    public void waitForSecond(int number) throws InterruptedException {
        Thread.sleep(number * 1000L);
    }
    @Step("Ekrani <number> piksel asagi kaydir")
    public void scrollDown(int number) {
        methods.scrollDown(number);
    }
    @Step("<key> degeri 4.0'ten buyukse <favori> elementine tikla")
    public void compareElement(String key, String favori)  {
        if(methods.replaceString(methods.getInnerText(key), ',','.') >= 4.0) {
            methods.clickElement(methods.getBy(favori));
        }
    }
    @Step("Alert onayla")
    public void confirmAlert()  {
        methods.confirmPopup();
    }

    @Step("<key> elementi varsa <product> elementine tikla")
    public void checkFavoritePage(String key, String product)  {
        if(methods.findElement(methods.getBy(key)) != null) {
            methods.clickElement(methods.getBy(product));
        }
    }
    @Step("<key> elementine <text> degerini yaz")
    public void sendKeystoElement(String key, String text)  {
        methods.findElement(methods.getBy(key)).sendKeys(text);
    }
}
