package com.example.vyapaar.dashboard.ui.dashboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vyapaar.R;
import com.example.vyapaar.dashboard.adapter.DashboardItemRecyclerViewAdapter;
import com.example.vyapaar.dashboard.contract.BaseContract;
import com.example.vyapaar.dashboard.model.DashboardResponse;
import com.example.vyapaar.dashboard.ui.dashboard.dummy.DummyContent;
import com.example.vyapaar.dashboard.ui.dashboard.dummy.DummyContent.DummyItem;
import com.example.vyapaar.databinding.FragmentDashboardItemListBinding;
import com.example.vyapaar.ui.contract.LoginContract;
import com.example.vyapaar.ui.main.admin.AdminRegistrationViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DashboardItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private DashboardViewModel mViewModel;
    private List<String> list;
    private BaseContract contract;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DashboardItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DashboardItemFragment newInstance(int columnCount) {
        DashboardItemFragment fragment = new DashboardItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard_item_list, container, false);
        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        FragmentDashboardItemListBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mViewModel);
        binding.setCallback(contract);
        mViewModel.isLoading.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(!mViewModel.isLoading.get()){
                    contract.stopLoader();
                }
            }
        });
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<DashboardResponse>() {
            @Override
            public void onChanged(DashboardResponse dashboardResponse) {
                list = dashboardResponse.getData();
                setAdapter(view);
            }
        });

        contract.startLoader();
        mViewModel.getDashboardData();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setContract(BaseContract contract){
        this.contract = contract;
    }

    private void setAdapter(View view) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new DashboardItemRecyclerViewAdapter(list, mListener));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String item);
    }
}
