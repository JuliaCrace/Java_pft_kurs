package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {


    @Test (enabled = false)
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Alex", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", "test2"), app);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        // int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
      //  int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

  //  @Test
  //  public void testContactDeletion2() {
  //      if (! app.getContactHelper().isThereAContact()) {
  //          app.getContactHelper().createContact(new ContactData("Alex", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", "test2"), app);

  //      }
  //      int before = app.getContactHelper().getContactCount();
  //      app.getContactHelper().selectContact();
  //      app.getContactHelper().delContact();
  //      int after = app.getContactHelper().getContactCount();
  //      Assert.assertEquals(after, before - 1);

  //  }
}
