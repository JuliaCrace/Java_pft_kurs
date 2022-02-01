package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void contactPage() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactByID(int id) {
      wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void initContactModification(int index) {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }

    public void delContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }


    public void create(ContactData contact, ApplicationManager app) {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) ;
        {
            app.group().create(new GroupData().withName(contact.getGroup()));
        }
        contactPage();
        fillContactForm(contact, true);
        submitContact();
        contactCache = null;
        gotoHomePage();

    }
    public void modify(ContactData contact) {
        selectContactByID(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        updateContact();
        contactCache = null;
        gotoHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
        gotoHome();
    }

    public void delete(ContactData Contact) {
        selectContactByID(Contact.getId());
        deleteContact();
        contactCache = null;
        gotoHome();
    }
    public void gotoHomePage() {
        click(By.linkText("home page"));

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();

    }

    public void gotoHome() {
        click(By.linkText("home"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));

        }
        return contacts;
    }

    public Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String[] phones = cells.get(5).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withHomePhone(phones[0]).withMobile(phones[1]).withWorkPhone(phones[2]));

        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(homePhone).withMobile(mobile).withWorkPhone(workPhone);
       
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

    //    wd.findElement(By.xpath(String.format("//input(@value='%s']/../../td[8]/a", id))).click();
    //    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }
}
