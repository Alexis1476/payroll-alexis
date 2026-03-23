package ch.etml.es.payroll.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@ConfigurationProperties(prefix = "employee.service")
public class EmployeeServiceProperties {
    String employeeServiceUrl;

    public String getUrl() {
        return this.employeeServiceUrl;
    }

    public void setUrl(String url) {
        this.employeeServiceUrl = url;
    }
}
