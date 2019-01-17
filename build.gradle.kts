import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.11"
    id("org.jetbrains.kotlin.jvm").version(kotlinVersion)
    id("org.jetbrains.kotlin.plugin.noarg").version(kotlinVersion)
    id("org.jetbrains.kotlin.plugin.spring").version(kotlinVersion)

    val springBootVersion = "2.1.2.RELEASE"
    id("org.springframework.boot").version(springBootVersion)
}
apply(plugin = "io.spring.dependency-management")


noArg {
    annotation("org.axonframework.spring.stereotype.Aggregate")
}

allOpen {
    annotation("org.axonframework.spring.stereotype.Aggregate")
}

repositories {
    jcenter()
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtime("org.springframework.boot:spring-boot-starter-tomcat")

    // GraphQL
    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:5.4")
    implementation("com.graphql-java-kickstart:graphql-java-tools:5.4.1")

    // GraphiQL
    implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:5.4")

    // Axon Server
    implementation("org.axonframework:axon-spring-boot-starter:4.0.3")

    // Reactor Core
    implementation("io.projectreactor:reactor-core")
}