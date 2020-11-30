package com.example.apphamburgueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantidade = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void subtrair(View view) {
        quantidade = quantidade - 1;
        displayQuantidade(quantidade);
    }

    public void somar(View view) {
        quantidade = quantidade + 1;
        displayQuantidade(quantidade);
    }

    public void displayQuantidade(int qtdHamburguer){
        TextView qtdTextview = (TextView) findViewById(R.id.quantidade_tv);
        qtdTextview.setText(""+qtdHamburguer);
    }

    public void displayPedido(String pedido) {
        TextView pedidoTextview = (TextView) findViewById(R.id.resumo_pedido);
        pedidoTextview.setText(pedido);
    }

    public void enviarPedido(View view){
        EditText campoNome = (EditText) findViewById(R.id.campo_nome);
        String nome = campoNome.getText().toString();

        CheckBox checkboxBacon = (CheckBox) findViewById(R.id.bacon);
        boolean temBacon = checkboxBacon.isChecked();

        CheckBox checkboxQueijo = (CheckBox) findViewById(R.id.queijo);
        boolean temQueijo = checkboxQueijo.isChecked();

        CheckBox checkboxOnion = (CheckBox) findViewById(R.id.onionrings);
        boolean temOnion = checkboxOnion.isChecked();

        int valor = calcularPreco(temBacon, temQueijo, temOnion);

        String mensagem = "Nome" + nome;
        mensagem += "\nBacon? " + temBacon;
        mensagem += "\nQueijo? " + temQueijo;
        mensagem += "\nOnion Rings? " + temOnion;
        mensagem += "\nQuantidade? " + quantidade;
        mensagem += "\n\nTotal: R$ " + valor;

        TextView pedidoTextview = (TextView) findViewById(R.id.resumo_pedido);
        pedidoTextview.setText(mensagem);

        Intent intent = new Intent(Intent.ACTION_SENDTO); //Email
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nome);
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        if(intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);

    }

    public int calcularPreco(boolean temBacon, boolean temQueijo, boolean temOnion){
        int precoBase = 20;
        if(temBacon)
            precoBase += 2;
        if(temQueijo)
            precoBase += 2;
        if(temOnion)
            precoBase += 3;

        return precoBase*quantidade;

    }
}