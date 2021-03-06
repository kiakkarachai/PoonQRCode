package th.co.cpn.poon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class ShowAllFragment extends Fragment {


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

            final String[] nameFoodString = new String[jsonArray.length()];
            final String[] imagePathString = new String[jsonArray.length()];
            final String[] categoryStrings = new String[jsonArray.length()];
            final String[] priceStrings = new String[jsonArray.length()];
            final String[] detailStrings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameFoodString[i] = jsonObject.getString("NameFood");
                imagePathString[i] = jsonObject.getString("ImagePath");
                categoryStrings[i] = jsonObject.getString("Category");
                priceStrings[i] = jsonObject.getString("Price");
                detailStrings[i] = jsonObject.getString("Detail");
            }  // for


            MyAdapter myAdapter = new MyAdapter(getActivity(), nameFoodString, imagePathString);
            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Replace Fragment with DetailFragment
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentServiceFragment, DetailFragment.detailInstance(
                            nameFoodString[i],
                            imagePathString[i],
                            categoryStrings[i],
                            priceStrings[i],
                            detailStrings[i]))
                            .addToBackStack(null)
                            .commit();



                }
            });

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
