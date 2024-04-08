package com.example.ghosthome.device;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ghosthome.R;
import com.example.ghosthome.device.adapter.CustomItemAdapter;
import com.example.ghosthome.device.adapter.CustomPagerAdapter;
import com.example.ghosthome.device.adapter.CustomSpinnerAdapter;
import com.example.ghosthome.device.model.Item;
import com.example.ghosthome.device.model.SpinnerItem;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddDeviceFragment extends Fragment implements View.OnClickListener {


    CardView control_cardview;
    String selectedSpinnerItem;
    String DistributionBoxGet;

    private RecyclerView recyclerView;
    private CustomItemAdapter adapterItem;
    List<Item> itemList;
    public static View.OnClickListener onClickListener;
    Spinner customSpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_device, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onClickListener = this;
        recyclerView = view.findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);

//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        itemList = new ArrayList<>();
        adapterItem = new CustomItemAdapter(getActivity(), itemList, onClickListener);
        recyclerView.setAdapter(adapterItem);

    }

    private List<SpinnerItem> getSpinnerItems() {
        List<SpinnerItem> items = new ArrayList<>();
        items.add(new SpinnerItem("Smart Pump", R.drawable.smart_pump));
        items.add(new SpinnerItem("Distribution box", R.drawable.distribution_box));
        items.add(new SpinnerItem("Smart lock", R.drawable.smart_lock));
        return items;
    }

    public void OtherDialogShow() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_device_other_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView title = dialog.findViewById(R.id.dialogtext);
        title.setText("Add " + selectedSpinnerItem);

        ViewPager viewPager = dialog.findViewById(R.id.viewPager);
        LinearLayout dotLayout = dialog.findViewById(R.id.dotLayout); // Reference to the dot indicator layout
        CustomPagerAdapter adapter = new CustomPagerAdapter(getContext(), dotLayout, "To add " + selectedSpinnerItem + " to ghost home . Follow the instructions ");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Update dot indicators when page changes
                for (int i = 0; i < adapter.getCount(); i++) {
                    adapter.getDot(i).setImageDrawable(
                            getContext().getDrawable(i == position ? R.drawable.dot_indicator_active : R.drawable.dot_indicator_inactive)
                    );
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (viewPager.getCurrentItem() == adapter.getCount() - 1) {
                        // Display success message
                        DialogShowSuccess();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }


    public void DialogShowSuccess() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_device_success_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView title = dialog.findViewById(R.id.text_success);
        title.setText(selectedSpinnerItem + " has successfully \n" +
                "been added");

        dialog.findViewById(R.id.closeBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                dialog.dismiss();
                ProgressBar();
            }
        });

        dialog.show();
    }

    public void ProgressBar() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_device_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        MaterialCardView cvDialogs = dialog.findViewById(R.id.cvDialog);
        MaterialCardView layoutDone = dialog.findViewById(R.id.layoutDone);
        TextView add_device_info = dialog.findViewById(R.id.add_device_info);
        new CountDownTimer(1000, 500) {
            public void onTick(long millisUntilFinished) {
                cvDialogs.setVisibility(View.VISIBLE);
                layoutDone.setVisibility(View.GONE);
            }

            public void onFinish() {
                cvDialogs.setVisibility(View.GONE);
                layoutDone.setVisibility(View.VISIBLE);

                new CountDownTimer(1000, 500) {
                    public void onTick(long millisUntilFinished) {
                        cvDialogs.setVisibility(View.GONE);
                        layoutDone.setVisibility(View.VISIBLE);
                        add_device_info.setText(selectedSpinnerItem+" Set Up Complete");
                    }

                    public void onFinish() {
                        String selectedItem = customSpinner.getSelectedItem().toString();

                        // Get the image corresponding to the selected item
                        int imageResourceId = getImageForSelectedItem(selectedItem);

                        // Add the selected item to the RecyclerView list
                        //adapterItem.addItem(new Item(selectedItem, imageResourceId));
                        itemList.add(new Item(selectedSpinnerItem, imageResourceId));
                        // Notify adapter about the changes
                        adapterItem.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }.start();

                dialog.show();
            }
        }.start();

        dialog.show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.addBtn) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.add_device_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            TextInputEditText DistributionBox = dialog.findViewById(R.id.textInputEditText);

             customSpinner = dialog.findViewById(R.id.spinner);
            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(getActivity(), R.layout.dropdown_add_device);
            adapter.addAll(getSpinnerItems());
            customSpinner.setAdapter(adapter);


            customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SpinnerItem selectedItem = (SpinnerItem) parent.getItemAtPosition(position);

                    // Get the text from the selected item
                    selectedSpinnerItem = selectedItem.getText();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });

            dialog.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //your business logic
//                        if(DistributionBox.getText().toString().isEmpty()) {
//                            DistributionBox.setError("This field is required");
//
//                        }
//                        else {
//                            OtherDialogShow();
//                            dialog.dismiss();
//                        }

                    DistributionBoxGet = DistributionBox.getText().toString();
                    OtherDialogShow();
                    dialog.dismiss();

                }
            });
            dialog.findViewById(R.id.closeBt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //your business logic
                    dialog.dismiss();
                }
            });


            dialog.show();
        }
    }


    private int getImageForSelectedItem(String selectedItem) {
        // Add your logic here to map selected items to image resource IDs
        // For simplicity, assuming image resource IDs are predefined
        switch (selectedSpinnerItem) {
            case "Smart Pump":
                return R.drawable.smart_pump;
            case "Distribution box":
                return R.drawable.distribution_box;
            case "Smart lock":
                return R.drawable.smart_lock;
            // Add more cases for additional items as needed
            default:
                return R.drawable.smart_pump; // Default image resource ID

        }
    }
}