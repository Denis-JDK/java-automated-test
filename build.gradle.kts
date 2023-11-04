import org.codehaus.groovy.runtime.DefaultGroovyMethods.each
import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test

plugins {
    id("java")
    id("io.freefair.lombok") version "6.5.1"
}

group = "org.iteco_QA_testing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //implementation group - задает зону действия зависимости на весь проект или нет, а в отдельной папке
    //testImplementation group: 'io.rest-assured', name: 'rest-assured', version: '5.3.0'
    //15 строка из репозитория преобразовывается в строку 17, чтоб подтянулась библиотека.
    implementation("io.rest-assured:rest-assured:5.3.0")
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

}


tasks.getByName<Test>("test") {
   // ["apiUrl"].each{
    // if (project.hasProperty(it)) systemProperty it, project[it] }

    useJUnitPlatform()
    //здесь можно указать методы с отфильтрованым запуском тестов по тегами
    //includeTags ("positive")
    //excludeTags ("slow")
}
//test() {
//    ["apiUrl"] {it -> if (project.hasProperty(it)) systemProperty it, project[it] }
//}