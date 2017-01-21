package io.habets.targettester.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.habets.targettester.R;
import io.habets.targettester.StopWatch;

public class NFCActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] filters;
    private String[][] techList;
    private StopWatch stopWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter techFilter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        filters = new IntentFilter[]{techFilter};
        techList = new String[][]{new String[]{MifareClassic.class.getName()}};
        stopWatch = new StopWatch();
    }

    @Override
    protected void onResume() {
        super.onResume();
        parseIntent(getIntent());
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, filters, techList);
        stopWatch.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIntent(intent);
    }

    private void parseIntent(Intent intent) {
        if (intent == null) return;
        if (!NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) return;

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String text = "Time: " + stopWatch.stopAndGetTime() +
                "\nID  : " + Arrays.toString(tag.getId()) +
                "\nTech: " + Arrays.toString(tag.getTechList());
        textView.setText(text);
    }
}
