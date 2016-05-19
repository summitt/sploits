# domains2scope

This [Burp](https://portswigger.net/) extesion adds to [Burp](https://portswigger.net/) the ability to add or exclude domains to burp's scope by selecting one or many items in the Proxy History or Repeater and selecting either Domain2Scope or  !Domain2Scope.

[Burp](https://portswigger.net/) currently has an option add items from the proxy history to the scope but this is very specific. It will only add the exact protocol and exact path to the scope.

This plugin adds both http and https and only adds the domain w/o path information. 

![Burp History: Right Click](/history-burp.png "Burp History: add or exclude domains from scope.")



![Burp Scope](/burp-scope.png "Both HTTPS and HTTP added to scope with only the domains names.")


You can check out the source code or download the jar from the [releases](https://github.com/summitt/domains2scope/releases)
