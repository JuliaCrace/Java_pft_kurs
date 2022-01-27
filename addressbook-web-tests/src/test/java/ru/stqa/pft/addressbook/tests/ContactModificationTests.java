package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition (){
        if ( app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Alex").withLastname("Ivanov").withAddress("г. Москва, проспект Ленинский, д.2").withMobile("+79510990101").withEmail("ivanov@mail.ru").withGroup("test2"), app);
        }
    }

    @Test
    public void testContactModification () {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Al").withLastname("Ivanov").withAddress("г. Москва, проспект Ленинский, д.2").withMobile("+79510990101").withEmail("ivanov@mail.ru");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
