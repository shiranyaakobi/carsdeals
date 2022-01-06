package com.example.carsapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    private List<Car> cars;
    public CarAdapter(List<Car> cars)
    {
        this.cars = cars;
    }

    public void AddCar(Car car)
    {
        cars.add(car);
        notifyDataSetChanged();
        DatabaseManager.StoreData(cars);
    }

    public void DeleteCar(int position)
    {
        cars.remove(position);
        notifyDataSetChanged();
        DatabaseManager.StoreData(cars);
    }
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_cardview,parent,false);
        CarViewHolder vh = new CarViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);

        holder.avatar.setImageResource(car.getPhoto());
        holder.brand.setText(car.getBrand());
        holder.model.setText(car.getModel());
        holder.year.setText(car.getYear());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),CarsViewActivity.class);
                i.putExtra("car",car);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                                holder.avatar,
                                "avatarTrasnition"
                        );
                v.getContext().startActivity(i,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
