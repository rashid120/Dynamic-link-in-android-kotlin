package com.example.dynamiclink

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.androidParameters
import com.google.firebase.dynamiclinks.iosParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var shortLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.click)

        //Explicitly allowlist dynamic link URL (Android 13)
        val intentFilter = IntentFilter(Intent.ACTION_VIEW)
        intentFilter.addDataScheme("https")
        intentFilter.addDataAuthority("google.com", null)

        registerReceiver(dynamicLinkReceiver, intentFilter)

        Firebase.dynamicLinks.shortLinkAsync {

            link = Uri.parse("https://www.edugaon.com")
            domainUriPrefix = "https://edugaon.page.link"

            androidParameters {  }
            iosParameters("com.example.ios") {}

        }.addOnSuccessListener {

            shortLink = it.shortLink.toString()
        }.addOnFailureListener {

            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }

        btn.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_SUBJECT, "App link")
            intent.putExtra(Intent.EXTRA_TEXT, shortLink)

            startActivity(Intent.createChooser(intent, "Dynamic link app"))
        }

    }

    //Implement dynamicLinkReceiver to handle incoming links
    private val dynamicLinkReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {

            //handel dynamic link data here
        }
    }

}