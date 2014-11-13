package com.cd.jsoup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jsoup {

	List<String> names = new ArrayList<String>();
	List<String> srcs = new ArrayList<String>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {

		File dir = new File("F:/冷笑话");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		Jsoup jsoup = new Jsoup();
		String url3 = "http://www.laifu.org/wangwen/jiongrenqiushi.htm";//囧人糗事
		String url4 = "http://www.laifu.org/wangwen/lengxiaohua.htm";//冷笑话
		String url11 = "http://www.laifudao.com/wangwen/youmoxiaohua.htm";//幽默笑话
		String url12 = "http://www.laifudao.com/wangwen/wuliwangwen.htm";//无厘网文
		String url13 = "http://www.laifudao.com/wangwen/quweixinwen.htm";//趣味新闻
		
		
		String url1="http://www.laifu.org/tupian/dongwutupian.htm";//动物
		String url2 = "http://www.laifu.org/tupian/ertongtupian.htm";//儿童
		String url5 = "http://www.laifudao.com/tupian/gaoxiaomanhua.htm";//爆笑
		String url6 = "http://www.laifudao.com/tupian/baozoumanhua.htm";//暴走
		String url7 = "http://www.laifudao.com/tupian/qiwenguaishi.htm";//奇闻
		String url8 = "http://www.laifudao.com/tupian/gaoxiaojiaotong.htm";//搞笑交通
		String url9 = "http://www.laifudao.com/tupian/junshi_tiyu.htm";//军事体育
		String url10 = "http://www.laifudao.com/tupian/gaoxiaogif.htm";//搞笑gif
//		jsoup.pagePic(url6);
//		jsoup.pagePic(url2);
//		jsoup.page(url3);
//		jsoup.pageText(url13);
//		jsoup.pageCartoon(url6);
		jsoup.nextPage(url1);
	}
	
	private String nextPage(String url){
		try {
			Document doc = org.jsoup.Jsoup.connect(url).get();
			Element body = doc.body();
			Element next = body.getElementsByClass("next").first();
			Element a = next.getElementsByTag("a").first();
			String page = "http://www.laifudao.com" + a.attr("href");
			System.out.println("this is the href:  " + sdf.format(new Date()) + "  " + page);
			nextPage(page);
			return page;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private void pagePic(String url){
		try {
			Document doc = org.jsoup.Jsoup.connect(url).get();
			Element body = doc.body();
			Elements contents = body.getElementsByClass("pic-content");
			System.out.println(contents.toString());
			for(Element content : contents){
				Element link = content.getElementsByTag("img").first();
				String name = link.attr("alt");
				String src = link.attr("src");
				if(name == null || src == null)
					continue;
				names.add(name);
				srcs.add(src);
			}
			System.out.println(" now names' size is :" + names.size() + "   srcs's size is :" + srcs.size());
			for(int i = 0;i<srcs.size();i++){
				System.out.println("name:" + names.get(i) + "\t\t\t\t\t\t\t\tsrc:" + srcs.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void pageText(String url){
		try {
			Document doc = org.jsoup.Jsoup.connect(url).get();
			Element body = doc.body();
			Elements contents = body.getElementsByClass("content").first().getElementsByTag("article");
			for(Element content : contents){
				Element header = content.getElementsByClass("post-header").first().getElementsByTag("h1").first();
				Element article = content.getElementsByClass("article-content").first();
				System.out.println(header.text() + " " + article.text() + "\n-------------------------------------------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void pageCartoon(String url){
		try {
			Document doc = org.jsoup.Jsoup.connect(url).get();
			Element body = doc.body();
			Elements contents = body.getElementsByClass("content").first().getElementsByTag("article");
			for(Element content : contents){
				Element picture = content.getElementsByClass("pic-content").first();
				Element img = picture.getElementsByTag("img").first();
				String src = img.attr("src");
				String header = img.attr("alt");
				System.out.println(header + "   " + src  + "\n-------------------------------------------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
