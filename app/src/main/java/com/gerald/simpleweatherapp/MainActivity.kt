package com.gerald.simpleweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*weatherTask().execute()*/
        val queue = Volley.newRequestQueue(this)
        val weatherLink = "https://api.weatherapi.com/v1/current.json?key=f269d6ac5ca5477896375924220208&q=Nakuru"
        val weatherRequest = JsonObjectRequest(Request.Method.GET,weatherLink,null,{
           weatherJsonObject ->
            val weatherArea = weatherJsonObject.getJSONObject("location")
            val city = weatherArea.get("name")
            val country = weatherArea.get("country")
            val sumCondition = weatherJsonObject.getJSONObject("current")
            val timeUpdate =sumCondition.get("last_updated")
            val weatherCondition = sumCondition.get("text")
            val tempNakuru = sumCondition.get("temp_c")
            val skyState = sumCondition.get("cloud")
            val windState = sumCondition.get("wind_dir")
            val atmosphereState = sumCondition.get("pressure")
            val humidityState = sumCondition.get("humidity")
            val maxTemp = sumCondition.get("temp_c")
            val minTemp = sumCondition.get("temp_c")


            findViewById<TextView>(R.id.temp_max).text = maxTemp.toString().trim()
            findViewById<TextView>(R.id.temp_min).text = minTemp.toString().trim()
            findViewById<TextView>(R.id.countyStudy).text = city.toString().trim()
           findViewById<TextView>(R.id.countryStudy).text = country.toString().trim()
            findViewById<TextView>(R.id.updated_at).text = timeUpdate.toString()
            findViewById<TextView>(R.id.status).text = weatherCondition.toString()
            findViewById<TextView>(R.id.temp).text = tempNakuru.toString()
            findViewById<TextView>(R.id.dataWind).text = skyState.toString()
            findViewById<TextView>(R.id.wind).text = windState.toString()
            findViewById<TextView>(R.id.pressure).text = atmosphereState.toString()
            findViewById<TextView>(R.id.humidity).text = humidityState.toString()

        },
            {
                    weatherError->
                Log.d("WEATHER","onCreate: Error while fetching weather data",weatherError)

            })
        queue.add(weatherRequest)
    }

   /* inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE

        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$City&units=metric&appid=$API")
                        .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObject = JSONObject(result)
                val main = jsonObject.getJSONObject("main")
                val sys = jsonObject.getJSONObject("sys")
                val wind = jsonObject.getJSONObject("wind")
                val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObject.getLong("dt")
                val updatedAtText =
                    "Updated at : " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "C"
                val minTemp = "Min Temp: " + main.getString("temp_min") + "C"
                val maxTemp = "Max Temp: " + main.getString("temp_max") + "C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("wind")
                val weatherDescription = weather.getString("description")
                val address = jsonObject.getString("name") + "," + sys.getString("country")

                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = minTemp
                findViewById<TextView>(R.id.temp_max).text = maxTemp
                findViewById<TextView>(R.id.sunrise).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                findViewById<TextView>(R.id.sunset).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE


            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

        }


    }*/
}