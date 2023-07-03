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
import android.widget.SearchView
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
    private lateinit var searchView: SearchView
    private var contacts: MutableList<String> = mutableListOf()

    fun scrollToTop(){
        recyclerView.scrollToPosition(0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = root.findViewById(R.id.recyclerView)
        fabAddContact = root.findViewById(R.id.fabAddContact)
        btnRefresh = root.findViewById(R.id.btnRefresh)
        searchView = root.findViewById(R.id.searchView)

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
            contacts = loadContacts()
            adapter.setContacts(contacts)
        } else {
            // 권한이 허용되지 않았을 경우 권한 요청을 합니다.
            requestContactsPermission()
        }

        fabAddContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE
            startActivity(intent)
        }

        // onCreateView 메서드 내부에서 새로고침 버튼을 참조하고 클릭 리스너를 등록합니다.
        btnRefresh.setOnClickListener {
            refreshContacts()
        }

        // SearchView에 검색 이벤트 리스너를 등록합니다.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterContacts(it) }
                return true
            }
        })

        return root
    }

    private fun refreshContacts() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            contacts = loadContacts()
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
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
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
                contacts = loadContacts()
                adapter.setContacts(contacts)
            } else {
                showToast("Contacts permission denied.")
            }
        }
    }

    private fun loadContacts(): MutableList<String> {
        val contacts = mutableListOf<String>()
        val contentResolver = requireContext().contentResolver
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
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

    private fun filterContacts(query: String) {
        val filteredContacts = contacts.filter {
            it.contains(query, true)
        }
        adapter.setContacts(filteredContacts)
    }

    private fun getPhoneNumber(contact: String): String {
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
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_CALL_PHONE
            )
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_CALL_PHONE
            )
        }
    }

    private fun deleteContact(contact: String) {
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

    class ContactsAdapter(
        private val onContactClickListener: OnContactClickListener,
        private val onContactLongClickListener: OnContactLongClickListener
    ) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
        private val contacts = mutableListOf<String>()
        private var filteredContacts = mutableListOf<String>()

        fun setContacts(contacts: List<String>) {
            this.contacts.clear()
            this.contacts.addAll(contacts)
            this.filteredContacts.clear()
            this.filteredContacts.addAll(contacts)
            notifyDataSetChanged()
        }

        fun removeContact(contact: String) {
            val position = contacts.indexOf(contact)
            if (position != -1) {
                contacts.removeAt(position)
                filteredContacts.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ContactViewHolder(view)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val contact = filteredContacts[position]
            holder.bind(contact)
        }

        override fun getItemCount(): Int {
            return filteredContacts.size
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
