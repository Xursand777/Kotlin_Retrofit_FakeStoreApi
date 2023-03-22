package com.x7.kotlin_retrofit_fakestoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.x7.kotlin_retrofit_fakestoreapi.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent=intent
        val title=intent.getStringExtra("title")
        val description=intent.getStringExtra("description")
        val price=intent.getDoubleExtra("price",0.0)
        val image=intent.getStringExtra("image")
        val rate=intent.getDoubleExtra("rating",0.0)

        binding.apply {
            textviewopenpreviewname.text=title
            textviewopenpreviewdescription.text=description
            textviewopenpreviewprice.text=price.toString()
            raitingbar.rating= rate.toFloat()
            Glide.with(this@MainActivity2).load(image).into(imagviewopenpreview)
        }


    }
}