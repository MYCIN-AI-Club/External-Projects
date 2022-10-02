package com.example.chatapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapplication.databinding.ActivityPreviewImgBinding

class PreviewImg : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewImgBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityPreviewImgBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = getIntent()
        val img= intent.getStringExtra("Image")
        val image = Uri.parse(img)
        binding.ImagePreview.setImageURI(image)
    }
}