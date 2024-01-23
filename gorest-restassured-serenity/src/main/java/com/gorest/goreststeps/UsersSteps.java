package com.gorest.goreststeps;

import com.gorest.constants.EndPoints;
import com.gorest.models.UsersPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class UsersSteps {
    @Step("Creating user with name : {0}, email : {1}, gender {2}, status : {3}")

    public ValidatableResponse CreateUser(String name, String email, String gender, String status) {

        UsersPojo userPojo = UsersPojo.getUserPojo(name, email, gender, status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 22569421895bacfdab09c3342e55ad05fa445d1c97cd3f6bc2466e2fabf00a05")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Getting user information by userId:{0}")

    public HashMap<String,Object> getUserInfoById(int userId){

        HashMap<String,Object> userMap = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 22569421895bacfdab09c3342e55ad05fa445d1c97cd3f6bc2466e2fabf00a05")
                .pathParam("userID", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");
        return userMap;

    }
    @Step("Updating user with userId: {0},name: {1}, email: {2}, gender: {3}, status: {4}")

    public ValidatableResponse updateUser(int userID,String name,String email, String gender, String status){

        UsersPojo userPojo = UsersPojo.getUserPojo(name,email,gender,status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 22569421895bacfdab09c3342e55ad05fa445d1c97cd3f6bc2466e2fabf00a05")
                .pathParam("userID",userID)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)

                .then();
    }
    @Step("Deleting user information with userId: {0}")
    public ValidatableResponse deleteUser(int userID){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 22569421895bacfdab09c3342e55ad05fa445d1c97cd3f6bc2466e2fabf00a05")
                .pathParam("userID",userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting user by userId: {0}")
    public ValidatableResponse getUserById(int userID){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 22569421895bacfdab09c3342e55ad05fa445d1c97cd3f6bc2466e2fabf00a05")
                .pathParam("userID",userID)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }

}
