package com.example.imagespider;

import us.codecraft.webmagic.Spider;

import com.example.imagespider.pipeline.ImagePipeline;
import com.example.imagespider.processor.KonachanSearchPageProcessor;
import com.example.imagespider.processor.WallhavenSearchPageProcessor;

public class KonachanSpider {
	public static void main(String[] args) {
		String savePath = "e:/konachan/";
		
		int picDownloadThread = 5;
		
		Spider spider = Spider.create(new KonachanSearchPageProcessor());
		
		spider.thread(3);
		
		spider.addPipeline(new ImagePipeline(savePath,picDownloadThread));
		// 添加了几个搜索页面
		for (int i = 6; i <= 20; ++i) {
			spider.addUrl("http://konachan.net/post?tags=touhou&page=" + i);
		}
		// 启动
		spider.start();
	}
}
