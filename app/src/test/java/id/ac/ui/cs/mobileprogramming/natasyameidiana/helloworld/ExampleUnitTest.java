package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void checkInput_nameIsNull() {
        String name = null;
        String phone = "012301230123";
        String result = ActivityHelper.giveResult(name, phone);
        assertEquals(result, "Name or Phone must be filled, found: name:" + name + " phone:" + phone);
    }

    @Test
    public void checkInput_phoneIsNull() {
        String name = "caca";
        String phone = null;
        String result = ActivityHelper.giveResult(name,phone);
        assertEquals(result, "Name or Phone must be filled, found: name:" + name + " phone:" + phone);
    }

    @Test
    public void checkInput_filledIsNull() {
        String name = null;
        String phone = null;
        String result = ActivityHelper.giveResult(name, phone);
        assertEquals(result, "Name or Phone must be filled, found: name:" + name + " phone:" + phone);
    }

    @Test
    public void checkInput_phonenumberIsNotValid() {
        String name = "caca";
        String phone = "012301230";
        String result = ActivityHelper.giveResult(name, phone);
        assertEquals(result, "Phone number is not valid");
    }
}