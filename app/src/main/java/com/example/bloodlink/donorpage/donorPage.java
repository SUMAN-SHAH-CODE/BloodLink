package com.example.bloodlink.donorpage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlink.R;

import java.util.ArrayList;

public class donorPage extends AppCompatActivity {
ArrayList<DonorModel> arrDonor=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_page);

        RecyclerView recyclerView=findViewById(R.id.recyclerdonor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Suman Thakur","23","AB-","1","Dhangadhi"));
        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Ram","22","B+","1","Dhangadhi"));
        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Hari Kumar","24","AB-","1","Dhangadhi"));
        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Kishan Joshi","35","B-","1","Attarya"));
        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Rahul Bhatta","29","AB","1","Attarya"));
        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Ravi Chy","27","O+","1","Attarya"));
        arrDonor.add(new DonorModel(R.drawable.baseline_person_24,"Bibek Dewal","28","AB","1","Attarya"));
        RecyclerDonorAdapter adapter=new RecyclerDonorAdapter(this,arrDonor);
        recyclerView.setAdapter(adapter);
    }
}