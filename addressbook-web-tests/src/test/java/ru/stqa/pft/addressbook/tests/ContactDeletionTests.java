package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    }

    @Test

    public void testContactDeletion2 () {
        app.getContactHelper().selectContact();
        app.getContactHelper().delContact();

    }
}
