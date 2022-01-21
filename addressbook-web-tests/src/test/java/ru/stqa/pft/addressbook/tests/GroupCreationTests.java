package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list(); // список объектов GroupData до создания группы
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        List<GroupData> after = app.group().list(); // содержит список элементов, после того, как создана новая группа
        Assert.assertEquals(after.size(), before.size() + 1); // проверка размера списка после создания группы с размером списка до создания группы

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);;
    }

}
