package com.example.generadorqrkotlin

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.generadorqrkotlin.databinding.ActivityMainBinding
import net.glxn.qrgen.android.QRCode
import java.io.File
import java.io.FileOutputStream
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var texto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //No se tiene permisos
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }

        binding.btnGenerarQR.setOnClickListener {
            texto = binding.etTexto.text.toString().trim()
            binding.etTexto.text = null
            generarQR()
        }

        binding.btnGuardar.setOnClickListener { guardarQR() }
    }

    fun generarQR() {
        if (TextUtils.isEmpty(texto)) {
            Toast.makeText(this, "DEBES PONER UN TEXTO PARA CONTINUAR", Toast.LENGTH_SHORT).show()
        } else {
            val bitmap = QRCode.from(texto).withSize(100,100).bitmap()
            binding.ivQRCode.setImageBitmap(bitmap)
        }
    }

    fun guardarQR() {
        val btm = (binding.ivQRCode.drawable as BitmapDrawable).bitmap
        val fecha = Date()
        try {
            val mPath = getExternalStorageDirectory().toString() + "/" + fecha + ".png"
            val u: View = binding.ivQRCode
            val bitmap: Bitmap = Bitmap.createBitmap(btm)
            val imageFile = File(mPath)
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: Throwable) {
            Toast.makeText(this, "ERROR al intentar generar imagen .png", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }
}