package pages

import PessoaService
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.FormPessoaComponent
import kotlinx.coroutines.launch
import models.Curso
import models.Pessoa
import services.CursoService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleDetailPage(navController: NavHostController, pessoa: Pessoa) {
    val cursosVinculados = remember { mutableStateListOf<Curso>() }
    val cursosDisponiveis = remember { mutableStateListOf<Curso>() }
    val cursosFiltrados = remember { mutableStateListOf<Curso>() }
    var searchQuery by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        CursoService.getCursos()
            .onSuccess {
                cursosFiltrados.addAll(it)
                cursosDisponiveis.addAll(it)
            }
            .onFailure {
                // faz nada
            }
        CursoService.getCursosByPessoaId(pessoa.id).onSuccess {
            cursosVinculados.addAll(it)
        }
            .onFailure {
                // faz nada
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Pessoa") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    item {
                        FormPessoaComponent(
                            onAddPessoa = {
                                // Lógica para add pessoa, se necessário
                            },
                            onClearClick = {
                                // Lógica para limpar os campos, se necessário
                            }, pessoa
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    item {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                                cursosFiltrados.clear()
                                cursosFiltrados.addAll(cursosDisponiveis.filter { curso ->
                                    curso.nome.contains(searchQuery, ignoreCase = true)
                                })
                            },
                            label = { Text("Pesquisar") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    items(cursosFiltrados) { curso ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Checkbox(
                                checked = cursosVinculados.contains(curso),
                                onCheckedChange = { isChecked ->
                                    if (isChecked) {
                                        scope.launch {
                                            PessoaService.addCursoToPessoa(pessoa, curso.id).onSuccess {
                                                cursosVinculados.add(curso)
                                            }
                                                .onFailure {
                                                    // faz nada
                                                }
                                        }
                                    } else {
                                        cursosVinculados.remove(curso)
                                        scope.launch {
                                            PessoaService.removeCursoToPessoa(pessoa, curso.id).onSuccess {
                                                // faz nada
                                            }
                                                .onFailure {
                                                    // faz nada
                                                }
                                        }
                                    }
                                    cursosFiltrados.sortByDescending {
                                        cursosVinculados.contains(
                                            it
                                        )
                                    }
                                }
                            )
                            Text(curso.nome, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    )
}
