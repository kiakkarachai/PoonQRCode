package th.co.cpn.poon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.co.cpn.poon.MainActivity;
import th.co.cpn.poon.R;

/**
 * Created by kiakkarachai on 08/03/2018.
 */

public class RegisterFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();


    } //Main Method

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

//        Setup Title and SubTitle
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_register));

//        Setup Navigator
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

}
