package com.ssafy.alarm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api-youtube")
public class YoutubeRestController {

    private final String API_KEY = "AIzaSyDfbOsEUS4Y3LH1dLN5uoGakt72OpX7_e4";
    private final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";

    @GetMapping("/search")
    public Object search(@RequestParam String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = YOUTUBE_API_URL + "?key=" + API_KEY + "&part=snippet&maxResults=10&q=" + query + "&type=video";
        return restTemplate.getForObject(url, Object.class);
    }
}

