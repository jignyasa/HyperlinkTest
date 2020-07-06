package com.ma.hyperlinktest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.JsonObject
import com.ma.hyperlinktest.R
import com.ma.hyperlinktest.adapter.ImageAdapter
import com.ma.hyperlinktest.remote.Retrofit
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter:ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        getImageData()
    }

    private fun init() {
        adapter= ImageAdapter(this)
        rvGrid.adapter=adapter
        rvGrid.layoutManager=StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL)
    }

    private fun getImageData() {
        pb.visibility= View.VISIBLE
        Retrofit.getRetrofit().getImages("latest")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<JsonObject> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(response: JsonObject) {
                    pb.visibility= View.GONE
                    if(response!=null)
                    {
                        val list=response.getAsJsonArray("images")
                        val alList=ArrayList<String>()
                        for(i in 0 until list.size())
                        {
                            val jsonObject=list[i].asJsonObject
                            alList.add(jsonObject.get("url").asString)
                        }
                        adapter.addData(alList)
                        Log.e("data",response.toString()+"\n"+alList.size)
                    }
                }

                override fun onError(e: Throwable) {
                    pb.visibility= View.GONE
                    Log.e("error",e.message)
                    Toast.makeText(this@MainActivity,"Something wrong with server",Toast.LENGTH_SHORT).show()
                }

            })
    }
}