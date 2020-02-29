package LoginProccess;

import com.codeborne.selenide.ElementsCollection;
import lombok.Value;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class UserPage {
    private String header = "Личный кабинет";
    private ElementsCollection accountsList = $$("li.list__item");


    public void verifySuccessfulLogin() {
        $(withText(getHeader())).waitUntil(visible, 10000);
    }
}
