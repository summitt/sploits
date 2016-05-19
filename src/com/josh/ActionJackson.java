package com.josh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import burp.IBurpExtenderCallbacks;
import burp.IContextMenuInvocation;
import burp.IHttpRequestResponse;

public class ActionJackson implements ActionListener{
	private IContextMenuInvocation inv;
	private IBurpExtenderCallbacks cb;
	private boolean isExclude=false;
	
	public ActionJackson(IContextMenuInvocation inv, IBurpExtenderCallbacks callbacks, boolean isExclude ){
		this.inv = inv;
		this.cb = callbacks;
		this.isExclude=isExclude;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(IHttpRequestResponse o : inv.getSelectedMessages()){
			String host = o.getUrl().getHost();
			//if(!cb.isInScope(o.getUrl())){
				URL https;
				URL http;
				try {
					https = new URL("https://"+host);
					http = new URL("http://"+host);
					
					if(!isExclude){
						cb.includeInScope(http);
						cb.includeInScope(https);
						cb.printOutput("Including " + host + " in Scope");
					}else{
						cb.excludeFromScope(http);
						cb.excludeFromScope(https);
						cb.printOutput("Excluding " + host + " in Scope");
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			//}
		}
		
		
	}
	

}
