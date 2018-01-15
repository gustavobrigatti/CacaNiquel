package com.example.cacaniquel;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class InicioActivity extends Activity {

	TextView tvCaca, tvNiquel;
	Button btnNiveis, btnInstru;
	ImageButton ibRanking;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		
		tvCaca = (TextView) findViewById(R.id.tvCaca);
		tvNiquel = (TextView) findViewById(R.id.tvNiquel);
		btnNiveis = (Button) findViewById(R.id.btnNiveis);
		btnInstru = (Button) findViewById(R.id.btnInstru);
		ibRanking = (ImageButton) findViewById(R.id.ibRanking);
		
		Typeface fonte = Typeface.createFromAsset(getAssets(), "mario.ttf");
		Typeface fonte2 = Typeface.createFromAsset(getAssets(), "BouCollege.ttf");
		
		tvCaca.setTypeface(fonte2);
		tvNiquel.setTypeface(fonte2);
		btnNiveis.setTypeface(fonte2);
		btnInstru.setTypeface(fonte2);
		
		btnNiveis.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarNiveis();
			}
		});
		btnInstru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarInstru();
			}
		});
		ibRanking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarRanking();
			}
		});
	}
	private void chamarNiveis(){
		startActivity(new Intent(this, NiveisActivity.class));
	}
	private void chamarInstru(){
		startActivity(new Intent(this, InstruInicioActivity.class));
	}
	private void chamarRanking(){
		startActivity(new Intent(this, RankingActivity.class));
	}
}
