package com.dicoding.batikpedia.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.batikpedia.R
import com.dicoding.batikpedia.api.response.ApiResponse
import com.dicoding.batikpedia.api.retrofit.ApiConfig
import com.dicoding.batikpedia.api.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ResultActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var responseTextView: TextView
    private lateinit var responseTextView2: TextView
    private lateinit var responseTextToko: TextView
    private lateinit var storeRecyclerView: RecyclerView
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        imageView = findViewById(R.id.imageView2)
        responseTextView = findViewById(R.id.textView)
        responseTextView2 = findViewById(R.id.textView2)
        storeRecyclerView = findViewById(R.id.storeRecyclerView)
        textView1 = findViewById(R.id.deskripsi)
        textView2 = findViewById(R.id.umkm)
        progressBar = findViewById(R.id.progressBar)

        textView1.visibility = View.GONE
        textView2.visibility = View.GONE
        responseTextView.visibility = View.GONE
        responseTextView2.visibility = View.GONE
        storeRecyclerView.visibility = View.GONE

        storeAdapter = StoreAdapter(emptyList())
        storeRecyclerView.layoutManager = LinearLayoutManager(this)
        storeRecyclerView.adapter = storeAdapter

        val imageUriString = intent.getStringExtra("IMAGE_URI")
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            imageView.setImageURI(imageUri)
            uploadImage(imageUri)
        } else {
            Toast.makeText(this, "No image found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImage(imageUri: Uri) {
        progressBar.visibility = View.VISIBLE  // Show the progress bar

        val inputStream = contentResolver.openInputStream(imageUri)
        val file = createTempFile("upload", ".jpg", cacheDir)
        val outputStream = file.outputStream()

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val apiService = ApiConfig.instance.create(ApiService::class.java)
        apiService.uploadImage(body).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val responseData = response.body()?.data
                    responseTextView.text = responseData?.batik?.name ?: "No name available"
                    responseTextView2.text = responseData?.batik?.description ?: "No description available"

                    val stores = responseData?.stores ?: emptyList()
                    storeAdapter.updateStores(stores)

                    textView1.visibility = View.VISIBLE
                    textView2.visibility = View.VISIBLE
                    responseTextView.visibility = View.VISIBLE
                    responseTextView2.visibility = View.VISIBLE
                    storeRecyclerView.visibility = View.VISIBLE

                    findViewById<TextView>(R.id.deskripsi).visibility = View.VISIBLE
                    findViewById<TextView>(R.id.umkm).visibility = View.VISIBLE
                } else {
                    val responseData = response.body()?.message ?: "Batik Pattern not found"
                    responseTextView.text = responseData
                    responseTextView2.text = getString(R.string.NotFound)

                    textView1.visibility = View.VISIBLE
                    textView2.visibility = View.VISIBLE
                    responseTextView.visibility = View.VISIBLE
                    responseTextView2.visibility = View.VISIBLE

                    findViewById<TextView>(R.id.deskripsi).visibility = View.GONE
                    findViewById<TextView>(R.id.umkm).visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                responseTextView.text = "Failure: ${t.message}"
                responseTextView2.text = "Failure: ${t.message}"
                textView1.text = "Failure: ${t.message}"

                textView1.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                responseTextView.visibility = View.VISIBLE
                responseTextView2.visibility = View.VISIBLE
            }
        })
    }
}