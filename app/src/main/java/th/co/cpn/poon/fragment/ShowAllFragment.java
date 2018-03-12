package th.co.cpn.poon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import th.co.cpn.poon.R;
import th.co.cpn.poon.utility.GetAllData;
import th.co.cpn.poon.utility.MyAdapter;
import th.co.cpn.poon.utility.MyConstance;

/**
 * Created by kiakkarachai on 12/03/2018.
 */

public class ShowAllFragment extends Fragment{


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();

    }  //Main Method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewFood);
        String tag = "12MarchV1";
        MyConstance myConstance = new MyConstance();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstance.getUrlReadAllFood());

            String jsonString = getAllData.get();
            Log.wtf(tag, "JSON ==>" + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] nameFoodString = new String[jsonArray.length()];
            String[] imagePathString = new String[jsonArray.length()];


            for (int i=0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameFoodString[i] = jsonObject.getString("NameFood");
                imagePathString[i] = jsonObject.getString("ImagePath");
            }  // for


            MyAdapter myAdapter = new MyAdapter(getActivity(), nameFoodString, imagePathString);
            listView.setAdapter(myAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);
        return view;
    }
}
