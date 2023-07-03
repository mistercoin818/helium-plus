package com.example.madcampweek1.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcampweek1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactsAdapter
    private lateinit var fabAddContact: FloatingActionButton
    private lateinit var btnRefresh: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = root.findViewById(R.id.recyclerView)
        fabAddContact = root.findViewById(R.id.fabAddContact)
        btnRefresh = root.findViewById(R.id.btnRefresh)

        adapter = ContactsAdapter(object : ContactsAdapter.OnContactClickListener {
            override fun onContactClick(contact: String) {
                val phoneNumber = getPhoneNumber(contact)
                if (phoneNumber.isNotEmpty()) {
                    makePhoneCall(phoneNumber)
                } else {
                    showToast("No phone number found for the contact.")
                }
            }
        }, object : ContactsAdapter.OnContactLongClickListener {
            override fun onContactLongClick(contact: String) {
                deleteContact(contact)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 연락처를 불러오기 위해 READ_CONTACTS 권한을 확인합니다.
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 허용되었을 경우 연락처를 불러오는 함수를 호출합니다.
            val contacts = loadContacts()
            adapter.setContacts(contacts)
        } else {
            // 권한이 허용되지 않았을 경우 권한 요청을 합니다.
            requestContactsPermission()
        }

        fabAddContact.setOnClickListener{
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE
            startActivity(intent)
        }

        // onCreateView 메서드 내부에서 새로고침 버튼을 참조하고 클릭 리스너를 등록합니다.
        btnRefresh.setOnClickListener {
            refreshContacts()
        }
        return root
    }

    private fun refreshContacts() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val contacts = loadContacts()
            adapter.setContacts(contacts)
            showToast("Contacts refreshed.")
        } else {
            showToast("Permission to read contacts is not granted.")
        }
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되었을 경우 연락처를 불러오는 함수를 호출합니다.
                val contacts = loadContacts()
                adapter.setContacts(contacts)
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
            ContactsContract.Contacts.DISPLAY_NAME + " ASC" // 가나다순으로 정렬하기 위해 추가
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

    private fun getPhoneNumber(contact: String): String {
        // 연락처에서 전화번호를 추출하여 반환하는 함수입니다.
        // 여기서는 간단히 전화번호를 괄호 '('와 ')' 사이의 문자열로 추출합니다.
        // 실제로는 더 복잡한 로직이 필요할 수 있습니다.
        val start = contact.indexOf('(')
        val end = contact.indexOf(')')
        return if (start != -1 && end != -1) {
            contact.substring(start + 1, end)
        } else {
            ""
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun requestCallPhonePermission(phoneNumber: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            )
        ) {
            // 권한이 거부되었을 때 권한 요청에 대한 설명을 보여줄 수 있습니다.
            // 이 예시에서는 직접적인 설명은 생략하였습니다.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_CALL_PHONE
            )
        } else {
            // 권한 요청 대화상자를 표시합니다.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_CALL_PHONE
            )
        }
    }

    private fun deleteContact(contact: String) {
        // 연락처를 삭제하는 로직을 구현합니다.
        // 여기서는 간단히 연락처를 리스트에서 제거하는 예시를 보여줍니다.
        adapter.removeContact(contact)
        showToast("Contact deleted: $contact")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
        private const val PERMISSIONS_REQUEST_CALL_PHONE = 200
    }

    // ContactsAdapter inner class
// ContactsAdapter inner class
    class ContactsAdapter(
        private val onContactClickListener: OnContactClickListener,
        private val onContactLongClickListener: OnContactLongClickListener
    ) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
        private val contacts = mutableListOf<String>()

        fun setContacts(contacts: List<String>) {
            this.contacts.clear()
            this.contacts.addAll(contacts)
            notifyDataSetChanged()
        }

        fun removeContact(contact: String) {
            val position = contacts.indexOf(contact)
            if (position != -1) {
                contacts.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ContactViewHolder(view)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val contact = contacts[position]
            holder.bind(contact)
        }

        override fun getItemCount(): Int {
            return contacts.size
        }

        interface OnContactClickListener {
            fun onContactClick(contact: String)
        }

        interface OnContactLongClickListener {
            fun onContactLongClick(contact: String)
        }

        inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textView: TextView = itemView.findViewById(android.R.id.text1)

            fun bind(contact: String) {
                textView.text = contact
                itemView.setOnClickListener {
                    onContactClickListener.onContactClick(contact)
                }
                itemView.setOnLongClickListener {
                    showDeleteContactDialog(contact)
                    true
                }
            }

            private fun showDeleteContactDialog(contact: String) {
                val alertDialog = AlertDialog.Builder(itemView.context)
                    .setTitle("Delete Contact")
                    .setMessage("Are you sure you want to delete this contact?")
                    .setPositiveButton("Yes") { _, _ ->
                        onContactLongClickListener.onContactLongClick(contact)
                    }
                    .setNegativeButton("No", null)
                    .create()
                alertDialog.show()
            }
        }
    }
}
