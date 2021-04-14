package com.tech.permissiontest

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step 1-get the permission status.

        var status=ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)

        //step 2-check the status(whether we can access the data or not?)
        if(status==PackageManager.PERMISSION_GRANTED)
        {
            readData()
        }else
        {
            //step 3
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),15)
        }
    }

    //step 4-override the method onRequestPermissionsResult.


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            readData()
        }else
        {
            Toast.makeText(this,"user is not allowed to access the data",Toast.LENGTH_SHORT).show()
        }
    }

    fun readData()
    {
       // Toast.makeText(this,"Welcome here",Toast.LENGTH_SHORT).show()
       var path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"

        var f = File(path)
        if (!f.exists()) {
            path = "/storage/SdCard/0/WhatsApp/Media/WhatsApp Images/"
        }
        var files = f.list()

        var myadapter=ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,files)
        list_view.adapter=myadapter

    }
}