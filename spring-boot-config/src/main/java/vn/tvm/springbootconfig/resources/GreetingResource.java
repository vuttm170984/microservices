package vn.tvm.springbootconfig.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.tvm.springbootconfig.settings.DbSetting;

@RestController
public class GreetingResource {

    @Value("${conf.greeting: This is default value}")
    private String greetingMessage;
    
    @Value("${conf.description:}")
    private String appDescription;
    
    @Value("This is the static message")
    private String staticMessage;
    
    @Value("${conf.listValues:}")
    private List<String> values;
    
    @Value("#{${conf.values:}}")
    private Map<String, String> mapValues;
    
    @Autowired
    private DbSetting dbSetting;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/greeting")
    public String greeting() {
        return greetingMessage + " - " + appDescription + " - " + staticMessage + " - "
                + values + " - " + mapValues + " - " + dbSetting.getConnection();
    }
    
    @GetMapping("/env-details")
    public String getEnvDetails() {
        // Don't look up profiles -> affects testability
        // Don't look up properties -> Should use @Value and ${}
        return env.toString();
    }
}
