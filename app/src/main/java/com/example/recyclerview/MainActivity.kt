package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.api.ApiService
import com.example.recyclerview.api.ServiceBuilder
import com.example.recyclerview.data.PopularMovie
import com.example.recyclerview.data.PopularMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchMovies()
    }

    private fun fetchMovies() {
        val request = ServiceBuilder.buildService(ApiService::class.java)
        val call = request.getMovies(getString(R.string.api_key))
        call.enqueue(object: Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){
                    initRecyclerView(response.body()!!.results)
                }
            }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(movies: List<PopularMovie>) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerAdapter(movies)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
