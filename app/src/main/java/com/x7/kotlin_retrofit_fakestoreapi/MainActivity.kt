package com.x7.kotlin_retrofit_fakestoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.x7.kotlin_retrofit_fakestoreapi.databinding.ActivityMainBinding
import com.x7.kotlin_retrofit_fakestoreapi.model.response.ProductModelItem
import com.x7.kotlin_retrofit_fakestoreapi.retrofit.RetrofitInstance.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter

    var arrayList=ArrayList<ProductModelItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       getAllProducts()
        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing=false


        }


    }


    fun getAllProducts(){
        val call:Call<ArrayList<ProductModelItem>> = api.getAllProduct()
        binding.progressbar1.visibility=View.VISIBLE
        call.enqueue(object :Callback<ArrayList<ProductModelItem>>{
            override fun onResponse(
                call: Call<ArrayList<ProductModelItem>>,
                response: Response<ArrayList<ProductModelItem>>
            ) {
                if (response.isSuccessful){
                    binding.progressbar1.visibility=View.INVISIBLE
                    arrayList=response.body()!!
                    binding.recyclerview.layoutManager=GridLayoutManager(this@MainActivity,3)
                    productAdapter= ProductAdapter(this@MainActivity,arrayList)
                    binding.recyclerview.adapter=productAdapter

                    binding.swiperefresh.isRefreshing=false
                }else{
                    binding.progressbar1.visibility=View.INVISIBLE
                    binding.swiperefresh.isRefreshing=false
                }
            }

            override fun onFailure(call: Call<ArrayList<ProductModelItem>>, t: Throwable) {
                Log.d("XURSAND", "onFailure: ${t.message}")
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            // Check if user triggered a refresh:
            R.id.menu_refresh -> {

                // Signal SwipeRefreshLayout to start the progress indicator
                binding.swiperefresh.isRefreshing = true

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.

                return true
            }
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item)
    }
}