package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));

        }
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

    public void initContactModification() { click(By.xpath ("//img[@alt='Edit']"));
    }

    public void updateContact() { click(By.name("update"));
    }

    public void deleteContact() { click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void delContact() { click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }

    public void createContact(ContactData contact, boolean b) {
        gotoContactPage();
        fillContactForm(contact, true);
        submitContact();
        gotoHomePage();

    }

    public void gotoHomePage() { click(By.linkText("home page"));

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
