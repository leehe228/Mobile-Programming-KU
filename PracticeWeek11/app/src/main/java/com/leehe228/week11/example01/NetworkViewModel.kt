package com.leehe228.week11.example01

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class NewsViewModel : ViewModel() {

    private val _newsList = mutableStateListOf<NewsData>()
    val newsList = _newsList

    private val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading

    fun fetchNews() {
        _isLoading.value = true // show indicator
        viewModelScope.launch {
            try {
                // val fetchedNews = getNews()
                val fetchedNews = getJTBCNews()
                _newsList.clear()
                _newsList.addAll(fetchedNews)

                // joke test
                val joke = getJokes()
                Log.i("joke", joke)

            } catch (e: Exception) {
                Log.e("error", "fetch 관련 오류 발생", e)
            } finally {
                _isLoading.value = false // hide indicator
            }
        }
    }

    private suspend fun getJTBCNews(): List<NewsData> = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect("https://fs.jtbc.co.kr/RSS/culture.xml")
            .parser(Parser.xmlParser()).get()
        val headlines = doc.select("item")
        headlines.mapNotNull { news ->
            NewsData(
                title = news.selectFirst("title")?.text().toString(),
                newsUrl = news.selectFirst("link")?.text().toString(),
            )
        }
    }

    private suspend fun getJokes(): String = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect("https://api.chucknorris.io/jokes/random?categories=food")
            .ignoreContentType(true).get()
        val json = JSONObject(doc.text())
        val joke = json.getString("value")
        joke
    }

    private suspend fun getNews(): List<NewsData> = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect("https://news.daum.net")
            .userAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Mobile Safari/537.36")
            .referrer("https://www.google.com").timeout(10000).get()

        val headlines = doc.select("ul.list_newsheadline2>li")
        headlines.mapNotNull { li ->
            val a = li.selectFirst("a") ?: return@mapNotNull null
            val title = a.select("strong.tit_txt").text()
            val link = a.absUrl("href")
            NewsData(title.toString(), link)
        }
    }
}
