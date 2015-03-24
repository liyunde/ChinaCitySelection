package knight.chinacityselection;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import knight.chinacityselection.adapter.GridViewAdapter;
import knight.chinacityselection.db.CityDB;
import knight.chinacityselection.views.GridPop;


public class MainActivity extends FragmentActivity {


    private GridPop gridpop;
    private GridView gv;

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.id_btn_1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gridpop != null) {
                    if (gridpop.isShowing()) {
                        gridpop.dismiss();
                    } else {
                        gridpop.showAsDropDown(v);
                    }
                }
            }
        });

        gridpop = new GridPop(MainActivity.this, R.layout.fragment_main);

        CityDB cityDB = ClientApplication.getInstance().getCityDB();
        ArrayList<String> stringArray = (ArrayList<String>) cityDB.getAllProvince();

        gv = gridpop.getAllItemGrid();
        gv.setAdapter(new GridViewAdapter(MainActivity.this, stringArray));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO DO SOMETHING
                Toast.makeText(getApplicationContext(), "item on clicked" + position, Toast.LENGTH_SHORT).show();
                if (gridpop.isShowing()) {
                    gridpop.dismiss();
                }
            }
        });
    }

}


