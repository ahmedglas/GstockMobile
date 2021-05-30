package com.example.gstock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SuiviEmpruntAdapter extends RecyclerView.Adapter<SuiviEmpruntAdapter.SuiviEmpruntViewHolder> {

    List<SuiviEmpruntModel> newList;

    public SuiviEmpruntAdapter(List<SuiviEmpruntModel> newList) {
        this.newList = newList;
    }

    @NonNull
    @Override
    public SuiviEmpruntViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SuiviEmpruntViewHolder(inflater.inflate(R.layout.suivi_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuiviEmpruntViewHolder holder, int position) {
        holder.bindView(newList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    class SuiviEmpruntViewHolder extends RecyclerView.ViewHolder {

        TextView nameMembre, telMembre, nameComposant;

        SuiviEmpruntViewHolder(View view) {
            super(view);
            nameMembre = view.findViewById(R.id.name_membre);
            telMembre = view.findViewById(R.id.tel_membre);
            nameComposant = view.findViewById(R.id.name_composant);
        }

        void bindView(SuiviEmpruntModel item, int position) {
            nameMembre.setText(item.nomMembre);
            telMembre.setText(item.tel);
            nameComposant.setText(item.nomC);
        }
    }
}
