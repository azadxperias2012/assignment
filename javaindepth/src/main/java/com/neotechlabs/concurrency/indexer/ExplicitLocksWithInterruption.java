package com.neotechlabs.concurrency.indexer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitLocksWithInterruption {
	
	private Deque<WebLink> queue = new ArrayDeque<>();
	private List<Thread> downloaderThreadList = new ArrayList<>();
	private List<Thread> indexerThreadList = new ArrayList<>();

	private static class WebLink {
		private long id;
		private String title;
		private String url;
		private String host;
		private String htmlPage;
		
		private volatile boolean stop;
		
		public boolean isStop() {
			return stop;
		}

		public void setStop(boolean stop) {
			this.stop = stop;
		}

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
		private Lock lock;
		private Condition pageCondition;

		public Downloader(WebLink webLink, Lock lock, Condition pageCondition) {
			this.webLink = webLink;
			this.lock = lock;
			this.pageCondition = pageCondition;
		}

		@Override
		public void run() {
			lock.lock();
			try {
				//synchronized (webLink) {
					InputStream in = HttpConnect.getInputStream(webLink.getUrl());
					
					// Background thread for stopping download process
					Thread bgThread = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								while (!webLink.isStop()) {
									TimeUnit.SECONDS.sleep(1);
								}
								System.out.println("Time out. Closing stream for " + webLink.getId());
								in.close();
							} catch (InterruptedException e) {
								System.out.println("bgThread interrupted -- " + webLink.getId());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					bgThread.start();
					
					String htmlPage;
					
					try {
						htmlPage = HttpConnect.download(in);
						System.out.println(webLink.getId() + " download complete ...");
						webLink.setHtmlPage(htmlPage);
						
						bgThread.interrupt();

						//webLink.notifyAll();
						pageCondition.signal();
						
					} catch (IOException e) {
						System.out.println(webLink.getId() + " could not be downloaded. Terminating as stream closed!!!");
					}
				//}
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
		private Lock lock;
		private Condition pageCondition;
		
		public Indexer(WebLink webLink, Lock lock, Condition pageCondition) {
			this.webLink = webLink;
			this.lock = lock;
			this.pageCondition = pageCondition;
		}

		@Override
		public void run() {
			try {
				// Threads waiting here on intrinsic locks are uninterruptible
				//synchronized (webLink) {
					lock.lockInterruptibly();
					String htmlPage = webLink.getHtmlPage();
					while (htmlPage == null) {
						try {
							System.out.println(webLink.getId() + " not yet downloaded. Waiting ...");
							
							//webLink.wait(); // WAITING State & lock released
							pageCondition.await();
							
							System.out.println(webLink.getId() + " awakened!");
							htmlPage = webLink.getHtmlPage();
						} catch (InterruptedException e) {
							throw e;
						}
					}
					index(htmlPage);
				//}
			} catch (InterruptedException e) {
				System.out.println(webLink.getId() + " (indexer) interrupted!!");
				
				// Clean-up: Stopping downloader thread indirectly
				webLink.setStop(true);
				
				Thread.currentThread().interrupt();
			} finally {
				if (!Thread.currentThread().isInterrupted())
					lock.unlock();
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
			
			Lock lock = new ReentrantLock();
			Condition pageCondition = lock.newCondition();
			
			Thread downloaderThread = new Thread(new Downloader(weblink, lock, pageCondition));
			Thread indexerThread = new Thread(new Indexer(weblink, lock, pageCondition));
			
			downloaderThread.setName("Downloader Thread with ID: " + weblink.getId());
			indexerThread.setName("Indexer Thread with ID: " + weblink.getId());
			
			downloaderThreadList.add(downloaderThread);
			indexerThreadList.add(indexerThread);

			downloaderThread.start();
			indexerThread.start();
			//downloaderThread.start();
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Interrupting downloader thread that got BLOCKED
			System.out.println("\nTime Up!!\n");
			for (int i = 0; i < downloaderThreadList.size(); i++) {
				if (downloaderThreadList.get(i).isAlive()) {
					System.out.println(downloaderThreadList.get(i).getName() + " is still alive. Stopping it");
					indexerThreadList.get(i).interrupt();
				}
			}
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
		ExplicitLocksWithInterruption explicitLocksWithInterruption = new ExplicitLocksWithInterruption();
		explicitLocksWithInterruption.add(explicitLocksWithInterruption.createWebLink(2000, "Taming Tiger, Part 2",
				"https://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"https://www.javaworld.com"));
		explicitLocksWithInterruption.add(explicitLocksWithInterruption.createWebLink(2001,
				"How do I import a pre-existing Java project into Eclipse and get up and running?",
				"https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
				"https://www.stackoverflow.com"));
		explicitLocksWithInterruption.add(explicitLocksWithInterruption.createWebLink(2002, "Interface vs Abstract Class",
				"http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
		explicitLocksWithInterruption.add(explicitLocksWithInterruption.createWebLink(2004, "Virtual Hosting and Tomcat",
				"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));
		explicitLocksWithInterruption.go();
	}

}
