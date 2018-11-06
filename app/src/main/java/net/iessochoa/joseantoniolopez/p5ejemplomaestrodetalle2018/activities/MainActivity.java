package net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.iessochoa.joseantoniolopez.p5ejemplomaestrodetalle2018.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Esta aplicación nos permite ver como se trabaja con Fragments para la visualización
 * de Maestro-Detalle creando activities específicas según el tamaño de la pantalla y la orientación
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnEstatico, R.id.btnDinamico})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btnEstatico:
                i = new Intent(MainActivity.this, MaestroDetalleEstaticoActivity.class);
                startActivity(i);
                break;
            case R.id.btnDinamico:
                i = new Intent(MainActivity.this, MaestroDetalleDinamicoActivity.class);
                startActivity(i);
                break;
        }
    }
}
