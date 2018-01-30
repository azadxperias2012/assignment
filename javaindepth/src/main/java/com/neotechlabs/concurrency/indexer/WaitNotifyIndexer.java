package com.neotechlabs.concurrency.indexer;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Deque;

public class WaitNotifyIndexer {
	
	private Deque<WebLink> queue = new ArrayDeque<>();

	private static class WebLink {
		private long id;
		private String title;
		private String url;
		private String host;
		
		private String htmlPage;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getHtmlPage() {
			return htmlPage;
		}

		public void setHtmlPage(String htmlPage) {
			this.htmlPage = htmlPage;
		}
	}
	
	private static class Downloader implements Runnable {

		private WebLink webLink;		
		public Downloader(WebLink webLink) {
			this.webLink = webLink;
		}

		@Override
		public void run() {			
			try {
				synchronized (webLink) {
					String htmlPage = HttpConnect.download(webLink.getUrl());
					webLink.setHtmlPage(htmlPage);

					webLink.notifyAll();
				}
				// lock released
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Indexer implements Runnable {

		private WebLink webLink;		
		public Indexer(WebLink webLink) {
			this.webLink = webLink;
		}

		@Override
		public void run() {
			synchronized (webLink) {
				String htmlPage = webLink.getHtmlPage();
				while (htmlPage == null) {
					try {
						System.out.println(webLink.getId() + " not yet downloaded!");
						webLink.wait(); // WAITING State & lock released
						System.out.println(webLink.getId() + " awakened!");
						htmlPage = webLink.getHtmlPage();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				index(htmlPage);
			}
		}
		
		private void index(String text) {
			if (text != null)
				System.out.println("\nIndexed: " + webLink.getId());
		}
	}
	
	public void go() {
		while (queue.size() > 0) {
			WebLink weblink = queue.remove();
			Thread downloaderThread = new Thread(new Downloader(weblink));
			Thread indexerThread = new Thread(new Indexer(weblink));

			//downloaderThread.start();
			indexerThread.start();
			downloaderThread.start();
		}
	}

	public void add(WebLink link) {
		queue.add(link);
	}
	
	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);		
		return weblink;
	}

	public static void main(String[] args) {
		WaitNotifyIndexer waitNotifyIndexer = new WaitNotifyIndexer();
		waitNotifyIndexer.add(waitNotifyIndexer.createWebLink(2000, "Taming Tiger, Part 2",
				"https://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"https://www.javaworld.com"));
		waitNotifyIndexer.add(waitNotifyIndexer.createWebLink(2001,
				"How do I import a pre-existing Java project into Eclipse and get up and running?",
				"https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
				"https://www.stackoverflow.com"));
		waitNotifyIndexer.add(waitNotifyIndexer.createWebLink(2002, "Interface vs Abstract Class",
				"http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
		waitNotifyIndexer.add(waitNotifyIndexer.createWebLink(2004, "Virtual Hosting and Tomcat",
				"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));
		waitNotifyIndexer.go();
	}

}
