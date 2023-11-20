plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))


    //Testing libraries
    implementation("junit:junit:4.13.2")
}
