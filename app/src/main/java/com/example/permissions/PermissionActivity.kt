package com.example.permissions

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        when {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED -> {
            // You can use the API that requires the permission.
            // performAction(...)
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected, and what
            // features are disabled if it's declined.
            // showInContextUI(...)
                requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS),
                    Datos.MY_PERMISSION_REQUEST_READ_CONTACTS)
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS),
                    Datos.MY_PERMISSION_REQUEST_READ_CONTACTS)
            }
        }


    }

    private fun requestPermission(context: Activity, permiso: String, justificacion: String, idCode: Int) {
        if (ContextCompat.checkSelfPermission(context, permiso) != PackageManager.PERMISSION_GRANTED) {
            //
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val txtContact = findViewById<TextView>(R.id.textResult)
        when (requestCode) {
            Datos.MY_PERMISSION_REQUEST_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission is granted. Continue the action or workflow
                // in your app.
                    Toast.makeText(this, "Thanks", Toast.LENGTH_SHORT).show()
                    txtContact.text = "PERMISO CONCEDIDO"
                    txtContact.setTextColor(Color.GREEN)

                } else {
                // Explain to the user that the feature is unavailable
                    txtContact.text = "PERMISO DENEGADO"
                    txtContact.setTextColor(Color.RED)
                }
                return
            }
            else -> {
            // Ignore all other requests.
            }
        }
    }

}