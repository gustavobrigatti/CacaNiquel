package com.example.cacaniquel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import sp.senai.br.dao.JogadorDAO;

public class JogoActivity extends Activity {
	
	int iRand1, iRand2, iRand3, iScore = 200, iRodadas = 0;
	boolean lStart = false, lStatus = false;
	ImageButton ibInstru, ibInicia, ibFreeMoney, ibMusica;
	TextView tvScore, tvMoedasGanhas, tvRodadas, tvPreco;
	private AnimationDrawable roll_01, roll_02, roll_03;
	ImageView ivRet1, ivRet2, ivRet3, ivPremio;
	MediaPlayer musica;
	AlertDialog.Builder adbNome, adb;
	EditText etNome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
		
		Typeface fonte = Typeface.createFromAsset(getAssets(), "mario.ttf");
		Typeface fonte2 = Typeface.createFromAsset(getAssets(), "BouCollege.ttf");
		musica = MediaPlayer.create(this,R.drawable.mario);
		musica.setLooping(true);
		musica.setVolume(0.07f,0.07f);
		musica.start();
		
		//etNome.setId(R.id.etNome);
		
		tvScore = (TextView) findViewById(R.id.tvScore);
		tvMoedasGanhas = (TextView) findViewById(R.id.tvMoedasGanhas);
		tvRodadas = (TextView) findViewById(R.id.tvRodadas);
		tvPreco = (TextView) findViewById(R.id.tvPreco);
		ibInicia = (ImageButton) findViewById(R.id.ibInicia);
		ibInstru = (ImageButton) findViewById(R.id.btnInstru);
		ibFreeMoney = (ImageButton) findViewById(R.id.ibFreeMoney);
		ibMusica = (ImageButton) findViewById(R.id.ibMusica);
		ivRet1 = (ImageView) findViewById(R.id.imRet1);
		ivRet2 = (ImageView) findViewById(R.id.imRet2);
		ivRet3 = (ImageView) findViewById(R.id.imRet3);
		
		tvScore.setTypeface(fonte2);
		tvMoedasGanhas.setTypeface(fonte2);
		tvRodadas.setTypeface(fonte2);
		tvPreco.setTypeface(fonte2);
		
		ibFreeMoney.setEnabled(false);
		tvMoedasGanhas.setText(null);
		
		ibInicia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				comparaStatusRoleta();
			}
		});
		
		ibInstru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarInstru();
			}
		});
		ibFreeMoney.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				criaDialogo();
			}
		});
		ibMusica.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(lStatus){
					ibMusica.setBackgroundResource(R.drawable.musica);
					musica.start();
					lStatus = false;
				}else{
					ibMusica.setBackgroundResource(R.drawable.semmusica);
					musica.pause(); 
					lStatus = true;
				}
			}
		});
	}
	@Override
    protected void onStop() {
    	// TODO Auto-generated method stub
		super.onStop();
    	musica.pause(); 
    }
	@Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
		super.onDestroy();
    	musica.stop();
    }
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		musica.start();
	}
//------------------------------------------------------------------
	public void colocaTexto(){
		tvScore.setText(""+iScore);
		tvRodadas.setText("RODADAS: "+iRodadas);
	}
//------------------------------------------------------------------
	public void comparaResultado(){
		if(iRand1 == 3 && iRand2 == 3 && iRand3 == 3){
			iScore += 100;
			tvMoedasGanhas.setText("VOCÊ GANHOU 100 MOEDAS");
			colocaTexto();
		}
		else if(iRand1 == iRand2 && iRand2 == iRand3 && iRand1 == iRand3){
			iScore += 40;
			tvMoedasGanhas.setText("VOCÊ GANHOU 40 MOEDAS");
			colocaTexto();
		}else if(iRand1 == iRand2 || iRand2 == iRand3 || iRand1 == iRand3){
			iScore += 20;
			tvMoedasGanhas.setText("VOCÊ GANHOU 20 MOEDAS");
			colocaTexto();
		}
	}
//------------------------------------------------------------------
	public void sorteiaImagem(){
		iRand1 = (int) (Math.random()*4)+1;
		iRand2 = (int) (Math.random()*4)+1;
		iRand3 = (int) (Math.random()*4)+1;
	}
//------------------------------------------------------------------
	public void comparaStatusRoleta(){
		if(lStart){
			roll_01.stop();
			roll_02.stop();
			roll_03.stop();
			colocaImagem();
			comparaResultado();
			comparaScore();
			lStart = false;
		}else{
			tvMoedasGanhas.setText(null);
			iScore -= 20;
			iRodadas++;
			colocaTexto();
			ivRet1.setBackgroundResource(R.drawable.roleta_01);
			roll_01 = (AnimationDrawable) ivRet1.getBackground();
			ivRet2.setBackgroundResource(R.drawable.roleta_02);
			roll_02 = (AnimationDrawable) ivRet2.getBackground();
			ivRet3.setBackgroundResource(R.drawable.roleta_03);
			roll_03 = (AnimationDrawable) ivRet3.getBackground();
			roll_01.start();
			roll_02.start();
			roll_03.start();
			lStart = true;
			sorteiaImagem();
		}
	}
//------------------------------------------------------------------
	public void comparaScore(){
		if(iScore == 0){
			int iPremio[] = {R.drawable.bala};
			AlertDialog.Builder alertadd = new AlertDialog.Builder(JogoActivity.this);
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.mensagem, (ViewGroup)findViewById(R.id.root));
			TextView tvTexto = (TextView) layout.findViewById(R.id.tvTexto);
			tvTexto.setText("VOCÊ GANHOU UMA BALA !!");
			ivPremio = (ImageView) layout.findViewById(R.id.ivPremio);
			ivPremio.setImageResource(iPremio[0]);
			alertadd.show();
			ibInicia.setEnabled(false);
			ibInicia.setBackgroundResource(R.drawable.exclamacaoescuro);
			ibFreeMoney.setEnabled(true);
			ibFreeMoney.setBackgroundResource(R.drawable.moedamario);
			adb = new AlertDialog.Builder(JogoActivity.this);
			adb.setView(layout);
			adb.show();
		}
	}
//------------------------------------------------------------------
	public void colocaImagem(){
		switch (iRand1) {
		case 1:
			ivRet1.setBackgroundResource(R.drawable.primeiro);
			break;
		case 2:
			ivRet1.setBackgroundResource(R.drawable.segundo);
			break;
		case 3:
			ivRet1.setBackgroundResource(R.drawable.terceiro);
			break;
		case 4:
			ivRet1.setBackgroundResource(R.drawable.quarto);
			break;
		default:
			break;
		}
		
		switch (iRand2) {
		case 1:
			ivRet2.setBackgroundResource(R.drawable.primeiro);
			break;
		case 2:
			ivRet2.setBackgroundResource(R.drawable.segundo);
			break;
		case 3:
			ivRet2.setBackgroundResource(R.drawable.terceiro);
			break;
		case 4:
			ivRet2.setBackgroundResource(R.drawable.quarto);
			break;
		default:
			break;
		}
		
		switch (iRand3) {
		case 1:
			ivRet3.setBackgroundResource(R.drawable.primeiro);
			break;
		case 2:
			ivRet3.setBackgroundResource(R.drawable.segundo);
			break;
		case 3:
			ivRet3.setBackgroundResource(R.drawable.terceiro);
			break;
		case 4:
			ivRet3.setBackgroundResource(R.drawable.quarto);
			break;
		default:
			break;
		}
	}
//------------------------------------------------------------------
	private void chamarInstru(){
		startActivity(new Intent(this, InstruActivity.class));
	}
//------------------------------------------------------------------
	public void criaDialogo(){
		adbNome = new AlertDialog.Builder(JogoActivity.this);
		adbNome.setTitle("Digite seu nome");
		etNome = new EditText(JogoActivity.this);
		adbNome.setView(etNome);
		adbNome.setPositiveButton("OK", positivo);
		adbNome.setNegativeButton("Cacelar", negativo);
		adbNome.show();
	}
//------------------------------------------------------------------
	public void chamaRanking(){
		startActivity(new Intent(this, RankingActivity.class));
		finish();
	}
	android.content.DialogInterface.OnClickListener positivo = new android.content.DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if(etNome.getText().toString().equalsIgnoreCase("")){
				
			}else{
				Jogador jogador = new Jogador();
				jogador.setsNome(etNome.getText().toString());
				jogador.setiRodadas(iRodadas);
				JogadorDAO dao = new JogadorDAO(JogoActivity.this);
				dao.insereJogador(jogador);
				dao.close();
				chamaRanking();
				finish();
			}
		}
	};
	android.content.DialogInterface.OnClickListener negativo = new android.content.DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			
		}
	};
//------------------------------------------------------------------
}
