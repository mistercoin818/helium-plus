package com.example.madcampweek1.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.madcampweek1.R

class HomeFragment : Fragment() {

    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        listView = root.findViewById(R.id.listView)

        // 연락처를 불러오기 위해 READ_CONTACTS 권한을 확인합니다.
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 허용되었을 경우 연락처를 불러오는 함수를 호출합니다.
            val contacts = loadContacts()
            displayContacts(contacts)
        } else {
            // 권한이 허용되지 않았을 경우 권한 요청을 합니다.
            requestContactsPermission()
        }

        return root
    }

    private fun requestContactsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_CONTACTS
            )
        ) {
            // 권한이 거부되었을 때 권한 요청에 대한 설명을 보여줄 수 있습니다.
            // 이 예시에서는 직접적인 설명은 생략하였습니다.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            // 권한 요청 대화상자를 표시합니다.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        @Suppress("deprecated")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되었을 경우 연락처를 불러오는 함수를 호출합니다.
                val contacts = loadContacts()
                displayContacts(contacts)
            } else {
                // 권한이 거부되었을 경우 처리할 내용을 추가합니다.
                // 이 예시에서는 거부되었다는 메시지를 출력합니다.
                // 필요에 따라 다른 동작을 수행할 수 있습니다.
                // 예를 들어, 사용자에게 권한이 필요한 이유를 설명하는 다이얼로그를 표시하거나
                // 다른 대체 기능을 제공하는 등의 처리를 할 수 있습니다.
                showToast("Contacts permission denied.")
            }
        }
    }

    private fun loadContacts(): List<String> {
        val contacts = mutableListOf<String>()
        val contentResolver = requireContext().contentResolver
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val nameColumnIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val numberColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                val name = it.getString(nameColumnIndex)
                val number = it.getString(numberColumnIndex)

                val contact = "$name ($number)"
                contacts.add(contact)
            }
        }

        return contacts
    }


    private fun displayContacts(contacts: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, contacts)
        listView.adapter = adapter
    }

    private fun showToast(message: String) {
        // Toast 메시지를 출력하는 함수입니다. 필요에 따라 알맞게 구현하시면 됩니다.
    }

    companion object {
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }
}

