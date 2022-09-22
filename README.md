# Libreria-QRGenerator-Kotlin-Android-Studio

Ejemplo para generar codigos QR y guardar usando la Librería QRGenerator en Kotlin y Android Studio

Librería: implementation 'com.github.kenglxn.QRGen:android:2.5.0'

settings.gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...

        maven { url 'https://jitpack.io' }
    }
}

build.gradle (Module)
dependencies {
    ...
    
    implementation 'com.github.kenglxn.QRGen:android:2.5.0'
    
    ...
}
