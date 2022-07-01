package eu.morozik.historicalplaces.resttemplate.controller;

import eu.morozik.historicalplaces.dto.PeopleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resttemplate")
public class RestTemplateController {

    private final RestTemplate restTemplate;

    @GetMapping("/simpleresponse")
    public String simpleResponse() {
        String url = "http://app2:8080/myapp/simpleresponse";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    @GetMapping("responsedto")
    public PeopleDto responseDto(@RequestParam String name) {
        String url = "http://app2:8080/myapp/responsedto?name=" + name;
        return restTemplate.getForObject(url, PeopleDto.class);
    }
}
