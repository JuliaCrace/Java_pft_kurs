package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class AddNewContactTests extends TestBase {

    @Test
    public void testAddNewContact() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
      //  int before = app.getContactHelper().getContactCount();
        app.getContactHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("Alex", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", "test2"), app);
        List<ContactData> after = app.getContactHelper().getContactList();
      //  int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() + 1);

    }

}
