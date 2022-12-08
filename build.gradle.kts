import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    /* default */ id("org.springframework.boot") version "3.0.0"
    /* default */ id("io.spring.dependency-management") version "1.1.0"
    /* default */ id("org.graalvm.buildtools.native") version "0.9.18"
    /* default */ kotlin("jvm") version "1.7.21"
    /* default */ kotlin("plugin.spring") version "1.7.21"
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

    /* sample005_quartz */ implementation("org.springframework.boot:spring-boot-starter-quartz")
    /* sample005_quartz */ implementation("org.quartz-scheduler:quartz:2.3.2")

    /* sample006_rabbitmq */ implementation("org.springframework.boot:spring-boot-starter-amqp")
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
