package com.example.sarmadabdulrahman.machinelearning
import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.widget.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class SearchCar : Fragment() {

    private val api:RestApi= RestApi()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View= inflater!!.inflate(R.layout.fragment_search_car, container, false)
        val Btn:Button=view.findViewById(R.id.search_button)
        val Ed:EditText=view.findViewById(R.id.editText)
        var Read:String?=null
        val inflater:LayoutInflater = view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val Alarm=inflater.inflate(R.layout.activity_poup_window,null)
        val popupWindow = PopupWindow(
                Alarm, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F

        }


        // If API level 23 or higher then execute the code
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for popup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.RIGHT
            popupWindow.exitTransition = slideOut


        }

        Btn.setOnClickListener {
            val cm:ConnectivityManager= view.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val INF:NetworkInfo=cm.activeNetworkInfo
            INF.isConnected()
            if (INF.isConnected()) {



Thread({
    try {
        val callResponse = api.getNews("", "1")
        val res = callResponse.execute()
        if (res.isSuccessful){
            val NEWS=res.body().data.children.map {
                val item = it.data
                ReadRowNewsResponse(item.author,item.title,item.num_comments,item.created, item.thumbnail, item.url)

            }
           // Log.i("The data is :-",NEWS[0].title)
            Read=NEWS[0].title

        }

    }
    catch (e:Exception){
        Log.i("The Erorr:-",e.toString())
    }
}).start()

            }
            else {

                Log.i("newtork", "s")
            }
            if (Read!=null) {
                popupWindow.showAtLocation(
                        view, // Location to display popup window
                        Gravity.CENTER, // Exact position of layout to display popup
                        0, // X offset
                        200 // Y offset
                )
            }

            val tv = Alarm.findViewById<TextView>(R.id.text_view)
            val buttonPopup = Alarm.findViewById<Button>(R.id.button_popup)



            tv.setText(Read)
            buttonPopup.setOnClickListener{
                // Dismiss the popup window
                popupWindow.dismiss()
            }
        }
        return view
    }

}// Required empty public constructor
