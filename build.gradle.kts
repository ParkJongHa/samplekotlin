import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    /* default */ id("org.springframework.boot") version "3.0.0"
    /* default */ id("io.spring.dependency-management") version "1.1.0"
    /* default */ id("org.graalvm.buildtools.native") version "0.9.18"
    /* default */ kotlin("jvm") version "1.7.21"
    /* default */ kotlin("plugin.spring") version "1.7.21"
    /* demo010_mapstruct */ kotlin("kapt") version "1.7.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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

    /* demo005_quartz */ implementation("org.springframework.boot:spring-boot-starter-quartz")
    /* demo005_quartz */ implementation("org.quartz-scheduler:quartz:2.3.2")

    /* demo006_rabbitmq */ implementation("org.springframework.boot:spring-boot-starter-amqp")

    /* demo007_h2 */ implementation("com.h2database:h2")

    /* demo008_jpa */ implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    /* demo009_gson */ implementation("com.google.code.gson:gson:2.10")

    /* demo010_mapstruct */ kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
    /* demo010_mapstruct */ kaptTest("org.mapstruct:mapstruct-processor:1.5.3.Final")
    /* demo010_mapstruct */ implementation("org.mapstruct:mapstruct:1.5.3.Final")
    /* demo010_mapstruct */ annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    /* multipartfile */ implementation("commons-fileupload:commons-fileupload:1.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        /* default */ freeCompilerArgs = listOf("-Xjsr305=strict")
        /* default */ jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
