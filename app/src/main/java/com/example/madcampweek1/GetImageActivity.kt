package com.example.madcampweek1

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.madcampweek1.databinding.EditImageBinding
import com.example.madcampweek1.ui.dashboard.DashboardFragment
//import com.example.tap.databinding.EditImageBinding
//import com.example.tap.databinding.FragmentDashboardBinding
//import com.example.tap.ui.dashboard.DashboardFragment

class GetImageActivity :AppCompatActivity() {
    lateinit var binding: EditImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = EditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //갤러리 호출
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activityResult.launch(intent)
        //버튼 이벤트
        val dashboardfragment = DashboardFragment()
        binding.galleryBtn.setOnClickListener {
            //버벅 버튼을 누르면, 해당 이미지 정보를 원래 fragment_dashboard View에 전달해야함
            //갤러리 호출
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            activityResult.launch(intent)
            //원래 프래그먼트로 돌아가기
//            val bundle = Bundle()
//            bundle.putString("key", "Hello dashboard")
//            dashboardfragment.arguments = bundle
//
//            val transaction = supportFragmentManager.beginTransaction()
////            transaction.add(R.id.tv_time, dashboardfragment)
//            transaction.commit()
//            supportFragmentManager.beginTransaction().add(R.id.rv_board,  dashboardfragment).commit()
        }
    }//onCreate

    //결과 가져오기
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == AppCompatActivity.RESULT_OK && it.data != null){
            //값 담기
            val uri  = it.data!!.data

            //화면에 보여주기
            Glide.with(this)
                .load(uri) //이미지
                .into(binding.imageView) //보여줄 위치
        }
    }
}