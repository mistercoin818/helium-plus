package com.example.madcampweek1.ui.weather

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.madcampweek1.R
import com.example.madcampweek1.databinding.FragmentWeatherBinding
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@Suppress("DEPRECATION")
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding

    // 런타임 권한 요청시 필요한 요청 코드입니다.
    private val PERMISSIONS_REQUEST_CODE = 100

    // 요청할 권한 리스트 입니다.
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    // 위치 서비스 요청시 필요한 런처입니다.
    lateinit var getGPSPermissionLauncher: ActivityResultLauncher<Intent>

    // 위도와 경도를 가져올 때 필요합니다.
    private lateinit var locationProvider: LocationProvider


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        return binding.root
    }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRefreshButton()
        checkAllPermissions()



        drawerLayout = view.findViewById(R.id.drawerLayout)
        // 사이드바 열기 버튼 설정
        val openDrawerButton: Button = view.findViewById(R.id.bookmark)
        openDrawerButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        navigationView = view.findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.seoul -> {
                    val address = getCurrentAddress(37.514575, 127.0495556) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    getAirQualityData(37.514575, 127.0495556)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.daejeon -> {
                    // 두 번째 아이템이 클릭되었을 때 실행될 동작
                    val address = getCurrentAddress(36.37366895657045, 127.3657259065586 ) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    getAirQualityData(36.37366895657045, 127.3657259065586 )
                    true
                }
                R.id.busan -> {
                    val address = getCurrentAddress(35.16001944, 129.1658083) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    getAirQualityData(35.16001944, 129.1658083)
                    true
                }
                R.id.daegu -> {
                    val address = getCurrentAddress(35.8715, 128.6017) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    getAirQualityData(35.8715, 128.6017)
                    true
                }
                R.id.ulsan -> {
                    val address = getCurrentAddress(35.546, 129.361245) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    getAirQualityData(35.546, 129.361245)
                    true
                }
                R.id.incheon -> {
                    val address = getCurrentAddress(37.46369169, 126.6502972) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    getAirQualityData(37.46369169, 126.6502972)
                    true
                }
                R.id.gwangju -> {
                    val address = getCurrentAddress(35.13301749, 126.9025572) //주소가 null 이 아닐 경우 UI 업데이트
                    address?.let {
                        binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                        binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    getAirQualityData(35.13301749, 126.9025572)
                    true
                }

                else -> false
            }
        }
    }

    private fun setRefreshButton() {
        binding.btnRefresh.setOnClickListener {
            updateUI()
        }
    }


    private fun updateUI() {
        locationProvider = LocationProvider(requireContext())

        //위도와 경도 정보를 가져옵니다.
        val latitude: Double = locationProvider.getLocationLatitude()
        val longitude: Double = locationProvider.getLocationLongitude()

        if (latitude != 0.0 || longitude != 0.0) {

            //1. 현재 위치를 가져오고 UI 업데이트
            //현재 위치를 가져오기
            val address = getCurrentAddress(latitude, longitude) //주소가 null 이 아닐 경우 UI 업데이트
            address?.let {
                binding.tvLocationTitle.text = "${it.thoroughfare}" // 예시: 역삼 1동
                binding.tvLocationSubtitle.text = "${it.countryName} ${it.adminArea}" // 예시 : 대한민국 서울특별시
            }

            //2. 현재 미세먼지 농도 가져오고 UI 업데이트
            getAirQualityData(latitude, longitude)

        } else {
            Toast.makeText(requireContext(), "위도, 경도 정보를 가져올 수 없었습니다. 새로고침을 눌러주세요.", Toast.LENGTH_LONG).show()
        }
    }

    // 레트로핏 클래스를 이용하여 미세먼지 오염 정보를 가져옵니다.

    private fun getAirQualityData(latitude: Double, longitude: Double) { // 레트로핏 객체를 이용하면 AirQualityService 인터페이스 구현체를 가져올 수 있습니다.
        val retrofitAPI = RetrofitConnection.getInstance().create(AirQualityService::class.java)

        retrofitAPI.getAirQualityData(latitude.toString(), longitude.toString(), "bdef5f3c-06f2-4a66-aff0-72e67eb5b581")
            .enqueue(object : Callback<AirQualityResponse> {
                override fun onResponse(
                    call: Call<AirQualityResponse>,
                    response: Response<AirQualityResponse>,
                ) { //정상적인 Response가 왔다면 UI 업데이트
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "최신 정보 업데이트 완료!", Toast.LENGTH_SHORT).show() //만약 response.body()가 null 이 아니라면 updateAirUI()
                        response.body()?.let { updateAirUI(it) }
                    } else {
                        Toast.makeText(requireContext(), "업데이트에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AirQualityResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }


    private fun updateAirUI(airQualityData: AirQualityResponse) {
        val pollutionData = airQualityData.data.current.pollution
        val weatherData = airQualityData.data.current.weather

        //수치 지정 (가운데 숫자)
        //binding.tvCount.text = pollutionData.aqius.toString()
        binding.tvCount.text = weatherData.tp.toString() + "°C"

        //측정된 날짜 지정
        //"2021-09-04T14:00:00.000Z" 형식을  "2021-09-04 23:00"로 수정
        val dateTime = ZonedDateTime.parse(pollutionData.ts).withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime()
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        binding.tvCheckTime.text = dateTime.format(dateFormatter).toString()

        when (pollutionData.aqius) {
            in 0..50 -> {
                binding.imgBg.setImageResource(R.drawable.bg_good)
            }

            in 51..150 -> {
                binding.imgBg.setImageResource(R.drawable.bg_soso)
            }

            in 151..200 -> {
                binding.imgBg.setImageResource(R.drawable.bg_bad)
            }

            else -> {
                binding.imgBg.setImageResource(R.drawable.bg_worst)
            }
        }
        when (weatherData.ic) {
            "01d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_01d)
            }

            "01n"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_01n)
            }
            "02d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_02d)
            }
            "02n"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_02n)
            }
            "03d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_03d)
            }
            "04d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_04d)
            }
            "09d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_09d)
            }
            "10d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_10d)
            }
            "10n"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_10n)
            }
            "11d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_11d)
            }
            "13d"-> {
                binding.weatherImg.setImageResource(R.drawable.icon_13d)
            }
            else-> {
                binding.weatherImg.setImageResource(R.drawable.icon_01d)
            }

        }
    }

    //위도와 경도를 기준으로 실제 주소를 가져온다.

    fun getCurrentAddress(latitude: Double, longitude: Double): Address? {
        val geocoder = Geocoder(requireContext(), Locale.getDefault()) // Address 객체는 주소와 관련된 여러 정보를 가지고 있습니다. android.location.Address 패키지 참고.
        val addresses: List<Address>?

        addresses = try { //Geocoder 객체를 이용하여 위도와 경도로부터 리스트를 가져옵니다.
            geocoder.getFromLocation(latitude, longitude, 7)
        } catch (ioException: IOException) {
            Toast.makeText(requireContext(), "지오코더 서비스 사용불가합니다.", Toast.LENGTH_LONG).show()
            return null
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(requireContext(), "잘못된 위도, 경도 입니다.", Toast.LENGTH_LONG).show()
            return null
        }

        //에러는 아니지만 주소가 발견되지 않은 경우
        if (addresses == null || addresses.size == 0) {
            Toast.makeText(requireContext(), "주소가 발견되지 않았습니다.", Toast.LENGTH_LONG).show()
            return null
        }

        val address: Address = addresses[0]

        return address
    }

    private fun checkAllPermissions() {
        if (!isLocationServicesAvailable()) { //1. 위치 서비스(GPS)가 켜져있는지 확인합니다.
            showDialogForLocationServiceSetting();
        } else {  //2. 런타임 앱 권한이 모두 허용되어있는지 확인합니다.
            isRunTimePermissionsGranted();
        }
    }

    fun isLocationServicesAvailable(): Boolean {
        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        ))
    }

    fun isRunTimePermissionsGranted() { // 위치 퍼미션을 가지고 있는지 체크합니다.
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) { // 권한이 한 개라도 없다면 퍼미션 요청을 합니다.
            ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        }
    }


    //@desc 런타임 권한을 요청하고 권한 요청에 따른 결과를 리턴한다.

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            var checkResult = true

            // 모든 퍼미션을 허용했는지 체크합니다.
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            if (checkResult) { //위치 값을 가져올 수 있음
                updateUI()
            } else { //퍼미션이 거부되었다면 앱을 종료합니다.
                Toast.makeText(requireContext(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show()
                requireActivity().finish()
            }
        }
    }

    //@desc LocationManager를 사용하기 위해서 권한을 요청한다.
    private fun showDialogForLocationServiceSetting() {

        //먼저 ActivityResultLauncher를 설정해줍니다. 이 런처를 이용하여 결과 값을 리턴해야하는 인텐트를 실행할 수 있습니다.
        getGPSPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> //결과 값을 받았을 때 로직을 작성해줍니다.
            if (result.resultCode == Activity.RESULT_OK) { //사용자가 GPS 를 활성화 시켰는지 확인합니다.
                if (isLocationServicesAvailable()) {
                    isRunTimePermissionsGranted()
                } else { //위치 서비스가 허용되지 않았다면 앱을 종료합니다.
                    Toast.makeText(requireContext(), "위치 서비스를 사용할 수 없습니다.", Toast.LENGTH_LONG).show()
                    requireActivity().finish()
                }
            }
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage("위치 서비스가 꺼져있습니다. 설정해야 앱을 사용할 수 있습니다.")
        builder.setCancelable(true)
        builder.setPositiveButton("설정", DialogInterface.OnClickListener { dialog, id ->
            val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            getGPSPermissionLauncher.launch(callGPSSettingIntent)
        })
        builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, id ->
            dialog.cancel()
            Toast.makeText(requireContext(), "기기에서 위치서비스(GPS) 설정 후 사용해주세요.", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        })
        builder.create().show()
    }


}