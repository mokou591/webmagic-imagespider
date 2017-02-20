package com.example.imagespider.pipeline;

import java.io.File;
import java.util.Set;

import com.example.imagespider.content.Content;
import com.example.imagespider.processor.ImageProcessor;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 获得图片网址集合，将创建爬虫以多线程地下载图片
 *
 */
public class ImagePipeline implements Pipeline {

	private String savePath = "";

	public ImagePipeline(String savePath) {
		this.savePath = savePath;
		File rootPath = new File(savePath);
		rootPath.mkdirs();
	}

	@Override
	public void process(ResultItems resultItems, Task task) {
		// 获取需要下载的内容
		Set<String> picUrlSet = (Set<String>) resultItems.get(Content.PICTURE_URL.toString());
		// 创建爬虫来下载图片
		Spider spider = Spider.create(new ImageProcessor(savePath));
		// 同时下载图片的线程数
		spider.thread(10);
		// 添加纯图片网址
		for (String picUrl : picUrlSet) {
			spider.addUrl(picUrl);
		}
		spider.start();
	}
}
