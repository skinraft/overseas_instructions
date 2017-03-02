package com.sl.tagmic.xca.zxing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;

import com.sl.tagmic.xca.AppContext;
import com.sl.tagmic.xca.zxing.view.XCallBack;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class XHttpUtil {

    private Handler mHandler;
    private Context mContext;
    // GET请求
    public static int GET = 0;
    // POST请求
    public static int POST = 1;
    // 请求OK
    public static int SUCCESS = 2;
    // 请求失败
    public static int FAIL = 3;

    public XHttpUtil(Context context) {
        this.mContext = context;
        trustAllHosts();
    }

    public void get(final String url, final XCallBack callback) {
        mHandler = new Handler(new Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                int what = msg.what;
                if (SUCCESS == what) {
                    // 成功
                    if (null != callback) {
                        callback.success((String) msg.obj);
                    }
                } else if (FAIL == what) {
                    // 失败
                    if (null != callback) {
                        callback.error((String) msg.obj);
                        Log.i("gege", (String) msg.obj);
                    }
                }
                return true;
            }
        });
        AppContext.cachedThreadPool.execute(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage();
                HttpURLConnection con = null;
                try {
                    URL urls = new URL(url);
                    HttpURLConnection https = (HttpURLConnection) new URL(url)
                            .openConnection();
                    if (urls.getProtocol().toLowerCase().equals("https")) {
                        ((HttpsURLConnection) https)
                                .setHostnameVerifier(DO_NOT_VERIFY);
                        con = https;
                    } else {
                        con = (HttpURLConnection) urls.openConnection();
                    }
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);
                    con.setRequestProperty("Connection", "Keep-Alive");
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.connect();
                    Log.i("gege", "url=" + url + "\n");
                    int res = con.getResponseCode();
                    InputStream is = null;
                    if (res == 200) {
                        is = con.getInputStream();
                        int ch;
                        StringBuffer b = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            b.append((char) ch);
                        }
                        is.close();
                        String string = new String(b.toString().getBytes(
                                "ISO-8859-1"), "UTF-8");
                        msg.what = SUCCESS;
                        msg.obj = string;
                        Log.i("gege", "url=" + url + "------>成功");
                    } else {
                        is = con.getErrorStream();
                        int ch;
                        StringBuffer b = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            b.append((char) ch);
                        }
                        is.close();
                        String string = new String(b.toString().getBytes(
                                "ISO-8859-1"), "UTF-8");
                        msg.what = FAIL;
                        msg.obj = string;
                        Log.i("gege", "url=" + url + "------>" + string);
                    }
                    con.disconnect();
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "URL异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } catch (ProtocolException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "协议异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } catch (SocketException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "SOCKET异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "IO异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } finally {
                    if (null != con) {
                        con.disconnect();
                    }
                }

            }
        });
    }

    public void post(final String url, final HashMap<String, String> params,
                     final XCallBack callback) {
        mHandler = new Handler(new Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                int what = msg.what;
                if (SUCCESS == what) {
                    // 成功
                    if (null != callback) {
                        callback.success((String) msg.obj);
                    }
                } else if (FAIL == what) {
                    // 失败
                    if (null != callback) {
                        callback.error((String) msg.obj);
                        Log.i("gege", (String) msg.obj);
                    }
                }
                return true;
            }
        });
        AppContext.cachedThreadPool.execute(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage();
                HttpURLConnection con = null;
                try {
                    URL urls = new URL(url);
                    HttpURLConnection https = (HttpURLConnection) new URL(url)
                            .openConnection();
                    if (urls.getProtocol().toLowerCase().equals("https")) {
                        ((HttpsURLConnection) https)
                                .setHostnameVerifier(DO_NOT_VERIFY);
                        con = https;
                    } else {
                        con = (HttpURLConnection) urls.openConnection();
                    }
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.setRequestMethod("POST");
                    con.setConnectTimeout(10000);
                    con.setReadTimeout(10000);
                    con.setRequestProperty("Connection", "Keep-Alive");
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestProperty("accept", "*/*");
                    con.connect();
                    Log.i("gege", "url=" + url + "\n");
                    Iterator<Entry<String, String>> iter = params.entrySet()
                            .iterator();
                    StringBuffer p = new StringBuffer();
                    while (iter.hasNext()) {
                        Entry<String, String> entry = iter.next();
                        String key = entry.getKey();
                        String val = entry.getValue();
                        p.append(key).append("=").append(val).append("&");
                    }
                    if (p.length() > 0) {
                        p.deleteCharAt(p.length() - 1);
                    }
                    byte[] bypes = p.toString().getBytes();
                    con.getOutputStream().write(bypes);// 输入参数
                    con.getOutputStream().flush();
                    int res = con.getResponseCode();
                    InputStream is = null;
                    if (res == 200) {
                        is = con.getInputStream();
                        int ch;
                        StringBuffer b = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            b.append((char) ch);
                        }
                        is.close();
                        String string = new String(b.toString().getBytes(
                                "ISO-8859-1"), "UTF-8");
                        msg.what = SUCCESS;
                        msg.obj = string;
                        Log.i("gege", "url=" + url + "------>成功");
                    } else {
                        is = con.getErrorStream();
                        int ch;
                        StringBuffer b = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            b.append((char) ch);
                        }
                        is.close();
                        String string = new String(b.toString().getBytes(
                                "ISO-8859-1"), "UTF-8");
                        msg.what = FAIL;
                        msg.obj = string;
                        Log.i("gege", "url=" + url + "------>" + string);
                    }
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "URL异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } catch (ProtocolException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "协议异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } catch (SocketException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "SOCKET异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    msg.what = FAIL;
                    msg.obj = null == e ? "IO异常" : e.getMessage() + "";
                    mHandler.sendMessage(msg);
                } finally {
                    if (null != con) {
                        con.disconnect();
                    }
                }
            }
        });
    }

    public static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    @SuppressLint("TrulyRandom")
    public static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                Log.i(TAG, "checkClientTrusted");
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                Log.i(TAG, "checkServerTrusted");
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
