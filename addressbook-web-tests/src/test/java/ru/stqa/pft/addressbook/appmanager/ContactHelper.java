package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    private By locator;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void click(By locator) {
        this.locator = locator;
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void gotoContactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact() { click(By.name("selected[]"));
    }

    public void initContactModification() { click(By.xpath ("//table[@id='maintable']/tbody/tr[3]/td[8]/a/img"));
    }

    public void updateContact() { click(By.name("update"));
    }

    public void deleteContact() { click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void delContact() { click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }

}
