package com.vouov.http;

import android.util.Log;
import com.vouov.exception.SVException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yuminglong
 * Date: 11-9-2
 * Time: 下午2:38
 * Version: 1.0.0
 */
public class HttpServlet {
    private static String TAG = "HttpServlet";
    /**
     * 浏览器代理
     */
    private static String AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13";
    /**
     * true 基础访问模式， false 高级访问模式
     */
    private boolean basic = true;
    protected DefaultHttpClient httpClient;
    /**
     * 访问服务器是否要需要传递cookies，以便服务器能够识别
     */
    protected boolean keepLive = false;
    protected CookieStore cookieStore;

    public HttpServlet() {
    }

    public HttpServlet(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * 请求访问
     * @param request
     * @return
     * @throws Exception
     */
    public byte[] doRequest(HttpUriRequest request) throws SVException {
        Log.d(TAG, "doRequest(HttpUriRequest request) [request = "+request.toString()+" ]");
        byte[] result = null;
        if (this.httpClient == null) {
            this.httpClient = this.getHttpClient(!basic);
        }
        if (keepLive && this.cookieStore != null) {
            this.httpClient.setCookieStore(this.cookieStore);
        }
        try {
            HttpResponse httpResponse = this.httpClient.execute(request);
            if (keepLive) {
                this.cookieStore = this.httpClient.getCookieStore();
            }
            HttpEntity entity = httpResponse.getEntity();
            if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
                result = EntityUtils.toByteArray(entity);
            }else{
                throw new SVException("Http response status not 200");
            }
            if (entity != null) {
                entity.consumeContent();
            }
        } catch (Exception e) {
            Log.e(TAG, "doRequest(HttpUriRequest request) [request = "+request.toString()+" ]", e);
            throw new SVException(e);
        }
        return result;
    }

    /**
     * Post请求
     * @param url
     * @param params
     * @return
     */
    public byte[] doPost(String url, List<BasicNameValuePair> params)  throws SVException {
        Log.d(TAG, "doPost(String url, List<BasicNameValuePair> params) [url = "+url+", parmas = "+params+" ]");
        HttpPost httpPost = new HttpPost(url);
        try {
            if (params != null && !params.isEmpty()) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                httpPost.setEntity(entity);
            }
            return doRequest(httpPost);
        } catch (SVException e) {
            Log.e(TAG, "doPost(String url, List<BasicNameValuePair> params) [url = "+url+", parmas = "+params+" ]", e);
            throw  e;
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "doPost Failed [UnsupportedEncodingException]", e);
            throw  new SVException(e);
        }
    }

    /**
     *
     * @param url
     * @param json
     * @return
     */
    public byte[] doPost(String url, JSONObject json)  throws SVException {
        Log.d(TAG, "doPost(String url, JSONObject json) [url = "+url+", json = "+json.toString()+" ]");
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("JSON", json.toString()));
        return doPost(url, params);
    }

    /**
     *
     * @param url
     * @param params
     * @param files
     * @return
     */
    public byte[] doPost(String url, List<BasicNameValuePair> params, List<FileNameValuePair> files)  throws SVException {
        Log.d(TAG, "doPost(String url, List<BasicNameValuePair> params, List<FileNameValuePair> files) [url = "+url
                +", parmas = "+params+", files = "+files+" ]");
        HttpPost httpPost = new HttpPost(url);
        try {
            MultipartEntity entity = new MultipartEntity();
            if (params != null && !params.isEmpty()) {
                for (NameValuePair param : params) {
                    entity.addPart(param.getName(), new StringBody(param.getValue()));
                }
            }
            if (files != null && !files.isEmpty()) {
                for (FileNameValuePair file : files) {
                    entity.addPart(file.getName(), new FileBody(file.getFile()));
                }
            }
            httpPost.setEntity(entity);
            return doRequest(httpPost);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "doPost(String url, List<BasicNameValuePair> params, List<FileNameValuePair> files) [url = "+url
                +", parmas = "+params+", files = "+files+" ]", e);
            throw new SVException(e);
        }
    }

    /**
     * 附加额外参数的URL Get请求
     * @param url
     * @param params
     * @return
     */
    public byte[] doGet(String url, List<BasicNameValuePair> params)  throws SVException {
        Log.d(TAG, "doGet(String url, List<BasicNameValuePair> params) [url = "+url+", parmas = "+params+" ]");
        try {
            String newUrl = url;
            if (params != null && !params.isEmpty()) {
                String query = "";
                for (NameValuePair param : params) {
                    query += "&" + param.getName() + "=" + URLEncoder.encode(param.getValue(), "UTF-8");
                }
                if (newUrl.endsWith("?")) {
                    query = query.replaceFirst("&", "");
                } else if (!newUrl.contains("?")) {
                    query = query.replaceFirst("&", "?");
                }
                if (!"".equals(query)) {
                    newUrl += query;
                }
            }

            HttpGet httpGet = new HttpGet(newUrl);
            return doRequest(httpGet);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "doGet Failed [UnsupportedEncodingException]", e);
            throw  new SVException(e);
        }
    }

    /**
     *  直接URL Get请求，
     * @param url
     * @return
     * @throws SVException
     */
    public byte[] doGet(String url)  throws SVException {
        Log.d(TAG, "doGet(String url) [url = "+url+" ]");
        return doGet(url, null);
    }

    /**
     * 获得HttpClient
     *
     * @param agent
     * @return
     */
    public static DefaultHttpClient getHttpClient(boolean agent) {
        Log.d(TAG, "getHttpClient(boolean agent) [agent = "+String.valueOf(agent)+"]");
        HttpParams params = new BasicHttpParams();
        params.setIntParameter(AllClientPNames.CONNECTION_TIMEOUT, 10000);
        if (agent) {
            params.setParameter(AllClientPNames.USER_AGENT, AGENT);
        }
        return new DefaultHttpClient(params);
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public DefaultHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public boolean isKeepLive() {
        return keepLive;
    }

    public void setKeepLive(boolean keepLive) {
        this.keepLive = keepLive;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public void close() {
        if (this.httpClient != null) {
            this.httpClient.getConnectionManager().shutdown();
            this.httpClient = null;
        }
    }
}
