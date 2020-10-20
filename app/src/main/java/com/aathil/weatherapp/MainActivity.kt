package com.aathil.weatherapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val CONNECTION_TIMEOUT_TIME = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBtn.setOnClickListener {
            //get city
            var city = cityInput.text.toString()
            var url =
                "https://api.weatherapi.com/v1/current.json?key=15ba982c91df49f5aed114238201910&q=$city"
            GetWeatherAsyncTask().execute(url)
        }
    }


    inner class GetWeatherAsyncTask: AsyncTask<String, String,String >(){

        override fun doInBackground(vararg urls: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                var url = URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = CONNECTION_TIMEOUT_TIME
                urlConnection.readTimeout = CONNECTION_TIMEOUT_TIME
                var inStream = streamToString(urlConnection.inputStream)
                publishProgress(inStream)
            }catch (ex: Exception){
                Log.d("Error", ex.toString())
            }
            finally {
                urlConnection?.disconnect()

            }

            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {


            super.onProgressUpdate(*values)
            try {



                var json = JSONObject(values[0])
                //get the location details
                val location = json.getJSONObject("location")
                val city = location.get("name")
                val country = location.get("country")

                //get current weather
                val current = json.getJSONObject("current")
                val celcius = current.get("temp_c")

                //get current time
                val time = location.get("localtime")
//                if(checkCity.isChecked){
//                    results.text = "City: $city" + results.text
//                }
//                if(checkCountry.isChecked){
//                    results.text = "\nCountry: $country" + results.text
//                }
//                if(checkTime.isChecked){
//                    results.text = "\nTime: $time" + results.text
//                }
//                if(checkWeather.isChecked){
//                    results.text = "\nWeather: $celcius℃" + results.text

//                }

                if(checkWeather.isChecked && checkTime.isChecked){
                    results.text =
                            "City: $city" +
                                    "\nCountry: $country" +
                                    "\nTime: $time" +
                                    "\nWeather: $celcius\u2103"
                }
                else if(checkWeather.isChecked && !checkTime.isChecked){
                    results.text =
                            "City: $city" +
                                    "\nCountry: $country" +
//                                    "\nTime: $time" +
                                    "\nWeather: $celcius\u2103"
                }
                else if(!checkWeather.isChecked && checkTime.isChecked){
                    results.text =
                            "City: $city" +
                                    "\nCountry: $country" +
                                    "\nTime: $time"
//                                    "\nWeather: $celcius\u2103"
                }
                else{
                    results.text =
                            "City: $city" +
                                    "\nCountry: $country"
//                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
                }
//                if(checkWeather.isChecked && checkCity.isChecked && checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
//                                    "\nCountry: $country" +
//                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(checkWeather.isChecked && checkCity.isChecked && checkCountry.isChecked && !checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
//                                    "\nCountry: $country" +
//                                    "\nWeather: $celcius\u2103"
//                }
//                else if(checkWeather.isChecked && checkCity.isChecked && !checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
//                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(checkWeather.isChecked && !checkCity.isChecked && checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
//                            "\nCountry: $country" +
//                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(!checkWeather.isChecked && checkCity.isChecked && checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
//                                    "\nCountry: $country" +
//                                    "\nTime: $time\n"
//                }
//                else if(checkWeather.isChecked && checkCity.isChecked && !checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
//                                    "\nCountry: $country" +
//                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(checkWeather.isChecked && checkCity.isChecked && !checkCountry.isChecked && !checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
////                                    "\nCountry: $country" +
////                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(checkWeather.isChecked && !checkCity.isChecked && !checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
////                            "City: $city" +
////                                    "\nCountry: $country" +
//                                    "\nTime: $time" +
//                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(!checkWeather.isChecked && checkCity.isChecked && !checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
//                            "City: $city" +
////                                    "\nCountry: $country" +
//                                    "\nTime: $time"
////                                    "\nWeather: $celcius\u2103"
//
//                }
//                else if(!checkWeather.isChecked && !checkCity.isChecked && checkCountry.isChecked && checkTime.isChecked){
//                    results.text =
////                            "City: $city" +
//                                    "\nCountry: $country" +
//                                    "\nTime: $time" +
////                                    "\nWeather: $celcius\u2103"
//
//                }

            }catch (ex: java.lang.Exception){}
        }


    }

    fun streamToString(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
    }




}