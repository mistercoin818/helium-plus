package com.example.madcampweek1.ui.dashboard
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcampweek1.GalleryActivity
import com.example.madcampweek1.GetImageActivity
import com.example.madcampweek1.R
import com.example.madcampweek1.databinding.FragmentDashboardBinding
data class BoardItem(val image: String, val title: String, val time: String)
class KashboardViewModel : ViewModel() {
    val itemList = ArrayList<BoardItem>()
    init {
        // 초기 데이터 추가         /*make itemList*/
        itemList.add(BoardItem("https://3.gall-img.com/tdgall/files/attach/images/82/332/321/157/d982a4327f51a4b15bb5758983dbe235.jpg","농담입니다","2023/07/01"))
        itemList.add(BoardItem("https://postfiles.pstatic.net/MjAyMTEyMTdfNjQg/MDAxNjM5NzIyOTA2MzYy.ED0XBboWb4DNEkDYpc_AUbg1xuSHrIvU35oi0rrsAy8g.YQ2DwI1uwAZvgP6AMMIZ53lCsP5gc0FeMEhXwAOfg8kg.JPEG.xvx404/KakaoTalk_20211129_191956435.jpg?type=w966","어찌하면좋곰","2023/07/01"))
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
    }
    fun addBoardItem(boardItem: BoardItem) {
        itemList.add(boardItem)
    }
}
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

class EmptyAdapter : RecyclerView.Adapter<EmptyAdapter.EmptyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return EmptyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {
        // 아무 작업도 수행하지 않습니다.
    }

    override fun getItemCount(): Int {
        return 0
    }

    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
class DashboardFragment : Fragment() {

    private val REQUEST_CODE_GALLERY = 1001
    private var selectedImageUri: Uri? = null
    private fun startImgActivity() {
        val intent = Intent(getActivity(), GetImageActivity::class.java)
        this.startActivity(intent)
    }
    private var _binding: FragmentDashboardBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var dashboardViewModel: KashboardViewModel
    private val binding get() = _binding!!
    private val itemList = ArrayList<BoardItem>()
    var boardAdapter = BoardAdapter(itemList)


    private lateinit var imageInputView: View
    private lateinit var titleEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var addToDashboardButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel = ViewModelProvider(this).get(KashboardViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(KashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        boardAdapter = BoardAdapter(dashboardViewModel.itemList)
        boardAdapter.notifyDataSetChanged()
        val RV_board :RecyclerView = binding.rvBoard
        RV_board.adapter = boardAdapter
        RV_board.layoutManager = GridLayoutManager(context, 3,  GridLayoutManager.VERTICAL, false)

        // 뷰 가릴려고 별짓을 다 하는 중.
//        dashboardView = View.inflate(context, R.layout.fragment_dashboard, null) // 새로운 View 생성

        imageInputView = inflater.inflate(R.layout.fragment_image_input, container, false)
        titleEditText = imageInputView.findViewById(R.id.etTitle)
        timeEditText = imageInputView.findViewById(R.id.etTime)
        addToDashboardButton = imageInputView.findViewById(R.id.btnAddToDashboard)

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri: Uri? = data?.data
                imageUri?.let {
                    val imagePath: String = imageUri.toString()
                    Toast.makeText(requireContext(), "파일경로 '${imagePath}' 이거양.", Toast.LENGTH_LONG).show()
                    Glide.with(this)
                        .load(imageUri) //이미지
                        .into(imageInputView.findViewById(R.id.ivSelectedImage)) //보여줄 위치
                    selectedImageUri = it
//                    showImageAndInputFields()
                }


                //뒤에 FragmentDashboard View를 안 보이게 함.
//                dashboardView.visibility = View.GONE
                // 기존 뷰의 이벤트 처리 중지
                binding.rvBoard.isEnabled = false
                binding.galleryBtn.isEnabled = false
                binding.galleryBtn.visibility = View.GONE
                binding.rvContainer.isEnabled = false

                // ImageInputView를 기존 View에 동적으로 추가
                val parentView = binding.root as ViewGroup
                parentView.addView(imageInputView)

                //어댑터 변경.
                val emptyAdapter = EmptyAdapter() // 빈 어댑터를 생성하거나 빈 어댑터 클래스를 구현해야 합니다.
                binding.rvBoard.adapter = emptyAdapter

                // ImageInputView에 대한 이벤트 처리
                addToDashboardButton.setOnClickListener {
                    val title = titleEditText.text.toString()
                    val time = timeEditText.text.toString()

                    if (title.isNotEmpty() && time.isNotEmpty()) {
                        val imagePath = selectedImageUri.toString()
                        val boardItem = BoardItem(imagePath, title, time)
                        dashboardViewModel.addBoardItem(boardItem)
                        boardAdapter.notifyDataSetChanged()

                        // 입력 필드 초기화
                        selectedImageUri = null
                        titleEditText.text.clear()
                        timeEditText.text.clear()

                        Toast.makeText(requireContext(), "사진이 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }

                    // ImageInputView 제거
                    parentView.removeView(imageInputView)


                    // 기존 뷰의 이벤트 처리 다시 활성화
                    binding.rvBoard.isEnabled = true
                    binding.galleryBtn.isEnabled = true
                    binding.galleryBtn.visibility = View.VISIBLE
                    binding.rvBoard.adapter = boardAdapter

                }

            }
        }

        fun openGallery() {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(galleryIntent)
        }

        binding.galleryBtn.setOnClickListener {
            openGallery()
        }

        return root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}