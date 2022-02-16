package vn.tvm.springbootconfig.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.tvm.springbootconfig.settings.DbSetting;

@RestController
public class GreetingResource {

    @Value("${conf.greeting: This is default value}")
    private String greetingMessage;
    
    @Value("${conf.app-description}")
    private String appDescription;
    
    @Value("This is the static message")
    private String staticMessage;
    
    @Value("${conf.app-list-values}")
    private List<String> values;
    
    @Value("#{${conf.values}}")
    private Map<String, String> mapValues;
    
    @Autowired
    private DbSetting dbSetting;
    
    @GetMapping("/greeting")
    public String greeting() {
        return greetingMessage + " - " + appDescription + " - " + staticMessage + " - "
                + values + " - " + mapValues + " - " + dbSetting.getConnection();
    }
}
