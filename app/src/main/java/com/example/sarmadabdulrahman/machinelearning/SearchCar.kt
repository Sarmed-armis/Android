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
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
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
        val VIN=Ed.text
        val callResponse = api.getNews(VIN.toString())

        val res = callResponse.execute()


        if (res.isSuccessful){
            val dd=res.body().Results[0].Make
            Read=dd+"\n"+res.body().Results[0].Model+"\n"+res.body().Results[0].ModelYear

            /*{
                val item = it.Results
                ReadRowNewsResponse(item.Windows,
                        item.WheelSizeRear,
                        item.WheelSizeFront,
                        item.Wheels,
                        item.WheelBaseType,
                        item.WheelBaseShort,
                        item.WheelBaseLong,
                        item.VIN,
                        item.VehicleType,
                        item.ValveTrainDesign,
                        item.Turbo,
                        item.Trim2,
                        item.Trim,
                        item.TransmissionStyle,
                        item.TransmissionSpeeds,
                        item.TrailerType,
                        item.TrailerLength,
                        item.TrailerBodyType,
                        item.TractionControl,
                        item.TrackWidth,
                        item.TPMS,
                        item.TopSpeedMPH,
                        item.SuggestedVIN,
                        item.SteeringLocation,
                        item.Series2,
                        item.Series,
                        item.Seats,
                        item.SeatRows,
                        item.SeatBeltsAll,
                        item.RearVisibilityCamera,
                        item.Pretensioner,
                        item.PossibleValues,
                        item.PlantState,
                        item.PlantCountry,
                        item.PlantCompanyName,
                        item.PlantCity,
                        item.ParkAssist,
                        item.OtherTrailerInfo,
                        item.OtherRestraintSystemInfo,
                        item.OtherMotorcycleInfo,
                        item.OtherEngineInfo,
                        item.OtherBusInfo,
                        item.Note,
                        item.NCSAModel,
                        item.NCSAMake,
                        item.NCSABodyType,
                        item.NCICCode,
                        item.NCAPModel,
                        item.NCAPMake,
                        item.NCAPBodyType,
                        item.MotorcycleSuspensionType,
                        item.MotorcycleChassisType,
                        item.ModelYear,
                        item.Model,
                        item.ManufacturerType,
                        item.ManufacturerId,
                        item.Manufacturer,
                        item.Make,
                        item.LaneKeepSystem,
                        item.LaneDepartureWarning,
                        item.GVWR,
                        item.FuelTypeSecondary,
                        item.FuelTypePrimary,
                        item.FuelInjectionType,
                        item.ForwardCollisionWarning,
                        item.EVDriveUnit,
                        item.ESC,
                        item.ErrorCode,
                        item.EquipmentType,
                        item.EntertainmentSystem,
                        item.EngineModel,
                        item.EngineManufacturer,
                        item.EngineKW,
                        item.EngineHP_to,
                        item.EngineHP,
                        item.EngineCylinders,
                        item.EngineCycles,
                        item.EngineConfiguration,
                        item.ElectrificationLevel,
                        item.DriveType,
                        item.DriverAssist,
                        item.Doors,
                        item.DisplacementL,
                        item.DisplacementCI,
                        item.DisplacementCC,
                        item.DestinationMarket,
                        item.CustomMotorcycleType,
                        item.CurbWeightLB,
                        item.Country,
                        item.CoolingType,
                        item.ChargerPowerKW,
                        item.ChargerLevel,
                        item.CashForClunkers,
                        item.CAFEModel,
                        item.CAFEMake,
                        item.CAFEBodyType,
                        item.BusType,
                        item.BusLength,
                        item.BusFloorConfigType,
                        item.BrakeSystemType,
                        item.BrakeSystemDesc,
                        item.BodyClass,
                        item.BodyCabType,
                        item.BlindSpotMon,
                        item.BedType,
                        item.BedLengthIN,
                        item.BatteryV_to,
                        item.BatteryV,
                        item.BatteryType,
                        item.BatteryPacks,
                        item.BatteryModules,
                        item.BatteryKWh_to,
                        item.BatteryKWh,
                        item.BatteryInfo,
                        item.BatteryCells,
                        item.BatteryA_to,
                        item.BatteryA,
                        item.BasePrice,
                        item.Axles,
                        item.AxleConfiguration,
                        item.Artemis,
                        item.AirBagLocSide,
                        item.AirBagLocSeatCushion,
                        item.AirBagLocKnee,
                        item.AirBagLocFront,
                        item.AirBagLocCurtain,
                        item.AEB,
                        item.AdditionalErrorText,
                        item.AdaptiveHeadlights,
                        item.AdaptiveCruiseControl,
                        item.ABS) }
                        */

        }

    }
    catch (e:Exception){
        Log.i("The Erorr:-",e.message)
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

