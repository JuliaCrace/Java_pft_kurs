package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

    @Test
    public void testAddNewContact() throws Exception {
        app.getContactHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("Alex", "Ivanov", "г. Москва, проспект Ленинский, д.2", "+79510990101", "ivanov@mail.ru", "test1"));

    }

}
