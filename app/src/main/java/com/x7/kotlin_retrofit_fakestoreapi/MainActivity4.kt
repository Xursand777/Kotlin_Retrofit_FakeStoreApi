package com.x7.kotlin_retrofit_fakestoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.x7.kotlin_retrofit_fakestoreapi.databinding.ActivityMain4Binding
import com.x7.kotlin_retrofit_fakestoreapi.model.request.ProductReq
import com.x7.kotlin_retrofit_fakestoreapi.model.response.ProductRes
import com.x7.kotlin_retrofit_fakestoreapi.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonaddproduct.setOnClickListener {
             addproduct(title = edittextproducttitle.text.toString(),
                 price = edittextproductprice.text.toString().toDouble(),
                 description = edittextproductdescription.text.toString(),
                 image = "",
                 category = edittextproductcategory.text.toString()
             )
            }
        }


    }

    fun addproduct(
        title :String,
        price :Double,
        description :String,
        image :String,
        category :String
    ){
        val productReq=ProductReq(
            title = title,
            price = price,
            description = description,
            image = image,
            category = category
        )
        val call:Call<ProductRes> = RetrofitInstance.api.addproduct(productReq)
        call.enqueue(object :Callback<ProductRes>{
            override fun onResponse(call: Call<ProductRes>, response: Response<ProductRes>) {
                if (response.isSuccessful){
                    val productRes=response.body()!!
                    Log.d("XURSAND", "onResponse: ${productRes.id}")
                }
            }

            override fun onFailure(call: Call<ProductRes>, t: Throwable) {

            }

        })
    }
}