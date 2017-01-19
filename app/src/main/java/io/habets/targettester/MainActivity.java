package io.habets.targettester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;
import io.habets.targettester.activities.GPSActivity;
import io.habets.targettester.activities.NFCActivity;
import io.habets.targettester.activities.QRActivity;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    private static final String[] menuItems = {
            "QR codes",
            "NFC",
            "GPS"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(this, menuItems));
    }

    @Override
    public void onItemClick(int position, String name) {
        Intent intent;
        if (name.equals(menuItems[0])) {
            intent = new Intent(this, QRActivity.class);
        } else if (name.equals(menuItems[1])) {
            intent = new Intent(this, NFCActivity.class);
        } else if (name.equals(menuItems[2])) {
            intent = new Intent(this, GPSActivity.class);
        } else {
            return;
        }

        startActivity(intent);
    }
}
