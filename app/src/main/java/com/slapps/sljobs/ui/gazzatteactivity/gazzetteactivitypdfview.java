package com.slapps.sljobs.ui.gazzatteactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cnrylmz.zionfiledownloader.DownloadFile;
import com.cnrylmz.zionfiledownloader.FILE_TYPE;
import com.cnrylmz.zionfiledownloader.ZionDownloadFactory;
import com.cnrylmz.zionfiledownloader.ZionDownloadListener;
import com.slapps.sljobs.R;

import im.delight.android.webview.AdvancedWebView;
import me.ibrahimsn.lib.CirclesLoadingView;

public class gazzetteactivitypdfview extends AppCompatActivity implements AdvancedWebView.Listener  {



    String pdf;
    ProgressDialog  progressDialog;
    private Uri Download_Uri;

    String ad_no;
    String name="ishanpdf";
    private DownloadManager downloadManager;
    private long refid;
    String pdflink;
    String date;
    ProgressDialog  progressDialogweb;
    CirclesLoadingView cc;
    LinearLayout ll;
    AdvancedWebView mWebView;
    private Button btndownload;
    private Button btnshare;
    ProgressDialog  progressDialogload;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gazzetteactivity);


            Intent iin= getIntent();
            Bundle b = iin.getExtras();
            pdflink =(String) b.get("link");
             date =(String) b.get("date");

             mWebView = (AdvancedWebView) findViewById(R.id.webView1);

             ll= (LinearLayout)findViewById(R.id.llbottom);

             progressDialogload = new ProgressDialog(this);
             progressDialogload.setMessage("Loading...");
             progressDialogload.setCancelable(false);
             progressDialogload.show();







//        mWebView.getSettings().setJavaScriptEnabled(true);
//        pdf = "https://www.youthcentral.vic.gov.au/sites/default/files/YouthCentral_CoverLetter_WorkExperience_March-27-2017.pdf";
//        mWebView.loadUrl(" https://docs.google.com/viewer?url=" + pdflink);
//        mWebView.getSettings().setSupportZoom(true);
//        mWebView.getSettings().setBuiltInZoomControls(true);
//      //  webview.setDisplayZoomControls(false);
//        mWebView.getSettings().setDisplayZoomControls(false);





        mWebView.setListener(this, this);
        mWebView.setGeolocationEnabled(false);
        mWebView.setMixedContentAllowed(false);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);


        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
              //  Toast.makeText(gazzetteactivitypdfview.this, "Finished loading", Toast.LENGTH_SHORT).show();


                ll.setVisibility(View.VISIBLE);
                btndownload.setVisibility(View.VISIBLE);
                btnshare.setVisibility(View.VISIBLE);
                progressDialogload.cancel();


            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
               // Toast.makeText(gazzetteactivitypdfview.this, title, Toast.LENGTH_SHORT).show();
            }

        });
        mWebView.addHttpHeader("X-Requested-With", "");
       // mWebView.loadUrl(pdflink);

        mWebView.loadUrl(" https://docs.google.com/viewer?url=" + pdflink);






          btndownload=(Button)findViewById(R.id.btndownload);
          btnshare=(Button)findViewById(R.id.btnshare);
        ll.setVisibility(View.INVISIBLE);
        btndownload.setVisibility(View.INVISIBLE);
        btnshare.setVisibility(View.INVISIBLE);

        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {

                downloadpdf();


                //new DownloadTask(gazzetteactivitypdfview.this, pdf);

                       }
        });


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi, Check this link , might helpfull for your Job search... \n"+pdflink);
                startActivity(Intent.createChooser(shareIntent, "Share link using"));
            }
        });



    }

    private void downloadpdf() {



        ProgressDialog  progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ZionDownloadFactory factory = new ZionDownloadFactory(this, pdflink, date);
        DownloadFile downloadFile = factory.downloadFile(FILE_TYPE.PDF);
        downloadFile.start(new ZionDownloadListener() {
            @Override
            public void OnSuccess(String dataPath) {

                Log.d("ishan","downloaded file path is "+dataPath);

              Toast.makeText(gazzetteactivitypdfview.this,"File Downloaded !",Toast.LENGTH_LONG).show();
              progressDialog.cancel();
                // the file saved in your device..
                //dataPath--> android/{your app package}/files/Download
            }

            @Override
            public void OnFailed(String message) {
                Log.d("ishan","downloaded file path is failed");
            }

            @Override
            public void OnPaused(String message) {
                Log.d("ishan","downloaded file path is paused ");
            }

            @Override
            public void OnPending(String message) {
                Log.d("ishan","downloaded file path is pendinf ");
            }

            @Override
            public void OnBusy() {
                Log.d("ishan","downloaded file path is  busy");
            }
        });

    }








    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        mWebView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageFinished(String url) {
        mWebView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Toast.makeText(gazzetteactivitypdfview.this, "onPageError(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        Toast.makeText(gazzetteactivitypdfview.this, "onDownloadRequested(url = "+url+",  suggestedFilename = "+suggestedFilename+",  mimeType = "+mimeType+",  contentLength = "+contentLength+",  contentDisposition = "+contentDisposition+",  userAgent = "+userAgent+")", Toast.LENGTH_LONG).show();

		/*if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
			// download successfully handled
		}
		else {
			// download couldn't be handled because user has disabled download manager app on the device
		}*/
    }

    @Override
    public void onExternalPageRequest(String url) {
       // Toast.makeText(gazzetteactivitypdfview.this, "onExternalPageRequest(url = "+url+")", Toast.LENGTH_SHORT).show();
    }



}
