package com.example.cacaniquel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class NiveisActivity extends Activity {

	TextView tvBordao, tvEscolha;
	ImageButton ibMario, ibPacMan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_niveis);
		
		tvBordao = (TextView) findViewById(R.id.tvBordao);
		tvEscolha = (TextView) findViewById(R.id.tvEscolha);
		ibMario = (ImageButton) findViewById(R.id.ibMario);
		ibPacMan = (ImageButton) findViewById(R.id.ibPacMan);
		
		Typeface fonte = Typeface.createFromAsset(getAssets(), "mario.ttf");
		Typeface fonte2 = Typeface.createFromAsset(getAssets(), "BouCollege.ttf");
		
		tvBordao.setTypeface(fonte2);
		tvEscolha.setTypeface(fonte2);
		
		ibMario.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v){
				chamarMario();
			}
		});
		
		ibPacMan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamaPC();
				
			}
		});
	}
	private void chamarMario(){
		startActivity(new Intent(this, JogoActivity.class));
	}
	private void chamaPC(){
		startActivity(new Intent(this, PacManActivity.class));
	}
}
