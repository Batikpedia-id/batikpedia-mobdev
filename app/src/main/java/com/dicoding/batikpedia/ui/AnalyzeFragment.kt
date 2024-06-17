package com.dicoding.batikpedia.ui


import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dicoding.batikpedia.R
import com.dicoding.batikpedia.databinding.FragmentAnalyzeBinding

class AnalyzeFragment : Fragment() {

    private var _binding: FragmentAnalyzeBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private var currentImageUri: Uri? = null

    private val pickGalleryImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            currentImageUri = result.data?.data
            showImage()
        }
    }

    private val takePictureUri = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && currentImageUri != null) {
            showImage()
        } else {
            showToast("Failed to capture image.")
        }
    }

    private val requestPermissionCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            createImageUri()?.let { uri ->
                currentImageUri = uri
                takePictureUri.launch(uri)
            } ?: showToast("Unable to create image URI.")
        } else {
            showToast("Camera Permission Denied! Try again")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyzeBinding.inflate(inflater, container, false)
        val view = binding.root

        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val scaleUpAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_up)
        imageView = binding.imageView
        button = binding.btnCamera
        val buttonGal = binding.btnGallery
        val buttonAnalyze = binding.btnAnalyze

        imageView.startAnimation(fadeInAnimation)
        button.startAnimation(scaleUpAnimation)
        buttonGal.startAnimation(scaleUpAnimation)
        buttonAnalyze.startAnimation(scaleUpAnimation)

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                createImageUri()?.let { uri ->
                    currentImageUri = uri
                    takePictureUri.launch(uri)
                } ?: showToast("Unable to create image URI.")
            } else {
                requestPermissionCamera.launch(android.Manifest.permission.CAMERA)
            }
        }

        buttonGal.setOnClickListener {
            startGallery()
        }

        buttonAnalyze.setOnClickListener {
            analyzeImage()
        }

        return view
    }

    private fun startGallery() {
        val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        pickGalleryImage.launch(galleryIntent)
    }

    private fun showImage() {
        currentImageUri?.let { uri ->
            imageView.setImageURI(uri)
        }
    }

    private fun analyzeImage() {
        currentImageUri?.let { uri ->
            moveToResult(uri)
        } ?: showToast("No image selected")
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToResult(imageUri: Uri) {
        Intent(context, ResultActivity::class.java).also { intent ->
            intent.putExtra("IMAGE_URI", imageUri.toString())
            startActivity(intent)
        }
    }

    private fun createImageUri(): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "camera_image_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
