plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("kapt") version "2.1.10"
    kotlin("plugin.spring") version "2.1.10"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.sample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.2")
    implementation("org.springframework.boot:spring-boot-starter-quartz:3.4.2") /* demo016_quartz */
    implementation("org.springframework.boot:spring-boot-starter-amqp:3.4.2") /* demo019_rabbitmq */
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.2") /* demo022_jpa */
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.2")
    implementation("org.springframework:spring-jdbc:6.2.2") /* demo024_mybatis */
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4") /* demo028_swagger */

    developmentOnly("org.springframework.boot:spring-boot-devtools:3.4.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.1")

    implementation("com.google.code.gson:gson:2.12.1") /* demo003_gson */

    kapt("org.mapstruct:mapstruct-processor:1.6.3") /* demo004_mapstruct */
    kaptTest("org.mapstruct:mapstruct-processor:1.6.3") /* demo004_mapstruct */
    implementation("org.mapstruct:mapstruct:1.6.3") /* demo004_mapstruct */
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3") /* demo004_mapstruct */

    /* demo008_multipartfile */ implementation("commons-fileupload:commons-fileupload:1.5")
    /* demo009_multipartfileimageresize */ implementation("org.imgscalr:imgscalr-lib:4.2")

    /* demo016_quartz */ implementation("org.quartz-scheduler:quartz:2.5.0")

    /* demo020_jsoup */ implementation("org.jsoup:jsoup:1.18.3") // jsoup 크롤링

    /* demo021_h2 */ implementation("com.h2database:h2:2.3.232")

    implementation("org.mybatis:mybatis:3.5.19") /* demo024_mybatis */
    implementation("org.mybatis:mybatis-spring:3.0.4") /* demo024_mybatis */

//    /* demo026_slf4j */ implementation("org.springframework.boot:spring-boot-starter-log4j2")
//    /* demo026_slf4j */ implementation("org.project-lombok:lombok:1.18.22")
//    compileOnly("org.project-lombok:lombok")
//    annotationProcessor("org.project-lombok:lombok")
//    implementation ('org.springframework.boot:spring-boot-starter-web') {
//        exclude module: 'spring-boot-starter-tomcat'
//    }

//    /* demo029_websocket */implementation("org.springframework.boot:spring-boot-starter-websocket:3.3.4")

    implementation("net.coobird:thumbnailator:0.4.20")// sample01_kotlin.demo16_image_compress // implementation("id.zelory:compressor:3.0.1")
//    implementation("com.twelvemonkeys.imageio:imageio-core:3.8.2")// sample01_kotlin.demo16_image_compress
//    implementation("com.twelvemonkeys.imageio:imageio-png:3.8.2")// sample01_kotlin.demo16_image_compress

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
