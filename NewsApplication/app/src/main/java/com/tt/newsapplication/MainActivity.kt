package com.tt.newsapplication

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tt.newsapplication.adapter.NewsListAdapter
import com.tt.newsapplication.api.APIClient
import com.tt.newsapplication.databinding.ActivityMainBinding
import com.tt.newsapplication.model.NewsListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
            getNewsList()
    }

    private  fun getNewsList() {
        MyFucntions.progressDialogShow(this@MainActivity);
        val call: Call<NewsListModel> = APIClient.client.getNews("tesla","2023-12-23","publishedAt","c179262bf94d46009ff84ff74babff43")
        call.enqueue(object : Callback<NewsListModel?> {
            override fun onResponse(
                call: Call<NewsListModel?>,
                response: Response<NewsListModel?>
            ) {
                if (response.isSuccessful) {
                    MyFucntions.progressDialogDismiss()
                    val body: NewsListModel? = response.body()
                    binding.addressList.visibility = View.VISIBLE
                    binding.noDataFound.visibility = View.GONE
                        newsListAdapter = NewsListAdapter(body!!.articles)
                        binding.addressList.adapter = newsListAdapter
                } else {
                    MyFucntions.progressDialogDismiss()
                    binding.noDataFound.visibility = View.VISIBLE
                    binding.addressList.visibility = View.GONE
                }
                MyFucntions.progressDialogDismiss()
            }

            override fun onFailure(call: Call<NewsListModel?>, t: Throwable) {
                MyFucntions.progressDialogDismiss()
                binding.noDataFound.visibility = View.VISIBLE
                binding.addressList.visibility = View.GONE
            }
        })
    }
    }

class MyFucntions {
    companion object {
        var dialog: ProgressDialog? = null

        open fun progressDialogShow(applicationContext: Context) {
            dialog = ProgressDialog(applicationContext)

            dialog!!.setTitle("Loading..")
            dialog!!.setCancelable(false)
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.show()


        }

        fun progressDialogDismiss() {
            if (dialog!!.isShowing()) dialog!!.dismiss()
        }
    }
}

