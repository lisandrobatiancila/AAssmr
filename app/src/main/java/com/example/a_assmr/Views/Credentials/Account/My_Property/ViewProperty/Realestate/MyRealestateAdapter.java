package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate;

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
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Model.MyRealestateLists;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller.RemoveCertainPropController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.RemoveServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.UpdateCertainRealestate;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.ViewCertainRealestate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRealestateAdapter extends RecyclerView.Adapter<MyRealestateHolder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<MyRealestateLists> realestatesLists;
    Common common;
    AlertDialog dialog;
    ItemViewModel itemViewModel;
    public MyRealestateAdapter(Context context, ArrayList<MyRealestateLists> realestatesLists) {
        this.context = context;
        this.realestatesLists = realestatesLists;
        this.inflater = LayoutInflater.from(context);
        this.common = new Common();
        this.itemViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ItemViewModel.class);
    }

    @NonNull
    @Override
    public MyRealestateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRealestateHolder(inflater.inflate(R.layout.account_my_realestate_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRealestateHolder holder, int position) {
        int pos = position;
        holder.txtOwner.setText("Owner: "+realestatesLists.get(position).getRealestateOwner());
        holder.txtPropertyType.setText("Property type: Realestate");
        holder.txtRealestateType.setText("Realestate: "+realestatesLists.get(position).getRealestateType());
        holder.txtStatus.setText("Status: "+realestatesLists.get(position).getPropertyStatus());

        String image = realestatesLists.get(position).getRealestateIMG();
        String[] imageArray = image.substring(1, image.length()-1).split((","));
        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.realestateImage);

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
                        Intent i_view_certain_realesate = new Intent(context, ViewCertainRealestate.class);
                        i_view_certain_realesate.putExtra("image", realestatesLists.get(pos).getRealestateIMG());
                        i_view_certain_realesate.putExtra("owner", realestatesLists.get(pos).getRealestateOwner());
                        i_view_certain_realesate.putExtra("contactno", realestatesLists.get(pos).getRealestateContactno());
                        i_view_certain_realesate.putExtra("location", realestatesLists.get(pos).getRealestateLocation());
                        i_view_certain_realesate.putExtra("downpayment", realestatesLists.get(pos).getRealestateDownpayment());
                        i_view_certain_realesate.putExtra("paid", realestatesLists.get(pos).getRealestateInstallmentpaid());
                        i_view_certain_realesate.putExtra("duration", realestatesLists.get(pos).getRealestateInstallmentduration());
                        i_view_certain_realesate.putExtra("delinquent", realestatesLists.get(pos).getRealestateDelinquent());
                        i_view_certain_realesate.putExtra("type", realestatesLists.get(pos).getRealestateType());
                        i_view_certain_realesate.putExtra("description", realestatesLists.get(pos).getRealestateDescription());
                        i_view_certain_realesate.putExtra("assumptionCount", realestatesLists.get(pos).getPropertyCount());
                        i_view_certain_realesate.putExtra("propertyStatus", realestatesLists.get(pos).getPropertyStatus());

                        context.startActivity(i_view_certain_realesate);
                    break;
                    case R.id.updateProperty:
                        Intent i_update_certain_realesate = new Intent(context, UpdateCertainRealestate.class);
                        i_update_certain_realesate.putExtra("propertyID", realestatesLists.get(pos).getPropertyID());
                        i_update_certain_realesate.putExtra("image", realestatesLists.get(pos).getRealestateIMG());
                        i_update_certain_realesate.putExtra("owner", realestatesLists.get(pos).getRealestateOwner());
                        i_update_certain_realesate.putExtra("contactno", realestatesLists.get(pos).getRealestateContactno());
                        i_update_certain_realesate.putExtra("location", realestatesLists.get(pos).getRealestateLocation());
                        i_update_certain_realesate.putExtra("downpayment", realestatesLists.get(pos).getRealestateDownpayment());
                        i_update_certain_realesate.putExtra("paid", realestatesLists.get(pos).getRealestateInstallmentpaid());
                        i_update_certain_realesate.putExtra("duration", realestatesLists.get(pos).getRealestateInstallmentduration());
                        i_update_certain_realesate.putExtra("delinquent", realestatesLists.get(pos).getRealestateDelinquent());
                        i_update_certain_realesate.putExtra("type", realestatesLists.get(pos).getRealestateType());
                        i_update_certain_realesate.putExtra("description", realestatesLists.get(pos).getRealestateDescription());
                        i_update_certain_realesate.putExtra("assumptionCount", realestatesLists.get(pos).getPropertyCount());
                        i_update_certain_realesate.putExtra("propertyStatus", realestatesLists.get(pos).getPropertyStatus());

                        context.startActivity(i_update_certain_realesate);
                    break;
                    case R.id.removeProperty:
                        int propertyID = realestatesLists.get(pos).getPropertyID();

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View view = inflater.inflate(R.layout.prompt_property_remove, null);
                        builder.setView(view);
                        builder.setTitle("Message");

                        LinearLayout layoutConfirmation = view.findViewById(R.id.layoutConfirmation);
                        LinearLayout layoutRemove = view.findViewById(R.id.layoutRemoving);
                        LinearLayout layoutActions = view.findViewById(R.id.layoutActions);

                        TextView txtPropInfo1, txtPropInfo2;
                        txtPropInfo1 = view.findViewById(R.id.txtPropInfo1);
                        txtPropInfo2 = view.findViewById(R.id.txtPropInfo2);

                        txtPropInfo1.setText("Property type: Realestate");
                        txtPropInfo2.setText("Realestate type: "+realestatesLists.get(pos).getRealestateType());
                        Button btnYes = view.findViewById(R.id.btnYes);
                        Button btnNo = view.findViewById(R.id.btnNo);

                        btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                layoutConfirmation.setVisibility(View.GONE);
                                layoutActions.setVisibility(View.GONE);
                                layoutRemove.setVisibility(View.VISIBLE);

                                RemoveCertainPropController rcpc = new RemoveCertainPropController(context, dialog);
                                GenericClass<MyRealestate> genericClass = new GenericClass<>();
                                genericClass.setCertainClass(new MyRealestate());
                                rcpc.removeCertainProperty(propertyID, "realesate", genericClass);
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.show();
                    break;
                }
                return true;
            }
        });

        itemViewModel.getSelectedItem().observe((LifecycleOwner) context, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof RemoveServerResponse) {
                if(((RemoveServerResponse) obj).code == 200) {
                    Toast.makeText(context, "Successfully removed.", Toast.LENGTH_SHORT).show();
                    realestatesLists.remove(pos);
                }
                else
                    Toast.makeText(context, "Removing failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return realestatesLists.size();
    }
}
