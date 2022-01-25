package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AddNewContactTests extends TestBase {

    @Test
    public void testAddNewContact() throws Exception {
        Set<ContactData> before = app.contact().all();
        app.contact().contactPage();
       // ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstname("Al").withLastname("Ivanov").withAddress("г. Москва, проспект Ленинский, д.2").withMobile("+79510990101").withEmail("ivanov@mail.ru").withGroup("test123");
        ContactData contact = new ContactData().withFirstname("Al").withLastname("Ivanov").withAddress("г. Москва, проспект Ленинский, д.2").withMobile("+79510990101").withEmail("ivanov@mail.ru").withGroup("test123");

        app.contact().create(contact, app);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
       // Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
       // before.sort(byId);
       // after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
