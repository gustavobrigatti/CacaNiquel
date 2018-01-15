package com.example.cacaniquel;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class InstruActivity extends Activity {

	TextView tvInstrucoes, tvInicia, tvMoeda, tvMusica, tvDuas, tvTres, tvEstrela;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instru);
		
		tvInstrucoes = (TextView) findViewById(R.id.tvInstrucoes);
		tvInicia = (TextView) findViewById(R.id.tvInicia);
		tvMoeda = (TextView) findViewById(R.id.tvMoeda);
		tvMusica = (TextView) findViewById(R.id.tvMusica);
		tvDuas = (TextView) findViewById(R.id.tvDuas);
		tvTres = (TextView) findViewById(R.id.tvTres);
		tvEstrela = (TextView) findViewById(R.id.tvEstrela);
		
		Typeface fonte = Typeface.createFromAsset(getAssets(), "mario.ttf");
		Typeface fonte2 = Typeface.createFromAsset(getAssets(), "BouCollege.ttf");
		
		tvInstrucoes.setTypeface(fonte2);
		tvInicia.setTypeface(fonte2);
		tvMoeda.setTypeface(fonte2);
		tvMusica.setTypeface(fonte2);
		tvDuas.setTypeface(fonte2);
		tvTres.setTypeface(fonte2);
		tvEstrela.setTypeface(fonte2);
	}
}
