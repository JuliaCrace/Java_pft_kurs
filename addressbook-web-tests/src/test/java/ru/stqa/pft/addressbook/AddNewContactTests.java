package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddNewContactTests {
  private WebDriver wb;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wb = new FirefoxDriver();
    wb.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wb.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wb.findElement(By.name("user")).click();
    wb.findElement(By.name("user")).clear();
    wb.findElement(By.name("user")).sendKeys(username);
    wb.findElement(By.name("pass")).click();
    wb.findElement(By.name("pass")).clear();
    wb.findElement(By.name("pass")).sendKeys(password);
    wb.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testAddNewContact() throws Exception {
    gotoContactPage();
    fillContactForm(new ContactData("Alex", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru"));
    submitContact();

  }

  private void submitContact() {
    wb.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wb.findElement(By.name("firstname")).click();
    wb.findElement(By.name("firstname")).clear();
    wb.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    wb.findElement(By.name("lastname")).click();
    wb.findElement(By.name("lastname")).clear();
    wb.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    wb.findElement(By.name("address")).click();
    wb.findElement(By.name("address")).clear();
    wb.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wb.findElement(By.name("mobile")).click();
    wb.findElement(By.name("mobile")).clear();
    wb.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    wb.findElement(By.name("email")).click();
    wb.findElement(By.name("email")).clear();
    wb.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void gotoContactPage() {
    wb.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wb.quit();

  }

  private boolean isElementPresent(By by) {
    try {
      wb.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wb.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
