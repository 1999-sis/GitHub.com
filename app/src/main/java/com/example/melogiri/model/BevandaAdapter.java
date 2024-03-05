package com.example.melogiri.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.melogiri.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class BevandaAdapter extends RecyclerView.Adapter<BevandaAdapter.ViewHolder> {

    private final RecycleViewInterface recycleViewInterface;
    private List<Bevanda> productList;
    private Carrello carrello;

    public BevandaAdapter(List<Bevanda> productList, Carrello carrello, RecycleViewInterface recycleViewInterface) {
        this.productList = productList;
        this.carrello = carrello;
        this.recycleViewInterface = recycleViewInterface;
    }

    public BevandaAdapter(List<Bevanda> productList, com.example.melogiri.view.HomePageActivity homePageActivity) {
        recycleViewInterface = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bevanda bevanda = productList.get(position);
        holder.productNameTextView.setText(bevanda.getNome());
        holder.productDescriptionTextView.setText(bevanda.getDescrizione());

        Picasso.get()
                .load(bevanda.getUrlPhoto())
                .resize(50, 50)
                .centerCrop()
                .into(holder.productImage);

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrello.aggiungiProdotto(bevanda);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /*
    @Override
    public int getItemCount() {
        return productList.size();
    }
*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productDescriptionTextView;
        ImageView productImage;
        Button addToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            productDescriptionTextView = itemView.findViewById(R.id.descrizione);
            productImage = itemView.findViewById(R.id.imageView2);
            addToCartButton = itemView.findViewById(R.id.button3); // Assumi che l'ID del pulsante sia "buttonAggiungiAlCarrello"

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onClickItem(pos);


                        }
                    }
                }
            });
        }
    }
}