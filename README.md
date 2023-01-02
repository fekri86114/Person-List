# 📒 Person-List (updating...)
or People Notebook is about add, remove & save someone that you wanna have in your notebook.

# ❓ Languages & Info
Used Kotlin, Android-xml, RecyclerView, Room, ...

# 📑 Dependencies
`buil.gradle(:app)`:

    plugins {
        id 'com.android.application'
        id 'org.jetbrains.kotlin.android'
        id 'kotlin-kapt'
    }

    android {
        namespace 'fekri.info'
        compileSdk 33

        defaultConfig {
            applicationId "fekri.info"
            minSdk 23
            targetSdk 33
            versionCode 1
            versionName "1.0"

            testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
         }

        buildTypes {
            release {
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }
        }
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = '1.8'
        }
        buildFeatures {
            viewBinding true
        }
    }

    dependencies {

        implementation 'androidx.core:core-ktx:1.9.0'
        implementation 'androidx.appcompat:appcompat:1.5.1'
        implementation 'com.google.android.material:material:1.7.0'
        implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
        testImplementation 'junit:junit:4.13.2'
        androidTestImplementation 'androidx.test.ext:junit:1.1.4'
        androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'

        // Glide - load image
        implementation 'com.github.bumptech.glide:glide:4.13.2'
        annotationProcessor 'com.github.bumptech.glide:compiler:4.13.2'

        // Room - handle database
        def room_version = "2.4.3"
        implementation "androidx.room:room-runtime:$room_version"
        kapt "androidx.room:room-compiler:$room_version"

    }

# 🎯 More
You can update and make changes. Do whatever you want 😈

So, this is my first project in 2023!

Happy new year 🎄🎉🎊🎆

I wish everyone the best in the new year!

Hoping for freedom and humanity for Iran and other countries (which have problems) and a year without VPN or such things.
