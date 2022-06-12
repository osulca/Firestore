package com.example.appfirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {
    private List<Usuario> misDatos;
    private LayoutInflater layoutInflater;
    private Context context;

    public UsuarioAdapter(List<Usuario> misDatos, Context context) {
        this.misDatos = misDatos;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_ropa, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.juntarData(misDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return misDatos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvUsuario;
        ImageView ivImagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.producto);
            tvUsuario = itemView.findViewById(R.id.precio);
            ivImagen = itemView.findViewById(R.id.imagen);
        }

        public void juntarData(Usuario item){
            tvUsername.setText(item.getUsername());
            tvUsuario.setText(item.getNombres());
            ivImagen.setImageResource(R.drawable.image);
        }
    }
}
