package com.example.rxpictory.ui.profileEdit

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.rxpictory.R
import com.example.rxpictory.connect.Connecter
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.ui.mypage.MyPageViewModel
import com.example.rxpictory.util.DataBindingActivity
import com.example.rxpictory.util.getToken
import kotlinx.android.synthetic.main.activity_profile_edit.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditActivity : DataBindingActivity<com.example.rxpictory.databinding.ActivityProfileEditBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_profile_edit

    private val viewModel: MyPageViewModel by lazy {
        ViewModelProviders.of(this).get(MyPageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        profileedit_name_et.setText(intent.getStringExtra("userName"))
        profileedit_birth_et.setText(intent.getStringExtra("birth"))
        viewModel.addImageEvent.observe(this, Observer {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        })

        profileedt_edit_btn.setOnClickListener {
            updateProfile()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1 && data != null) {
            val path = data.data
            if (resultCode === Activity.RESULT_OK) {
                try {
                    viewModel.profileIMG.value = getRealPathFromURI(path)
                    val inGallery = contentResolver.openInputStream(path)
                    val img = BitmapFactory.decodeStream(inGallery)
                    inGallery!!.close()
                    profileImage.setImageBitmap(img)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getRealPathFromURI(contentUri: Uri): String {
        if (contentUri.getPath().startsWith("/storage")) {
            return contentUri.getPath();
        }
        val id = DocumentsContract.getDocumentId(contentUri).split(":")[1];
        val columns = arrayOf(MediaStore.Files.FileColumns.DATA)
        val selection = MediaStore.Files.FileColumns._ID + " = " + id;
        val cursor =
            getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            val columnIndex = cursor.getColumnIndex(columns[0])
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor.close()
        }
        return "hello"
    }

    fun updateProfile() {
        Connecter.api.profileEdit(
            getToken(applicationContext),
            getData(profileedit_name_et.text.toString()),
            getData(profileedit_birth_et.text.toString())
        )
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    when (response.code()) {
                        200 -> {
                            Toast.makeText(this@ProfileEditActivity, "수정 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        404 -> {
                            Log.d("프로필 수정", profileedit_name_et.text.toString())
                        }
                        500 ->  {
                            Toast.makeText(this@ProfileEditActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                            Log.d("프로필 수정", response.code().toString())
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    Log.d("회원가입", "실패")
                    Toast.makeText(this@ProfileEditActivity, "수정 실패", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
    }

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }
}