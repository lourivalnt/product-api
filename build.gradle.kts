plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.productapi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Quarkus Core
	implementation("io.quarkus:quarkus-resteasy")
	implementation ("io.quarkus:quarkus-jdbc-postgresql")
	implementation ("io.quarkus:quarkus-redis-client")

	// Swagger/OpenAPI
	implementation("io.swagger.core.v3:swagger-annotations")
	implementation("io.swagger.core.v3:swagger-jaxrs2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
