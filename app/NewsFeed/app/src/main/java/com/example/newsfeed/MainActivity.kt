package com.example.newsfeed


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest



class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recView=findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)

        recView.layoutManager=LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recView.adapter = mAdapter
    }
    private fun fetchData()
    {
        val url= "https://newsdata.io/api/1/news?apikey=pub_5364ca51a8e68c36acb9d69d7a538fad19ff&country=in"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {

                val newsArray= ArrayList<News>()
                val newsJsonArray= it.getJSONArray("results")
                for (i in 0 until newsJsonArray.length())
                {
                    val newsJsonObject= newsJsonArray.getJSONObject(i)
                    val news=News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("creator"),
                        newsJsonObject.getString("link"),
                        newsJsonObject.getString("image_url")
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {


            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClicked(item: News) {

        val builder= CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url));

    }
}