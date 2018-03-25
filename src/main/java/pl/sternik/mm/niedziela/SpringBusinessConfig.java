package pl.sternik.mm.niedziela;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "pl.sternik.mm.niedziela" }, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "pl\\.sternik\\.mm\\.niedziela\\.web\\..*") })
@ImportResource({"classpath:/applicationContext.xml"})
public class SpringBusinessConfig {
}
