package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class AddNewContactTests extends TestBase {

    @Test
    public void testAddNewContact() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoContactPage();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Al", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", "test123");
        app.getContactHelper().createContact(contact, app);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
