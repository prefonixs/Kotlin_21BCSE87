package com.example.andbatch1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.filled.ThumbUpAlt
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.andbatch1.ui.theme.AndBatch1Theme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Math.round

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    private val productVM: ProductViewModel by viewModels()
    private val userVM: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Column(modifier = Modifier.padding(10.dp)) {
//                Spacer(modifier = Modifier.height(10.dp))
//                Text(
//                    text = "Products", fontSize = 25.sp, fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//                val products by productVM.products.observeAsState(emptyList())
//                LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
//                    items(products.size) { index ->
//                        ProductItem(product = products[index])
//                    }
//                }
//            }
            Column(modifier = Modifier.padding(10.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Users", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                val users by userVM.users.observeAsState(emptyList())
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(2.dp)
                )
                {
                    items(users) { user ->
                        UserList(user = user)
                    }
                }
            }
        }
    }

    @Composable
    fun ProductItem(product: Product) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(239, 244, 250)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(500.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredHeight(210.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = product.image, contentDescription = "Product image",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(
                    text = product.title, color = Color.Black, fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis, maxLines = 3,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.category, color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(text = "$${product.price}", color = Color(5, 174, 221), fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = "In Stock", color = Color(152, 196, 122))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.StarRate,
                        contentDescription = "Rating",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "${product.rating.rate}(${product.rating.count})",
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Button(
                        onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                243,
                                42,
                                128,
                                255
                            )
                        )
                    ) {
                        Text(text = "Add to cart")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.AddShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                    Row() {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    185,
                                    185,
                                    190,
                                    255
                                )
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite"
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    185,
                                    185,
                                    190,
                                    255
                                )
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Visibility,
                                contentDescription = "View"
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun UserList(user: User) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(238, 242, 247, 255)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(200.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row {
                    Text(text = user.username, color = Color.Black, fontSize = 30.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(235, 238, 235, 255)
                        ),
                        modifier = Modifier.height(30.dp).width(60.dp),
                        elevation = ButtonDefaults.buttonElevation(1.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "",
                            tint = Color(0, 252, 0, 255),
                            modifier = Modifier.size(10.dp)
                        )
                        Text(text = "Active", fontSize = 10.sp, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${user.name.firstname} ${user.name.lastname}", color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(text = user.email, color = Color(5, 174, 221), fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = user.phone, color = Color(152, 196, 122))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                224,
                                222,
                                222
                            )
                        ),
                        modifier = Modifier.width(70.dp),
                        elevation = ButtonDefaults.buttonElevation(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            tint = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                127,
                                181,
                                233
                            )
                        ),
                        modifier = Modifier.width(70.dp),
                        elevation = ButtonDefaults.buttonElevation(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUpAlt,
                            contentDescription = "",
                            tint = Color(255, 203, 130)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(135, 223, 120, 255)
                        ),
                        modifier = Modifier.width(70.dp),
                        elevation = ButtonDefaults.buttonElevation(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Message,
                            contentDescription = "",
                            tint = Color(145, 143, 143, 255)
                        )
                    }
                }
            }
        }
    }
}

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Float,
    val count: Int
)

data class User(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val phone: String,
    val name: Name
)

data class Name(
    val firstname: String,
    val lastname: String
)

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("users")
    suspend fun getUsers(): List<User>
}

object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class ProductRepository(private val apiService: ApiService) {
    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
}

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products
    private val repository = ProductRepository(RetrofitClient.apiService)

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val productList = repository.getProducts()
                _products.postValue(productList)
                println(productList)

            } catch (e: Exception) {

            }
        }
    }
}

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users
    private val repository = UserRepository(RetrofitClient.apiService)

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                _users.postValue(userList)
                println(userList)

            } catch (e: Exception) {

            }
        }
    }
}