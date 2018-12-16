package com.example.georgeissac.mvplearnarch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.georgeissac.mvplearnarch.remote.retrofit.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
     var compositeJob = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitFactory.getRetrofit()
        val remoteDataSource = RemoteDataSource(service)

        /*if cancelled before they are called,they are not called at all*/
        //compositeJob.cancel()

        /*GlobalScope.launch(compositeJob) {

        }*/

        //https://github.com/georgeisaac25/Coroutine_Examples.git

        CoroutineScope(Dispatchers.IO).launch(compositeJob){
            //compositeJob.cancel() //if cancelled  then catch called
            //compositeJob.cancel() //if cancelled  and not inside try then next line not called
            try {


                var country = remoteDataSource.getCountriesUsingMaybe()

                withContext(Dispatchers.Main) {
                    textView.text = country.toString()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    textView.text = e.toString()
                }
            }

            if (compositeJob.isCancelled){
                Log.e("cancel","cancel")
            }
        }

        /*CoroutineScope(Dispatchers.IO).launch(compositeJob){
            try {
               Thread.sleep(5000)

                withContext(Dispatchers.Main) {
                    textView.text = "Hi"
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    textView.text = e.toString()
                }
            }


        }*/

    }

    override fun onStop() {
        super.onStop()
        compositeJob.cancel()
    }
}
