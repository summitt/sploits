#sploits - A [Burp Extension](https://portswigger.net/) with predefined payloads that can be injected into your requests and responses.

This [Burp](https://portswigger.net/) extension  adds to [Burp](https://portswigger.net/) the 'sploits' right click menue to Repeater. This allows users to select many common XSS or SQLi payloads that replace text in Repeater that is selected. If no text is selected then the payload is added to where the mouse pointer is currently located. 

Sploits lets users create a local text file with their custom exploits or use files from remote sources. 

![](/showsploits.png )

## sploits naming 
The name of the sploit can change the submenu for the sploit. The '.' is used to separate normal menu items from sub menus. 
If the name of the sploit is 'basicXSS' then it will show up in the top menu. If the sploit is named 'xss.basic' then it will show up under a submenu named 'xss'.
![](/sploitname.png )
![](/sploitmenu.png )

The following names are reserved.
- Starts with 'remote'. These are used by the tool to store URL's to remote properties files.
- 'proxy' is used to store local proxy setting to access remote properties files

## remote sploit naming
If you want to host a sploit properties file then the same rule apply as mentioned above with one exception. It is highly recommended that you add a property called "title" to your properties file. This will create a submenu with your title name so that all your contributions are contained together.
![](/sploitprops.png )
![](/sploitpropsmenu.png )







## Add All or Remove All from scope

This [Burp](https://portswigger.net/) extension also adds to [Burp](https://portswigger.net/) the ability to add or exclude domains to burp's scope by selecting one or many items in the Proxy History or Repeater and selecting either 'Add All To Scope' or  'Remove All From Scope'.

[Burp](https://portswigger.net/) currently has an option add items from the proxy history to the scope but this is very specific. It will only add the exact protocol and exact path to the scope.

This plugin adds both http and https and only adds the domain w/o path information. 

Note: The following screenshots show an older version of the plugin. The Domains2Scope has been replaces with 'Add All To Scope' and !Domains2Scope has been replaces with 'Remove All From Scope'

![Burp History: Right Click](/history-burp.png "Burp History: add or exclude domains from scope.")



![Burp Scope](/burp-scope.png "Both HTTPS and HTTP added to scope with only the domains names.")


You can check out the source code or download the jar from the [releases](https://github.com/summitt/domains2scope/releases)
