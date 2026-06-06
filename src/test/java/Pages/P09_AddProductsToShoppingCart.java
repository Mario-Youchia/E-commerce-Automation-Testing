package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

import java.util.List;

public class P09_AddProductsToShoppingCart {
    final String activeLastClassCSS = "li[class*=\"active last\"]>a";
    final String urlAttribute = "href";
    final String ProductsIDs_id = "div[data-productid]";
    final String processorDropdown_id = "product_attribute_1";
    final String RAMDropdown_id = "product_attribute_2";
    final String HDD_radioBtn_name = "product_attribute_3";
    final String OS_radioBtn_name = "product_attribute_4";
    final String SoftwareCheckboxes_name = "product_attribute_5";
    final String AddToCartBtnCSS = "button[class*=\"add-to-cart\"]";
    final String quantityOfProductTextField_id = "product_enteredQuantity_4";
    final String sizeDropdown_id = "product_attribute_9";
    final String colorOptions_id = "color-squares-10";
    final String sizeDropdown2_id = "product_attribute_6";
    final String colorDropdown_id = "product_attribute_7";
    final String printOptions_id = "image-squares-8";
    final String enterTextTF_id = "product_attribute_12";
    final String sizeOptions3_id = "product_attribute_11";
    final String sizeOptions4_id = "product_attribute_13";
    final String customerPrice1_id = "addtocart_35_CustomerEnteredPrice";
    final String customerPrice2_id = "addtocart_36_CustomerEnteredPrice";
    final String startDateTF_id = "rental_start_date_40";
    final String endDateTF_id = "rental_end_date_40";
    final String RecipientNameGift25TF_id = "giftcard_43_RecipientName";
    final String RecipientEmailGift25TF_id = "giftcard_43_RecipientEmail";
    final String SenderNameGift25TF_id = "giftcard_43_SenderName";
    final String SenderEmailGift25TF_id = "giftcard_43_SenderEmail";
    final String giftCardMsgGift25TF_id = "giftcard_43_Message";
    final String RecipientNameGift50TF_id = "giftcard_44_RecipientName";
    final String SenderNameGift50TF_id = "giftcard_44_SenderName";
    final String giftCardMsgGift50TF_id = "giftcard_44_Message";
    final String RecipientNameGift100TF_id = "giftcard_45_RecipientName";
    final String SenderNameGift100TF_id = "giftcard_45_SenderName";
    final String giftCardMsgGift100TF_id = "giftcard_45_Message";
    final String closeNotificationBar_class = "close";
    final String ShoppingCart_id = "topcartlink";
    final String ShoppingCartItems_CSS = "input[id*=\"itemquantity\"]";
    final String UpdateShoppingCartButton_id = "updatecart";
    final String ProductsNamesInShoppingCart_CSS = "a[class*=product-name]";
    final String TotalQuantityOfItemsInShoppingCart_CSS = "span[class*=\"cart-qty\"]";
    final String ProductQuantityInShoppingCartAttribute = "value";


    public String getActiveLastURL() {
        return Hooks.driver.findElement(By.cssSelector(activeLastClassCSS)).getAttribute(urlAttribute);
    }
    public By getProductsIDsBy() {
        return By.cssSelector(ProductsIDs_id);
    }
    public WebElement getProcessorOptions() {
        return Hooks.driver.findElement(By.id(processorDropdown_id));
    }
    public WebElement getRAMOptions() {
        return Hooks.driver.findElement(By.id(RAMDropdown_id));
    }
    public List<WebElement> getHDDOptions() {
        return Hooks.driver.findElements(By.name(HDD_radioBtn_name));
    }
    public List<WebElement> getOSOptions() {
        return Hooks.driver.findElements(By.name(OS_radioBtn_name));
    }
    public List<WebElement> getSoftwareOptions() {
        return Hooks.driver.findElements(By.name(SoftwareCheckboxes_name));
    }
    public String getAddToCartBtnCSS() {
        return AddToCartBtnCSS;
    }
    public List<WebElement> get_availableProductsToBeAddedToCart() {
        return Hooks.driver.findElements(By.cssSelector(AddToCartBtnCSS));
    }
    public WebElement get_availableProductToBeAddedToCart() {
        return Hooks.driver.findElement(By.cssSelector(AddToCartBtnCSS));
    }
    public WebElement getQuantityOfProductTF() {
        return Hooks.driver.findElement(By.id(quantityOfProductTextField_id));
    }
    public WebElement getSizeOptions() {
        return Hooks.driver.findElement(By.id(sizeDropdown_id));
    }
    public List<WebElement> getColorOptions() {
        return Hooks.driver.findElements(By.id(colorOptions_id));
    }
    public WebElement getSizeOptions2() {
        return Hooks.driver.findElement(By.id(sizeDropdown2_id));
    }
    public WebElement getColorOptions2() {
        return Hooks.driver.findElement(By.id(colorDropdown_id));
    }
    public List<WebElement> getPrintOptions() {
        return Hooks.driver.findElements(By.id(printOptions_id));
    }
    public WebElement getEnterTextTF() {
        return Hooks.driver.findElement(By.id(enterTextTF_id));
    }
    public WebElement getSizeOptions3() {
        return Hooks.driver.findElement(By.id(sizeOptions3_id));
    }
    public WebElement getSizeOptions4() {
        return Hooks.driver.findElement(By.id(sizeOptions4_id));
    }
    public WebElement getCustomerPrice1TF() {
        return Hooks.driver.findElement(By.id(customerPrice1_id));
    }
    public WebElement getCustomerPrice2TF() {
        return Hooks.driver.findElement(By.id(customerPrice2_id));
    }
    public WebElement getStartDateTF() {
        return Hooks.driver.findElement(By.id(startDateTF_id));
    }
    public WebElement getEndDateTF() {
        return Hooks.driver.findElement(By.id(endDateTF_id));
    }
    public WebElement getRecipientNameIn25GiftCard() {
        return Hooks.driver.findElement(By.id(RecipientNameGift25TF_id));
    }
    public WebElement getRecipientEmailIn25GiftCard() {
        return Hooks.driver.findElement(By.id(RecipientEmailGift25TF_id));
    }
    public WebElement getSenderNameIn25GiftCard() {
        return Hooks.driver.findElement(By.id(SenderNameGift25TF_id));
    }
    public WebElement getSenderEmailIn25GiftCard() {
        return Hooks.driver.findElement(By.id(SenderEmailGift25TF_id));
    }
    public WebElement getMsgIn25GiftCard() {
        return Hooks.driver.findElement(By.id(giftCardMsgGift25TF_id));
    }
    public WebElement getRecipientNameIn50GiftCard() {
        return Hooks.driver.findElement(By.id(RecipientNameGift50TF_id));
    }
    public WebElement getSenderNameIn50GiftCard() {
        return Hooks.driver.findElement(By.id(SenderNameGift50TF_id));
    }
    public WebElement getMsgIn50GiftCard() {
        return Hooks.driver.findElement(By.id(giftCardMsgGift50TF_id));
    }
    public WebElement getRecipientNameIn100GiftCard() {
        return Hooks.driver.findElement(By.id(RecipientNameGift100TF_id));
    }
    public WebElement getSenderNameIn100GiftCard() {
        return Hooks.driver.findElement(By.id(SenderNameGift100TF_id));
    }
    public WebElement getMsgIn100GiftCard() {
        return Hooks.driver.findElement(By.id(giftCardMsgGift100TF_id));
    }
    public WebElement getCloseNotificationBar() {
        return Hooks.driver.findElement(By.className(closeNotificationBar_class));
    }
    public WebElement getShoppingCart() {
        return Hooks.driver.findElement(By.id(ShoppingCart_id));
    }
    public List<WebElement> getShoppingCartItems() {
        return Hooks.driver.findElements(By.cssSelector(ShoppingCartItems_CSS));
    }
    public WebElement getShoppingCartBtn() {
        return Hooks.driver.findElement(By.id(UpdateShoppingCartButton_id));
    }
    public List<WebElement> getShoppingCartProductsNames() {
        return Hooks.driver.findElements(By.cssSelector(ProductsNamesInShoppingCart_CSS));
    }
    public WebElement getTotalQuantityFromShoppingCart() {
        return Hooks.driver.findElement(By.cssSelector(TotalQuantityOfItemsInShoppingCart_CSS));
    }
    public String getProductQuantityInShoppingCartAttribute() {
        return ProductQuantityInShoppingCartAttribute;
    }
}
