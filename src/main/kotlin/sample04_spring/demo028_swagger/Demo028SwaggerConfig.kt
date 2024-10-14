package sample04_spring.demo028_swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Demo028SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        val info: Info = Info()
            .version("Demo028SwaggerConfig.kt swgger config info version")
            .title("Demo028SwaggerConfig.kt swgger config info title")
            .description("Demo028SwaggerConfig.kt swgger config info description")

        return OpenAPI()
            .info(info)
    }

}

/*
#swagger-ui 설정
#swagger-ui 접속 경로(http://localhost:4000/swagger-ui/index.html)
springdoc.swagger-ui.path=/swagger-ui.html

#OpenAPI JSON 문서 확인 주소 (http://localhost:4000/api-docs)
springdoc.api-docs.path=/api-docs

#기본 요청 컨텐츠 json
springdoc.default-consumes-media-type=application/json
springdoc.default-produces-media-type=application/json

#ui 알파벳 순 정렬
springdoc.swagger-ui.operations-sorter=alpha
springdoc.swagger-ui.tags-sorter=alpha

#swagger url 비활성화, json화 된 config 파일 대신 파라미터를 이용하여 swagger-ui에 접근하도록 함
springdoc.swagger-ui.disable-swagger-default-url=true
springdoc.swagger-ui.display-query-params-without-oauth2=true
springdoc.swagger-ui.doc-expansion=none
 */
