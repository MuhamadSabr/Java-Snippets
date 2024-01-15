package com.mmd;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainURI {
    public static void main(String[] args) {

        try {

            //Define a new URI
            URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsunge");

            URI uriBase = new URI("http://username:password@myserver.com:5000");
            URI uriPath = new URI("/catalogue/phones?os=android#samsunge");

            URI resolvedURI = uriBase.resolve(uriPath);
            System.out.println("Resolved URI: " + resolvedURI);

            URI relativizeURI = uri.relativize(uriPath);
            System.out.println("Relativized ur: " + relativizeURI);
            System.out.println(uri.relativize(uriBase));

//            //Get specific components of the URI.
//            System.out.println(uri.getScheme());                //db
//            System.out.println(uri.getSchemeSpecificPart());//everything after the db: except the fragment //username:password@server.com:5000/catalogue/phones?os=android
//            System.out.println(uri.getAuthority());     //username:password@server.com:5000 a combination of usernameInfo + host + host
//            System.out.println(uri.getUserInfo());  //username:password
//            System.out.println(uri.getHost());      //server.com
//            System.out.println(uri.getPort());  //5000
//            System.out.println(uri.getPath());  //      /catalogue/phones
//            System.out.println(uri.getQuery()); // os=android
//            System.out.println(uri.getFragment());              //samsunge


        } catch (URISyntaxException e){
            e.printStackTrace();
        }
    }
}
