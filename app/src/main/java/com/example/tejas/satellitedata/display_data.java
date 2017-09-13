package com.example.tejas.satellitedata;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Tejas on 13-Jul-17.
 */

public class display_data extends Fragment {
    MainActivity activity;
    WebView myWebView;
    Runnable runnable;
    Handler handler;
    View view;
    public static int no_internet=0;
    float currentScale;
    String str;
    int flag = 1;
    Context context;
    String temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = new MainActivity();
        this.context=container.getContext();
        temp=activity.link;
        return inflater.inflate(R.layout.obc_summary, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parentViewGroup = (ViewGroup) view.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flag = 1;

        //you can set the title for your toolbar here for different fragments different titles
        //MainActivity.toolbar.setTitle(MainActivity.Title);
        myWebView = (WebView) view.findViewById(R.id.web1);
        final WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(30 * 1024 * 1024);
        webSettings.setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
        webSettings.setAllowFileAccess(true);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        //myWebView.addJavascriptInterface(new WebAppInterface(getActivity()), "Android");
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                webSettings.setJavaScriptEnabled(true);
                webSettings.setDomStorageEnabled(true);

                webSettings.setUseWideViewPort(true);
                webSettings.setBuiltInZoomControls(true);
                  myWebView.setWebViewClient(new WebViewClient() {
                      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                          webSettings.setBuiltInZoomControls(false);
                          myWebView.loadUrl("file:///android_asset/offline.html");
                          no_internet=1;
                          flag = 1;
                      }

                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        Log.d("WEBVIEW 1", "Page started to load");
                        fetch_data fetch = new fetch_data(getActivity(), 0);
                        fetch.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        Log.d("WEBVIEW 1","After fetch_data");
                    }

                    @Override
                    public void onPageFinished(WebView view, String string) {
                        //flag=1;
                        Log.d("WEBVIEW 1", "onpage finished called");
                        super.onPageFinished(view, string);
                        flag = 1;
                    }

                });

                        if (fetch_data.line == -1) {
                            //Toast.makeText(getActivity(),"Data",Toast.LENGTH_SHORT).show();
                            myWebView.loadUrl(activity.offline);
                        } else {
                            if (flag == 1) {
                                myWebView.loadUrl(activity.link);
                                flag = 0;
                                no_internet=0;
                            }
                        }
                    handler.postDelayed(this, 1000);
                }
            };
        runnable.run();
        }
    }

