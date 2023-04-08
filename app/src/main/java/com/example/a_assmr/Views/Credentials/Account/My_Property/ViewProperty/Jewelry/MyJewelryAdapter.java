package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model.MyJewelryLists;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller.RemoveCertainPropController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.RemoveServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.UpdateCertainJewelry;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.ViewCertainJewelry;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyJewelryAdapter extends RecyclerView.Adapter<MyJewelryHolder> {
    Context context;
    LayoutInflater inflater;
    List<MyJewelryLists> jewelryLists;
    Common common;
    AlertDialog dialog;
    int propertyID;
    ItemViewModel itemViewModel;
    public MyJewelryAdapter(Context context, List<MyJewelryLists> jewelryLists) {
        this.context = context;
        this.jewelryLists = jewelryLists;
        this.inflater = LayoutInflater.from(context);
        this.common = new Common();
        this.itemViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ItemViewModel.class);
    }

    @NonNull
    @Override
    public MyJewelryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyJewelryHolder(inflater.inflate(R.layout.account_my_jewelry_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyJewelryHolder holder, int position) {
        int pos = position;
        holder.txtOwner.setText("Owner: "+jewelryLists.get(position).getJewelryOwner());
        holder.txtJewelryName.setText("Jewelry name: "+jewelryLists.get(position).getJewelryName());
        holder.txtJewelryModel.setText("Jewelry model: "+jewelryLists.get(position).getJewelryModel());
        holder.txtStatus.setText("Status: "+jewelryLists.get(position).getPropertyStatus());

        String image = jewelryLists.get(position).getJewelryIMG();
        String[] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.propImage);

        PopupMenu popupMenu = new PopupMenu(context, holder.threeDots);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        holder.threeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.viewProperty:
                        Intent i_view_jewelry = new Intent(context, ViewCertainJewelry.class);
                        i_view_jewelry.putExtra("image", jewelryLists.get(pos).getJewelryIMG());
                        i_view_jewelry.putExtra("owner", jewelryLists.get(pos).getJewelryOwner());
                        i_view_jewelry.putExtra("contactno", jewelryLists.get(pos).getJewelryContactno());
                        i_view_jewelry.putExtra("jewelry_name", jewelryLists.get(pos).getJewelryName());
                        i_view_jewelry.putExtra("jewelry_model", jewelryLists.get(pos).getJewelryModel());
                        i_view_jewelry.putExtra("location", jewelryLists.get(pos).getJewelryLocation());
                        i_view_jewelry.putExtra("downpayment", jewelryLists.get(pos).getJewelryDownpayment());
                        i_view_jewelry.putExtra("paid", jewelryLists.get(pos).getJewelryInstallmentpaid());
                        i_view_jewelry.putExtra("duration", jewelryLists.get(pos).getJewelryInstallmentduration());
                        i_view_jewelry.putExtra("description", jewelryLists.get(pos).getJewelryDescription());
                        i_view_jewelry.putExtra("assumptionCount", jewelryLists.get(pos).getAssumptionCount());
                        i_view_jewelry.putExtra("propertyStatus", jewelryLists.get(pos).getPropertyStatus());

                        context.startActivity(i_view_jewelry);
                    break;
                    case R.id.updateProperty:
                        Intent i_update_jewelry = new Intent(context, UpdateCertainJewelry.class);
                        i_update_jewelry.putExtra("image", jewelryLists.get(pos).getJewelryIMG());
                        i_update_jewelry.putExtra("owner", jewelryLists.get(pos).getJewelryOwner());
                        i_update_jewelry.putExtra("contactno", jewelryLists.get(pos).getJewelryContactno());
                        i_update_jewelry.putExtra("jewelry_name", jewelryLists.get(pos).getJewelryName());
                        i_update_jewelry.putExtra("jewelry_model", jewelryLists.get(pos).getJewelryModel());
                        i_update_jewelry.putExtra("location", jewelryLists.get(pos).getJewelryLocation());
                        i_update_jewelry.putExtra("downpayment", jewelryLists.get(pos).getJewelryDownpayment());
                        i_update_jewelry.putExtra("paid", jewelryLists.get(pos).getJewelryInstallmentpaid());
                        i_update_jewelry.putExtra("duration", jewelryLists.get(pos).getJewelryInstallmentduration());
                        i_update_jewelry.putExtra("delinquent", jewelryLists.get(pos).getJewelryDelinquent());
                        i_update_jewelry.putExtra("description", jewelryLists.get(pos).getJewelryDescription());
                        i_update_jewelry.putExtra("assumptionCount", jewelryLists.get(pos).getAssumptionCount());
                        i_update_jewelry.putExtra("propertyStatus", jewelryLists.get(pos).getPropertyStatus());
                        i_update_jewelry.putExtra("propertyID", jewelryLists.get(pos).getPropertyID());
                        context.startActivity(i_update_jewelry);
                    break;
                    case R.id.removeProperty:
                        propertyID = jewelryLists.get(pos).getPropertyID();

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View view = inflater.inflate(R.layout.prompt_property_remove, null);

                        builder.setView(view);
                        builder.setCancelable(false);
                        builder.setTitle("Message");
                        dialog = builder.create();

                        LinearLayout layoutConfirmation = view.findViewById(R.id.layoutConfirmation);
                        LinearLayout layoutRemoving = view.findViewById(R.id.layoutRemoving);
                        LinearLayout layoutActions = view.findViewById(R.id.layoutActions);

                        TextView txtPropInfo1, txtPropInfo2;
                        txtPropInfo1 = view.findViewById(R.id.txtPropInfo1);
                        txtPropInfo2 = view.findViewById(R.id.txtPropInfo2);

                        txtPropInfo1.setText("Jewelry name: "+jewelryLists.get(pos).getJewelryName());
                        txtPropInfo2.setText("Jewelrly type: "+jewelryLists.get(pos).getJewelryModel());

                        Button btnYes = view.findViewById(R.id.btnYes);
                        Button btnNo = view.findViewById(R.id.btnNo);
                        btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                layoutConfirmation.setVisibility(View.GONE);
                                layoutActions.setVisibility(View.GONE);
                                layoutRemoving.setVisibility(View.VISIBLE);

                                RemoveCertainPropController rcpc = new RemoveCertainPropController(context, dialog);
                                GenericClass<MyJewelry> genericClass = new GenericClass<>();
                                genericClass.setCertainClass(new MyJewelry());

                                rcpc.removeCertainProperty(propertyID, "jewelry", genericClass);
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    break;
                }
                return true;
            }
        });
        itemViewModel.getSelectedItem().observe((LifecycleOwner) context, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof RemoveServerResponse) {
                Toast.makeText(context, ((RemoveServerResponse) obj).code+" ok ", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jewelryLists.size();
    }
}
