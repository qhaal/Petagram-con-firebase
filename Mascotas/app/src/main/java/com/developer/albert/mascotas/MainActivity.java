package com.developer.albert.mascotas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.developer.albert.mascotas.adapter.PageAdapter;
import com.developer.albert.mascotas.fragment.PerfilFragment;
import com.developer.albert.mascotas.fragment.RecyclerViewFragment;
import com.developer.albert.mascotas.model.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if(toolbar!=null)
        {
            setSupportActionBar(toolbar);
        }
    }
    private ArrayList<Fragment> agregarFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.casa);
        tabLayout.getTabAt(1).setIcon(R.drawable.perro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.mFavoritos:
                Intent intent = new Intent(this,MascotasFavoritas.class);
                startActivity(intent);
                break;
            case R.id.mContacto:
                Intent intentContacto = new Intent(this,ContactoEnviarCorreo.class);
                startActivity(intentContacto);
                break;
            case R.id.mAcercade:
                Intent intentAcercade = new Intent(this,AcercaDe.class);
                startActivity(intentAcercade);
                break;
            case R.id.mConfigurarCuenta:
                Intent intentConfigurarCuenta = new Intent(this,ActivityConfigurarCuenta.class);
                startActivity(intentConfigurarCuenta);
                finish();
                break;
            case R.id.mRecibirNotificaciones:
                Intent intentNotificaciones = new Intent(this,RecibirNotificaciones.class);
                startActivity(intentNotificaciones);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
