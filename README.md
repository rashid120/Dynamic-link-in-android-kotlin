Step 1 - connect your app to firebase

Step 2 - add dependency in gradle (build.gradle.kts(Module: app))
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation (platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation ("com.google.firebase:firebase-dynamic-links-ktx")
    implementation ("com.google.firebase:firebase-analytics-ktx")
    
Step 3 - Add the Google services Gradle plugin (build.gradle.kts(Module: app))
    id("com.google.gms.google-services")
    
Step 4 - Add the dependency for the Google services Gradle plugin (build.gradle.kts(Project: your_app_name))
    id("com.google.gms.google-services") version "4.4.0" apply false
    
Step 5 - Add intent filter in AndroidManifest.xml in <activity >

            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>

                <data android:scheme="https"/>
                <data android:host="edugaon.page.link"/> // your dynamic link

                <data android:scheme="https"/>
                <data android:host="google.com"/>

            </intent-filter>
            
Step 6 - Add SHA 1 AND SHA 256 in your firebase project

Step 7 - Write code in MainActivity.kt
