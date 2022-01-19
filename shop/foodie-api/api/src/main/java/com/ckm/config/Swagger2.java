package com.ckm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //让spring容器扫描到
@EnableSwagger2
public class Swagger2 {

    //官方访问地址：http:/localhost:8088/swagger-ui.html
    //新皮肤访问地址：http:/localhost:8088/doc.html

    //配置Swagger2核心配值 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //执行api类型为swagger2
                .apiInfo(apiInfo())                   //用于定义api文档汇总信息
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.ckm.controller")) //要扫描的包
                .paths(PathSelectors.any())  //所有的Controller
                .build();
    }

    //文档显示数据配置
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货 电商平台接口api")   //文档标题
                .contact(new Contact("陈凯明",
                        "https://www.chenkaiming.cn",
                        "2386574911@qq.com"))
                .description("专为天天吃货提供的api文档")  //详情
                .version("1.0.1") //文档版本号
                .termsOfServiceUrl("https://www.chenkaiming.cn") //网站地址
                .build();
    }
}
