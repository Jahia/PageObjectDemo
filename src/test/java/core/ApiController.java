package core;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Thread.currentThread;

/**
 * This is an utility clas that holds methods to do API calls To the DX and receive / set system properties.
 */
public final class ApiController {
    private static Logger logger = LoggerFactory.getLogger(core.ApiController.class);
    public static final String API_URL_BASE = getBaseURL() + "/modules/seleniumTests/api";

    public static String getBaseURL() {
        return new StringBuilder("http://").append(getPropertyValue("selenium.jahia.host", "localhost")).append(":").append(getPropertyValue("selenium.jahia.port", "8080")).append(getPropertyValue("selenium.jahia.context", "")).toString();
    }

    public static String getStaticResourcePath(String path) {
        try {
            byte[] body = FileUtils.readFileToByteArray(new File(currentThread().getContextClassLoader().getResource(path).toURI()));
            Unirest.setTimeouts(60000,600000);
            return Unirest.post(API_URL_BASE+"/filepath").header("Content-Type", "application/octet-stream").body(body).asJson().getBody().getObject().getString("path");
        } catch (UnirestException e) {
            return currentThread().getContextClassLoader().getResource(path).getPath();
        } catch (URISyntaxException e) {
            return currentThread().getContextClassLoader().getResource(path).getPath();
        } catch (IOException e) {
            return currentThread().getContextClassLoader().getResource(path).getPath();
        }
    }

    public static void deleteSite(String sitekey){
        try {
            HttpResponse<JsonNode> jsonResponse;
            Unirest.setTimeouts(60000,600000);
            jsonResponse = Unirest.delete(API_URL_BASE + "/site/" + sitekey).header("Content-Type","application/json").asJson();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a site from a prepackaged zip file
     * @param prepackagedSiteJson : Json file consisting sitekey, modules and prepackaged fileName
     */
    public static void createSite(String prepackagedSiteJson){
        try {
            HttpResponse<JsonNode> jsonResponse;
            String siteJson = FileUtils.readFileToString(new File(currentThread().getContextClassLoader().getResource(prepackagedSiteJson).toURI()));
            Unirest.setTimeouts(60000,600000);
            jsonResponse = Unirest.post(API_URL_BASE + "/site").header("Content-Type","application/json").body(siteJson).asJson();
            logger.error("jsonResponse = " + jsonResponse.getBody().getObject());

        } catch (IOException | URISyntaxException | UnirestException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void initWithGroovyFile(String groovyFile) {
        try {
            HttpResponse<JsonNode> jsonResponse;
            byte[] body = FileUtils.readFileToByteArray(new File(currentThread().getContextClassLoader().getResource(groovyFile).toURI()));
            Unirest.setTimeouts(60000,600000);
            jsonResponse = Unirest.post(API_URL_BASE).header("Content-Type","application/octet-stream").body(body).asJson();
            logger.error("jsonResponse = " + jsonResponse.getBody().getObject());
        } catch (IOException | URISyntaxException | UnirestException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void initWithGroovyScript(String groovyScript) {
        try {
            HttpResponse<JsonNode> jsonResponse;
            Unirest.setTimeouts(60000,600000);
            jsonResponse = Unirest.post(API_URL_BASE).header("Content-Type","application/octet-stream").body(groovyScript).asJson();
            logger.error("jsonResponse = " + jsonResponse.getBody().getObject());
        } catch (UnirestException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void precompileJSP() {
        try {
            Unirest.setTimeouts(60000,600000);
            logger.error(Unirest.post(API_URL_BASE + "/precompile").queryString("jsp_precompile", "true").asJson().getBody().getObject().toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    /**
     * Will check the state of the module in a loop until the module returns the state as STARTED.
     */
    public static void checkModuleState(){
        try {
            Unirest.setTimeouts(60000,600000);
            logger.error(Unirest.get(API_URL_BASE + "/module/" + getPropertyValue("system.jahia.bundle.mandatory", "default")).asJson().getBody().getObject().toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the module if it's already started
     */
    public static void stopModule(){
        try {
            Unirest.setTimeouts(60000,600000);
            logger.error(Unirest.post(API_URL_BASE + "/stopmodule/" + getPropertyValue("system.jahia.bundle.mandatory", "default")).asJson().getBody().getObject().toString());
        } catch (UnirestException e){
            e.printStackTrace();
        }
    }

    /**
     * Starts a module if it's stopped
     */
    public static void startModule(){
        try {
            Unirest.setTimeouts(60000,600000);
            logger.error(Unirest.post(API_URL_BASE + "/startmodule/" + getPropertyValue("system.jahia.bundle.mandatory", "default") + "/" + getPropertyValue("system.jahia.bundle.version", "default")).asJson().getBody().getObject().toString());
        } catch (UnirestException e){
            e.printStackTrace();
        }
    }


    /**
     * Check if module started, running or what.
     * @param moduleId Module ID
     * @return Module state or Exception message
     */
    public static String checkModuleState(String moduleId){
        try {
            Unirest.setTimeouts(60000,600000);
            return Unirest.get(API_URL_BASE + "/module/"+moduleId).asJson().getBody().getObject().toString();
        } catch (UnirestException e) {
            return "Unirest exception occured :(\n" +
                    e.getMessage();
        }
    }

    public static String getSiteName(){
        return System.getProperty("test.site.name");
    }

    public static String getPropertyValue(String propertyName, String defaultValue) {
        String value = System.getProperty(propertyName);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        } else {
            return value;
        }
    }
}
