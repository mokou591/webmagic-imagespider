package com.example.imagespider;
import us.codecraft.webmagic.Spider;

import com.example.imagespider.pipeline.ImagePipeline;
import com.example.imagespider.processor.HuabanSearchPageProcessor;
import com.example.imagespider.processor.KonachanSearchPageProcessor;

public class HuabanSpider {
	public static void main(String[] args) {
		String url = "http://huaban.com/search/?q=";
		String keyword = "touhou";
		String savePath = "e:/huaban/";
		int picDownloadThread = 5;
		Spider spider = Spider.create(new HuabanSearchPageProcessor());
		spider.thread(3);
		spider.addPipeline(new ImagePipeline(savePath, picDownloadThread));
		// Ìí¼ÓËÑË÷Ò³Ãæ
		spider.addUrl(url + keyword);
		// Æô¶¯
		spider.start();
	}
}
