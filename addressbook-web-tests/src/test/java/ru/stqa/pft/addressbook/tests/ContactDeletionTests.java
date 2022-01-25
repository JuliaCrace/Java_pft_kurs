package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition (){
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Alex").withLastname("Ivanov").withAddress("г. Москва, проспект Ленинский, д.2").withMobile("+79510990101").withEmail("ivanov@mail.ru").withGroup("test123"), app);
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
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
