package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click(); //находим все элементы по локатору и выбираем нужный по индексу и делаем по нему клик
     //   click(By.name("selected[]"));
    }
    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() { click(By.name("edit"));
    }

    public void submitGroupModification() { click (By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }
    public void modify(GroupData group) {
        selectGroupById(group.getId()); // выбирается последний элемент
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();

    }



    public boolean isTherAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); // найти все элементы, которые имеют тег span и класс групп
        for (WebElement element : elements) {
            String name = element.getText(); // получаем имя группы из пробегаемого списка
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withId(id).withName(name);
            groups.add(group);
        }
        return groups;
    }

    public Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); // найти все элементы, которые имеют тег span и класс групп
        for (WebElement element : elements) {
            String name = element.getText(); // получаем имя группы из пробегаемого списка
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withId(id).withName(name);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }


}
