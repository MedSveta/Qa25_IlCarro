package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "6/10/2024", "6/15/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "8/15/2024", "10/11/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

//    @Test
//    public void searchAnyPeriodSuccess(){
//        app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel", "8/15/2024", "6/20/2025");
//        app.getHelperCar().submit();
//        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
//
//
//    }
}
