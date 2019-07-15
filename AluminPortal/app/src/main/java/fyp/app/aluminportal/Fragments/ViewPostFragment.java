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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import fyp.app.aluminportal.R;
import fyp.app.aluminportal.model.PostModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPostFragment extends Fragment {

    //    int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private ViewFlipper simpleViewFlipper;
    private ArrayList<String> mCategories = new ArrayList<>();


    int countInt, incrementalCount;
    DatabaseReference CustomerReference;
    //    CustomerProfileAdapter mProductAdapter;
    RecyclerView mCustomerRecycVw;
    String count;

    public ViewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        getActivity().setTitle("Post");


        CustomerReference = FirebaseDatabase.getInstance().getReference().child("Categories");
        mCustomerRecycVw = view.findViewById(R.id.main_recycler_vw);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mCustomerRecycVw.setLayoutManager(mLayoutManager);
        return view;

    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<PostModel> options = new FirebaseRecyclerOptions.Builder<PostModel>()
                .setQuery(CustomerReference, PostModel.class)
                .build();

        final FirebaseRecyclerAdapter<PostModel, CustomersViewHolder> adapter = new FirebaseRecyclerAdapter<PostModel, ViewPostFragment.CustomersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewPostFragment.CustomersViewHolder holder, final int position, @NonNull final PostModel model) {


                DisplayMetrics displaymetrics = new DisplayMetrics();
                (getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                //if you need three fix imageview in width

                holder.postTitle.setText(model.getProductkey());

                Glide.with(getActivity().getApplicationContext()).load(model.getImgurl()).into(holder.postImage);


//                holder.mMinusBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        count=holder.mTotalCount.getText().toString();
//                        countInt=Integer.parseInt(count);
//                        incrementalCount=countInt--;
//
//                        holder.mTotalCount.setText(countInt+"");
//                    }
//                });


            }

            @NonNull
            @Override
            public ViewPostFragment.CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post_item_layout, viewGroup, false);
                ViewPostFragment.CustomersViewHolder customersViewHolder = new ViewPostFragment.CustomersViewHolder(view);
                return customersViewHolder;
            }
        };

        mCustomerRecycVw.setAdapter(adapter);
        adapter.startListening();

    }


    public static class CustomersViewHolder extends RecyclerView.ViewHolder {


        ImageView postImage;
        TextView postTitle;


        public CustomersViewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = (ImageView) itemView.findViewById(R.id.UserImage);
            postTitle = (TextView) itemView.findViewById(R.id.userName);

        }
    }



}
