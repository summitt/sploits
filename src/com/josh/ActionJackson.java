package com.josh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import burp.IBurpExtenderCallbacks;
import burp.IContextMenuInvocation;
import burp.IHttpRequestResponse;

public class ActionJackson implements ActionListener{
	private IContextMenuInvocation inv;
	private IBurpExtenderCallbacks cb;
	private boolean isExclude=false;
	private HashMap<String,String> sploits;
	private String PropKey;
	
	public ActionJackson(IContextMenuInvocation inv, IBurpExtenderCallbacks callbacks, boolean isExclude ){
		this.inv = inv;
		this.cb = callbacks;
		this.isExclude=isExclude;
		
	}
	public ActionJackson(IContextMenuInvocation inv, IBurpExtenderCallbacks callbacks, HashMap<String,String>sploits){
		this.inv = inv;
		this.cb = callbacks;
		this.sploits = sploits;
		
	}
	public ActionJackson(IContextMenuInvocation inv, IBurpExtenderCallbacks callbacks, HashMap<String,String>sploits, String PropKey){
		this.inv = inv;
		this.cb = callbacks;
		this.sploits = sploits;
		this.PropKey = PropKey;
		
	}

	public ActionJackson(IContextMenuInvocation inv, IBurpExtenderCallbacks callbacks){
		this.inv = inv;
		this.cb = callbacks;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String caller = e.getActionCommand();
		if(PropKey != null && !PropKey.equals(""))
			caller=PropKey;
		
		//If adding a domian to scope then do this:
		if(caller.equals("Domains2Scope") || caller.equals("!Domains2Scope")){
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
		}else{ // Else we want to add our attacks.
			replace(sploits.get(caller));
			
		}
		
		
	}
	
	
	
	

	private void replace(Object replace){
		if(replace == null)
			return;
		int start = inv.getSelectionBounds()[0];
		int stop = inv.getSelectionBounds()[1];
		if(replace.getClass().getName().equals("java.lang.String")){
			for(IHttpRequestResponse o : inv.getSelectedMessages()){
				String all = getMessage(o);
				String Selected = all.substring(start, stop);
				String begin = all.substring(0, start);
				String end = all.substring(stop);
				all = begin + replace + end;
				setMessage(o, all);
				break;
			}
		}else{
			for(IHttpRequestResponse o : inv.getSelectedMessages()){
				byte[] all = getMsgBytes(o);
				byte[] begin = Arrays.copyOfRange(all, 0, start);
				byte[] end = Arrays.copyOfRange(all, stop, all.length);
				byte [] r = (byte[])replace;
				byte [] out = new byte[begin.length + end.length + r.length];
				System.arraycopy(begin, 0, out, 0, begin.length);
				System.arraycopy(r, 0, out, begin.length, r.length);
				System.arraycopy(end, 0, out, r.length+begin.length, end.length);
				setMsgBytes(o,out);
				break;
			}
			
		}
	
	}
	private boolean isRequest(){
		if(inv.getInvocationContext() == inv.CONTEXT_MESSAGE_EDITOR_REQUEST || inv.getInvocationContext() == inv.CONTEXT_MESSAGE_VIEWER_REQUEST)
			return true;
		else 
			return false;
		
	}
	
	private String getMessage(IHttpRequestResponse o){
		return (new String(isRequest()? o.getRequest(): o.getResponse()));
	}
	private byte[] getMsgBytes(IHttpRequestResponse o){
		return isRequest()? o.getRequest(): o.getResponse();
	}
	private void setMessage(IHttpRequestResponse o, String update){
		if(isRequest()){
			o.setRequest(update.getBytes());
			
		}else{
			o.setResponse(update.getBytes());
		}
	}
	private void setMsgBytes(IHttpRequestResponse o, byte [] update){
		if(isRequest()){
			o.setRequest(update);
			
		}else{
			o.setResponse(update);
		}
	}
	

}
