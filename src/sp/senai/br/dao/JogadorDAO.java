package sp.senai.br.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.cacaniquel.Jogador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class JogadorDAO extends SQLiteOpenHelper {
	
	int iCont = 0;
	
	private static final int VERSAO = 1;
	private static final String TABELA = "Jogadores";
	private static final String DATABASE = "JogadoresCacaNiquel";

	public JogadorDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sSQL = "CREATE TABLE "+TABELA+" (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, "+
				"rodadas INT)";
		db.execSQL(sSQL);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sSQL = "DROP TABLE IF EXISTS "+TABELA;
		db.execSQL(sSQL);
		onCreate(db);
	}
	public void insereJogador(Jogador jogador){
		ContentValues valores = new ContentValues();
		valores.put("nome", jogador.getsNome());
		valores.put("rodadas", jogador.getiRodadas());
		//inserindo no banco
		getWritableDatabase().insert(TABELA, null, valores);
	}
	public List<Jogador> getLista(){
		List<Jogador> alJogador = new ArrayList<Jogador>();
		//Armazena na variavel toda a informação que o SELECT trará.
		Cursor c = getReadableDatabase().rawQuery("SELECT * FROM "+TABELA+" order by rodadas desc;",null);
		while (c.moveToNext() && iCont <= 9){
			Jogador jogador = new Jogador();
			jogador.setlId(c.getLong(c.getColumnIndex("id")));
			jogador.setsNome(c.getString(c.getColumnIndex("nome")));
			jogador.setiRodadas(c.getInt(c.getColumnIndex("rodadas")));
			alJogador.add(jogador);
			iCont++;
		}
		c.close();
		return alJogador;
	}

}
