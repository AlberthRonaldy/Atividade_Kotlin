package com.example.atividade

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun LayoutCadastro(navController: NavController?) {
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var qtdEstoque by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cadastre seu Produto: ", fontSize = 25.sp)

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = nome, onValueChange = { nome = it }, label = { Text(text = "Nome: ") })

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = categoria, onValueChange = { categoria = it }, label = { Text(text = "Categoria: ") })

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = preco, onValueChange = { preco = it }, label = { Text(text = "Preço: ") })

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = qtdEstoque, onValueChange = { qtdEstoque = it }, label = { Text(text = "Quantidade em estoque: ") })

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            if (nome.isNotBlank() && categoria.isNotBlank() && preco.isNotBlank() && qtdEstoque.isNotBlank()){

                val precoDouble = preco.toDoubleOrNull()
                val qtdEstoqueInt = qtdEstoque.toIntOrNull()

                if (precoDouble != null && qtdEstoqueInt != null) {
                    Estoque.listaProdutos += (Produto(nome, categoria, precoDouble, qtdEstoqueInt))
                    Toast.makeText(context, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    //navController.navigate("listaProdutos")
                }else if (qtdEstoque.toInt() < 1 || preco.toInt() <= 0){
                    Toast.makeText(context, "Preço deve ser maior que 0 e quantidade maior que 0", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(context, "Preço e Quantidade devem ser numéricos", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(context,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Cadastrar Produto")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate("listaProdutos") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Ir para a Lista")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LayoutCadastro(navController = null)
}