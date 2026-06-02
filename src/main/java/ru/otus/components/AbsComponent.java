package ru.otus.components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import lombok.AllArgsConstructor;
import ru.otus.pageobject.AbsPageObject;

//содержит общие методы только для компонентов
@SuppressWarnings("unchecked")
@AllArgsConstructor
public abstract class AbsComponent<T extends AbsComponent<T>> extends AbsPageObject {
  //корневой элемент компонента, от которого начинается поиск любого другого элемента компонента
  protected final SelenideElement root;

  //возможная проверка условия(например, видимости) компонента
  public T shouldBe(WebElementCondition... conditions) {
    root.shouldBe(conditions);
    return (T) this;
  }
}
