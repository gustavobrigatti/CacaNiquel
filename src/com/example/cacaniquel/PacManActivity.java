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

public class PacManActivity extends Activity {
	
	int iRand1, iRand2, iRand3, iScore = 200, iPontosGanhos, iRodadaspm = 0;
	ImageButton ibIniciar, ibCereja, ibInstru, ibMusicaPM;
	private AnimationDrawable roll_01, roll_02, roll_03;
	ImageView ivRet1, ivRet2, ivRet3, ivPremio;
	boolean lStart = false, lStatus = false;
	TextView tvScore, tvTextoScore, tvPontosGanhos, tvRodadaspm, tvPrecopm;
	MediaPlayer musicapm;
	AlertDialog.Builder adbNome, adb;
	EditText etNome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pac_man);		
		
		ibIniciar = (ImageButton) findViewById(R.id.ibIniciarcp);
		ibCereja = (ImageButton) findViewById(R.id.ibCereja);
		ivRet1 = (ImageView) findViewById(R.id.ivRetpc1);
		ivRet2 = (ImageView) findViewById(R.id.ivRetpc2);
		ivRet3 = (ImageView) findViewById(R.id.ivRetpc3);
		tvScore = (TextView) findViewById(R.id.tvScore);
		tvTextoScore = (TextView) findViewById(R.id.tvTextoScore);
		tvPontosGanhos = (TextView) findViewById(R.id.tvPontosGanhos);
		tvRodadaspm = (TextView) findViewById(R.id.tvRodadasPM);
		tvPrecopm = (TextView) findViewById(R.id.tvPrecopm);
		ibInstru = (ImageButton) findViewById(R.id.ibInstrupm);
		ibMusicaPM = (ImageButton) findViewById(R.id.ibMusicapm);
		
		Typeface fonte = Typeface.createFromAsset(getAssets(), "DietPacMan.ttf");
		Typeface fonte2 = Typeface.createFromAsset(getAssets(), "BouCollege.ttf");
		musicapm = MediaPlayer.create(this,R.drawable.pm);
		musicapm.setLooping(true);
		musicapm.setVolume(0.07f,0.07f);
		musicapm.start();
		tvTextoScore.setTypeface(fonte);
		tvScore.setTypeface(fonte2);
		tvPontosGanhos.setTypeface(fonte2);
		tvRodadaspm.setTypeface(fonte2);
		tvPrecopm.setTypeface(fonte2);
		
		ibCereja.setEnabled(false);
		
		tvPontosGanhos.setText(null);
		
		ibIniciar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				comparaStatusRoleta();
			}
		});
		ibCereja.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				criaDialogo();
			}
		});
		ibInstru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamaInstru();
			}
		});
		ibMusicaPM.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(lStatus){
					ibMusicaPM.setBackgroundResource(R.drawable.somon);
					musicapm.start();
					lStatus = false;
				}else{
					ibMusicaPM.setBackgroundResource(R.drawable.somoff);
					musicapm.pause(); 
					lStatus = true;
				}
			}
		});
	}
	@Override
    protected void onStop() {
    	// TODO Auto-generated method stub
		super.onStop();
    	musicapm.pause(); 
    }
	@Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
		super.onDestroy();
    	musicapm.stop();
    }
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		musicapm.start();
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
			tvPontosGanhos.setText(null);
			iScore -= 20;
			iRodadaspm++;
			colocaTexto();
			ivRet1.setBackgroundResource(R.drawable.roletapm_01);
			roll_01 = (AnimationDrawable) ivRet1.getBackground();
			ivRet2.setBackgroundResource(R.drawable.roletapm_02);
			roll_02 = (AnimationDrawable) ivRet2.getBackground();
			ivRet3.setBackgroundResource(R.drawable.roletapm_03);
			roll_03 = (AnimationDrawable) ivRet3.getBackground();
			roll_01.start();
			roll_02.start();
			roll_03.start();
			lStart = true;
			sorteiaImagem();
		}
	}
	//------------------------------------------------------------------
	public void colocaImagem(){
		switch (iRand1) {
		case 1:
			ivRet1.setBackgroundResource(R.drawable.primeiropc);
			break;
		case 2:
			ivRet1.setBackgroundResource(R.drawable.segundopc);
			break;
		case 3:
			ivRet1.setBackgroundResource(R.drawable.terceiropc);
			break;
		case 4:
			ivRet1.setBackgroundResource(R.drawable.quartopc);
			break;
		default:
			break;
		}
		
		switch (iRand2) {
		case 1:
			ivRet2.setBackgroundResource(R.drawable.primeiropc);
			break;
		case 2:
			ivRet2.setBackgroundResource(R.drawable.segundopc);
			break;
		case 3:
			ivRet2.setBackgroundResource(R.drawable.terceiropc);
			break;
		case 4:
			ivRet2.setBackgroundResource(R.drawable.quartopc);
			break;
		default:
			break;
		}
		
		switch (iRand3) {
		case 1:
			ivRet3.setBackgroundResource(R.drawable.primeiropc);
			break;
		case 2:
			ivRet3.setBackgroundResource(R.drawable.segundopc);
			break;
		case 3:
			ivRet3.setBackgroundResource(R.drawable.terceiropc);
			break;
		case 4:
			ivRet3.setBackgroundResource(R.drawable.quartopc);
			break;
		default:
			break;
		}
	}
	//------------------------------------------------------------------
	public void comparaResultado(){
		if(iRand1 == 3 && iRand2 == 3 && iRand3 == 3){
			iScore += 100;
			tvPontosGanhos.setText("VOCÊ GANHOU 100 PONTOS");
			colocaTexto();
		}
		else if(iRand1 == iRand2 && iRand2 == iRand3 && iRand1 == iRand3){
			iScore += 40;
			tvPontosGanhos.setText("VOCÊ GANHOU 40 PONTOS");
			colocaTexto();
		}else if(iRand1 == iRand2 || iRand2 == iRand3 || iRand1 == iRand3){
			iScore += 20;
			tvPontosGanhos.setText("VOCÊ GANHOU 20 PONTOS");
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
	public void colocaTexto(){
		tvScore.setText(""+iScore);
		tvRodadaspm.setText("RODADAS: "+iRodadaspm);
	}
	//------------------------------------------------------------------
	public void comparaScore(){
		if(iScore == 0){
			int iPremio[] = {R.drawable.bala};
			AlertDialog.Builder alertadd = new AlertDialog.Builder(PacManActivity.this);
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.mensagem, (ViewGroup)findViewById(R.id.root));
			TextView tvTexto = (TextView) layout.findViewById(R.id.tvTexto);
			tvTexto.setText("VOCÊ GANHOU UMA BALA !!");
			ivPremio = (ImageView) layout.findViewById(R.id.ivPremio);
			ivPremio.setImageResource(iPremio[0]);
			ibIniciar.setEnabled(false);
			ibIniciar.setBackgroundResource(R.drawable.pcescuro);
			ibCereja.setEnabled(true);
			ibCereja.setBackgroundResource(R.drawable.cereja);
			adb = new AlertDialog.Builder(PacManActivity.this);
			adb.setView(layout);
			adb.show();
		}
	}
	//------------------------------------------------------------------
	private void chamaInstru(){
		startActivity(new Intent(this, InstruPmActivity.class));
	}
	public void criaDialogo(){
		adbNome = new AlertDialog.Builder(PacManActivity.this);
		adbNome.setTitle("Digite seu nome");
		etNome = new EditText(PacManActivity.this);
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
				jogador.setiRodadas(iRodadaspm);
				JogadorDAO dao = new JogadorDAO(PacManActivity.this);
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
