package fyp.app.aluminportal.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;

import fyp.app.aluminportal.R;
import fyp.app.aluminportal.model.user;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewUsersFragment extends Fragment {


    private ViewFlipper simpleViewFlipper;
    private ArrayList<String> mCategories = new ArrayList<>();
    RotateLoading loading;

    int countInt, incrementalCount;
    DatabaseReference CustomerReference;
    RecyclerView mCustomerRecycVw;
    String count;


    public ViewUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_users, container, false);
        getActivity().setTitle("user");


        CustomerReference= FirebaseDatabase.getInstance().getReference().child("user");
        mCustomerRecycVw = view.findViewById(R.id.main_recycler_vw);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mCustomerRecycVw.setLayoutManager(mLayoutManager);
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        CustomerReference = FirebaseDatabase.getInstance().getReference().child("user");

        FirebaseRecyclerOptions<user> options = new FirebaseRecyclerOptions.Builder<user>()
                .setQuery(CustomerReference, user.class)
                .build();


        final FirebaseRecyclerAdapter<user, CustomersViewHolder> adapter = new FirebaseRecyclerAdapter<user, CustomersViewHolder>(options){
            @Override
            protected void onBindViewHolder(@NonNull final ViewUsersFragment.CustomersViewHolder holder, final int position, @NonNull final user model) {


                DisplayMetrics displaymetrics = new DisplayMetrics();
                (getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

                holder.mName.setText(model.getUsername());
                holder.mPhone.setText(model.getPhone());
                holder.mAddress.setText(model.getAddress());


            }
            @NonNull
            @Override
            public ViewUsersFragment.CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_users_item_layout, viewGroup, false);
                ViewUsersFragment.CustomersViewHolder customersViewHolder = new ViewUsersFragment.CustomersViewHolder(view);
                return customersViewHolder;
            }
        };

        mCustomerRecycVw.setAdapter(adapter);
        adapter.startListening();
        // loading.setVisibility(View.GONE);

    }
    public static class CustomersViewHolder extends RecyclerView.ViewHolder {


        ImageView postImage;
        TextView mName, mPhone, mAddress;


        public CustomersViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.UserNameTV);
            mPhone = itemView.findViewById(R.id.PhoneTV);
            mAddress = itemView.findViewById(R.id.AddressTV);

        }
    }


}
