package io.habets.targettester.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import io.habets.targettester.R;
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class QRActivity extends AppCompatActivity implements BarcodeRetriever {

    private BarcodeCapture barcodeCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
    }

    public void scanQR(View v) {
        findViewById(R.id.hideView).setVisibility(View.GONE);
        findViewById(R.id.btnScan).setVisibility(View.GONE);
        barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(R.id.viewScanner);
        barcodeCapture.setRetrieval(this);
    }

    @Override
    public void onRetrieved(final Barcode barcode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(QRActivity.this, barcode.displayValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRetrievedMultiple(Barcode barcode, List<BarcodeGraphic> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onRetrievedFailed(String s) {

    }
}
