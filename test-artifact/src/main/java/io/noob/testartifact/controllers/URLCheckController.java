package io.noob.testartifact.controllers;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//REST SERVICE TO CHECK INPUT URL
@RestController
public class URLCheckController {
    //ERROR MESSAGES
    private final String SITE_UP = "website is up!";
    private final String SITE_DOWN = "website is down!";
    private final String INCORRECT_URL = "url is incorrect!";

    @GetMapping("/check")
    public String getStatusMessage(@RequestParam String url){
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();;
            if(responseCode == 200){
                returnMessage = SITE_UP;
            }

            else{
                returnMessage = SITE_DOWN;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e){
            returnMessage = SITE_DOWN;
        }
        return returnMessage;
    }
}
