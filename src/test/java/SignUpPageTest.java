import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class SignUpPageTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;
    public final static String createAccountField = "*//a[@ajaxify = '/reg/spotlight/']";
    public final static String firstNameField = "*//input[@name = 'firstname']";
    public final static String lastNameField = "*//input[@name = 'lastname']";
    public final static String emailOrNumberField = "*//input[@name = 'reg_email__']";
    public final static String passwordField = "*//input[@name = 'reg_passwd__']";
    public final static String monthField = "*//select[@name = 'birthday_month']";
    public final static String dayField = "*//select[@name = 'birthday_day']";
    public final static String yearField = "*//select[@name = 'birthday_year']";
    public final static String femaleField = "*//input[@type='radio' and @value = '1']";
    public final static String maleField = "*//input[@type='radio' and @value = '2']";
    public final static String customField = "*//input[@type='radio' and @value = '-1']";
    public final static String pronounField = "*//select[@name = 'preferred_pronoun']";
    public final static String optionalGenderField = "*//input[@name = 'custom_gender']";
    public final static String signUpField = "*//button[@name = 'websubmit']";


    @BeforeAll
    public static void classSetUp() {
        driver = SharedDriver.getWebDriver();

    }

    @BeforeEach
    public void classSetUpBeforeEach() throws InterruptedException {
        driver.get(HOME_PAGE_URL);
        WebElement createNewAccountElement = driver.findElement(By.xpath(createAccountField));
        assertNotNull(createNewAccountElement);
        createNewAccountElement.click();
        Thread.sleep(1000);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void classTearDownEach() {
        WebElement femaleGender = driver.findElement(By.xpath(femaleField));
        assertNotNull(femaleGender);
        femaleGender.click();

        WebElement maleGender = driver.findElement(By.xpath(maleField));
        assertNotNull(maleGender);
        maleGender.click();

        WebElement customGender = driver.findElement(By.xpath(customField));
        assertNotNull(customGender);
        customGender.click();

        WebElement selectYourPronoun = driver.findElement(By.xpath(pronounField));
        assertNotNull(selectYourPronoun);
        selectYourPronoun.sendKeys("She:n\"Wish her a happy birthday n\"");
        String selectYourPronounValue = selectYourPronoun.getAttribute("value");
        ArrayList<String> pronoun = new ArrayList<String>();
        pronoun.add("She:n\"Wish her a happy birthday n\"");
        pronoun.add("He:n\"Wish him a happy birthday n\"");
        pronoun.add("They:n\"Wish them a happy birthday n\"");
        int result = Integer.parseInt(selectYourPronounValue) - 1;
        assertEquals("She:n\"Wish her a happy birthday n\"", pronoun.get(result));

        WebElement genderOptional = driver.findElement(By.xpath(optionalGenderField));
        assertNotNull(genderOptional);
        genderOptional.sendKeys("Something new");
        String genderValue = genderOptional.getAttribute("value");
        assertEquals("Something new", genderValue);

        WebElement signUpButton = driver.findElement(By.xpath(signUpField));
        assertNotNull(signUpButton);
        signUpButton.click();
    }

    @Test
    public void openBrowserTest() {
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URL's do not match");
    }

    @ParameterizedTest
    @CsvSource({
            "Tom^%$#, Smith&%$^, *&^%^&, **(*&",
            "Tom^%$#, Smith&%$^, tomsmith, tomsmith",
            "Tom^%$#, Smith&%$^, tomsmith234@gmail, 234827468",
            "Tom^%$#, Smith&%$^, томсмис@gmail, 234827468",
            "Tomtomtomtomtomtom, Smithsmithsmithsmith, t@gmail.com, 1 ",
            "t,s,t1@gmail.com, t",
            "1234567,987654, 876347, 2348274"
    })

    public void getFirstAndLastName(String firstName, String lastName, String mobileNumberOrEmail, String password) throws InterruptedException {
        WebElement firstNameNew = driver.findElement(By.xpath(firstNameField));
        assertNotNull(firstNameNew);
        firstNameNew.sendKeys(firstName);
        String firstNameValue = firstNameNew.getAttribute("value");
        assertEquals(firstName, firstNameValue);

        WebElement lastNameNew = driver.findElement(By.xpath(lastNameField));
        assertNotNull(lastNameNew);
        lastNameNew.sendKeys(lastName);
        String lastNameValue = lastNameNew.getAttribute("value");
        assertEquals(lastName, lastNameValue);

        WebElement mobileNumberOrEmailNew = driver.findElement(By.xpath(emailOrNumberField));
        assertNotNull(mobileNumberOrEmailNew);
        mobileNumberOrEmailNew.sendKeys(mobileNumberOrEmail);
        String mobileNumberValue = mobileNumberOrEmailNew.getAttribute("value");
        assertEquals(mobileNumberOrEmail, mobileNumberValue);

        WebElement newPasswordNew = driver.findElement(By.xpath(passwordField));
        assertNotNull(newPasswordNew);
        newPasswordNew.sendKeys(password);
        String newPasswordValue = newPasswordNew.getAttribute("value");
        assertEquals(password, newPasswordValue);

        WebElement month = driver.findElement(By.xpath(monthField));
        assertNotNull(month);
        month.sendKeys("May");
        String monthValue = month.getAttribute("value");
        ArrayList<String> monthArray = new ArrayList<String>();
        monthArray.add("January");
        monthArray.add("February");
        monthArray.add("March");
        monthArray.add("April");
        monthArray.add("May");
        monthArray.add("June");
        monthArray.add("July");
        monthArray.add("August");
        monthArray.add("September");
        monthArray.add("October");
        monthArray.add("November");
        monthArray.add("December");
        int res = Integer.parseInt(monthValue) - 1;
        assertEquals("May", monthArray.get(res));

        WebElement day = driver.findElement(By.xpath(dayField));
        assertNotNull(day);
        day.sendKeys("6");
        String dayValue = day.getAttribute("value");
        assertEquals("6", dayValue);

        WebElement year = driver.findElement(By.xpath(yearField));
        assertNotNull(year);
        year.sendKeys("1997");
        String yearValue = day.getAttribute("value");
        assertEquals("6", yearValue);
    }


    @Test
    public void positiveSignUpScreenTest() throws InterruptedException {
        WebElement firstName = driver.findElement(By.xpath(firstNameField));
        assertNotNull(firstName);
        firstName.sendKeys("Tom");
        String firstNameValue = firstName.getAttribute("value");
        assertEquals("Tom", firstNameValue);

        WebElement lastName = driver.findElement(By.xpath(lastNameField));
        assertNotNull(lastName);
        lastName.sendKeys("Smith");
        String lastNameValue = lastName.getAttribute("value");
        assertEquals("Smith", lastNameValue);

        WebElement mobileNumberOrEmail = driver.findElement(By.xpath(emailOrNumberField));
        assertNotNull(mobileNumberOrEmail);
        mobileNumberOrEmail.sendKeys("+14033076543");
        String mobileNumberValue = mobileNumberOrEmail.getAttribute("value");
        assertEquals("+14033076543", mobileNumberValue);

        WebElement newPassword = driver.findElement(By.xpath(passwordField));
        assertNotNull(newPassword);
        newPassword.sendKeys("Smith");
        String newPasswordValue = newPassword.getAttribute("value");
        assertEquals("Smith", newPasswordValue);

        WebElement month = driver.findElement(By.xpath(monthField));
        assertNotNull(month);
        month.sendKeys("May");
        String monthValue = month.getAttribute("value");
        ArrayList<String> monthArray = new ArrayList<String>();
        monthArray.add("January");
        monthArray.add("February");
        monthArray.add("March");
        monthArray.add("April");
        monthArray.add("May");
        monthArray.add("June");
        monthArray.add("July");
        monthArray.add("August");
        monthArray.add("September");
        monthArray.add("October");
        monthArray.add("November");
        monthArray.add("December");
        int res = Integer.parseInt(monthValue) - 1;
        assertEquals("May", monthArray.get(res));

        WebElement day = driver.findElement(By.xpath(dayField));
        assertNotNull(day);
        day.sendKeys("6");
        String dayValue = day.getAttribute("value");
        assertEquals("6", dayValue);

        WebElement year = driver.findElement(By.xpath(yearField));
        assertNotNull(year);
        year.sendKeys("1997");
        String yearValue = day.getAttribute("value");
        assertEquals("6", yearValue);
    }
}
