package burp;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import com.josh.ActionJackson;

public class BurpExtender implements IBurpExtender,IContextMenuFactory{
	private IBurpExtenderCallbacks cb;

	@Override
	public List<JMenuItem> createMenuItems(IContextMenuInvocation inv) {
		
		JMenuItem inc = new JMenuItem("Domain2Scope");
		inc.addActionListener(new ActionJackson(inv, cb, false)); // This will add the domain to the scope.
		JMenuItem exc = new JMenuItem("!Domain2Scope");
		exc.addActionListener(new ActionJackson(inv, cb, true)); // This will add the domain to the scope.
		List<JMenuItem>stuff = new ArrayList<JMenuItem>();
		stuff.add(inc);
		stuff.add(exc);
		return stuff;
	}

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		this.cb = callbacks;
		cb.setExtensionName("Domain2Scope");
		cb.registerContextMenuFactory(this);
		
	}

}
