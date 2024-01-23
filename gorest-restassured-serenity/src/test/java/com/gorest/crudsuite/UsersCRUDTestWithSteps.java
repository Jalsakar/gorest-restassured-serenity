package com.gorest.crudsuite;

import com.gorest.goreststeps.UsersSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UsersCRUDTestWithSteps extends TestBase {

    static String name = TestUtils.getRandomName() + "Sona";
    static String email = TestUtils.getRandomString() + "@gmail.com";
    static String gender = "female";
    static String status = "active";
    static int userID;

    @Steps
    UsersSteps usersSteps;

    @Title("Creating new user")
    @Test
    public void test001() {
        ValidatableResponse response = usersSteps.CreateUser(name, email, gender, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @Title("Verifying that a new user is added")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = usersSteps.getUserInfoById(userID);
        Assert.assertThat(userMap, hasValue(userID));
        System.out.println(userMap);
    }

    @Title("Updating the user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        usersSteps.updateUser(userID, name, email, gender, status).statusCode(200);
        HashMap<String, Object> userMap = usersSteps.getUserInfoById(userID);
        Assert.assertThat(userMap, hasValue(userID));
    }

    @Title("Deleting user information with ID and verify that user is deleted ")
    @Test
    public void test004() {
        usersSteps.deleteUser(userID).statusCode(204);
        usersSteps.getUserById(userID).statusCode(404);
    }

}
