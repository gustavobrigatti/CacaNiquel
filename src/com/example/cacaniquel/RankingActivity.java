package com.example.cacaniquel;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import sp.senai.br.dao.JogadorDAO;

public class RankingActivity extends Activity {

	ListView lvRanking;
	private Jogador jogador;
	private List<Jogador> alJogador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		
		lvRanking = (ListView) findViewById(R.id.lvListaRanking);
		
		//Avisa o layout que o componente possui um menu de contexto.
		registerForContextMenu(lvRanking);
		
		JogadorDAO dao = new JogadorDAO(this);
		alJogador=dao.getLista();
		dao.close();
		
		//ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alAluno);
		ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, alJogador);
		lvRanking.setAdapter(adapter);
	}
}
