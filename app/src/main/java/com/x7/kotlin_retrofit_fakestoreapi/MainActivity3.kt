package com.x7.kotlin_retrofit_fakestoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.x7.kotlin_retrofit_fakestoreapi.databinding.ActivityMain3Binding
import com.x7.kotlin_retrofit_fakestoreapi.model.response.ProductModelItem
import com.x7.kotlin_retrofit_fakestoreapi.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

     binding.buttonrequestid.setOnClickListener {
         if (binding.edittextproductid.text.isNotEmpty()){
             //getproductwithid(binding.edittextproductid.text.toString().toInt())
              getproductwithlimit(binding.edittextproductid.text.toString().toInt())

         }else{
             binding.edittextproductid.setError("Error")
         }

     }


    }

    fun getproductwithlimit(limit:Int){
        val call:Call<ArrayList<ProductModelItem>> = RetrofitInstance.api.getproductsLimit(limit)
        call.enqueue(object :Callback<ArrayList<ProductModelItem>>{
            override fun onResponse(
                call: Call<ArrayList<ProductModelItem>>,
                response: Response<ArrayList<ProductModelItem>>
            ) {
                if (response.isSuccessful){
                    val arrayList:ArrayList<ProductModelItem> = response.body()!!
                    Log.d("XURSAND", "onResponse: ${arrayList.size}")
                }



            }

            override fun onFailure(call: Call<ArrayList<ProductModelItem>>, t: Throwable) {

            }

        })
    }


    fun getproductwithid(id:Int){
        binding.progressbarwithid.visibility=View.VISIBLE
        binding.linearlayoutproduct.visibility=View.GONE

        val call: Call<ProductModelItem> = RetrofitInstance.api.getproductwithpid(id)
        call.enqueue(object : Callback<ProductModelItem> {
            override fun onResponse(
                call: Call<ProductModelItem>,
                response: Response<ProductModelItem>
            ) {
                if (response.isSuccessful){
                   val productModelItem: ProductModelItem =response.body()!!
                    binding.apply {
                        progressbarwithid.visibility=View.GONE
                        linearlayoutproduct.visibility=View.VISIBLE
                        textviewopenpreviewname3.text=productModelItem.title
                        textviewopenpreviewdescription3.text=productModelItem.description
                        textviewopenpreviewprice3.text=productModelItem.price.toString()
                        raitingbar3.rating=productModelItem.rating.rate.toFloat()
                        Glide.with(this@MainActivity3).load(productModelItem.image).into(imagviewopenpreview3)
                    }

                }else{
                    binding.progressbarwithid.visibility=View.GONE
                    binding.linearlayoutproduct.visibility=View.GONE
                }
            }

            override fun onFailure(call: Call<ProductModelItem>, t: Throwable) {

            }

        })
    }
}