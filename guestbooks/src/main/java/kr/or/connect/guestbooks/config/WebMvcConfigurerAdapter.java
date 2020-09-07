package kr.or.connect.guestbooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //얘가 설정파일이다.
@EnableWebMvc //기본적인 설정들을 자동으로 해줘라.
@ComponentScan(basePackages = {"kr.or.connect.guestbooks.controller"}) //controller 알아서 읽어와라
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter {
	//이 설정들은 DispatcherServlet이 읽을거야
	//아래의 메서드는 url요청이 /css/**처럼 들어오는 것을 /css/ 처럼 처리해준다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
	// default servlet handler를 사용하게 함
	// 매핑정보가 없는 url 요청을 spring의 defaultservlethttprequesthandler가 처리하도록 한다.
	// 그렇게 되념 매핑 없는 url 들어왔을때 WAS의 default servlet이 static한 자원을 읽어서 보여줄 수 있게 한다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	// 특정 url에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있도록 해주는 부분
	// '/'라는 요청이 오면 'index'라는 이름의 뷰로 보여줘라.
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("index");
    }
	// 아래는 적절한 view resolver가 실제로 뷰의 이름을 갖고 어떤 뷰인지에 대한 정보를 찾아낼 수 있게 해준다.
	// index가 들어오면 /WEB-INF/views/index.jsp를 보여주세요 라고 하게끔
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
