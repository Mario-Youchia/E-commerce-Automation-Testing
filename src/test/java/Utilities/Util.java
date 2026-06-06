package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import stepDefinitions.Hooks;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class Util {
    public static void AlertFinish() throws Exception {
        PlaySound("Sounds/Test Finished.wav");
        Thread.sleep(1000);
        if (finishStepDefinition.numberOfScenarios != finishStepDefinition.numberOfPassedScenarios) {
            PlaySound("Sounds/Failure.wav");
            Thread.sleep(1500);
        } else {
            PlaySound("Sounds/Success.wav");
            Thread.sleep(1500);
        }
    }

    public static void PlaySound(String soundFilePath) {
        File soundFile = new File(soundFilePath);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static String GetLastCreatedFolder(String path) {
        File directory = new File(path);
        File requiredFolder = null;
        for (File folder : Objects.requireNonNull(directory.listFiles())) {
            if (folder.isDirectory()
                    && (requiredFolder == null || requiredFolder.lastModified() < folder.lastModified())) {
                requiredFolder = folder;
            }
        }
        if (requiredFolder != null) {
            return requiredFolder.getAbsolutePath();
        } else {
            return null;
        }
    }

    public static String GetAbsolutePathOfReport(String ReportFolder, String ReportSubFolder) {
        String AbsolutePath;
        String str = GetLastCreatedFolder(ReportFolder);
        AbsolutePath = str + ReportSubFolder;
        return AbsolutePath;
    }

    public static void OpenReport(String ReportFolder, String ReportSubFolder) {
        String ReportPath = GetAbsolutePathOfReport(ReportFolder, ReportSubFolder);
        File report = new File(ReportPath);
        try {
            Process process = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + report);
            process.waitFor();
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (InterruptedException e) {
            System.out.println("waitFor() is interrupted");
        }
    }

    public static String generateName(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

    public static String generateEmail(int lengthOfEmailUsername, int lengthOfEmailDomain) {
        String EmailUsername = generateName(lengthOfEmailUsername);
        String EmailDomain = generateName(lengthOfEmailDomain);
        String Email = EmailUsername + "@" + EmailDomain + ".com";
        return Email;
    }

    public static String convertRGBaTohex(String RGBaColor) {
        String[] hexValue = RGBaColor.replace("rgba(", "").replace(")", "").split(",");
        return String.format("#%2x%2x%2x", Integer.parseInt(hexValue[0]), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
    }

    public static int[] InitializeArrayByNegativeOnes(int numOfElements) {
        int[] negativeOnesArray = new int[numOfElements];
        for (int i = 0; i < numOfElements; i++) {
            negativeOnesArray[i] = -1;
        }
        return negativeOnesArray;
    }

    public static int[] getIndicesOfCategoriesHavingSubCategories() {
        int numberOfCategories = Hooks.searchPage.getNumberOfMainCategories();
        int[] arrayOfIndicesOfCategoriesHavingSubCategories = InitializeArrayByNegativeOnes(numberOfCategories);
        int j = 0;
        for (int i = 0; i < Hooks.searchPage.getNumberOfMainCategories(); i++) {
            int indexInHTML = i + 1;
            int sizeOfElement = Hooks.driver.findElements(By.xpath(Hooks.searchPage.getRootOfMenuCategoriesXPath() + "/li[" + indexInHTML + "]/ul")).size();
            if (sizeOfElement > 0) {
                arrayOfIndicesOfCategoriesHavingSubCategories[j] = indexInHTML;
                j++;
            }
        }
        return arrayOfIndicesOfCategoriesHavingSubCategories;
    }

    public static int getNumberOfParents() {
        int[] arrayOfIndicesOfCategoriesHavingSubCategories = getIndicesOfCategoriesHavingSubCategories();
        int numberOfParents = Hooks.searchPage.getNumberOfMainCategories();
        for (int arrayOfIndicesOfCategoriesHavingSubCategory : arrayOfIndicesOfCategoriesHavingSubCategories) {
            if (arrayOfIndicesOfCategoriesHavingSubCategory != -1) {
                numberOfParents--;
                numberOfParents += 3;
            }
        }
        return numberOfParents;
    }

    public static String[] getXPathSelectorsOfDirectParentsToCategories() {
        int numberOfParents = getNumberOfParents();
        int[] arrayOfIndicesOfCategoriesHavingSubCategories = getIndicesOfCategoriesHavingSubCategories();
        String[] directParentToCategoryOrSubCategoryXPath = new String[numberOfParents];
        int k = 0;
        for (int i = 0; i < arrayOfIndicesOfCategoriesHavingSubCategories.length; i++) {
            int indexInHTML1 = i + 1;
            String path;
            if (arrayOfIndicesOfCategoriesHavingSubCategories[i] != -1) {
                for (int j = 0; j < Hooks.searchPage.getNumberOfSubcategories(); j++) {
                    int indexInHTML2 = j + 1;
                    path = Hooks.searchPage.getRootOfMenuCategoriesXPath() + "/li[" + indexInHTML1 + "]/ul/li[" + indexInHTML2 + "]";
                    directParentToCategoryOrSubCategoryXPath[k] = path;
                    k++;
                }
            } else {
                path = Hooks.searchPage.getRootOfMenuCategoriesXPath() + "/li[" + indexInHTML1 + "]";
                directParentToCategoryOrSubCategoryXPath[k] = path;
                k++;
            }
        }
        return directParentToCategoryOrSubCategoryXPath;
    }

    public static String[] getURLsOfAllCategoriesAndSubcategories() {
        String[] directParentToCategoryOrSubCategoryXPath = getXPathSelectorsOfDirectParentsToCategories();
        String[] URLsOfAllCategoriesAndSubCategories = new String[directParentToCategoryOrSubCategoryXPath.length];
        for (int i = 0; i < directParentToCategoryOrSubCategoryXPath.length; i++) {
            String xPath = directParentToCategoryOrSubCategoryXPath[i] + Hooks.searchPage.getTagContainingURLOfCategory();
            String url = Hooks.driver.findElement(By.xpath(xPath)).getAttribute(Hooks.searchPage.getAttributeOfURLOfCategories());
            URLsOfAllCategoriesAndSubCategories[i] = url;
        }
        Hooks.URLsOfAllCategoriesAndSubcategories = URLsOfAllCategoriesAndSubCategories;
        return URLsOfAllCategoriesAndSubCategories;
    }

    public static List<String> getAllProductsOnWebsite() {
        List<String> AllProducts = new ArrayList<>();
        String[] URLsOfAllCategoriesAndSubCategories = getURLsOfAllCategoriesAndSubcategories();
        for (String urLsOfAllCategoriesAndSubCategory : URLsOfAllCategoriesAndSubCategories) {
            Hooks.driver.navigate().to(urLsOfAllCategoriesAndSubCategory);
            List<WebElement> webElements = Hooks.driver.findElements(By.cssSelector(Hooks.searchPage.getProductTitleClassCSS()));
            for (WebElement webElement : webElements) {
                AllProducts.add(webElement.getText());
            }
        }
        return AllProducts;
    }

    public static List<String> getInitialSearchKeywords() {
        List<String> AllProducts = getAllProductsOnWebsite();
        List<String> InitialSearchKeywords = new ArrayList<>();
        for (String product : AllProducts) {

            String[] splitProduct = product.split(" ");

            for (int j = 0; j < splitProduct.length; j++) {
                splitProduct[j] = splitProduct[j].trim();
                if (splitProduct[j].length() > 0) {
                    InitialSearchKeywords.add(splitProduct[j]);
                }
            }
        }
        return InitialSearchKeywords;
    }

    public static List<String> getValidSearchKeywords() {
        // Since some keywords like "a" is not valid because
        // number of characters is less than 3, then we have
        // to remove invalid keywords from the list.
        List<String> InitialSearchKeywords = getInitialSearchKeywords();
        List<String> validSearchKeywords = new ArrayList<>();
        for (String initialSearchKeyword : InitialSearchKeywords) {
            if (initialSearchKeyword.length() >= Hooks.searchPage.getMinimumNumberOfCharForSearch()) {
                validSearchKeywords.add(initialSearchKeyword);
            }
        }
        return validSearchKeywords;
    }

    public static List<String> getAllPossibleSearchKeywords() {
        // Since a search keyword is considered as a valid keyword
        // if the number of its characters is 3 (at least).
        // This function is used to get all possible combinations
        // of each of the valid keywords.
        // For example: the possible combinations of Build are
        // Bui - uil - ild ... and so on.
        List<String> validSearchKeywords = getValidSearchKeywords();
        List<String> AllPossibleSearchKeywords = new ArrayList<>();
        for (String search : validSearchKeywords) {
            char[] arrayOfChar = search.toCharArray();
            for (int i = 0; i < search.length(); i++) {
                if (i + Hooks.searchPage.getMinimumNumberOfCharForSearch() - 1 < search.length()) {
                    char[] subArr = {arrayOfChar[i], arrayOfChar[i + 1], arrayOfChar[i + 2]};
                    String subStr = new String(subArr);
                    AllPossibleSearchKeywords.add(subStr);
                }
            }
        }
        // Since keywords are 3 characters only, so the probability of having repeated elements increases.
        // So, this function is used to remove any repeated elements.
        AllPossibleSearchKeywords = AllPossibleSearchKeywords.stream().distinct().collect(Collectors.toList());
        return AllPossibleSearchKeywords;
    }

    public static String getARandomSearchKeyword() {
        Random randomProduct = new Random();
        int indexOfProduct = randomProduct.nextInt(Hooks.allPossibleSearchKeywords.size());
        String searchKeyword = Hooks.allPossibleSearchKeywords.get(indexOfProduct);
        return searchKeyword;
    }

    public static void switchCurrency(String currentCurrency) {
        Select currencyOptions = new Select(Hooks.currency.CurrencyDropdown());
        if (Objects.equals(currentCurrency, Hooks.currencies[0])) {
            currencyOptions.selectByVisibleText(Hooks.currencies[1]);
        } else {
            currencyOptions.selectByVisibleText(Hooks.currencies[0]);
        }
    }

    public static void hoverToRandomCategory() {
        Hooks.indexOfCategory = new Random().nextInt(Hooks.indicesOfCategory.length);
        WebElement category = Hooks.driver.findElement(By.cssSelector(Hooks.category.getMainCategoryCSS()));
        Hooks.CategoryURL = category.getAttribute("href");
        Hooks.actions.moveToElement(category);
        if (Hooks.indexOfCategory < 3) {
            Hooks.indexOfSubCategory = new Random().nextInt(Hooks.indicesOfSubCategory[Hooks.indexOfCategory].length);
            WebElement subCategory = Hooks.driver.findElement(By.cssSelector(Hooks.category.getSubCategoryCSS()));
            Hooks.CategoryURL = subCategory.getAttribute("href");
            Hooks.actions.moveToElement(subCategory);
        }
    }

    public static int getNumberOfProductsInCurrentCategory() {
        return Hooks.driver.findElements(Hooks.shoppingCart.getProductsIDsBy()).size();
    }

    public static int getNumberOfProductsAvailableToBeAddedToCart() {
        return Hooks.shoppingCart.get_availableProductsToBeAddedToCart().size();
    }

    public static void Build_your_own_computer_settings() {
        Select ProcessorOptions = new Select(Hooks.shoppingCart.getProcessorOptions());
        ProcessorOptions.selectByIndex(new Random().nextInt(2) + 1);
        Select RAMOptions = new Select(Hooks.shoppingCart.getRAMOptions());
        RAMOptions.selectByIndex(new Random().nextInt(3) + 1);
        Hooks.shoppingCart.getHDDOptions().get(new Random().nextInt(Hooks.shoppingCart.getHDDOptions().size())).click();
        Hooks.shoppingCart.getOSOptions().get(new Random().nextInt(Hooks.shoppingCart.getOSOptions().size())).click();
        // Clear default selected checkbox
        Hooks.shoppingCart.getSoftwareOptions().get(0).click();
        // Select a number of software options to be selected
        int numberOfOptions = new Random().nextInt(3) + 1;
        for (int i = 0; i < numberOfOptions; i++) {
            int softwareOption = new Random().nextInt(3);
            while (!Hooks.shoppingCart.getSoftwareOptions().get(softwareOption).isSelected()) {
                Hooks.shoppingCart.getSoftwareOptions().get(softwareOption).click();
            }
        }
    }

    public static void Apple_MacBook_Pro_13_inch_settings() {
        Hooks.shoppingCart.getQuantityOfProductTF().clear();
        Hooks.shoppingCart.getQuantityOfProductTF().sendKeys(Integer.toString(new Random().nextInt(5) + 2));
    }

    public static void adidas_Consortium_Campus_80s_Running_Shoes_settings() {
        Select SizeOptions = new Select(Hooks.shoppingCart.getSizeOptions());
        SizeOptions.selectByIndex(new Random().nextInt(4) + 1);
        Hooks.shoppingCart.getColorOptions().get(new Random().nextInt(Hooks.shoppingCart.getColorOptions().size())).click();
    }

    public static void Nike_Floral_Roshe_Customized_Running_Shoes_settings() {
        Select SizeOptions2 = new Select(Hooks.shoppingCart.getSizeOptions2());
        SizeOptions2.selectByIndex(new Random().nextInt(4) + 1);
        Select ColorOptions2 = new Select(Hooks.shoppingCart.getColorOptions2());
        ColorOptions2.selectByIndex(new Random().nextInt(2) + 1);
        Hooks.shoppingCart.getPrintOptions().get(new Random().nextInt(Hooks.shoppingCart.getPrintOptions().size())).click();
    }

    public static void Custom_T_Shirt_settings() {
        Hooks.shoppingCart.getEnterTextTF().sendKeys(generateName(20));
    }

    public static void Nike_Tailwind_Loose_Short_Sleeve_Running_Shirt_settings() {
        Select SizeOptions3 = new Select(Hooks.shoppingCart.getSizeOptions3());
        SizeOptions3.selectByIndex(new Random().nextInt(6) + 1);
    }

    public static void Obey_Propaganda_Hat_settings() {
        Select SizeOptions4 = new Select(Hooks.shoppingCart.getSizeOptions4());
        SizeOptions4.selectByIndex(new Random().nextInt(4) + 1);
    }

    public static void If_You_Wait_donation_settings() {
        double max = 100;
        double min = 0.5;
        // Generate a random double between 0.5 to 100
        Hooks.shoppingCart.getCustomerPrice1TF().clear();
        Hooks.shoppingCart.getCustomerPrice1TF().sendKeys(Double.toString((new Random().nextInt((int) ((max - min) * 100 + 1)) + min * 100) / 100));
    }

    public static void Science_Faith_settings() {
        double max = 1000;
        double min = 0.5;
        // Generate a random double between 0.5 to 1000
        Hooks.shoppingCart.getCustomerPrice2TF().clear();
        Hooks.shoppingCart.getCustomerPrice2TF().sendKeys(Double.toString((new Random().nextInt((int) ((max - min) * 100 + 1)) + min * 100) / 100));
    }

    public static int numberOfDaysInMonth(int monthNumber, int yearNumber) {
        if (monthNumber == 1 || monthNumber == 3 || monthNumber == 5 || monthNumber == 7 || monthNumber == 8 || monthNumber == 10 || monthNumber == 12) {
            return 31;
        } else if (monthNumber == 2) {
            if (yearNumber % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }

    public static int[] GenerateDate(int startYear, int endYear, int startMonth, int startDay) {
        int[] Date = new int[3];

        int year = year(startYear, endYear);
        int month = month(startYear, startMonth, year);
        int day = day(startYear, startMonth, startDay, year, month);

        Date[0] = day;
        Date[1] = month;
        Date[2] = year;
        return Date;
    }

    public static int day(int startYear, int startMonth, int startDay, int CurrentYear, int CurrentMonth) {
        int start;
        int end;
        if (CurrentYear == startYear && CurrentMonth == startMonth) {
            start = startDay;
        } else {
            start = 1;
        }
        end = numberOfDaysInMonth(CurrentMonth, CurrentYear);
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static int month(int startYear, int startMonth, int year) {
        int start;
        int end;
        if (year == startYear) {
            start = startMonth;
        } else {
            start = 1;
        }
        end = 12;
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static int year(int startYear, int endYear) {
        return startYear + (int) Math.round(Math.random() * (endYear - startYear));
    }

    public static String getStartDate(int day, int month, int year) {
        return month + "/" + day + "/" + year;
    }

    public static void Elegant_Gemstone_Necklace_rental_settings() {
        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int CurrentYear = currentDate.getYear();
        int CurrentMonth = currentDate.getMonthValue();
        int CurrentDay = currentDate.getDayOfMonth();
        int[] startDate = GenerateDate(CurrentYear, CurrentYear + 10, CurrentMonth, CurrentDay);
        String start_Date = getStartDate(startDate[0], startDate[1], startDate[2]);
        int[] endDate = GenerateDate(startDate[2], startDate[2] + 10, startDate[1], startDate[0]);
        String end_Date = getStartDate(endDate[0], endDate[1], endDate[2]);
        Hooks.shoppingCart.getStartDateTF().clear();
        Hooks.shoppingCart.getStartDateTF().sendKeys(start_Date);
        Hooks.shoppingCart.getEndDateTF().clear();
        Hooks.shoppingCart.getEndDateTF().sendKeys(end_Date);
    }

    public static void Gift_Card_25_settings() {
        Hooks.shoppingCart.getRecipientNameIn25GiftCard().sendKeys(generateName(10));
        Hooks.shoppingCart.getRecipientEmailIn25GiftCard().sendKeys(generateEmail(15, 15));
        Hooks.shoppingCart.getSenderNameIn25GiftCard().sendKeys(generateName(10));
        Hooks.shoppingCart.getSenderEmailIn25GiftCard().sendKeys(generateEmail(15, 15));
        Hooks.shoppingCart.getMsgIn25GiftCard().sendKeys(generateName(300));
    }

    public static void Gift_Card_50_settings() {
        Hooks.shoppingCart.getRecipientNameIn50GiftCard().sendKeys(generateName(10));
        Hooks.shoppingCart.getSenderNameIn50GiftCard().sendKeys(generateName(10));
        Hooks.shoppingCart.getMsgIn50GiftCard().sendKeys(generateName(150));
    }

    public static void Gift_Card_100_settings() {
        Hooks.shoppingCart.getRecipientNameIn100GiftCard().sendKeys(generateName(10));
        Hooks.shoppingCart.getSenderNameIn100GiftCard().sendKeys(generateName(10));
        Hooks.shoppingCart.getMsgIn100GiftCard().sendKeys(generateName(200));
    }

    public static void AddProductToCart() {
        int numberOfProduct = new Random().nextInt(getNumberOfProductsAvailableToBeAddedToCart()) + 1;
        String CurrentActiveURL = Hooks.shoppingCart.getActiveLastURL();
        Hooks.shoppingCart.get_availableProductsToBeAddedToCart().get(numberOfProduct - 1).click();
        Hooks.isProductOutOfStock = false;
        // https://demo.nopcommerce.com/desktops
        if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[0])) {
            if (numberOfProduct == 1) {
                Build_your_own_computer_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            }
        } // https://demo.nopcommerce.com/notebooks
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[1])) {
            if (numberOfProduct == 1) {
                Apple_MacBook_Pro_13_inch_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            }
        } // https://demo.nopcommerce.com/camera-photo
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[3])) {
            if (numberOfProduct == 1) {
                sleep(1);
                Hooks.shoppingCart.get_availableProductsToBeAddedToCart().get(new Random().nextInt(getNumberOfProductsAvailableToBeAddedToCart())).click();
            }
        } // https://demo.nopcommerce.com/shoes
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[6])) {
            if (numberOfProduct == 1) {
                adidas_Consortium_Campus_80s_Running_Shoes_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            } else if (numberOfProduct == 2) {
                Nike_Floral_Roshe_Customized_Running_Shoes_settings();
                // To avoid intercepted element exception use javascript executor.
                ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click()", Hooks.shoppingCart.get_availableProductToBeAddedToCart());
            }
        } // https://demo.nopcommerce.com/clothing
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[7])) {
            if (numberOfProduct == 1) {
                Custom_T_Shirt_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            } else if (numberOfProduct == 3) {
                Nike_Tailwind_Loose_Short_Sleeve_Running_Shirt_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            }
        } // https://demo.nopcommerce.com/accessories
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[8])) {
            if (numberOfProduct == 1) {
                Obey_Propaganda_Hat_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            } else if (numberOfProduct == 3) {
                Hooks.isProductOutOfStock = true;
            }
        } // https://demo.nopcommerce.com/digital-downloads
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[9])) {
            if (numberOfProduct == 1) {
                If_You_Wait_donation_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            } else if (numberOfProduct == 3) {
                Science_Faith_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            }
        } // https://demo.nopcommerce.com/jewelry
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[11])) {
            if (numberOfProduct == 1) {
                Elegant_Gemstone_Necklace_rental_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            }
        } // https://demo.nopcommerce.com/gift-cards
        else if (Objects.equals(CurrentActiveURL, Hooks.URLsOfAllCategoriesAndSubcategories[12])) {
            if (numberOfProduct == 1) {
                Gift_Card_25_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            } else if (numberOfProduct == 2) {
                Gift_Card_50_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            } else if (numberOfProduct == 3) {
                Gift_Card_100_settings();
                Hooks.shoppingCart.get_availableProductToBeAddedToCart().click();
            }
        }
    }

    public static void sleep(int numberOfSeconds) {
        int numberOfSecondsInMillis = numberOfSeconds * 1000;
        try {
            Thread.sleep(numberOfSecondsInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updateShoppingCartItemQuantity(int indexOfItemInShoppingCart, int increasedValue) {
        List<WebElement> shoppingCartItems = Hooks.shoppingCart.getShoppingCartItems();
        String numberOfQuantitiesBeforeEdit = shoppingCartItems.get(indexOfItemInShoppingCart).getAttribute("value");
        int newNumberOfQuantities = Integer.parseInt(numberOfQuantitiesBeforeEdit) + increasedValue;
        shoppingCartItems.get(indexOfItemInShoppingCart).clear();
        shoppingCartItems.get(indexOfItemInShoppingCart).sendKeys(Integer.toString(newNumberOfQuantities));
    }

    public static void printShoppingCartProductsNamesAndQuantities() {
        List<WebElement> productsNames = Hooks.shoppingCart.getShoppingCartProductsNames();
        List<WebElement> productsQuantities = Hooks.shoppingCart.getShoppingCartItems();
        System.out.println("Product (Quantity)");
        for (int i = 0; i < productsNames.size(); i++) {
            String product = productsNames.get(i).getText();
            String quantity = productsQuantities.get(i).getAttribute(Hooks.shoppingCart.getProductQuantityInShoppingCartAttribute());
            System.out.println(product + "(" + quantity + ")");
        }

        System.out.println("Total quantity = " + totalQuantityInShoppingCart());
    }

    public static int totalQuantityInShoppingCart() {
        List<WebElement> productsQuantities = Hooks.shoppingCart.getShoppingCartItems();
        int total = 0;
        for (WebElement productsQuantity : productsQuantities) {
            total += Integer.parseInt(productsQuantity.getAttribute(Hooks.shoppingCart.getProductQuantityInShoppingCartAttribute()));
        }
        return total;
    }
}