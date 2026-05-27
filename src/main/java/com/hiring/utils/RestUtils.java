package com.hiring.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * RestUtils - Reusable utility class for performing REST API operations.
 * Encapsulates all HTTP methods with built-in authentication support.
 */
public class RestUtils {

    private String accessToken;

    public RestUtils() {
    }

    public RestUtils(String accessToken) {
        this.accessToken = accessToken;
    }

    // ======================== SETTERS / GETTERS ========================

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    // ======================== CRUD METHODS ========================

    /**
     * POST - Create a resource.
     */
    public Response post(String url, Object body) {
        return buildRequest().body(body).when().post(url);
    }

    /**
     * POST - Without body (e.g., trigger actions).
     */
    public Response post(String url) {
        return buildRequest().when().post(url);
    }

    /**
     * GET - Read a resource or list.
     */
    public Response get(String url) {
        return buildRequest().when().get(url);
    }

    /**
     * GET - With query parameters.
     */
    public Response get(String url, Map<String, ?> queryParams) {
        return buildRequest().queryParams(queryParams).when().get(url);
    }

    /**
     * PUT - Full update a resource.
     */
    public Response put(String url, Object body) {
        return buildRequest().body(body).when().put(url);
    }

    /**
     * PUT - With query parameters (e.g., status updates).
     */
    public Response putWithQueryParams(String url, Map<String, ?> queryParams) {
        return buildRequest().queryParams(queryParams).when().put(url);
    }

    /**
     * DELETE - Remove a resource.
     */
    public Response delete(String url) {
        return buildRequest().when().delete(url);
    }

    /**
     * PATCH - Partial update a resource.
     */
    public Response patch(String url, Object body) {
        return buildRequest().body(body).when().patch(url);
    }

    /**
     * POST - Multipart file upload.
     */
    public Response uploadFile(String url, String filePath, String fieldName) {
        RequestSpecification request = RestAssured.given()
                .header("Accept", "application/json");

        if (accessToken != null && !accessToken.isEmpty()) {
            request.header("Authorization", "Bearer " + accessToken);
        }

        return request.multiPart(fieldName, new java.io.File(filePath)).when().post(url);
    }

    /**
     * GET - With path parameters.
     */
    public Response getWithPathParams(String url, Map<String, ?> pathParams) {
        return buildRequest().pathParams(pathParams).when().get(url);
    }

    /**
     * PUT - With query parameters and body.
     */
    public Response put(String url, Object body, Map<String, ?> queryParams) {
        return buildRequest().queryParams(queryParams).body(body).when().put(url);
    }

    // ======================== HELPER ========================

    /**
     * Replaces path parameter placeholder in URL.
     * Example: replacePathParam("/api/jobs/{id}", "id", "5") returns "/api/jobs/5"
     *
     * @param url       URL with placeholder
     * @param paramName parameter name (without braces)
     * @param value     value to substitute
     * @return resolved URL
     */
    public static String replacePathParam(String url, String paramName, String value) {
        return url.replace("{" + paramName + "}", value);
    }

    /**
     * Builds a RequestSpecification with JSON headers and Bearer token.
     */
    private RequestSpecification buildRequest() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        if (accessToken != null && !accessToken.isEmpty()) {
            request.header("Authorization", "Bearer " + accessToken);
        }

        return request;
    }
}

