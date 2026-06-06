package stepDefinitions;

import Utilities.Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class S09_AddProductsToShoppingCart {

    @And("user adds a first product to shopping cart")
    public void userAddsAFirstProductToShoppingCart() {
        do {
            Util.AddProductToCart();
        } while (Hooks.isProductOutOfStock);
    }

    @And("user adds a second product to shopping cart")
    public void userAddsASecondProductToShoppingCart() {
        do {
            Util.AddProductToCart();
        } while (Hooks.isProductOutOfStock);
    }

    @And("user adds a third product to shopping cart")
    public void userAddsAThirdProductToShoppingCart() {
        do {
            Util.AddProductToCart();
        } while (Hooks.isProductOutOfStock);
    }

    @And("user navigates to shopping cart")
    public void userNavigatesToShoppingCart() {
        Hooks.shoppingCart.getCloseNotificationBar().click();
        Util.sleep(1);
        Hooks.shoppingCart.getShoppingCart().click();
    }

    @And("user increases the quantity of the first product by {int}")
    public void userIncreasesTheQuantityOfTheFirstProductByN(int arg0) {
        Util.updateShoppingCartItemQuantity(0,arg0);
    }

    @And("user increases the quantity of the third product by {int}")
    public void userIncreasesTheQuantityOfTheThirdProductByN(int arg0) {
        Util.updateShoppingCartItemQuantity(2,arg0);
    }

    @And("user clicks on <Update shopping cart> button")
    public void userClicksOnUpdateShoppingCartButton() {
        Hooks.shoppingCart.getShoppingCartBtn().click();
    }

    @Then("Add products to shopping cart is done successfully")
    public void addProductsToShoppingCartIsDoneSuccessfully() {
        // 1) Check if number of items in shopping cart is greater than zero
        int numberOfItems = Hooks.shoppingCart.getShoppingCartItems().size();
        Assert.assertTrue(numberOfItems > 0);
        // 2) Check if the actual total quantities is the same as the expected total quantities
        String actualResult = Hooks.shoppingCart.getTotalQuantityFromShoppingCart().getText();
        String expectedResult = "(" + Util.totalQuantityInShoppingCart() + ")";
        Assert.assertEquals(actualResult,expectedResult);
        Util.printShoppingCartProductsNamesAndQuantities();
    }
}
