package com.chelsea.app.chelseablues.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class HttpRequest {

	private static final String TAG = "HttpRequest";

	private static class SocketTimeOutRetryHandler implements HttpRequestRetryHandler {

    private final HttpParams httpParams;
    private final int maxNrRetries;

    /**
     * @param httpParams
     *            HttpParams that will be used in the HttpRequest.
     * @param maxNrRetries
     *            Max number of times to retry Request on failure due to
     *            SocketTimeOutException.
     */
    private SocketTimeOutRetryHandler(HttpParams httpParams, int maxNrRetries) {
      this.httpParams = httpParams;
      this.maxNrRetries = maxNrRetries;
    }

    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
      if (exception instanceof SocketTimeoutException) {
        if (executionCount <= maxNrRetries) {

          if (httpParams != null) {
            final int newSocketTimeOut = HttpConnectionParams.getSoTimeout(httpParams) * 2;
            HttpConnectionParams.setSoTimeout(httpParams, newSocketTimeOut);
            // log.d(ACRA.LOG_TAG,
            // "SocketTimeOut - increasing time out to " +
            // newSocketTimeOut + " millis and trying again");
          } else {
            // log.d(ACRA.LOG_TAG,
            // "SocketTimeOut - no HttpParams, cannot increase time out. Trying again with current settings");
          }

          return true;
        }

        // log.d(ACRA.LOG_TAG,
        // "SocketTimeOut but exceeded max number of retries : " +
        // maxNrRetries);
      }

      return false; // To change body of implemented methods use File |
              // Settings | File Templates.
    }
  }

  private String login;
  private String password;
  private int connectionTimeOut = 5000;
  private int socketTimeOut = 5000;
  private int maxNrRetries = 3;

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setConnectionTimeOut(int connectionTimeOut) {
    this.connectionTimeOut = connectionTimeOut;
  }

  public void setSocketTimeOut(int socketTimeOut) {
    this.socketTimeOut = socketTimeOut;
  }

  /**
   * The default number of retries is 3.
   * 
   * @param maxNrRetries
   *            Max number of times to retry Request on failure due to
   *            SocketTimeOutException.
   */
  public void setMaxNrRetries(int maxNrRetries) {
    this.maxNrRetries = maxNrRetries;
  }

  
  
  
  /**
   * Posts to a URL.
   * 
   * @param url
   *            URL to which to post.
   * @param parameters
   *            Map of parameters to post to a URL.
   * @throws IOException
   *             if the data cannot be posted.
   */
  public HttpResponse sendPost(URL url, Map<?, ?> parameters) throws IOException {
	  return sendPost(url.toString(), parameters);
  }
  
  public HttpResponse sendPost(String url, Map<?, ?> parameters) throws IOException {
	  
    final HttpClient httpClient = getHttpClient();
    final HttpPost httpPost = getHttpPost(url, parameters);

    // log.d(ACRA.LOG_TAG, "Sending request to " + url);

    // TODO Consider using a RequestRetryHandler and if its a
    // SocketTimeoutException to up the SocketTimeout and try again.
    // See
    // http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
    // I think SocketTimeOut while waiting for response may be the cause of
    // the multiple crash reports () - I
    final HttpResponse response = httpClient.execute(httpPost, new BasicHttpContext());
    
    if (response != null) {
      final StatusLine statusLine = response.getStatusLine();
      
      if (statusLine != null) {
        final String statusCode = Integer.toString(response.getStatusLine().getStatusCode());
        if (statusCode.startsWith("4") || statusCode.startsWith("5")) {
          throw new IOException("Host returned error code " + statusCode);
        }
      }
      
      //final String ret = EntityUtils.toString(response.getEntity());
      // if (ACRA.DEV_LOGGING) log.d(ACRA.LOG_TAG,
      // "HTTP " + (statusLine != null ? statusLine.getStatusCode() :
      // "NoStatusLine#noCode") + " - Returning value:"
      // + ret.substring(0, Math.min(ret.length(), 200)));
    } 
    else {
      // if (ACRA.DEV_LOGGING) log.d(ACRA.LOG_TAG, "HTTP no Response!!");
    }
    
    return response;
  }
  
  /**
   * Gets to a URL.
   * 
   * @param url
   *            URL to which to get.
   * @throws IOException
   *             if the data cannot be posted.
   */
  public HttpResponse sendGet(URL url) throws IOException {
	  return sendGet(url.toString());
  }
  
  public HttpResponse sendGet(String url) throws IOException {
	  
    final HttpClient httpClient = getHttpClient();
    final HttpGet httpGet = getHttpGet(url);

    // log.d(ACRA.LOG_TAG, "Sending request to " + url);

    // TODO Consider using a RequestRetryHandler and if its a
    // SocketTimeoutException to up the SocketTimeout and try again.
    // See
    // http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
    // I think SocketTimeOut while waiting for response may be the cause of
    // the multiple crash reports () - I
    final HttpResponse response = httpClient.execute(httpGet, new BasicHttpContext());
    
    if (response != null) {
      final StatusLine statusLine = response.getStatusLine();
      
      if (statusLine != null) {
        final String statusCode = Integer.toString(response.getStatusLine().getStatusCode());
        if (statusCode.startsWith("4") || statusCode.startsWith("5")) {
          throw new IOException("Host returned error code " + statusCode);
        }
      }
      
      //final String ret = EntityUtils.toString(response.getEntity());
      // if (ACRA.DEV_LOGGING) log.d(ACRA.LOG_TAG,
      // "HTTP " + (statusLine != null ? statusLine.getStatusCode() :
      // "NoStatusLine#noCode") + " - Returning value:"
      // + ret.substring(0, Math.min(ret.length(), 200)));
    } 
    else {
      // if (ACRA.DEV_LOGGING) log.d(ACRA.LOG_TAG, "HTTP no Response!!");
    }
    
    return response;
  }

  /**
   * @return HttpClient to use with this HttpRequest.
   */
  private HttpClient getHttpClient() {
    final HttpParams httpParams = new BasicHttpParams();
    
    httpParams.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
    HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeOut);
    HttpConnectionParams.setSoTimeout(httpParams, socketTimeOut);
    HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

    final SchemeRegistry registry = new SchemeRegistry();
    registry.register(new Scheme("http", new PlainSocketFactory(), 80));
    registry.register(new Scheme("https", (new FakeSocketFactory()), 443));

    final ClientConnectionManager clientConnectionManager = new ThreadSafeClientConnManager(httpParams, registry);
    final DefaultHttpClient httpClient = new DefaultHttpClient(clientConnectionManager, httpParams);

    final HttpRequestRetryHandler retryHandler = new SocketTimeOutRetryHandler(httpParams, maxNrRetries);
    httpClient.setHttpRequestRetryHandler(retryHandler);

    return httpClient;
  }

  /**
   * @return Credentials to use with this HttpRequest or null if no credentials were supplied.
   */
  private UsernamePasswordCredentials getCredentials() {
    if (login != null || password != null) {
      return new UsernamePasswordCredentials(login, password);
    }

    return null;
  }

  private HttpPost getHttpPost(String url, Map<?, ?> parameters) throws UnsupportedEncodingException {

    final HttpPost httpPost = new HttpPost(url);

    // log.d(ACRA.LOG_TAG, "Setting httpPost headers");
    final UsernamePasswordCredentials creds = getCredentials();
    if (creds != null) {
      httpPost.addHeader(BasicScheme.authenticate(creds, "UTF-8", false));
    }
    
    httpPost.setHeader("User-Agent", "Android");
    httpPost.setHeader("Accept", "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

    final String paramsAsString = getParamsAsString(parameters);
    httpPost.setEntity(new StringEntity(paramsAsString, "UTF-8"));

    return httpPost;
  }
  
  private HttpGet getHttpGet(String url) throws UnsupportedEncodingException {

	    final HttpGet httpGet = new HttpGet(url);

	    // log.d(ACRA.LOG_TAG, "Setting httpPost headers");
	    final UsernamePasswordCredentials creds = getCredentials();
	    if (creds != null) {
	    	httpGet.addHeader(BasicScheme.authenticate(creds, "UTF-8", false));
	    }
	    
	    httpGet.setHeader("User-Agent", "Android");
	    httpGet.setHeader("Accept", "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
	    httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");

	    return httpGet;
	  }

  /**
   * Converts a Map of parameters into a URL encoded Sting.
   * 
   * @param parameters
   *            Map of parameters to convert.
   * @return URL encoded String representing the parameters.
   * @throws UnsupportedEncodingException
   *             if one of the parameters couldn't be converted to UTF-8.
   */
  private String getParamsAsString(Map<?, ?> parameters) throws UnsupportedEncodingException {

    final StringBuilder dataBfr = new StringBuilder();
    
    if (parameters == null) {
    	return dataBfr.toString();
    }
    
    for (final Object key : parameters.keySet()) {
      if (dataBfr.length() != 0) {
        dataBfr.append('&');
      }
    
      final Object preliminaryValue = parameters.get(key);
      final Object value = (preliminaryValue == null) ? "" : preliminaryValue;
      
      dataBfr.append(URLEncoder.encode(key.toString(), "UTF-8"));
      dataBfr.append('=');
      dataBfr.append(URLEncoder.encode(value.toString(), "UTF-8"));
    }

    return dataBfr.toString();
  }
}

/**
 * Accepts any certificate, ideal for self-signed certificates.
 */
class NaiveTrustManager implements X509TrustManager {
  /*
   * (non-Javadoc)
   * 
   * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
   */
  public X509Certificate[] getAcceptedIssuers() {
    return new X509Certificate[0];
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.
   * X509Certificate[], java.lang.String)
   */
  public void checkClientTrusted(X509Certificate[] x509CertificateArray, String string) throws CertificateException {
	  
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.
   * X509Certificate[], java.lang.String)
   */
  public void checkServerTrusted(X509Certificate[] x509CertificateArray, String string) throws CertificateException {
	  
  }
}

class FakeSocketFactory implements SocketFactory, LayeredSocketFactory {

  private SSLContext sslcontext = null;

  private static SSLContext createEasySSLContext() throws IOException {
    try {
      final SSLContext context = SSLContext.getInstance("TLS");
      context.init(null, new TrustManager[] { new NaiveTrustManager() },
          null);
      return context;
    } catch (GeneralSecurityException e) {
      throw new IOException(e.getMessage());
    }
  }

  private SSLContext getSSLContext() throws IOException {
    if (this.sslcontext == null) {
      this.sslcontext = createEasySSLContext();
    }
    
    return this.sslcontext;
  }

  public Socket connectSocket(Socket sock, String host, int port, InetAddress localAddress, int localPort, HttpParams params)
		  																	throws IOException {
    final int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
    final int soTimeout = HttpConnectionParams.getSoTimeout(params);

    final InetSocketAddress remoteAddress = new InetSocketAddress(host, port);
    final SSLSocket sslsock = (SSLSocket) ((sock != null) ? sock : createSocket());

    if ((localAddress != null) || (localPort > 0)) {
      // we need to bind explicitly
      if (localPort < 0) {
        localPort = 0; // indicates "any"
      }
      
      final InetSocketAddress isa = new InetSocketAddress(localAddress, localPort);
      sslsock.bind(isa);
    }

    sslsock.connect(remoteAddress, connTimeout);
    sslsock.setSoTimeout(soTimeout);

    return sslsock;
  }

  public Socket createSocket() throws IOException {
    return getSSLContext().getSocketFactory().createSocket();
  }

  public boolean isSecure(Socket arg0) throws IllegalArgumentException {
    return true;
  }

  public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
    return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
  }

}
