import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    /* default */ kotlin("jvm") version "1.9.25"
    /* default */ kotlin("plugin.spring") version "2.0.21"
    /* default */ id("org.springframework.boot") version "3.3.4"
    /* default */ id("io.spring.dependency-management") version "1.1.6"
    /* demo004_mapstruct */ kotlin("kapt") version "2.0.21"
}

group = "com.sample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    /* default */ mavenCentral()
}

dependencies {
    /* default */ implementation("org.springframework.boot:spring-boot-starter-web")
    /* default */ implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    /* default */ implementation("org.jetbrains.kotlin:kotlin-reflect")
    /* default */ implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    /* default */ developmentOnly("org.springframework.boot:spring-boot-devtools")
    /* default */ testImplementation("org.springframework.boot:spring-boot-starter-test")

    /* demo003_gson */ implementation("com.google.code.gson:gson:2.10")

    /* demo004_mapstruct */ kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
    /* demo004_mapstruct */ kaptTest("org.mapstruct:mapstruct-processor:1.5.3.Final")
    /* demo004_mapstruct */ implementation("org.mapstruct:mapstruct:1.5.3.Final")
    /* demo004_mapstruct */ annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    /* demo007_restful */ implementation("org.springframework.boot:spring-boot-starter-validation:2.7.1")


    /* demo008_multipartfile */ implementation("commons-fileupload:commons-fileupload:1.5")
    /* demo009_multipartfileimageresize */ implementation("org.imgscalr:imgscalr-lib:4.2")

    /* demo016_quartz */ implementation("org.springframework.boot:spring-boot-starter-quartz")
    /* demo016_quartz */ implementation("org.quartz-scheduler:quartz:2.3.2")

    /* demo019_rabbitmq */ implementation("org.springframework.boot:spring-boot-starter-amqp")

    /* demo020_jsoup */ implementation("org.jsoup:jsoup:1.15.3") // jsoup 크롤링

    /* demo021_h2 */ implementation("com.h2database:h2")

    /* demo022_jpa */ implementation("org.springframework.boot:spring-boot-starter-data-jpa")

//    /* demo026_slf4j */ implementation("org.springframework.boot:spring-boot-starter-log4j2")
//    /* demo026_slf4j */ implementation("org.project-lombok:lombok:1.18.22")
//    compileOnly("org.project-lombok:lombok")
//    annotationProcessor("org.project-lombok:lombok")
//    implementation ('org.springframework.boot:spring-boot-starter-web') {
//        exclude module: 'spring-boot-starter-tomcat'
//    }




    /* kotlin coroutines */ implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")


    /* demo004 og */implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
