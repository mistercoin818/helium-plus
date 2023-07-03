package com.example.madcampweek1.ui.dashboard

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
//import com.example.tap.databinding.FragmentDashboardBinding
//import com.example.tap.databinding.ItmeRecyclerViewBinding
import com.bumptech.glide.Glide
import com.example.madcampweek1.GalleryActivity
import com.example.madcampweek1.GetImageActivity
import com.example.madcampweek1.R
import com.example.madcampweek1.databinding.FragmentDashboardBinding
//import com.example.tap.GalleryActivity
//import com.example.tap.GetImageActivity
//import com.example.tap.R
import com.google.android.material.internal.ContextUtils
import com.google.android.material.internal.ContextUtils.getActivity


data class BoardItem(val image: String, val title: String, val time: String)

class BoardAdapter(val itemList: ArrayList<BoardItem>):
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>(){
    override fun getItemCount(): Int {
       return itemList.count()
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.itme_recycler_view, parent, false)
        return BoardViewHolder(view)
    }


    class BoardViewHolder(view: View):RecyclerView.ViewHolder(view){
        private fun startGalleryActivity(imageUrl: String) {
            val intent = Intent(itemView.context, GalleryActivity::class.java)
            intent.putExtra(GalleryActivity.EXTRA_IMAGE_RES_ID, imageUrl)
            itemView.context.startActivity(intent)
        }
        fun bindItems(data: BoardItem){
            val imageUrl = data.image
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(itemView.findViewById(R.id.tv_img))
            itemView.findViewById<TextView>(R.id.tv_title).text = data.title
            itemView.findViewById<TextView>(R.id.tv_time).text = data.time

            itemView.setOnClickListener({
                Toast.makeText(itemView.context, "아이템 '${data.title}'를 클릭했습니다.", Toast.LENGTH_LONG).show()
                val intent = Intent(itemView.context, GalleryActivity::class.java)
                startGalleryActivity(imageUrl)

            })
        }
    }


    }


class DashboardFragment : Fragment() {

    private fun startImgActivity() {
        val intent = Intent(getActivity(), GetImageActivity::class.java)
//            intent.putExtra(GalleryActivity.EXTRA_IMAGE_RES_ID, imageUrl)
        this.startActivity(intent)
    }

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        /*make itemList*/
        val itemList = ArrayList<BoardItem>()
        /*구조체 처럼. res/drawable/jokebear33.jpg와 "https://"가 공존하는 방법이 있을 거 같은데 귀찮음.*/
        itemList.add(BoardItem("https://3.gall-img.com/tdgall/files/attach/images/82/332/321/157/d982a4327f51a4b15bb5758983dbe235.jpg","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNjQg/MDAxNjM5NzIyOTA2MzYy.ED0XBboWb4DNEkDYpc_AUbg1xuSHrIvU35oi0rrsAy8g.YQ2DwI1uwAZvgP6AMMIZ53lCsP5gc0FeMEhXwAOfg8kg.JPEG.xvx404/KakaoTalk_20211129_191956435.jpg?type=w966","어찌하면좋곰","2023/07/01"))
        itemList.add(BoardItem("https://3.gall-img.com/tdgall/files/attach/images/82/332/321/157/b7540af0387c5465cd5146fa0a5d1353.jpg","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNDIg/MDAxNjM5NzI0NTM0NTQy.Kg00KseRfKe1s7IYhOxaA_q8ZwPqrmyf5rg1loN9scEg.UGdQ2hlKt4RzmTXAlMuzwWM9q3F3L88euDZZfwvw8Wwg.JPEG.xvx404/KakaoTalk_20211104_114046906.jpg?type=w966","농담곰인물","2023/07/01"))
        itemList.add(BoardItem("https://pbs.twimg.com/media/E0r14B9VoAIH-Oy.jpg","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNjQg/MDAxNjM5NzIyOTA2MzYy.ED0XBboWb4DNEkDYpc_AUbg1xuSHrIvU35oi0rrsAy8g.YQ2DwI1uwAZvgP6AMMIZ53lCsP5gc0FeMEhXwAOfg8kg.JPEG.xvx404/KakaoTalk_20211129_191956435.jpg?type=w966","어찌하면좋곰","2023/07/01"))
        itemList.add(BoardItem("https://pbs.twimg.com/media/FACQ-ZwUUAE5qw-?format=jpg&name=large","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNDIg/MDAxNjM5NzI0NTM0NTQy.Kg00KseRfKe1s7IYhOxaA_q8ZwPqrmyf5rg1loN9scEg.UGdQ2hlKt4RzmTXAlMuzwWM9q3F3L88euDZZfwvw8Wwg.JPEG.xvx404/KakaoTalk_20211104_114046906.jpg?type=w966","농담곰인물","2023/07/01"))
        itemList.add(BoardItem("https://pbs.twimg.com/media/FACQ-IoVIAcS9Y3.jpg:small","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNjQg/MDAxNjM5NzIyOTA2MzYy.ED0XBboWb4DNEkDYpc_AUbg1xuSHrIvU35oi0rrsAy8g.YQ2DwI1uwAZvgP6AMMIZ53lCsP5gc0FeMEhXwAOfg8kg.JPEG.xvx404/KakaoTalk_20211129_191956435.jpg?type=w966","어찌하면좋곰","2023/07/01"))
        itemList.add(BoardItem("https://3.gall-img.com/tdgall/files/attach/images/82/392/830/064/b7f74c84dd15dad09dd4f9abd619721c.jpg","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNDIg/MDAxNjM5NzI0NTM0NTQy.Kg00KseRfKe1s7IYhOxaA_q8ZwPqrmyf5rg1loN9scEg.UGdQ2hlKt4RzmTXAlMuzwWM9q3F3L88euDZZfwvw8Wwg.JPEG.xvx404/KakaoTalk_20211104_114046906.jpg?type=w966","농담곰인물","2023/07/01"))

        val boardAdapter = BoardAdapter(itemList)
        boardAdapter.notifyDataSetChanged()

        val RV_board :RecyclerView = binding.rvBoard
        RV_board.adapter = boardAdapter
        RV_board.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)


        binding.galleryBtn.setOnClickListener{
            val intent = Intent(getActivity(), GetImageActivity::class.java)
            startImgActivity()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}