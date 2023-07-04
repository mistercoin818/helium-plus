package com.example.madcampweek1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class GalleryActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMAGE_RES_ID = "extra_image_res_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_activity)
        val imageView = findViewById<ImageView>(R.id.image_full)
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_RES_ID)

        // 이미지 뷰에 선택한 이미지 설정
        Glide.with(this)
            .load(imageUrl)
            .into(this.findViewById(R.id.image_full))

        //전체화면 뷰에서 다시 이미지 클릭하면 돌아옴.
        val ImgFull : ImageView = findViewById<ImageView>(R.id.image_full)
        ImgFull.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}
