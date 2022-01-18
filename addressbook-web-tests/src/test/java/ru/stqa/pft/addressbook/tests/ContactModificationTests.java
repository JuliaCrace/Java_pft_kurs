package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification () {
        if (! app.getContactHelper().isThereAContact()) {
              app.getContactHelper().createContact(new ContactData("Alex", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", "test2"), app);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
       // int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Al", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
       // int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
